/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatterns.singleton;


import java.util.Objects;

/**
 *
 * @author Michel
 */
public class Ligne {
    protected int idligne;
    protected Produit produit;
    protected ComFact comfact;
    protected int quantite;
    protected double pa;

    public Ligne(int idligne, Produit produit, ComFact comfact, int quantite, double pa) {
        this.idligne = idligne;
        this.produit = produit;
        this.comfact = comfact;
        this.quantite = quantite;
        this.pa = pa;
    }

    public int getIdligne() {
        return idligne;
    }

    public void setIdligne(int idligne) {
        this.idligne = idligne;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public ComFact getComfact() {
        return comfact;
    }

    public void setComfact(ComFact comfact) {
        this.comfact = comfact;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPa() {
        return pa;
    }

    public void setPa(double pa) {
        this.pa = pa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.produit);
        hash = 97 * hash + Objects.hashCode(this.comfact);
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
        final Ligne other = (Ligne) obj;
        if (!Objects.equals(this.produit, other.produit)) {
            return false;
        }
        if (!Objects.equals(this.comfact, other.comfact)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "Ligne{" + "idligne=" + idligne + ", produit=" + produit + ", comfact=" + comfact + ", quantite=" + quantite + ", pa=" + pa + '}';
    }
    
}
