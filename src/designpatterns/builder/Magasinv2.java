/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatterns.builder;

/**
 *
 * @author Michel
 */
public class Magasinv2 {
    public static void main(String[] args)  {
        
        try {
            ClientV2 cl1 = new ClientV2.ClientBuilder(1,"Durant","Eric","0455/332211").
                    setLocalite("Mons").
                    build();
            System.out.println(cl1);
        } catch (Exception e) {
            System.out.println("erreur "+e);
        }
        
            try {
            ClientV2 cl2 = new ClientV2.ClientBuilder(0,"Durant","Eric","0455/332211").
                    setLocalite("Mons").
                    build();
                System.out.println(cl2);
        } catch (Exception e) {
            System.out.println("erreur "+e);
        }
        
    }
}
