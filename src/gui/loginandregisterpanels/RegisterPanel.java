package gui.loginandregisterpanels;

import javax.swing.*;

import functionality.User;

import java.awt.*;
import gui.Frame;

public class RegisterPanel extends JPanel {
    private Frame mainFrame;

    public RegisterPanel(Frame frame) {
        this.mainFrame = frame;
        setBounds(300, 0, 900, 800);
        setBackground(Color.BLACK);
        setLayout(null);

        // Admin Log In Label
        JLabel adminLabel = new JLabel("Register");
        adminLabel.setFont(new Font("Arial", Font.BOLD, 24));
        adminLabel.setForeground(Color.WHITE);
        adminLabel.setBounds(350, 20, 200, 50);
        add(adminLabel);

        // Name Label
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(250, 90, 400, 20);
        add(nameLabel);

        // Name TextField
        JTextField nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.BOLD, 18));
        nameField.setBounds(250, 115, 400, 30);
        add(nameField);

        // Email Label
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 18));
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(250, 165, 400, 20);
        add(emailLabel);

        // Email TextField
        JTextField emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.BOLD, 18));
        emailField.setBounds(250, 190, 400, 30);
        add(emailField);

        // Password Label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(250, 240, 400, 20);
        add(passwordLabel);

        // Password Field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(250, 265, 400, 30);
        add(passwordField);

        // PhoneNumber Label
        JLabel phoneNumberLabel = new JLabel("PhoneNumber");
        phoneNumberLabel.setFont(new Font("Arial", Font.BOLD, 18));
        phoneNumberLabel.setForeground(Color.WHITE);
        phoneNumberLabel.setBounds(250, 315, 400, 20);
        add(phoneNumberLabel);

        // Gender Field
        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setBounds(250, 340, 400, 30);
        add(phoneNumberField);

        // // Create radio buttons for gender
        // JRadioButton maleRadioButton = new JRadioButton("Male");
        // maleRadioButton.setBounds(50 , 500 , 20,20);
        // JRadioButton femaleRadioButton = new JRadioButton("Female");
        // femaleRadioButton.setBounds(200 , 500 , 20,20);
        // // Group the radio buttons to ensure only one can be selected at a time
        // ButtonGroup genderGroup = new ButtonGroup();
        // genderGroup.add(maleRadioButton);
        // genderGroup.add(femaleRadioButton);
        // // Add radio buttons to the panel
        // add(maleRadioButton);
        // add(femaleRadioButton);

        // مفيش gender علشان احمد خالد ميزعلش

        // Register Button
        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(255, 102, 0));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.PLAIN, 16));
        registerButton.setFocusPainted(false);
        registerButton.setBounds(250, 420, 400, 50);
        registerButton.setBorder(null);
        add(registerButton);

        // Register button action listener
        registerButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String phoneNumber = phoneNumberField.getText();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            User.register(name, email, phoneNumber, password);

            nameField.setText("");
            emailField.setText("");
            passwordField.setText("");
            phoneNumberField.setText("");

            // back to login panel after successful registration
            mainFrame.setPanel(mainFrame.loginPanel);
        });

        // Already Have an Account Button
        JButton haveAccountBtn = new JButton("Already Have an Account? Log In");
        haveAccountBtn.setBackground(new Color(33, 33, 33));
        haveAccountBtn.setForeground(Color.WHITE);
        haveAccountBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        haveAccountBtn.setFocusPainted(false);
        haveAccountBtn.setBounds(250, 510, 400, 50);
        haveAccountBtn.setBorder(null);
        add(haveAccountBtn);

        haveAccountBtn.addActionListener(e -> {
            mainFrame.setPanel(mainFrame.loginPanel); 
        });
    }
}