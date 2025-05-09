package com.swuber.functionality;

import java.util.Random;

import com.swuber.db.DatabaseConfig;

import java.sql.SQLException;
import java.util.Date;
import jakarta.persistence.*;;

@Entity
@Table(name = "Ride")
@Inheritance(strategy = InheritanceType.JOINED)
// @DiscriminatorColumn(name = "ride_type")
public class Ride {

	// el mafrood da yb2a enum (mksl)
	public static final String STATUS_PENDING = "PENDING";
	public static final String STATUS_ACCEPTED = "ACCEPTED";
	public static final String STATUS_PAID = "PAID";
	public static final String STATUS_COMPLETED = "COMPLETED";

	// private static int rideIDGen = 0;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rideId")
	private int rideId;
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	@ManyToOne
	@JoinColumn(name = "driverId")
	private Driver driver;
	@ManyToOne
	@JoinColumn(name = "vehicleId")
	private Vehicle vehicle;
	@ManyToOne
	@JoinColumn(name = "paymentId")
	private Payment payment;

	private String startLocation;
	private String endLocation;
	private double cost;
	private String status;
	private boolean isShuttle;
	private static DatabaseConfig dbConnect;
	private float rating;
	private Date createdAt;

	// Default constructor required by Hibernate
	public Ride() {
		// Required empty constructor for JPA/Hibernate
	}

	public Ride(User user, String startLocation, String endLocation) {
		// this.rideID = rideIDGen + 1;
		this.user = user;
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.status = STATUS_PENDING;
		this.isShuttle = false;
		this.cost = calculateCost();
		this.createdAt = new Date();
	}

	public static void setDBConnect(DatabaseConfig dbConfig) {
		dbConnect = dbConfig;
	}

	private double calculateCost() {
		return isShuttle ? 80.0 : 50 + new Random().nextDouble() * 150;
	}

	public boolean processPayment(Payment payment, String discountCode) {
		if (payment != null && payment.processPayment(discountCode)) {
			this.payment = payment;
			this.status = STATUS_PAID;
			return true;
		}
		return false;
	}

	public void completeRide() {
		this.status = STATUS_COMPLETED;
	}

	public void rateRide(float rating, Driver driver) throws SQLException {
		if (rating >= 0.0f && rating <= 5.0f) {
			this.rating = rating;

		}

	}

	public float getRating() {
		return this.rating;
	}

	public void setRating(float rating) {
		EntityManager em = dbConnect.getEntityManager();
		try {
			em.getTransaction().begin();
			this.rating = rating;
			em.merge(this);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	public int getRideID() {
		return rideId;
	}

	public User getUser() {
		return user;
	}

	public Driver getDriver() {
		return driver;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public String getStartLocation() {
		return startLocation;
	}

	public String getEndLocation() {
		return endLocation;
	}

	public double getCost() {
		return cost;
	}

	public String getStatus() {
		return status;
	}

	public boolean isShuttle() {
		return isShuttle;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setDriver(Driver driver) {
		EntityManager em = dbConnect.getEntityManager();
		try {
			em.getTransaction().begin();
			this.driver = driver;
			em.merge(this);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	public void setVehicle(Vehicle vehicle) {
		EntityManager em = dbConnect.getEntityManager();
		try {
			em.getTransaction().begin();
			this.vehicle = vehicle;
			em.merge(this);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	public void setStatus(String status) {
		EntityManager em = dbConnect.getEntityManager();
		try {
			em.getTransaction().begin();
			this.status = status;
			em.merge(this);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	public void setPayment(Payment payment) {
		EntityManager em = dbConnect.getEntityManager();
		try {
			em.getTransaction().begin();
			this.payment = payment;
			em.merge(this);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	public static Ride addRide(User user, String startLocation, String endLocation) throws Exception {
		EntityManager em = dbConnect.getEntityManager();
		Ride ride = null;
		try {
			em.getTransaction().begin();

			// Create a new User instance from the functionality package
			ride = new Ride(user, startLocation, endLocation);
			em.persist(ride);
			em.getTransaction().commit();
			System.out.println("Ride added successfully,");
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println("Error adding user: " + e.getMessage());
			throw e;
		}
		return ride;
	}

}