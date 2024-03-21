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
public class Magasin {
    public static void main(String[] args)  {
        
        try {
            Client cl1 = new Client.ClientBuilder().
                    setIdclient(1).
                    setNom("Durant").
                    setPrenom("Eric").
                    setTel("0455/332211").
                    setLocalite("Mons").
                    build();
            System.out.println(cl1);
        } catch (Exception e) {
            System.out.println("erreur "+e);
        }
        
            try {
            Client cl2 = new Client.ClientBuilder().
                    setIdclient(1).
                    setNom("Durant").
                    setPrenom("Eric").
                    setLocalite("Mons").
                    build();
                System.out.println(cl2);
        } catch (Exception e) {
            System.out.println("erreur "+e);
        }
        
    }
}
