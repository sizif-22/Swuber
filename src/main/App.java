package main;

import java.util.List;

import fun.*;

public class App {
	public static void main(String[] args) {
		System.out.println("=== Initializing System ===");
		RidePlanner planner = new RidePlanner();

		Vehicle vehicle1 = new Vehicle("Toyota Corolla", "Black", "Comfort", "ABC123");
		Vehicle vehicle2 = new Vehicle("Honda Civic", "White", "Comfort", "XYZ789");
		Vehicle vehicle3 = new Vehicle("Tesla Model 3", "Red", "Premium", "TSL456");

		Driver driver1 = new Driver("Ahmed", "Downtown", vehicle1);
		Driver driver2 = new Driver("Sherif", "Uptown", vehicle2);
		Driver driver3 = new Driver("Mo", "Downtown", vehicle3);

		System.out.println("Drivers in System: " + Driver.getAllDrivers().size());

		planner.addDriver(driver1);
		planner.addDriver(driver2);
		planner.addDriver(driver3);

		User user1 = new User("Noureen", "na@example.com", "1234567890", "password123");
		User user2 = new User("Nada", "n2@example.com", "0987654321", "password456");

		System.out.println("\n=== Driver method Test ===");
		System.out.println("Driver 1: " + driver1.getName());
		System.out.println("Location: " + driver1.getCurrentLocation());
		System.out.println("Initial Rating: " + driver1.getRating());
		System.out.println("Completed Rides: " + driver1.getCompletedRides());
		System.out.println("Available: " + driver1.isAvailable());

		System.out.println("\n=== Testing Driver Location Change ===");
		driver1.setLocation("Uptown");
		System.out.println("New Location: " + driver1.getCurrentLocation());

		System.out.println("\n=== Testing Vehicle Change ===");
		Vehicle newVehicle = new Vehicle("BMW X5", "Silver", "Premium", "BMW694");
		driver1.setVehicle(newVehicle);
		System.out.println("New Vehicle: " + driver1.getVehicle().getVehicleModel());

		System.out.println("\n=== Testing Driver Availability In Location 'Downtown' ===");
		List<Driver> availableDowntownDrivers = planner.getAvailableDrivers("Downtown");
		System.out.println("Available drivers in Downtown: " + availableDowntownDrivers.size());

		System.out.println("\n=== Final Driver Ratings ===");
		for (Driver driver : Driver.getAllDrivers()) {
			System.out.println(driver.getName() + ": " + driver.calculateRating());
		}

		processRide(planner, user1, driver1, "downtown", "uptown");

	}

	private static void processRide(RidePlanner planner, User user, Driver driver, String startLocation,
			String endLocation) {
		System.out.println("\nnew ride from " + startLocation + " to " + endLocation);

		Ride ride = user.requestRide(planner, startLocation, endLocation);

		System.out.println("Ride Cost: $" + String.format("%.2f", ride.getCost()));

		boolean rideRequested = false;
		if (ride != null) {
			rideRequested = true;
		}

		if (rideRequested) {
			System.out.println("Ride accepted by: " + ride.getDriver().getName());
			// simulation - there should be a wait time here
			Card card = new Card("Visa", "4111111111111111", "12/25", user.getName());
			Payment payment = new Payment(ride, user, card, ride.getCost());
			boolean paymentSuccess = ride.processPayment(payment, null);

			float rating = 4.5f; // static rating, should use scanner but useless since we're migrating to GUI
														// anyway

			if (paymentSuccess) {
				System.out.println("Payment processed successfully");
				driver.markRideAsComplete(ride); // also adds the ride to driver history
				user.addRideToHistory(ride);
				planner.completeRide(ride);
				ride.rateRide(rating);
				driver.updateRating(rating);
				ride.completeRide();
			}
		}

	}

}