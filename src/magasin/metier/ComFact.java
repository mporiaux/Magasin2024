package magasin.metier;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * classe métier de gestion d'une commande-facture
 *
 * @author Michel Poriaux
 * @version 1.0
 * @see Client
 * @see Ligne
 */
public class ComFact {

    /**
     * identifiant unique-numéro de commande
     */
    protected int idcommande;
    /**
     * numéro de facture - unique
     */
    protected Integer numfact;

    /**
     * date de la commande
     */
    protected LocalDate datecom;

    /**
     * date de la facture
     */
    protected LocalDate dateFacturation;
    /**
     * date du payement
     */
    protected LocalDate datePayement;
    /**
     * état de la facture :c:en commande-f:facturée-p:payée
     */
    protected char etat;
    /**
     * montant total de la commande
     */
    protected BigDecimal montant;
    /**
     * client de la commande
     */
    protected Client client;

    /**
     * liste des lignes de commande
     */
    protected List<Ligne> lignes = new ArrayList<>();

    /**
     * constructeur paramétré
     *
     * @param idcommande numéro de commande
     * @param numfact numéro de facture
     * @param datecom date de commande
     * @param etat état de la commande C,F,P
     * @param montant montant total de la commande
     */
    public ComFact(int idcommande, Integer numfact, LocalDate datecom, char etat, BigDecimal montant, Client client) {
        this.idcommande = idcommande;
        this.numfact = numfact;
        this.datecom = datecom;
        this.etat = etat;
        this.montant = montant.setScale(2, RoundingMode.HALF_UP);
        this.client = client;
    }

    public ComFact() {

    }


    /**
     * getter client de la commande
     * @return client de la commande
     */
    public Client getClient() {
        return client;
    }

    /**
     * setter client de la commande
     * @param client client de la commande
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * getter date de commande
     *
     * @return date de la commande
     */
    public LocalDate getDatecom() {
        return datecom;
    }

    /**
     * setter date de commande
     *
     * @param datecom date de la commande
     */
    public void setDatecom(LocalDate datecom) {
        this.datecom = datecom;
    }


    /**
     * getter date de commande
     *
     * @return date de facturation
     */
    public LocalDate getDateFacturation() {
        return dateFacturation;
    }

    /**
     * setter date de commande
     *
     * @param dateFacturation date de facturation
     */
    public void setDateFacturation(LocalDate dateFacturation) {
        this.dateFacturation = dateFacturation;
    }

    /**
     * getter date de payement
     *
     * @return date de payement
     */
    public LocalDate getDatePayement() {
        return datePayement;
    }

    /**
     * setter date de payement
     *
     * @param datePayement date de payement
     */
    public void setDatePayement(LocalDate datePayement) {
        this.datePayement= datePayement;
    }

    /**
     * getter état de la commande
     *
     * @return état de la commande (C:en commande,F:facturée,P:payée)
     */
    public char getEtat() {
        return etat;
    }

    /**
     * setter état de la commande
     *
     * @param etat état de la commande (C:en commande,F:facturée,P:payée)
     */
    public void setEtat(char etat) {
        this.etat = etat;
    }

    /**
     * getter montant
     *
     * @return montant total de la commande
     */
    public BigDecimal getMontant() {
        return montant;
    }

    /**
     * setter du montant
     *
     * @param montant montant total de la commande
     */
    public void setMontant(BigDecimal montant) {
        this.montant = montant.setScale(2,RoundingMode.HALF_UP);
    }

    /**
     * getter numéro de commande
     *
     * @return numéro de la commande
     */
    public int getIdcommande() {
        return idcommande;
    }

    /**
     * setter numéro de commande
     *
     * @param idcommande numéro de la commande
     */
    public void setIdcommande(int idcommande) {
        this.idcommande = idcommande;
    }

    /**
     * getter numéro de facture
     *
     * @return nuléro de la facture
     */
    public Integer getNumfact() {
        return numfact;
    }

    /**
     * setter numéro de facture
     *
     * @param numfact numéro de facture
     */
    public void setNumfact(Integer numfact) {
        this.numfact = numfact;
    }

    /**
     * getter lignes de commandes
     *
     * @return listes des lignes de commande
     */
    public List<Ligne> getLignes() {
        return lignes;
    }

    /**
     * setter lignes
     *
     * @param lignes liste des lignes de commande
     */
    public void setLignes(List<Ligne> lignes) {
                this.lignes = lignes;
                majMontant();
    }

    /**
     * méthode toString
     * @return informations complètes de la commande
     */

    /**
     * ajout d'une ligne à la commande
     * @param ligne ligne à ajouter
     * @return ajout effectué ou pas
     */
    public boolean  addLigne(Ligne ligne){
        for(Ligne l : lignes){
            if(l.getProduit().equals(ligne.getProduit())) return false;
        }
        lignes.add(ligne);
        majMontant();
        return true;
        //TODO vérifier produit
    }

    /**
     * suppression d'une ligne à la commande
     * @param ligne ligne à supprimer
     */
    public void supLigne(Ligne ligne){
        lignes.remove(ligne);
        majMontant();
    }
    @Override
    public String toString() {
        return "ComFact{" +
                "idcommande=" + idcommande +
                ", numfact=" + numfact +
                ", datecom=" + datecom +
                ", dateFacturation=" + dateFacturation +
                ", datePayement=" + datePayement +
                ", etat=" + etat +
                ", montant=" + montant +
                ", client=" + client +
                '}';
    }

    /**
     * mise à jour du montant de la commande sur base des lignes de commande
     */
    private void majMontant(){
        montant = new BigDecimal(0).setScale(0,RoundingMode.HALF_UP);
        for(Ligne ligne : lignes){
            montant.add(ligne.valLigne());
        }
    }

    /**
     * vérification du retard d'une facture
     * @return en retard ou pas
     */
    public boolean estEnRetard(){
        return etat=='f' && datePayement==null &&  dateFacturation.plusDays(30).isBefore(LocalDate.now());
    }

    public boolean estNonPayee(){
        return etat=='f' && datePayement == null;
    }

    /**
     * calcul du hashcode de la commande
     * @return hashcode de la commande
     */

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.idcommande;
        return hash;
    }

    /**
     * vérification de l'égalité de deux commandes basée sur l'id de la commande
     * @param obj autre commande
     * @return égalité ou pas
     */

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
        if (this.idcommande != other.idcommande) {
            return false;
        }
        return true;
    }

}
