package DataAccess;

import Model.Clients;
import Model.Orders;

import java.util.List;

/**
 * Implementează operațiunile specifice pentru accesul la date pentru entitatea Orders.
 */

public class OrderDAO extends AbstractDAO<Orders>{

    public OrderDAO(){
        this.ok = 3;
    }

    public List<Orders> findAll() {

        return super.findAll();
    }

    public Orders findById(int id) {

        return super.findById(id);
    }

    public Orders insert(Orders order) {

        return super.insert(order);
    }

    public Orders update(Orders order) {

        return null;
    }


}
