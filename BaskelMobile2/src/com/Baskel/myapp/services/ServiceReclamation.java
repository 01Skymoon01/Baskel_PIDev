/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baskel.myapp.services;

import com.Baskel.myapp.entities.Reclamation;
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
 * @author 21692
 */
public class ServiceReclamation {
    int rep ;
    int Userid =9;
      public ArrayList<Reclamation> reclamations;
    public static ServiceReclamation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceReclamation() {
         req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }  
    
    
    
     public ArrayList<Reclamation> parseReclamations(String jsonText) throws IOException, ParseException {
        try {
            reclamations=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Reclamation t = new Reclamation();
                
                float id = Float.parseFloat(obj.get("id").toString());
                t.setIdR((int)id);
                
                t.setUserid(((int)Float.parseFloat(obj.get("idUser").toString())));
               
                t.setObjetR(obj.get("objetR").toString());
                
                t.setEtatR(obj.get("etatR").toString());
                t.setDetailsR(obj.get("detailsR").toString());
                
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
                  String date = obj.get("date").toString();
                try {
                    //System.out.println(sdf.parse(date));
                     t.setDateR(sdf.parse(date));

                } catch (ParseException ex) {
                    //Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                }
                /*t.setNbrProduit((int)nbrProduit);*/
                
                reclamations.add(t);
            }
            
            
        } catch (IOException ex) {
            
       
       }
        return reclamations;
    }


    
     public ArrayList<Reclamation> getAllReclamations(){
        String url = Statics.BASE_URL+"/Frite/ReadAllReclamationJson";
        //String url = Statics.BASE_URL+"/ReadAllCommandeJson/" + 1 + "/" + 2 ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              
                try {
                  reclamations = parseReclamations(new String(req.getResponseData()));
                } catch (Exception ex) {
                 // Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }
     
         public int AjouterReclamationJson(Reclamation r) throws IOException {

        String url = Statics.BASE_URL + "/Frite/AjoutReclamationJson"+"/" +r.getObjetR() +"/"+ r.getDetailsR()+"/" + Userid ;
             req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    // System.out.println(new String(req.getResponseData()));
                    rep = parseAddReclamation(new String(req.getResponseData()));
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
         
         
         public int parseAddReclamation(String jsonText) throws IOException {
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

         
          public int SupprimerReclamationJson(Reclamation r) throws IOException {

        String url = Statics.BASE_URL + "/Frite/SupprimerReclamationJSON"+"/" +r.getIdR();
             req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    // System.out.println(new String(req.getResponseData()));
                     rep = parseAddReclamation(new String(req.getResponseData()));
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
          
        public int ModifierReclamationJson(Reclamation r) {

        String url = Statics.BASE_URL + "/Frite/ModifierReclamationJson"+"/" +r.getObjetR() +"/"+ r.getDetailsR()+"/" + r.getIdR();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    // System.out.println(new String(req.getResponseData()));
                     rep = parseAddReclamation(new String(req.getResponseData()));
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
    

