package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import functionality.*;

public class HomeLeftPanel extends JPanel {

    private Frame mainFrame;
    private User currentUser;

    public HomeLeftPanel(Frame frame, User user) {
        setBounds(0, 0, 300, 800);
        setBackground(new Color(33, 33, 33));
        setLayout(null); // Consider using a layout manager for better maintainability

        this.mainFrame = frame;
        this.currentUser = user;

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Swuber");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        welcomeLabel.setForeground(new Color(0xff8519));
        welcomeLabel.setBounds(30, 0, 300, 200);
        add(welcomeLabel);
        welcomeLabel.setVerticalAlignment(JLabel.CENTER);

        // Configure buttons
        configureButton(createButton("Book a Ride", 200), e -> mainFrame.gotoBookRidePanel(currentUser));
        configureButton(createButton("Swuber Shuttle", 270), e -> mainFrame.gotoSwuberShuttlePanel(currentUser)); // New method in Frame
        configureButton(createButton("Ride History", 340), e -> mainFrame.gotoRideHistoryPanel(currentUser)); // New method in Frame

        // User Info
        ImageIcon originalUserIcon = new ImageIcon(HomeLeftPanel.class.getResource("../user.png"));
        Image originalUserImage = originalUserIcon.getImage();
        Image resizedUserImage = originalUserImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon userIcon = new ImageIcon(resizedUserImage);

        JLabel footer = new JLabel(currentUser.getName());
        footer.setFont(new Font("Arial", Font.PLAIN, 20));
        footer.setForeground(Color.white);
        footer.setBackground(new Color(11, 11, 11));
        footer.setOpaque(true);
        footer.setIcon(userIcon);
        footer.setIconTextGap(15); 
        footer.setHorizontalTextPosition(JLabel.RIGHT); 
        footer.setHorizontalAlignment(SwingConstants.LEFT);
        footer.setBounds(0, 680, 300, 80);
        add(footer);

        // Exit Button
        ImageIcon originalExitIcon = new ImageIcon(HomeLeftPanel.class.getResource("../Exit-icon.png"));
        Image originalExitImage = originalExitIcon.getImage();
        Image resizedExitImage = originalExitImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon ExitIcon = new ImageIcon(resizedExitImage);

        JButton exitBtn = new JButton();
        exitBtn.setIcon(ExitIcon);
        exitBtn.setFocusPainted(false);
        exitBtn.setBorderPainted(false);
        exitBtn.setBounds(250, 680, 50, 80);
        exitBtn.setBackground(new Color(11, 11, 11));
        add(exitBtn);

        exitBtn.addActionListener(e -> System.exit(0)); // Close the application
    }

    private void configureButton(JButton button, ActionListener listener) {
        button.setContentAreaFilled(false);
        button.setForeground(Color.WHITE);
        button.setBorder(null);
        button.setFont(new Font("Arial", Font.PLAIN, 30));
        button.setFocusPainted(false);
        button.setHorizontalAlignment(JButton.LEFT);
        button.addActionListener(listener);
        add(button);
    }

    private JButton createButton(String text, int y) {
        JButton button = new JButton(text);
        button.setBounds(30, y, 230, 60);
        return button;
    }
}