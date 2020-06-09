/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.Baskel.myapp.entities.Event;
import com.Baskel.myapp.entities.Reservation;
import com.Baskel.myapp.entities.SessionUser;
import com.Baskel.myapp.services.ServiceCommande;
import com.Baskel.myapp.services.ServiceEvent;
import com.codename1.components.FloatingActionButton;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import java.text.SimpleDateFormat;

/**
 *
 * @author Nizar
 */
public class ReservationForm extends BaseForm {

    int id_client=SessionUser.getInstance().getId();
    public ReservationForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    @Override
    protected boolean isCurrentInbox() {
        return true;
    }

    public ReservationForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Vos Reservations", "Title"),
                        new Label(Integer.toString(ServiceEvent.getInstance().AfficherReservation(id_client).size()), "InboxNumber")
                )
        );

        installSidemenu(resourceObjectInstance);




    
    }

//-- DON'T EDIT BELOW THIS LINE!!!
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {

        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("Reservations");
        setName("Reservations");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
        int i;
        //ServiceCommande.getInstance().getAllCommandes().size()
        for (i = 0; i < ServiceEvent.getInstance().AfficherReservation(id_client).size() ; i++) {
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

            addComponent(gui_Container_1);

            gui_Container_1.setName("Container_1");
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Container_2);
            gui_Container_2.setName("Container_2");
            gui_Container_2.addComponent(gui_Label_1);
         // System.out.println(ServiceEvent.getInstance().AfficherReservation(id_client).get(i).getId_event().getDate());
         if(ServiceEvent.getInstance().AfficherReservation(id_client).get(i).getId_event().getDate()!=null)
       gui_Label_1.setText(sdf.format(ServiceEvent.getInstance().AfficherReservation(id_client).get(i).getId_event().getDate())); // w hedhi kif nhotha tamel eghhhhhhhhhhhughh le le kenet mawjouda amma ma khedmetsh 
            gui_Label_1.setUIID("SmallFontLabel");
            gui_Label_1.setName("Label_1");
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, gui_Container_4);
            gui_Container_4.setName("Container_4");
            ((com.codename1.ui.layouts.FlowLayout) gui_Container_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
            gui_Container_4.addComponent(gui_Label_4);
            gui_Label_4.setUIID("Padding2");
            gui_Label_4.setName("Label_4");
            gui_Label_4.setText("X");
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_3);
            gui_Container_3.setName("Container_3");
            gui_Container_3.addComponent(gui_Label_3);
            gui_Container_3.addComponent(gui_Label_2);
            gui_Container_3.addComponent(gui_Text_Area_1);
            gui_Label_ID.setText(Integer.toString(ServiceEvent.getInstance().AfficherReservation(id_client).get(i).getId()));
            gui_Label_3.setText("Reservation  n:" + ServiceEvent.getInstance().AfficherReservation(id_client).get(i).getId());
            gui_Label_3.setName("Label_3");
            gui_Label_2.setText(ServiceEvent.getInstance().AfficherReservation(id_client).get(i).getEtat()); // hedhi lahne temshiiii
            gui_Label_2.setUIID("RedLabel");
            gui_Label_2.setName("Label_2");
            gui_Text_Area_1.setText(ServiceEvent.getInstance().AfficherReservation(id_client).get(i).getId_event().getNom()); // hedhi ma temshiiiiiiiiish heya kenet meghir to string le le dimamawjouda hedhi non amma t
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
                   Reservation r = new Reservation();
                     r.setId(Integer.parseInt(gui_Label_ID.getText()));
                   if(ServiceEvent.getInstance().SupprimerReservationJson(r)==1){
                    
                        new ReservationForm(resourceObjectInstance).show();
                        System.out.println("  deleted");
                           /***API****/
     
        
                
        

           /***API****/
                   
                   }
                  
                }
            });

            gui_Container_1.setLeadComponent(myBtn);
        }

    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
