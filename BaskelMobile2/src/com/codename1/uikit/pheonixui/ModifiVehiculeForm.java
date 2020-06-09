/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.Baskel.myapp.entities.Vehicule;
import com.Baskel.myapp.services.ServiceCommande;
import com.Baskel.myapp.services.ServiceVehicule;
import com.codename1.components.FloatingActionButton;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
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
import com.codename1.ui.util.Resources;
import java.io.IOException;
/**
 *
 * @author Achraf Zaafrane
 */
public class ModifiVehiculeForm extends BaseForm {
    
     public ModifiVehiculeForm(Resources res,int id) {

        
         initGuiBuilderComponents(res,id);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Modifier ", "Title") ,
                        new Label("Vehicule n: "+Integer.toString(id), "Title")
                )
        );

        installSidemenu(res);

      
        
    }

//-- DON'T EDIT BELOW THIS LINE!!!
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(Resources resourceObjectInstance, int id) {


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
        gui_Button_2.setText("Modifier");
        gui_Button_2.setName("Button_2");

          gui_Button_2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Vehicule r = new Vehicule();
                 
                     System.out.println("paaaaaaaaaaaaaaaaaaaaaaa555");
                     
                       r.setId(id);
                       r.setMatricule(gui_Text_Field_2.getText());
                        r.setMarque(gui_Text_Field_1.getText());
                                                r.setType(gui_Text_Field_3.getText());

                        
                        
                   System.out.println(id);
                    //System.out.println("supprimeee");
                    if (ServiceVehicule.getInstance().ModifierReclamationJson(r)==1) {
                        new VehiculeForm(resourceObjectInstance).show();
                        // Dialog.show("Completed", "Voulez-vous vraiment supprimer la reclamation n:"+gui_Label_ID.getText(), "Modifier", "Supprimer");
                    } // Logger.getLogger(ReclamationForm.class.getName()).log(Level.SEVERE, null, ex);
                    
                }
            });

      
        }

    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    
