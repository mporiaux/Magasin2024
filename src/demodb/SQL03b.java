package demodb;

import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SQL03b {

    public void demo() {

        Scanner sc = new Scanner(System.in);
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        try (PreparedStatement pstm = dbConnect.prepareStatement("SELECT * FROM APICLIENT WHERE NOM = ? AND PRENOM = ? ")) {
            System.out.print("Nom du client recherché :");
            String nomrech = sc.nextLine();
            System.out.print("Prénom du client recherché : ");
            String pnomrech = sc.nextLine();
            pstm.setString(1, nomrech);
            pstm.setString(2, pnomrech);
            boolean trouve = false;
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    trouve = true;
                    String nom = rs.getString("NOM");
                    String prenom = rs.getString("PRENOM");
                    String loc = rs.getString("LOCALITE");
                    System.out.println(nom + " " + prenom + " " + loc);
                }
                if (!trouve) {
                    System.out.println("client inconnu");
                }

            }

        }
        catch (SQLException e) {
            System.out.println("erreur SQL =" + e);
        }
        DBConnection.closeConnection();

    }

    public static void main(String[] args) {
        SQL03b pgm = new SQL03b();
        pgm.demo();

    }
}
