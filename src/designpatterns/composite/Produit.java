package designpatterns.composite;

import java.util.HashSet;
import java.util.Set;


/**
 * classe métier de gestion d'un produit
 * @author Michel Poriaux
 * @version 1.0
 */
public class Produit extends Element{
  
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
        super(idproduit);
     
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


     
        public Set<Ligne> getMesLignes() {
        return mesLignes;
    }

        
        @Override
     public float valStock(){
         return stock*phtva;
     }
    /**
     * affichage des infos du produit
     * @return description complète du produit
    */
    @Override
    public String toString() {
        return "Produit{" + "idproduit="+ getId()+", numprod=" + numprod + ", description=" + description + ", phtva=" + phtva + ", stock=" + stock +" valeur="+valStock()+ '}';
    }

 
    
        
}


    