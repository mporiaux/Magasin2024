package demodb;

import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQL03 {

    public void demo() {

        Scanner sc = new Scanner(System.in);
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        System.out.println("Nom du client recherche : ");
        String nomrech = sc.nextLine();
        System.out.println("prenom du client recheche : ");
        String pnomrech = sc.nextLine();
        String query="SELECT *  FROM APICLIENT WHERE NOM = '"+ nomrech + "' AND PRENOM = '" + pnomrech + "'";
        System.out.println(query);
        try (Statement stmt = dbConnect.createStatement();
             ResultSet rs = stmt.executeQuery(query);
                        ) {
            boolean trouve = false;
            while (rs.next()) {
                trouve = true;
                String localite = rs.getString("LOCALITE");
                int n = rs.getInt("IDCLIENT");
                System.out.println(nomrech + " " + pnomrech + ":" + n + " " + localite);
            }
            if (!trouve) {
                System.out.println("client inconnu");
            }

        } catch (SQLException e) {
            System.out.println("erreur SQL =" + e);
        }
        DBConnection.closeConnection();
    }

    public static void main(String[] args) {
        SQL03 pgm = new SQL03();
        pgm.demo();
    }
}
