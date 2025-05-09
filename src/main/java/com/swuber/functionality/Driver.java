package com.swuber.functionality;

import java.util.*;
import com.swuber.db.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int driverId;

    private String name;
    private String location;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ride> rideHistory;

    private int completedRides;
    private float rating;
    private boolean isAvailable;
    private Date createdAt;

    private static DatabaseConfig dbConnect;

    @OneToOne
    @JoinColumn(name = "vehicleId") // Fixed: This is the correct way to specify a join column
    private Vehicle vehicle;

    // Default constructor required by JPA
    public Driver() {
        this.rideHistory = new HashSet<>();
        this.createdAt = new Date();
    }

    public Driver(String name, String location, Vehicle vehicle) {
        this();
        this.name = name;
        this.location = location;
        this.completedRides = 0;
        this.rating = 5.0f;
        this.vehicle = vehicle;
        this.isAvailable = true;
    }

    public static List<Driver> getAllDrivers() {
        EntityManager em = dbConnect.getEntityManager();
        List<Driver> allDrivers = null;
        try {
            TypedQuery<Driver> query = em.createQuery("select d from Driver d", Driver.class);
            allDrivers = query.getResultList();
        } catch (Error e) {
            System.out.println(e.toString());
        }
        return allDrivers;
    }

    public static List<Driver> getAvailableDrivers(String location) {
        List<Driver> allDrivers = getAllDrivers();
        List<Driver> availableDrivers = new ArrayList<>();
        for (Driver driver : allDrivers) {
            if (driver.isAvailable() && driver.getCurrentLocation().equals(location)) {
                availableDrivers.add(driver);
            }
        }
        return availableDrivers;
    }

    public static Driver matchDriverToRide(Ride ride) {
        List<Driver> availableDrivers = getAvailableDrivers(ride.getStartLocation());
        Driver matchedDriver = null;
        if (!availableDrivers.isEmpty()) {
            matchedDriver = availableDrivers.get(0);
            matchedDriver.setAvailable(false);
            ride.setDriver(matchedDriver);
        }
        return matchedDriver;
    }

    public static void setDBConnect(DatabaseConfig dbConfig) {
        dbConnect = dbConfig;
    }

    public float calculateRating() {
        if (rideHistory.isEmpty()) {
            return 5.0f;
        }

        float totalRating = 0;
        int ratedRides = 0;

        for (Ride ride : rideHistory) {
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

    public void setName(String name) {
        EntityManager em = dbConnect.getEntityManager();
        try {
            em.getTransaction().begin();
            this.name = name;
            em.merge(this);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public String getCurrentLocation() {
        return location;
    }

    public void setLocation(String location) {
        EntityManager em = dbConnect.getEntityManager();
        try {
            em.getTransaction().begin();
            this.location = location;
            em.merge(this);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public Set<Ride> getRideHistory() {
        return Collections.unmodifiableSet(this.rideHistory);
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public String getVehicleInfo() {
        Vehicle vehicle = getVehicle();
        if (vehicle != null) {
            return vehicle.getColor() + " " + vehicle.getVehicleModel() + " " + "License Plate: "
                    + vehicle.getLicenseNo();
        }
        return "No vehicle information available";
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        EntityManager em = dbConnect.getEntityManager();
        try {
            em.getTransaction().begin();
            this.isAvailable = available;
            em.merge(this);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public float getRating() {
        return rating;
    }

    public void updateRating(float newRating) {
        System.out.println("New rating: " + newRating);
        if (newRating >= 0 && newRating <= 5) {
            EntityManager em = dbConnect.getEntityManager();
            try {
                em.getTransaction().begin();
                float r = (this.rating * completedRides + newRating) / (completedRides + 1);
                this.rating = r;
                em.merge(this);
                em.getTransaction().commit();
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                e.printStackTrace();
            }
        }
    }

    public void markRideAsComplete(Ride ride) {
        System.out.println("Ride completed...");
        EntityManager em = dbConnect.getEntityManager();

        try {
            em.getTransaction().begin();

            System.out.println("Ride status: " + ride.getStatus());
            this.completedRides++;
            em.merge(this);

            em.getTransaction().commit();

            // These operations may start new transactions
            this.updateRating(ride.getRating());
            this.setLocation(ride.getEndLocation());
            this.setAvailable(true);

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public int getCompletedRides() {
        return completedRides;
    }

    public int getDriverId() {
        return driverId;
    }
}