package Model;

/**
 * Clasa Bill reprezintă o înregistrare a unei facturi ce conține informații despre achiziția unui client.
 * Este o clasă imutabilă.
 */

public record Bill(int idClient, int idProdus, int nrProduse, float valoare) {

}
