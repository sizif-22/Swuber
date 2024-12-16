package gui.homerightpanels;

import javax.swing.*;
import java.awt.*;

import functionality.Ride;
import functionality.User;

public class PreviousRides extends JPanel{
    public PreviousRides(Frame frame, User user){
        setBounds(300, 0, 900, 800);
        setBackground(new Color(55,55,55));

        setLayout(null);
     
        JLabel h1 = new JLabel("Previous Rides");
        h1.setFont(new Font("Arial", Font.BOLD, 50));
        h1.setForeground(Color.WHITE);
        h1.setBounds(50, 30, 900, 70);
        add(h1);
        int index = 0;
        for(Ride ride : user.getRideHistory().getRides()){
            add(new RidesCard(ride.getStartLocation(), ride.getEndLocation(), ride.getDriver().getName(), ride.getRating() ,index));
            index++;
        }

        
    
    }
}
