/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baskel.myapp.services;

import com.Baskel.myapp.entities.Commande;
import com.Baskel.myapp.entities.Details_commande;
import com.Baskel.myapp.entities.User;
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
import java.util.*;

import static java.lang.System.*;


/**
 *
 * @author hp
 */
public class ServiceUser {
    public User loggedUser= new User();
    public ArrayList<User> Users;
    public ArrayList<Details_commande> D_commandes;
    public static ServiceUser instance=null;
    public boolean resultOK;
    private final ConnectionRequest req;

    public ServiceUser() {
        req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }


    /* Afficher Commande */

    public ArrayList<User> parseUsers(String jsonText) throws IOException {


            Users = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> tasksListJson;
            tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list;
            list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {
                //System.out.println("fok ala zébbi");
                User t = new User();
                //System.out.println("fok ala zébbi 2");
                //int id = Integer.parseInt(obj.get("id").toString());
                float id;
                String rar = obj.get("id").toString();
                System.out.println("rar : " + rar);
                id = Float.parseFloat(rar);
                //int id = (int) obj.get("id");

                String username = obj.get("username").toString();
                String password = obj.get("password").toString();
                String email = obj.get("email").toString();
                //String picturepath = obj.get("picturepath").toString();
                //String numtel = obj.get("numtel").toString();
                //t.setCin((int)Float.parseFloat(obj.get("cin").toString()));


                t.setId((int) id);
                t.setUsername(username);
                t.setPassword(password);
                t.setEmail(email);
                //t.setPicturepath(picturepath);
                /*t.setNumtel(numtel);*/

                //System.out.println("id  : " + id);
                Users.add(t);
                //out.println("added : " + add);
            }

            return Users;
        }

    public ArrayList<User> getAllUser(){
        String url = Statics.BASE_URL+"/ReadAllUser";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    ///out.println("response : " + Arrays.toString(req.getResponseData()));
                    Users = parseUsers(new String(req.getResponseData()));
                    out.println("Users : " + Users);
                } catch (Exception ex) {
                    //  Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                }

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Users;
    }


    public User Authentification(String username, String password)
    {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = Statics.BASE_URL+"/login_api/" + username + "/" + password;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            if(str.equals("false"))
            {
                loggedUser = null;
            }
            else
            {
                ServiceUser ser = new ServiceUser();
                try {
                    loggedUser = ser.parseUserJson(new String(con.getResponseData()));
                } catch (ParseException ex) {

                }
                //Session.getInstance().setLoggedInUser(loggedUser);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
        System.out.println(loggedUser);
        return loggedUser;
    }

    public User parseUserJson(String json) throws ParseException {

        ArrayList<User> listUsers = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient
            l'utilité de new CharArrayReader(json.toCharArray())

            La méthode parse json retourne une MAP<String,Object> ou String est
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));


            /* Ici on récupère l'objet contenant notre liste dans une liste
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                User u = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int) id);

                u.setUsername(obj.get("username").toString());
                String password = obj.get("password").toString();
                String email = obj.get("email").toString();
                u.setPassword(password);
                u.setEmail(email);


                listUsers.add(u);
            }

        } catch (IOException ex) {
        }

        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web

        */
        return listUsers.get(0);
    }




}
