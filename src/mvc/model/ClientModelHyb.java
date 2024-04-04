package mvc.model;

import magasin.metier.Client;
import magasin.metier.ComFact;
import magasin.metier.Produit;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientModelHyb extends DAOClient{

    protected Connection dbConnect;
    public  ClientModelHyb() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");
            System.exit(1);
        }
    }

    @Override
    public Client addClient(Client client) {
        String query1 = "insert into APICLIENT(nom,prenom,cp,localite,rue,num,tel) values(?,?,?,?,?,?,?)";
        String query2 = "select idclient from APICLIENT where nom= ? and prenom =? and tel =?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,client.getNom());
            pstm1.setString(2,client.getPrenom());
            pstm1.setInt(3,client.getCp());
            pstm1.setString(4,client.getLocalite());
            pstm1.setString(5,client.getRue());
            pstm1.setString(6,client.getNum());
            pstm1.setString(7,client.getTel());
            int n = pstm1.executeUpdate();
            if(n==1){
                pstm2.setString(1,client.getNom());
                pstm2.setString(2,client.getPrenom());
                pstm2.setString(3,client.getTel());
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int idclient= rs.getInt(1);
                    client.setIdclient(idclient);
                    notifyObservers();
                    return client;
                }
                else {

                    System.err.println("record introuvable");
                    return null;
                }
            }
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return null;
        }
    }

    @Override
    public boolean removeClient(Client client) {
        String query = "delete from APICLIENT where idclient = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,client.getIdclient());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return true;
            else return false;

        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return false;
        }
    }

    @Override
    public Client updateClient(Client client) {
        String query = "update APICLIENT set nom =?,prenom=?,cp=?,localite=?,rue=?,num=?,tel=? where idclient = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1,client.getNom());
            pstm.setString(2,client.getPrenom());
            pstm.setInt(3,client.getCp());
            pstm.setString(4,client.getLocalite());
            pstm.setString(5,client.getRue());
            pstm.setString(6,client.getNum());
            pstm.setString(7,client.getTel());
            pstm.setInt(8,client.getIdclient());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return readClient(client.getIdclient());
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);

            return null;
        }
    }

    @Override
    public Client readClient(int idClient) {
        /*
        CREATE OR REPLACE FORCE EDITIONABLE VIEW "ORA30"."APICLIENTCOMFACTV2" ("IDCLIENT", "NOM", "PRENOM", "CP", "LOCALITE", "RUE", "NUM", "TEL", "IDCOMMANDE", "NUMFACT", "MONTANT", "ETAT", "DATECOMMANDE", "DATEFACTURATION", "DATEPAYEMENT") AS
  SELECT
 apiclient.idclient as idclient,
 apiclient.nom as nom,
 apiclient.prenom as prenom,
 apiclient.cp as cp,
 apiclient.localite as localite,
 apiclient.rue as rue,
 apiclient.num as num,
 apiclient.tel as tel,
 apicomfact.idcommande as idcommande,
 apicomfact.numfact as numfact,
 apicomfact.montant as montant,
 apicomfact.etat as etat,
 apicomfact.datecommande as datecommande,
 apicomfact.datefacturation as datefacturation,
 apicomfact.datepayement as datepayement
FROM
apiclient left join apicomfact on apiclient.idclient = apicomfact.idclient
order by apiclient.idclient
;

 */

        String query = "select * from APICLIENTCOMFACTV2 where idclient = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idClient);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String nom = rs.getString(2);
                String prenom = rs.getString(3);
                int cp = rs.getInt(4);
                String loc = rs.getString(5);
                String rue= rs.getString(6);
                String num = rs.getString(7);
                String tel = rs.getString(8);
                Client cl = new Client(idClient,nom,prenom,cp,loc,rue,num,tel);
                List<ComFact> lc = new ArrayList<>();
                int numcommande = rs.getInt(9);
                if(numcommande!=0){
                    do {
                        numcommande = rs.getInt(9);
                        Integer numfact = rs.getInt(10);
                        BigDecimal montant = rs.getBigDecimal(11);
                        char etat = rs.getString(12).charAt(0);
                        LocalDate dateCom = rs.getDate(13).toLocalDate();
                        Date date = rs.getDate(14);
                        LocalDate datefact = date!=null?date.toLocalDate():null;
                        date =rs.getDate(15);
                        LocalDate datepay = date!=null?date.toLocalDate():null;
                        ComFact cf = new ComFact(numcommande, numfact, dateCom, etat, montant, cl);
                        cf.setDateFacturation(datefact);
                        cf.setDatePayement(datepay);
                        lc.add(cf);
                    }while(rs.next());
                }
                 cl.setComFacts(lc);
                return cl;
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            // System.err.println("erreur sql :"+e);
            return null;
        }
    }

    @Override
    public List<Client> getClients() {
        List<Client> lc = new ArrayList<>();
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
                lc.add(cl);
            }
            return lc;
        } catch (SQLException e) {
            //System.err.println("erreur sql :"+e);

            return null;
        }
    }
    @Override
    public List<ComFact>  factPayees(Client client) {
       return client.factPayees();
    }
    @Override
    public List<ComFact> factRetard(Client client) {
      return client.factRetard();
    }

    @Override
    public List<ComFact> factNonPayees(Client client) {
       return client.factNonPayees();
    }


    @Override
    public List<ComFact> commandes(Client client) {
        return client.getComFacts();
    }

    @Override
    public List<Produit> produits(Client client) {
        List<Produit> lpr = new ArrayList<>();
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
                Produit pr = new Produit(idprod,numprod,descr,new BigDecimal(0),0,0);
                lpr.add(pr);
            }
            if(!trouve) System.out.println("aucune commande trouvée");
        } catch (SQLException e) {

            return null;
        }
        return lpr;
    }

    @Override
    public List getNotification() {
        return null;
    }
}
