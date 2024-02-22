package demodb;


import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQL12b{
public SQL12b(){
int n = 1;

Scanner sc=new Scanner(System.in);
 Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(0);
        }
        System.out.println("connexion établie");
try {

Statement requete = dbConnect.createStatement(
ResultSet.TYPE_SCROLL_SENSITIVE,
ResultSet.CONCUR_READ_ONLY);
ResultSet resultat= requete.executeQuery(
"select IDCLIENT,NOM,PRENOM from APICLIENT ");
while(resultat.next()) {
System.out.println(n++ +"."+resultat.getInt(1) +
" "+resultat.getString(2)+" "+resultat.getString(3));
}
System.out.println(
"N° du record à atteindre (0 pour stopper) :");

n = sc.nextInt();
sc.skip("\n");
while(n!=0){
resultat.absolute(n);
resultat.refreshRow();
System.out.println(n++ +"."+resultat.getInt(1) +
" "+resultat.getString(2)+" "+resultat.getString(3));
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
    public static void main(String[] args) {
        SQL12b pgm=new SQL12b();
    }
}
