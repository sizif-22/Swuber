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



        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null); 
        contentPanel.setBackground(new Color(33,33,33));
        contentPanel.setBorder(null);
        // Add Cards here
        int counter = 0;
        for(Ride ride : user.getRideHistory().getRides()){
            contentPanel.add(new RidesCard(ride.getStartLocation(), ride.getEndLocation(), ride.getDriver().getName(), ride.getRating() ,counter++));
        }
        
        
        contentPanel.setPreferredSize(new Dimension(820, (165*counter+10))); 
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBounds(20, 170, 840, 550);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);

        // Add the scroll pane to this panel
        add(scrollPane);

        
    
    }
}
