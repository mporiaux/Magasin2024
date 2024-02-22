package demodb;


import myconnections.DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


public class SQL13b {

 public SQL13b(){

   Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(0);
        }
        System.out.println("connexion établie");

      try( CallableStatement  cs = dbConnect.prepareCall("call APINOUVPROD(?,?,?)")) {
          
      
       Scanner sc= new Scanner(System.in)  ;
       System.out.print("Code Produit: ");
       String codepro=sc.nextLine();
        System.out.println("Description:" );
       String descr=sc.nextLine();
        System.out.println("PHTVA :");
       float phtva=sc.nextFloat();
       sc.skip("\n");

       cs.setString(1,codepro);
       cs.setString(2,descr);
       cs.setFloat(3,phtva);
       cs.executeUpdate();
         }
    catch (SQLException e) {System.out.println("erreur SQL ="+e);

 } 
    catch(Exception e) { System.out.println("Exception"+e);}

     DBConnection.closeConnection();
  }

      

 public static void main(String[] args){

  SQL13b pgm = new SQL13b();

      

 }    

}

