package gui.homerightpanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import functionality.*;
import gui.Frame;

public class BookRideCard extends JPanel {
	private String startLocation;
	private int id;
	private String endLocation;
	private Frame frame;
	private User user;
	private float price;
	private Boolean isScheduled;
	// static int rideCounter = 0;

	public BookRideCard(int id, String startLocation, String endLocation, int maxPassengers, float price, Frame frame,
			User user, int rideCounter, Boolean isScheduled) {
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.id = id;
		this.frame = frame;
		this.user = user;
		this.price = price;
		this.isScheduled = isScheduled;

		int height = 10 + rideCounter * 165;

		setBounds(10, height, 800, 150);
		setBackground(new Color(55, 55, 55));
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
			bookBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ShuttleRide ride = new ShuttleRide(user, startLocation, endLocation, maxPassengers, "10:00 AM",
							price);
					try {
						String sql = "insert into scheduledRides (shuttleId , userId) values (" + id + " , "
								+ user.getUserId() + ");";
						System.out.println(sql);
						frame.db.statement.executeUpdate(sql);
						frame.gotoPaymentPanel(user, ride);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
	}
}