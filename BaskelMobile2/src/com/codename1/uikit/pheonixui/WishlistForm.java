/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;


import com.Baskel.myapp.entities.Produits;
import com.Baskel.myapp.entities.Wishlist;
import com.Baskel.myapp.services.ServiceProduits;

import com.codename1.components.ImageViewer;
import static com.codename1.components.ImageViewer.IMAGE_FILL;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author hp
 */
public class WishlistForm extends BaseForm {
    com.codename1.ui.Container gui_Container_star = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    com.codename1.ui.Label gui_Label_cart = new com.codename1.ui.Label();
   // Command c ;
   // Image icon;
    
    public WishlistForm(Resources res) {

        //icon = res.getImage("wishlistIcon.png").scaled(90, 90);
        initGuiBuilderComponents(res);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("VOTRE WISHLIST", "Title") ,
                        new Label(Integer.toString(ServiceProduits.getInstance().getAllWishlist(10).size()), "InboxNumber")
                )
        );

        installSidemenu(res);

        //nbr items panier
         //c = new Command("("+ServiceProduits.getInstance().getAllWishlist(1).size()+")",icon);
       
        //getToolbar().addCommandToRightBar(c);
   
    }
    
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
       
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        Resources theme = UIManager.initFirstTheme("/theme");
      
      
   
       // for(int i=0;i<ServiceCommande.getInstance().getAllProduits().size();i++){
        for(int i=0;i<ServiceProduits.getInstance().getAllWishlist(10).size();i++){
       
            /*EncodedImage placehorlder = EncodedImage.createFromImage(resourceObjectInstance.getImage("contact-a.png"), true);
            URLImage background = URLImage.createToStorage(placehorlder ,"http://localhost/WebProjectSymfony/Baskel/web/uploads/images/"+ServiceCommande.getInstance().getAllProduits().get(i).getImage(),
                    "http://localhost/WebProjectSymfony/Baskel/web/uploads/images/"+ServiceCommande.getInstance().getAllProduits().get(i).getImage() );*/
            
            
            
            int id=ServiceProduits.getInstance().getAllWishlist(10).get(i).getId();
            int ref=ServiceProduits.getInstance().getAllWishlist(10).get(i).getRefProd();
            String nom=ServiceProduits.getInstance().getAllWishlist(10).get(i).getNomProd();
            String imgPath=ServiceProduits.getInstance().getAllWishlist(10).get(i).getImgProd();
            double prix=ServiceProduits.getInstance().getAllWishlist(10).get(i).getPrixProd();
        //   int quantite = ServiceProduits.getInstance().getAllWishlist(1).get(i).getP().getQuantite();
            
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
            //com.codename1.ui.Label gui_Label_img = new com.codename1.ui.Label();
            com.codename1.ui.Label gui_Label_prix = new com.codename1.ui.Label();

            //gui_Label_img.setText(imgPath);
            
            
            gui_Label_prix.setText(Double.toString(prix));
            addComponent(gui_Container_1);

           /**********************MY IMAAAAAAAAGE***********************/
           try {
               Image placeholderImage = FontImage.createMaterial(FontImage.MATERIAL_PERSON,"label",20);
                
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
            gui_Label_2.setText(Double.toString(prix)+" DN" );
            gui_Label_2.setUIID("RedLabel");
            gui_Label_2.setName("Label_2");
            /*if(quantite==0){
                gui_Text_Area_1.setText("En stock.");}
            else {
                gui_Text_Area_1.setText("Hors stock.");
            }*/
            gui_Text_Area_1.setUIID("SmallFontLabel");
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
            
            
            
            /**********************BTN DELETE********************************/
            
                Button btnDelete = new Button();
               
                
                Image deleteIcon = theme.getImage("delete2.png").scaled(80, 80);
                btnDelete.getAllStyles().setBackgroundType(BACKGROUND_NONE);
                btnDelete.setIcon(deleteIcon);
                //btnDelete.getAllStyles().setPadding(20, 20 , 20, 20);
                
                btnDelete.addActionListener(event3->{
                   if(ServiceProduits.getInstance().deleteFromWishlist(id)){
                       System.out.println("Produit supprimé de votre wishlist.");
                       
                       
                    ServiceProduits.getInstance().getAllWishlist(1).removeAll(ServiceProduits.getInstance().getAllWishlist(1));
                       
                    new WishlistForm(resourceObjectInstance).show();
                     // new WishlistForm(theme).show();
                   }
                });
                
                gui_Container_2.add(btnDelete);
            
            /****************************************************************/
            
           
    
      
        }
        
       
     
        
        
     
    }
}

