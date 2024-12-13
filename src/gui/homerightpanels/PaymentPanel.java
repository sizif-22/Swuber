package gui.homerightpanels;

import javax.swing.*;
import java.awt.*;

public class PaymentPanel extends JPanel{
    public PaymentPanel(){

        setBounds(300, 0, 900, 800);
        setBackground(Color.BLACK);
        setLayout(null);
        // H1 Label
        JLabel h1 = new JLabel("Choose a Payment Method");
        h1.setFont(new Font("Arial", Font.BOLD, 50));
        h1.setForeground(Color.WHITE);
        h1.setBounds(50, 20, 900, 70);
        add(h1);

        add(new PaymentCard("Sherif", "skhfowf33r32"));
        add(new PaymentCard("Sherif", "skhfowf33r32"));

        int numOfCards = PaymentCard.getCardsCounter(); //each card's height is 150.
        int height = 120 + numOfCards*150 + 10*(numOfCards - 1);
        
        JButton addNewBtn = new JButton("+ Add New");
        addNewBtn.setBounds(50 , height + 30 , 300 , 70 );
        addNewBtn.setFocusPainted(false);
        addNewBtn.setFont(new Font("Arial", Font.BOLD, 20));
        addNewBtn.setForeground(Color.white);
        addNewBtn.setHorizontalAlignment(JButton.LEFT);
        addNewBtn.setBorder(null);
        addNewBtn.setContentAreaFilled(false);
        add(addNewBtn);

        JLabel orLabel = new JLabel("Or");
        orLabel.setBounds(50,height + 120 ,200 , 70 );
        orLabel.setFont(new Font("Arial", Font.BOLD, 20));
        orLabel.setForeground(Color.white);
        add(orLabel);

        JButton cashBtn = new JButton("+ Add New");
        cashBtn.setBounds(50 , height + 210 , 150 , 70 );
        cashBtn.setFocusPainted(false);
        cashBtn.setFont(new Font("Arial", Font.BOLD, 20));
        cashBtn.setBackground(Color.green);
        cashBtn.setForeground(Color.white);
        cashBtn.setBorder(null);
        add(cashBtn);
        
    }
}
