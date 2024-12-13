package functionality;

import java.util.ArrayList;
import java.util.List;

public class ShuttleRide extends Ride {
    private List<User> passengers;
    private int maxPassengers;
    private int availableSeats;
    private String route;
    private String startTime;
    private String shuttleArrivalTime;
    private float price;

    public ShuttleRide(User initialUser, String startLocation, String endLocation, int maxPassengers, String route, String startTime, float price) {
        super(initialUser, startLocation, endLocation);
        this.passengers = new ArrayList<>();
        this.passengers.add(initialUser);
        this.maxPassengers = maxPassengers;
        this.availableSeats = maxPassengers - 1;
        this.route = route;
        this.startTime = startTime;
        this.shuttleArrivalTime = "";
        this.price = price;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void addPassenger(User passenger) {
        if (availableSeats > 0) {
            passengers.add(passenger);
            availableSeats--;
        } else {
            throw new IllegalStateException("No available seats in this shuttle ride");
        }
    }

    public List<User> getPassengers() {
        return new ArrayList<>(passengers); // Return a copy to prevent modification
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public String getRoute() {
        return route;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getShuttleArrivalTime() {
        return shuttleArrivalTime;
    }

    public void setShuttleArrivalTime(String shuttleArrivalTime) {
        this.shuttleArrivalTime = shuttleArrivalTime;
    }
    
    public float getPrice() {
        return price;
    }

    private String calculateEstimatedArrivalTime() {
        // Implement your logic to calculate estimated arrival time
        // This is a placeholder
        return "15 Min";
    }
}