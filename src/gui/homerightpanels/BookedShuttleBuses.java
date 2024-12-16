package gui.homerightpanels;

import java.awt.*;
import javax.swing.*;
import functionality.*;
import gui.Frame;

public class BookedShuttleBuses extends JPanel {


  public BookedShuttleBuses() {

    setBounds(300, 0, 900, 800);
    setBackground(new Color(55,55,55));

    setLayout(null);

    JLabel h1 = new JLabel("My Shuttle Bookings");
    h1.setFont(new Font("Arial", Font.BOLD, 50));
    h1.setForeground(Color.WHITE);
    h1.setBounds(20, 40, 900, 80);
    add(h1);



    JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null); 
        contentPanel.setBackground(new Color(33,33,33));
        contentPanel.setBorder(null);
        // Add Cards here
        int counter = 0;
        
        contentPanel.add(new BookedShuttleCard(counter++,"qq","aa",23.0f));
        contentPanel.add(new BookedShuttleCard(counter++,"rr","aa",27.0f));
        contentPanel.add(new BookedShuttleCard(counter++,"ff","aa",25.0f));
        contentPanel.add(new BookedShuttleCard(counter++,"gg","aa",83.0f));
        
        
        contentPanel.setPreferredSize(new Dimension(820, (160*counter+10))); 
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBounds(20, 150, 840, 570);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);

        // Add the scroll pane to this panel
        add(scrollPane);


  }
}