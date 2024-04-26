package demodb;

import magasin.metier.Client;
import magasin.metier.ComFact;
import magasin.metier.Produit;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author Michel
 */
public class GestClients {

    private Scanner sc = new Scanner(System.in);
    private Connection dbConnect;

    public void gestion() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        do {
            System.out.println("1.ajout\n2.recherche\n3.modification\n4.suppression\n5.tous\n6.fin");
            System.out.println("choix : ");
            int ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    ajout();
                    break;
                case 2:
                    recherche();
                    break;
                case 3:
                    modification();
                    break;
                case 4:
                    suppression();
                    break;
                case 5:
                   tous();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);

    }




   /* public void ajout() {

        System.out.print("nom :");
        String nom = sc.nextLine();
        System.out.print("prénom :");
        String prenom = sc.nextLine();
        System.out.print("cp :");
        Integer cp = sc.nextInt();
        sc.skip("\n");
        System.out.print("localite :");
        String loc = sc.nextLine();
        System.out.print("rue :");
        String rue = sc.nextLine();
        System.out.print("numéro :");
        String num = sc.nextLine();
        System.out.print("tel :");
        String tel = sc.nextLine();
        String query1 = "insert into APICLIENT(nom,prenom,cp,localite,rue,num,tel) values(?,?,?,?,?,?,?)";
        String query2 = "select idclient from APICLIENT where nom= ? and prenom =? and tel =?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
            ){
            pstm1.setString(1,nom);
            pstm1.setString(2,prenom);
            pstm1.setInt(3,cp);
            pstm1.setString(4,loc);
            pstm1.setString(5,rue);
            pstm1.setString(6,num);
            pstm1.setString(7,tel);
            int n = pstm1.executeUpdate();
            System.out.println(n+" ligne insérée");
            if(n==1){
                pstm2.setString(1,nom);
                pstm2.setString(2,prenom);
                pstm2.setString(3,tel);
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int idclient= rs.getInt(1);
                    System.out.println("idclient = "+idclient);
                }
                else System.out.println("record introuvable");
            }

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
      }*/
    public void ajout() {

        System.out.print("nom :");
        String nom = sc.nextLine();
        System.out.print("prénom :");
        String prenom = sc.nextLine();
        System.out.print("cp :");
        Integer cp = sc.nextInt();
        sc.skip("\n");
        System.out.print("localite :");
        String loc = sc.nextLine();
        System.out.print("rue :");
        String rue = sc.nextLine();
        System.out.print("numéro :");
        String num = sc.nextLine();
        System.out.print("tel :");
        String tel = sc.nextLine();
        String query1 = "call apicreatecli(?,?,?,?,?,?,?,?)";

        try(CallableStatement cs= dbConnect.prepareCall(query1);

        ){
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setString(2,nom);
            cs.setString(3,prenom);
            cs.setInt(4,cp);
            cs.setString(5,loc);
            cs.setString(6,rue);
            cs.setString(7,num);
            cs.setString(8,tel);
            cs.executeUpdate();
            int numcli = cs.getInt(1);
            System.out.println("numéro de client = "+numcli);


        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }


    public void recherche() {

        System.out.println("id du client recherché ");
        int idrech = sc.nextInt();
        String query = "select * from APICLIENT where idclient = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idrech);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String nom = rs.getString(2);
                String prenom = rs.getString(3);
                int cp = rs.getInt(4);
                String loc = rs.getString(5);
                String rue= rs.getString(6);
                String num = rs.getString(7);
                String tel = rs.getString(8);
                Client cl = new Client(idrech,nom,prenom,cp,loc,rue,num,tel);
                System.out.println(cl);
                opSpeciales(cl);
            }
            else System.out.println("record introuvable");
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }

    }

    public void modification() {
        System.out.println("id du client recherché ");
        int idrech = sc.nextInt();
        sc.skip("\n");
        System.out.println("nouveau téléphone ");
        String ntel = sc.nextLine();
        String query = "update APICLIENT set tel=? where idclient = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1,ntel);
            pstm.setInt(2,idrech);
            int n = pstm.executeUpdate();
            if(n!=0) System.out.println(n+ "ligne mise à jour");
            else System.out.println("record introuvable");

        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
        }
    }
    public void suppression() {
        System.out.println("id du client recherché ");
        int idrech = sc.nextInt();
        String query = "delete from APICLIENT where idclient = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idrech);
            int n = pstm.executeUpdate();
            if(n!=0) System.out.println(n+ "ligne supprimée");
            else System.out.println("record introuvable");

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }

    }

    private void tous() {
        String query="select * from APICLIENT";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int idclient = rs.getInt(1);
                String nom = rs.getString(2);
                String prenom = rs.getString(3);
                int cp = rs.getInt(4);
                String loc = rs.getString(5);
                String rue= rs.getString(6);
                String num = rs.getString(7);
                String tel = rs.getString(8);
                Client cl = new Client(idclient,nom,prenom,cp,loc,rue,num,tel);
                System.out.println(cl);
            }

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }
    private void opSpeciales(Client client) {
        do {
            System.out.println("1.commandes en cours\n2.factures non payees\n3.factures en retard\n4.factures payees\n5.produits achetés\n6.menu principal");
            System.out.println("choix : ");
            int ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    commandes(client);
                    break;
                case 2:
                    factNonPayees(client);
                    break;
                case 3:
                   factRetard(client);
                    break;
                case 4:
                    factPayees(client);
                    break;
                case 5:
                    produits(client);
                    break;

                case 6: return;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);

    }




    private void factPayees(Client client) {
        String query = "select * from APICOMFACT where idclient = ? AND  DATEPAYEMENT IS NOT NULL order by IDCOMMANDE ";
        rechercheCommandes(client,query);
    }
    private void factRetard(Client client) {
        String query = "select * from APICOMFACT where idclient = ?  AND  DATEPAYEMENT IS NULL AND DATEFACTURATION + 30 < SYSDATE order by IDCOMMANDE";
        rechercheCommandes(client,query);
    }

    private void factNonPayees(Client client) {
        String query = "select * from APICOMFACT where idclient = ? AND DATEFACTURATION IS NOT NULL AND DATEPAYEMENT IS NULL order by IDCOMMANDE ";
        rechercheCommandes(client,query);
    }


    private void commandes(Client client) {

        String query = "select * from APICOMFACT where idclient = ? order by IDCOMMANDE";
        rechercheCommandes(client,query);
    }
    private void rechercheCommandes(Client client,String query){
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,client.getIdclient());
            ResultSet rs = pstm.executeQuery();
            boolean trouve= false;
            while(rs.next()){
                trouve=true;
                int nc = rs.getInt(1);
                Integer nf = rs.getInt(2);//permet d'éviter une erreur si n° de facture nul
                LocalDate dateCom = rs.getDate(3)==null? null: rs.getDate(3).toLocalDate();
                LocalDate dateFact = rs.getDate(4)==null? null: rs.getDate(3).toLocalDate();
                LocalDate datePay= rs.getDate(5)==null? null: rs.getDate(3).toLocalDate();
                BigDecimal montant = rs.getBigDecimal(6);
                char etat = rs.getString(7).charAt(0);
                ComFact cf = new ComFact(nc,nf,dateCom,etat,montant,client) ;
                cf.setDateFacturation(dateFact);
                cf.setDatePayement(datePay);
                System.out.println(cf);
            }
            if(!trouve) System.out.println("aucune commande trouvée");
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }
    private void produits(Client client) {
        String query="select * from prodcli where idclient = ? order by numprod";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,client.getIdclient());
            ResultSet rs = pstm.executeQuery();
            boolean trouve= false;
            while(rs.next()){
                trouve=true;
                int idprod = rs.getInt(2);
                String numprod = rs.getString(3);
                String descr = rs.getString(4);
                Produit pr = new Produit(idprod,numprod,descr,null,0,0);
                System.out.println(pr);
            }
            if(!trouve) System.out.println("aucune commande trouvée");
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }

    public static void main(String[] args) {

        GestClients g = new GestClients();
        g.gestion();
    }

}
