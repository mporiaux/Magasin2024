package demodb;

import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class SQL09 {

    public void demo() {

        Scanner sc = new Scanner(System.in);
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        String query = "update apicomfact set datecommande = ?  where idcommande=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {

            System.out.print("Numero de commande dont la date doit changer :");
            int nf = sc.nextInt();
            System.out.print("Nouvelle date JJ MM AAAA :");
            int j = sc.nextInt();
            int m = sc.nextInt();
            int a = sc.nextInt();
            LocalDate nd = LocalDate.of(a, m, j);
            pstm.setDate(1, java.sql.Date.valueOf(nd));
            pstm.setInt(2, nf);
            int nl = pstm.executeUpdate();
            System.out.println("nombre de lignes mises à jour = " + nl);

        } catch (SQLException e) {
            System.out.println("erreur SQL =" + e);
        }

        DBConnection.closeConnection();
    }

    public static void main(String args[]) {
        SQL09 pgm = new SQL09();
        pgm.demo();
    }
}
