package com.swuber.functionality;

import java.util.*;
import javax.swing.JOptionPane;
import com.swuber.db.*; // New ODB package
import jakarta.persistence.*;

@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue
	@Column(name = "userId")
	private int userId; // OID (Object ID) managed by the ODB
	@Column(name = "name")
	private String name;

	@Column(unique = true, name = "email")
	private String email;
	@Column(name = "phoneNumber")
	private String phoneNumber;
	@Column(name = "password")
	private String password;
	@Column(name = "createdAt")
	private Date createdAt;

	// Fixed mappedBy to match the case-sensitive field name in Card class
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Card> savedPaymentOptions;

	// Fixed mappedBy to match the case-sensitive field name in Ride class
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Ride> rideHistory;

	// Database connection manager
	private static DatabaseConfig odbManager;

	public User() {
		// Default constructor for ODB
		this.savedPaymentOptions = new ArrayList<Card>();
		this.rideHistory = new ArrayList<Ride>();
		this.createdAt = new Date();
	}

	public User(String name, String email, String phoneNumber, String password) {
		this(); // Call default constructor
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}

	public static void setODBManager(DatabaseConfig manager) {
		odbManager = manager;
	}

	public Ride requestRide(String startLocation, String destination) {
		EntityManager em = odbManager.getEntityManager();

		try {
			em.getTransaction().begin();

			// Create new ride
			Ride ride = new Ride(this, startLocation, destination);

			// Find available driver in the location
			TypedQuery<Driver> query = em.createQuery(
					"SELECT d FROM Driver d WHERE d.isAvailable = true AND d.location = :loc",
					Driver.class);
			query.setParameter("loc", startLocation);
			query.setMaxResults(1);

			List<Driver> availableDrivers = query.getResultList();
			Driver matchedDriver = null;

			if (!availableDrivers.isEmpty()) {
				matchedDriver = availableDrivers.get(0);
				matchedDriver.setAvailable(false);
				ride.setDriver(matchedDriver);
				ride.setVehicle(matchedDriver.getVehicle());
				ride.setStatus(Ride.STATUS_ACCEPTED);

				// Save ride
				em.persist(ride);

				// Add to collections
				this.rideHistory.add(ride);
				matchedDriver.getRideHistory().add(ride);

				em.getTransaction().commit();
				return ride;
			} else {
				em.getTransaction().rollback();
				System.out.println("No available drivers for the requested location.");
				return null;
			}
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	public String getName() {
		return this.name;
	}

	public int getUserId() {
		return userId;
	}

	public void setName(String newName) {
		EntityManager em = odbManager.getEntityManager();
		try {
			em.getTransaction().begin();
			this.name = newName;
			em.merge(this); // Update this object in the database
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		EntityManager em = odbManager.getEntityManager();
		try {
			em.getTransaction().begin();
			this.email = email;
			em.merge(this);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String newPhoneNumber) {
		EntityManager em = odbManager.getEntityManager();
		try {
			em.getTransaction().begin();
			this.phoneNumber = newPhoneNumber;
			em.merge(this);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	public List<Ride> getRideHistory() {
		EntityManager em = odbManager.getEntityManager();
		try {
			TypedQuery<Ride> query = em.createQuery(
					"SELECT r FROM Ride r WHERE r.user = :user",
					Ride.class);
			query.setParameter("user", this);
			List<Ride> allRides = query.getResultList();
			for (Ride ride : allRides) {
				System.out.println(ride.getDriver().getDriverId());
			}
			System.out.println("length is : " + allRides.size());
			return allRides;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>(); // Return empty list instead of null for safety
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
	}

	public void addCard(String cardName, String cardNumber, String expirationDate, String cardHolderName) {
		EntityManager em = odbManager.getEntityManager();

		try {
			em.getTransaction().begin();

			Card newCard = new Card(cardName, cardNumber, expirationDate, cardHolderName, this);
			em.persist(newCard);

			this.savedPaymentOptions.add(newCard);
			em.merge(this);

			em.getTransaction().commit();
			System.out.println("Card added successfully: " + newCard.getCard());
			savedPaymentOptions = Card.getCards(this);
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	public void removeCard(String cardName) {
		EntityManager em = odbManager.getEntityManager();

		try {
			em.getTransaction().begin();

			// Find the card by name
			Card cardToRemove = null;
			for (Card card : savedPaymentOptions) {
				if (card.getCardName().equalsIgnoreCase(cardName)) {
					cardToRemove = card;
					break;
				}
			}

			if (cardToRemove != null) {
				savedPaymentOptions.remove(cardToRemove);
				em.remove(cardToRemove);
				em.merge(this);
			}

			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	// public Set<Card> getCards() {
	// return Collections.unmodifiableSet(new HashSet<>(savedPaymentOptions));
	// }
	public List<Card> getCards() {
		savedPaymentOptions = Card.getCards(this);
		return savedPaymentOptions;
	}

	public static void register(String name, String email, String phoneNumber, String password) {
		EntityManager em = odbManager.getEntityManager();

		try {
			// Check if email already exists
			TypedQuery<Long> query = em.createQuery(
					"SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class);
			query.setParameter("email", email);
			long count = query.getSingleResult();

			if (count > 0) {
				JOptionPane.showMessageDialog(null, "A user with this email already exists.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			em.getTransaction().begin();

			User newUser = new User(name, email, phoneNumber, password);
			em.persist(newUser);

			em.getTransaction().commit();

			JOptionPane.showMessageDialog(null, "User registered successfully.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error registering user.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static User login(String email, String password) {
		EntityManager em = odbManager.getEntityManager();

		try {
			TypedQuery<User> query = em.createQuery(
					"SELECT u FROM User u WHERE u.email = :email", User.class);
			query.setParameter("email", email);
			query.setMaxResults(1);

			List<User> results = query.getResultList();

			if (!results.isEmpty()) {
				User user = results.get(0);
				if (user.password.equals(password)) {
					System.out.println("Login successful. Welcome, " + user.name + "!");
					return user;
				}
			}

			System.out.println("Invalid email or password.");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static User getUser(long userId) {
		EntityManager em = odbManager.getEntityManager();

		try {
			return em.find(User.class, userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void addUser(String name, String email, String phoneNumber, String password) throws Exception {
		EntityManager em = odbManager.getEntityManager();

		try {
			em.getTransaction().begin();

			// Create a new User instance from the functionality package
			User user = new User(name, email, phoneNumber,
					password);
			em.persist(user);
			em.getTransaction().commit();
			System.out.println("User added successfully, name: " + name);
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println("Error adding user: " + e.getMessage());
			throw e;
		}
	}
}