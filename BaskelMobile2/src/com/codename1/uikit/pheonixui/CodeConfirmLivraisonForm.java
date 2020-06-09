/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;
import com.Baskel.myapp.entities.Commande;
import com.Baskel.myapp.entities.Livraison;
import com.Baskel.myapp.services.ServiceCommande;
import com.Baskel.myapp.services.ServiceLivraison;
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
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Achraf Zaafrane
 */
public class CodeConfirmLivraisonForm extends BaseForm{
    
    @Override
    protected boolean isCurrentInbox() {
        return true;
    }

    public CodeConfirmLivraisonForm(com.codename1.ui.util.Resources resourceObjectInstance , int id) {
        initGuiBuilderComponents(resourceObjectInstance,id);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Code confirmation", "Title"),
                        //new Label(Integer.toString(ServiceLivraison.getInstance().getLivreur().get(0).getSolde())+" DT", "DT")
                                        new Label("de commande N: "+Integer.toString(id), "Title")

                        )
        );

        installSidemenu(resourceObjectInstance);

        
    }

//-- DON'T EDIT BELOW THIS LINE!!!
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance,int id) {


    com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
     com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
     com.codename1.ui.TextField gui_Text_Field_2 = new com.codename1.ui.TextField();
   //  com.codename1.ui.TextField gui_Text_Field_1 = new com.codename1.ui.TextField();
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
       // gui_Component_Group_1.addComponent(gui_Text_Field_1);
        gui_Text_Field_2.setHint("Code de confirmation");
        gui_Text_Field_2.setName("Text_Field_2");
      //  gui_Text_Field_1.setText("desc");
       // gui_Text_Field_1.setName("Text_Field_1");
        gui_Container_1.addComponent(gui_Button_2);
 
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("Confirmer");
        gui_Button_2.setName("Button_2");

          gui_Button_2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("bbbbbbbbbbbbbbbbbb "+id);
//System.out.println( gui_Label_3.getText());
                  Livraison r = new Livraison();
                    Commande ccc= new Commande();
                    ccc.setId(id);
                   r.setIdCommande(ccc);
                      int a = r.getIdCommande().getId();
                      String cc=gui_Text_Field_2.getText();
                   System.out.println( "aaaaaaaaaaaaaaaa "+a);
                    System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD"+ServiceLivraison.getInstance().codeJson(a));
                   //  if(ServiceLivraison.getInstance().codeJson(r)==1)
                   if(cc.equals(ServiceLivraison.getInstance().codeJson(r.getIdCommande().getId())))
                       System.out.println("SUCEESSSSSSSSSSSSSS"); else System.out.println("FAAAAIIIIIIIIIIIIIIIIIIIIIL");
                    new LivraisonForm(resourceObjectInstance).show();
                   //new ReclamationForm(resourceObjectInstance).show();



                }
            });

      
        }

    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!


