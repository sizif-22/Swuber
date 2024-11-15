package package1;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static int userId;
    private String name;
    private String email;
    private String phoneNumber;
    private RideHistory rideHistory;
    private List<Card> savedPaymentOptions;

    public User(String name, String email, String phoneNumber) {
        this.userId += 1;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.rideHistory = new RideHistory();
        this.savedPaymentOptions = new ArrayList<Card>();

    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.name = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }

    // public void register() {
    //     // Register the user
    // }

    // public void login() {
    //     // Login the user
    // }
    

    public List<Ride> getRideHistory() {
        return this.rideHistory;
    }

    public void updateProfile (String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    

    public SavedPaymentOptions getSavedPaymentOptions() {
        return this.savedPaymentOptions;
    }

    public void addRideToHistory(Ride ride) {
        this.rideHistory.addRide(ride);
    }

    public void addPaymentOption(PaymentOption paymentOption) {
        this.savedPaymentOptions.addPaymentOption(paymentOption);
    }

    public void removePaymentOption(String name) {
        this.savedPaymentOptions.removePaymentOption(paymentOption);
    }
}