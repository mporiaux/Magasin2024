package mvc.view;

import magasin.metier.Client;
import static utilitaires.Utilitaire.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class ClientViewConsole extends ClientAbstractView {
    private Scanner sc = new Scanner(System.in);

    @Override
    public void affMsg(String msg) {
        System.out.println("information:" + msg);
    }

   @Override
    public void menu() {
        update(clientController.getAll());
        do {

            int ch = choixListe(Arrays.asList("ajout", "retrait", "rechercher", "modifier", "fin"));
            switch (ch) {
                case 1:
                    ajouter();
                    break;
                case 2:
                    retirer();
                    break;
                case 3:
                    rechercher();
                    break;
                case 4:
                    modifier();
                    break;
                case 5:
                    return;
            }
        } while (true);
    }

    @Override
    public void affList(List l) {
        affListe(l);
    }

    private void special(Client cl) {

        do {
            int ch = choixListe(Arrays.asList("commandes en cours", "factures non payees", "factures en retard", "factures payees", "produits achetés", "menu principal"));
            if(ch==6) return;
            List l =   switch (ch) {
                case 1 ->  clientController.commandes(cl);

                case 2 ->  clientController.factNonPayees(cl);

                case 3 ->   clientController.factRetard(cl);

                case 4 ->   clientController.factPayees(cl);

                case 5  ->   clientController.produits(cl);
                default -> null;
              };
            if(l==null || l.isEmpty()) affMsg("aucun élément trouvée");
            else affList(l);
        } while (true);
    }



    private void modifier() {
        int nl = choixElt(lc) - 1;
        Client client = lc.get(nl);
        String nom = modifyIfNotBlank("nom", client.getNom());
        String prenom = modifyIfNotBlank("prénom", client.getPrenom());
        int cp = Integer.parseInt(modifyIfNotBlank("cp", "" + client.getCp()));
        String localite = modifyIfNotBlank("localité", client.getLocalite());
        String rue = modifyIfNotBlank("rue", client.getRue());
        String num = modifyIfNotBlank("num", client.getNum());
        String tel = modifyIfNotBlank("nom", client.getTel());
        Client cl =clientController.updateClient(new Client(client.getIdclient(), nom, prenom, cp, localite, rue, num, tel));
        if(cl==null) affMsg("mise à jour infructueuse");
        else affMsg("mise à jour effectuée : "+cl);
    }

    private void rechercher() {
        System.out.println("idclient : ");
        int idClient = sc.nextInt();
        Client cl = clientController.search(idClient);
        if(cl==null) affMsg("recherche infructueuse");
        else {
            affMsg(cl.toString());
            special(cl);
        }
    }

    private void retirer() {
        int nl = choixElt(lc)-1;
        Client client = lc.get(nl);
        boolean ok = clientController.removeClient(client);
        if(ok) affMsg("client effacé");
        else affMsg("client non effacé");
    }

    private void ajouter() {
        System.out.print("nom : ");
        String nom = sc.nextLine();
        System.out.print("prenom: ");
        String prenom = sc.nextLine();
        System.out.print("cp: ");
        int cp = Integer.parseInt(sc.nextLine());
        System.out.print("localité : ");
        String loc = sc.nextLine();
        System.out.print("rue: ");
        String rue = sc.nextLine();
        System.out.print("numéro: ");
        String num = sc.nextLine();
        System.out.print("téléphone: ");
        String tel = sc.nextLine();
        Client cl = clientController.addClient(new Client(0, nom, prenom, cp, loc, rue, num, tel));
        if(cl!=null) affMsg("création de :"+cl);
        else affMsg("erreur de création");
    }

    @Override
    public Client selectionner() {
        update(clientController.getAll());
        int nl = choixListe(lc);
        Client client = lc.get(nl - 1);
        return client;
    }
}

