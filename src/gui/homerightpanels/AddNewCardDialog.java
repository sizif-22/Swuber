package gui.homerightpanels; 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import functionality.Card;
import functionality.User;
import gui.Frame; 

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

                if (cardName.isEmpty() || cardNumber.isEmpty() || expirationDate.isEmpty() || cardHolderName.isEmpty()) {
                    JOptionPane.showMessageDialog(AddNewCardDialog.this, "Please fill in all card details.");
                    return;
                }

                user.addCard(cardName, cardNumber, expirationDate, cardHolderName); 
                JOptionPane.showMessageDialog(AddNewCardDialog.this, "Card added successfully!");
                dispose();
                paymentPanel.refreshCards();
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