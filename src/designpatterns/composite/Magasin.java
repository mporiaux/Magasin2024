/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatterns.composite;

/**
 *
 * @author Michel
 */
public class Magasin {

    public static void main(String[] args) {
        Produit p1 = new Produit(1, "P00001", "écran", 200, 10);
        Produit p2 = new Produit(2, "P00002", "souris", 15, 100);
        Produit p3 = new Produit(3, "P00003", "clavier", 12, 10);
        Produit p4 = new Produit(4, "P00004", "clé usb", 20, 15);
        Categorie c1 = new Categorie(5, "générale");
        Categorie c2 = new Categorie(6, "cat1");
        Categorie c3 = new Categorie(7, "cat2");
        c1.getElts().add(p1);
        c1.getElts().add(c2);
        c1.getElts().add(c3);
        c2.getElts().add(p2);
        c2.getElts().add(p3);
        c3.getElts().add(p4);
        System.out.println(c1);

    }
}
