package demodb;

import myconnections.DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class SQL15 {

   public SQL15() {
 CallableStatement cs=null;
 Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(0);
        }
        System.out.println("connexion établie");
   try {
   String query = "call APITELCLI(?,?)";
CallableStatement statement = dbConnect.prepareCall(query);
statement.setInt(1,1);
statement.registerOutParameter(2, Types.VARCHAR);
//enregistrement du paramètre de sortie en fonction de son type et de son index
statement.execute();
//récupération du résultat en fonction de l'index
String tel = statement.getString(2);
System.out.println("tel du client = "+tel);


        }
 catch (SQLException e) {System.out.println("erreur SQL ="+e);
    }
 catch (Exception e) { System.out.println("erreur ="+e);
   }
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
   public static void main(String args[]) {
   SQL15 pgm = new SQL15();
 }

}
