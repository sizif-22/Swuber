package gui.homerightpanels;

import javax.swing.*;
import java.awt.*;

public class RideCompletedPanel extends JPanel {
    public RideCompletedPanel(String driverName){
        setBounds(300, 0, 900, 800);
        setBackground(Color.BLACK);
        setLayout(null);

        JLabel h1 = new JLabel("Ride Completed");
        h1.setFont(new Font("Arial", Font.BOLD, 50));
        h1.setForeground(Color.WHITE);
        h1.setBounds(50, 70, 900, 70);
        add(h1);

        JLabel h2 = new JLabel("Ride " + driverName + " :");
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

        JButton backBtn = new JButton("Back to Home");
        backBtn.setForeground(Color.white);
        backBtn.setBounds(300,650 ,300,50);
        backBtn.setFont(new Font("Arial" , Font.BOLD, 25));
        backBtn.setFocusPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setBorder(null);
        add(backBtn);
    }
}
