package demodb;

import myconnections.DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL14b {

 public SQL14b(){

  Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(0);
        }
        System.out.println("connexion établie");
     try( CallableStatement   cs = dbConnect.prepareCall("{?=call apilisteclis}")) {

        cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
        cs.executeQuery();
       ResultSet rs=(ResultSet) cs.getObject(1);
      while(rs.next()){
      String nom = rs.getString("NOM");
      String prenom = rs.getString("PRENOM");
      System.out.println(nom+"  "+prenom);
      }
   }
    catch (SQLException e) {System.out.println("erreur SQL ="+e);
 }
   catch(Exception e) { System.out.println("Exception"+e);}
     DBConnection.closeConnection();
 }
 

 public static void main(String[] args){
  SQL14b pgm = new SQL14b();
  }
}



