package Functionality;

import java.util.ArrayList;
import java.util.List;

public class RidePlanner {
    private List<Ride> availableRides;

    public List<Driver> getAvailableDrivers(String start) {
        List<Driver> drivers = new ArrayList<>();
        for (Driver driver : Driver.getAllDrivers()) {
            if (driver.isAvailable() && driver.getLocation().equalsIgnoreCase(start)) {
                drivers.add(driver);
            }
        }
        return drivers;
    }

    public List<ShuttleRide> getScheduledShuttles(String startLocation, String destination) {
        List<ShuttleRide> scheduledShuttles = new ArrayList<>();
        for (Ride ride : availableRides) {
            if (ride instanceof ShuttleRide) {
                ShuttleRide shuttle = (ShuttleRide) ride;
                if (shuttle.route.contains(startLocation) && shuttle.route.contains(destination)) {
                    scheduledShuttles.add(shuttle);
                }
            }
        }
        return scheduledShuttles;
    }

    public void estimateArrivalTime(String startLocation, String endLocation) {
        // Implementation for estimating arrival time
    }

    public void scheduleRide(String startLocation, String endLocation, String user, String time) {
        // Implementation for scheduling a ride
    }

    public Driver matchDriverToRide(Ride ride) {
        for (Driver driver : Driver.getAllDrivers()) {
            if (driver.isAvailable()) {
                return driver;
            }
        }
        return null;
    }
}
