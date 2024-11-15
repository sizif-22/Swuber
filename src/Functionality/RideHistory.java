package functionality;

import java.util.ArrayList;
import java.util.List;

public class RideHistory {
    private List<Ride> rideHistory;

    // Constructor
    public RideHistory() {
        this.rideHistory = new ArrayList<>();
    }

    /**
     * Add a ride to the history
     * @param ride The ride to be added
     */
    public void addRideToHistory(Ride ride) {
        if (ride != null) {
            rideHistory.add(ride);
        }
    }

    /**
     * Get the complete ride history
     * @return List of all rides
     */
    public List<Ride> getRideHistory() {
        return new ArrayList<>(rideHistory); // Return a copy to maintain encapsulation
    }

    /**
     * Get the number of rides in history
     * @return The size of ride history
     */
    // public int getRideCount() {
    //     return rideHistory.size();
    // }

    // /**
    //  * Clear the ride history
    //  */
    // public void clearHistory() {
    //     rideHistory.clear();
    // }

    // /**
    //  * Get rides by status
    //  * @param status The status to filter by
    //  * @return List of rides with the specified status
    //  */
    // public List<Ride> getRidesByStatus(String status) {
    //     List<Ride> filteredRides = new ArrayList<>();
    //     for (Ride ride : rideHistory) {
    //         if (ride.getStatus().equals(status)) {
    //             filteredRides.add(ride);
    //         }
    //     }
    //     return filteredRides;
    // }

    // /**
    //  * Get rides by date range
    //  * @param startDate The start date
    //  * @param endDate The end date
    //  * @return List of rides within the date range
    //  */
    // public List<Ride> getRidesByDateRange(String startDate, String endDate) {
    //     // In a real implementation, you would parse the dates and compare
    //     // This is a simplified version
    //     return new ArrayList<>(rideHistory);
    // }

    // /**
    //  * Get total spent on rides
    //  * @return The total cost of all rides
    //  */
    // public double getTotalSpent() {
    //     double total = 0.0;
    //     for (Ride ride : rideHistory) {
    //         total += ride.getCost();
    //     }
    //     return total;
    // }

    // /**
    //  * Get average ride cost
    //  * @return The average cost per ride
    //  */
    // public double getAverageRideCost() {
    //     if (rideHistory.isEmpty()) {
    //         return 0.0;
    //     }
    //     return getTotalSpent() / rideHistory.size();
    // }

    // /**
    //  * Get most frequent destination
    //  * @return The most common destination in the ride history
    //  */
    // public String getMostFrequentDestination() {
    //     if (rideHistory.isEmpty()) {
    //         return null;
    //     }

    //     // Create a map to count destinations
    //     java.util.Map<String, Integer> destinationCounts = new java.util.HashMap<>();
        
    //     // Count occurrences of each destination
    //     for (Ride ride : rideHistory) {
    //         String destination = ride.getEndLocation();
    //         destinationCounts.put(destination, 
    //             destinationCounts.getOrDefault(destination, 0) + 1);
    //     }

    //     // Find the most frequent destination
    //     String mostFrequent = null;
    //     int maxCount = 0;
        
    //     for (java.util.Map.Entry<String, Integer> entry : destinationCounts.entrySet()) {
    //         if (entry.getValue() > maxCount) {
    //             maxCount = entry.getValue();
    //             mostFrequent = entry.getKey();
    //         }
    //     }

    //     return mostFrequent;
    // }

    // /**
    //  * Get favorite driver based on ratings
    //  * @return The driver with the highest average rating
    //  */
    // public Driver getFavoriteDriver() {
    //     if (rideHistory.isEmpty()) {
    //         return null;
    //     }

    //     // Create a map to store driver ratings
    //     java.util.Map<Driver, List<Double>> driverRatings = new java.util.HashMap<>();

    //     // Collect all ratings for each driver
    //     for (Ride ride : rideHistory) {
    //         Driver driver = ride.getDriver();
    //         driverRatings.computeIfAbsent(driver, k -> new ArrayList<>())
    //                     .add(driver.getRating());
    //     }

    //     // Find driver with highest average rating
    //     Driver favoriteDriver = null;
    //     double highestAvgRating = 0.0;

    //     for (java.util.Map.Entry<Driver, List<Double>> entry : driverRatings.entrySet()) {
    //         double avgRating = entry.getValue().stream()
    //                               .mapToDouble(Double::doubleValue)
    //                               .average()
    //                               .orElse(0.0);
            
    //         if (avgRating > highestAvgRating) {
    //             highestAvgRating = avgRating;
    //             favoriteDriver = entry.getKey();
    //         }
    //     }

    //     return favoriteDriver;
    // }
}