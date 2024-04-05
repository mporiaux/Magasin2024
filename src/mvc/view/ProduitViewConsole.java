package mvc.view;

import magasin.metier.Produit;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class ProduitViewConsole extends ProduitAbstractView {
    private Scanner sc = new Scanner(System.in);


    @Override
    public void affMsg(String msg) {
        System.out.println("information:"+msg);
    }

    @Override
    public void affList(List infos) {
        affListe(infos);
    }


    public void menu(){
        update(produitController.getAll());
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



    private void modifier() {
        int nl = choixElt(lp);

            Produit pr = lp.get(nl-1);
            String numprod= modifyIfNotBlank("numéro de produit",pr.getNumprod());
            String description = modifyIfNotBlank("description",pr.getDescription());
            BigDecimal phtva = new BigDecimal(modifyIfNotBlank("prix HTVA",""+pr.getPhtva()));
            int stock = Integer.parseInt(modifyIfNotBlank("stock",""+pr.getStock()));
            int stockMin= Integer.parseInt(modifyIfNotBlank("stock min",""+pr.getStockMin()));
           Produit prmaj =  produitController.update(new Produit(pr.getIdproduit(),numprod,description,phtva,stock,stockMin));
           if(prmaj==null) affMsg("mise à jour infructueuse");
           else affMsg("mise à jour effectuée : "+prmaj);
    }

    private void rechercher() {
        System.out.println("idProduit : ");
        int idProduit = sc.nextInt();
        produitController.search(idProduit);
    }

    private void retirer() {

    int nl = choixElt(lp);
    Produit pr = lp.get(nl-1);
      boolean ok = produitController.removeProduit(pr);
        if(ok) affMsg("produit effacé");
        else affMsg("produit non effacé");
    }

    private void ajouter() {
        System.out.print("numéro de produit : ");
        String numprod= sc.nextLine();
        System.out.print("description : ");
        String description = sc.nextLine();
        System.out.print("prix HTVA :");
        BigDecimal phtva = new BigDecimal(sc.nextLine());
        System.out.print("stock : ");
        int stock = Integer.parseInt(sc.nextLine());
        System.out.print("stock min : ");
        int stockMin= Integer.parseInt(sc.nextLine());
        Produit pr = produitController.addProduit(new Produit(0,numprod,description,phtva,stock,stockMin)) ;
        if(pr!=null) affMsg("création de :"+pr);
        else affMsg("erreur de création");
     }

    @Override
    public Produit selectionner(){
         update(produitController.getAll());
         int nl =  choixListe(lp);
         Produit pr = lp.get(nl-1);
            return pr;
        }
}

