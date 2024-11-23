package functionality;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    private String name;
    private String location;
    private RideHistory rideHistory;
    private int completedRides;
    private Vehicle vehicle;
    private static List<Driver> allDrivers = new ArrayList<>();
    private float rating;
    private boolean isAvailable;

    public Driver(String name, String location, Vehicle vehicle) {
        this.name = name;
        this.location = location;
        this.vehicle = vehicle;
        this.completedRides = 0;
        this.rating = 5.0f; 
        this.isAvailable = true;
        this.rideHistory = new RideHistory();
        allDrivers.add(this);
    }

    public float calculateRating() {
        List<Ride> pastRides = rideHistory.getRides(); 
        if (pastRides.isEmpty()) {
            return 5.0f; 
        }

        float totalRating = 0;
        int ratedRides = 0;

        for (Ride ride : pastRides) {
            float rideRating = ride.getRating(); 
            if (rideRating >= 0) { 
                totalRating += rideRating;
                ratedRides++;
            }
        }

        this.rating = (ratedRides > 0) ? totalRating / ratedRides : 5.0f;
        return this.rating;
    }

    public String getName() {
        return name;
    }

    public String getCurrentLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static List<Driver> getAllDrivers() {
        return allDrivers;
    }

    public RideHistory getRideHistory() {
        return rideHistory;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public float getRating() {
        return rating;
    }

    public void updateRating(float newRating) {
        if (newRating >= 0 && newRating <= 5) {
            this.rating = (this.rating * completedRides + newRating) / (completedRides + 1);
        }
    }

    public void markRideAsComplete(Ride ride) {
        if (ride != null && ride.getStatus().equals("PAID")) {
            this.completedRides++;
            this.rideHistory.addRide(ride);
            setAvailable(true);
        }
    }

    public int getCompletedRides() {
        return completedRides;
    }
}
