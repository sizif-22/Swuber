package functionality;

import java.util.ArrayList;
import java.util.List;

public class RideHistory {
	private List<Ride> rides;

	public RideHistory() {
		this.rides = new ArrayList<>();
	}

	public void addRide(Ride ride) {
		if (ride != null && "COMPLETED".equals(ride.getStatus())) {
			rides.add(ride);
		}
	}

	public List<Ride> getRides() {
		return rides;
	}
}
