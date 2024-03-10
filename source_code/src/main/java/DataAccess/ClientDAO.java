package DataAccess;

import Model.Clients;

import java.util.List;

/**
 * Implementează operațiunile specifice pentru accesul la date pentru entitatea Clients.
 */

public class ClientDAO extends AbstractDAO<Clients>{

    private Clients client;

    public ClientDAO(){

        this.ok = 1;

    }

    public List<Clients> findAll() {

        return super.findAll();
    }

    public Clients findById(int id) {

        return super.findById(id);
    }

    public Clients insert(Clients client) {

        return super.insert(client);
    }

    public Clients update(Clients client) {

        return super.update(client);
    }

    public boolean delete(Clients client) {

        return super.delete(client);
    }

}
