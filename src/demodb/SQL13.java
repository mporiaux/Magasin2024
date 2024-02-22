package demodb;


import myconnections.DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

 

public class SQL13{

 public SQL13(){
     CallableStatement cs=null;
   Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(0);
        }
        System.out.println("connexion établie");

      try {
          
      
       Scanner sc= new Scanner(System.in)  ;
       System.out.print("Code Produit: ");
       String codepro=sc.nextLine();
        System.out.println("Description:" );
       String descr=sc.nextLine();
        System.out.println("PHTVA :");
       float phtva=sc.nextFloat();
       sc.skip("\n");
       cs = dbConnect.prepareCall(
       "call APINOUVPROD(?,?,?)");
       cs.setString(1,codepro);
       cs.setString(2,descr);
       cs.setFloat(3,phtva);
       cs.executeUpdate();
         }
    catch (SQLException e) {System.out.println("erreur SQL ="+e);

 } 
    catch(Exception e) { System.out.println("Exception"+e);}

   finally {
            //finalement fermer les ressources
          
            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (SQLException e) {
                System.out.println("erreur de fermeture de statement " + e);
            }
              try {
                if (dbConnect != null) {
                    dbConnect.close();
                }
            } catch (SQLException e) {
                System.out.println("erreur de fermeture de connexion " + e);
            }

      }

  }

      

 public static void main(String[] args){

  SQL13 pgm = new SQL13();     

      

 }    

}

