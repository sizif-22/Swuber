package com.swuber.gui;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;
import com.swuber.db.*;
import com.swuber.functionality.*;
import com.swuber.gui.homerightpanels.*;
import com.swuber.gui.loginandregisterpanels.*;

public class Frame extends JFrame {

    private LeftPanel leftPanel;
    private JPanel currentPanel;
    public LoginPanel loginPanel;
    public RegisterPanel registerPanel;
    // public static RidePlanner planner;
    public DatabaseConfig db = new DatabaseConfig();

    public Frame() throws SQLException {
        // Initialize the database connection in User class
        User.setODBManager(db);
        Vehicle.setDBConnect(db);
        Driver.setDBConnect(db);
        Card.setDBManager(db);
        Ride.setDBConnect(db);
        ScheduledRide.setDBConnect(db);
        ShuttleRide.setDBConnect(db);
        ShuttleRide2.setDBConnect(db);
        ImageIcon img = new ImageIcon(Frame.class.getResource("../assets/swuber.jpg"));
        setIconImage(img.getImage());
        setTitle("Swuber");
        getContentPane().setBackground(new Color(55, 55, 55));

        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        leftPanel = new LeftPanel(this);
        currentPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(300, getHeight()));
        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(currentPanel, BorderLayout.CENTER);

        loginPanel = new LoginPanel(this);
        registerPanel = new RegisterPanel(this);
        setPanel(loginPanel);

        setVisible(true);

        // Vehicle vehicle1 = new Vehicle("Toyota Corolla", "Black", "Comfort", "ABC123"
        // ,db);
        // Vehicle vehicle2 = new Vehicle("Honda Civic", "White", "Comfort", "XYZ789"
        // ,db);
        // Vehicle vehicle3 = new Vehicle("Tesla Model 3", "Red", "Premium",
        // "TSL456",db);

        // Driver driver1 = new Driver("Ahmed", "shobra", vehicle1);
        // Driver driver2 = new Driver("Sherif", "shobra", vehicle2);
        // Driver driver3 = new Driver("Mohamed", "el Salam", vehicle3);
        // Driver driver4 = new Driver("Nour", "el Salam", vehicle1);
        // Driver driver5 = new Driver("Seif", "imbaba", vehicle2);
        // Driver driver6 = new Driver("AbdelRahman", "downtown", vehicle3);
        // Driver driver7 = new Driver("Yosef", "october", vehicle1);
        // Driver driver8 = new Driver("Yousry", "zayed", vehicle2);
        // Driver driver9 = new Driver("Amir", "zamalek", vehicle3);

        // planner.addDriver(driver1);
        // planner.addDriver(driver2);
        // planner.addDriver(driver3);
        // planner.addDriver(driver4);
        // planner.addDriver(driver5);
        // planner.addDriver(driver6);
        // planner.addDriver(driver7);
        // planner.addDriver(driver8);
        // planner.addDriver(driver9);
        // planner.loadDriversFromDB();

    }

    public void setPanel(JPanel panel) {
        currentPanel.removeAll();
        currentPanel.add(panel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(currentPanel);
    }

    public void gotoHomePanels(User user) {
        leftPanel.removeAll();
        leftPanel.add(new HomeLeftPanel(this, user), BorderLayout.CENTER);
        leftPanel.revalidate();
        leftPanel.repaint();

        currentPanel.removeAll();
        currentPanel.add(new BookRide(this, user), BorderLayout.CENTER);
        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public void gotoPaymentPanel(User user, Ride ride, ShuttleRide sRide) throws SQLException {
        currentPanel.removeAll();
        currentPanel.add(new PaymentPanel(this, user, ride, sRide), BorderLayout.CENTER);
        currentPanel.revalidate();
        currentPanel.repaint();
    }

    public void gotoRideCompletedPanel(User user, Ride ride, Driver driver) {
        setPanel(new RideCompletedPanel(user, ride, driver, db));
    }

    public void gotoBookRidePanel(User user) {
        setPanel(new BookRide(this, user));
    }

    public void gotoSwuberShuttlePanel(User user) throws SQLException {
        setPanel(new Shuttle(this, user));
    }

    public void gotoScheduled(User user) throws SQLException {
        setPanel(new BookedShuttleBuses(this, user));
    }

    public void gotoRideHistoryPanel(User user) throws SQLException {
        setPanel(new PreviousRides(this, user));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Frame();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}