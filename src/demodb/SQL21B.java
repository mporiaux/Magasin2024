package demodb;


import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class SQL21B{

 public SQL21B(){

   Scanner sc=new Scanner(System.in);
   Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(0);
        }
        System.out.println("connexion établie");

   try {

    int n =1;

    Statement requete =

     dbConnect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
     ResultSet.CONCUR_UPDATABLE);
     ResultSet resultat= requete.executeQuery(
"select IDCLIENT,NOM,PRENOM,CP,LOCALITE,RUE,NUM,TEL FROM" +
 " APICLIENT FOR UPDATE");

    while(resultat.next()) {
    System.out.println(n++ +"."+resultat.getInt(1) +
    "  "+resultat.getString(2)+" "+resultat.getString(3));
   }
    System.out.println("Ajouter un client (o/n) ? ");
     while( sc.nextLine().charAt(0)=='o'){
             System.out.println("Entrez le nom :");
             String nouvNom = sc.nextLine();
             System.out.println("Entrez le prenom :");
             String nouvPrenom = sc.nextLine();
             System.out.println("Entrez le code postal :");
             int nouvCP = sc.nextInt();
             sc.skip("\n");
             System.out.println("Entrez la localite :");
             String nouvLoc = sc.nextLine();
             System.out.println("Entrez la rue :");
             String nouvRue = sc.nextLine();
             System.out.println("Entrez le numero de rue :");
             String nouvNr = sc.nextLine();
             System.out.println("Entrez le Tel :");
             String nouvTel = sc.nextLine();
             resultat.moveToInsertRow();
             resultat.updateString(2,nouvNom);
             resultat.updateString(3,nouvPrenom);
             resultat.updateInt(4,nouvCP);
             resultat.updateString(5,nouvLoc);
             resultat.updateString(6,nouvRue);
             resultat.updateString(7,nouvNr);
             resultat.updateString(8,nouvTel);
             resultat.insertRow();
             System.out.println(
               "Nouvel etat du recordset :\n");
            resultat.close();
               resultat= requete.executeQuery(
"select IDCLIENT,NOM,PRENOM,CP,LOCALITE,RUE,NUM,TEL FROM" +
 " CLIENT FOR UPDATE");
             n=1;
             while(resultat.next()) {
               System.out.println(
              n++ +"."+resultat.getInt(1) + "  "+resultat.getString(2));
             }

            System.out.println("Ajouter un client (o/n) ? ");
          }
   }

    catch (SQLException e) {

    System.out.println("erreur SQL ="+e);

    }



    catch(Exception e) { e.printStackTrace();}

    DBConnection.closeConnection();

  }



 public static void main(String[] args){

  SQL21B pgm = new SQL21B();



 }

}





