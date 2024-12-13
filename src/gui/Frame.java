package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import functionality.*;
import gui.homerightpanels.*;
import gui.loginandregisterpanels.*;

public class Frame extends JFrame {

    private LeftPanel leftPanel;
    private JPanel currentPanel;
    public LoginPanel loginPanel; 
    public RegisterPanel registerPanel; 
    public static RidePlanner planner;

    public Frame() {
        planner = new RidePlanner();
        setTitle("Swuber");
        getContentPane().setBackground(Color.BLACK);

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

    public void setPanel(JPanel panel) {
        currentPanel.removeAll();
        currentPanel.add(panel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(currentPanel);
    }
    public void gotoHomePanels(User user) { 
        leftPanel.removeAll(); 
        leftPanel.add(new HomeLeftPanel(this, user), BorderLayout.CENTER); // Add HomeLeftPanel
        leftPanel.revalidate(); 
        leftPanel.repaint();

        currentPanel.removeAll();
        currentPanel.add(new BookRide(this, user), BorderLayout.CENTER);
        currentPanel.revalidate();
        currentPanel.repaint();
    }
    public void gotoPaymentPanel(User user, Ride ride) {
        currentPanel.removeAll();
        currentPanel.add(new PaymentPanel(this, user, ride), BorderLayout.CENTER);
        currentPanel.revalidate();
        currentPanel.repaint();
    }
    
    public void gotoRideCompletedPanel(User user, Ride ride, Driver driver) {
        setPanel(new RideCompletedPanel(user, ride, driver));
    }
    public void gotoBookRidePanel(User user) {
        setPanel(new BookRide(this, user));
    }
    
    public void gotoSwuberShuttlePanel(User user) {
        setPanel(new Shuttle(this, user)); // Assuming you have this panel
    }
    
    public void gotoRideHistoryPanel(User user) {
        setPanel(new PreviousRides(this, user)); // Assuming you have this panel
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Frame());
    }
}