package main;

import java.util.List;
import java.util.Scanner;
import functionality.*;

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

		processRide(planner, user1, driver1, "Downtown", "Uptown");

	}

	private static void processRide(RidePlanner planner, User user, Driver driver, String startLocation,
			String endLocation) {
		System.out.println("\nNew ride from " + startLocation + " to " + endLocation);

		Ride ride = user.requestRide(planner, startLocation, endLocation);

		if (ride == null || ride.getDriver() == null) {
			System.out.println("No drivers currently available in this location");
			return;
		}

		System.out.println("Ride Cost: $" + String.format("%.2f", ride.getCost()));
		System.out.println("Ride accepted by: " + ride.getDriver().getName());

		// simulate payment and ride completion
		Card card = new Card("Visa", "4111111111111111", "12/25", user.getName());
		Payment payment = new Payment(ride, user, card, ride.getCost());
		boolean paymentSuccess = ride.processPayment(payment, null);

		if (paymentSuccess) {
			System.out.println("Payment processed successfully");
			driver.markRideAsComplete(ride);
			user.addRideToHistory(ride);
			planner.completeRide(ride);

			// Get ride rating from user - scanner
			Scanner scanner = new Scanner(System.in);
			try {
				float rating = -1;
				while (rating < 0 || rating > 5) {
					System.out.print("Please rate your ride (0.0 to 5.0): ");
					if (scanner.hasNextFloat()) {
						rating = scanner.nextFloat();
						if (rating < 0 || rating > 5) {
							System.out.println("Invalid rating. Please enter a value between 0.0 and 5.0.");
						}
					} else {
						System.out.println("Invalid input. Please enter a numeric value.");
						scanner.next(); // Clear invalid input
					}
				}

				ride.rateRide(rating);
				driver.updateRating(rating);
				ride.completeRide();

				System.out
						.println("Thank you for your feedback! Your rated " + ride.getDriver().getName() + " " + rating + " Stars");
			} finally {
				scanner.close();
			}
		} else {
			System.out.println("Payment failed. Ride could not be completed.");
		}
	}
}
