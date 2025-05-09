package com.swuber.functionality;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ScheduledRideId implements Serializable {
    
    @Column(name = "rideId")
    private Integer rideId;
    
    @Column(name = "userId")
    private Integer userId;
    
    // Default no-arg constructor required by JPA
    public ScheduledRideId() {
    }
    
    // Constructor with all fields
    public ScheduledRideId(Integer rideId, Integer userId) {
        this.rideId = rideId;
        this.userId = userId;
    }
    
    // Getters and setters
    public Integer getRideId() {
        return rideId;
    }
    
    public void setRideId(Integer rideId) {
        this.rideId = rideId;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    // Equals and hashCode methods are required for composite keys
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduledRideId that = (ScheduledRideId) o;
        return Objects.equals(rideId, that.rideId) &&
               Objects.equals(userId, that.userId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(rideId, userId);
    }
}