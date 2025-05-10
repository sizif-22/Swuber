package com.swuber.gui.homerightpanels;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.*;
import com.swuber.functionality.*;
import com.swuber.gui.Frame;

public class PaymentPanel extends JPanel {

    private User user;
    private Ride ride;
    private ShuttleRide sRide;
    private Frame frame;
    private JPanel cardsPanel;

    public PaymentPanel(Frame frame, User user, Ride ride, ShuttleRide sRide) throws SQLException {
        this.user = user;
        this.ride = ride;
        this.sRide = sRide;
        this.frame = frame;

        setLayout(null);
        setBackground(new Color(55, 55, 55));

        // H1 Label
        JLabel h1 = new JLabel("Choose a Payment Method");
        h1.setFont(new Font("Arial", Font.BOLD, 50));
        h1.setForeground(Color.WHITE);
        h1.setBounds(50, 20, 900, 70);
        add(h1);

        // Ride Information Label - Updated to handle both cases
        String rideInfoText;
        if (ride != null && sRide == null) {
            rideInfoText = "Ride from " + ride.getStartLocation() + " to " + ride.getEndLocation();
        } else if (sRide != null && ride == null) {
            rideInfoText = "Shuttle from " + sRide.getStartLocation() + " to " + sRide.getEndLocation();
        } else {
            rideInfoText = "Invalid ride configuration";
        }

        JLabel rideInfo = new JLabel(rideInfoText);
        rideInfo.setFont(new Font("Arial", Font.BOLD, 20));
        rideInfo.setBounds(50, 450, 800, 30);
        rideInfo.setForeground(Color.white);
        add(rideInfo);

        // Payment Card Panel
        cardsPanel = new JPanel();
        cardsPanel.setBackground(new Color(33, 33, 33));
        cardsPanel.setLayout(null); // Use null layout for absolute positioning
        cardsPanel.setBorder(null);

        refreshCards();

        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setBounds(20, 120, 840, 300); // Set the bounds for the scrollable panel
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add the scroll pane to this panel
        scrollPane.setBorder(null);
        add(scrollPane);

        int addNewBtnHeight = 70;
        int orLabelHeight = 70;
        int cashBtnHeight = 70;
        int verticalSpacing = 20;

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
        cashBtn.setBounds(50, 450 + 50 + addNewBtnHeight + verticalSpacing + orLabelHeight + verticalSpacing, 150,
                cashBtnHeight);
        cashBtn.setFocusPainted(false);
        cashBtn.setFont(new Font("Arial", Font.BOLD, 20));
        cashBtn.setBackground(Color.green);
        cashBtn.setForeground(Color.white);
        cashBtn.setBorder(null);
        add(cashBtn);

        cashBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sRide != null && ride == null) {
                    // Handle Shuttle Ride case
                    try {
                        ScheduledRide scheduledRide = new ScheduledRide(sRide, user);
                        // Add the scheduled ride to database or perform necessary operations
                        JOptionPane.showMessageDialog(frame,
                                "Shuttle seat reserved.",
                                "Reservation Confirmed",
                                JOptionPane.INFORMATION_MESSAGE);
                        frame.gotoHomePanels(user);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame,
                                "Failed to reserve shuttle seat: " + ex.getMessage(),
                                "Reservation Failed",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else if (ride != null && sRide == null) {
                    // Handle Regular Ride case
                    frame.gotoRideCompletedPanel(user, ride, ride.getDriver());
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Invalid ride configuration",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void refreshCards() throws SQLException {
        cardsPanel.removeAll();
        int counter = 0;

        // Fix for ClassCastException: Convert the Collection to a List safely
        Collection<?> cardsCollection = user.getCards();
        List<Card> userCards = new ArrayList<>();

        // Safely convert the collection to a List of Cards
        if (cardsCollection != null) {
            for (Object obj : cardsCollection) {
                if (obj instanceof Card) {
                    userCards.add((Card) obj);
                }
            }
        }

        for (int i = 0; i < Math.min(userCards.size(), 2); i++) {
            Card card = userCards.get(i);
            cardsPanel.add(new PaymentCard(frame, user, card.getCardName(), card.getCardL4Numbers(), ride, sRide, i));
            counter++;
        }

        cardsPanel.revalidate();
        cardsPanel.repaint();
        System.out.println("counter is : " + counter);
        cardsPanel.setPreferredSize(new Dimension(820, counter * 160 + 10));
    }
}