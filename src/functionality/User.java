package functionality;

import java.util.*;
import java.sql.*;
import javax.swing.JOptionPane;
import db.*;

public class User {
	// private static int userIdCounter = 1;
	int userId;
	private String name;
	private String email;
	private String phoneNumber;
	private String password;
	private RideHistory rideHistory;
	private List<Card> savedPaymentOptions;
	private static List<User> registeredUsers = new ArrayList<>();
	private static DBConfig dbConnect;

	public User(String name, String email, String phoneNumber, String password, DBConfig dbConnect) {
		// this.userId = userIdCounter++;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.rideHistory = new RideHistory(this, null, dbConnect);
		this.savedPaymentOptions = new ArrayList<>();
		User.dbConnect = dbConnect;
	}

	public Ride requestRide(RidePlanner planner, String startLocation, String destination) throws SQLException {
		Ride ride = new Ride(this, startLocation, destination);
		Driver matchedDriver = planner.matchDriverToRide(ride);
		if (matchedDriver != null) {
			ride.setDriver(matchedDriver);
			try {
				ride.setVehicle(matchedDriver.getVehicle());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ride.setStatus("ACCEPTED");
			planner.addRide(ride);
			ride.setStatus("PENDING");
			return ride;
		} else {
			System.out.println("No available drivers for the requested location.");
			return null;
		}
	}

	// public void addRideToHistory(Ride ride) {
	// if (ride != null && "COMPLETED".equals(ride.getStatus())) {
	// this.rideHistory.addRide(ride);
	// }
	// }

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

	public void addCard(String cardName, String cardNumber, String expirationDate, String cardHolderName)
			throws SQLException {
		String sql = "insert into card (cardName ,cardNumber,expirationDate, cardHolderName,userId) values ('"
				+ cardName + "','" + cardNumber + "','" + expirationDate + "','" + cardHolderName + "'," + userId
				+ ");";
		dbConnect.statement.executeUpdate(sql);
		String sql2 = "select cardId from Card order by cardId desc limit 1";
		ResultSet rs = dbConnect.statement.executeQuery(sql2);
		rs.next();
		Card newCard = new Card(cardName, cardNumber, expirationDate, cardHolderName, userId, dbConnect);
		newCard.cardId = rs.getInt("cardId");
		savedPaymentOptions.add(newCard);
		System.out.println("Card added successfully: " + newCard.getCard());
	}

	public void removeCard(String cardName) throws SQLException {
		for (Card card : savedPaymentOptions) {
			if (card.getCardName().equalsIgnoreCase(cardName)) {
				String sql = "delete from card where cardId = " + card.cardId;
				dbConnect.statement.executeUpdate(sql);
				savedPaymentOptions.remove(card);
				break;
			}
		}
	}

	public void loadCardsFromDB() throws SQLException {
		if (dbConnect == null) {
			System.err.println("Database connection not initialized.");
			return;
		}
		savedPaymentOptions.clear();
		ResultSet resultSet = dbConnect.statement.executeQuery("select * from card where userId = " + userId + ";");
		while (resultSet.next()) {
			int cardId = resultSet.getInt("cardId");
			int userId = resultSet.getInt("userId");
			String cardName = resultSet.getString("cardName");
			String cardNumber = resultSet.getString("cardNumber");
			String expirationDate = resultSet.getString("expirationDate");
			String cardHolderName = resultSet.getString("cardHolderName");
			Card newCard = new Card(cardName, cardNumber, expirationDate, cardHolderName, userId, dbConnect);
			newCard.cardId = cardId;
			savedPaymentOptions.add(newCard);
		}
	}

	public List<Card> getCards() throws SQLException {
		loadCardsFromDB();
		return savedPaymentOptions;
	}

	public static void setDBConnect(DBConfig dbConfig) {
		dbConnect = dbConfig;
	}

	public static void register(String name, String email, String phoneNumber, String password) throws SQLException {
		if (dbConnect == null) {
			JOptionPane.showMessageDialog(null, "Database connection not initialized.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		loadUsersFromDB();
		for (User user : registeredUsers) {
			if (user.email.equalsIgnoreCase(email)) {
				JOptionPane.showMessageDialog(null, "A user with this email already exists.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		User newUser = new User(name, email, phoneNumber, password, dbConnect);
		registeredUsers.add(newUser);
		dbConnect.addUser(name, email, phoneNumber, password);

		JOptionPane.showMessageDialog(null, "User registered successfully.", "Success",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private static void loadUsersFromDB() throws SQLException {
		if (dbConnect == null) {
			System.err.println("Database connection not initialized.");
			return;
		}
		registeredUsers.clear();
		ResultSet resultSet = dbConnect.statement.executeQuery("select * from Users;");
		while (resultSet.next()) {
			int id = resultSet.getInt("userId");
			String userName = resultSet.getString("name");
			String userEmail = resultSet.getString("email");
			String userPassword = resultSet.getString("password");
			String userPhoneNumber = resultSet.getString("phoneNumber");
			User user = new User(userName, userEmail, userPhoneNumber, userPassword, dbConnect);
			user.userId = id;
			registeredUsers.add(user);
			System.out.println(
					"user: " + id + " " + userName + " " + userEmail + " " + userPhoneNumber + " " + userPassword);
		}
	}

	public static User login(String email, String password) throws SQLException {
		if (dbConnect == null) {
			JOptionPane.showMessageDialog(null, "Database connection not initialized.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}

		loadUsersFromDB();

		for (User user : registeredUsers) {
			if (user.email.equalsIgnoreCase(email) && user.password.equals(password)) {
				System.out.println("Login successful. Welcome, " + user.name + "!");
				return user;
			}
		}

		System.out.println("Invalid email or password.");
		return null;
	}

	public static User getUser(int userId) throws SQLException {
		String sql = "select * from users where userId = " + userId + ";";
		ResultSet resultSet = dbConnect.statement.executeQuery(sql);
		if (resultSet.next()) {

			int id = resultSet.getInt("userId");
			String userName = resultSet.getString("name");
			String userEmail = resultSet.getString("email");
			String userPassword = resultSet.getString("password");
			String userPhoneNumber = resultSet.getString("phoneNumber");
			User user = new User(userName, userEmail, userPhoneNumber, userPassword, dbConnect);
			user.userId = id;
			return user;
		} else {
			return null;
		}
	}
}