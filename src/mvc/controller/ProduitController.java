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
    public Produit addProduit(Produit produit) {
       return  model.addProduit(produit);
    }

    public boolean removeProduit(Produit pr) {
        return model.removeProduit(pr);
    }

    public Produit update(Produit produit) {
        return model.updateProduit(produit);

    }

    public Produit search(int idProduit) {
       return  model.readProduit(idProduit);
    }

}

