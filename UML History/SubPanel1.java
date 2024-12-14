package gui;

import javax.swing.*;
import java.awt.*;

public class SubPanel1 extends JPanel {
    static int counter =0;
    SubPanel1(String text){
        setLayout(null);
        setBackground(Color.black);
        setBounds(0,400+counter,999,50);
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        // p1.setLayout(null);
        p1.setBounds(200,0,200,100);
        
        p1.setBackground(Color.BLACK);
        p2.setBackground(Color.BLACK);
        p2.setBounds(500,0,200,100);
        JLabel lbl = new JLabel(text);
        lbl.setBounds(0,0,400,200);
        lbl.setForeground(Color.white);
        JTextField tf = new JTextField(10);
        
        p1.add(lbl);
        p2.add(tf);
        
        add(p1);
        add(p2);
        counter+=50;
    }
}
