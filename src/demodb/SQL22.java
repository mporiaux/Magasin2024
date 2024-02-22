package demodb;


import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class SQL22{

 public SQL22(){

     Scanner sc=new Scanner(System.in);
     Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(0);
        }
        System.out.println("connexion établie");
   try {

    int n = 1;

   Statement requete =

    dbConnect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,

      ResultSet.CONCUR_UPDATABLE);

    ResultSet resultat= requete.executeQuery(

     "select IDCLIENT,NOM from APICLIENT FOR UPDATE");

    while(resultat.next()) {

        System.out.println(n++ +"."+resultat.getInt(1) +

        "  "+resultat.getString(2));

    }

    System.out.println(

    "N° du record à atteindre (0 pour stopper) :");

    n = sc.nextInt();

    while(n!=0)

    {

     resultat.absolute(n);

     System.out.println(resultat.getInt(1) +

      "  "+resultat.getString(2));

     System.out.println("effacer (o/n) ? ");

     char rep = sc.next().charAt(0);

     if(rep == 'o')

           {

            resultat.deleteRow();

           }

     System.out.println(

      "N° du record à atteindre (0 pour stopper) :");

     n = sc.nextInt();

    }

  }

  catch (SQLException e) {System.out.println("erreur SQL ="+e);

  }

  catch(Exception e) { e.printStackTrace();}

  DBConnection.closeConnection();

 }



 public static void main(String[] args){

  SQL22 pgm = new SQL22();



 }

}



