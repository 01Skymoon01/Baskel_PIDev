/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.Baskel.myapp.entities.Panier;
import com.Baskel.myapp.entities.Produits;
import com.Baskel.myapp.entities.Reclamation;
import com.Baskel.myapp.services.ServiceCommande;
import com.Baskel.myapp.services.ServiceReclamation;
import com.codename1.components.FloatingActionButton;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.messaging.Message;
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
import java.io.IOException;
import java.text.SimpleDateFormat;


/**
 *
 * @author hp
 */
public class AjoutReclamationForm extends BaseForm {

    public AjoutReclamationForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    @Override
    protected boolean isCurrentInbox() {
        return true;
    }

    public AjoutReclamationForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Passez Reclamation", "Title"),
                        new Label(Integer.toString(ServiceCommande.getInstance().getAllCommandes().size()), "InboxNumber")
                )
        );

        installSidemenu(resourceObjectInstance);

      
    }

//-- DON'T EDIT BELOW THIS LINE!!!
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {


    com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
     com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
     com.codename1.ui.TextField gui_Text_Field_2 = new com.codename1.ui.TextField();
     com.codename1.ui.TextField gui_Text_Field_1 = new com.codename1.ui.TextField();
     com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();

        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("ReclamationFrom");
        setName("ReclamationFrom");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(gui_Text_Field_2);
        gui_Component_Group_1.addComponent(gui_Text_Field_1);
        gui_Text_Field_2.setHint("Objet");
        gui_Text_Field_2.setName("Text_Field_2");
        gui_Text_Field_1.setHint("Description");
        gui_Text_Field_1.setName("Text_Field_1");
        gui_Container_1.addComponent(gui_Button_2);
 
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("Ajouter");
        gui_Button_2.setName("Button_2");

          gui_Button_2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   Reclamation r = new Reclamation ();
                     
                    r.setObjetR(gui_Text_Field_2.getText());
                    r.setDetailsR(gui_Text_Field_1.getText());
                    try {
                        if(ServiceReclamation.getInstance().AjouterReclamationJson(r)==1)
                            
                            new ReclamationForm(resourceObjectInstance).show();
                        
                          
                       Message m = new Message(gui_Text_Field_1.getText());
//m.getAttachments().put(textAttachmentUri, "text/plain");
//m.getAttachments().put(imageAttachmentUri, "image/png");
Display.getInstance().sendMessage(new String[] {"baskelunseen@gmail.com"}, gui_Text_Field_2.getText(), m);
                        
                    } catch (IOException ex) {
                      //  Logger.getLogger(AjoutReclamationForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            });

      
        }

    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!


