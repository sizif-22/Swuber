package gui.homerightpanels;

import java.awt.*;
import javax.swing.*;
import functionality.*;
import gui.Frame;

public class Shuttle extends JPanel {

  private Frame frame;
  private User user;

  public Shuttle(Frame frame, User user) {
    this.frame = frame;
    this.user = user;

    setBounds(300, 0, 900, 800);
    setBackground(Color.BLACK);
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

    String r1 = "New Cairo - October";
    String r2 = "Cairo - Alex";
    // String r3 = "Giza - Maadi";

    add(new BookRideCard("New Cairo", "6th October", r1, 10, 50.0f,"12:00PM", frame, user,0));
    add(new BookRideCard("Cairo", "Alexandria", r2, 16, 80.0f, "9:00 AM", frame, user,1));
    // add(new BookRideCard("Giza", "Maadi", r3, 15, 60.0f,"1:30 PM", frame, user,1));

  }
}