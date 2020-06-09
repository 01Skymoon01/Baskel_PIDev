/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baskel.myapp.services;

import com.Baskel.myapp.entities.Commande;
import com.Baskel.myapp.entities.Details_commande;
import com.Baskel.myapp.entities.Event;
import com.Baskel.myapp.entities.Produits;
import com.Baskel.myapp.entities.Reservation;
import com.Baskel.myapp.entities.SessionUser;
import com.Baskel.myapp.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
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
 * @author Nizar
 */
public class ServiceEvent {

    int idClient = SessionUser.getInstance().getId();
    public ArrayList<Event> events;
    public ArrayList<Reservation> TabReservations;
    int rep;
    public ArrayList<Reservation> res;
    public static ServiceEvent instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEvent() {
        req = new ConnectionRequest();
    }

    public static ServiceEvent getInstance() {
        if (instance == null) {
            instance = new ServiceEvent();
        }
        return instance;
    }

    /* Afficher Commande */
       //*----------------------------------------------affichage---------------------------------------------**//
    public ArrayList<Event> parseEvents(String jsonText) throws IOException {
        try {
            events = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {

                Event t = new Event();

                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);

                t.setImage(obj.get("image").toString());

                t.setNom(obj.get("nom").toString());
                t.setDescription(obj.get("description").toString());
                /*
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
                    //Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
                }
                t.setNbrProduit((int) nbrProduit);
                 */
                events.add(t);

            }

        } catch (IOException ex) {

        }
        return events;
    }

    public ArrayList<Event> getAllEvents() {
        String url = Statics.BASE_URL + "/ReadAllEventJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    events = parseEvents(new String(req.getResponseData()));
                } catch (Exception ex) {
                    //  Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
                }

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
   //*----------------------------------------------affichage---------------------------------------------**//
    
    
    //*----------------------------------------------ajout---------------------------------------------**//
    public int parseReserver(String jsonText) throws IOException {
       
        try {

            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {

                double reffs = Double.parseDouble(obj.get("rep").toString());
                int reffs2 = (int) reffs;

                rep = reffs2;
            }

        } catch (IOException ex) {

        }
        return rep;
    }

    public int Reserver(Reservation R) {

        String url = Statics.BASE_URL + "/ReserverJson/" + R.getId_event().getId() + "/" + R.getId_user();
        req.setUrl(url);
        req.setPost(true);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    rep = parseReserver(new String(req.getResponseData()));
                    
                } catch (IOException ex) {

                }

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        /***API****/
     
        
        
        
        

           /***API****/
        return rep;
    }

    
     //*----------------------------------------------ajout---------------------------------------------**//
    
    
     //*----------------------------------------------affichage---------------------------------------------**//
    public ArrayList<Reservation> parseReservations(String jsonText) throws IOException {
        try {
            res = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {

                Reservation t = new Reservation();

                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);

                t.setEtat(obj.get("etat").toString());
                

                Event E1 = new Event();
                 E1.setNom(obj.get("nom").toString());
                    System.out.println("nom set");
                    
                    
                    if(obj.get("date").toString().equals(" "))
                    {
                        System.out.println("date not set");
                    }
                    else
                    {
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
               String date = obj.get("date").toString();
                        System.out.println(date);
                try {
                 System.out.println(sdf.parse(date));
                    E1.setDate(sdf.parse(date));
                    System.out.println("date set");
                   

                } catch (ParseException ex) {
                    //Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
                }
                        }
             

                t.setId_event(E1);
                System.out.println("event set");
    
                res.add(t);

            }

        } catch (IOException ex) {

        }
        return res;
    }

    public ArrayList<Reservation> AfficherReservation(int id_client) {

        String url = Statics.BASE_URL + "/ReadAllReservationJson/" + id_client;
        req.setUrl(url);
        req.setPost(true);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    TabReservations = parseReservations(new String(req.getResponseData()));
                } catch (IOException ex) {

                }

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return TabReservations;
    }
   //*----------------------------------------------affichage---------------------------------------------**//
    
    public int SupprimerReservationJson(Reservation r) {
        String url = Statics.BASE_URL + "/SupprimerReservationJSON"+"/" +r.getId();
             req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    // System.out.println(new String(req.getResponseData()));
                     rep = parseReserver(new String(req.getResponseData()));
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
