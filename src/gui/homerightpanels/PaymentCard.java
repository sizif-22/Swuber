package gui.homerightpanels;

import javax.swing.*;
import java.awt.*;
import functionality.*;
import gui.Frame;

public class PaymentCard extends JPanel {

    static int cardsCounter = 0;

    public PaymentCard(Frame frame, User user, String cardName, String cardNumber, Ride ride, int i) {
        int height = 10 + 160 * i;

        setBounds(10, height, 800, 150);
        setBackground(new Color(55,55,55));

        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.white, 3));

        JLabel cardNameLabel = new JLabel(cardName);
        cardNameLabel.setForeground(Color.WHITE);
        cardNameLabel.setBounds(20, 20, 500, 50);
        cardNameLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        add(cardNameLabel);

        JLabel cardNumberLabel = new JLabel("Card ending with " + cardNumber.substring(Math.max(cardNumber.length() - 4, 0)));
        cardNumberLabel.setForeground(Color.WHITE);
        cardNumberLabel.setBounds(20, 80, 500, 50);
        cardNumberLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        add(cardNumberLabel);

        JButton bookBtn = new JButton("Use");
        bookBtn.setBounds(550, 40, 200, 80);
        bookBtn.setBackground(new Color(123, 50, 250));
        bookBtn.setFocusPainted(false);
        bookBtn.setFont(new Font("Arial", Font.BOLD, 20));
        bookBtn.setForeground(Color.white);
        bookBtn.setBorder(null);
        add(bookBtn);

        bookBtn.addActionListener(e -> {
            if (ride instanceof ShuttleRide && ride != null) {
                ShuttleRide shuttleRide = (ShuttleRide) ride; 
                shuttleRide.addPassenger(user); 
                JOptionPane.showMessageDialog(frame, "Shuttle seat reserved.", "Reservation Confirmed", JOptionPane.INFORMATION_MESSAGE);
                frame.gotoHomePanels(user); 
            } else {
                frame.gotoRideCompletedPanel(user, ride, ride.getDriver()); 
            }       
        });

        cardsCounter++;
    }

    static int getCardsCounter() {
        return cardsCounter;
    }
}
