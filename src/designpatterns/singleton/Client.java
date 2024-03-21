
package designpatterns.singleton;


import java.util.HashSet;
import java.util.Set;

/**
 * classe métier de gestion d'un client
 * @author Michel Poriaux
 * @version 1.0
 * 
 */


public class Client {
  /**
   * identifiant unique du client
   */
	protected int idclient;
  /**
   * nom du client
   */
	protected String nom;
  /**
   * prénom du client
   */
	protected String prenom;
  /**
   * code postal de la localité
   */
	protected int cp;
   /**
    * localité
    */
	protected String localite;
   /**
    * rue
    */
	protected String rue;
   /**
    * numéro de rue
    */
        protected String num;
   /**
    * numéro de téléphone du client
    */
	protected String tel;
        
        protected Set<ComFact> mesCommandes = new HashSet<>();


/**
 * constructeur paramétré
 * @param idclient identifiant unique du client, affecté par la base de données
 * @param nom nom du client
 * @param prenom prénom du client
 * @param cp code postal de l'adresse
 * @param localite localité de l'adresse
 * @param rue rue de l'adresse
 * @param num numéro de l'adresse
 * @param tel téléphone du client
 */

    public Client(int idclient, String nom, String prenom, int cp, String localite, String rue, String num, String tel) {
        this.idclient = idclient;
        this.nom = nom;
        this.prenom = prenom;
        this.cp = cp;
        this.localite = localite;
        this.rue = rue;
        this.num = num;
        this.tel = tel;
        Journal.getInstance().getLignes().add("creation de "+this);
    }


   /**
    * getter idclient
    * @return identifiant du client
    */
    public int getIdclient() {
        return idclient;
    }

    /**
     * setter idclient
     * @param idclient identifiant du client
     */
    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }
  
    /**
     * getter code postal
     * @return code postal
     */
    public int getCp() {
        return cp;
    }

    /**
     * setter code postal
     * @param cp code postal
     */
    public void setCp(int cp) {
        this.cp = cp;
    }

    /**
     * getter localite
     * @return localite
     */
    public String getLocalite() {
        return localite;
    }

    /**
     * setter localite
     * @param localite localité
     */
    public void setLocalite(String localite) {
        this.localite = localite;
    }

    /**
     * getter nom
     * @return nom du client
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter nom du client
     * @param nom nom du client
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter numéro de rue
     * @return numéro de rue
     */
    public String getNum() {
        return num;
    }

    /**
     * setter numéro de rue
     * @param num numéro de rue
     */
    public void setNum(String num) {
        this.num = num;
    }

    /**
     * getter prénom du client
     * @return prénom du client
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * setter nom du client
     * @param prenom prénom du client
     */

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * getter rue
     * @return rue
     */
    public String getRue() {
        return rue;
    }

    /**
     * setter rue
     * @param rue rue de l'adresse
     */
    public void setRue(String rue) {
        this.rue = rue;
    }

    /**
     * getter téléphone
     * @return téléphone du client
     */
    public String getTel() {
        return tel;
    }

    /**
     * setter téléphone
     * @param tel téléphone du client
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

 /**
 * méthode toString
 * @return informations complètes
 */
    @Override
    public String toString() { 
        return "Client{" + "idclient=" + idclient + ", nom=" + nom + ", prenom=" + prenom + ", cp=" + cp + ", localite=" + localite + ", rue=" + rue + ", num=" + num + ", tel=" + tel + '}';
    }

    public Set<ComFact> getMesCommandes() {
        return mesCommandes;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.idclient;
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
        final Client other = (Client) obj;
        if (this.idclient != other.idclient) {
            return false;
        }
        return true;
    }
  
}
