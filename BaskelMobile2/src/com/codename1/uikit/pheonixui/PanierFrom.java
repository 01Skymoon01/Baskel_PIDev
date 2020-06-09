/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.Baskel.myapp.entities.Panier;
import com.Baskel.myapp.entities.Produits;
import com.Baskel.myapp.services.ServiceCommande;
import com.codename1.components.ImageViewer;
import com.codename1.io.Log;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.notifications.LocalNotificationCallback;
import com.codename1.push.PushCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
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
 * @author hp
 */
public class PanierFrom extends BaseForm implements LocalNotificationCallback {
 Image icon ;
Command c;
      
    public PanierFrom(Resources res) {

        
  icon = res.getImage("validate.png").scaled(80, 80);
         initGuiBuilderComponents(res);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Votre Panier", "Title"),
                        new Label(Integer.toString(Panier.getInstance().panier.size()), "InboxNumber")
                        //totalPrix
                       
                )
        );
        
        //                        new Label(Double.toString(Panier.getInstance().totalPrix(Panier.getInstance().panier))), "InboxNumber")
 

        installSidemenu(res);

    //  c = new Command(" ",icon);
      
   getToolbar().addCommandToRightBar("", icon, (e) ->  {
       ServiceCommande.getInstance().ValiderPanierCommandeJson();
           //Panier.getInstance().panier.clear();

   new InboxForm(res).show();


           });
       // getToolbar().addCommandToRightBar(c);
              
      
                

        
    }
    
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        
      
      
   
        for(int i=0;i<Panier.getInstance().panier.size();i++){
       
            
            EncodedImage placehorlder = EncodedImage.createFromImage(resourceObjectInstance.getImage("contact-a.png"), true);
            URLImage background = URLImage.createToStorage(placehorlder ,"http://localhost/WebProjectSymfony/Baskel/web/uploads/images/"+Panier.getInstance().panier.get(i).getImage(),
                    "http://localhost/WebProjectSymfony/Baskel/web/uploads/images/"+Panier.getInstance().panier.get(i).getImage());
            ImageViewer img = new ImageViewer(background.fill(resourceObjectInstance.getImage("contact-a.png").getWidth()*2, resourceObjectInstance.getImage("contact-a.png").getHeight()*2));
            com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
            com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
            com.codename1.ui.Container gui_Container_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
            com.codename1.ui.Label gui_Label_4 = new com.codename1.ui.Label();
            com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
            com.codename1.ui.Label gui_Label_3 = new com.codename1.ui.Label();
            com.codename1.ui.Label  gui_Label_ID = new com.codename1.ui.Label();
            com.codename1.ui.Label gui_Label_2 = new com.codename1.ui.Label();
            com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
            com.codename1.ui.Label gui_Label_6 = new com.codename1.ui.Label();
            com.codename1.ui.Label gui_Label_img = new com.codename1.ui.Label();

            addComponent(gui_Container_1);

           

         
            gui_Label_ID.setText(Integer.toString(Panier.getInstance().panier.get(i).getRef_p()));


            gui_Container_1.setName("Container_1");
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Container_2);
            gui_Container_2.setName("Container_2");
            gui_Container_2.addComponent(gui_Label_1);
           
            gui_Label_1.setUIID("SmallFontLabel");
            gui_Label_1.setName("Label_1");
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, gui_Container_4);
            gui_Container_4.setName("Container_4");
            ((com.codename1.ui.layouts.FlowLayout) gui_Container_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
            gui_Container_4.addComponent(gui_Label_4);
            gui_Container_4.addComponent(img);
            gui_Label_4.setUIID("Padding2");
            gui_Label_4.setName("Label_4");
            gui_Label_4.setText( " x ");
            gui_Label_img.setIcon(background);
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_3);
            gui_Container_3.setName("Container_3");
            gui_Container_3.addComponent(gui_Label_3);
            gui_Container_3.addComponent(gui_Label_2);
            gui_Container_3.addComponent(gui_Text_Area_1);
            gui_Label_3.setText(Panier.getInstance().panier.get(i).getNom_p());
            gui_Label_3.setName("Label_3");
            gui_Label_2.setText(Double.toString(Panier.getInstance().panier.get(i).getPrix_p())+" DN" );
            gui_Label_2.setUIID("RedLabel");
            gui_Label_2.setName("Label_2");
            gui_Text_Area_1.setText(" ");
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
            
             myBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   System.out.println( gui_Label_3.getText());
                   Produits p = new Produits();
                   
                   p.setRef_p(Integer.parseInt(gui_Label_ID.getText()));
                   p.setNom_p(gui_Label_3.getText());
                   
                   Panier.getInstance().panier.remove(p);
                   System.out.println( Panier.getInstance().toString());
                   
                   new PanierFrom(resourceObjectInstance).show();
                }
            });

             
            gui_Container_4.setLeadComponent(myBtn);
      
        }
        
       
     
        
        
     
    }

    @Override
    public void localNotificationReceived(String notificationId) {
  System.out.println("Received local notification ");    }

}

