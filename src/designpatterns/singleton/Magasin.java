/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatterns.singleton;

/**
 *
 * @author Michel
 */
public class Magasin {
    public static void main(String[] args) {
        Client cl=new Client(1,"Durand","Jean",1000,"BXL","de la Senne","12A","0456/990088");
        Produit pr=new Produit(1,"P00001","écran",100f, 50);
        for(String l:Journal.getInstance().getLignes()){
            System.out.println(l);
        }
    }
}
