package demodb;

import myconnections.ODBCConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQLODBC2 {


	public static void main(String[] args) {
		new SQLODBC2();
	}


	public SQLODBC2() {
		int n = 1;
    Scanner sc=new Scanner(System.in);
    Connection dbConnect=null;
    ODBCConnection DB2c = new ODBCConnection();
    dbConnect = DB2c.getConnection();
    if(dbConnect == null) System.exit(0) ;
    System.out.println("connexion �tablie");
    try {
      Statement requete = dbConnect.createStatement(
      ResultSet.TYPE_SCROLL_SENSITIVE,
      ResultSet.CONCUR_UPDATABLE);
      ResultSet resultat= requete.executeQuery(
      "select NUMCLI,NOM,PRENOM from APICLIENT FOR UPDATE ");
      while(resultat.next()) {
       System.out.println(n++ +"."+resultat.getInt(1) +
       "  "+resultat.getString(2)+" "+resultat.getString(3));
     }
    System.out.print(
    "N� du record � atteindre (0 pour stopper) :");

    n = sc.nextInt();
    while(n!=0){
     resultat.absolute(n);
     System.out.println(n++ +"."+resultat.getInt(1) +
     "  "+resultat.getString(2)+" "+resultat.getString(3));
     System.out.print("mettre � jour(o/n) ? ");
     char rep = sc.next().charAt(0);
     if(rep == 'o'){
        System.out.print("Entrez le nouveau nom :");
        String nouvNom = sc.nextLine();
        resultat.updateString(2,nouvNom);
        resultat.updateRow();
     }
     System.out.print(
      "N� du record � atteindre (0 pour stopper) :");
     n = sc.nextInt();
    }
  }
  catch (SQLException e) {
       System.out.println("erreur SQL ="+e);
  }
 catch(Exception e) { e.printStackTrace();}
 finally {
    try { dbConnect.close();}
    catch(SQLException e) {e.printStackTrace();}
 }

	}
}
