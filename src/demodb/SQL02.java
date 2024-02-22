package demodb;

import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

public class SQL02 {

    public void demo() {
        Scanner sc = new Scanner(System.in);
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        String query = "select DATECOMMANDE from APICOMFACT";
        try (Statement stmt = dbConnect.createStatement();
                ResultSet rs = stmt.executeQuery(query);) {

            System.out.println("delai en jours :");
            int delai = sc.nextInt();

            LocalDate auj = LocalDate.now();
            LocalDate dlim = auj.minusDays(delai);
            while (rs.next()) {
                LocalDate df = rs.getDate(1).toLocalDate();
                if (df.isAfter(dlim)) {
                    System.out.println(df);
                }
            }
        } catch (SQLException e) {
            System.out.println("erreur SQL =" + e);
          }
          DBConnection.closeConnection();
    }

    public static void main(String args[]) {
        SQL02 pgm = new SQL02();
        pgm.demo();
    }
}
