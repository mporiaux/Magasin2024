package demodb;

import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SQL04 {

    public void demo() {

        Scanner sc = new Scanner(System.in);
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        String query = "SELECT NOM,PRENOM,APICLIENT.IDCLIENT,IDCOMMANDE"
                + " FROM APICLIENT INNER JOIN APICOMFACT ON APICLIENT.IDCLIENT = APICOMFACT.IDCLIENT "
                + "WHERE APICLIENT.IDCLIENT = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {

            System.out.print("No de client recherché (0 pour arrêter): ");
            int nr = sc.nextInt();

            while (nr != 0) {
                pstm.setInt(1, nr);
                try (ResultSet rs = pstm.executeQuery()) {
                    boolean trouve = false;
                    while (rs.next()) {
                        trouve = true;
                        String nom = rs.getString("NOM");
                        String prenom = rs.getString("PRENOM");
                        String numCom = "" + rs.getInt("IDCOMMANDE");
                        System.out.println(nr + ":" + nom + " " + prenom + " " + numCom);
                    }
                    if (!trouve) {
                        System.out.println("numero de client inconnu ou pas de commande/facture");
                    }
                }
                System.out.print("No du client recherche(0 pour stopper) :");
                nr = sc.nextInt();
            }

        } catch (SQLException e) {
            System.out.println("erreur SQL =" + e);
        }
         DBConnection.closeConnection();
    }

    public static void main(String[] args) {
        SQL04 pgm = new SQL04();
        pgm.demo();
    }
}
