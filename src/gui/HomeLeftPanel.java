package gui;

import java.awt.*;

import javax.swing.*;

public class HomeLeftPanel extends JPanel{
    public HomeLeftPanel(){
        setBounds(0, 0, 300, 800); // Fixed width for left panel
        setBackground(new Color(33, 33, 33));
        setLayout(null);
        // Welcome Label
        JLabel welcomeLabel = new JLabel("Swuber");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        welcomeLabel.setForeground(new Color(0xff8519));
        welcomeLabel.setBounds(30, 0, 300, 200); // Positioned at the top
        add(welcomeLabel);
        welcomeLabel.setVerticalAlignment(JLabel.CENTER);



        // Book a Ride Button
        JButton bookRide = new JButton("Book a Ride");
        // bookRide.setBackground(new Color(33, 33, 33));
        bookRide.setContentAreaFilled(false);
        bookRide.setForeground(Color.WHITE);
        bookRide.setBorder(null);
        bookRide.setFont(new Font("Arial", Font.PLAIN, 30));
        bookRide.setFocusPainted(false);
        bookRide.setHorizontalAlignment(JButton.LEFT);
        bookRide.setBounds(30, 200, 230, 60);
        add(bookRide);
        
        // Swuber Shuttle Button
        JButton swuberShuttle = new JButton("Swuber Shuttle");
        // swuberShuttle.setBackground(new Color(33, 33, 33));
        swuberShuttle.setContentAreaFilled(false);
        swuberShuttle.setForeground(Color.WHITE);
        swuberShuttle.setBorder(null);
        swuberShuttle.setFont(new Font("Arial", Font.PLAIN, 30));
        swuberShuttle.setFocusPainted(false);
        swuberShuttle.setHorizontalAlignment(JButton.LEFT);
        swuberShuttle.setBounds(30, 270, 230, 60);
        add(swuberShuttle);

        // Ride History Button
        JButton rideHistory = new JButton("Ride History");
        // rideHistory.setBackground(new Color(33, 33, 33));
        rideHistory.setContentAreaFilled(false);
        rideHistory.setForeground(Color.WHITE);
        rideHistory.setBorder(null);
        rideHistory.setFont(new Font("Arial", Font.PLAIN, 30));
        rideHistory.setFocusPainted(false);
        rideHistory.setHorizontalAlignment(JButton.LEFT);
        rideHistory.setBounds(30, 340, 230, 60);
        add(rideHistory);


        ImageIcon originalUserIcon = new ImageIcon(HomeLeftPanel.class.getResource("../user.png"));
        // Resize the image
        Image originalUserImage = originalUserIcon.getImage();
        Image resizedUserImage = originalUserImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Width and height set to 100 pixels
        
        // Create a new ImageIcon with the resized image
        ImageIcon userIcon = new ImageIcon(resizedUserImage);

        JLabel footer = new JLabel("username");
        footer.setFont(new Font("Arial", Font.PLAIN, 20));
        footer.setForeground(Color.white);
        footer.setBackground(new Color(11,11,11));
        footer.setOpaque(true);
        footer.setIconTextGap(10);
        footer.setBounds(0,680,250,70);
        footer.setIcon(userIcon);
        add(footer);

        ImageIcon originalExitIcon = new ImageIcon(HomeLeftPanel.class.getResource("../Exit-icon.png"));
        // Resize the image
        Image originalExitImage = originalExitIcon.getImage();
        Image resizedExitImage = originalExitImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Width and height set to 100 pixels
        
        // Create a new ImageIcon with the resized image
        ImageIcon ExitIcon = new ImageIcon(resizedExitImage);

        JButton exitBtn = new JButton();
        exitBtn.setIcon(ExitIcon);
        exitBtn.setFocusPainted(false); // Remove focus outline
        exitBtn.setBorderPainted(false); // Remove button border
        // exitBtn.setContentAreaFilled(false); // Make background transparent
        exitBtn.setBounds(250,680,50,70);
        exitBtn.setBackground(new Color(11,11,11));
        add(exitBtn);
    }
}
