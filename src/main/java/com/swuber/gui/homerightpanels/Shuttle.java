package com.swuber.gui.homerightpanels;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;
import com.swuber.functionality.*;
import com.swuber.gui.Frame;

public class Shuttle extends JPanel {

  private Frame frame;
  private User user;

  public Shuttle(Frame frame, User user) throws SQLException {
    this.frame = frame;
    this.user = user;

    setBounds(300, 0, 900, 800);
    setBackground(new Color(55, 55, 55));

    setLayout(null);

    JLabel h1 = new JLabel("Swuber Shuttle");
    h1.setFont(new Font("Arial", Font.BOLD, 50));
    h1.setForeground(Color.WHITE);
    h1.setBounds(50, 100, 900, 40);
    add(h1);

    JLabel h2 = new JLabel("Available Routes");
    h2.setFont(new Font("Arial", Font.PLAIN, 35));
    h2.setForeground(Color.WHITE);
    h2.setBounds(50, 160, 900, 40);
    add(h2);

    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(null);
    contentPanel.setBackground(new Color(33, 33, 33));
    contentPanel.setBorder(null);

    // Get all available shuttle rides
    List<ShuttleRide> rides = ShuttleRide.getAllShuttleRides();
    int counter = 0;

    // Create a ride card for each shuttle ride
    for (ShuttleRide ride : rides) {
      System.out.println("Processing shuttle ride #" + ride.getRideID());
      // Check if the ride is already scheduled by this user
      boolean isScheduled = ride.isScheduledBy(user);
      System.out
          .println("Is ride #" + ride.getRideID() + " scheduled by user #" + user.getUserId() + "? " + isScheduled);

      contentPanel.add(new BookRideCard(
          ride.getRideID(),
          ride.getStartLocation(),
          ride.getEndLocation(),
          ride.getMaxPassengers(),
          ride.getPrice(),
          frame,
          user,
          counter++,
          isScheduled));
    }

    // Set up scrolling panel for ride cards
    contentPanel.setPreferredSize(new Dimension(820, (165 * Math.max(counter, 1) + 10)));
    JScrollPane scrollPane = new JScrollPane(contentPanel);
    scrollPane.setBounds(20, 220, 840, 500);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setBorder(null);

    // Add the scroll pane to this panel
    add(scrollPane);
  }
}