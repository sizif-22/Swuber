package gui;

import java.awt.Color;

import javax.swing.*;
import gui.homerightpanels.*;
import gui.loginandregisterpanels.*;

public class Frame extends JFrame{
    
    public Frame(){
        ImageIcon img = new ImageIcon(Frame.class.getResource("../swuber.jpg"));
        setIconImage(img.getImage());
        setTitle("Swuber");
        getContentPane().setBackground(Color.BLACK);

        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        
        add(new LeftPanel(false));
        add(new RegisterPanel());
        // add(new LoginPanel());
        
        // add(new BookRide());
        // add(new Shuttle());
        // add(new PaymentPanel());
        // add(new RideCompletedPanel("(His Name)"));
        // add(new PreviousRides());
        // add(new HomeLeftPanel());

        setVisible(true);
    }
}
