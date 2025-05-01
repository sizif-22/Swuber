package gui.homerightpanels;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import functionality.*;
import gui.Frame;
import gui.homerightpanels.*;

public class PaymentPanel extends JPanel {

    private User user;
    private Ride ride;
    private Frame frame;
    private JPanel cardsPanel;

    public PaymentPanel(Frame frame, User user, Ride ride) throws SQLException {
        this.user = user;
        this.ride = ride;
        this.frame = frame;

        setLayout(null);
        setBackground(new Color(55,55,55));


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
        // cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        cardsPanel.setBackground(new Color(33,33,33));
        // cardsPanel.setBounds(50, 120, 800, 300);
        
        // JPanel contentPanel = new JPanel();
        cardsPanel.setLayout(null); // Use null layout for absolute positioning
        cardsPanel.setBorder(null);
        
        
        refreshCards();

        
        // Set the preferred size for scrolling
        // add(cardsPanel);
        
        
        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setBounds(20, 120, 840, 300); // Set the bounds for the scrollable panel
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        // Add the scroll pane to this panel
        scrollPane.setBorder(null);
        add(scrollPane);


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

    public void refreshCards() throws SQLException {
        
        cardsPanel.removeAll();
        int counter=0;
        List<Card> userCards = user.getCards();
        for (int i = 0; i < Math.min(userCards.size(), 2); i++) {
            Card card = userCards.get(i);
            cardsPanel.add(new PaymentCard(frame, user, card.getCardName(), card.getCardL4Numbers(), ride, i));
            counter++;
        }
        cardsPanel.revalidate();
        cardsPanel.repaint();
        System.out.println("counter is : "+counter);
        cardsPanel.setPreferredSize(new Dimension(820, counter*160+10)); 
        
    }
}
