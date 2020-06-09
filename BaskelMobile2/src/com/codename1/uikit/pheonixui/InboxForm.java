/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.codename1.uikit.pheonixui;

import com.Baskel.myapp.entities.Commande;
import com.Baskel.myapp.entities.Livraison;
import com.Baskel.myapp.entities.SessionUser;
import com.Baskel.myapp.services.ServiceCommande;
import com.Baskel.myapp.services.ServiceLivraison;
import com.Baskel.myapp.services.ServiceTask;
import com.codename1.components.FloatingActionButton;
import com.codename1.messaging.Message;
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
import java.text.SimpleDateFormat;


/**
 * GUI builder created Form
 *
 * @author shai
 */
public class InboxForm extends BaseForm {

    public InboxForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    @Override
    protected boolean isCurrentInbox() {
        return true;
    }

    public InboxForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Vos Commandes", "Title"),
                        new Label(Integer.toString(ServiceCommande.getInstance().getAllCommandes().size()), "InboxNumber")
                )
        );

        installSidemenu(resourceObjectInstance);

        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("decision-32.png"), e -> {
            Message m = new Message("Bonjour Baskel j ai une question ");

Display.getInstance().sendMessage(new String[] {"nour.khedher@esprit.tn"}, "Commandes", m);
        });

        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder) fab.getUnselectedStyle().getBorder();
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());
        fab.addActionListener(e -> {
            fab.setUIID("FloatingActionButtonClose");
            Image oldImage = fab.getIcon();
            FontImage image = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "FloatingActionButton", 3.8f);
            fab.setIcon(image);
            Dialog popup = new Dialog();
            popup.setDialogUIID("Container");
            popup.setLayout(new LayeredLayout());

            Button c1 = new Button(resourceObjectInstance.getImage("contact-a.png"));
            Button c2 = new Button(resourceObjectInstance.getImage("contact-b.png"));
            Button c3 = new Button(resourceObjectInstance.getImage("contact-c.png"));
            Button trans = new Button(" ");
            trans.setUIID("Container");
            c1.setUIID("Container");
            c2.setUIID("Container");
            c3.setUIID("Container");
            Style c1s = c1.getAllStyles();
            Style c2s = c2.getAllStyles();
            Style c3s = c3.getAllStyles();

            c1s.setMarginUnit(Style.UNIT_TYPE_DIPS);
            c2s.setMarginUnit(Style.UNIT_TYPE_DIPS);
            c3s.setMarginUnit(Style.UNIT_TYPE_DIPS);

            c1s.setMarginBottom(16);
            c1s.setMarginLeft(12);
            c1s.setMarginRight(3);

            c2s.setMarginLeft(4);
            c2s.setMarginTop(5);
            c2s.setMarginBottom(10);
            c3s.setMarginRight(14);

            c3s.setMarginTop(12);
            c3s.setMarginRight(16);

            popup.add(trans).
                    add(FlowLayout.encloseIn(c1)).
                    add(FlowLayout.encloseIn(c2)).
                    add(FlowLayout.encloseIn(c3));

            ActionListener a = ee -> popup.dispose();

            trans.addActionListener(a);
            c1.addActionListener(a);
            c2.addActionListener(a);
            c3.addActionListener(a);

            popup.setTransitionInAnimator(CommonTransitions.createEmpty());
            popup.setTransitionOutAnimator(CommonTransitions.createEmpty());
            popup.setDisposeWhenPointerOutOfBounds(true);
            int t = InboxForm.this.getTintColor();
            InboxForm.this.setTintColor(0);
            popup.showPopupDialog(new Rectangle(InboxForm.this.getWidth() - 10, InboxForm.this.getHeight() - 10, 10, 10));
            InboxForm.this.setTintColor(t);
            fab.setUIID("FloatingActionButton");
            fab.setIcon(oldImage);
        });
    }

//-- DON'T EDIT BELOW THIS LINE!!!
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {

        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("InboxForm");
        setName("InboxForm");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int i;
        //ServiceCommande.getInstance().getAllCommandes().size()
        for (i = 0; i < ServiceCommande.getInstance().getAllCommandes().size() ; i++) {
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
        com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();

            addComponent(gui_Container_1);

            gui_Container_1.setName("Container_1");
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Container_2);
            gui_Container_2.setName("Container_2");
            gui_Container_2.addComponent(gui_Label_1);       
            gui_Container_2.addComponent(gui_Button_2);

            //System.out.println(ServiceCommande.getInstance().getAllCommandes().get(i).getDate());
            gui_Label_1.setText(sdf.format(ServiceCommande.getInstance().getAllCommandes().get(i).getDate()));
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
           //gui_Container_3.addComponent(gui_Button_2);

        gui_Button_2.setText("Livrer");
        gui_Button_2.setName("Button_2");
            
            gui_Label_ID.setText(Integer.toString(ServiceCommande.getInstance().getAllCommandes().get(i).getId()));
            gui_Label_3.setText("Commande n:" + ServiceCommande.getInstance().getAllCommandes().get(i).getId());
            gui_Label_3.setName("Label_3");
            gui_Label_2.setText(Double.toString(ServiceCommande.getInstance().getAllCommandes().get(i).getTOtalPrix()) + " DN");
            gui_Label_2.setUIID("RedLabel");
            gui_Label_2.setName("Label_2");
            gui_Text_Area_1.setText(Integer.toString(ServiceCommande.getInstance().getAllCommandes().get(i).getNbrProduit()) + " Produits");
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
                    System.out.println(gui_Label_ID.getText());
                    new DetailsCommandeForm(resourceObjectInstance, Integer.parseInt(gui_Label_ID.getText())).show();
                }
            });

            gui_Container_3.setLeadComponent(myBtn);
            
            
            
        gui_Button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println(gui_Label_ID.getText());
                    Livraison l = new  Livraison();
                    Commande c =new Commande();
                    c.setId(Integer.parseInt(gui_Label_ID.getText()));
                    l.setIdClient(SessionUser.getInstance().getId());
                    System.out.println("User Id= " + SessionUser.getInstance().getId());
                    l.setIdCommande(c);
                    
                    if(ServiceLivraison.getInstance().addLivraison(l)==1)
                        // bech naamel fazet l textview
                        // fazet l alert
                        // fazet  annou kif yenzel aala oui tsir update fel commande twali etat 1 w ajout mta3 livraison
                                                System.out.println("added livraison");

                          new CodeConfirmLivraisonForm(resourceObjectInstance, Integer.parseInt(gui_Label_ID.getText())).show();
                        System.out.println("added livraison");
                        } catch (IOException ex) {
                   // Logger.getLogger(InboxForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
            }
        });
            
         
        }

    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
