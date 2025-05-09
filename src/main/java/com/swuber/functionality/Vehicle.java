package com.swuber.functionality;

import jakarta.persistence.*;
import com.swuber.db.*;

@Entity
@Table(name = "Vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleId;

    private String vehicleModel;
    private String color;
    private String vehicleOption;
    private String licenseNo;

    private static DatabaseConfig dbConnect;

    // Default constructor required by JPA
    public Vehicle() {
    }

    public Vehicle(String vehicleModel, String color, String vehicleOption, String licenseNo) {
        this.vehicleModel = vehicleModel;
        this.color = color;
        this.vehicleOption = vehicleOption;
        this.licenseNo = licenseNo;
    }

    public static void setDBConnect(DatabaseConfig dbConfig) {
        dbConnect = dbConfig;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        EntityManager em = dbConnect.getEntityManager();
        try {
            em.getTransaction().begin();
            this.vehicleModel = vehicleModel;
            em.merge(this);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        EntityManager em = dbConnect.getEntityManager();
        try {
            em.getTransaction().begin();
            this.color = color;
            em.merge(this);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public String getVehicleOption() {
        return vehicleOption;
    }

    public void setVehicleOption(String vehicleOption) {
        EntityManager em = dbConnect.getEntityManager();
        try {
            em.getTransaction().begin();
            this.vehicleOption = vehicleOption;
            em.merge(this);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        EntityManager em = dbConnect.getEntityManager();
        try {
            em.getTransaction().begin();
            this.licenseNo = licenseNo;
            em.merge(this);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public int getVehicleId() {
        return vehicleId;
    }
}