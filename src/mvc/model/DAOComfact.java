package mvc.model;

import magasin.metier.ComFact;
import magasin.metier.Ligne;
import magasin.metier.Produit;
import mvc.observer.Subject;

import java.util.List;

public abstract class DAOComfact extends Subject {
    public abstract ComFact addComfact(ComFact comFact);

    public abstract boolean removeComfact(ComFact comFact);

    public abstract ComFact updateComfact(ComFact comFact);

    public abstract ComFact readComfact(int idComfact);

    public abstract List<ComFact> getComfacts();
    public abstract boolean addProd(ComFact cf, Produit pr, int q);

    public abstract boolean updateProd(ComFact cf, Produit pr, int q);

    public abstract boolean removeProd(ComFact cf, Produit pr);

    public abstract  List<Ligne> getProduits(ComFact cf);

}
