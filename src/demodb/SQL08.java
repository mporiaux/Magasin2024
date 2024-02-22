package demodb;


import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SQL08{
 public void demo(){

    Scanner sc=new Scanner(System.in);
   Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
    try(PreparedStatement pstm = dbConnect.prepareStatement("UPDATE APIPRODUIT SET PHTVA = ? WHERE NUMPROD = ?")) {

     System.out.print("code produit (STOP pour terminer) :");
     String codePro = sc.nextLine();
     while(!codePro.equals("STOP")){
      System.out.print("PRIX HTVA :");
      double phtva = sc.nextFloat();
      sc.skip("\n");
      BigDecimal bgdc = (new BigDecimal(phtva)).setScale(2,BigDecimal.ROUND_UP);
      pstm.setBigDecimal(1,bgdc);
      pstm.setString(2,codePro);
      int nl = pstm.executeUpdate();
      System.out.println(nl + "ligne insérée");
      System.out.print("code produit (STOP pour terminer) :");
      codePro = sc.nextLine();
    }
  }
  catch (SQLException e) {System.out.println("erreur SQL ="+e);
  }
   DBConnection.closeConnection();
 }
 public static void main(String[] args){
  SQL08 pgm = new SQL08();
  pgm.demo();
 }
}

