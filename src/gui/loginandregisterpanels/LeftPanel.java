package gui.loginandregisterpanels;

import java.awt.*;

import javax.swing.*;

public class LeftPanel extends JPanel {

    public LeftPanel(boolean login){
            setBounds(0, 0, 300, 800); // Fixed width for left panel
            setBackground(new Color(33, 33, 33));
            setLayout(null);
            // Welcome Label
            JLabel welcomeLabel = new JLabel("Welcome");
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
            welcomeLabel.setForeground(Color.WHITE);
            welcomeLabel.setBounds(0, 0, 300, 80); // Positioned at the top
            add(welcomeLabel);
            welcomeLabel.setVerticalAlignment(JLabel.CENTER);
            welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
            welcomeLabel.setBackground(new Color(55,55,55));
            welcomeLabel.setOpaque(true);
    
            // Log In Button
            JButton loginButton = new JButton("Log In");
            loginButton.setForeground(Color.WHITE);
            loginButton.setFont(new Font("Arial", Font.BOLD, 16));
            loginButton.setFocusPainted(false);
            loginButton.setBounds(50, 200, 200, 40);
            loginButton.setBorder(null);
            add(loginButton);

            // Register Button
            JButton registerButton = new JButton("Register");
            registerButton.setForeground(Color.WHITE);
            registerButton.setFont(new Font("Arial", Font.BOLD, 16));
            registerButton.setFocusPainted(false);
            registerButton.setBounds(50, 270, 200, 40);
            registerButton.setBorder(null);
            add(registerButton);
            
            
            if(login){
                loginButton.setBackground(new Color(0, 122, 255));
                registerButton.setBackground(new Color(33, 33, 33));
            } else {
                registerButton.setBackground(new Color(0, 122, 255));
                loginButton.setBackground(new Color(33, 33, 33));
            }
    }
}
