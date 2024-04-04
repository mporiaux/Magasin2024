package mvc.model;

import magasin.metier.Client;
import magasin.metier.ComFact;
import magasin.metier.Produit;
import mvc.observer.Subject;

import java.util.List;

public abstract class  DAOClient extends Subject {
    public abstract Client addClient(Client client);

    public abstract boolean removeClient(Client client);

    public abstract Client updateClient(Client client);

    public abstract Client readClient(int idClient);

    public abstract List<Client> getClients();

    public abstract List<ComFact> factPayees(Client client);

    public abstract List<ComFact> factRetard(Client client);

    public abstract List<ComFact> factNonPayees(Client client);


    public abstract List<ComFact> commandes(Client client);

    public abstract List<Produit> produits(Client client);
}
