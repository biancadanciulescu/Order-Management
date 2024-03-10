package Presentation;
import DataAccess.ProductDAO;
import Model.Products;
import com.mysql.cj.xdevapi.Client;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Clasa DeleteProduct reprezintă o fereastră de ștergere a unui produs.
 */
public class DeleteProduct extends JFrame {

    private JFrame f = new JFrame();
    private JLabel l1;
    private JTextField t1;
    private JButton b1, b2;
    private JComboBox c1;

    public DeleteProduct() {

        b1 = new JButton("Delete Product");
        b1.setBounds(250, 300, 160, 30);
        b1.setFont(new Font("serif", Font.BOLD, 15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        f.add(b1);

        l1 = new JLabel("Id Product:");
        l1.setBounds(70, 100, 160, 30);
        l1.setFont(new Font("serif", Font.BOLD, 20));
        f.add(l1);

        c1 = new JComboBox();
        c1.setBounds(180, 100, 160, 30);
        f.add(c1);

        b2 = new JButton("Back");
        b2.setBounds(30, 300, 160, 30);
        b2.setFont(new Font("serif", Font.BOLD, 15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        f.add(b2);

    }

    public void addActionListeners(ActionListener actionListener, ActionListener backListener) {

        b1.addActionListener(actionListener);
        b2.addActionListener(backListener);
    }

    public static void main(String[] arg) {
        DeleteProduct newProduct = new DeleteProduct();
    }

    public void openFrame() {
        f.setTitle("Delete Product");
        f.setBackground(Color.white);
        f.setLayout(null);
        f.getContentPane().setBackground(Color.WHITE);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setSize(450, 450);
        f.setLocation(600, 200);
        updateCombo();

    }

    public void closeFrame() {
        f.setVisible(false);
    }


    public JButton getB1() {
        return b1;
    }

    public JButton getB2() {
        return b2;
    }

    public ArrayList<String> getInput() {

        ArrayList<String> inputs = new ArrayList<>();

        inputs.add(c1.getSelectedItem().toString()); //id produs
        return inputs;
    }

    public JComboBox getC1() {
        return c1;
    }

    public void updateCombo() {

        ProductDAO productDAO = new ProductDAO();
        List<Products> idProduse = productDAO.findAll();

        for (Products c : idProduse) {
            this.c1.addItem(String.valueOf(c.getId()));
        }

    }
}


