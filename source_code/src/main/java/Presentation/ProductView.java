package Presentation;
import com.mysql.cj.xdevapi.Client;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

/**
 * Clasa ProductView reprezintă o fereastră de vizualizare a optiunilor unui produs.
 */
public class ProductView extends JFrame{

    private JFrame f = new JFrame();
    private JButton b1, b2, b3, b4, b5;

    public ProductView(){

        b1 = new JButton("Add Product");
        b1.setBounds(130,40,160,30);
        b1.setFont(new Font("serif",Font.BOLD,15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        f.add(b1);

        b2=new JButton("Edit Product");
        b2.setBounds(130, 110,160,30);
        b2.setFont(new Font("serif",Font.BOLD,15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        f.add(b2);

        b3=new JButton("Delete Product");
        b3.setBounds(130,180,160,30);
        b3.setFont(new Font("serif",Font.BOLD,15));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        f.add(b3);

        b4 = new JButton("View All Products");
        b4.setBounds(130,250,160,30);
        b4.setFont(new Font("serif",Font.BOLD,15));
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.WHITE);
        f.add(b4);

        b5 = new JButton("Back");
        b5.setBounds(130,320,160,30);
        b5.setFont(new Font("serif",Font.BOLD,15));
        b5.setBackground(Color.BLACK);
        b5.setForeground(Color.WHITE);
        f.add(b5);
    }

    public void openFrame(){
        f.setTitle("Product Options");
        f.setBackground(Color.white);
        f.setLayout(null);
        f.getContentPane().setBackground(Color.WHITE);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setSize(450,450);
        f.setLocation(600,200);

    }

    public void closeFrame(){
        f.setVisible(false);
    }


    public void addActionListeners(ActionListener actionListener, ActionListener backListener){

        b1.addActionListener(actionListener);
        b2.addActionListener(actionListener);
        b3.addActionListener(actionListener);
        b4.addActionListener(actionListener);
        b5.addActionListener(backListener);
    }

    public JButton getB1() {
        return b1;
    }

    public JButton getB2() {
        return b2;
    }

    public JButton getB3() {
        return b3;
    }

    public JButton getB4() {
        return b4;
    }

    public JButton getB5() {
        return b5;
    }
}


