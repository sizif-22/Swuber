package gui.homerightpanels;

import java.awt.*;
import java.awt.event.*;
import functionality.*;
import javax.swing.*;
import gui.Frame;

public class BookRide extends JPanel {

	private Frame frame;
	private User user;

	public BookRide(Frame frame, User user) {
		this.frame = frame;
		this.user = user;

		System.out.println("Drivers in System: " + Driver.getAllDrivers().size());

		setBounds(300, 0, 900, 800);
		setBackground(Color.BLACK);
		setLayout(null);

		// title Label
		JLabel title = new JLabel("Book a Ride With Swuber");
		title.setFont(new Font("Arial", Font.BOLD, 45));
		title.setForeground(Color.WHITE);
		title.setBounds(50, 100, 900, 80);
		add(title);

		// Pickup Location Label
		JLabel pickupLocation = new JLabel("Pickup Location");
		pickupLocation.setFont(new Font("Arial", Font.BOLD, 20));
		pickupLocation.setForeground(Color.WHITE);
		pickupLocation.setBounds(50, 230, 400, 40);
		add(pickupLocation);

		// pickup Location Field TextField
		JTextField pickupLocationField = new JTextField();
		pickupLocationField.setBounds(50, 290, 400, 30);
		add(pickupLocationField);

		// Destination Label
		JLabel destination = new JLabel("Destination");
		destination.setFont(new Font("Arial", Font.BOLD, 20));
		destination.setForeground(Color.WHITE);
		destination.setBounds(50, 370, 400, 40);
		add(destination);

		// Destination Field TextField
		JTextField destinationField = new JTextField();
		destinationField.setBounds(50, 430, 400, 30);
		add(destinationField);

		JButton bookRideBtn = new JButton("Book Ride");
		bookRideBtn.setBounds(50, 510, 150, 50);
		bookRideBtn.setBackground(new Color(0, 122, 255));
		bookRideBtn.setFocusPainted(false);
		bookRideBtn.setFont(new Font("Arial", Font.BOLD, 20));
		bookRideBtn.setForeground(Color.white);
		bookRideBtn.setBorder(null);

		add(bookRideBtn);
		bookRideBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pickupLocation = pickupLocationField.getText().toLowerCase();
				String destination = destinationField.getText().toLowerCase();

				if (pickupLocation.isEmpty() || destination.isEmpty()) {
					// Show error message
					JOptionPane.showMessageDialog(BookRide.this, "Please enter pickup location and destination!");
					return;
				}

				// creating ride obj 
				Ride ride = new Ride(user, pickupLocation, destination);

				// Call RidePlanner to match a driver
				Driver matchedDriver = Frame.planner.matchDriverToRide(ride);
				if (matchedDriver != null) {
					JOptionPane.showMessageDialog(BookRide.this, "Driver found!");

					try {
						JOptionPane.showMessageDialog(BookRide.this,
								"Ride in Progress! Your Driver: " + ride.getDriver().getName() + " Heading to: "
										+ ride.getEndLocation() + " In a " + ride.getDriver().getVehicleInfo());

						Thread.sleep(5000); // 5 Second Ride Sim : ak
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}

					// After simulated wait time, show payment panel ( i wanted to do a spinny loader :( )
					frame.gotoPaymentPanel(user, ride);
				} else {
					// no driver found, show error message
					JOptionPane.showMessageDialog(BookRide.this,
							"No driver available at this time. Please try again later.");
				}
			}
		});
	}
}