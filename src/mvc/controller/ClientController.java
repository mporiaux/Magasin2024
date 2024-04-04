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
    public Client addClient(Client client) {
       return  model.addClient(client);
    }

    public boolean removeClient(Client client) {
      return model.removeClient(client);
    }

    public Client updateClient(Client client) {
    return model.updateClient(client);
    }

    public Client search(int idClient) {
          return model.readClient(idClient);
    }

    public List<ComFact> commandes(Client client) {
      return  model.commandes(client);
    }

    public List<ComFact> factNonPayees(Client client) {
       return  model.factNonPayees(client);
    }

    public List<ComFact> factRetard(Client client) {
        return  model.factRetard(client);
    }

    public List<ComFact> factPayees(Client client) {
        return  model.factPayees(client);
    }

    public List<Produit> produits(Client client) {
        return  model.produits(client);
    }
}

