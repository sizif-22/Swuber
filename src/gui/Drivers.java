package gui;
import functionality.*;

public class Drivers {

    private Drivers() {
    }

    public static final Vehicle VEHICLE_1 = new Vehicle("Toyota Corolla", "Black", "Comfort", "ABC123");
    public static final Vehicle VEHICLE_2 = new Vehicle("Honda Civic", "White", "Comfort", "XYZ789");
    public static final Vehicle VEHICLE_3 = new Vehicle("Tesla Model 3", "Red", "Premium", "TSL456");

    public static final Driver AHMED = new Driver("Ahmed", "Downtown", VEHICLE_1);
    public static final Driver SHERIF = new Driver("Sherif", "Uptown", VEHICLE_2);
    public static final Driver MO = new Driver("Mo", "Downtown", VEHICLE_3);

}