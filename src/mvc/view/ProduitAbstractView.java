package mvc.view;

import magasin.metier.Client;
import magasin.metier.ComFact;
import magasin.metier.Produit;
import mvc.controller.ClientController;
import mvc.controller.ProduitController;
import mvc.observer.Observer;

import java.util.List;

public abstract class ProduitAbstractView implements Observer {

    protected ProduitController produitController;
    protected List<Produit> lp;

    public void  setController(ProduitController produitController){
        this.produitController=produitController;
    }
    public abstract void affMsg(String msg);

    public abstract Produit selectionner();

    public abstract void menu();

    public abstract void affList(List l);

    @Override
    public void update(List lp) {
        this.lp = lp;
        affList(lp);
    }

}
