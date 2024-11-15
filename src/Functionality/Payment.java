package functionality;

public class Payment {
    private Ride ride;
    private User user;
    private Card card;
    private double cost;
    private String discountCode;
    
    // Constructor
    public Payment(Ride ride, User user, Card card, double cost) {
        this.ride = ride;
        this.user = user;
        this.card = card;
        this.cost = cost;
        this.discountCode = null;
    }

    /**
     * Get payment details as a formatted string
     * @return String containing payment details
     */
    public String getPaymentDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Payment Details:\n");
        details.append("User: ").append(user.getName()).append("\n");
        details.append("Ride ID: ").append(ride.getRideID()).append("\n");
        details.append("Card: ").append(card.getCardNumber()).append("\n");
        details.append("Original Cost: $").append(String.format("%.2f", cost)).append("\n");
        
        if (discountCode != null) {
            double discountedCost = calculateDiscountedCost();
            details.append("Discount Applied: ").append(discountCode).append("\n");
            details.append("Final Cost: $").append(String.format("%.2f", discountedCost));
        } else {
            details.append("Final Cost: $").append(String.format("%.2f", cost));
        }
        
        return details.toString();
    }

    /**
     * Apply a discount code to the payment
     * @param code The discount code to apply
     */
    public void applyDiscount(String code) {
        if (isValidDiscountCode(code)) {
            this.discountCode = code;
            this.cost = calculateDiscountedCost();
        }
    }

    /**
     * Calculate the cost after applying discount
     * @return The discounted cost
     */
    private double calculateDiscountedCost() {
        if (discountCode == null) {
            return cost;
        }

        // Example discount logic - in real implementation, would likely
        // fetch discount rates from a database or configuration
        switch (discountCode.toUpperCase()) {
            case "NEWUSER":
                return cost * 0.8; // 20% off
            case "WEEKEND":
                return cost * 0.9; // 10% off
            case "HOLIDAY":
                return cost * 0.85; // 15% off
            default:
                return cost;
        }
    }

    /**
     * Validate the discount code
     * @param code The discount code to validate
     * @return boolean indicating if the code is valid
     */
    private boolean isValidDiscountCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            return false;
        }

        // Example validation - in real implementation, would likely
        // check against a database of valid codes
        String upperCode = code.toUpperCase();
        return upperCode.equals("NEWUSER") || 
               upperCode.equals("WEEKEND") || 
               upperCode.equals("HOLIDAY");
    }

    /**
     * Process the payment
     * @return boolean indicating if payment was successful
     */
    public boolean processPayment() {
        // Validate payment details
        if (!validatePaymentDetails()) {
            return false;
        }

        // In a real implementation, this would:
        // 1. Connect to payment gateway
        // 2. Process card payment
        // 3. Handle response
        // 4. Update ride status
        // 5. Generate receipt

        // Simulate payment processing
        boolean paymentSuccessful = processCardPayment();
        
        if (paymentSuccessful) {
            ride.updateRideStatus("PAID");
            generateReceipt();
        }

        return paymentSuccessful;
    }

    /**
     * Validate all payment details before processing
     * @return boolean indicating if details are valid
     */
    private boolean validatePaymentDetails() {
        if (ride == null || user == null || card == null || cost <= 0) {
            return false;
        }

        // Validate card
        if (!card.isValid()) {
            return false;
        }

        // Validate cost against ride cost
        if (Math.abs(cost - ride.getCost()) > 0.01) { // Using small delta for double comparison
            return false;
        }

        // Validate user matches ride user
        if (!user.equals(ride.getUser())) {
            return false;
        }

        return true;
    }

    /**
     * Process the card payment
     * @return boolean indicating if card payment was successful
     */
    private boolean processCardPayment() {
        // Simulate card payment processing
        // In real implementation, this would interact with a payment gateway
        return true;
    }

    /**
     * Generate a receipt for the payment
     */
    private void generateReceipt() {
        // In real implementation, this would:
        // 1. Create a formatted receipt
        // 2. Save to database
        // 3. Send to user's email
        // 4. Generate PDF version if needed
    }

    // Getters and Setters
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
}
