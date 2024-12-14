package gui.homerightpanels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import functionality.*;
import gui.Frame;
import gui.homerightpanels.AddNewCardDialog;

public class PaymentPanel extends JPanel {

    private User user;
    private Ride ride;
    private Frame frame;
    private JPanel cardsPanel;

    public PaymentPanel(Frame frame, User user, Ride ride) {
        this.user = user;
        this.ride = ride;
        this.frame = frame;

        setLayout(null);
        setBackground(Color.BLACK);

        // H1 Label
        JLabel h1 = new JLabel("Choose a Payment Method");
        h1.setFont(new Font("Arial", Font.BOLD, 50));
        h1.setForeground(Color.WHITE);
        h1.setBounds(50, 20, 900, 70);
        add(h1);

        // Ride Information Label
        JLabel rideInfo = new JLabel("Ride from " + ride.getStartLocation() + " to " + ride.getEndLocation());
        rideInfo.setFont(new Font("Arial", Font.BOLD, 20));
        rideInfo.setBounds(50, 450, 800, 30);
        rideInfo.setForeground(Color.white);
        add(rideInfo);

        // Payment Card Panel
        cardsPanel = new JPanel();
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        cardsPanel.setBackground(Color.BLACK);
        cardsPanel.setBounds(50, 120, 800, 300);
        add(cardsPanel);
        refreshCards();


        int numOfCards = user.getCards().size(); 
        int cardHeight = 150;
        int spacing = 10;
        int addNewBtnHeight = 70;
        int orLabelHeight = 70;
        int cashBtnHeight = 70;
        int verticalSpacing = 20; 

        int height = 120 + 
                numOfCards * cardHeight + 
                (numOfCards > 0 ? (numOfCards - 1) * spacing : 0) +
                addNewBtnHeight + verticalSpacing + orLabelHeight + verticalSpacing + cashBtnHeight;

        // Add New Button
        JButton addNewBtn = new JButton("+ Add New");
        addNewBtn.setBounds(50, 450 + 50, 300, addNewBtnHeight); 
        addNewBtn.setFocusPainted(false);
        addNewBtn.setFont(new Font("Arial", Font.BOLD, 20));
        addNewBtn.setForeground(Color.white);
        addNewBtn.setHorizontalAlignment(JButton.LEFT);
        addNewBtn.setBorder(null);
        addNewBtn.setContentAreaFilled(false);
        add(addNewBtn);

        addNewBtn.addActionListener(e -> {
            AddNewCardDialog dialog = new AddNewCardDialog(frame, user, this);
            dialog.setVisible(true);
        });

        // "Or" Label
        JLabel orLabel = new JLabel("Or");
        orLabel.setBounds(50, 450 + 50 + addNewBtnHeight + verticalSpacing, 200, orLabelHeight);
        orLabel.setFont(new Font("Arial", Font.BOLD, 20));
        orLabel.setForeground(Color.white);
        add(orLabel);

        JButton cashBtn = new JButton("Cash");
        cashBtn.setBounds(50, 450 + 50 + addNewBtnHeight + verticalSpacing + orLabelHeight + verticalSpacing, 150, cashBtnHeight);
        cashBtn.setFocusPainted(false);
        cashBtn.setFont(new Font("Arial", Font.BOLD, 20));
        cashBtn.setBackground(Color.green);
        cashBtn.setForeground(Color.white);
        cashBtn.setBorder(null);
        add(cashBtn);

        cashBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ride instanceof ShuttleRide && ride != null) {
                    ShuttleRide shuttleRide = (ShuttleRide) ride; 
                    shuttleRide.addPassenger(user); 
                    JOptionPane.showMessageDialog(frame, "Shuttle seat reserved.", "Reservation Confirmed", JOptionPane.INFORMATION_MESSAGE);
                    frame.gotoHomePanels(user); 
                } else {
                    frame.gotoRideCompletedPanel(user, ride, ride.getDriver()); 
                }
            }
        });
        
    }

    public void refreshCards() {
        cardsPanel.removeAll();
        List<Card> userCards = user.getCards();
        for (int i = 0; i < Math.min(userCards.size(), 2); i++) {
            Card card = userCards.get(i);
            cardsPanel.add(new PaymentCard(frame, user, card.getCardName(), card.getCardL4Numbers(), ride, i));
        }
        cardsPanel.revalidate();
        cardsPanel.repaint();
    }
}
