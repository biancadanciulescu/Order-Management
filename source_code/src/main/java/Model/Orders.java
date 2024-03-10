package Model;

import java.util.ArrayList;

/**
 * Reprezintă o entitate pentru comenzi.
 */
public class Orders {
    //private int id;
    private int idClient;
    private int idProdus;
    private int nrProduse;
    private float valoare;

    public Orders( int idClient, int idProdus, int nrProduse, float valoare) {
        //this.id = id;
        this.idClient = idClient;
        this.idProdus = idProdus;
        this.nrProduse = nrProduse;
        this.valoare = valoare;
    }

   /* public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    public int getNrProduse() {
        return nrProduse;
    }

    public void setNrProduse(int nrProduse) {
        this.nrProduse = nrProduse;
    }

    public float getValoare() {
        return valoare;
    }

    public void setValoare(float valoare) {
        this.valoare = valoare;
    }


}
