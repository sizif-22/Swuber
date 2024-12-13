package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.io.*;
import functionality.*;

public class GUI {
    private static RidePlanner planner = new RidePlanner();
    private static User currentUser;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            initializeData();
            showLoginPage();
        });
    }

    private static void initializeData() {
        Vehicle vehicle1 = new Vehicle("Toyota Corolla", "Black", "Comfort", "ABC123");
        Vehicle vehicle2 = new Vehicle("Honda Civic", "White", "Comfort", "XYZ789");
        Vehicle vehicle3 = new Vehicle("Tesla Model 3", "Red", "Premium", "TSL456");

        Driver driver1 = new Driver("Ahmed", "Downtown", vehicle1);
        Driver driver2 = new Driver("Sherif", "Uptown", vehicle2);
        Driver driver3 = new Driver("Mo", "Downtown", vehicle3);

        planner.addDriver(driver1);
        planner.addDriver(driver2);
        planner.addDriver(driver3);
    }

    private static void showLoginPage() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            currentUser = User.login(email, password);
            if (currentUser != null) {
                showMainMenu();
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid email or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerButton.addActionListener(e -> {
            frame.dispose();
            showRegisterPage();
        });

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void showRegisterPage() {
        JFrame frame = new JFrame("Register");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton registerButton = new JButton("Register");

        registerButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String password = new String(passwordField.getPassword());
                User.register(name, email, phone, password);
                JOptionPane.showMessageDialog(frame, "Registration successful! Please log in.");
                frame.dispose();
                showLoginPage();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(registerButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void showMainMenu() {
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton bookRideButton = new JButton("Book a Ride");
        JButton shuttleScheduleButton = new JButton("Shuttle Schedule");
        JButton rideHistoryButton = new JButton("Ride History");
        JButton logoutButton = new JButton("Logout");

        bookRideButton.addActionListener(e -> {
            frame.dispose();
            showBookingPage();
        });

        shuttleScheduleButton.addActionListener(e -> {
            frame.dispose();
            showShuttleSchedule();
        });

        rideHistoryButton.addActionListener(e -> {
            frame.dispose();
            showRideHistory();
        });

        logoutButton.addActionListener(e -> {
            frame.dispose();
            showLoginPage();
        });

        panel.add(bookRideButton);
        panel.add(shuttleScheduleButton);
        panel.add(rideHistoryButton);
        panel.add(logoutButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void showBookingPage() {
        JFrame frame = new JFrame("Book a Ride");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel currentLocationLabel = new JLabel("Current Location:");
        JTextField currentLocationField = new JTextField();

        JLabel destinationLabel = new JLabel("Destination:");
        JTextField destinationField = new JTextField();

        JButton bookButton = new JButton("Book Ride");

        bookButton.addActionListener(e -> {
            String currentLocation = currentLocationField.getText();
            String destination = destinationField.getText();
            Ride ride = currentUser.requestRide(planner, currentLocation, destination);
            if (ride != null) {
                JOptionPane.showMessageDialog(frame, "Ride booked successfully! Driver: " + ride.getDriver().getName());
            } else {
                JOptionPane.showMessageDialog(frame, "No drivers available in this location.");
            }
        });

        panel.add(currentLocationLabel);
        panel.add(currentLocationField);
        panel.add(destinationLabel);
        panel.add(destinationField);
        panel.add(bookButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void showShuttleSchedule() {
        JFrame frame = new JFrame("Shuttle Schedule");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JTextArea scheduleArea = new JTextArea("Shuttle Schedules:\n1. Downtown to Uptown - 8:00 AM\n2. Uptown to Downtown - 6:00 PM");
        scheduleArea.setEditable(false);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose();
            showMainMenu();
        });

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(scheduleArea), BorderLayout.CENTER);
        frame.add(backButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private static void showRideHistory() {
        JFrame frame = new JFrame("Ride History");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JTextArea historyArea = new JTextArea("Ride History:\n" + currentUser.getRideHistory().toString());
        historyArea.setEditable(false);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose();
            showMainMenu();
        });

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(historyArea), BorderLayout.CENTER);
        frame.add(backButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}