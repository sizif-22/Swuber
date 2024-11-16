package functionality;

import java.util.ArrayList;
import java.util.List;

public class User {
	private static int userIdCounter = 1;
	private int userId;
	private String name;
	private String email;
	private String phoneNumber;
	private String password;
	private RideHistory rideHistory;
	private List<Card> savedPaymentOptions;
	private static List<User> registeredUsers = new ArrayList<>();

	public User(String name, String email, String phoneNumber, String password) {
		this.userId = userIdCounter++;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.rideHistory = new RideHistory();
		this.savedPaymentOptions = new ArrayList<>();
	}

	public void addRideToHistory(Ride ride) {
		if (ride != null && "COMPLETED".equals(ride.getStatus())) {
			this.rideHistory.addRide(ride);
		}
	}

	public String getName() {
		return this.name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String newPhoneNumber) {
		this.phoneNumber = newPhoneNumber;
	}

	public RideHistory getRideHistory() {
		return this.rideHistory;
	}

	public void addCard(String cardName, String cardNumber, String expirationDate, String cardHolderName) {
		Card newCard = new Card(cardName, cardNumber, expirationDate, cardHolderName);
		savedPaymentOptions.add(newCard);
		System.out.println("Card added successfully: " + newCard.getCard());
	}

	public void removeCard(String cardName) {
		for (Card card : savedPaymentOptions) {
			if (card.cardName.equalsIgnoreCase(cardName)) {
				savedPaymentOptions.remove(card);
				break;
			}
		}
	}

	public static void register(String name, String email, String phoneNumber, String password) {
		for (User user : registeredUsers) {
			if (user.email.equalsIgnoreCase(email)) {
				System.out.println("A user with this email already exists.");
				return;
			}
		}
		User newUser = new User(name, email, phoneNumber, password);
		registeredUsers.add(newUser);
		System.out.println("User registered successfully.");
	}

	public static User login(String email, String password) {
		for (User user : registeredUsers) {
			if (user.email.equalsIgnoreCase(email) && user.password.equals(password)) {
				System.out.println("Login successful. Welcome, " + user.name + "!");
				return user;
			}
		}
		System.out.println("Invalid email or password.");
		return null;
	}
}
