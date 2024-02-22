package demodb;

import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SQL11 {

    public SQL11() {
       PreparedStatement stm=null,affVille=null;

       Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(0);
        }
        System.out.println("connexion établie");
        try {
            Scanner sc = new Scanner(System.in);
            dbConnect.setAutoCommit(false);
            stm = dbConnect.prepareStatement(
                    "UPDATE APICLIENT SET "
                    + "LOCALITE = ? WHERE IDCLIENT = ?");
            affVille = dbConnect.prepareStatement(
                    "SELECT LOCALITE FROM APICLIENT "
                    + " WHERE IDCLIENT = ? FOR UPDATE WAIT 20 ");//ou nowait
            System.out.println("N° de client (0 pour terminer) :");
            int numcli = sc.nextInt();
            sc.skip("\n");
            while (numcli != 0) {
                try {
                    affVille.setInt(1, numcli);
                    ResultSet rs = affVille.executeQuery();
                    if (rs.next()) {
                        String ancLoc = rs.getString("LOCALITE");
                        System.out.println("ANCIENNE LOCALITE :" + ancLoc);
                        System.out.println("NOUVELLE LOCALITE :");
                        String loc = sc.nextLine();
                        stm.setString(1, loc);
                        stm.setInt(2, numcli);
                        int nl = stm.executeUpdate();
                        System.out.println(nl + "ligne insérée");
                        System.out.println("Confirmez-vous la mise à jour (o/n) ?");
                        char rep = sc.nextLine().charAt(0);
                        if (rep == 'o') {
                            dbConnect.commit();
                        } else {
                            try {
                                dbConnect.rollback();
                            } catch (Exception e) {
                                System.out.println("erreur lors de l'annulation : " + e);
                            }
                        }
                    } else {
                        System.out.println("Client introuvable ");
                    }
                } //fin du try interne
                catch (SQLException e) {

                    if (e.getErrorCode() != 30006) {
                        System.out.println("erreur SQL =" + e);
                        break;
                    } else {
                        System.out.println(
                                "Record verrouillé par une autre application ");
                    }
                }//fin du catch SQL

                System.out.println("numero de client(0 pour terminer) :");
                numcli = sc.nextInt();
                sc.skip("\n");
            }//fin du while
        } //fin du try principal
        catch (Exception e) {
            System.out.println("erreur =" + e);

        }

        finally {
            //finalement fermer les ressources

            try {
                if (stm!= null) {
                    stm.close();
                }
            } catch (SQLException e) {
                System.out.println("erreur de fermeture de statement " + e);
            }
            try {
                if (affVille != null) {
                    affVille.close();
                }
            } catch (SQLException e) {
                System.out.println("erreur de fermeture de statement " + e);
            }

            try {
                if (dbConnect != null) {
                    dbConnect.close();
                }
            } catch (SQLException e) {
                System.out.println("erreur de fermeture de connexion " + e);
            }
        }
    }

    public static void main(String[] args) {

        SQL11 pgm = new SQL11();

    }

}
