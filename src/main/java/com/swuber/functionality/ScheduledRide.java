package com.swuber.functionality;

import java.io.Serializable;
import java.util.*;

import com.swuber.db.DatabaseConfig;

import jakarta.persistence.*;

@Entity
@Table(name = "ScheduledRide")
@NamedNativeQuery(name = "ShuttleRide.findWithScheduledStatus", query = "SELECT s.*, CASE WHEN sr.userId IS NULL THEN false ELSE true END AS isScheduled "
          +
          "FROM ShuttleRide s LEFT OUTER JOIN ScheduledRide sr " +
          "ON s.rideId = sr.rideId AND sr.userId = :userId ", resultClass = ShuttleRide2.class)
public class ScheduledRide {

     @EmbeddedId
     private ScheduledRideId id;

     @MapsId("rideId")
     @ManyToOne
     @JoinColumn(name = "rideId")
     private ShuttleRide shuttleRide;

     @MapsId("userId")
     @ManyToOne
     @JoinColumn(name = "userId")
     private User user;

     private static DatabaseConfig dbConnect;

     // Default no-arg constructor required by JPA
     public ScheduledRide() {
     }

     // Constructor with all fields
     public ScheduledRide(ShuttleRide shuttleRide, User user) {
          this.shuttleRide = shuttleRide;
          this.user = user;
          this.id = new ScheduledRideId(shuttleRide.getRideID(), user.getUserId());
     }

     public static void setDBConnect(DatabaseConfig dbConfig) {
          dbConnect = dbConfig;
     }

     public static void addNewScheduledRide(ShuttleRide shuttleRide, User user) {
          EntityManager em = dbConnect.getEntityManager();
          em.getTransaction().begin();
          try {
               // First, merge the existing entities to ensure they are managed
               shuttleRide = em.merge(shuttleRide);
               user = em.merge(user);

               // Create and persist the ScheduledRide with managed entities
               ScheduledRide sr = new ScheduledRide(shuttleRide, user);
               em.persist(sr);

               // Update available seats count
               // if (shuttleRide.getAvailableSeats() > 0) {
               // shuttleRide.setAvailableSeats(shuttleRide.getAvailableSeats() - 1);
               // // No need to merge again as shuttleRide is already managed
               // }

               em.getTransaction().commit();
               System.out.println("Scheduled ride added successfully");
          } catch (Exception e) {
               if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
               }
               System.out.println("Error adding scheduled ride: " + e.getMessage());
               e.printStackTrace();
          } finally {
               // It's a good practice to close the EntityManager if it's not container-managed
               // If you're using a container-managed EntityManager, you might want to remove
               // this
               if (em != null && em.isOpen()) {
                    em.close();
               }
          }
     }

     public User getUser() {
          return user;
     }

     public ShuttleRide getShuttleRide() {
          return shuttleRide;
     }

     public ScheduledRideId getId() {
          return id;
     }

     public void setId(ScheduledRideId id) {
          this.id = id;
     }
}