package main;

import java.util.List;
import functionality.Card;
import functionality.Driver;
import functionality.Payment;
import functionality.Ride;
import functionality.RidePlanner;
import functionality.User;
import functionality.Vehicle;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Initializing System ===");
        RidePlanner planner = new RidePlanner();
        
        Vehicle vehicle1 = new Vehicle("Toyota Camry", "Black", "Comfort", "ABC123", 4);
        Vehicle vehicle2 = new Vehicle("Honda Civic", "White", "Economy", "XYZ789", 4);
        Vehicle vehicle3 = new Vehicle("Tesla Model 3", "Red", "Premium", "TSL456", 4);
        
        Driver driver1 = new Driver("Bob Smith", "Downtown", vehicle1);
        Driver driver2 = new Driver("Alice Johnson", "Uptown", vehicle2);
        Driver driver3 = new Driver("Charlie Brown", "Downtown", vehicle3);
        
        System.out.println("Initial Drivers in System: " + Driver.getAllDrivers().size());
        
        planner.addDriver(driver1);
        planner.addDriver(driver2);
        planner.addDriver(driver3);
        
        User user1 = new User("John Doe", "john@example.com", "1234567890", "password123");
        User user2 = new User("Jane Smith", "jane@example.com", "0987654321", "password456");
        
        System.out.println("\n=== Driver Initial Status ===");
        System.out.println("Driver 1: " + driver1.getName());
        System.out.println("Location: " + driver1.getCurrentLocation());
        System.out.println("Initial Rating: " + driver1.getRating());
        System.out.println("Completed Rides: " + driver1.getCompletedRides());
        System.out.println("Available: " + driver1.isAvailable());

        System.out.println("\n=== Processing Multiple Rides ===");
        processRide(planner, user1, driver1, "Downtown", "Airport", 4.5f);
        processRide(planner, user2, driver1, "Downtown", "Mall", 3.8f);
        processRide(planner, user1, driver1, "Downtown", "Beach", 5.0f);

        System.out.println("\n=== Updated Driver Status ===");
        System.out.println("Driver: " + driver1.getName());
        System.out.println("Current Rating: " + driver1.getRating());
        System.out.println("Completed Rides: " + driver1.getCompletedRides());
        System.out.println("Ride History Size: " + driver1.getRideHistory().getRides().size());

        System.out.println("\n=== Testing Driver Location Change ===");
        driver1.setLocation("Uptown");
        System.out.println("New Location: " + driver1.getCurrentLocation());
        
        System.out.println("\n=== Testing Vehicle Change ===");
        Vehicle newVehicle = new Vehicle("BMW X5", "Silver", "Premium", "BMW789", 5);
        driver1.setVehicle(newVehicle);
        System.out.println("New Vehicle: " + driver1.getVehicle().getVehicleModel());

        System.out.println("\n=== Testing Multiple Driver Availability ===");
        List<Driver> availableDowntownDrivers = planner.getAvailableDrivers("Downtown");
        System.out.println("Available drivers in Downtown: " + availableDowntownDrivers.size());
        
        System.out.println("\n=== Final Driver Ratings ===");
        for (Driver driver : Driver.getAllDrivers()) {
            System.out.println(driver.getName() + ": " + driver.calculateRating());
        }
    }

    private static void processRide(RidePlanner planner, User user, Driver driver, 
                                  String startLocation, String endLocation, float rating) {
        System.out.println("\nProcessing new ride from " + startLocation + " to " + endLocation);
        
        Ride ride = new Ride(user, startLocation, endLocation);
        System.out.println("Ride Cost: $" + String.format("%.2f", ride.getCost()));
        
        boolean rideRequested = ride.requestRide(planner);
        if (rideRequested) {
            System.out.println("Ride accepted by: " + ride.getDriver().getName());
            
            Card card = new Card("Visa", "4111111111111111", "12/25", user.getName());
            Payment payment = new Payment(ride,user,card,ride.getCost());
            boolean paymentSuccess = ride.processPayment(payment, null);
            
            if (paymentSuccess) {
                System.out.println("Payment processed successfully");
                ride.completeRide();
                ride.rateRide(rating);
                driver.markRideAsComplete(ride);
                user.addRideToHistory(ride);
                planner.completeRide(ride);
                
                System.out.println("Ride completed with rating: " + rating);
                System.out.println("Driver's updated rating: " + driver.getRating());
            }
        }
    }
}