package magasin.metier;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Scanner;

/**
 * classe métier de gestion d'un produit
 * @author Michel Poriaux
 * @version 1.0
 */
public class Produit {


    /**
     * id unique du produit
     */
    protected int idproduit;

    /**
     * numéro unique du produit
     */
	protected String numprod;
    /**
     *  description du produit
     */
	protected String description;
     /**
      * prix hors tva
      */
	protected BigDecimal phtva;
      /**
       * stock en cours
       */
	protected int stock;

    /**
     * stock minimum en cours
     */
    protected int stockMin;

  /**
 * constructeur par défaut
 */
    public Produit() {
    }
/**
 * constructeur paramétré
 * @param idproduit id numérique unique du produit
 * @param numprod numéro unique du produit
 * @param description description du produit
 * @param phtva prix hors tva du produit
 * @param stock stock en cours du produit/**
 * constructeur paramétré
 */

    public Produit(int idproduit,String numprod, String description, BigDecimal phtva, int stock,int stockMin)  {
        this.idproduit=idproduit;
        this.numprod = numprod;
        this.description = description;
        this.phtva = phtva;
        phtva.setScale(2,RoundingMode.HALF_UP);
        this.stock = stock;
        this.stockMin=stockMin;
    }



 /**
    * getter phtva
    * @return prix hors tva du produit
    */
    public BigDecimal getPhtva() {
        return phtva;
    }
 /**
     * setter phtva
     * @param phtva prix hors tva du produit
     */
    public void setPhtva(BigDecimal phtva) {
        this.phtva = phtva;
    }

    /**
     * getter stock
     * @return stock en cours
     */

    public int getStock() {
        return stock;
    }

     /**
     * setter stock
     * @param stock nouveau stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * getter stockMin
     * @return stock en cours
     */

    public int getStockMin() {
        return stockMin;
    }

    /**
     * setter stock
     * @param stockMin nouveau stock
     */
    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }
    /**
     * getter description
     * @return description du produit
     */
    public String getDescription() {
        return description;
    }


     /**
     * setter description
     * @param description nouvelle description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * getter numéro de produit
     * @return numéro de produit
     */
     public String getNumprod() {
        return numprod;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public void setNumprod(String numprod) {
        this.numprod = numprod;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "idproduit=" + idproduit +
                ", numprod='" + numprod + '\'' +
                ", description='" + description + '\'' +
                ", phtva=" + phtva +
                ", stock=" + stock +
                ", stockMin=" + stockMin +
                '}';
    }

    /**
     * affichage des infos du produit
     * @return description complète du produit
    */


    /**
     * vérification si le stock est suffisant
     * @return stock suffisant ou pas
     */
    public boolean stockSuffisant(){
        return stock>=stockMin;
    }

    /**
     * calcul de la quantité à recommander pour réalimenter le stock du produit
     * @return quantité à recommander
     */
    public int quantiteACommander(){
        int stockMax = (int)(stockMin*1.5);
        if (stockMax>stock) return stockMax-stock;
        else return 0;
      }

    /**
     * Réapprovisionnement du stock
     * @param q quantité du réapprovisionnement
     */
    public void reapprovisionner(int q){

             stock+=q;
    }


    /**
     * Demande de diminution du stock
     * @param q valeur de la diminution
     * @return diminution effectuée ou pas selon la valeur en stock
     */
    public boolean retirer(int q){
        if(q>stock) return false;
        stock-=q;
        return true;
    }

    /**
     * calcul de la valeur monétaire en stock
     * @return valeur monétaire du stock
     */
    public BigDecimal valeurStock(){
       return phtva.multiply(new BigDecimal(stock));
    }

    /**
     * vérification de l'égalité de deux produits basée sur le numéro du produit
     * @param o
     * @return égalité ou pas
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produit produit = (Produit) o;
        return numprod.equals(produit.numprod);
    }

    /**
     * calcul du hashcode du produit
     * @return valeur du hashcode du produit
     */
    @Override
    public int hashCode() {
        return Objects.hash(numprod);
    }
}


