package BusinessLogic;

import DataAccess.ClientDAO;
import DataAccess.ProductDAO;
import Model.Clients;
import Model.Products;
import Presentation.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Clasa de nivel de logica a afacerii pentru gestionarea produselor.
 */

public class ProductBLL {

    private ProductDAO productDAO;
    private Controller controller;


    /**
     * Construiește o nouă instanță a clasei ProductBLL.
     *
     * @param productDAO   obiectul de acces la date pentru produse
     * @param controller   controlerul pentru gestionarea nivelului de prezentare
     */
    public ProductBLL(ProductDAO productDAO, Controller controller) {

        this.productDAO = productDAO;
        this.controller = controller;

        controller.getAddProduct().addActionListeners(new AddProductListener(), new BackListener());
        controller.getUpdateProduct().addActionListeners(new UpdateProductListener(), new BackListener());
        controller.getDeleteProduct().addActionListeners(new DeleteProductListener(), new BackListener());
    }

    /**
     * Implementare ActionListener pentru adăugarea de produse.
     */

    public class AddProductListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(controller.getAddProduct().getB1())) {

                ArrayList<String> inputs = controller.getAddProduct().getInput();

                if (!inputs.isEmpty()) {

                    Products product = new Products(Integer.parseInt(inputs.get(0)), inputs.get(1), Integer.parseInt(inputs.get(2)), Float.parseFloat(inputs.get(3)));

                    Products oldProduct = productDAO.findById(product.getId());
                    if (oldProduct != null) {
                        JOptionPane.showMessageDialog(null, "This product already exists", "Try Again", JOptionPane.ERROR_MESSAGE);
                    } else {
                        productDAO.insert(product);
                        JOptionPane.showMessageDialog(null, "The product has been added");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Add valid inputs.");
                }
            }
        }
    }

    /**
     * Implementare ActionListener pentru actualizarea de produse.
     */

    public class UpdateProductListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(controller.getUpdateProduct().getB1())) {

                ArrayList<String> inputs = controller.getUpdateProduct().getInput();

                if (!inputs.isEmpty()) {

                    Products product = new Products(Integer.parseInt(inputs.get(0)), inputs.get(1), Integer.parseInt(inputs.get(2)), Float.parseFloat(inputs.get(3)));

                    Products oldProduct = productDAO.findById(product.getId());
                    if (oldProduct == null) {
                        JOptionPane.showMessageDialog(null, "This product doesn't exists", "Try Again", JOptionPane.ERROR_MESSAGE);
                    } else {
                        productDAO.update(product);
                        JOptionPane.showMessageDialog(null, "The product has been updated");
                    }
                }
                else {
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

            if (e.getSource().equals(controller.getAddProduct().getB2())) {
                controller.getAddProduct().closeFrame();
            }

            if (e.getSource().equals(controller.getDeleteProduct().getB2())) {
                controller.getDeleteProduct().closeFrame();
            }

            if (e.getSource().equals(controller.getUpdateProduct().getB2())) {
                controller.getUpdateProduct().closeFrame();
            }
        }
    }

    /**
     * Implementare ActionListener pentru stergerea de produse.
     */
    public class DeleteProductListener implements ActionListener {
        private DeleteProduct deleteProduct;

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(controller.getDeleteProduct().getB1())) {

                ArrayList<String> inputs = controller.getDeleteProduct().getInput();

                if (!inputs.isEmpty()) {

                    Products product = productDAO.findById(Integer.parseInt(inputs.get(0)));

                    if (product == null) {
                        JOptionPane.showMessageDialog(null, "This product doesn't exists", "Try Again", JOptionPane.ERROR_MESSAGE);
                    } else {
                        productDAO.delete(product);
                        JOptionPane.showMessageDialog(null, "The product has been deleted");
                        //deleteClient.updateCombo();
                        JComboBox c = new JComboBox<>();
                        controller.getDeleteProduct().getC1().removeItem(controller.getDeleteProduct().getC1().getSelectedItem());
                    }
                }
            }
        }
    }
}
