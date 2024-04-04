package mvc.model;

import magasin.metier.Client;
import magasin.metier.ComFact;
import magasin.metier.Ligne;
import magasin.metier.Produit;
import myconnections.DBConnection;


import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ComfactModelDB extends DAOComfact {
    private Connection dbConnect;

    public ComfactModelDB(){
        dbConnect = DBConnection.getConnection();
            if (dbConnect == null) {
               System.err.println("erreur de connexion");
                  System.exit(1);
            }

    }

    @Override
    public ComFact addComfact(ComFact comfact) {
        String query1 = "insert into APICOMFACT(datecommande,montant,etat,idclient) values(CURRENT_DATE,0,'c',?)";
        String query2 = "select max(idcommande) from APICOMFACT where idclient = ?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setInt(1,comfact.getClient().getIdclient());
            int n = pstm1.executeUpdate();
              if(n==1){
                pstm2.setInt(1, comfact.getClient().getIdclient());
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int idcommande= rs.getInt(1);
                     comfact.setIdcommande(idcommande);
                    notifyObservers();
                     return comfact;
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
    public boolean removeComfact(ComFact comFact) {
        String query = "delete from APICOMFACT where idcommande = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,comFact.getIdcommande());
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
    public ComFact updateComfact(ComFact comfact) {
        String query = "update APICOMFACT set numfact =?,datefacturation=?,datepayement=?,montant=?,etat=? where idcommande = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,comfact.getNumfact());
            pstm.setDate(2,comfact.getDateFacturation()!=null?Date.valueOf(comfact.getDateFacturation()):null);
            pstm.setDate(3,comfact.getDatePayement()!=null?Date.valueOf(comfact.getDatePayement()):null);
            pstm.setBigDecimal(4,comfact.getMontant());
            pstm.setString(5,""+comfact.getEtat());//car état est un char
            pstm.setInt(6,comfact.getIdcommande());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return readComfact(comfact.getIdcommande());
            else return null;

        } catch (SQLException e) {
           System.err.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public ComFact readComfact(int idComfact) {
        String query = "select * from APICLIENTCOMFACT where idcommande = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idComfact);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                int idclient = rs.getInt(1);
                String nom = rs.getString(2);
                String prenom = rs.getString(3);
                int cp = rs.getInt(4);
                String loc = rs.getString(5);
                String rue= rs.getString(6);
                String num = rs.getString(7);
                String tel = rs.getString(8);
                Client cl = new Client(idclient,nom,prenom,cp,loc,rue,num,tel);
                int idcommande = rs.getInt(9);
                Integer numfact = rs.getInt(10);
                BigDecimal montant = rs.getBigDecimal(11);
                char etat = rs.getString(12).charAt(0);
                LocalDate datecom = rs.getDate(13).toLocalDate();
                Date date = rs.getDate(14);
                LocalDate datefact = date!=null?date.toLocalDate():null;
                date =rs.getDate(15);
                LocalDate datepay = date!=null?date.toLocalDate():null;
                ComFact cf = new ComFact(idcommande,numfact,datecom,etat,montant,cl);

                return  cf;

            }
            else {
                return null;
            }
        } catch (SQLException e) {
           System.err.println("erreur sql :"+e);

            return null;
        }
    }

    @Override
    public List<ComFact> getComfacts() {
        List<ComFact> lcf = new ArrayList<>();
        String query="select * from APICLIENTCOMFACT";
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
                int idcommande = rs.getInt(9);
                Integer numfact = rs.getInt(10);
                BigDecimal montant = rs.getBigDecimal(11);
                char etat = rs.getString(12).charAt(0);
                LocalDate datecom = rs.getDate(13).toLocalDate();
                Date date = rs.getDate(14);
                LocalDate datefact = date!=null?date.toLocalDate():null;
                date =rs.getDate(15);
                LocalDate datepay = date!=null?date.toLocalDate():null;
                ComFact cf = new ComFact(idcommande,numfact,datecom,etat,montant,cl);
                cf.setDateFacturation(datefact);
                cf.setDatePayement(datepay);
                lcf.add(cf);
            }
          return lcf ;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return null;
        }
    }

    @Override
    public boolean addProd(ComFact cf, Produit pr, int q) {
        String query = "insert into  APILIGNE(idcommande,idproduit,quantite,prixachat) values(?,?,?,?)";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,cf.getIdcommande());
            pstm.setInt(2,pr.getIdproduit());
            pstm.setInt(3,q);
            pstm.setBigDecimal(4,pr.getPhtva());
            int n = pstm.executeUpdate();
            if(n!=0) return true;
            else return false;

        } catch (SQLException e) {
             System.err.println("erreur sql :" + e);

            return false;
        }
    }

    @Override
    public boolean updateProd(ComFact cf, Produit pr, int q) {
        String query = "update  APILIGNE set quantite = ? where idcommande = ? AND idproduit = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,q);
            pstm.setInt(2,cf.getIdcommande());
            pstm.setInt(3,pr.getIdproduit());
            int n = pstm.executeUpdate();
            if(n!=0) return true;
            else return false;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public boolean removeProd(ComFact cf, Produit pr) {
        String query = "DELETE FROM  APILIGNE where  idcommande = ? AND idproduit = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,cf.getIdcommande());
            pstm.setInt(2,pr.getIdproduit());
            int n = pstm.executeUpdate();
            if(n!=0) return true;
            else return false;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public List<Ligne> getProduits(ComFact cf) {
        /*

        CREATE OR REPLACE FORCE EDITIONABLE VIEW "LIGNEPROD" ("IDLIGNE", "IDCOMMANDE", "IDPRODUIT", "PRIXACHAT", "QUANTITE", "NUMPROD", "DESCRIPTION", "PHTVA", "STOCK", "STOCKMIN") AS
  SELECT
    L.IDLIGNE,L.IDCOMMANDE,L.IDPRODUIT,L.PRIXACHAT,L.QUANTITE,P.NUMPROD,P.DESCRIPTION,P.PHTVA,P.STOCK,P.STOCKMIN

    FROM APILIGNE L join APIPRODUIT P ON L.IDPRODUIT = P.IDPRODUIT;

         */
        String query = "SELECT * FROM  LIGNEPROD where  idcommande = ?";
        List<Ligne> ll=new ArrayList<>();
        try(PreparedStatement pstm = dbConnect.prepareStatement(query) ){
            pstm.setInt(1,cf.getIdcommande());
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                int idligne = rs.getInt("idligne");
                int idcommande = rs.getInt("idcommande"); //pas nécessaire en POO car navigabilité
                int idproduit = rs.getInt("idproduit");
                int quantite = rs.getInt("quantite");
                BigDecimal prixachat = rs.getBigDecimal("prixachat");
                String numprod = rs.getString("numprod");
                String description = rs.getString("description");
                BigDecimal phtva = rs.getBigDecimal("phtva");
                int stock= rs.getInt("stock");
                int stockmin=rs.getInt("stockmin");
                Produit pr = new Produit(idproduit,numprod,description,phtva,stock,stockmin);
                Ligne l = new Ligne(idligne,pr,quantite,prixachat);
                ll.add(l);
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
        }
        return ll;
    }

    @Override
    public List getNotification() {
        return getComfacts();
    }
}
