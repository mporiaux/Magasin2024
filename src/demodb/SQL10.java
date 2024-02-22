package demodb;

import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SQL10 {

    public void demo() {

        Scanner sc = new Scanner(System.in);
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");

        try (
                PreparedStatement insDet = dbConnect.prepareStatement(
                        "INSERT INTO APILIGNE(IDCOMMANDE,IDPRODUIT,QUANTITE,PRIXACHAT) VALUES(?,?,?,?)");
                PreparedStatement insCom = dbConnect.prepareStatement(
                        "INSERT INTO APICOMFACT(IDCLIENT,ETAT,MONTANT,DATECOMMANDE) VALUES(?,'c',0,CURRENT_DATE)");
                PreparedStatement rechProd = dbConnect.prepareStatement(
                        "SELECT IDPRODUIT,PHTVA FROM APIPRODUIT WHERE NUMPROD = ?");
                PreparedStatement majCom = dbConnect.prepareStatement(
                        "UPDATE APICOMFACT SET MONTANT = ? WHERE IDCOMMANDE= ?");
                PreparedStatement rechNumCom = dbConnect.prepareStatement(
                        "select max(IDCOMMANDE) from APICOMFACT WHERE IDCLIENT= ?");
                PreparedStatement majStock = dbConnect.prepareStatement(
                        "update apiproduit set STOCK=STOCK - ? where NUMPROD=?")) {
            dbConnect.setAutoCommit(false);

            System.out.println("Nouvel achat :");

            System.out.println("N° de client  : ");

            int numcli = sc.nextInt();
            sc.skip("\n");
            insCom.setInt(1, numcli);
            int nlFact = insCom.executeUpdate();
            rechNumCom.setInt(1, numcli);
            try (ResultSet rs = rechNumCom.executeQuery()) {
                rs.next();
                int numCom = rs.getInt(1);
                System.out.println("commande  N° " + numCom);
                float montant = 0;
                System.out.println("code produit (STOP pour terminer) :");
                String codePro = sc.nextLine();
                int nlDet = 0;
                while (!codePro.equals("STOP")) {
                    rechProd.setString(1, codePro);
                    try (ResultSet rs2 = rechProd.executeQuery()) {

                        if (!rs2.next()) {
                            System.out.println("Code Produit " + codePro + " inconnu!");
                        } else {
                            int idproduit = rs2.getInt("IDPRODUIT");
                            float phtva = rs2.getFloat("PHTVA");
                            System.out.println("Quantité :");
                            int quant = sc.nextInt();
                            sc.skip("\n");
                            insDet.setInt(1, numCom);
                            insDet.setInt(2, idproduit);
                            insDet.setInt(3, quant);
                            insDet.setFloat(4, phtva);
                            int nl = insDet.executeUpdate();
                            majStock.setInt(1, quant);
                            majStock.setString(2, codePro);
                            majStock.executeUpdate();
                            nlDet += nl;
                            montant += phtva * quant;
                        }
                    }
                    System.out.println("code produit (STOP pour terminer) :");
                    codePro = sc.nextLine();
                }
                if (nlDet > 0) {
                    majCom.setFloat(1, montant);
                    majCom.setInt(2, numCom);
                    majCom.executeUpdate();
                    System.out.println(nlDet + " lignes inserees dans la table LIGNE");
                    System.out.println(nlFact + " lignes inserees dans la table COMFACT");
                    dbConnect.commit();
                } else {
                    throw new SQLException("aucune ligne inseree");
                }
            }
        } catch (SQLException e) {
            System.out.println("erreur :" + e);
            System.out.println("la transaction va être annulee");
            try {
                dbConnect.rollback();
            } catch (SQLException e2) {
                System.out.println("erreur de rollback :" + e2);
            }
        }
        DBConnection.closeConnection();
    }

    public static void main(String[] args) {

        SQL10 pgm = new SQL10();
        pgm.demo();

    }

}
