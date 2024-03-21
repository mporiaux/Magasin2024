package designpatterns.observer;

import java.util.HashSet;
import java.util.Set;


/**
 * classe métier de gestion d'un produit
 * @author Michel Poriaux
 * @version 1.0
 */
public class Produit extends Subject{
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
 * constructeur par défaut
 */
    public Produit() {
    }
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
        notifyObservers();
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idproduit;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produit other = (Produit) obj;
        if (this.idproduit != other.idproduit) {
            return false;
        }
        return true;
    }
        
        

    /**
     * affichage des infos du produit
     * @return description complète du produit
    */
    @Override
    public String toString() {
        return "Produit{" + "idproduit="+idproduit+", numprod=" + numprod + ", description=" + description + ", phtva=" + phtva + ", stock=" + stock + '}';
    }

    @Override
    public String getNotification() {
        return "nouveau prix de "+numprod+" = "+phtva;
    }

 
    
        
}


    