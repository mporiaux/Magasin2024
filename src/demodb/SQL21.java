package demodb;


import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class SQL21{

 public SQL21(){

   Scanner sc=new Scanner(System.in);
    Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(0);
        }
        System.out.println("connexion établie");
   try {
   int n =1;
    Statement requete = dbConnect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
     ResultSet.CONCUR_UPDATABLE);
    String query="select NUMPROD,DESCRIPTION,PHTVA FROM APIPRODUIT FOR UPDATE";
    ResultSet resultat= requete.executeQuery(query);

    while(resultat.next()) {
    System.out.println(n++ +"."+resultat.getString(1) +
    "  "+resultat.getString(2)+" "+resultat.getFloat(3));
   }
    System.out.println("Ajouter un produit (o/n) ? ");
     while( sc.nextLine().charAt(0)=='o'){
             System.out.println("Entrez le code produit :");
             String nouvCode = sc.nextLine();
             System.out.println("Entrez la description :");
             String nouvDescr = sc.nextLine();
             System.out.println("Entrez le prix :");
             float nouvPrix = sc.nextFloat();
             sc.skip("\n");

             resultat.moveToInsertRow();
             resultat.updateString(1,nouvCode);
             resultat.updateString(2,nouvDescr);
             resultat.updateFloat(3,nouvPrix);

             resultat.insertRow();
             System.out.println(
               "Nouvel etat du recordset :\n");
             resultat.close();
            resultat= requete.executeQuery(query);


              n=1;
              while(resultat.next()) {
               System.out.println(
              n++ +"."+resultat.getString(1) + "  "+resultat.getString(2)+" "+resultat.getFloat(3));
             }
            System.out.println("Ajouter un produit (o/n) ? ");
          }
   }

    catch (SQLException e) {

    System.out.println("erreur SQL ="+e);

    }



    catch(Exception e) { e.printStackTrace();}

   DBConnection.closeConnection();

  }



 public static void main(String[] args){

  SQL21 pgm = new SQL21();



 }

}





