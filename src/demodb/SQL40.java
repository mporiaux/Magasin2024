package demodb;


import myconnections.DBConnection;

import java.sql.*;


public class SQL40{

 public SQL40(){

     

      Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(0);
        }
        System.out.println("connexion établie");

     try {

      Statement stmt;

     stmt = dbConnect.createStatement();

     String query = "select * from CLIENT";

     ResultSet rs = stmt.executeQuery(query);

   

 

     //Récupérer les metadata de la table CLIENTS

 

     ResultSetMetaData md = rs.getMetaData();

     int numColumns = md.getColumnCount();

     System.out.println("Nombre de colonnes = " + numColumns);

    for (int i=1; i<=numColumns; i++) {

      System.out.print ("Nom de la colonne = " + md.getColumnName (i));

      System.out.print (" Numero de Type = " + md.getColumnType (i) );

      System.out.println (" Nom du Type = " + md.getColumnTypeName (i));

 

     }

   }

    catch (SQLException e) {

        System.out.println("Erreur SQL =  " + e.getMessage());

    }

    catch (Exception e) {System.out.println("erreur ="+e);

   } 

 

 

DBConnection.closeConnection();

 

 }

     

 public static void main(String[] args){

  SQL40 pgm = new SQL40();  

     

 }   

     

     

     

}

