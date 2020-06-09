


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;


import com.Baskel.myapp.entities.Panier;
import com.Baskel.myapp.entities.Produits;
import com.Baskel.myapp.entities.Rating;
import com.Baskel.myapp.entities.Wishlist;
import com.Baskel.myapp.services.ServiceProduits;

import com.codename1.components.ImageViewer;
import static com.codename1.components.ImageViewer.IMAGE_FILL;
import com.codename1.components.MediaPlayer;
import com.codename1.components.SliderBridge;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class ProductsForm extends BaseForm {
    com.codename1.ui.Container gui_Container_star = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    com.codename1.ui.Label gui_Label_cart = new com.codename1.ui.Label();
    Command c ;
    Image icon;
    
    Command c2 ;
    Image icon2 ;
    Resources theme = UIManager.initFirstTheme("/theme");
    
    public ProductsForm(Resources res) {

        icon = res.getImage("wishlistIcon.png").scaled(90, 90);
        icon2 = res.getImage("cart4.png").scaled(90, 90);
        initGuiBuilderComponents(res);
        
        
            
            

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Shop", "Title") 
                        //new Label("de commande N: "+Integer.toString(id), "Title")
                )
        );

        installSidemenu(res);

        //nbr items panier
        c = new Command("("+ServiceProduits.getInstance().getAllWishlist(1).size()+")",icon);
        getToolbar().addCommandToRightBar(c);
        
        c2 = new Command("("+Integer.toString(Panier.getInstance().panier.size())+")",icon2);
        getToolbar().addCommandToRightBar(c2);
        
        
        
/*****************************************FOR VIDEOOOOOO*****************************************/
        
        Style s = UIManager.getInstance().getComponentStyle("Title");
        FontImage iconVideo = FontImage.createMaterial(FontImage.MATERIAL_VIDEO_LIBRARY, s);
        FontImage exitVideo = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, s);
        final Form hi = new Form("Our Ad", new BorderLayout());
        getToolbar().addCommandToLeftBar(new Command("Play ad.", iconVideo) {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
              try {
                    hi.removeAll();
                    hi.revalidate();
                    
                    /*TextField volumeText=new TextField();
                    int volume = Integer.parseInt(volumeText.getText());*/
                    
                    
                    Media media = MediaManager.createMedia("file://C:/wamp64/www/PiDevMobile/ArchiveBaskelMobile3/mdd-miku-ievan-polkka-dance.mp4", true);
                    media.setFullScreen(true);
                    
                    hi.add(BorderLayout.CENTER, new MediaPlayer(media));
                    //hi.add(exitVideo);
                    
                    media.setVolume(50);
                    //media.setNativePlayerMode(true);
                    media.play();
                    
                    
                    hi.getToolbar().addCommandToLeftBar(new Command("", exitVideo) {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                       System.out.println("Going back to shop ...");
                       media.pause();
                       new ProductsForm(theme).show();
                    }
                
                });
                    
                } catch (IOException ex) {
                   
                }
                
                hi.show();
                
            }
           
        });
        
/*****************************************END FOR VIDEOOOOOO*****************************************/
   
   
    }
    
    /******************************RATING STARS*******************************/
    
        private void initStarRankStyle(Style s, Image star) {
                    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
                    //s.setBorder(Border.createEmpty());
                    s.setBgImage(star);
                    s.setBgTransparency(0);
                }

                private Slider createStarRankSlider() {
                    
                    ArrayList<Image> starsList = new ArrayList();
                    Slider starRank = new Slider();
                    starRank.setEditable(true);
                    starRank.setMinValue(1);
                    
                    starRank.setMaxValue(6);
                    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                            derive(Display.getInstance().convertToPixels(3, true), Font.STYLE_PLAIN);
                    Style s = new Style(0xffff33, 0, fnt, (byte)0);
                    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
                    s.setOpacity(100);
                    s.setFgColor(0);
                    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
                   
                    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
                    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
                  
                  //  initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
                  //  initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
                    
                    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
                    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
                    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
                    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
                
                    
                    
                    
                    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
                
                    return starRank;
                }
                
                
    /***************************************************************************/
    
    
    
    
    
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
       
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        
        
        
        for(int i=0;i<ServiceProduits.getInstance().getAllProduits().size();i++){
       
            System.out.println("");
            /*EncodedImage placehorlder = EncodedImage.createFromImage(resourceObjectInstance.getImage("contact-a.png"), true);
            URLImage background = URLImage.createToStorage(placehorlder ,"http://localhost/WebProjectSymfony/Baskel/web/uploads/images/"+ServiceCommande.getInstance().getAllProduits().get(i).getImage(),
                    "http://localhost/WebProjectSymfony/Baskel/web/uploads/images/"+ServiceCommande.getInstance().getAllProduits().get(i).getImage() );*/
            
            
            
            int ref=ServiceProduits.getInstance().getAllProduits().get(i).getRef_p();
            String nom=ServiceProduits.getInstance().getAllProduits().get(i).getNom_p();
            String imgPath=ServiceProduits.getInstance().getAllProduits().get(i).getImage();
            double prix=ServiceProduits.getInstance().getAllProduits().get(i).getPrix_p();
            int quantite = ServiceProduits.getInstance().getAllProduits().get(i).getQuantite();
            
           // background.scale(i, RIGHT);
            //ImageViewer img = new ImageViewer(background.fill(resourceObjectInstance.getImage("contact-a.png").getWidth()*2, resourceObjectInstance.getImage("contact-a.png").getHeight()*2));
            
            com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
            com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
            com.codename1.ui.Container gui_Container_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
            com.codename1.ui.Label gui_Label_4 = new com.codename1.ui.Label();
            com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
            com.codename1.ui.Label gui_Label_3 = new com.codename1.ui.Label();
            com.codename1.ui.Label gui_Label_ID = new com.codename1.ui.Label();
            com.codename1.ui.Label gui_Label_2 = new com.codename1.ui.Label();
            com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
            com.codename1.ui.Label gui_Label_6 = new com.codename1.ui.Label();
            com.codename1.ui.Label gui_Label_img = new com.codename1.ui.Label();
            com.codename1.ui.Label gui_Label_prix = new com.codename1.ui.Label();
            

            gui_Label_img.setText(ServiceProduits.getInstance().getAllProduits().get(i).getImage());
            gui_Label_prix.setText(Double.toString(ServiceProduits.getInstance().getAllProduits().get(i).getPrix_p()));
           
            
            addComponent(gui_Container_1);

           /**********************MY IMAAAAAAAAGE***********************/
           try {
               Image placeholderImage = FontImage.createMaterial(FontImage.MATERIAL_PERSON,"label",28);
                
              //  img.fill(placeholderImage.getWidth(),placeholderImage.getHeight());
                ImageViewer iv = new ImageViewer();
                Image img;
                           
                img = Image.createImage("file://C:/wamp64/www/WebProjectSymfony/Baskel/web/uploads/images/"+imgPath);
                
             
                
                iv.setImage(img.fill(placeholderImage.getWidth(),placeholderImage.getHeight()));
                
               //iv.setHeight(10);
                //iv.setImage(img.fill(placeholderImage.getWidth(), placeholderImage.getHeight()));
                //iv.getUnselectedStyle().setBgImage(img.fill(placeholderImage.getWidth(),placeholderImage.getHeight()));
                
                iv.setImageInitialPosition(IMAGE_FILL);
               
                ServiceProduits.getInstance().readImage(ref, iv);
                
                gui_Container_4.addComponent(iv);
                
                
                
                 } catch (IOException ex) {
               
            }
           
           
           
           
         
         
            gui_Label_ID.setText(Integer.toString(ref));
            gui_Container_1.setName("Container_1");
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Container_2);
            gui_Container_2.setName("Container_2");
            gui_Container_2.addComponent(gui_Label_1);
            //System.out.println(ServiceCommande.getInstance().getAllCommandes().get(i).getDate());
           // gui_Label_1.setText(Integer.toString(ServiceCommande.getInstance().ReadDetailsCommande(50).get(i).getQteProduit()));
            gui_Label_1.setUIID("SmallFontLabel");
            gui_Label_1.setName("Label_1");
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, gui_Container_4);
            gui_Container_4.setName("Container_4");
            ((com.codename1.ui.layouts.FlowLayout) gui_Container_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
            
            gui_Label_4.setUIID("Padding2");
            gui_Label_4.setName("Label_4");
            gui_Label_4.setText("produits");
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_3);
            gui_Container_3.setName("Container_3");
            gui_Container_3.addComponent(gui_Label_3);
            gui_Container_3.addComponent(gui_Label_2);
            gui_Container_3.addComponent(gui_Text_Area_1);
            gui_Label_3.setText(nom);
            
           
            
            gui_Label_3.setName("Label_3");
            //gui_Label_3.setUIID("BigFontLabel");
            gui_Label_2.setText(Double.toString(prix)+" DN" );
            //gui_Label_2.setSize(new Dimension(30, 30));
           
            //gui_Label_2.setUIID("SmallFontLabel");
            gui_Label_2.getAllStyles().setFgColor(0xFF0000);
            gui_Label_2.setName("Label_2");
            
             if(quantite==0){
             gui_Text_Area_1.setText("-Hors stock-");
             }
            else {
                gui_Text_Area_1.setText("-En stock-");
            }
             
            
          //  gui_Text_Area_1.setUIID("SmallFontLabel");
            gui_Text_Area_1.setName("Text_Area_1");
            gui_Container_2.setName("Container_2");
            gui_Container_4.setName("Container_4");
            ((com.codename1.ui.layouts.FlowLayout) gui_Container_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
            gui_Container_3.setName("Container_3");
            addComponent(gui_Label_6);

            gui_Container_1.setName("Container_1");
            gui_Label_6.setText("");
            gui_Label_6.setUIID("Separator");
            gui_Label_6.setName("Label_6");
            
            
            
            
            /*****************************FOR RATING********************************/
               
               Button rating=new Button("rate");
               
               Slider progress = createStarRankSlider();
               
               gui_Container_3.add(FlowLayout.encloseLeftMiddle(progress));
               
               
               try{
                    int total = ServiceProduits.getInstance().getRating(ref).get(0).getTotalRate();
                    progress.setEditable(true);
                    progress.setProgress(total);
                    
                    
               } catch (IndexOutOfBoundsException io) {
                   
               }
             
                ConnectionRequest cr = new ConnectionRequest();
                SliderBridge.bindProgress(cr, progress);
               
                 progress.addActionListener(c->{
                  
                  int newRate= progress.getProgress();
                  Rating r = new Rating(ref,9,newRate);
                  
                  ServiceProduits.getInstance().submitRating(r);
                  System.out.println("Your rate to this product is : "+progress.getProgress());
                  
                  progress.setProgress(0);
                  revalidate();
                  
                  try{
                    int total = ServiceProduits.getInstance().getRating(ref).get(0).getTotalRate();
                    progress.setEditable(true);
                    progress.setProgress(total);
                    revalidate();
                    
               } catch (IndexOutOfBoundsException io) {
                   
               } 
                
              });
            
            
            
            /***********************************************************************/
            
            
            
          
            
            
            /***************************ADD WISHLIST*********************************/

           
           Button btnWishlist = new Button();
           //FontImage.setMaterialIcon(btnWishlist, FontImage.MATERIAL_STAR);
           
           
           Image heartIcon = theme.getImage("wishlist.png").scaled(98, 98);
           btnWishlist.setIcon(heartIcon);
           
           btnWishlist.getAllStyles().setBackgroundType(BACKGROUND_NONE);
           
           
           
         
           btnWishlist.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                Produits p = new Produits(nom,prix,quantite,imgPath,ref);
                Wishlist w = new Wishlist(10,p);
                //ServiceProduits.getInstance().addToWishlist(w);
                
                if(ServiceProduits.getInstance().addToWishlist(w)==1){
                    
                    System.out.println("Product added to wishlist!");
                    getToolbar().removeCommand(c);
                    revalidate();
                    c = new Command("("+ServiceProduits.getInstance().getAllWishlist(10).size()+")",icon);
                    getToolbar().addCommandToRightBar(c);
                    revalidate();
                    
                    
                    
                } else {
                   Dialog.show(" ", " Produit existe déjà dans votre wishlist! ", "OK", null);
                }
          
                  
                }
            });
           
        //   gui_Container_2.add(new DrawingCanvas())
           gui_Container_2.add(btnWishlist);
           gui_Container_4.setLeadComponent(btnWishlist);
           
           
           
           
           
           
           
           /****************************LEL COMMANDE***********************************/
            
             Button myBtn = new Button();
            
             myBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   System.out.println( gui_Label_3.getText());
                   Produits p = new Produits();
                   
                   p.setRef_p(Integer.parseInt(gui_Label_ID.getText()));
                   p.setNom_p(gui_Label_3.getText());
                   p.setImage(gui_Label_img.getText());
                   p.setPrix_p(Double.parseDouble(gui_Label_prix.getText()));
                   if(Panier.getInstance().AddProduitInPanier(p)){
                    
                       System.out.println(Panier.getInstance().toString());

                   //refresh bnr items                  
                    getToolbar().removeCommand(c2);
                    revalidate();
                    c2 = new Command("("+Integer.toString(Panier.getInstance().panier.size())+")",icon2);
                   // c.actionPerformed(e); 
                     getToolbar().addCommandToRightBar(c2);
                     revalidate();
                   
                   }else{
                    Dialog.show(" ", " Deja ajouter ", "OK", null);
                   }
                  
                }
            });

            gui_Container_4.setLeadComponent(myBtn);
       
      
       /***************************************************************************/
       
       /**********************************SOME STYYYYLE********************************************/
         
            gui_Container_3.getAllStyles().setPaddingTop(27);
           
            gui_Container_1.getAllStyles().setPaddingTop(32);
            gui_Container_1.getAllStyles().setPaddingLeft(25);
            
            gui_Label_3.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE));
       
            Border border = Border.createCompoundBorder(null, null, null, null);
            gui_Text_Area_1.getAllStyles().setBgTransparency(0);
            gui_Text_Area_1.getAllStyles().setBorder(border);
            gui_Text_Area_1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
            gui_Text_Area_1.setEditable(false);
            
            
        /**********************************EEND STYYYYLE********************************************/
            
      
        }
         /***************************END LIST PROD*********************************/
       
            
       
        
        
     
    }
}

