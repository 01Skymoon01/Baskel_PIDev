/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baskel.myapp.services;

import com.Baskel.myapp.entities.Commande;
import com.Baskel.myapp.entities.Details_commande;
import com.Baskel.myapp.entities.Produits;
import com.Baskel.myapp.entities.Rating;
import com.Baskel.myapp.entities.Wishlist;
import com.Baskel.myapp.utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ery
 */
public class ServiceProduits {
    
    public ArrayList<Produits> prodList;
    public ArrayList<Rating> rating;
    public ArrayList<Wishlist> wishList;
    public static ServiceProduits instance=null;
    public boolean resultOK;
    private final ConnectionRequest req;
    int rep = 0;

    private ServiceProduits() {
         req = new ConnectionRequest();
    }

    public static ServiceProduits getInstance() {
        if (instance == null) {
            instance = new ServiceProduits();
        }
        return instance;
    } 
    
    
    
    public ArrayList<Produits> parseProduits(String jsonText) throws IOException {
        try {
            prodList = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
           
            for(Map<String,Object> obj : list){
                Produits p = new Produits();
                
               
                try{
                   double reffs= Double.parseDouble(obj.get("ref_p").toString());
                   int reffs2 = (int) reffs;
                   p.setRef_p(reffs2);
                   
                } catch (NumberFormatException ex){ 
                 //System.out.println(ex);
                }
                
                try{
                   double qte= Double.parseDouble(obj.get("quantiteP").toString());
                   int qte2 = (int) qte;
                   p.setQuantite(qte2);
                   
                } catch (NumberFormatException ex){ 
                 //System.out.println(ex);
                }
                
            
                String nom_p = (String) obj.get("nomP");
                p.setNom_p((String)nom_p);
                
                String image = (String) obj.get("image");
                p.setImage((String)image);
                
                double prix = Double.parseDouble(obj.get("prixP").toString());
                p.setPrix_p((double)prix);

                
                prodList.add(p);
            }
            
            
        } catch (IOException ex) {
            
       
       }
        return prodList;
    }
    
    
    
    public ArrayList<Produits> getAllProduits(){
        String url = Statics.BASE_URL+"/Produits/getProdDetailsJson";
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              
                try {
                    prodList = parseProduits(new String(req.getResponseData()));
                } catch (IOException ex) {
                  //  Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return prodList;
    }
    
    
    
   
    
    
     public void readImage(int ref, ImageViewer iv) {
        ConnectionRequest connectionRequest = new ConnectionRequest() {
            
            Produits p = new Produits();
            List imgs = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                           JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    //Map<String,Object> imgRow = (Map<String, Object>)data.get("images");
                    imgs.clear();
                    
                   
                  
                    for (Map<String, Object> obj : content) {
                        imgs.add(new Produits(Integer.parseInt((String)obj.get("ref_p")),(String) obj.get("image")));
                    }
                } catch (IOException err) {
                      System.out.println(err);
                }
            }

            @Override
            protected void postResponse() {
                try {
                    p = (Produits) imgs.get(0);
                    iv.setImage(Image.createImage(p.getImage()));
                    System.out.println(p.getImage());
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        };
        
        
        req.setUrl("http://localhost/WebProjectSymfony/Baskel/web/app_dev.php/Produits/getProdImageJson/" + ref);
        NetworkManager.getInstance().addToQueueAndWait(req);
   
    }
     
     
     public int parseAddWishlist(String jsonText) throws IOException {
     // int rep = 0;
         try {
            
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
           
            for(Map<String,Object> obj : list){
                
                
              
                double reffs= Double.parseDouble(obj.get("rep").toString());
                int reffs2 = (int) reffs;
             
             
                

                
                rep=reffs2;
            }
            
            
        } catch (IOException ex) {
            
       
       }
        return rep;
    }
     
     
     public int addToWishlist(Wishlist w) {
        
        String url = Statics.BASE_URL + "/Produits/addToWishlistJSON/" + w.getP().getRef_p() +"/" + w.getId_client();
        req.setUrl(url);
        req.setPost(true);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              
                try {
                    rep = parseAddWishlist(new String(req.getResponseData()));
                } catch (IOException ex) {
               
                }
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return rep;
    }
     
     
     
     
     
     
     
     public ArrayList<Wishlist> parseWishlist(String jsonText) throws IOException {
        try {
            wishList = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
           
            for(Map<String,Object> obj : list){
                Wishlist w = new Wishlist();
              
                String nom = (String) obj.get("nomProd");
                w.setNomProd((String)nom);
                
                String image = (String) obj.get("imageProd");
                w.setImgProd((String)image);
                
                double prix = Double.parseDouble(obj.get("prixProd").toString());
                w.setPrixProd((double)prix);
                
                try{
                   double reffs= Double.parseDouble(obj.get("refP").toString());
                   int reffs2 = (int) reffs;
                   w.setRefProd(reffs2);
                   
                } catch (NumberFormatException ex){ 
                 //System.out.println(ex);
                }
                
                try{
                   double id= Double.parseDouble(obj.get("id").toString());
                   int id2 = (int) id;
                   w.setId(id2);
                   
                } catch (NumberFormatException ex){ 
                 //System.out.println(ex);
                }

                
                wishList.add(w);
            }
            
            
        } catch (IOException ex) {
            
       
       }
        return wishList;
    }
     
     
     
     public ArrayList<Wishlist> getAllWishlist(int idClient){
        String url = Statics.BASE_URL+"/Produits/getWishlistJSON/"+idClient;
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              
                try {
                    wishList = parseWishlist(new String(req.getResponseData()));
                } catch (IOException ex) {
                  //  Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return wishList;
    }
     
     
     public boolean deleteFromWishlist(int idWishlist){
        String url = Statics.BASE_URL+"/Produits/deleteFromWishlistJson/"+idWishlist;
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              
               resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     
     
     
     
     public ArrayList<Rating> parseRating(String jsonText) throws IOException {
        try {
            rating = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
           
            for(Map<String,Object> obj : list){
                
                Rating w = new Rating();
              
                
               
                
                try{
                   double rate= Double.parseDouble(obj.get("totalRate").toString());
                   int rate2 = (int) rate;
                   w.setTotalRate(rate2);
                   
                } catch (NumberFormatException ex){ 
                 
                }
                
                
                rating.add(w);
            }
            
            
        } catch (IOException ex) {
            
       
       }
        return rating;
    }
     
     
     public ArrayList<Rating> getRating(int idProd){
        
        String url = Statics.BASE_URL+"/Produits/getRatingJSON/"+idProd;
        req.setUrl(url);
        req.setPost(true);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              
                try {
                    rating = parseRating(new String(req.getResponseData()));
                } catch (IOException ex) {
                  //  Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return rating;
    }
     
     public boolean submitRating(Rating r) {
        
        String url = Statics.BASE_URL + "/Produits/submitRatingJSON/" + r.getIdProd() + "/"+r.getIdClient()+"/"+r.getRate();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     
     
     
     
     
     public ArrayList<Rating> parseRating2(String jsonText) throws IOException {
        try {
            rating = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
           
            for(Map<String,Object> obj : list){
                
                Rating w = new Rating();
              
                
                       
                try{
                   double id= Double.parseDouble(obj.get("idProd").toString());
                   int id2 = (int) id;
                   w.setIdProd(id2);
                   
                } catch (NumberFormatException ex){ 
                 
                }
                
                
                rating.add(w);
            }
            
            
        } catch (IOException ex) {
            
       
       }
        return rating;
    }
     
     
     public ArrayList<Rating> getAllRating(){
        
        String url = Statics.BASE_URL+"/Produits/getAllRatingJSON";
        req.setUrl(url);
        req.setPost(true);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              
                try {
                    rating = parseRating2(new String(req.getResponseData()));
                } catch (IOException ex) {
                  //  Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return rating;
    }
     
    
}
