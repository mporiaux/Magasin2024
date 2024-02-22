package demodb;

import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SQL11c{

 public SQL11c(){

     Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(0);
        }
        System.out.println("connexion établie");
    try {
     Scanner sc=new Scanner(System.in);
     dbConnect.setAutoCommit(false);
     PreparedStatement stm = dbConnect.prepareStatement(
      "UPDATE APIPRODUIT SET " +
      "STOCK = ? WHERE NUMPROD = ?");
     PreparedStatement affStock = dbConnect.prepareStatement(
      "SELECT STOCK FROM APIPRODUIT "+
      " WHERE NUMPROD = ? FOR UPDATE WAIT 10 ");//ou nowait
     System.out.println("N° de produit (STOP pour terminer) :");
     String numprod = sc.next();
     sc.skip("\n");
     while(!numprod.equals("STOP")){
      try {
       affStock.setString(1,numprod);
       ResultSet rs=affStock.executeQuery();
       if(rs.next()){
         String ancStock = rs.getString("STOCK");
         System.out.println("ANCIEN STOCK:"+ ancStock);
         System.out.println("NOUVEAU STOCK:");
         int stock= sc.nextInt();
         stm.setInt(1,stock);
         stm.setString(2,numprod);
         int nl = stm.executeUpdate();
         System.out.println(nl + "lignes modifiées");
         System.out.println("Confirmez-vous la mise à jour (o/n) ?");
         sc.skip("\n");
         char rep = sc.nextLine().charAt(0);
         if(rep == 'o') dbConnect.commit();
         else {
           try{
              dbConnect.rollback();
           }
           catch(Exception e){
              System.out.println("erreur lors de l'annulation : "+e);
           }
         }
        }
        else  System.out.println("Produit introuvable ");
      } //fin du try interne

      catch (SQLException e) {

           if(e.getErrorCode()!=30006){
             System.out.println("erreur SQL ="+e);
                                 break;
           }
           else System.out.println(
           "Record verrouillé par une autre application ");
        }//fin du catch SQL

   System.out.println("numero de produit(STOP pour terminer) :");
   numprod = sc.next();
   sc.skip("\n");
     }//fin du while
 } //fin du try principal

catch (Exception e) {
  System.out.println("erreur ="+e);
  e.printStackTrace();
}

DBConnection.closeConnection();
}


 public static void main(String[] args){

  SQL11c pgm = new SQL11c();

 }

}


