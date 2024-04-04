package mvc.model;


import magasin.metier.Produit;
import myconnections.DBConnection;


import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProduitModelDB extends DAOProduit{

    protected Connection dbConnect;

    public ProduitModelDB(){
        dbConnect = DBConnection.getConnection();
            if (dbConnect == null) {
               System.err.println("erreur de connexion");

                System.exit(1);
            }

    }

    @Override
    public Produit addProduit(Produit produit) {
        String query1 = "insert into APIPRODUIT(numprod,phtva,description,stock,stockmin) values(?,?,?,?,?)";
        String query2 = "select idproduit from APIPRODUIT where numprod= ?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,produit.getNumprod());
            pstm1.setBigDecimal(2,produit.getPhtva());
            pstm1.setString(3,produit.getDescription());
            pstm1.setInt(4,produit.getStock());
            pstm1.setInt(5,produit.getStockMin());
            int n = pstm1.executeUpdate();
              if(n==1){
                pstm2.setString(1,produit.getNumprod());
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int idproduit= rs.getInt(1);
                     produit.setIdproduit(idproduit);
                    notifyObservers();
                     return produit;
                }
                else {

                  System.err.println("record introuvable");
                    return null;
                }
            }
              else return null;

        } catch (SQLException e) {
            //System.err.println("erreur sql :"+e);

            return null;
        }
    }

    @Override
    public boolean removeProduit(Produit produit) {
        String query = "delete from APIPRODUIT where idproduit = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,produit.getIdproduit());
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
    public Produit updateProduit(Produit produit) {
        String query = "update APIPRODUIT set numprod =?,phtva=?,description=?,stock=?,stockmin=? where idproduit = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1,produit.getNumprod());
            pstm.setBigDecimal(2,produit.getPhtva());
            pstm.setString(3,produit.getDescription());
            pstm.setInt(4,produit.getStock());
            pstm.setInt(5,produit.getStockMin());
            pstm.setInt(6,produit.getIdproduit());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return readProduit(produit.getIdproduit());
            else return null;

        } catch (SQLException e) {
           System.err.println("erreur sql :" + e);

            return null;
        }
    }

    @Override
    public Produit readProduit(int idProduit) {
        String query = "select * from APIPRODUIT where idproduit = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idProduit);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String numprod = rs.getString(2);
                BigDecimal phtva = rs.getBigDecimal(3);
                String description = rs.getString(4);
                int stock = rs.getInt(5);
                int stockMin = rs.getInt(6) ;
                Produit pr = new Produit(idProduit,numprod,description,phtva,stock,stockMin);
                return  pr;

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
    public List<Produit> getProduits() {
        List<Produit> lp = new ArrayList<>();
        String query="select * from APIPRODUIT";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int idProduit = rs.getInt(1);
                String numprod = rs.getString(2);
                BigDecimal phtva = rs.getBigDecimal(3);
                String description = rs.getString(4);
                int stock = rs.getInt(5);
                int stockMin = rs.getInt(6) ;
                Produit pr = new Produit(idProduit,numprod,description,phtva,stock,stockMin);
                lp.add(pr);
            }
          return lp;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return null;
        }
    }

    @Override
    public List getNotification() {
        return getProduits();
    }
}
