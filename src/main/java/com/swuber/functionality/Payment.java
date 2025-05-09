package com.swuber.functionality;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @Column(name = "paymentId")
    private String paymentId; // Changed to String to match DB schema

    @OneToOne
    @JoinColumn(name = "rideId")
    private Ride ride;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne
    @JoinColumn(name = "cardId")
    private Card card;

    @Column(name = "cost")
    private double cost;

    @Column(name = "discountCode")
    private String discountCode;

    @Column(name = "isProcessed")
    private boolean isProcessed;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    // Default constructor required by JPA
    public Payment() {
    }

    public Payment(String paymentId, Ride ride, User user, Card card, double cost) {
        this.paymentId = paymentId;
        this.ride = ride;
        this.user = user;
        this.card = card;
        this.cost = cost;
        this.isProcessed = false;
        this.createdAt = LocalDateTime.now();
    }

    public void applyDiscount(String code) {
        if (code == null) {
            return;
        }

        this.discountCode = code;

        switch (code.toUpperCase()) {
            case "NEWUSER":
                this.cost *= 0.8;
                break;
            case "WEEKEND":
                this.cost *= 0.9;
                break;
            case "HOLIDAY":
                this.cost *= 0.85;
                break;
            default:
                break;
        }
    }

    public boolean processPayment() {
        if (this.discountCode != null) {
            applyDiscount(this.discountCode);
        }
        this.isProcessed = true;
        this.ride.setStatus("COMPLETED");
        return true;
    }

    public boolean processPayment(String discountCode) {
        applyDiscount(discountCode);
        this.ride.setStatus("COMPLETED");
        return true;
    }

    // Getters and Setters
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}