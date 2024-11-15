package functionality;

public class Ride {
    private String rideID;
    private User user;
    private Driver driver;
    private Vehicle vehicle;
    private String startLocation;
    private String endLocation;
    private double cost;
    private String status;
    private boolean isShuttle;
    private double waitFreeRate;
    private Payment payment;

    // Constructor
    public Ride(String rideID, User user, Driver driver, Vehicle vehicle, 
                String startLocation, String endLocation) {
        this.rideID = rideID;
        this.user = user;
        this.driver = driver;
        this.vehicle = vehicle;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.status = "PENDING";
        this.isShuttle = false;
        this.waitFreeRate = 0.0;
        this.cost = 0.0;
    }

    // Request a ride
    public void requestRide(String startLocation, String endLocation, boolean isShuttle) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.isShuttle = isShuttle;
        this.status = "REQUESTED";
    }

    // Calculate the cost of the ride
    public double calculateCost(String startLocation, String endLocation, boolean isShuttle) {
        // Basic distance-based calculation (simplified example)
        double baseCost = calculateDistanceCost(startLocation, endLocation);
        
        if (isShuttle) {
            // Apply shuttle discount
            baseCost *= 0.8;
        }
        
        this.cost = baseCost;
        return this.cost;
    }

    // Helper method to calculate distance-based cost
    private double calculateDistanceCost(String startLocation, String endLocation) {
        // Simplified distance calculation - in real implementation, 
        // would use actual geocoding and distance calculation
        return 10.0; // Base fare for example
    }

    // Add wait-free time (premium feature)
    public double addWaitFreeTime(int minutes) {
        double ratePerMinute = 0.5; // Example rate
        this.waitFreeRate = minutes * ratePerMinute;
        this.cost += this.waitFreeRate;
        return this.waitFreeRate;
    }

    // Update ride status
    public void updateRideStatus(String status) {
        this.status = status;
    }

    // Process payment for the ride
    public boolean processPayment(Payment payment) {
        if (payment != null && payment.processPayment()) {
            this.payment = payment;
            this.status = "PAID";
            return true;
        }
        return false;
    }

    // Complete the ride
    public void completeRide() {
        if (this.status.equals("PAID")) {
            this.status = "COMPLETED";
            this.driver.markRideAsComplete(this);
        }
    }

    // Getters and Setters
    public String getRideID() {
        return rideID;
    }

    public User getUser() {
        return user;
    }

    public Driver getDriver() {
        return driver;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public double getCost() {
        return cost;
    }

    public String getStatus() {
        return status;
    }

    public boolean isShuttle() {
        return isShuttle;
    }

    public double getWaitFreeRate() {
        return waitFreeRate;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setShuttle(boolean shuttle) {
        isShuttle = shuttle;
    }

    public void setWaitFreeRate(double waitFreeRate) {
        this.waitFreeRate = waitFreeRate;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}