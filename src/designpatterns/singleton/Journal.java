/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatterns.singleton;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michel
 */
public class Journal {
    private List<String> lignes;
    private static Journal journal;
    private Journal(){
        lignes=new ArrayList<>();
    }
    
    public static Journal getInstance(){
        if(journal==null) journal=new Journal();
        return journal;
    }

    public List<String> getLignes() {
        return lignes;
    }
   
      
}
