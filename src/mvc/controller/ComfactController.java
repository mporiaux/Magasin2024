package mvc.controller;

import magasin.metier.ComFact;
import magasin.metier.Ligne;
import magasin.metier.Produit;
import mvc.model.DAOComfact;
import mvc.view.ComfactAbstractView;

import java.util.List;

public class ComfactController {
    private DAOComfact model;
    private ComfactAbstractView view;


    public ComfactController(DAOComfact model, ComfactAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<ComFact> getAll(){
        return model.getComfacts();
    }

    public boolean removeComfact(ComFact comfact) {
        return  model.removeComfact(comfact);
    }

    public ComFact update(ComFact comfact) {
         return model.updateComfact(comfact);
    }

    public ComFact search(int idCommande) {
       return  model.readComfact(idCommande);
    }

    public boolean addProduit(ComFact cf,int q,Produit pr){
       return  model.addProd(cf,pr,q);
    }

    public boolean modifProduit(ComFact cf,Produit pr,int q){
        return model.updateProd(cf,pr,q);
    }

    public boolean supProduit(ComFact cf,Produit pr){

        return  model.removeProd(cf,pr);
    }

    public List<Ligne> getProduits(ComFact cf){
        return model.getProduits(cf);
    }

    public ComFact addComfact(ComFact cf) {
        return model.addComfact(cf);
    }
}

