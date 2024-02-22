package demodb;

import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SQL07 {

    public void demo() {


        Scanner sc = new Scanner(System.in);
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        String query = "update apiproduit set phtva = ? where numprod = ?";

        try( PreparedStatement pstm = dbConnect.prepareStatement(query)) {

            System.out.println("code produit (STOP pour terminer) :");

            String codePro = sc.nextLine();

            while (!codePro.equals("STOP")) {

                System.out.println("PRIX HTVA :");
                float phtva = sc.nextFloat();
                sc.skip("\n");
                pstm.setFloat(1, phtva);
                pstm.setString(2, codePro);
                int nl = pstm.executeUpdate();
                if (nl == 0) System.out.println("produit introuvable");
                System.out.println(nl + "ligne mise à jour");
                System.out.print("code produit (STOP pour terminer) ");
                codePro = sc.nextLine();
            }

        }
        catch (SQLException e) {
             System.out.println("erreur SQL =" + e);
            }
          DBConnection.closeConnection();
       }

    public static void main(String[] args) {

        SQL07 pgm = new SQL07();
        pgm.demo();

    }

}
