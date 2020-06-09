/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baskel.myapp.services;

import com.Baskel.myapp.entities.Vehicule;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Achraf Zaafrane
 */
public class ServiceVehicule {
        int rep ;
    int Userid =9;
    public ArrayList<Vehicule> reclamations;
    public static ServiceVehicule instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceVehicule() {
         req = new ConnectionRequest();
    }

    public static ServiceVehicule getInstance() {
        if (instance == null) {
            instance = new ServiceVehicule();
        }
        return instance;
    }  
    
     public ArrayList<Vehicule> parseVehicules(String jsonText) throws IOException, ParseException {
        try {
            reclamations=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Vehicule t = new Vehicule();
                
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                
               
                t.setMatricule(obj.get("matricule").toString());
                
                t.setMarque(obj.get("marque").toString());
                t.setType(obj.get("type").toString());
                
      
                /*t.setNbrProduit((int)nbrProduit);*/
                
                reclamations.add(t);
            }
            
            
        } catch (IOException ex) {
            
       
       }
        return reclamations;
    }


    
     public ArrayList<Vehicule> getAllVehicules(){
        String url = Statics.BASE_URL+"/ReadVehiculeJson";
        //String url = Statics.BASE_URL+"/ReadAllCommandeJson/" + 1 + "/" + 2 ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              
                try {
                  reclamations = parseVehicules(new String(req.getResponseData()));
                } catch (Exception ex) {
                 // Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }
     
         public int AjouterVehiculeJson(Vehicule r) throws IOException {

        String url = Statics.BASE_URL + "/AjoutVehiculeJson"+"/" +r.getMatricule()+"/"+ r.getMarque()+"/" + r.getType() ;
             req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    // System.out.println(new String(req.getResponseData()));
                    rep = parseAddVehicules(new String(req.getResponseData()));
                   // System.out.println(reclamations.get(0).getIdR());
                   // ValiderPanierDetailsCommandeJson(commandes.get(0).getId());
                } catch (Exception ex) {
                    //  Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                }

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
         Media m = MediaManager.createMedia((Display.getInstance().getResourceAsStream(getClass(), "/bell.mp3")), "audio/mpeg");
                            m.play();
        return rep;
     
     
        }
         
         
         public int parseAddVehicules(String jsonText) throws IOException {
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

         
          public int SupprimerReclamationJson(Vehicule r) throws IOException {

        String url = Statics.BASE_URL + "/SupprimerVehiculeJSON"+"/" +r.getId();
             req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    // System.out.println(new String(req.getResponseData()));
                     rep = parseAddVehicules(new String(req.getResponseData()));
                   // System.out.println(reclamations.get(0).getIdR());
                   // ValiderPanierDetailsCommandeJson(commandes.get(0).getId());
                } catch (Exception ex) {
                    //  Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                }

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
           Media m = MediaManager.createMedia((Display.getInstance().getResourceAsStream(getClass(), "/bell.mp3")), "audio/mpeg");
                            m.play();
        return rep;
     
     
        }
          
        public int ModifierReclamationJson(Vehicule r) {

        String url = Statics.BASE_URL + "/ModifierVehiculeJson"+"/" +r.getId()+"/"+ r.getMatricule()+"/" + r.getMarque() + "/" +r.getType();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    // System.out.println(new String(req.getResponseData()));
                     rep = parseAddVehicules(new String(req.getResponseData()));
                   // System.out.println(reclamations.get(0).getIdR());
                   // ValiderPanierDetailsCommandeJson(commandes.get(0).getId());
                } catch (Exception ex) {
                    //  Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                }

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return rep;
     
     
        }
         
    
}
