package com.swuber.gui.homerightpanels;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import com.swuber.functionality.Card;
import com.swuber.functionality.User;
import com.swuber.gui.Frame;

public class AddNewCardDialog extends JDialog {

    public AddNewCardDialog(Frame owner, User user, PaymentPanel paymentPanel) {
        super(owner, "Add New Card", true);
        this.user = user;
        this.paymentPanel = paymentPanel;

        JPanel contentPane = new JPanel(new GridLayout(5, 2, 5, 5));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel cardNameLabel = new JLabel("Card Name:");
        cardNameField = new JTextField();
        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberField = new JTextField();
        JLabel expirationDateLabel = new JLabel("Expiration Date (MM/YY):");
        expirationDateField = new JTextField();
        JLabel cardHolderNameLabel = new JLabel("Card Holder Name:");
        cardHolderNameField = new JTextField();

        JButton addCardButton = new JButton("Add Card");
        addCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardName = cardNameField.getText();
                String cardNumber = cardNumberField.getText();
                String expirationDate = expirationDateField.getText();
                String cardHolderName = cardHolderNameField.getText();

                if (cardName.isEmpty() || cardNumber.isEmpty() || expirationDate.isEmpty()
                        || cardHolderName.isEmpty()) {
                    JOptionPane.showMessageDialog(AddNewCardDialog.this, "Please fill in all card details.");
                    return;
                }

                try {
                    user.addCard(cardName, cardNumber, expirationDate, cardHolderName);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(AddNewCardDialog.this, "Card added successfully!");
                dispose();
                try {
                    paymentPanel.refreshCards();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        contentPane.add(cardNameLabel);
        contentPane.add(cardNameField);
        contentPane.add(cardNumberLabel);
        contentPane.add(cardNumberField);
        contentPane.add(expirationDateLabel);
        contentPane.add(expirationDateField);
        contentPane.add(cardHolderNameLabel);
        contentPane.add(cardHolderNameField);
        contentPane.add(new JLabel(""));
        contentPane.add(addCardButton);

        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(owner);
        setVisible(true);
    }

    private JTextField expirationDateField;
    private JTextField cardHolderNameField;
    private JTextField cardNameField;
    private JTextField cardNumberField;
    private User user;
    private PaymentPanel paymentPanel;

}