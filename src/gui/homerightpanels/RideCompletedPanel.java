package gui.homerightpanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import functionality.*;

public class RideCompletedPanel extends JPanel {

  private Ride ride;
  private Driver driver;

  public RideCompletedPanel(User user, Ride ride, Driver driver) {
    this.ride = ride;
    this.driver = driver;
    setBounds(300, 0, 900, 800);
    setBackground(Color.BLACK);
    setLayout(null);

    JLabel h1 = new JLabel("Ride Completed");
    h1.setFont(new Font("Arial", Font.BOLD, 50));
    h1.setForeground(Color.WHITE);
    h1.setBounds(50, 70, 900, 70);
    add(h1);

    JLabel h2 = new JLabel("Ride with " + driver.getName() + " :");
    h2.setFont(new Font("Arial", Font.BOLD, 30));
    h2.setForeground(Color.WHITE);
    h2.setBounds(50, 200, 400, 50);
    add(h2);

    JTextField tf = new JTextField();
    tf.setFont(new Font("Arial", Font.BOLD, 20));
    tf.setBounds(410, 200, 400, 50);
    add(tf);

    JLabel h3 = new JLabel("Thank you for using Swuber");
    h3.setFont(new Font("Arial", Font.BOLD, 30));
    h3.setForeground(Color.WHITE);
    h3.setBounds(0, 350, 900, 70);
    h3.setHorizontalAlignment(JLabel.CENTER);
    add(h3);

    JPanel starPanel = new JPanel();
    starPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
    starPanel.setBounds(250, 450, 400, 50);
    starPanel.setBackground(Color.BLACK);
    add(starPanel);

    ButtonGroup ratingGroup = new ButtonGroup();
    JRadioButton[] stars = new JRadioButton[5];
    for (int i = 0; i < 5; i++) {
      stars[i] = new JRadioButton(new ImageIcon("star.png"));
      stars[i].setBackground(Color.BLACK);
      stars[i].setBorder(null);
      ratingGroup.add(stars[i]);
      starPanel.add(stars[i]);
    }
    stars[4].setSelected(true);

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
        float rating = 0;
        for (int i = 0; i < 5; i++) {
          if (stars[i].isSelected()) {
            rating = i + 1;
            break;
          }
        }

        ride.rateRide(rating);
        ride.completeRide();
        ride.getDriver().markRideAsComplete(ride);
        user.addRideToHistory(ride);
        JOptionPane.showMessageDialog(null, "Driver rating submitted successfully!",
            "Rating Submitted", JOptionPane.INFORMATION_MESSAGE);
      }
    });
  }
}