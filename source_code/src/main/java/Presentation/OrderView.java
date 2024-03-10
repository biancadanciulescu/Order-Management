package Presentation;

import DataAccess.ClientDAO;
import DataAccess.ProductDAO;
import Model.Clients;
import Model.Products;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.*;
import java.util.List;

/**
 * Clasa OrderView reprezintă o fereastră de vizualizare a optiunilor unei comenzi.
 */
public class OrderView extends JFrame {

    private JFrame f = new JFrame();
    private JLabel l1, l2, l3;
    private JTextField t1;
    private JButton b1, b2;
    private JComboBox c1, c2;

    ClientDAO clientDAO = new ClientDAO();
    ProductDAO productDAO = new ProductDAO();

    public OrderView(){

        b1 = new JButton("Order");
        b1.setBounds(250,300,160,30);
        b1.setFont(new Font("serif",Font.BOLD,15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        f.add(b1);

        l1 = new JLabel("Id Client:");
        l1.setBounds(80,100,160,30);
        l1.setFont(new Font("serif",Font.BOLD,20));
        f.add(l1);

        c1 = new JComboBox();
        c1.setBounds(180, 100, 160, 30);
        f.add(c1);


        l2 = new JLabel("Id Product:");
        l2.setBounds(80,150,160,30);
        l2.setFont(new Font("serif",Font.BOLD,20));
        f.add(l2);

        c2 = new JComboBox();
        c2.setBounds(180, 150, 160, 30);
        f.add(c2);


        l3 = new JLabel("Quantity:");
        l3.setBounds(80,200,160,30);
        l3.setFont(new Font("serif",Font.BOLD,20));
        f.add(l3);

        t1 =  new JTextField(20);
        t1.setBounds(180,200,160,30);
        f.add(t1);

        b2 = new JButton("Back");
        b2.setBounds(30,300,160,30);
        b2.setFont(new Font("serif",Font.BOLD,15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        f.add(b2);


        List<Clients> idClienti = clientDAO.findAll();
        List<Products> idProduse = productDAO.findAll();

        for(Clients c : idClienti){
            c1.addItem(String.valueOf(c.getId()));
        }

        for(Products p : idProduse){
            c2.addItem(String.valueOf(p.getId()));
        }

    }

    public static void main(String[] atgs){
        OrderView o = new OrderView();
        o.openFrame();
    }

    public void openFrame(){
        f.setTitle("Orders");
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
        b2.addActionListener(backListener);
    }

    public JButton getB1() {
        return b1;
    }

    public JButton getB2() {
        return b2;
    }

    public ArrayList<String> getInput() {

        ArrayList<String> inputs = new ArrayList<>();

        if(!t1.getText().isEmpty() && t1.getText().matches("\\d+")) {
        inputs.add(c1.getSelectedItem().toString()); //id cl
        inputs.add(c2.getSelectedItem().toString()); //id prod

            inputs.add(t1.getText()); //cantitate
        }

        return inputs;
    }
}
