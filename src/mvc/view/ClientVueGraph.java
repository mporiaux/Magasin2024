/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;


import magasin.metier.Client;
import magasin.metier.ComFact;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

import static utilitaires.Utilitaire.choixListe;

/**
 *
 * @author Michel
 */
public class ClientVueGraph extends ClientAbstractView {



    public Client create() {

        JTextField tfnom = new JTextField();
        JTextField tfprenom = new JTextField();
        JTextField tfcp = new JTextField();
        JTextField tfloc = new JTextField();
        JTextField tfrue = new JTextField();
        JTextField tfnum = new JTextField();
        JTextField tftel = new JTextField();
        Object[] message = {
                "nom: ", tfnom,
                "prénom:", tfprenom,
                "cp:", tfcp,
                "localité:", tfloc,
                "rue:", tfrue,
                "num:", tfnum,
                "tel", tftel
        };

        int option = JOptionPane.showConfirmDialog(null, message, "nouveau client", JOptionPane.DEFAULT_OPTION);
            String nom = tfnom.getText().toString();
            String prenom = tfprenom.getText().toString();
            Integer cp = Integer.parseInt(tfcp.getText().toString());
            String loc = tfloc.getText().toString();
            String rue = tfrue.getText().toString();
            String num = tfnum.getText().toString();
            String tel = tftel.getText().toString();
            Client newcl = new Client(0,nom, prenom, cp, loc, rue, num, tel);
            return newcl;
    }
  

    public void display(Client cl) {
        displayMsg(cl.toString());
        if (!cl.getComFacts().isEmpty()) {
            String rep;
            do {
                rep = getMsg("Afficher ses commandes (o/n) ");
            } while (!rep.equalsIgnoreCase("o") && !rep.equalsIgnoreCase("n"));

            if (rep.equalsIgnoreCase("o")) {
                int i=0;
                StringBuffer sb= new StringBuffer(200);
                for (ComFact cf : cl.getComFacts()) sb.append((++i)+"."+cf+"\n");
                displayMsg(sb.toString());
            }
        }
    }

    private String getMsg(String msg) {
        int option = JOptionPane.showConfirmDialog(null, msg, "question", JOptionPane.DEFAULT_OPTION);
        return ""+option;
    }

    private void displayMsg(String msg) {
        JOptionPane.showConfirmDialog(null, msg, "info", JOptionPane.DEFAULT_OPTION);
    }


    public Client update(Client cl) {
       
        do {
            String ch = getMsg("1.changement de téléphone\n2.fin");
            switch (ch) {
                case "1":
                    String ntel = getMsg("nouveau numéro de téléphone :");
                    cl.setTel(ntel);//on devrait tester que cela ne crée pas de doublons nom-prénom-tel
                    break;
                case "2":
                    return cl;
                default:
                    displayMsg("choix invalide");
            }

        } while (true);
    }



    public Integer read() {
        String ns = getMsg("numéro de client : ");
        int n = Integer.parseInt(ns);
        return n;
    }


    public void affAll(List<Client> lc) {
        int i =0;
        StringBuffer sb= new StringBuffer(200);

        for(Client cl:lc) sb.append((++i)+"."+cl+"\n");
        displayMsg(sb.toString());
    }


    public void affLobj(List lobj) {
        int i =0;
        for(Object o:lobj){
            displayMsg((++i)+"."+o.toString());
        }
    }

    @Override
    public void affMsg(String msg) {

    }

    @Override
    public Client selectionner() {
        return null;
    }

    @Override

        public void menu() {
            update(clientController.getAll());
            do {

                int ch = choixListe(Arrays.asList("ajout", "fin"));
                switch (ch) {
                    case 1:
                        ajouter();
                        break;
                    case 2 :
                        return;
                }
            } while (true);
        }



    private void ajouter() {
        Client cl = create();
        clientController.addClient(cl);
    }

    @Override
    public void affList(List l) {
          displayMsg(l.toString());
    }
}
