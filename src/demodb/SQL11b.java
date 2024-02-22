package demodb;

import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SQL11b{

 public SQL11b(){

    Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(0);
        }
        System.out.println("connexion établie");

    try {
     Scanner sc=new Scanner(System.in);

     PreparedStatement affVille = dbConnect.prepareStatement(
      "SELECT LOCALITE FROM APICLIENT "+
      " WHERE IDCLIENT = ? ");

     System.out.println("N° de client (0 pour terminer) :");
     int numcli = sc.nextInt();
     while(numcli!=0){
     affVille.setInt(1,numcli);
       ResultSet rs=affVille.executeQuery();
       if(rs.next()){
         String loc = rs.getString("LOCALITE");
         System.out.println("LOCALITE :"+loc );

         }
       else  System.out.println("Client introuvable ");
     System.out.println("numero de client(0 pour terminer) :");
   numcli = sc.nextInt();
  }//fin du while
 } //fin du try principal
catch (SQLException e) {
           System.out.println("erreur "+e);
        }//fin du catch SQL
catch (Exception e) {
  System.out.println("erreur ="+e);
  e.printStackTrace();
}

   DBConnection.closeConnection();
}


 public static void main(String[] args){

  SQL11b pgm = new SQL11b();

 }

}


