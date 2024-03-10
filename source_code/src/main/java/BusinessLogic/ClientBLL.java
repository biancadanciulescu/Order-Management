package BusinessLogic;

import DataAccess.ClientDAO;
import Model.Clients;
import Model.Products;
import Presentation.Controller;
import Presentation.DeleteClient;
import Presentation.UpdateClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Clasa de nivel de logica a afacerii pentru gestionarea clienților.
 */
public class ClientBLL {

    private ClientDAO clientDAO;
    private Controller controller;


    /**
     * Construiește o nouă instanță a clasei ClientBLL.

     */
    public ClientBLL(ClientDAO clientDAO, Controller controller) {

        this.clientDAO = clientDAO;
        this.controller = controller;

        controller.getAddClient().addActionListeners(new AddClientListener(), new BackListener());
        controller.getUpdateClient().addActionListeners(new UpdateClientListener(), new BackListener());
        controller.getDeleteClient().addActionListeners(new DeleteClientListener(), new BackListener());
    }

    /**
     * Implementare ActionListener pentru adăugarea unui client.
     */
    public class AddClientListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(controller.getAddClient().getB1())) {
                // controller.getAddClient().closeFrame();

                ArrayList<String> inputs = controller.getAddClient().getInput();
                /*System.out.println(inputs.get(0));
                System.out.println(inputs.get(1));
                System.out.println(inputs.get(2));*/



                if (!inputs.isEmpty()) {
                    //System.out.println(inputs);
                    //System.out.println("1");
                    // if(inputs.get(0) != null && inputs.get(1) != null && inputs.get(2) != null){

                    Clients client = new Clients(Integer.parseInt(inputs.get(0)), inputs.get(1), inputs.get(2));

                    Clients oldClient = clientDAO.findById(client.getId());
                    if (oldClient != null) {
                        JOptionPane.showMessageDialog(null, "This client already exists", "Try Again", JOptionPane.ERROR_MESSAGE);
                    } else {
                        clientDAO.insert(client);
                        JOptionPane.showMessageDialog(null, "The client has been added");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Add valid inputs.");
                }
            }
        }
    }

    /**
     * Implementare ActionListener pentru actualizarea unui client.
     */

    public class UpdateClientListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(controller.getUpdateClient().getB1())) {

                ArrayList<String> inputs = controller.getUpdateClient().getInput();

                if (!inputs.isEmpty()) {

                    Clients client = new Clients(Integer.parseInt(inputs.get(0)), inputs.get(1), inputs.get(2));

                    Clients oldClient = clientDAO.findById(client.getId());
                    if (oldClient == null) {
                        JOptionPane.showMessageDialog(null, "This client doesn't exists", "Try Again", JOptionPane.ERROR_MESSAGE);
                    } else {
                        clientDAO.update(client);
                        JOptionPane.showMessageDialog(null, "The client has been updated");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Add valid inputs.");
                }
            }
        }
    }

    /**
     * Implementare ActionListener pentru butoanele de back.
     */

        public class BackListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource().equals(controller.getAddClient().getB2())) {
                    controller.getAddClient().closeFrame();
                }

                if (e.getSource().equals(controller.getDeleteClient().getB2())) {
                    controller.getDeleteClient().closeFrame();
                }

                if (e.getSource().equals(controller.getUpdateClient().getB2())) {
                    controller.getUpdateClient().closeFrame();
                }
            }
        }

    /**
     * Implementare ActionListener pentru stergerea unui client.
     */

        public class DeleteClientListener implements ActionListener {
            private DeleteClient deleteClient;

            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource().equals(controller.getDeleteClient().getB1())) {

                    ArrayList<String> inputs = controller.getDeleteClient().getInput();

                    if (!inputs.isEmpty()) {

                        Clients client = clientDAO.findById(Integer.parseInt(inputs.get(0)));

                        if (client == null) {
                            JOptionPane.showMessageDialog(null, "This client doesn't exists", "Try Again", JOptionPane.ERROR_MESSAGE);
                        } else {
                            clientDAO.delete(client);
                            JOptionPane.showMessageDialog(null, "The client has been deleted");
                            //deleteClient.updateCombo();
                            JComboBox c = new JComboBox<>();
                            controller.getDeleteClient().getC1().removeItem(controller.getDeleteClient().getC1().getSelectedItem());

                        }
                    }
                }
            }
        }
    }
