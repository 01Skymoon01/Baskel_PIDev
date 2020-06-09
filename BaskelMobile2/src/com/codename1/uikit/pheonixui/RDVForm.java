/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.Baskel.myapp.entities.RDV;
import com.Baskel.myapp.services.ServiceRDV;
import com.codename1.components.FloatingActionButton;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.components.FloatingActionButton;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import java.io.IOException;
import java.text.DateFormat;



/**
 *
 * @author 21692
 */
public class RDVForm extends BaseForm {

    public RDVForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    @Override
    protected boolean isCurrentInbox() {
        return true;
    }

    public RDVForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Vos RDV", "Title"),
                        new Label(Integer.toString(ServiceRDV.getInstance().getAllRdv().size()), "InboxNumber")
                )
        );

        installSidemenu(resourceObjectInstance);

       
    }

//-- DON'T EDIT BELOW THIS LINE!!!
// <editor-fold defaultstate="collapsed" desc="Generated Code">    
    
     
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {

        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("RDVForm");
        setName("RDVForm");
        
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int i;
        
      //  gui_Container_text.add(gui_Text_Field_1);
        //ServiceCommande.getInstance().getAllCommandes().size()
        for (i = 0; i < ServiceRDV.getInstance().getAllRdv().size(); i++) {
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
            com.codename1.ui.TextArea gui_Label_Detail = new com.codename1.ui.TextArea();
            com.codename1.ui.TextArea gui_Label_Date = new com.codename1.ui.TextArea();
            com.codename1.ui.TextArea gui_Label_Tech = new com.codename1.ui.TextArea();
          

            addComponent(gui_Container_1);


            gui_Container_1.setName("Container_1");
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Container_2);
            gui_Container_2.setName("Container_2");
            gui_Container_2.addComponent(gui_Label_1);
            //System.out.println(ServiceRDV.getInstance().getAllCommandes().get(i).getDate());
            gui_Label_1.setText(sdf.format(ServiceRDV.getInstance().getAllRdv().get(i).getDateDeptRDV()));
            gui_Label_1.setUIID("SmallFontLabel");
            gui_Label_1.setName("Label_1");
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, gui_Container_4);
            gui_Container_4.setName("Container_4");
            ((com.codename1.ui.layouts.FlowLayout) gui_Container_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
            gui_Container_4.addComponent(gui_Label_4);
            gui_Label_4.setUIID("Padding2");
            gui_Label_4.setName("Label_4");
            gui_Label_4.setIcon(resourceObjectInstance.getImage("label_round.png"));
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_3);
            gui_Container_3.setName("Container_3");
            gui_Container_3.addComponent(gui_Label_3);
            gui_Container_3.addComponent(gui_Label_2);
            gui_Container_3.addComponent(gui_Text_Area_1);
            gui_Container_3.addComponent(gui_Label_Detail);
            gui_Container_3.addComponent(gui_Label_Tech);
            gui_Container_3.addComponent(gui_Label_Date);
            gui_Label_ID.setText(Integer.toString(ServiceRDV.getInstance().getAllRdv().get(i).getIdRDV()));
            gui_Label_3.setText("Rendez-vous n:" + ServiceRDV.getInstance().getAllRdv().get(i).getIdRDV());
            gui_Label_3.setName("Label_3");
            gui_Label_2.setText( ServiceRDV.getInstance().getAllRdv().get(i).getObjetRDV());
            gui_Label_2.setUIID("RedLabel");
            gui_Label_2.setName("Label_2");
            gui_Text_Area_1.setText( ServiceRDV.getInstance().getAllRdv().get(i).getEtatRDV());
            gui_Label_Detail.setText (ServiceRDV.getInstance().getAllRdv().get(i).getDetailsRDV());
            System.out.println("POOOOOOOOOO55555555"+ServiceRDV.getInstance().getAllRdv().get(i).getTechnicienid());
            if(!ServiceRDV.getInstance().getAllRdv().get(i).getTechnicienid().equals("0")){
            gui_Label_Tech.setText (ServiceRDV.getInstance().getAllRdv().get(i).getTechnicienid());}
            else
            {gui_Label_Tech.setText("non traitee");}  
            gui_Label_Date.setText (sdf.format(ServiceRDV.getInstance().getAllRdv().get(i).getDateRDV()));
            gui_Text_Area_1.setUIID("SmallFontLabel");
            gui_Label_Detail.setUIID("SmallFontLabel");
            gui_Label_Tech.setUIID("SmallFontLabel");
            gui_Label_Date.setUIID("SmallFontLabel");
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
                /*   System.out.println( gui_Label_3.getText());
                  RDV r = new RDV();
                   
                   r.setIdRDV(Integer.parseInt(gui_Label_ID.getText()));
                      
                   System.out.println( gui_Label_ID);
                    System.out.println("supprimeee");
                    try {
                        if (ServiceRDV.getInstance().SupprimerRDVJson(r)==1) {
                            new RDVForm(resourceObjectInstance).show();
                        } // Logger.getLogger(ReclamationForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                      //  Logger.getLogger(RDVForm.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                         if ( Dialog.show("Completed", "Voulez-vous supprimer ou modifier le rendez-vous n:"+gui_Label_ID.getText(), "Supprimer", "Modifier") ){
                    RDV r = new RDV();
                    r.setIdRDV(Integer.parseInt(gui_Label_ID.getText()));
                    System.out.println( gui_Label_ID);
                    System.out.println("supprimeee");
                    try {
                    if(ServiceRDV.getInstance().SupprimerRDVJson(r)==1)
                    new RDVForm(resourceObjectInstance).show();
                    
                    
                    // Dialog.show("Completed", "Voulez-vous vraiment supprimer la reclamation n:"+gui_Label_ID.getText(), "Modifier", "Supprimer");
                    } catch (IOException ex) {
                    // Logger.getLogger(ReclamationForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    } else {
                    new ModifiRDVForm(resourceObjectInstance, Integer.parseInt(gui_Label_ID.getText())).show();
                    
                  
               
           
                         }
        }
        
        
                });// </editor-fold>
            gui_Container_4.setLeadComponent(myBtn);
//-- DON'T EDIT ABOVE THIS LINE!!!
        }}}
    
