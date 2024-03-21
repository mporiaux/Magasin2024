package designpatterns.composite;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * classe métier de gestion d'une commande-facture
 * @author Michel Poriaux
 * @version 1.0
 * @see Client
 */

public class ComFact {
    
    /**
     * identifiant unique-numéro de commande
     */
	protected int numcommande;
     /**
      * numéro de facture - unique
      */
	protected int numfact;
        
       /**
        * date de la facture
        */
	protected Date datecom;
        /**
         * état de la facture :c:en commande-f:facturée-p:payée
         */
	protected char etat;
        /**
         * montant total de la commande
         */
	protected float montant;
         /**
         * client propriétaire
         */        
        protected  Client monClient;
       
	protected Set<Ligne> mesLignes =new HashSet<>();
        
    /**
     * constructeur par défaut
     */
    public ComFact() {
    }
        
    /**
     * constructeur paramétré
     * @param numcommande numéro de commande
     * @param numfact numéro de facture
     * @param datecom date de commande
     * @param etat état de la commande c,f,p
     * @param montant montant total de la commande
     */
    public ComFact(int numcommande, int numfact, Date datecom,char etat, float montant,Client monClient) {
        this.numcommande = numcommande;
        this.numfact = numfact;
        this.datecom = datecom;
        this.etat = etat;
        this.montant = montant;
        this.monClient=monClient;
        
    }



    /**
     * getter date de commande
     * @return date de la commande
     */
    public Date getDatecom() {
        return datecom;
    }
 /**
  * setter date de commande
  * @param datecom date de la commande
  */
    public void setDatecom(Date datecom) {
        this.datecom = datecom;
    }
/**
 * getter état de la commande
 * @return état de la commande (c:en commande,f:facturée,p:payée)
 */
    public char getEtat() {
        return etat;
    }
/**
 * setter état de la commande
 * @param etat état de la commande (c:en commande,f:facturée,p:payée)
 */
    public void setEtat(char etat) {
        this.etat = etat;
    }


    /**
     * getter montant 
     * @return montant total de la commande
     */
    public float getMontant() {
        return montant;
    }

    /**
     * setter du montant
     * @param montant montant total de la commande
     */
    public void setMontant(float montant) {
        this.montant = montant;
    }
/**
 * getter numéro de commande
 * @return numéro de la commande
 */
    public int getNumcommande() {
        return numcommande;
    }
/**
 * setter numéro de commande
 * @param numcommande numéro de la commande 
 */
    public void setNumcommande(int numcommande) {
        this.numcommande = numcommande;
    }
/**
 * getter numéro de facture
 * @return nuléro de la facture
 */
    public int getNumfact() {
        return numfact;
    }
/**
 * setter numéro de facture
 * @param numfact numéro de facture
 */
    public void setNumfact(int numfact) {
        this.numfact = numfact;
    }
        public Client getMonClient() {
        return monClient;
    }

    public void setMonClient(Client monClient) {
        this.monClient = monClient;
    }

    public Set<Ligne> getMesLignes() {
        return mesLignes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.numcommande;
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
        final ComFact other = (ComFact) obj;
        if (this.numcommande != other.numcommande) {
            return false;
        }
        return true;
    }
    
    
    /**
 * méthode toString
 * @return informations complètes
 */
    @Override
    public String toString() {
        return "ComFact{" + "numcommande=" + numcommande + ", numfact=" + numfact + ", datecom=" + datecom + ", etat=" + etat + ", montant=" + montant + ", fkclient=" + monClient + '}';
    }



   

        
}
