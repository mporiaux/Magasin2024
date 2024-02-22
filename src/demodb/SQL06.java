package demodb;

import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SQL06 {

    public void demo() {

        Scanner sc = new Scanner(System.in);
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");

        System.out.print("N° de commande à effacer : ");
        int nf = sc.nextInt();
        String query1 = "DELETE FROM APILIGNE WHERE IDCOMMANDE = ?";
        String query2 = "DELETE FROM APICOMFACT WHERE IDCOMMANDE = ?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {

            pstm1.setInt(1, nf);
            int nl = pstm1.executeUpdate();
            System.out.println(nl + " lignes effacees Dans la table LIGNE");

            pstm2.setInt(1, nf);
            nl = pstm2.executeUpdate();
            System.out.println(nl + " lignes effacees Dans la table COMFACT");

        } catch (SQLException e) {
            System.out.println("erreur " + e);
        }
         DBConnection.closeConnection();
    }

    public static void main(String[] args) {

        SQL06 pgm = new SQL06();
        pgm.demo();

    }

}
