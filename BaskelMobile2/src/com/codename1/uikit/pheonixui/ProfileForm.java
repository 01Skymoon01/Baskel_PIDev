/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author hp
 */
public class ProfileForm extends BaseForm {

    public ProfileForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public ProfileForm(Resources globalResources) {
    }

    @Override
    protected boolean isCurrentInbox() {
        return true;
    }

    public ProfileForm(com.codename1.ui.util.Resources resourceObjectInstance,String emailDB,String login,String passwordDBe) {
        initGuiBuilderComponents(resourceObjectInstance,emailDB,login,passwordDBe);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Profile "+login, "Title")
                        //new Label(Integer.toString(ServiceCommande.getInstance().getAllCommandes().size()), "InboxNumber")
                )
        );


        installSidemenu(resourceObjectInstance);



    }

    //-- DON'T EDIT BELOW THIS LINE!!!
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance,String emailDB,String login,String passwordDBe) {


        com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();

        com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();

        com.codename1.ui.Label gui_Text_Field_2 = new   com.codename1.ui.Label();
        com.codename1.ui.Label gui_Text_Field_3 = new com.codename1.ui.Label();
        com.codename1.ui.Label gui_Text_Field_1 = new com.codename1.ui.Label();
        com.codename1.ui.Label gui_Text_Field_4 = new com.codename1.ui.Label();
        com.codename1.ui.Label gui_Text_Field_5 = new   com.codename1.ui.Label();


        com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();

        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("ProfileFrom");
        setName("ProfileFrom");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");


        gui_Component_Group_1.addComponent(gui_Label_1);

        //gui_Component_Group_1.addComponent(gui_Text_Field_5);
        //gui_Component_Group_1.addComponent(gui_Text_Field_4);
        gui_Component_Group_1.addComponent(gui_Text_Field_3);
        gui_Component_Group_1.addComponent(gui_Text_Field_2);
      //  gui_Component_Group_1.addComponent(gui_Text_Field_1);

        gui_Component_Group_1.addComponent(gui_Button_2);


        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        gui_Label_1.setIcon(resourceObjectInstance.getImage("manager.png"));
        
        
        gui_Text_Field_3.setText("Adresse mail : "+emailDB);
        gui_Text_Field_3.setName("Text_Field_3");

        gui_Text_Field_2.setText("Nom d'utilisateur : "+login);
        gui_Text_Field_2.setName("Text_Field_2");


        gui_Text_Field_1.setText("Mot de passe crypté : " + passwordDBe); //image setIcone
        gui_Text_Field_1.setName("Text_Field_1");
        //gui_Text_Field_5.setText("ID du compte : " + id); //image setIcone
        //gui_Text_Field_5.setName("Text_Field_4");

        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("Consulter Commandes");
        gui_Button_2.setName("Button_2");

        gui_Button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DetailsForm().show();
            }
        });


    }

}// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!


