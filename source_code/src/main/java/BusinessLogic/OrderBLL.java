package BusinessLogic;

import Connection.ConnectionFactory;
import DataAccess.AbstractDAO;
import DataAccess.OrderDAO;
import DataAccess.ProductDAO;
import Model.Bill;
import Model.Clients;
import Model.Orders;
import Model.Products;
import Presentation.Controller;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clasa de nivel de logica a afacerii pentru gestionarea comenzilor.
 */

public class OrderBLL {

  //  OrderDAO orderDAO = new OrderDAO();
  //  ProductDAO productDAO = new ProductDAO();
    private OrderDAO orderDAO;
    private Controller controller;
    ProductDAO productDAO = new ProductDAO();
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());


    /**
     * Construiește o nouă instanță a clasei OrderBLL.
     */
    public OrderBLL(OrderDAO orderDAO, Controller controller){
        this.orderDAO = orderDAO;
        this.controller = controller;

        controller.getOrderView().addActionListeners(new OrderBLL.AddOrderViewListener(), new OrderBLL.BackListener());

    }

    /**
     * Implementare ActionListener pentru orders.
     */

    public class AddOrderViewListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(controller.getOrderView().getB1())) {

                ArrayList<String> inputs = controller.getOrderView().getInput();

                if (!inputs.isEmpty()) {

                    Products produs = productDAO.findById(Integer.parseInt(inputs.get(1)));

                    if(produs.getStoc() < Integer.parseInt(inputs.get(2)))
                        JOptionPane.showMessageDialog(null, "Out of stock", "Try Again", JOptionPane.ERROR_MESSAGE);
                    else{
                        float totalPrice = Float.parseFloat(inputs.get(2)) * produs.getPret();

                        Orders order = new Orders(Integer.parseInt(inputs.get(0)), Integer.parseInt(inputs.get(1)), Integer.parseInt(inputs.get(2)), totalPrice);

                        int stocNou = produs.getStoc() - Integer.parseInt(inputs.get(2));
                        orderDAO.insert(order);

                        Bill bill=new Bill(Integer.parseInt(inputs.get(0)), Integer.parseInt(inputs.get(1)), Integer.parseInt(inputs.get(2)), totalPrice);
                        Connection connection = null;
                        PreparedStatement statement = null;
                        ResultSet resultSet = null;
                        String query="insert into logtable values(" +bill.idClient()+","+bill.idProdus()+","+bill.nrProduse()+","+bill.valoare()+")";
                        try{
                            connection= ConnectionFactory.getConnection();
                            statement= ((Connection) connection).prepareStatement(query);
                            statement.executeUpdate();
                        }catch (SQLException a) {
                            LOGGER.log(Level.WARNING,   a.getMessage());
                        } finally {
                            ConnectionFactory.close(resultSet);
                            ConnectionFactory.close(statement);
                            ConnectionFactory.close(connection);
                        }


                        produs.setStoc(stocNou);

                        productDAO.update(produs);


                        JOptionPane.showMessageDialog(null, "Placed order");


                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Add valid input.");
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

            if (e.getSource().equals(controller.getOrderView().getB2())) {
                controller.getOrderView().closeFrame();
                controller.getView().openFrame();
            }
        }
    }
}






