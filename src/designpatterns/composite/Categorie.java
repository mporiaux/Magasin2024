/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatterns.composite;


import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Michel
 */
public class Categorie extends Element {
    private String nom;
    private Set<Element> elts=new HashSet<>();
    
    public Categorie(int id,String nom){
        super(id);
        this.nom=nom;
    }

    @Override
    public String toString() {
        StringBuilder aff= new StringBuilder(getId()+" "+nom+"\n");
        
        for(Element e:elts){
            aff.append(e+"\n");
        }
        return aff+"valeur totale " +nom +" = "+valStock()+"\n";
    }

  

    @Override
    public float valStock() {
        float somme=0;
       for(Element pc:elts){
           somme+=pc.valStock();
       }
       return somme;
    }

    public Set<Element> getElts() {
        return elts;
    }
    
}
