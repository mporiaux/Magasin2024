package mvc;

import mvc.model.*;
import mvc.controller.ClientController;
import mvc.controller.ComfactController;
import mvc.controller.ProduitController;
import mvc.view.*;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestMagasin {
    private DAOClient cm;
    private DAOComfact cfm;
    private DAOProduit pm;
    private ClientController cc;
    private ComfactController cfc;
    private ProduitController pc;
    private ClientAbstractView cv;
    private ComfactAbstractView cfv;
    private ProduitAbstractView pv;


    public void gestion(){
       // cm = new ClientModelDB();
        cm = new ClientModelHyb();
        cfm = new ComfactModelDB();
        pm=new ProduitModelDB();

        cv = new ClientViewConsole();
        cfv = new ComfactViewConsole();
        pv =  new ProduitViewConsole();

        cc = new ClientController(cm,cv);//création et injection de dépendance
        cfc = new ComfactController(cfm,cfv);
        pc= new ProduitController(pm,pv);

        cfv.setClientView(cv);
        cfv.setProduitView(pv);
        cm.addObserver(cv);

        List<String> loptions = Arrays.asList("clients","commandes","produits","fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch){
                case 1: cv.menu();
                        break;
                case 2 : cfv.menu();
                        break;
                case 3: pv.menu();
                        break;
                case 4: System.exit(0);
            }
        }while(true);
    }
    public static void main(String[] args) {
      GestMagasin gm = new GestMagasin();
       gm.gestion();
    }
}
