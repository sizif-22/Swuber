package Functionality;

import java.util.ArrayList;
import java.util.List;

public class Driver {
	private String name;
	private String location;
	private boolean isAvailable;

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
}
