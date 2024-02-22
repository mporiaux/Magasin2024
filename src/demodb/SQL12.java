package demodb;


import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQL12{
 public SQL12(){
    int n = 1;
     Scanner sc=new Scanner(System.in);
     Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(0);
        }
        System.out.println("connexion établie");
    try {
      Statement requete = dbConnect.createStatement(
     ResultSet.TYPE_SCROLL_SENSITIVE
     // ResultSet.TYPE_SCROLL_INSENSITIVE
              ,
      ResultSet.CONCUR_UPDATABLE
     //  ResultSet.CONCUR_READ_ONLY
      );
      ResultSet resultat= requete.executeQuery(
     "select IDCLIENT,NOM,PRENOM from APICLIENT"
     // "select IDCLIENT,NOM,PRENOM from CLIENT FOR UPDATE "
      );
      while(resultat.next()) {
       System.out.println(n++ +"."+resultat.getInt(1) +
       "  "+resultat.getString(2)+" "+resultat.getString(3));
     }
    System.out.println(
    "N° du record à atteindre (0 pour stopper) :");

    n = sc.nextInt();
    sc.skip("\n");
    while(n!=0){
     resultat.absolute(n);
     resultat.refreshRow();//uniquement si ResultSet.TYPE_SCROLL_SENSITIVE
     System.out.println(n++ +"."+resultat.getInt(1) +
     "  "+resultat.getString(2)+" "+resultat.getString(3));
     System.out.println("mettre à jour(o/n) ? ");
     char rep = sc.nextLine().charAt(0);

     if(rep == 'o'){
        System.out.println("Entrez le nouveau nom :");
        String nouvNom = sc.nextLine();
        resultat.updateString(2,nouvNom);
        resultat.updateRow();
     }
     System.out.println(
      "N° du record à atteindre (0 pour stopper) :");
     n = sc.nextInt();
     sc.skip("\n");
    }
  }
  catch (SQLException e) {
       System.out.println("erreur SQL ="+e);
  }
 catch(Exception e) { e.printStackTrace();}
 DBConnection.closeConnection();
}

 public static void main(String[] args){
  SQL12 pgm = new SQL12();
 }
}
