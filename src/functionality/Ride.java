package functionality;

import java.util.Random;
import java.util.Date;

public class Ride {

	// el mafrood da yb2a enum (mksl)
	public static final String STATUS_PENDING = "PENDING";
	public static final String STATUS_ACCEPTED = "ACCEPTED";
	public static final String STATUS_PAID = "PAID";
	public static final String STATUS_COMPLETED = "COMPLETED";

	private static int rideIDGen = 0;
	private int rideID;
	private User user;
	private Driver driver;
	private Vehicle vehicle;
	private String startLocation;
	private String endLocation;
	private double cost;
	private String status;
	private boolean isShuttle;
	private Payment payment;
	private float rating;
	private Date rideDate;

	public Ride( User user, String startLocation, String endLocation) {
		this.rideID = rideIDGen + 1;
		this.user = user;
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.status = STATUS_PENDING;
		this.isShuttle = false;
		this.cost = calculateCost();
		this.rideDate = new Date();
	}

	private double calculateCost() {
		return isShuttle ? 80.0 : 50 + new Random().nextDouble() * 150;
	}

	public boolean processPayment(Payment payment, String discountCode) {
		if (payment != null && payment.processPayment(discountCode)) {
			this.payment = payment;
			this.status = STATUS_PAID;
			return true;
		}
		return false;
	}

	public void completeRide() {
		if (this.status.equals(STATUS_PAID)) {
			this.status = STATUS_COMPLETED;
		}
	}

	public void rateRide(float rating) {
		if (rating >= 0 && rating <= 5) {
				this.rating = rating;
		}
}

public float getRating() {
		return this.rating;
}

	public int getRideID() {
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

	public Payment getPayment() {
		return payment;
	}

	public void setDriver(Driver driver){
		this.driver = driver;
	}
	
	public void setVehicle(Vehicle vehicle){
		this.vehicle= vehicle;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}
