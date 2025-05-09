// package com.swuber.functionality;

// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

// import com.swuber.db.DBConfig;

// public class RideHistory {
// 	private List<Ride> rides;
// 	private static DBConfig dbConnect;
// 	private User user;
// 	private Driver driver;

// 	public RideHistory(User user, Driver driver, DBConfig dbConnect) {
// 		this.rides = new ArrayList<>();
// 		this.user = user;
// 		this.driver = driver;
// 		RideHistory.dbConnect = dbConnect;
// 	}

// 	public static void setDBConnect(DBConfig dbConfig) {
// 		dbConnect = dbConfig;
// 	}

// 	public static void addRide(Ride ride) throws SQLException {
// 		// Check if dbConnect is initialized
// 		if (dbConnect == null) {
// 			throw new SQLException("Database connection not initialized. Call setDBConnect before adding a ride.");
// 		}

// 		// Handle potential null payment by using a default value of 0
// 		// Object paymentId = null;
// 		// if (ride.getPayment() != null) {
// 		// paymentId = ride.getPayment().paymentId;
// 		// }
// 		Driver driver = ride.getDriver();
// 		System.out.println(driver.driverId + " , " + driver.vehicleId);
// 		String sql = "insert into Ride (userId, driverId, vehicleId, startLocation, endLocation, cost, status,  rating) values ('"
// 				+ ride.getUser().userId + "','"
// 				+ ride.getDriver().driverId + "','"
// 				+ ride.getDriver().vehicleId + "','"
// 				+ ride.getStartLocation() + "','"
// 				+ ride.getEndLocation() + "','"
// 				+ ride.getCost() + "','"
// 				+ ride.getStatus() + "','"
// 				+ ride.getRating() + "');";

// 		System.out.println(sql);
// 		dbConnect.statement.executeUpdate(sql);
// 	}

// 	public List<Ride> getRides() throws SQLException {
// 		String sql;
// 		if (user == null) {
// 			sql = "select * from Ride where driverId = '" + driver.driverId + "';";
// 			ResultSet resultSet = dbConnect.statement.executeQuery(sql);
// 			rides.clear();
// 			while (resultSet.next()) {
// 				int rideId = resultSet.getInt("rideId");
// 				int userId = resultSet.getInt("userId");
// 				int driverId = resultSet.getInt("driverId");
// 				int vehicleId = resultSet.getInt("vehicleId");
// 				int paymentId = resultSet.getInt("paymentId");
// 				float cost = resultSet.getFloat("cost");
// 				float rating = resultSet.getFloat("rating");
// 				Boolean isActive = resultSet.getBoolean("isActive");
// 				String startLocation = resultSet.getString("startLocation");
// 				String endLocation = resultSet.getString("endLocation");
// 				String status = resultSet.getString("status");
// 				User newUser = User.getUser(userId);
// 				Ride newRide = new Ride(newUser, startLocation, endLocation);
// 				rides.add(newRide);
// 			}
// 			return rides;
// 		} else if (driver == null) {
// 			sql = "select u.* , r.* , d.name as dname , d.location as dlocation , d.vehicleId as dvehicleId , d.completedRides as dCompletedRides , d.rating as drating, d.isAvailable as davailable from users u , ride r , driver d where u.userId = r.userId and d.driverId = r.driverId and u.userid = "
// 					+ +user.userId + ";";

// 			ResultSet resultSet = dbConnect.statement.executeQuery(sql);
// 			rides.clear();
// 			while (resultSet.next()) {
// 				String startLocation = resultSet.getString("startLocation");
// 				String endLocation = resultSet.getString("endLocation");
// 				float rating = resultSet.getFloat("rating");
// 				String name = resultSet.getString("name");
// 				String email = resultSet.getString("email");
// 				String phoneNumber = resultSet.getString("phoneNumber");
// 				String password = resultSet.getString("password");

// 				int vehicleId = resultSet.getInt("dvehicleId");
// 				int completedRides = resultSet.getInt("dCompletedRides");
// 				float drating = resultSet.getFloat("drating");
// 				Boolean isAvailable = resultSet.getBoolean("davailable");
// 				String dname = resultSet.getString("dname");
// 				String location = resultSet.getString("dlocation");

// 				Driver newDriver = new Driver(dname, location, vehicleId, completedRides, rating, isAvailable,
// 						dbConnect);
// 				User newUser = new User(name, email, phoneNumber, password, dbConnect);
// 				Ride newRide = new Ride(newUser, startLocation, endLocation);
// 				newRide.setDriver(newDriver);
// 				newRide.setRating(rating);
// 				rides.add(newRide);
// 			}
// 			return rides;
// 		} else {
// 			return null;
// 		}
// 	}
// }