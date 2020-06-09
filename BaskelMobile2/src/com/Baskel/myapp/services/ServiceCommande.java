/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baskel.myapp.services;

import com.Baskel.myapp.entities.Commande;
import com.Baskel.myapp.entities.Details_commande;
import com.Baskel.myapp.entities.Panier;
import com.Baskel.myapp.entities.Produits;
import com.Baskel.myapp.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hp
 */
public class ServiceCommande {

    int idClient = 20;
    public ArrayList<Commande> commandes;
    public ArrayList<Details_commande> D_commandes;
    public ArrayList<Produits> produit;
    public static ServiceCommande instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCommande() {
        req = new ConnectionRequest();
    }

    public static ServiceCommande getInstance() {
        if (instance == null) {
            instance = new ServiceCommande();
        }
        return instance;
    }

    /* Afficher Commande */
    public ArrayList<Commande> parseCommandes(String jsonText) throws IOException {
        try {
            commandes = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                
                Commande t = new Commande();

                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);

                t.setIdClient(((int) Float.parseFloat(obj.get("idClient").toString())));

                float totalPrix = Float.parseFloat(obj.get("TotalePrix").toString());
                t.setTOtalPrix((double) totalPrix);

                float nbrProduit = Float.parseFloat(obj.get("nbrProduit").toString());
                t.setNbrProduit((int) nbrProduit);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = obj.get("date").toString();
                try {
                    //System.out.println(sdf.parse(date));
                    t.setDate(sdf.parse(date));

                } catch (ParseException ex) {
                    //Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                }
                t.setNbrProduit((int) nbrProduit);

                commandes.add(t);
                
            }

        } catch (IOException ex) {

        }
        return commandes;
    }

    public ArrayList<Commande> getAllCommandes() {
        String url = Statics.BASE_URL + "/ReadAllCommandeJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    commandes = parseCommandes(new String(req.getResponseData()));
                } catch (Exception ex) {
                    //  Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                }

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commandes;
    }

    /* Afficher Details d une commande */
    public ArrayList<Details_commande> parseDetailsCommande(String jsonText) throws IOException {
        try {
            D_commandes = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Details_commande t = new Details_commande();

               float idP = Float.parseFloat(obj.get("idProduit").toString());

                t.setIdProduit((int) idP);

                t.setNomProduit(obj.get("nomProduit").toString());

                float Prix = Float.parseFloat(obj.get("prixPrduit").toString());
                t.setPrixProduit((double) Prix);

                float nbrProduit = Float.parseFloat(obj.get("qteProduit").toString());
                t.setQteProduit((int) nbrProduit);
             
     /*         try{
                   double reffs= Double.parseDouble(obj.get("idProduit").toString());
                   int reffs2 = (int) reffs;
                   t.setIdProduit(reffs2);
                   
                } catch (NumberFormatException ex){ 
                 //System.out.println(ex);
                }
             
             
             try{
                   double qte= Double.parseDouble(obj.get("qteProduit").toString());
                   int qte2 = (int) qte;
                   t.setQteProduit(qte2);
                   
                } catch (NumberFormatException ex){ 
                 //System.out.println(ex);
                }
                
             
             String nom_p = (String) obj.get("nomProduit");
                t.setNomProduit((String)nom_p);
             
             
             double prix = Double.parseDouble(obj.get("prixPrduit").toString());
                t.setPrixProduit((double)prix);*/

                //  System.out.println(nbrProduit);
                D_commandes.add(t);
            }

        } catch (IOException ex) {

        }
        return D_commandes;
    }

    public ArrayList<Details_commande> ReadDetailsCommande(int id) {
        String url = Statics.BASE_URL + "/AllDetailsCommandeJson/" + id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    // System.out.println(new String(req.getResponseData()));
                    D_commandes = parseDetailsCommande(new String(req.getResponseData()));
                } catch (Exception ex) {
                    //  Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                }

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return D_commandes;
    }

    /* Afficher Les Produits  
    public ArrayList<Produits> parseProduit(String jsonText) throws IOException {
        try {
            produit = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Produits t = new Produits();

                float id = Float.parseFloat(obj.get("refP").toString());
                t.setRef_p((int) id);

                t.setNom_p(obj.get("nomP").toString());

                float Prix = Float.parseFloat(obj.get("prixP").toString());
                t.setPrix_p((double) Prix);

                float nbrProduit = Float.parseFloat(obj.get("quantiteP").toString());
                t.setQuantite((int) nbrProduit);

                t.setImage(obj.get("image").toString());

                produit.add(t);
            }

        } catch (IOException ex) {

        }
        return produit;
    }

    public ArrayList<Produits> getAllProduits() {
        String url = Statics.BASE_URL + "/AllProductsJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    produit = parseProduit(new String(req.getResponseData()));
                } catch (Exception ex) {
                    //  Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                }

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produit;
    }

    /* Ajouter une commande  */
    public void ValiderPanierCommandeJson() {

        String url = Statics.BASE_URL + "/LouayPanierCommandeJson/" + Panier.getInstance().panier.size() +"/" +  Panier.getInstance().totalPrix() +"/" + idClient;
             req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    // System.out.println(new String(req.getResponseData()));
                    commandes = parseCommandes(new String(req.getResponseData()));
                    System.out.println(commandes.get(0).getId());
                   // ValiderPanierDetailsCommandeJson(commandes.get(0).getId());
                } catch (Exception ex) {
                    //  Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                }

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
      
        for(int i=0;i<Panier.getInstance().panier.size();i++){
        ValiderPanierDetailsCommandeJson(commandes.get(0).getId(),i);
        }

        
    }
    
      public void ValiderPanierDetailsCommandeJson(int id,int i) {

          
        String url = Statics.BASE_URL + "/DetailsJson/" + id 
                +"/" 
                +  Panier.getInstance().panier.get(i).getRef_p()
                +"/" 
                +Panier.getInstance().panier.get(i).getNom_p()
                +"/"
                +Panier.getInstance().panier.get(i).getPrix_p();
            
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    // System.out.println(new String(req.getResponseData()));
                  //  commandes = parseCommandes(new String(req.getResponseData()));
                    //System.out.println(commandes.get(0).getId());
                } catch (Exception ex) {
                    //  Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                }

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
          
    }
}
