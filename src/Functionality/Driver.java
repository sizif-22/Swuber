package functionality;

import java.util.ArrayList;
import java.util.List;

public class Driver {
	private String name;
	private String location;
	private boolean isAvailable;
	private RideHistory rideHistory;
	private int completedRides;
	private Vehicle vehicle;
	private static List<Driver> allDrivers = new ArrayList<>();

	public Driver(String name, String location, boolean isAvailable) {
		this.name = name;
		this.location = location;
		this.isAvailable = isAvailable;
		allDrivers.add(this);
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
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

	/**
	 * Update driver profile with completed ride information
	 * 
	 * @param rideID      The ID of the completed ride
	 * @param vehicleInfo The vehicle information used for the ride
	 */
	private void updateDriverProfile(String rideID, String vehicleInfo) {
		// In a real implementation, this might:
		// 1. Update driver statistics
		// 2. Log completion time
		// 3. Update availability status
		// 4. Trigger notifications
		// 5. Update earning calculations
	}

	public void markRideAsComplete(Ride ride) {
		if (ride != null && ride.getStatus().equals("COMPLETED")) {
			// Increment completed rides counter
			this.completedRides++;

			// Add to ride history
			this.rideHistory.addRideToHistory(ride);

			// Update driver's profile info
			updateDriverProfile(ride.getRideID(), ride.getVehicle().getVehicleInfo());
		}
	}
}
