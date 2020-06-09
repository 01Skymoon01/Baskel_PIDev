/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;
import com.Baskel.myapp.entities.Panier;
import com.Baskel.myapp.entities.Produits;
import com.Baskel.myapp.entities.Vehicule;
import com.Baskel.myapp.services.ServiceCommande;
import com.Baskel.myapp.services.ServiceVehicule;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ShareButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
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
import com.codename1.ui.util.ImageIO;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
/**
 *
 * @author Achraf Zaafrane
 */
public class AjoutVehiculeForm extends BaseForm {
    
 public AjoutVehiculeForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    @Override
    protected boolean isCurrentInbox() {
        return true;
    }

    public AjoutVehiculeForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Ajout Vehicule", "Title"),
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
        com.codename1.ui.TextField gui_Text_Field_3 = new com.codename1.ui.TextField();
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
        gui_Component_Group_1.addComponent(gui_Text_Field_3);

        gui_Text_Field_2.setHint("Matricule");
        gui_Text_Field_2.setName("Text_Field_2");
        gui_Text_Field_1.setHint("Marque");
        gui_Text_Field_1.setName("Text_Field_1");
           gui_Text_Field_3.setHint("Type");
        gui_Text_Field_3.setName("Text_Field_3");
        gui_Container_1.addComponent(gui_Button_2);
 
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("Ajouter");
        gui_Button_2.setName("Button_2");
ShareButton sb = new ShareButton();
sb.setText("Share Screenshot");
        gui_Container_1.addComponent(sb);
        Image screenshot = Image.createImage(AjoutVehiculeForm.this.getWidth(), AjoutVehiculeForm.this.getHeight());
AjoutVehiculeForm.this.revalidate();
AjoutVehiculeForm.this.setVisible(true);
AjoutVehiculeForm.this.paintComponent(screenshot.getGraphics(), true);
String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
    ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
} catch(IOException err) {
    Log.e(err);
}
sb.setImageToShare(imageFile, "image/png");

          gui_Button_2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   Vehicule r = new Vehicule ();
                     
                    r.setMatricule(gui_Text_Field_2.getText());
                    r.setMarque(gui_Text_Field_1.getText());
                      r.setType(gui_Text_Field_3.getText());
                    try {
                        if(ServiceVehicule.getInstance().AjouterVehiculeJson(r)==1)
                            
                            new VehiculeForm(resourceObjectInstance).show();
                    } catch (IOException ex) {
                      //  Logger.getLogger(AjoutReclamationForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            });

      
        }

    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!


