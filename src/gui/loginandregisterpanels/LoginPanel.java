package gui.loginandregisterpanels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import functionality.User;
import gui.Frame;

public class LoginPanel extends JPanel {

    private Frame mainFrame;
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginPanel(Frame frame) {
        this.mainFrame = frame;

        setBounds(300, 0, 900, 800);
        setBackground(Color.BLACK);
        setLayout(null);

        JLabel adminLabel = new JLabel("Log In");
        adminLabel.setFont(new Font("Arial", Font.BOLD, 24));
        adminLabel.setForeground(Color.WHITE);
        adminLabel.setBounds(350, 80, 200, 50);
        add(adminLabel);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 18));
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(250, 160, 400, 20);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(250, 180, 400, 30);
        add(emailField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(250, 240, 400, 20);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(250, 260, 400, 30);
        add(passwordField);

        JButton loginFormButton = new JButton("Log In");
        loginFormButton.setBackground(new Color(255, 102, 0));
        loginFormButton.setForeground(Color.WHITE);
        loginFormButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginFormButton.setFocusPainted(false);
        loginFormButton.setBounds(250, 360, 400, 40);
        add(loginFormButton);

        JLabel registerLabel = new JLabel("Don't have an account? Register");
        registerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        registerLabel.setForeground(Color.LIGHT_GRAY);
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setBounds(250, 420, 400, 30);
        add(registerLabel);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(250, 450, 400, 30);
        add(registerButton);
        registerButton.addActionListener(e -> {
            mainFrame.setPanel(mainFrame.registerPanel);
        });

        loginFormButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(LoginPanel.this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            User loggedInUser = User.login(email, password);
            if (loggedInUser != null) {
                JOptionPane.showMessageDialog(LoginPanel.this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                mainFrame.gotoHomePanels(loggedInUser);
            } else {
                JOptionPane.showMessageDialog(LoginPanel.this, "Invalid email or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}