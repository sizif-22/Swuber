package functionality;

import java.util.ArrayList;
import java.util.List;

public class RidePlanner {
	private List<Ride> activeRides;
	private List<Driver> allDrivers;

	public RidePlanner() {
		this.activeRides = new ArrayList<>();
		this.allDrivers = new ArrayList<>();
	}

	public void addDriver(Driver driver) {
		allDrivers.add(driver);
	}

	public List<Driver> getAvailableDrivers(String location) {
		List<Driver> availableDrivers = new ArrayList<>();
		for (Driver driver : allDrivers) {
			if (driver.isAvailable() && driver.getCurrentLocation().equals(location)) {
				availableDrivers.add(driver);
			}
		}
		return availableDrivers;
	}

	// public Driver matchDriverToRide(Ride ride) {
	// 	List<Driver> availableDrivers = getAvailableDrivers(ride.getStartLocation());
	// 	if (!availableDrivers.isEmpty()) {
	// 		Driver matchedDriver = availableDrivers.get(0);
	// 		matchedDriver.setAvailable(false);
	// 		return matchedDriver;
	// 	}
	// 	return null;
	// }

	public Driver matchDriverToRide(Ride ride) {
    List<Driver> availableDrivers = getAvailableDrivers(ride.getStartLocation());
    if (!availableDrivers.isEmpty()) {
        Driver matchedDriver = availableDrivers.get(0);
        matchedDriver.setAvailable(false);
        ride.setDriver(matchedDriver); 
        return matchedDriver;
    }
    return null;
}

	public void addRide(Ride ride) {
		activeRides.add(ride);
	}

	public void completeRide(Ride ride) {
		ride.setStatus("COMPLETED");
		activeRides.remove(ride);
		ride.getDriver().markRideAsComplete(ride);
		ride.getUser().addRideToHistory(ride);
	}
}