
package demodb;

import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class SQL20 {
  public SQL20(){
int n = 1;

Scanner sc=new Scanner(System.in);
 Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(0);
        }
        System.out.println("connexion établie");
try {
String query="select numprod,description,phtva,stock from apiproduit where description like ? ";
PreparedStatement pstm = dbConnect.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
System.out.println("description recherchée : ");
String descr = sc.nextLine();
pstm.setString(1, "%"+descr+"%");
ResultSet resultat= pstm.executeQuery();
while(resultat.next()) {
System.out.println(n++ +"."+resultat.getString(1) +
" "+resultat.getString(2)+" "+resultat.getDouble(3)+" "+resultat.getInt(4));
}
System.out.println(
"N° du record à atteindre (0 pour stopper) :");

n = sc.nextInt();
sc.skip("\n");
while(n!=0){
resultat.absolute(n);
resultat.refreshRow();
System.out.println(resultat.getString(1) +
" "+resultat.getString(2)+" "+resultat.getDouble(3)+" "+resultat.getInt(4));
 System.out.print("nouvelle description :");
    String nd=sc.nextLine();
    System.out.print("nouveau prix :");
    double np=sc.nextDouble();
    System.out.print("nouveau stock : ");
    int ns=sc.nextInt();
    resultat.updateString(2, nd);
    resultat.updateDouble(3, np);
    resultat.updateInt(4, ns);
    resultat.updateRow();
System.out.println("N° du record à atteindre (0 pour stopper) :");
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
    public static void main(String[] args) {
        SQL20 pgm=new SQL20();
    }
}
