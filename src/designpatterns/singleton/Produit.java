package designpatterns.singleton;

import java.util.HashSet;
import java.util.Set;


/**
 * classe métier de gestion d'un produit
 * @author Michel Poriaux
 * @version 1.0
 */
public class Produit {
    /**
     * identifiant unique du produit
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
	protected float phtva;
      /**
       * stock en cours
       */
	protected int stock;
        
        protected Set<Ligne> mesLignes =new HashSet<>();


/**
 * constructeur paramétré
 * @param idproduit id unique du produit
 * @param numprod numéro unique du produit
 * @param description description du produit
 * @param phtva prix hors tva du produit
 * @param stock stock en cours du produit/**
 * constructeur paramétré
 */
        
    public Produit(int idproduit,String numprod, String description, float phtva, int stock)  {
        this.idproduit=idproduit;
        this.numprod = numprod;
        this.description = description;
        this.phtva = phtva;
        this.stock = stock;
        Journal.getInstance().getLignes().add("creation de "+this);

    }
 /**
    * getter phtva
    * @return prix hors tva du produit
    */
    public float getPhtva() {
        return phtva;
    }
 /**
     * setter phtva
     * @param phtva prix hors tva du produit
     */
    public void setPhtva(float phtva) {
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

      /**
     * getter id du produit
     * @return id du produit
     */
     public int getIdproduit() {
        return idproduit;
    }
     
        public Set<Ligne> getMesLignes() {
        return mesLignes;
    }

    /**
     * affichage des infos du produit
     * @return description complète du produit
    */
    @Override
    public String toString() {
        return "Produit{" + "idproduit="+idproduit+", numprod=" + numprod + ", description=" + description + ", phtva=" + phtva + ", stock=" + stock + '}';
    }

 
    
        
}


    