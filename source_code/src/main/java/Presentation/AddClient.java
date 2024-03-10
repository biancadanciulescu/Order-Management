package Presentation;
import com.mysql.cj.xdevapi.Client;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Clasa AddClient reprezintă o fereastră de adăugare a unui client.
 */

public class AddClient extends JFrame{

    private JFrame f = new JFrame();
    private JLabel l1, l2, l3;
    private JTextField t1, t2, t3;
    private JButton b1, b2, b3;

    public AddClient(){

       /* b1 = new JButton("Add Client");
        b1.setBounds(250,300,160,30);
        b1.setFont(new Font("serif",Font.BOLD,15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        f.add(b1);

        b2 = new JButton("Update Client");
        b2.setBounds(30,300,160,30);
        b2.setFont(new Font("serif",Font.BOLD,15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        f.add(b2);


       l1 = new JLabel("Name:");
       l1.setBounds(80,100,160,30);
       l1.setFont(new Font("serif",Font.BOLD,20));
       f.add(l1);

       t1 =  new JTextField(20);
       t1.setBounds(180,100,160,30);
       f.add(t1);

       l2 = new JLabel("Surname:");
       l2.setBounds(80,150,160,30);
       l2.setFont(new Font("serif",Font.BOLD,20));
       f.add(l2);

       t2 = new JTextField(20);
       t2.setBounds(180,150,160,30);
       f.add(t2);

        b3 = new JButton("Back");
        b3.setBounds(140,350,160,30);
        b3.setFont(new Font("serif",Font.BOLD,15));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        f.add(b3);*/

        b1 = new JButton("Add Client");
        b1.setBounds(250, 300, 160, 30);
        b1.setFont(new Font("serif", Font.BOLD, 15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        f.add(b1);

        b2 = new JButton("Back");
        b2.setBounds(30, 300, 160, 30);
        b2.setFont(new Font("serif", Font.BOLD, 15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        f.add(b2);


        l1 = new JLabel("Id:");
        l1.setBounds(80, 100, 160, 30);
        l1.setFont(new Font("serif", Font.BOLD, 20));
        f.add(l1);

        t1 = new JTextField(20);
        t1.setBounds(180, 100, 160, 30);
        f.add(t1);

        l2 = new JLabel("Name:");
        l2.setBounds(80, 150, 160, 30);
        l2.setFont(new Font("serif", Font.BOLD, 20));
        f.add(l2);

        t2 = new JTextField(20);
        t2.setBounds(180, 150, 160, 30);
        f.add(t2);

        l3 = new JLabel("Surname:");
        l3.setBounds(80, 200, 160, 30);
        l3.setFont(new Font("serif", Font.BOLD, 20));
        f.add(l3);

        t3 = new JTextField(20);
        t3.setBounds(180, 200, 160, 30);
        f.add(t3);

/*        b3 = new JButton("Back");
        b3.setBounds(140,350,160,30);
        b3.setFont(new Font("serif",Font.BOLD,15));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        f.add(b3);*/

    }

    /*public void addActionListeners(ActionListener actionListener, ActionListener backListener){

        b1.addActionListener(actionListener);
        b2.addActionListener(actionListener);
        b3.addActionListener(backListener);
    }*/

    public void addActionListeners(ActionListener actionListener, ActionListener backListener){

        b1.addActionListener(actionListener);
        b2.addActionListener(backListener);
        //b3.addActionListener(backListener);
    }

    public static void main(String[] arg){
        AddClient newClient = new AddClient();
        newClient.openFrame();
    }

    public void openFrame(){
        f.setTitle("Add Client");
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

    public JButton getB1() {
        return b1;
    }

    public JButton getB2() {
        return b2;
    }

    public JButton getB3() {
        return b3;
    }

    public ArrayList<String> getInput() {

        ArrayList<String> inputs = new ArrayList<>();
        if(!t1.getText().isEmpty() && !t2.getText().isEmpty() && !t3.getText().isEmpty()) {
            if (t1.getText().matches("\\d+")) {
                inputs.add(t1.getText()); //id
                inputs.add(t2.getText()); //nume
                inputs.add(t3.getText()); //prenume
            }
        }
        return inputs;
    }
}


