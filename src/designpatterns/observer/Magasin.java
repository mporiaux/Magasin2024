/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatterns.observer;

/**
 *
 * @author Michel
 */
public class Magasin {
    public static void main(String[] args) {
         Produit p1 = new Produit(1, "P00001", "écran", 200, 10);
         Produit p2 = new Produit(2, "P00002", "souris", 15, 100);
         Client cl1= new Client(1,"Durand","Jean",1000,"BXL","de la Senne","12A","0456/990088");
         Client cl2= new Client(2,"Dupond","Annie",1000,"BXL","de la bière","14","0451/441122");
         p1.addObserver(cl1);
         p1.addObserver(cl2);
         p2.addObserver(cl1);
         
         p1.setPhtva(210);
         p2.setPhtva(12);
    }
}
