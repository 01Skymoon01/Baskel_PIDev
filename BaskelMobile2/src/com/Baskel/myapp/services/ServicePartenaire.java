/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baskel.myapp.services;

import com.Baskel.myapp.entities.Commande;
import com.Baskel.myapp.entities.Details_commande;
import com.Baskel.myapp.entities.Event;
import com.Baskel.myapp.entities.Partenaire;
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
public class ServicePartenaire {

    int idClient = SessionUser.getInstance().getId();

    /**
     *
     */
    public ArrayList<Partenaire> partenaires;
    public ArrayList<Partenaire> TabPartenaires;
    int rep;
    public ArrayList<Reservation> res;
    public static ServicePartenaire instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicePartenaire() {
        req = new ConnectionRequest();
    }

    public static ServicePartenaire getInstance() {
        if (instance == null) {
            instance = new ServicePartenaire();
        }
        return instance;
    }

   
       //*----------------------------------------------affichage---------------------------------------------**//
    public ArrayList<Partenaire> parsePartenaire(String jsonText) throws IOException {
        try {
            partenaires = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {

                Partenaire t = new Partenaire();

                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);

                

                t.setNom(obj.get("nom").toString());
                t.setDescription(obj.get("description").toString());
                 t.setType(obj.get("type").toString());
                 t.setRepresentant(obj.get("representant").toString());
                
                partenaires.add(t);

            }

        } catch (IOException ex) {

        }
        return partenaires;
    }

    public ArrayList<Partenaire> getAllPartenaire() {
        String url = Statics.BASE_URL + "/ReadAllPartenaireJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    partenaires = parsePartenaire(new String(req.getResponseData()));
                } catch (Exception ex) {
                    //  Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
                }

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return partenaires;
    }
   //*----------------------------------------------affichage---------------------------------------------**//
    
}
