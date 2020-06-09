/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.Baskel.myapp.entities.RDV;
import com.Baskel.myapp.services.ServiceRDV;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

/**
 *
 * @author 21692
 */
public class ModifiRDVForm extends BaseForm {

     public ModifiRDVForm(Resources res,int id) {

        
         initGuiBuilderComponents(res,id);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Modifier ", "Title") ,
                        new Label("de Rendez-vous N: "+Integer.toString(id), "Title")
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
     com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
     Picker datePicker = new Picker();
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
        gui_Component_Group_1.addComponent(datePicker);
        datePicker.setType(Display.PICKER_TYPE_DATE);
        gui_Text_Field_2.setHint("Objet");
        gui_Text_Field_2.setName("Text_Field_2");
        gui_Text_Field_1.setHint("Description");
        gui_Text_Field_1.setName("Text_Field_1");
        gui_Container_1.addComponent(gui_Button_2);
 
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("Modifier");
        gui_Button_2.setName("Button_2");

          gui_Button_2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    RDV r = new RDV();
                 
                     System.out.println("paaaaaaaaaaaaaaaaaaaaaaa555");
                     
                       r.setIdRDV(id);
                       r.setObjetRDV(gui_Text_Field_2.getText());
                        r.setDetailsRDV(gui_Text_Field_1.getText());
                         r.setDateRDV(datePicker.getDate());
                   System.out.println( id);
                    //System.out.println("supprimeee");
                    if (ServiceRDV.getInstance().ModifierRDVJson(r)==1) {
                        new RDVForm(resourceObjectInstance).show();
                        // Dialog.show("Completed", "Voulez-vous vraiment supprimer la reclamation n:"+gui_Label_ID.getText(), "Modifier", "Supprimer");
                    } // Logger.getLogger(ReclamationForm.class.getName()).log(Level.SEVERE, null, ex);
                    
                }

      
            });

      
        }

    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!



