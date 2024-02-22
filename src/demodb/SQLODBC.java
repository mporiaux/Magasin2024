package demodb;


import myconnections.ODBCConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class SQLODBC {



public static void main(String[] args) {


    Connection dbConnect=null;

    ODBCConnection odbcc = new ODBCConnection();

    dbConnect = odbcc.getConnection();

    if(dbConnect == null) System.exit(0) ;

    System.out.println("connexion �tablie");

    try{
     Statement stmt = dbConnect.createStatement();

     ResultSet rs = stmt.executeQuery("select * from apiclient");

   //ensemble des lignes r�pondant � la requ�te
  while (rs.next()) {
     String nom = rs.getString("NOM");
      //ou rs.getString(2);
     String prenom = rs.getString("PRENOM");
      //ou rs.getString(3);
     int n = rs.getInt("NUMCLI");;
      //ou rs.getBigDecimal(1,0) etc�
     System.out.println(nom+" "+prenom+" "+n);
    }

   }
catch (SQLException e) {System.out.println("erreur SQL ="+e);
 }
catch (Exception e) {System.out.println("erreur ="+e);
 }

 try {
    dbConnect.close();
    System.out.println("d�connexion reussie");
 }
  catch (Exception e) {System.out.println("erreur ="+e);
  }

}

}
