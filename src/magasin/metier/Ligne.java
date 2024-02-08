/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magasin.metier;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * classe représentant les informations d'une ligne de commande
 * @see ComFact
 * @See Produit
 * @author Michel
 */
public class Ligne {
    /**
     * id unique de la ligne de commande
     */
    protected int idligne;
    /**
     * produit relatif à la ligne de commande
     */
    protected Produit produit;
    /**
     * quantité achetée
     */
    protected int quantite;
    /**
     * prix d'achat du produit au moment de la commande
     */
    protected BigDecimal prixAchat;


    /**
     * constructeur paramétré
     * @param idligne identifiant de la ligne
     * @param produit produit acheté
     * @param quantite quantité achetée
     * @param prixAchat prix d'achat au moment de l'achat
     */
    public Ligne(int idligne,Produit produit, int quantite, BigDecimal prixAchat) {
        this.idligne=idligne;
        this.produit = produit;
        this.quantite = quantite;
        this.prixAchat = prixAchat;
        prixAchat.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * getter idligne
     * @return idligne
     */
    public int getIdligne() {
        return idligne;
    }

    /**
     * setter idligne
     * @param idligne identifiant de la ligne
     */
    public void setIdligne(int idligne) {
        this.idligne = idligne;
    }


    /**
     * getter produit
     * @return produit acheté
     */
    public Produit getProduit() {
        return produit;
    }

    /**
     * setter produit
     * @param produit produit acheté
     */

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    /**
     * getter quantité
     * @return quantité achetée
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * setter quantité
     * @param quantite quantité achetée
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     * getter prix d'achat
     * @return prix d'achat
     */
    public BigDecimal getPrixAchat() {
        return prixAchat;
    }

    /**
     * setter prix d'achat
     * @param prixAchat prix d'achat du produit
     */
    public void setPrixAchat(BigDecimal prixAchat) {
        this.prixAchat = prixAchat;
    }

    /**
     * calcul de la valeur d'une ligne basée sur le prix d'achat du produit et la quantité achetée
     * @return valeur de la ligne
     */
    public BigDecimal valLigne(){
        return prixAchat.multiply(new BigDecimal(quantite)).setScale(2,RoundingMode.HALF_UP);
    }
    /**
     * méthode toString
     * @return informations complètes
     */
    @Override
    public String toString() {
        return "Ligne{" + "produit=" + produit + ", quantite=" + quantite + ", prixAchat=" + prixAchat + '}';
    }

    /**
     * méthode de vérification d'égalité" de deux lignes
     * @param o autre ligne
     * @return égalité ou pas
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ligne ligne = (Ligne) o;
        return idligne == ligne.idligne;
    }

    /**
     * calcul du hashcode de la ligne
     * @return hashcode de la ligne
     */
    @Override
    public int hashCode() {
        return Objects.hash(idligne);
    }
}
