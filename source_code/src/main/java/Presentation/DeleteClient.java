package Presentation;
import DataAccess.ClientDAO;
import Model.Clients;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Clasa DeleteClient reprezintă o fereastră de ștergere a unui client.
 */


public class DeleteClient extends JFrame {

    private JFrame f = new JFrame();
    private JLabel l1;
    private JTextField t1;
    private JButton b1, b2;
    private JComboBox c1;

    public DeleteClient() {

        b1 = new JButton("Delete Client");
        b1.setBounds(250, 300, 160, 30);
        b1.setFont(new Font("serif", Font.BOLD, 15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        f.add(b1);

        l1 = new JLabel("Id Client:");
        l1.setBounds(80, 100, 160, 30);
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
        DeleteClient newClient = new DeleteClient();
    }


    public void openFrame() {
        f.setTitle("Delete Client");
        f.setBackground(Color.white);
        f.setLayout(null);
        f.getContentPane().setBackground(Color.WHITE);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setSize(450, 450);
        f.setLocation(600, 200);
        updateCombo();
    }

    public JComboBox getC1() {
        return c1;
    }

    public void setC1(JComboBox c1) {
        this.c1 = c1;
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

        inputs.add(c1.getSelectedItem().toString()); //id client
        return inputs;
    }
    public void updateCombo(){
       // JComboBox comboBox=new JComboBox<>();
        ClientDAO clientDAO = new ClientDAO();
        List<Clients> idClienti = clientDAO.findAll();

        for(Clients c : idClienti){
            this.c1.addItem(String.valueOf(c.getId()));
        }

    }
}