package gui.homerightpanels;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RidesCard extends JPanel {
  public RidesCard(String startLocation, String endLocation, String driverName, Float rating, Date rideDate, int rideCounter) {
    int height = 130 + rideCounter * 165;

    setBounds(50, height, 800, 180);
    setBackground(Color.BLACK);
    setLayout(null);
    setBorder(BorderFactory.createLineBorder(Color.white, 3));

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

    JLabel driverRatingLabel = new JLabel(driverName + " - Rating: " + rating);
    driverRatingLabel.setForeground(Color.WHITE);
    driverRatingLabel.setBounds(20, 105, 500, 30);
    driverRatingLabel.setFont(new Font("Arial", Font.PLAIN, 25));
    add(driverRatingLabel);

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    JLabel dateLabel = new JLabel("Date: " + dateFormat.format(rideDate));
    dateLabel.setForeground(Color.WHITE);
    dateLabel.setBounds(20, 140, 500, 30);
    dateLabel.setFont(new Font("Arial", Font.PLAIN, 25));
    add(dateLabel);
  }
}
