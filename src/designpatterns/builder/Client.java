
package designpatterns.builder;

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
 * @param cb objet de la classe ClientBuilder contenant les informations d'initialisation
 */

   private Client(ClientBuilder cb) {
        this.idclient = cb.idclient;
        this.nom = cb.nom;
        this.prenom = cb.prenom;
        this.cp = cb.cp;
        this.localite = cb.localite;
        this.rue = cb.rue;
        this.num = cb.num;
        this.tel = cb.tel;
    }


   /**
    * getter idclient
    * @return identifiant du client
    */
    public int getIdclient() {
        return idclient;
    }

    
    /**
     * getter code postal
     * @return code postal
     */
    public int getCp() {
        return cp;
    }

   

    /**
     * getter localite
     * @return localite
     */
    public String getLocalite() {
        return localite;
    }

    

    /**
     * getter nom
     * @return nom du client
     */
    public String getNom() {
        return nom;
    }

    

    /**
     * getter numéro de rue
     * @return numéro de rue
     */
    public String getNum() {
        return num;
    }

    

    /**
     * getter prénom du client
     * @return prénom du client
     */
    public String getPrenom() {
        return prenom;
    }

   

    /**
     * getter rue
     * @return rue
     */
    public String getRue() {
        return rue;
    }

    

    /**
     * getter téléphone
     * @return téléphone du client
     */
    public String getTel() {
        return tel;
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
  
    
    public static class ClientBuilder{
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

        public ClientBuilder setIdclient(int idclient) {
            this.idclient = idclient;
            return this;
        }

        public ClientBuilder setNom(String nom) {
            this.nom = nom;
            return this;
        }

        public ClientBuilder setPrenom(String prenom) {
            this.prenom = prenom;
            return this;
        }

        public ClientBuilder setCp(int cp) {
            this.cp = cp;
            return this;
        }

        public ClientBuilder setLocalite(String localite) {
            this.localite = localite;
            return this;
        }

        public ClientBuilder setRue(String rue) {
            this.rue = rue;
            return this;
        }

        public ClientBuilder setNum(String num) {
            this.num = num;
            return this;
        }

        public ClientBuilder setTel(String tel) {
            this.tel = tel;
            return this;
        }
     
        public Client build() throws Exception{
            if(idclient<=0 || nom==null || prenom==null || tel==null) throw new Exception("informations de construction incomplètes");
            return new Client(this);
        }

}
    
}
