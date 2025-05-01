package functionality;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConfig;

public class Vehicle {
  int vehicleId;
  private String vehicleModel;
  private String color;
  private String vehicleOption;
  private String licenseNo;
  // private int maxPassengers;
  // private int currentPassengers;
  private static DBConfig dbConnect;

  public Vehicle(String vehicleModel, String color, String vehicleOption, String licenseNo, DBConfig dbConnect) {
    this.vehicleModel = vehicleModel;
    this.color = color;
    this.vehicleOption = vehicleOption;
    this.licenseNo = licenseNo;
    Vehicle.dbConnect = dbConnect;
  }

  // public Vehicle getVehicle(int vid) throws SQLException {
  //   try {

  //     String state = "select * from Vehicle where id= " + vid + " limit 1;";
  //     ResultSet rs = dbConnect.statement.executeQuery(state);
  //     //
  //     return null;
  //   } catch (SQLException e) {
  //     System.out.println(e);
  //     return null;
  //   }
  // }

  public static void setDBConnect(DBConfig dbConfig) {
    dbConnect = dbConfig;
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
}