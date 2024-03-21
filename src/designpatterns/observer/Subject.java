/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michel
 */
public abstract class Subject {
   protected List<Observer> myObservers = new ArrayList<>();
  
   public void addObserver(Observer o){
       myObservers.add(o);
   }
   public void removeObserver(Observer o){
       myObservers.remove(o);
   }
   public void notifyObservers(){
       String msg=getNotification();
       for(Observer o : myObservers) o.update(msg);
   }
   
   public abstract String getNotification();
  
}
