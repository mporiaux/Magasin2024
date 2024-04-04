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

    public void removeComfact(ComFact comfact) {
        boolean ok = model.removeComfact(comfact);
        if(ok) view.affMsg("commande effacée");
        else view.affMsg("commande non effacée");
        List<ComFact> comfacts = model.getComfacts();
    }

    public void update(ComFact comfact) {
        ComFact cf =model.updateComfact(comfact);
        if(cf==null) view.affMsg("mise à jour infrucueuse");
        else view.affMsg("mise à jour effectuée : "+cf);
    }

    public ComFact search(int idCommande) {
        ComFact cf  = model.readComfact(idCommande);
       return cf;
    }

    public void addProduit(ComFact cf,int q,Produit pr){
        boolean ok =  model.addProd(cf,pr,q);
       if(ok) view.affMsg("produit ajouté");
       else view.affMsg("erreur lors de l'ajout du produit");
    }

    public void modifProduit(ComFact cf,Produit pr,int q){
        model.updateProd(cf,pr,q);
    }

    public void supProduit(ComFact cf,Produit pr){
        model.removeProd(cf,pr);
    }

    public void getProduits(ComFact cf){
        List<Ligne> ll = model.getProduits(cf);
        if(ll.isEmpty()) view.affMsg("aucune ligne pour cette commmande");
        else view.affList(ll);
    }

    public void addComfact(ComFact cf) {
        cf=model.addComfact(cf);
        if(cf!=null) view.affMsg("création de :"+cf);
        else view.affMsg("erreur de création");
    }
}

