package demodb;

import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL01b {

    public void demo(){

        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        String query = "select * from APICLIENT";
        try (Statement stmt = dbConnect.createStatement();
                ResultSet rs = stmt.executeQuery(query);) {

            while (rs.next()) {
                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");
                String n = "" + rs.getInt("IDCLIENT");
                System.out.println(nom + " " + prenom + " " + n);
            }
        } catch (SQLException e) {
            System.out.println("erreur SQL " + e);
        }

        DBConnection.closeConnection();
    }

    public static void main(String[] args) {
        SQL01b pgm = new SQL01b();
        pgm.demo();
    }
}
