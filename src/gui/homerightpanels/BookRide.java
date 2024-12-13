package gui.homerightpanels;

import java.awt.*;

import javax.swing.*;

public class BookRide extends JPanel{
    public BookRide(){
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
        bookRideBtn.setBounds(50 , 510 , 150 , 50);
        bookRideBtn.setBackground(new Color(0, 122, 255));
        bookRideBtn.setFocusPainted(false);
        bookRideBtn.setFont(new Font("Arial", Font.BOLD, 20));
        bookRideBtn.setForeground(Color.white);
        bookRideBtn.setBorder(null);

        add(bookRideBtn);
    }
}
