package demodb;

import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQL01c {

    public void demo() {
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        String query = "select * from APICLIENT";
        try (
                Statement stmt = dbConnect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery(query)) {

            rs.afterLast();
            while (rs.previous()) {
                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");
                String n = "" + rs.getInt("IDCLIENT");
                System.out.println(nom + " " + prenom + " " + n);
            }

            System.out.println("-----------------------------------");
            rs.beforeFirst();
            Scanner sc = new Scanner(System.in);
            while (rs.next()) {

                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");
                String n = "" + rs.getInt("IDCLIENT");
                System.out.println(nom + " " + prenom + " " + n);
                if (rs.getRow() % 3 == 0) {
                    System.out.print("suite==>");
                    sc.nextLine();
                }
            }
            System.out.println("------------------------------------------");
            rs.absolute(1);
            int ch;
            do {
                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");
                String n = "" + rs.getInt("IDCLIENT");
                System.out.println(nom + " " + prenom + " " + n);
                System.out.print("1.|<- 2.<- 3.-> 4.->| 5.end");
                ch = sc.nextInt();
                switch (ch) {
                    case 1:
                        rs.absolute(1);
                        break;
                    case 2:
                        if (!rs.isFirst()) {
                            rs.previous();
                        }
                        break;
                    case 3:
                        if (!rs.isLast()) {
                            rs.next();
                        }
                        break;
                    case 4:
                        rs.absolute(-1);
                        break;

                }
            } while (ch != 5);

        } catch (SQLException e) {
            System.out.println("erreur SQL =" + e);
        }

        DBConnection.closeConnection();
    }

    public static void main(String args[]) {
        SQL01c pgm = new SQL01c();
        pgm.demo();
    }
}
