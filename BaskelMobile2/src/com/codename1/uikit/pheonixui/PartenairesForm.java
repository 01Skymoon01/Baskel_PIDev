/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.Baskel.myapp.entities.Event;
import com.Baskel.myapp.entities.Panier;
import com.Baskel.myapp.entities.Produits;
import com.Baskel.myapp.entities.Reservation;
import com.Baskel.myapp.services.ServiceCommande;
import com.Baskel.myapp.services.ServiceEvent;
import com.Baskel.myapp.services.ServicePartenaire;
import com.codename1.components.ImageViewer;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Nizar
 */
public class PartenairesForm extends BaseForm {
    com.codename1.ui.Container gui_Container_cart = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            com.codename1.ui.Label gui_Label_cart = new com.codename1.ui.Label();
Command c ;
    Image icon ;
    public PartenairesForm(Resources res) {

        icon = res.getImage("cart4.png").scaled(80, 80);
         initGuiBuilderComponents(res);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Partenaires", "Title") 
                        //new Label("de commande N: "+Integer.toString(id), "Title")
                )
        );

        installSidemenu(res);

        //nbr items panier
      //   c = new Command("("+Integer.toString(Panier.getInstance().panier.size())+")",icon);
       
       // getToolbar().addCommandToRightBar(c);
   
    }
    
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        
      
      
   
        for(int i=0;i<ServicePartenaire.getInstance().getAllPartenaire().size();i++){
       // for(int i=0;i<3;i++){
       
            /*EncodedImage placehorlder = EncodedImage.createFromImage(resourceObjectInstance.getImage("contact-a.png"), true);
            URLImage background = URLImage.createToStorage(placehorlder ,"http://localhost/kolilalala/PiDevUnseen-master/Symfony/Baskel/web/uploads/images/"+ServiceEvent.getInstance().getAllPartenaire().get(i).getImage(),
                    "http://localhost/kolilalala/PiDevUnseen-master/Symfony/Baskel/web/uploads/images/"+ServiceEvent.getInstance().getAllPartenaire().get(i).getImage() );*/
            
           // background.scale(i, RIGHT);
        //    ImageViewer img = new ImageViewer(background.fill(resourceObjectInstance.getImage("contact-a.png").getWidth()*2, resourceObjectInstance.getImage("contact-a.png").getHeight()*2));
            
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

            //gui_Label_img.setText(ServiceEvent.getInstance().getAllPartenaire().get(i).getImage());
           // gui_Label_prix.setText(Double.toString(ServiceCommande.getInstance().getAllProduits().get(i).getSolde()));
            addComponent(gui_Container_1);

           

         
         
            gui_Label_ID.setText(Integer.toString(ServicePartenaire.getInstance().getAllPartenaire().get(i).getId()));
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
        //    gui_Container_4.addComponent(img);
            gui_Label_4.setUIID("Padding2");
            gui_Label_4.setName("Label_4");
            gui_Label_4.setText("produits");
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_3);
            gui_Container_3.setName("Container_3");
            gui_Container_3.addComponent(gui_Label_3);
            gui_Container_3.addComponent(gui_Label_2);
            gui_Container_3.addComponent(gui_Text_Area_1);
            gui_Label_3.setText(ServicePartenaire.getInstance().getAllPartenaire().get(i).getNom());
            gui_Label_3.setName("Label_3");
            gui_Label_2.setText(ServicePartenaire.getInstance().getAllPartenaire().get(i).getDescription() );
            gui_Label_2.setUIID("RedLabel");
            gui_Label_2.setName("Label_2");
            gui_Text_Area_1.setText(ServicePartenaire.getInstance().getAllPartenaire().get(i).getType());
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
            
          Button myBtn = new Button();
            
           /*  myBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   System.out.println( gui_Label_3.getText());
                   Reservation r = new Reservation();
                   
                   Event E1 = new Event();
                   E1.setId(Integer.parseInt(gui_Label_ID.getText()));
                   r.setId_event(E1);
                    r.setId_user(9);
                    
                   if(ServicePartenaire.getInstance().Reserver(r)==1){
                       

                    
                        new ReservationForm(resourceObjectInstance).show();
             
                   
                   }else{
                    Dialog.show(" ", " Error ", "OK", null);
                   }
                  
                }
            });

            gui_Container_1.setLeadComponent(myBtn);
      */
        }
        
       
     
        
        
     
    }
}