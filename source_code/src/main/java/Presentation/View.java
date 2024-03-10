package Presentation;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class View extends JFrame{

    private JFrame f = new JFrame();
    private JLabel l1,l2;
    private JTextField t1;
    private JPasswordField t2;
    private JButton b1, b2, b3;

    /**
     * Clasa View reprezintă fereastra principală a aplicației.
     */

    public View(){

        b1 = new JButton("Client Options");
        b1.setBounds(130,100,160,30);
        b1.setFont(new Font("serif",Font.BOLD,15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        f.add(b1);


        b2=new JButton("Products Options");
        b2.setBounds(130,200,160,30);
        b2.setFont(new Font("serif",Font.BOLD,15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        f.add(b2);

        b3=new JButton("Orders");
        b3.setBounds(130,300,160,30);
        b3.setFont(new Font("serif",Font.BOLD,15));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        f.add(b3);


    }

    public void openFrame(){
        f.setTitle("Main Meniu");
        f.setBackground(Color.white);
        f.setLayout(null);
        f.getContentPane().setBackground(Color.WHITE);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(450,450);
        f.setLocation(600,200);
        f.setVisible(true);
    }

    public void closeFrame(){
        f.setVisible(false);

    }

    public void addActionListeners(ActionListener actionListener){

        b1.addActionListener(actionListener);
        b2.addActionListener(actionListener);
        b3.addActionListener(actionListener);
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
}


