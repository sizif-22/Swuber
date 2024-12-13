package gui.homerightpanels;

import javax.swing.*;
import java.awt.*;

public class BookRideCard extends JPanel{
    static int counter = 0;
    public BookRideCard(String from , String to , int seatsAv){
        int height = 250 + 220*counter;

        setBounds(50, height, 800, 200);
        setBackground(Color.BLACK);
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.white,3));
        // setBorder(BorderFactory.createLineBorder(new Color(123,50,250),3));
        
        JLabel fromTo = new JLabel(from + " - " + to);
        fromTo.setForeground(Color.WHITE);
        fromTo.setBounds(20,20,500,50);
        fromTo.setFont(new Font("Arial",Font.PLAIN,40));
        add(fromTo);

        JLabel seatsAvailable = new JLabel(seatsAv + " Seats Available");
        seatsAvailable.setForeground(Color.WHITE);
        seatsAvailable.setBounds(20,80,500,50);
        seatsAvailable.setFont(new Font("Arial",Font.PLAIN,30));
        add(seatsAvailable);

        JButton bookBtn = new JButton("Book Ride");
        bookBtn.setBounds( 550, 60 , 200 , 80);
        bookBtn.setBackground(new Color(123, 50, 250));
        bookBtn.setFocusPainted(false);
        bookBtn.setFont(new Font("Arial", Font.BOLD, 20));
        bookBtn.setForeground(Color.white);
        bookBtn.setBorder(null);
        add(bookBtn);
        
        
        counter++;
    }
}
