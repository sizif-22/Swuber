package gui;

import java.awt.*;


import javax.swing.*;


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
        ImageIcon img = new ImageIcon(Frame.class.getResource("../swuber.jpg"));
        setIconImage(img.getImage());
        planner = new RidePlanner();
        setTitle("Swuber");
        getContentPane().setBackground(new Color(55,55,55));

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

		Driver driver1 = new Driver("Ahmed", "shobra", vehicle1);
		Driver driver2 = new Driver("Sherif", "shobra", vehicle2);
		Driver driver3 = new Driver("Mohamed", "el Salam", vehicle3);
        Driver driver4 = new Driver("Nour", "el Salam", vehicle1);
		Driver driver5 = new Driver("Seif", "imbaba", vehicle2);
		Driver driver6 = new Driver("AbdelRahman", "downtown", vehicle3);
        Driver driver7 = new Driver("Yosef", "october", vehicle1);
		Driver driver8 = new Driver("Yousry", "zayed", vehicle2);
		Driver driver9 = new Driver("Amir", "zamalek", vehicle3);

        planner.addDriver(driver1);
        planner.addDriver(driver2);
        planner.addDriver(driver3);
        planner.addDriver(driver4);
        planner.addDriver(driver5);
        planner.addDriver(driver6);
        planner.addDriver(driver7);
        planner.addDriver(driver8);
        planner.addDriver(driver9);

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
        setPanel(new Shuttle(this, user)); 
    }
    
    public void gotoRideHistoryPanel(User user) {
        setPanel(new PreviousRides(this, user)); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Frame());
    }
}