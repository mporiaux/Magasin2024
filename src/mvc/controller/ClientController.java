package mvc.controller;

import magasin.metier.Client;
import magasin.metier.ComFact;
import magasin.metier.Produit;
import mvc.model.DAOClient;
import mvc.view.ClientAbstractView;


import java.util.List;

public class ClientController {
    private DAOClient model;
    private ClientAbstractView view;

    public ClientController(DAOClient model, ClientAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }
    public List<Client> getAll(){
        return model.getClients();
    }
    public void addClient(Client client) {
       Client cl = model.addClient(client);
       if(cl!=null) view.affMsg("création de :"+cl);
       else view.affMsg("erreur de création");
    }


    public void removeClient(Client client) {
        boolean ok = model.removeClient(client);
        if(ok) view.affMsg("client effacé");
        else view.affMsg("client non effacé");
     }

    public Client selectionner() {
        Client cl = view.selectionner();
        return cl;
    }

    public void updateClient(Client client) {
        Client cl =model.updateClient(client);
        if(cl==null) view.affMsg("mise à jour infrucueuse");
        else view.affMsg("mise à jour effectuée : "+cl);
    }

    public Client search(int idClient) {
        Client cl = model.readClient(idClient);
        return cl;
    }

    public void commandes(Client client) {
     List<ComFact> lcf =   model.commandes(client);
        if(lcf==null || lcf.isEmpty()) view.affMsg("aucune commande trouvée");
        else view.affList(lcf);
    }

    public void factNonPayees(Client client) {
        List<ComFact> lcf =   model.factNonPayees(client);
        if(lcf==null || lcf.isEmpty()) view.affMsg("aucune commande trouvée");
        else view.affList(lcf);
    }

    public void factRetard(Client client) {
        List<ComFact> lcf =   model.factRetard(client);
        if(lcf==null || lcf.isEmpty()) view.affMsg("aucune commande trouvée");
        else view.affList(lcf);
    }

    public void factPayees(Client client) {
        List<ComFact> lcf =  model.factPayees(client);
        if(lcf==null || lcf.isEmpty()) view.affMsg("aucune commande trouvée");
        else view.affList(lcf);
    }

    public void produits(Client client) {
        List<Produit> lpr = model.produits(client);
        if(lpr==null || lpr.isEmpty()) view.affMsg("aucun produit trouvé");
        else view.affList(lpr);
    }
}

