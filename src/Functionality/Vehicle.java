package functionality;

public class Vehicle {
    private String vehicleModel;
    private String color;
    private String vehicleOption;
    private String licenseNo;
    private int maxPassengers;
    private int currentPassengers;
    private boolean isOperational;

    // Constructor
    public Vehicle(String vehicleModel, String color, String vehicleOption, 
                  String licenseNo, int maxPassengers) {
        this.vehicleModel = vehicleModel;
        this.color = color;
        this.vehicleOption = vehicleOption;
        this.licenseNo = licenseNo;
        this.maxPassengers = maxPassengers;
        this.currentPassengers = 0;
        this.isOperational = true;
    }

    /**
     * Get vehicle details as a formatted string
     * @return String containing vehicle details
     */
    public String getVehicleDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Vehicle Details:\n");
        details.append("Model: ").append(vehicleModel).append("\n");
        details.append("Color: ").append(color).append("\n");
        details.append("Option: ").append(vehicleOption).append("\n");
        details.append("License: ").append(licenseNo).append("\n");
        details.append("Maximum Passengers: ").append(maxPassengers).append("\n");
        details.append("Current Passengers: ").append(currentPassengers).append("\n");
        details.append("Status: ").append(isOperational ? "Operational" : "Not Operational");
        return details.toString();
    }

    /**
     * Update vehicle information
     * @param vehicleInfo The new vehicle information
     */
    public void updateVehicleInfo(String vehicleInfo) {
        // Parse vehicleInfo string and update relevant fields
        // This is a simplified version - in real implementation,
        // would parse a more structured format
        if (vehicleInfo != null && !vehicleInfo.trim().isEmpty()) {
            String[] parts = vehicleInfo.split(",");
            for (String part : parts) {
                String[] keyValue = part.trim().split(":");
                if (keyValue.length == 2) {
                    updateField(keyValue[0].trim(), keyValue[1].trim());
                }
            }
        }
    }

    /**
     * Helper method to update individual fields
     * @param field The field to update
     * @param value The new value
     */
    private void updateField(String field, String value) {
        switch (field.toLowerCase()) {
            case "model":
                this.vehicleModel = value;
                break;
            case "color":
                this.color = value;
                break;
            case "option":
                this.vehicleOption = value;
                break;
            case "license":
                this.licenseNo = value;
                break;
            case "maxpassengers":
                try {
                    this.maxPassengers = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    // Handle invalid number format
                }
                break;
        }
    }

    /**
     * Check if vehicle can accommodate more passengers
     * @param count Number of additional passengers
     * @return boolean indicating if passengers can be added
     */
    public boolean canAccommodate(int count) {
        return (currentPassengers + count) <= maxPassengers;
    }

    /**
     * Add passengers to the vehicle
     * @param count Number of passengers to add
     * @return boolean indicating if passengers were successfully added
     */
    public boolean addPassengers(int count) {
        if (canAccommodate(count)) {
            currentPassengers += count;
            return true;
        }
        return false;
    }

    /**
     * Remove passengers from the vehicle
     * @param count Number of passengers to remove
     */
    public void removePassengers(int count) {
        currentPassengers = Math.max(0, currentPassengers - count);
    }

    /**
     * Toggle operational status
     * @param operational New operational status
     */
    public void setOperational(boolean operational) {
        this.isOperational = operational;
    }

    /**
     * Check if vehicle is operational
     * @return boolean indicating operational status
     */
    public boolean isOperational() {
        return isOperational;
    }

    /**
     * Get available seats
     * @return number of available seats
     */
    public int getAvailableSeats() {
        return maxPassengers - currentPassengers;
    }

    // Getters and Setters
    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVehicleOption() {
        return vehicleOption;
    }

    public void setVehicleOption(String vehicleOption) {
        this.vehicleOption = vehicleOption;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
        if (maxPassengers >= 0) {
            this.maxPassengers = maxPassengers;
        }
    }

    public int getCurrentPassengers() {
        return currentPassengers;
    }

    /**
     * Get vehicle information for display
     * @return String containing basic vehicle info
     */
    public String getVehicleInfo() {
        return String.format("%s %s (%s) - %s", color, vehicleModel, vehicleOption, licenseNo);
    }
}