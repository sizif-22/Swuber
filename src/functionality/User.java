package functionality;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class User {
	private static final String USER_FILE_PATH = "./users.txt";
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

	public Ride requestRide(RidePlanner planner, String startLocation, String destination) {
		Ride ride = new Ride(this, startLocation, destination);
		Driver matchedDriver = planner.matchDriverToRide(ride);
		if (matchedDriver != null) {
			ride.setDriver(matchedDriver);
			ride.setVehicle(matchedDriver.getVehicle());
			ride.setStatus("ACCEPTED");
			planner.addRide(ride);
		} else {
			System.out.println("No available drivers for the requested location.");
			return null;
		}
		ride.setStatus("PENDING");
		return ride;
	} // A: no error handling here if no matched driver - this will cause runtime
		// errors

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
			if (card.getCardName().equalsIgnoreCase(cardName)) {
				savedPaymentOptions.remove(card);
				break;
			}
		}
	}

	public List<Card> getCards() {
		return savedPaymentOptions;
	}

	public static void register(String name, String email, String phoneNumber, String password) {
		loadUsersFromFile(); // Load existing users

		for (User user : registeredUsers) {
			if (user.email.equalsIgnoreCase(email)) {
				JOptionPane.showMessageDialog(null, "A user with this email already exists.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return; // Stop registration if email exists
			}
		}

		User newUser = new User(name, email, phoneNumber, password);
		registeredUsers.add(newUser);
		writeUserToFile(newUser);

		JOptionPane.showMessageDialog(null, "User registered successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
	}

	private static void writeUserToFile(User user) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE_PATH, true))) {
			writer.write(user.userId + "," + user.name + "," + user.email + "," + user.phoneNumber + "," + user.password);
			writer.newLine();
		} catch (IOException e) {
			System.err.println("Error writing user data to file: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Error writing user data to file", "Error", JOptionPane.ERROR_MESSAGE); // Show
																																																									// error
																																																									// message
		}
	}

	private static void loadUsersFromFile() {
		registeredUsers.clear();
		try (Scanner scanner = new Scanner(new File(USER_FILE_PATH))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] userData = line.split(",");
				if (userData.length == 5) {
					try {
						User user = new User(userData[1], userData[2], userData[3], userData[4]);
						user.userId = Integer.parseInt(userData[0]);
						registeredUsers.add(user);
					} catch (NumberFormatException ex) {
						System.err.println("Invalid user ID format in file: " + line);
					}
				} else {
					System.err.println("Invalid user data format in file: " + line);
				}
			}
		} catch (IOException e) {
			// handle file not found exception
			System.err.println("Error loading user data from file: " + e.getMessage());

		}
	}

	public static User login(String email, String password) {
		loadUsersFromFile(); // Load users from file before login attempt

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
