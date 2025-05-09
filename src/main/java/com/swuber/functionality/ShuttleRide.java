package com.swuber.functionality;

import java.io.Serializable;
import java.util.*;
import java.time.LocalDateTime;

import com.swuber.db.DatabaseConfig;

import jakarta.persistence.*;

@Entity
@Table(name = "ShuttleRide")
public class ShuttleRide implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rideId")
    private Integer rideId;

    @Column(name = "startLocation")
    private String startLocation;

    @Column(name = "endLocation")
    private String endLocation;

    @Column(name = "maxPassengers", nullable = false)
    private Integer maxPassengers = 8;

    @Column(name = "price")
    private Float price;

    @Column(name = "availableSeats")
    private Integer availableSeats;

    @Column(name = "startTime")
    private LocalDateTime startTime;

    @Column(name = "shuttleArrivalTime")
    private LocalDateTime shuttleArrivalTime;

    // Relationships
    @OneToMany(mappedBy = "shuttleRide")
    private List<ScheduledRide> scheduledRides;

    // Default constructor
    public ShuttleRide() {
    }

    // Getters and Setters
    public Integer getRideID() {
        return rideId;
    }

    public void setRideID(Integer rideId) {
        this.rideId = rideId;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public Integer getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(Integer maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getShuttleArrivalTime() {
        return shuttleArrivalTime;
    }

    public void setShuttleArrivalTime(LocalDateTime shuttleArrivalTime) {
        this.shuttleArrivalTime = shuttleArrivalTime;
    }

    public List<ScheduledRide> getScheduledRides() {
        return scheduledRides;
    }

    public void setScheduledRides(List<ScheduledRide> scheduledRides) {
        this.scheduledRides = scheduledRides;
    }

    // Static methods for database operations
    private static DatabaseConfig dbConnect;

    public static void setDBConnect(DatabaseConfig dbConfig) {
        dbConnect = dbConfig;
    }

    public static List<ShuttleRide> getAllShuttleRides() {
        EntityManager em = dbConnect.getEntityManager();
        TypedQuery<ShuttleRide> query = em.createQuery("SELECT s FROM ShuttleRide s", ShuttleRide.class);
        return query.getResultList();
    }

    // Add method to check if a ride is scheduled by a specific user
    public boolean isScheduledBy(User user) {
        EntityManager em = dbConnect.getEntityManager();
        try {
            // Use direct query to avoid lazy loading issues
            Query query = em.createQuery(
                    "SELECT COUNT(sr) FROM ScheduledRide sr WHERE sr.shuttleRide.rideId = :rideId AND sr.user.userId = :userId");
            query.setParameter("rideId", this.rideId);
            query.setParameter("userId", user.getUserId());

            Long count = (Long) query.getSingleResult();
            return count > 0;
        } catch (Exception e) {
            System.out.println("Error checking if ride is scheduled: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static List<ShuttleRide> getShuttleRidesForUser(User user) {

        EntityManager em = dbConnect.getEntityManager();
        TypedQuery<ShuttleRide> query = em.createQuery(
                "SELECT DISTINCT sr.shuttleRide FROM ScheduledRide sr WHERE sr.user.userId = :userId",
                ShuttleRide.class);
        query.setParameter("userId", user.getUserId());
        return query.getResultList();
    }
}