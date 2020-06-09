/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baskel.myapp.services;

import com.Baskel.myapp.entities.Commande;
import com.Baskel.myapp.entities.Details_commande;
import com.Baskel.myapp.entities.Livraison;
import com.Baskel.myapp.entities.Livreur;
import com.Baskel.myapp.entities.Produits;
import static com.Baskel.myapp.services.ServiceCommande.instance;
import com.Baskel.myapp.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Achraf Zaafrane
 */
public class ServiceLivraison {
    int idLivreur =7;
     public ArrayList<Livraison> livraisons; 
     public ArrayList<Livreur> livreurs;
     String rep2="";
     
    public static ServiceLivraison instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    int rep;

    public ServiceLivraison() {
            req = new ConnectionRequest();

    }
    
 public static ServiceLivraison getInstance() {
        if (instance == null) {
            instance = new ServiceLivraison();
        }
        return instance;
    }

    /* Afficher Commande */
    public ArrayList<Livraison> parseLivraisons(String jsonText) throws IOException {
        try {
            livraisons = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                
                Livraison t = new Livraison();

                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);

/*
                float totalPrix = Float.parseFloat(obj.get("TotalePrix").toString());
                t.setTOtalPrix((double) totalPrix);

                float nbrProduit = Float.parseFloat(obj.get("nbrProduit").toString());
                t.setNbrProduit((int) nbrProduit);
*/

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = obj.get("dateLiv").toString();
            
                try {
                    //System.out.println(sdf.parse(date));
                    t.setDate(sdf.parse(date));

                } catch (ParseException ex) {
                    //Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                }
           //     t.setNbrProduit((int) nbrProduit);

                livraisons.add(t);
                
            }

        } catch (IOException ex) {

        }
        return livraisons;
    }

    
     /* Afficher Commande */
    public ArrayList<Livreur> parseLivreur(String jsonText) throws IOException {
        try {
            livreurs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                
                Livreur t = new Livreur();

                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
   float solde = Float.parseFloat(obj.get("solde").toString());
                t.setSolde((int) solde);
/*
                float totalPrix = Float.parseFloat(obj.get("TotalePrix").toString());
                t.setTOtalPrix((double) totalPrix);

                float nbrProduit = Float.parseFloat(obj.get("nbrProduit").toString());
                t.setNbrProduit((int) nbrProduit);
*/
/*
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = obj.get("dateLiv").toString();
  */          /*
                try {
                    //System.out.println(sdf.parse(date));
                  //  t.setDate(sdf.parse(date));

                } catch (ParseException ex) {
                    //Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                }
           //     t.setNbrProduit((int) nbrProduit);
*/
                livreurs.add(t);
                
            }

        } catch (IOException ex) {

        }
        return livreurs;
    }
    
      public ArrayList<Livreur> getLivreur() {
        String url = Statics.BASE_URL + "/ReadLivreurJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    livreurs = parseLivreur(new String(req.getResponseData()));
                    
                } catch (Exception ex) {
                    //  Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                }

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return livreurs;
    }

    public ArrayList<Livraison> getAllLivraison() {
        String url = Statics.BASE_URL + "/ReadAllLivraisonJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    livraisons = parseLivraisons(new String(req.getResponseData()));
                    
                } catch (Exception ex) {
                    //  Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                }

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return livraisons;
    }
    
    
    public int addLivraison(Livraison w) throws IOException  {
        
        String url = Statics.BASE_URL + "/AddLivraisonJson/" + w.getIdCommande().getId()+"/" + w.getIdClient();
        req.setUrl(url);
        req.setPost(true);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              
                try {
                    rep = parseAddLivraison(new String(req.getResponseData()));
                } catch (IOException ex) {
               
                }
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
         Media m = MediaManager.createMedia((Display.getInstance().getResourceAsStream(getClass(), "/bell.mp3")), "audio/mpeg");
                            m.play();
      
        return rep;
    }
    
    
    
public int parseAddLivraison(String jsonText) throws IOException {
     // int rep = 0;
         try {
            
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
           
            for(Map<String,Object> obj : list){
                
                
              
                double reffs= Double.parseDouble(obj.get("etat").toString());
                System.out.println(reffs);
                int reffs2 = (int) reffs;
             
             
                

                
                rep=reffs2;
            }
            
            
        } catch (IOException ex) {
            
       
       }
        return rep;
    }







    
    
public String parseAddLivraison2(String jsonText) throws IOException {
     // int rep = 0;
         try {
            
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
           
            for(Map<String,Object> obj : list){
                
                
              
                String reffs=obj.get("etat").toString();
                System.out.println(reffs);
             
             
                

                
                rep2=reffs;
            }
            
            
        } catch (IOException ex) {
            
       
       }
        return rep2;
    }




public String codeJson(int r) {

        String url = Statics.BASE_URL + "/coderecup/" +r;
             req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    // System.out.println(new String(req.getResponseData()));
                     rep2 = parseAddLivraison2(new String(req.getResponseData()));
                   // System.out.println(reclamations.get(0).getIdR());
                   // ValiderPanierDetailsCommandeJson(commandes.get(0).getId());
                } catch (Exception ex) {
                    //  Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                }

                req.removeResponseListener(this);
            }
        });
                 NetworkManager.getInstance().addToQueueAndWait(req);
        return rep2;
     
     
        }
}
