package DataAccess;
import Model.Products;
import java.util.List;

/**
 * Implementează operațiunile specifice pentru accesul la date pentru entitatea Products.
 */

public class ProductDAO extends AbstractDAO<Products>{

    public ProductDAO(){
        this.ok = 2;
    }

    public List<Products> findAll() {

        return super.findAll();
    }

    public Products findById(int id) {

        return super.findById(id);
    }

    public Products insert(Products product) {

        return super.insert(product);
    }

    public Products update(Products product) {

        return super.update(product);
    }

    public boolean delete(Products product) {

        return super.delete(product);
    }
}
