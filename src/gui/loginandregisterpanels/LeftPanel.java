package gui.loginandregisterpanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import gui.Frame;

public class LeftPanel extends JPanel implements ActionListener {

    private Frame mainFrame;

    public LeftPanel(Frame frame) {
        this.mainFrame = frame;

        setBackground(new Color(33, 33, 33));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add top spacing
        add(Box.createRigidArea(new Dimension(0, 50))); // Adjust the vertical space here

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setOpaque(true);
        welcomeLabel.setBackground(new Color(55, 55, 55));
        welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
        welcomeLabel.setPreferredSize(new Dimension(300, 80));
        add(welcomeLabel);

        // Add spacing between title and buttons
        add(Box.createRigidArea(new Dimension(0, 50))); // Adjust vertical space here

        // Log In Button
        JButton loginButton = new JButton("Log In");
        styleButton(loginButton);
        loginButton.addActionListener(this);
        add(loginButton);

        // Add spacing between buttons
        add(Box.createRigidArea(new Dimension(0, 10))); // Adjust vertical space here

        // Register Button
        JButton registerButton = new JButton("Register");
        styleButton(registerButton);
        registerButton.addActionListener(this);
        add(registerButton);

        setPreferredSize(new Dimension(300, 800));
    }

    private void styleButton(JButton button) {
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(null);
        button.setBackground(new Color(0, 122, 255));
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 40));
        button.setMinimumSize(new Dimension(200, 40));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton.getText().equals("Register")) {
            mainFrame.setPanel(new RegisterPanel(mainFrame));
        } else if (clickedButton.getText().equals("Log In")) {
            mainFrame.setPanel(new LoginPanel(mainFrame));
        }
    }
}