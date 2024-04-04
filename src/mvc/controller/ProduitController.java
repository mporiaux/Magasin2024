package mvc.controller;

import magasin.metier.Produit;
import mvc.model.DAOProduit;
import mvc.view.ProduitAbstractView;


import java.util.List;

public class ProduitController {
    private DAOProduit model;
    private ProduitAbstractView view;



    public ProduitController(DAOProduit model, ProduitAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }


    public List<Produit> getAll(){
        return model.getProduits();
    }
    public void addProduit(Produit produit) {
       Produit pr = model.addProduit(produit);
       if(pr!=null) view.affMsg("création de :"+pr);
       else view.affMsg("erreur de création");
    }


    public void removeProduit(Produit pr) {
        boolean ok = model.removeProduit(pr);
        if(ok) view.affMsg("produit effacé");
        else view.affMsg("produit non effacé");
    }

    public void update(Produit produit) {
        Produit pr =model.updateProduit(produit);
        if(pr==null) view.affMsg("mise à jour infrucueuse");
        else view.affMsg("mise à jour effectuée : "+pr);
    }

    public Produit search(int idProduit) {
        Produit pr = model.readProduit(idProduit);
        return pr;
    }

}

