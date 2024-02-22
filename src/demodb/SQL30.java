package demodb;

import myconnections.DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class SQL30 {
    public SQL30(){
int n = 1;

Scanner sc=new Scanner(System.in);
 Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(0);
        }
        System.out.println("connexion établie");
try {
String query1="{?=call readproduit1(?)}";
CallableStatement  cstm1 = dbConnect.prepareCall(query1);
String query2="{call updateproduit(?,?,?,?)}";
CallableStatement  cstm2 = dbConnect.prepareCall(query2);
System.out.println("numéro recherché :");
String numr =sc.nextLine();
cstm1.setString(2,numr);
cstm1.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR );

cstm1.executeQuery();
ResultSet resultat= (ResultSet) cstm1.getObject(1);

if(resultat.next()) {
    System.out.println("Etat actuel");
    System.out.println(n++ +"."+resultat.getString(1) + 
  " "+resultat.getString(2)+" "+resultat.getDouble(3)+" "+resultat.getInt(4));
    System.out.print("nouvelle description :");
    String nd=sc.nextLine();
    System.out.print("nouveau prix :");
    double np=sc.nextDouble();
    System.out.print("nouveau stock : ");
    int ns=sc.nextInt();
    cstm2.setString(1,numr);
    cstm2.setString(2, nd);
    cstm2.setDouble(3, np);
    cstm2.setInt(4, ns);
    cstm2.executeUpdate();
} 
else System.out.println("numéro de produit inconnu");
}
catch (SQLException e) {
   System.out.println("erreur SQL ="+e);
} 
catch(Exception e) { 
   System.out.println("erreur  ="+e);
}
DBConnection.closeConnection();
}
    public static void main(String[] args) {
        SQL30 pgm=new SQL30();
    }
}
