/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baskel.myapp.services;

import com.Baskel.myapp.entities.RDV;
import com.Baskel.myapp.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.notifications.LocalNotification;
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
public class ServiceRDV {
    
    int rep ;
    int Userid =9;
      public ArrayList<RDV> rdvs;
    public static ServiceRDV instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceRDV() {
         req = new ConnectionRequest();
    }

   public static ServiceRDV getInstance() {
        if (instance == null) {
            instance = new ServiceRDV();
        }
        return instance;
    }  

    
     public ArrayList<RDV> parseRDV (String jsonText) throws IOException, ParseException {
        try {
            rdvs =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                RDV t = new RDV();
                
                float id = Float.parseFloat(obj.get("id").toString());
                t.setIdRDV((int)id);
                
                t.setUserid(((int)Float.parseFloat(obj.get("idUser").toString())));
               
                t.setObjetRDV(obj.get("objetR").toString());
                
                t.setEtatRDV(obj.get("etatR").toString());
                t.setDetailsRDV(obj.get("detailsR").toString());
              
                t.setTechnicienid(obj.get("tech").toString());
               
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
                  String datedept = obj.get("dateDep").toString();
                try {
                    //System.out.println(sdf.parse(date));
                     t.setDateDeptRDV(sdf.parse(datedept));

                } catch (ParseException ex) {
                    //Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                 
                  String date = obj.get("date").toString();
                try {
                    //System.out.println(sdf.parse(date));
                     t.setDateRDV(sdf.parse(date));

                } catch (ParseException ex) {
                    //Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                }
                /*t.setNbrProduit((int)nbrProduit);*/
                
                rdvs.add(t);
            }
            
            
        } catch (IOException ex) {
            
       
       }
        return rdvs;
    }


    
     public ArrayList<RDV> getAllRdv(){
        String url = Statics.BASE_URL+"/Frite/ReadAllRdvJson";
        //String url = Statics.BASE_URL+"/ReadAllCommandeJson/" + 1 + "/" + 2 ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              
                try {
                  rdvs = parseRDV (new String(req.getResponseData()));
                } catch (Exception ex) {
                 // Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return rdvs;
    }
     
     
             public int parseAddRdv (String jsonText) throws IOException {
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
         
     
     
     
        public int AjouterRDVJson(RDV r) throws IOException {

        String url = Statics.BASE_URL + "/Frite/AjoutRDVJson"+"/" +r.getObjetRDV() +"/"+ r.getDetailsRDV()+"/" + Userid + "/"+ r.getDateRDV();
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    // System.out.println(new String(req.getResponseData()));
                    
                    rep = parseAddRdv(new String(req.getResponseData()));
                    System.out.println(rep);
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
         
         
 
         
         
                   public int SupprimerRDVJson(RDV r) throws IOException {

        String url = Statics.BASE_URL + "/Frite/SupprimerRDVJSON"+"/" +r.getIdRDV();
             req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    // System.out.println(new String(req.getResponseData()));
                     rep = parseAddRdv(new String(req.getResponseData()));
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
     
                   
        public int ModifierRDVJson(RDV r) {

        String url = Statics.BASE_URL + "/Frite/ModifierRDVJson"+"/" +r.getObjetRDV() +"/"+ r.getDetailsRDV()+"/" + r.getIdRDV()+"/"+r.getDateRDV();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    // System.out.println(new String(req.getResponseData()));
                     rep = parseAddRdv(new String(req.getResponseData()));
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
     