// package com.swuber.functionality;

// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

// import com.swuber.db.DBConfig;

// public class RidePlanner {
// 	private List<Ride> activeRides;
// 	private static List<Driver> allDrivers;
// 	private static DBConfig dbConnect;

// 	public RidePlanner() {
// 		this.activeRides = new ArrayList<>();
// 		this.allDrivers = new ArrayList<>();
// 	}

// 	public static void setDBConnect(DBConfig dbConfig) {
// 		dbConnect = dbConfig;
// 	}

// 	public void addDriver(Driver driver) {
// 		allDrivers.add(driver);
// 	}

// 	// function to fetch all drivers from db
// 	public void loadDriversFromDB() throws SQLException {
// 		if (dbConnect == null) {
// 			System.err.println("Database connection not initialized.");
// 			return;
// 		}
// 		allDrivers.clear();
// 		ResultSet resultSet = dbConnect.statement.executeQuery("select * from Driver;");
// 		while (resultSet.next()) {
// 			int id = resultSet.getInt("driverId");
// 			int vehicleId = resultSet.getInt("vehicleId");
// 			float rating = resultSet.getFloat("rating");
// 			int completedRides = resultSet.getInt("completedRides");
// 			Boolean isAvailable = resultSet.getBoolean("isAvailable");
// 			String name = resultSet.getString("name");
// 			String location = resultSet.getString("location");
// 			Driver driver = new Driver(name, location, vehicleId, completedRides, rating, isAvailable, dbConnect);
// 			driver.driverId = id;
// 			allDrivers.add(driver);
// 			System.out.println(
// 					"driver: " + id + " " + name + ", vid: " + vehicleId);
// 		}
// 	}

// 	public List<Driver> getAvailableDrivers(String location) {
// 		List<Driver> availableDrivers = new ArrayList<>();
// 		for (Driver driver : allDrivers) {
// 			if (driver.isAvailable() && driver.getCurrentLocation().equals(location)) {
// 				availableDrivers.add(driver);
// 			}
// 		}
// 		return availableDrivers;
// 	}
// 	public static List<Driver> getAllDrivers(){
// 		return allDrivers;
// 	}

// 	// public Driver matchDriverToRide(Ride ride) {
// 	// List<Driver> availableDrivers = getAvailableDrivers(ride.getStartLocation());
// 	// if (!availableDrivers.isEmpty()) {
// 	// Driver matchedDriver = availableDrivers.get(0);
// 	// matchedDriver.setAvailable(false);
// 	// return matchedDriver;
// 	// }
// 	// return null;
// 	// }

// 	public Driver matchDriverToRide(Ride ride) throws SQLException {
// 		List<Driver> availableDrivers = getAvailableDrivers(ride.getStartLocation());
// 		if (!availableDrivers.isEmpty()) {
// 			Driver matchedDriver = availableDrivers.get(0);
// 			matchedDriver.setAvailable(false);
// 			ride.setDriver(matchedDriver);
// 			return matchedDriver;
// 		}
// 		return null;
// 	}

// 	public void addRide(Ride ride) {
// 		activeRides.add(ride);
// 	}

// 	public void completeRide(Ride ride) throws SQLException {
// 		activeRides.remove(ride);
// 		ride.getDriver().setAvailable(true);
// 	}
// }