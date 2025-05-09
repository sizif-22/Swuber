package com.swuber.gui.homerightpanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import com.swuber.functionality.*;
import com.swuber.gui.Frame;

import jakarta.persistence.EntityManager;

public class BookRideCard extends JPanel {
	private int rideId;
	private String startLocation;
	private String endLocation;
	private Frame frame;
	private User user;
	private float price;
	private int maxPassengers;
	private Boolean isScheduled;

	public BookRideCard(int rideId, String startLocation, String endLocation, int maxPassengers, float price,
			Frame frame,
			User user, int rideCounter, Boolean isScheduled) {
		this.rideId = rideId;
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.frame = frame;
		this.user = user;
		this.price = price;
		this.maxPassengers = maxPassengers;
		this.isScheduled = isScheduled;

		int height = 10 + rideCounter * 165;

		setBounds(10, height, 800, 150);
		setBackground(new Color(55, 55, 55));
		setLayout(null);
		setBorder(BorderFactory.createLineBorder(Color.white, 3));

		// Set up ride card UI components
		JLabel rideLabel = new JLabel("Ride " + (rideCounter + 1));
		rideLabel.setForeground(Color.WHITE);
		rideLabel.setBounds(20, 10, 500, 40);
		rideLabel.setFont(new Font("Arial", Font.BOLD, 30));
		add(rideLabel);

		JLabel startEndLabel = new JLabel(startLocation + " - " + endLocation);
		startEndLabel.setForeground(Color.WHITE);
		startEndLabel.setBounds(20, 60, 500, 40);
		startEndLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		add(startEndLabel);

		JLabel priceLabel = new JLabel(String.valueOf(price) + " EGP ");
		priceLabel.setForeground(Color.WHITE);
		priceLabel.setBounds(20, 105, 500, 30);
		priceLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		add(priceLabel);

		// Create the appropriate button based on whether the ride is already scheduled
		JButton bookBtn;
		if (isScheduled) {
			bookBtn = new JButton("Booked");
			bookBtn.setBounds(600, 50, 150, 50);
			bookBtn.setBackground(new Color(50, 50, 50));
			bookBtn.setFocusPainted(false);
			bookBtn.setFont(new Font("Arial", Font.BOLD, 20));
			bookBtn.setForeground(Color.white);
			bookBtn.setBorder(null);
			add(bookBtn);
		} else {
			bookBtn = new JButton("Book Now");
			bookBtn.setBounds(600, 50, 150, 50);
			bookBtn.setBackground(new Color(123, 50, 250));
			bookBtn.setFocusPainted(false);
			bookBtn.setFont(new Font("Arial", Font.BOLD, 20));
			bookBtn.setForeground(Color.white);
			bookBtn.setBorder(null);
			add(bookBtn);

			// Add action listener for booking
			bookBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						// Get the ride from the database by ID
						EntityManager em = frame.db.getEntityManager();
						ShuttleRide ride = em.find(ShuttleRide.class, rideId);
						System.out.println("is the condition true ? " + ride != null);
						if (ride != null) {
							// Create scheduled ride

							ScheduledRide.addNewScheduledRide(ride, user);
							frame.gotoPaymentPanel(user, null, ride);

						} else {
							JOptionPane.showMessageDialog(frame,
									"This ride is no longer available.",
									"No Seats Available",
									JOptionPane.WARNING_MESSAGE);
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(frame,
								"Database error: " + e1.getMessage(),
								"Error",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(frame,
								"An error occurred: " + e2.getMessage(),
								"Error",
								JOptionPane.ERROR_MESSAGE);
						e2.printStackTrace();
					}
				}
			});
		}
	}
}