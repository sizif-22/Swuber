package gui.homerightpanels;

import javax.swing.*;
import java.awt.*;

import functionality.Ride;
import functionality.User;

public class PreviousRides extends JPanel{
    public PreviousRides(Frame frame, User user){
        setBounds(300, 0, 900, 800);
        setBackground(Color.BLACK);
        setLayout(null);
        // H1 Label
        JLabel h1 = new JLabel("Previous Rides");
        h1.setFont(new Font("Arial", Font.BOLD, 50));
        h1.setForeground(Color.WHITE);
        h1.setBounds(50, 30, 900, 70);
        add(h1);

        for(Ride ride : user.getRideHistory().getRides()){
            add(new RidesCard(ride.getStartLocation(), ride.getEndLocation(), ride.getDriver().getName(), ride.getRating()));
        }

        
    
    }
}