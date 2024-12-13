package gui.homerightpanels;

import javax.swing.*;
import java.awt.*;

public class PreviousRides extends JPanel{
    public PreviousRides(){
        setBounds(300, 0, 900, 800);
        setBackground(Color.BLACK);
        setLayout(null);
        // H1 Label
        JLabel h1 = new JLabel("Previous Rides");
        h1.setFont(new Font("Arial", Font.BOLD, 50));
        h1.setForeground(Color.WHITE);
        h1.setBounds(50, 30, 900, 70);
        add(h1);

        add(new RidesCard("Imbaba", "Shobra", "elrahmani", "ebda3"));
        add(new RidesCard("Imbaba", "Shobra", "elrahmani", "ebda3"));
        add(new RidesCard("Imbaba", "Shobra", "elrahmani", "ebda3"));
    
    }
}
