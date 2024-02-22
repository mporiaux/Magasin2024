package magasin;

import magasin.metier.Client;
import magasin.metier.Produit;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

       Produit p = new Produit(1,"A2","TEST",new BigDecimal("100.0"),100,50);
        System.out.println("quantité sup ");
        Scanner sc =new Scanner(System.in);
        int q = sc.nextInt();
        p.reapprovisionner(q);
        System.out.println("nouveau stock de "+p.getDescription()+" "+p.getStock());
    }
}