package mvc.view;


import magasin.metier.Client;
import magasin.metier.ComFact;
import magasin.metier.Ligne;
import magasin.metier.Produit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;
import static utilitaires.Utilitaire.choixListe;

public class ComfactViewConsole extends ComfactAbstractView {

    private Scanner sc = new Scanner(System.in);

    @Override
    public void affMsg(String msg) {
        System.out.println("information:"+msg);
    }

    @Override
    public void affList(List infos) {
        affListe(infos);
    }

    @Override
    public void menu(){
        update(comfactController.getAll());
        do{
            int ch = choixListe(Arrays.asList("ajout", "retrait", "rechercher", "modifier", "fin"));
            switch(ch){
                case 1: ajouter();
                        break;
                case 2 : retirer();
                        break;
                case 3: rechercher();
                    break;
                case 4 : modifier();
                    break;
                case 5 : return;
            }
        }while(true);
    }


    private void special(ComFact cf) {
        do {
            int ch = choixListe(Arrays.asList("ajouter produit", "modifier produit", "supprimer produit", "lister produits", "menu principal"));

            switch (ch) {
                case 1:
                   ajouterProduit(cf);
                    break;
                case 2:
                   modifierProduit(cf);
                    break;
                case 3:
                    supprimerProduit(cf);
                    break;
                case 4:
                    listerProduits(cf);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);

    }

    public void ajouterProduit(ComFact cf){
        System.out.println("ajout d'une ligne");
        Produit pr = pv.selectionner();
        System.out.print("quantité :");
        int q = sc.nextInt();
        boolean ok = comfactController.addProduit(cf, q,pr);
        if(ok) affMsg("produit ajouté");
        else affMsg("erreur lors de l'ajout du produit");
    }

    private void listerProduits(ComFact cf) {
        System.out.println("produits de la commande");
        List<Ligne> ll = comfactController.getProduits(cf);
        if(ll.isEmpty()) affMsg("aucune ligne pour cette commmande");
        else affList(ll);
    }

    private void supprimerProduit(ComFact cf) {
        System.out.println("suppression d'une ligne");
        Produit pr = pv.selectionner();
        boolean ok = comfactController.supProduit(cf,pr);
        if(ok) affMsg("ligne de produit supprimée");
        else affMsg("ligne de produit non supprimée");
    }

    private void modifierProduit(ComFact cf) {
        System.out.println("modification d'une ligne");
        Produit pr = pv.selectionner();
        System.out.print("quantité :");
        int q = sc.nextInt();
        boolean ok = comfactController.modifProduit(cf,pr,q);
        if(ok) affMsg("mise à jour effectuée");
        else  affMsg("mise à jour infructueuse");
    }



    private void modifier() {
        int nl = choixElt(lcf);
            ComFact cf = lcf.get(nl-1);
            Integer numfact = Integer.parseInt(modifyIfNotBlank("numéro de facture ",cf.getNumfact()+""));
            String date = modifyIfNotBlank("date de facturation ",cf.getDateFacturation()+"");
            LocalDate datefact = !date.equals("null")?LocalDate.parse(date):null;
            date=modifyIfNotBlank("date de payement ",cf.getDatePayement()+"");
            LocalDate datepay = !date.equals("null")?LocalDate.parse(date):null;
            BigDecimal montant = new BigDecimal(modifyIfNotBlank("montant",cf.getMontant().toString())).setScale(2,RoundingMode.HALF_UP);
            char etat  = modifyIfNotBlank("état",""+cf.getEtat()).charAt(0);
            ComFact ncf = new ComFact(cf.getIdcommande(),numfact,cf.getDatecom(),etat,montant,cf.getClient());
            ncf.setDateFacturation(datefact);
            ncf.setDatePayement(datepay);
            cf = comfactController.update(ncf);
            if(cf==null) affMsg("mise à jour infrucueuse");
            else affMsg("mise à jour effectuée : "+cf);
          }

    private void rechercher() {
        System.out.println("idcommande: ");
        int idCommande = sc.nextInt();
        ComFact cf =comfactController.search(idCommande);
        if(cf==null) affMsg("recherche infructueuse");
        else {
            affMsg(cf.toString());
            special(cf);
        }
    }

    private void retirer() {
            int nl = choixElt(lcf);
            ComFact cf = lcf.get(nl-1);
           boolean ok = comfactController.removeComfact(cf);
        if(ok) affMsg("commande effacée");
        else affMsg("commande non effacée");
    }

    private void ajouter() {
            Client cl = clv.selectionner();
            ComFact cf = new ComFact();
            cf.setClient(cl);
            cf=comfactController.addComfact(cf);
        if(cf!=null) affMsg("création de :"+cf);
        else affMsg("erreur de création");
     }

 }



