package functionality;

public class Vehicle {
  private String vehicleModel;
  private String color;
  private String vehicleOption;
  private String licenseNo;
  private int maxPassengers;
  private int currentPassengers;

  public Vehicle(String vehicleModel, String color, String vehicleOption, String licenseNo, int maxPassengers) {
    this.vehicleModel = vehicleModel;
    this.color = color;
    this.vehicleOption = vehicleOption;
    this.licenseNo = licenseNo;
    this.maxPassengers = maxPassengers;
    this.currentPassengers = 0;
  }

  public boolean canAddPassengers(int count) {
    return currentPassengers + count <= maxPassengers;
  }

  public boolean addPassengers(int count) {
    if (canAddPassengers(count)) {
      currentPassengers += count;
      return true;
    }
    return false;
  }

  public void removePassengers(int count) {
    currentPassengers = Math.max(0, currentPassengers - count);
  }

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
    this.maxPassengers = maxPassengers;
  }

  public int getCurrentPassengers() {
    return currentPassengers;
  }

  public int getAvailableSeats() {
    return maxPassengers - currentPassengers;
  }
}