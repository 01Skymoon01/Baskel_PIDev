/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;


import com.Baskel.myapp.services.ServiceCommande;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

/**
 *
 * @author hp
 */
public class DetailsCommandeForm extends BaseForm {
 
    public DetailsCommandeForm(Resources res,int id) {

        
         initGuiBuilderComponents(res,id);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Details", "Title") ,
                        new Label("de commande N: "+Integer.toString(id), "Title")
                )
        );

        installSidemenu(res);


        
    }
    
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance,int id) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        
      
      
   
        for(int i=0;i<ServiceCommande.getInstance().ReadDetailsCommande(id).size();i++){
       
            com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
            com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
            com.codename1.ui.Container gui_Container_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
            com.codename1.ui.Label gui_Label_4 = new com.codename1.ui.Label();
            com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
            com.codename1.ui.Label gui_Label_3 = new com.codename1.ui.Label();

            com.codename1.ui.Label gui_Label_2 = new com.codename1.ui.Label();
            com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
            com.codename1.ui.Label gui_Label_6 = new com.codename1.ui.Label();

            addComponent(gui_Container_1);

           

         
         

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
            gui_Container_4.addComponent(gui_Label_4);
            gui_Label_4.setUIID("Padding2");
            gui_Label_4.setName("Label_4");
            gui_Label_4.setText(Integer.toString(ServiceCommande.getInstance().ReadDetailsCommande(id).get(i).getQteProduit())+ " x ");
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_3);
            gui_Container_3.setName("Container_3");
            gui_Container_3.addComponent(gui_Label_3);
            gui_Container_3.addComponent(gui_Label_2);
            gui_Container_3.addComponent(gui_Text_Area_1);
           // gui_Label_ID.setText(Integer.toString(ServiceCommande.getInstance().getAllCommandes().get(i).getId()));
            gui_Label_3.setText(ServiceCommande.getInstance().ReadDetailsCommande(id).get(i).getNomProduit());
            System.out.println(ServiceCommande.getInstance().ReadDetailsCommande(id).get(i).getNomProduit());
            gui_Label_3.setName("Label_3");
            gui_Label_2.setText(Double.toString(ServiceCommande.getInstance().ReadDetailsCommande(id).get(i).getPrixProduit())+" DN" );
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
      
        }
        
       
     
        
        
     
    }
}
