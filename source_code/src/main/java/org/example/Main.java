package org.example;

import BusinessLogic.ClientBLL;
import BusinessLogic.OrderBLL;
import BusinessLogic.ProductBLL;
import DataAccess.ClientDAO;
import DataAccess.OrderDAO;
import DataAccess.ProductDAO;
import Model.Products;
import Presentation.*;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import javax.swing.*;

public class Main {
    public static void main(String[] args){ //throws IllegalAccessException {

        View view = new View();
        ClientView clientView = new ClientView();
        ProductView productView = new ProductView();
        OrderView orderView = new OrderView();
        AddClient newClient = new AddClient();
        AddProduct newProduct = new AddProduct();
        DeleteClient deleteClient = new DeleteClient();
        DeleteProduct deleteProduct = new DeleteProduct();


        JTableClient jTableClient = new JTableClient();
        JTableProduct jTableProduct = new JTableProduct();
        UpdateClient updateClient = new UpdateClient();
        UpdateProduct updateProduct = new UpdateProduct();
        Controller c = new Controller(newClient, newProduct, clientView, deleteClient, deleteProduct, orderView, productView, view, jTableClient, jTableProduct, updateClient, updateProduct);


        ClientDAO clientDAO = new ClientDAO();
        ClientBLL clientBLL = new ClientBLL(clientDAO, c);
        OrderDAO orderDAO = new OrderDAO();
        OrderBLL orderBLL = new OrderBLL(orderDAO, c);

        ProductDAO productDAO = new ProductDAO();
        ProductBLL productBLL = new ProductBLL(productDAO, c);

    }
}