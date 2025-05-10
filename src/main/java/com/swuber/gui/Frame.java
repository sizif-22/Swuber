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

    public Frame() {
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
        setPanel(new RideCompletedPanel(this, user, ride, driver, db));
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

    public void gotoRideHistoryPanel(User user) {
        setPanel(new PreviousRides(this, user));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Frame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}