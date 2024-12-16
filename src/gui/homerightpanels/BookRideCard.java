package gui.homerightpanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import functionality.*;
import gui.Frame;


public class BookRideCard extends JPanel {
	private String startLocation;
	private String endLocation;
	private Frame frame;
	private User user;
	private float price;
	// static int rideCounter = 0;

	public BookRideCard(String startLocation, String endLocation, String route, int maxPassengers, float price, Frame frame, User user , int rideCounter) {
			this.startLocation = startLocation;
			this.endLocation = endLocation;
			this.frame = frame;
			this.user = user;
			this.price = price;

			int height = 220 + rideCounter * 165;

			setBounds(50, height, 800, 150);
			setBackground(new Color(55,55,55));
			setLayout(null); 
			setBorder(BorderFactory.createLineBorder(Color.white, 3));

			JLabel rideLabel = new JLabel("Ride" + (rideCounter + 1));
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

			JButton bookBtn = new JButton("Book Now");
			bookBtn.setBounds(600, 50, 150, 50); 
			bookBtn.setBackground(new Color(123, 50, 250));
			bookBtn.setFocusPainted(false);
			bookBtn.setFont(new Font("Arial", Font.BOLD, 20));
			bookBtn.setForeground(Color.white);
			bookBtn.setBorder(null);
			add(bookBtn);

			bookBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
							ShuttleRide ride = new ShuttleRide(user, startLocation, endLocation, maxPassengers, route, "10:00 AM",
											price); 
							frame.gotoPaymentPanel(user, ride);
					}
			});

			// rideCounter++;
	}
}