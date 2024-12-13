package gui.homerightpanels;

import java.awt.*;
import javax.swing.*;

public class Shuttle extends JPanel {
    public Shuttle(){
        
        setBounds(300, 0, 900, 800);
        setBackground(Color.BLACK);
        setLayout(null);
        // H1 Label
        JLabel h1 = new JLabel("Swuber Shuttle");
        h1.setFont(new Font("Arial", Font.BOLD, 50));
        h1.setForeground(Color.WHITE);
        h1.setBounds(50, 100, 900, 40);
        add(h1);
        
        JLabel h2 = new JLabel("Available Routes");
        h2.setFont(new Font("Arial", Font.PLAIN, 35));
        h2.setForeground(Color.WHITE);
        h2.setBounds(50,160, 900, 40);
        add(h2);
        
        add(new BookRideCard("Cairo","Giza", 10));
        add(new BookRideCard("Alexandria","Cairo",15));
        

    }
}
