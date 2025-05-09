package com.swuber.functionality;

import java.util.*;

import com.swuber.db.DatabaseConfig;

import jakarta.persistence.*;

@Table(name = "ShuttleRide")
public class ShuttleRide2 {
     @Id
     @Column(name = "rideId")
     private int rideId;
     // @Column(name = "isScheduled")
     // private Boolean isScheduled;
     @Column(name = "startLocation")
     private String startLocation;
     @Column(name = "endLocation")
     private String endLocation;
     @Column(name = "maxPassengers")
     private int maxPassengers;
     @Column(name = "price")
     private float price;

     private static DatabaseConfig dbConnect;

     public ShuttleRide2(String startLocation, String endLocation, int maxPassengers,
               String startTime, float price) {

     }

     public static void setDBConnect(DatabaseConfig dbConfig) {
          dbConnect = dbConfig;
     }

     // public Boolean getIsScheduled() {
     // return isScheduled;
     // }

     // public static List<ShuttleRide2> findShuttleRidesWithScheduledStatus(User
     // user) {
     // EntityManager em = dbConnect.getEntityManager();
     // List<ShuttleRide2> thelist = null;
     // try {
     // TypedQuery query = em.createQuery(
     // "SELECT s.rideId, s.startLocation, s.endLocation, s.maxPassengers, s.price,
     // CASE WHEN sr.userId IS NULL THEN false ELSE true END AS isScheduled FROM
     // ShuttleRide s LEFT OUTER JOIN ScheduledRide sr ON s.rideId = sr.rideId AND
     // sr.userId = "
     // + user.getUserId(),
     // ShuttleRide2.class);
     // thelist = query.getResultList();
     // } catch (Exception e) {
     // System.out.println(e);
     // }

     // return thelist;

     // }
     public int getRideId(){
          return this.rideId;
     }
     public int getMaxPassengers() {
          return maxPassengers;
     }

     public String getStartLocation() {
          return startLocation;
     }

     public String getEndLocation() {
          return endLocation;
     }

     public float getPrice() {
          return price;
     }

     public static List<ShuttleRide2> getAllShuttleRides() {
          List<ShuttleRide2> allRides = null;
          EntityManager em = dbConnect.getEntityManager();
          try {
               TypedQuery<ShuttleRide2> query = em.createQuery(
                         "select s from ShuttleRide s",
                         ShuttleRide2.class);
               allRides = query.getResultList();
          } catch (Exception e) {
               System.out.println(e);
          }

          return allRides;
     }

     // public void setIsScheduled(Boolean isScheduled) {
     // this.isScheduled = isScheduled;
     // }

}