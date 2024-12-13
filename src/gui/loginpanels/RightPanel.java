package gui.loginpanels;

import java.awt.*;

import javax.swing.*;

public class RightPanel extends JPanel{
    public RightPanel(){
        setBounds(300, 0, 900, 800);
        setBackground(Color.BLACK);
        setLayout(null);

        // Admin Log In Label
        JLabel adminLabel = new JLabel("Admin Log In");
        adminLabel.setFont(new Font("Arial", Font.BOLD, 24));
        adminLabel.setForeground(Color.WHITE);
        adminLabel.setBounds(350, 80, 200, 50);
        add(adminLabel);

        // Email Label
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 18));
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(250, 160, 400, 20);
        add(emailLabel);

        // Email TextField
        JTextField emailField = new JTextField();
        emailField.setBounds(250, 180, 400, 30);
        add(emailField);

        // Password Label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(250, 240, 400, 20);
        add(passwordLabel);

        // Password Field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(250, 260, 400, 30);
        add(passwordField);

        // Remember Me Checkbox
        JCheckBox rememberMe = new JCheckBox("Remember me");
        rememberMe.setFont(new Font("Arial", Font.BOLD, 12));
        rememberMe.setForeground(Color.WHITE);
        rememberMe.setBackground(Color.BLACK);
        rememberMe.setBounds(250, 300, 150, 20);
        add(rememberMe);

        // Forgot Password Label
        JLabel forgotPassword = new JLabel("Forgot password?");
        forgotPassword.setFont(new Font("Arial", Font.PLAIN, 12));
        forgotPassword.setForeground(Color.LIGHT_GRAY);
        forgotPassword.setBounds(500, 300, 150, 20);
        add(forgotPassword);
        forgotPassword.setHorizontalAlignment(JLabel.RIGHT);

        // Log In Button
        JButton loginFormButton = new JButton("Log In");
        loginFormButton.setBackground(new Color(255, 102, 0));
        loginFormButton.setForeground(Color.WHITE);
        loginFormButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginFormButton.setFocusPainted(false);
        loginFormButton.setBounds(250, 360, 400, 40);
        add(loginFormButton);

        // Register Label
        JLabel registerLabel = new JLabel("Do you have an account? Register");
        registerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        registerLabel.setForeground(Color.LIGHT_GRAY);
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setBounds(250, 420, 400, 30);
        add(registerLabel);
    }
}
