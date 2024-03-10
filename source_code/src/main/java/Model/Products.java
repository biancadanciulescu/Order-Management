package Model;

/**
 * Reprezintă o entitate pentru produse.
 */
public class Products {
    private int id;
    private String denumire;
    private int stoc;
    private float pret;

    public Products(int id, String denumire, int stoc, float pret) {
        this.id = id;
        this.denumire = denumire;
        this.stoc = stoc;
        this.pret = pret;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }
}
