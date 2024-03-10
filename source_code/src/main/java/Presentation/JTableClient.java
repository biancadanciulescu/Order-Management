package Presentation;

import DataAccess.ClientDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa JTableClient reprezintă o fereastră pentru afișarea tabelară a clientilor.
 */

public class JTableClient extends JFrame {

    private JFrame f = new JFrame();
    private JTable table = new JTable();
    private JButton b1;

    public JTableClient(){

        b1 = new JButton("Back");
        b1.setBounds(250, 300, 160, 30);
        b1.setFont(new Font("serif", Font.BOLD, 15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        f.add(b1);


    }


    public void openFrame() throws IllegalAccessException {
 //       f.setTitle("View All Clients");
  //      f.setBackground(Color.white);
  //      f.setLayout(null);
 //       f.getContentPane().setBackground(Color.WHITE);
        //      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ClientDAO clientDAO = new ClientDAO();
//        table=clientDAO.createTable(clientDAO.findAll());
//
//       // f.add(new JScrollPane(table));
//        f.setVisible(true);
        f.setSize(450, 450);
        f.setLocation(600, 200);

        JTable table = clientDAO.createTable(clientDAO.findAll());

        // Create a JFrame and add the JTable to it
        // JFrame frame = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new JScrollPane(table));
        //f.pack();

        f.setVisible(true);



    }


    public void closeFrame() {
        f.setVisible(false);
    }


    public void addActionListeners(ActionListener backListener) {

        this.b1.addActionListener(backListener);

    }

    public JButton getB1() {
        return b1;
    }
}