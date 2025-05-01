package db;

import java.sql.*;

public class DBConfig {
     String url = "jdbc:mysql://localhost:3306/swuber";
     String userName = "root";
     String password = "";
     Connection connection;
     public Statement statement;

     public DBConfig() {
          DBConnect();
     }

     void DBConnect() {

          try {

               Class.forName("com.mysql.cj.jdbc.Driver");
               connection = DriverManager.getConnection(url, userName, password);
               statement = connection.createStatement();
               System.out.println("DB connected");
               // ResultSet resultSet = statement.executeQuery("");
          } catch (Exception e) {
               System.out.println(e);
          }
     }

     public void addUser(String name, String email, String PhoneNumber, String password) throws SQLException {

          try {

               String state = "insert into users (name , email , password , phoneNumber) value ('" + name + "','"
                         + email + "','" + password + "','" + PhoneNumber + "');";
               statement.executeUpdate(state);
               System.out.println("user add successfully, name:" + name);
          } catch (SQLException e) {
               System.out.println(e);
          }

     }
     
}