package Presentation;

import BusinessLogic.ClientBLL;
import Model.Products;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller {

    private final AddClient addClient;
    private final AddProduct addProduct;
    private final ClientView clientView;
    private final DeleteClient deleteClient;
    private final DeleteProduct deleteProduct;
    private final OrderView orderView;
    private final ProductView productView;
    private final View view;
    private final JTableClient jTableClient;
    private final JTableProduct jTableProduct;
    private final UpdateClient updateClient;
    private final UpdateProduct updateProduct;


    public Controller(AddClient addClient, AddProduct addProduct, ClientView clientView, DeleteClient deleteClient, DeleteProduct deleteProduct, OrderView orderView, ProductView productView, View view, JTableClient jTableClient, JTableProduct jTableProduct, UpdateClient updateClient, UpdateProduct updateProduct) {
        this.addClient = addClient;
        this.addProduct = addProduct;
        this.clientView = clientView;
        this.deleteClient = deleteClient;
        this.deleteProduct = deleteProduct;
        this.orderView = orderView;
        this.productView = productView;
        this.view = view;
        this.jTableClient = jTableClient;
        this.jTableProduct = jTableProduct;
        this.updateClient = updateClient;
        this.updateProduct = updateProduct;

        this.view.addActionListeners(new Listener());
        this.clientView.addActionListeners(new ButtonListener(), new BackListener());
        this.productView.addActionListeners(new ButtonListener(), new BackListener());
       // this.orderView.addActionListeners(new ButtonListener(), new BackListener());


       // this.addClient.addActionListeners(new ButtonListener(), new BackListener());
      //  this.addProduct.addActionListeners(new ButtonListener(), new BackListener());
       // this.deleteClient.addActionListeners(new ButtonListener(), new BackListener());
       // this.deleteProduct.addActionListeners(new BackListener(), new BackListener());

        this.view.openFrame();


        this.jTableClient.addActionListeners(new BackListener());
        this.jTableProduct.addActionListeners(new BackListener());


        //this.addClient.addActionListeners(new ClientBLL.AddClientListener(), new BackListener());
    }

    public AddClient getAddClient() {
        return addClient;
    }

    public AddProduct getAddProduct() {
        return addProduct;
    }

    public DeleteClient getDeleteClient() {
        return deleteClient;
    }

    public DeleteProduct getDeleteProduct() {
        return deleteProduct;
    }

    public OrderView getOrderView() {
        return orderView;
    }

    public ProductView getProductView() {
        return productView;
    }

    public View getView() {
        return view;
    }

    public ClientView getClientView() {
        return clientView;
    }

    public UpdateClient getUpdateClient() {
        return updateClient;
    }

    public UpdateProduct getUpdateProduct(){ return updateProduct; }

    class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(view.getB1())){

                view.closeFrame();
                clientView.openFrame();

            }else if(e.getSource().equals(view.getB2())){

                view.closeFrame();
                productView.openFrame();

            }else if(e.getSource().equals(view.getB3())){

                view.closeFrame();
                orderView.openFrame();

            }
        }
    }

   public class BackListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(clientView.getB5())) {

                clientView.closeFrame();
                view.openFrame();

            }else if(e.getSource().equals(productView.getB5())){

                productView.closeFrame();
                view.openFrame();

            }else if(e.getSource().equals(orderView.getB2())){

                orderView.closeFrame();
                view.openFrame();
            }

            if (e.getSource().equals(deleteClient.getB2())) {

                deleteClient.closeFrame();

            }else if(e.getSource().equals(deleteProduct.getB2())){

                deleteProduct.closeFrame();
            }

            if (e.getSource().equals(jTableClient.getB1())) {
                jTableClient.closeFrame();
            }

            if (e.getSource().equals(jTableProduct.getB1())) {
                jTableProduct.closeFrame();
            }
        }
    }

    class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(clientView.getB1())) {

                addClient.openFrame();

            } else if (e.getSource().equals(clientView.getB2())) {

                updateClient.openFrame();

            } else if (e.getSource().equals(clientView.getB3())) {

                deleteClient.openFrame();

            } else if (e.getSource().equals(clientView.getB4())) {
                try {
                    jTableClient.openFrame();
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }

            } else if (e.getSource().equals(productView.getB1())) {
                addProduct.openFrame();
            } else if (e.getSource().equals(productView.getB2())) {
                updateProduct.openFrame();
            } else if (e.getSource().equals(productView.getB3())) {
                deleteProduct.openFrame();
            } else if (e.getSource().equals(productView.getB4())) {

                try {
                    jTableProduct.openFrame();
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}

