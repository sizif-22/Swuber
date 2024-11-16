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

    public ShuttleRide(User initialUser, String startLocation, String endLocation, int maxPassengers, String route, String startTime) {
        super(initialUser, startLocation, endLocation);
        this.passengers = new ArrayList<>();
        this.passengers.add(initialUser);
        this.maxPassengers = maxPassengers;
        this.availableSeats = maxPassengers - 1; 
        this.route = route;
        this.startTime = startTime;
        this.shuttleArrivalTime = "";
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
        return new ArrayList<>(passengers);
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

    @Override
    public boolean requestRide(RidePlanner planner) {
        boolean success = super.requestRide(planner);
        if (success) {
            this.shuttleArrivalTime = calculateEstimatedArrivalTime();
        }
        return success;
    }

    private String calculateEstimatedArrivalTime() {
        return "15 Min"; // placeholder idk how we'd calculcate this so lets leave it static
    }
}