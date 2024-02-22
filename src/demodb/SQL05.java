package demodb;

import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SQL05 {

    public void demo() {

        Scanner sc = new Scanner(System.in);
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");

        String query1 = "INSERT INTO APICLIENT(nom,prenom,cp,localite,rue,num,tel)"
                + "VALUES(?,?,?,?,?,?,?)";
        String query2 = "select idclient from apiclient where nom=? and prenom=? and tel=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
                PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {

            int numcli = 0;
            System.out.println("Nouveau client :");

            System.out.print("Nom :");
            String nom = sc.nextLine();
            System.out.print("Prenom :");
            String prenom = sc.nextLine();
            System.out.print("Code postal :");
            int cp = sc.nextInt();
            sc.skip("\n");
            System.out.print("Localite :");
            String loc = sc.nextLine();
            System.out.print("Rue :");
            String rue = sc.nextLine();
            System.out.print("Numero :");
            String num = sc.nextLine();
            System.out.print("Telephone :");
            String tel = sc.nextLine();
            pstm1.setString(1, nom);
            pstm1.setString(2, prenom);
            pstm1.setInt(3, cp);
            pstm1.setString(4, loc);
            pstm1.setString(5, rue);
            pstm1.setString(6, num);
            pstm1.setString(7, tel);

            int nl = pstm1.executeUpdate();
            System.out.println(nl + "ligne insérée");
            pstm2.setString(1, nom);
            pstm2.setString(2, prenom);
            pstm2.setString(3, tel);
            try (ResultSet rs = pstm2.executeQuery()) {

                if (rs.next()) {
                    int nc = rs.getInt(1);
                    System.out.println("numero de client =" + nc);

                } else {
                    System.out.println("erreur lors de l'insertion ,numero de client introuvable");
                }

            }
        } catch (SQLException e) {
            System.out.println("erreur SQL =" + e);
        }
         DBConnection.closeConnection();
    }

    public static void main(String[] args) {
        SQL05 pgm = new SQL05();
        pgm.demo();
    }
}
