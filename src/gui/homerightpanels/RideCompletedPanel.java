package gui.homerightpanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import functionality.*;

public class RideCompletedPanel extends JPanel {

  private Ride ride;
  private Driver driver;
  private JFormattedTextField tf;
  private User user;

  public RideCompletedPanel(User user, Ride ride, Driver driver) {
    this.ride = ride;
    this.driver = driver;
    this.user = user;

    setBounds(300, 0, 900, 800);
    setBackground(new Color(55,55,55));

    setLayout(null);

    JLabel h1 = new JLabel("Ride Completed");
    h1.setFont(new Font("Arial", Font.BOLD, 50));
    h1.setForeground(Color.WHITE);
    h1.setBounds(50, 70, 900, 70);
    add(h1);

    JLabel h2 = new JLabel("Rate ride with " + driver.getName() + " :");
    h2.setFont(new Font("Arial", Font.BOLD, 30));
    h2.setForeground(Color.WHITE);
    h2.setBounds(50, 200, 500, 50);
    add(h2);

    tf = new JFormattedTextField(NumberFormat.getNumberInstance());
    tf.setFont(new Font("Arial", Font.BOLD, 20));
    tf.setBounds(600, 200, 200, 50);
    add(tf);

    JLabel h3 = new JLabel("Thank you for using Swuber");
    h3.setFont(new Font("Arial", Font.BOLD, 30));
    h3.setForeground(Color.WHITE);
    h3.setBounds(0, 350, 900, 70);
    h3.setHorizontalAlignment(JLabel.CENTER);
    add(h3);

    JButton submitBtn = new JButton("Submit Rating");
    submitBtn.setForeground(Color.white);
    submitBtn.setBounds(300, 550, 300, 50);
    submitBtn.setFont(new Font("Arial", Font.BOLD, 25));
    submitBtn.setFocusPainted(false);
    submitBtn.setContentAreaFilled(false);
    submitBtn.setBorder(null);
    add(submitBtn);

    submitBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        float rating = 0.0f;
        try {
          rating = ((Number) tf.getValue()).floatValue();
          if (rating < 0 || rating > 5) {
            JOptionPane.showMessageDialog(null, "Rating must be between 0 and 5!", "Invalid Rating",
                JOptionPane.ERROR_MESSAGE);
            return; // Stop further execution
          }
          ride.rateRide(rating);
          ride.completeRide();
          ride.getDriver().markRideAsComplete(ride);
          user.addRideToHistory(ride);
          JOptionPane.showMessageDialog(null, "Driver rating submitted successfully!",
              "Rating Submitted", JOptionPane.INFORMATION_MESSAGE);
        } catch (NullPointerException ex) {
          JOptionPane.showMessageDialog(null, "Please enter a rating.", "Invalid Rating", JOptionPane.ERROR_MESSAGE);
        } catch (ClassCastException ex) {
          JOptionPane.showMessageDialog(null, "Invalid rating format. Please enter a number.", "Invalid Rating",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });
  }
}