package functionality;

import java.sql.ResultSet;
import java.sql.SQLException;
// import java.util.ArrayList;
import java.util.List;

import db.DBConfig;

public class Driver {
    int driverId;
    private String name;
    private String location;
    private RideHistory rideHistory;
    private int completedRides;
    // private Vehicle vehicle;
    int vehicleId;
    // private static List<Driver> allDrivers = new ArrayList<>();
    private float rating;
    private boolean isAvailable;
    private static DBConfig dbConnect;

    public Driver(String name, String location, int vehicleId, int completedRides, float rating, Boolean isAvailable,
            DBConfig dbConnect) throws SQLException {
        this.name = name;
        this.location = location;
        this.vehicleId = vehicleId;
        this.completedRides = completedRides;
        this.rating = rating;
        this.isAvailable = isAvailable;
        this.rideHistory = new RideHistory(null, this, dbConnect);
        Driver.dbConnect = dbConnect;
        // allDrivers.add(this);
    }

    public static void setDBConnect(DBConfig dbConfig) {
        dbConnect = dbConfig;
    }

    public float calculateRating() throws SQLException {
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

    public void setLocation(String location) throws SQLException {
        String state = "update driver set location ='" + location + "' where driverId = " + driverId + ";";
        System.out.println(state);
        dbConnect.statement.executeUpdate(state);
        this.location = location;
    }

    // public static List<Driver> getAllDrivers() {
    // return allDrivers;
    // }

    public RideHistory getRideHistory() {
        return rideHistory;
    }

    public Vehicle getVehicle() throws SQLException {
        System.out.println("vid : " + vehicleId);
        String state = "select * from Vehicle where vehicleId=" + vehicleId + " limit 1;";
        System.out.println(state);
        ResultSet resultSet = dbConnect.statement.executeQuery(state);

        // Add this line to move to the first row in the result set
        if (resultSet.next()) {
            String color = resultSet.getString("color");
            String ln = resultSet.getString("licenseNo");
            String vm = resultSet.getString("vehicleModel");
            String vo = resultSet.getString("vehicleOption");
            Vehicle vehicle = new Vehicle(vm, color, vo, ln, dbConnect);
            return vehicle;
        } else {
            // Handle the case when no vehicle with the given ID is found
            throw new SQLException("No vehicle found with ID: " + vehicleId);
        }
    }

    public String getVehicleInfo() throws SQLException {
        Vehicle vehicle = getVehicle();
        return vehicle.getColor() + " " + vehicle.getVehicleModel() + " " + "License Plate: " + vehicle.getLicenseNo();
    }

    // public void setVehicle(Vehicle vehicle) {
    // this.vehicle = vehicle;
    // }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) throws SQLException {
        String state = "update driver set isAvailable =" + available + " where driverId = " + driverId + ";";
        dbConnect.statement.executeUpdate(state);
        isAvailable = available;
    }

    public float getRating() {
        return rating;
    }

    public void updateRating(float newRating) throws SQLException {
        System.out.println(newRating);
        if (newRating >= 0 && newRating <= 5) {
            float r = (this.rating * completedRides + newRating) / (completedRides + 1);
            String state = "update driver set rating ='" + r + "' where driverId = " + driverId + ";";
            dbConnect.statement.executeUpdate(state);
            this.rating = r;
        }
    }

    public void markRideAsComplete(Ride ride) throws SQLException {
        System.out.println("Ride completed ...");
        // if (ride != null && ride.getStatus().equals("PAID")) {
        System.out.println(ride.getStatus());
        this.completedRides++;
        String state = "update driver set completedRides =" + completedRides + " where driverId = " + driverId
                + ";";
        dbConnect.statement.executeUpdate(state);
        // this.rideHistory.addRide(ride);
        this.updateRating(ride.getRating());
        this.setLocation(ride.getEndLocation());
        this.setAvailable(true);
        System.out.println("completedRides: " + completedRides);
        System.out.println("location: " + location);
        System.out.println("isAvailable: " + isAvailable);
        // }
    }

    public int getCompletedRides() {
        return completedRides;
    }
}
