



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

import com.Baskel.myapp.entities.SessionUser;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 * Utility methods common to forms e.g. for binding the side menu
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {
    public void installSidemenu(Resources res) {
        Image selection = res.getImage("selection-in-sidemenu.png");
        
        Image inboxImage = null;
        if(isCurrentInbox()) inboxImage = selection;

        Image trendingImage = null;
        if(isCurrentTrending()) trendingImage = selection;
        
        Image calendarImage = null;
        if(isCurrentCalendar()) calendarImage = selection;
        
        Image statsImage = null;
        if(isCurrentStats()) statsImage = selection;
        
        Image DetailsImage = null;
        if(isCurrentDetais()) DetailsImage = selection;
        
        Button inboxButton = new Button("Commandes", inboxImage);
        inboxButton.setUIID("SideCommand");
        inboxButton.getAllStyles().setPaddingBottom(0);
        Container inbox = FlowLayout.encloseMiddle(inboxButton, 
                new Label("18", "SideCommandNumber"));
        inbox.setLeadComponent(inboxButton);
        inbox.setUIID("SideCommand");
        inboxButton.addActionListener(e -> new InboxForm().show());
        getToolbar().addComponentToSideMenu(inbox);
        
        getToolbar().addCommandToSideMenu("Shop", statsImage, e -> new ProductsForm(res).show());
        getToolbar().addCommandToSideMenu("Profie", statsImage, e -> new ProfileForm(res,SessionUser.getInstance().getEmail(),SessionUser.getInstance().getUsername(),SessionUser.getInstance().getPassword()).show());
       // getToolbar().addCommandToSideMenu("Calendar", calendarImage, e -> new CalendarForm(res).show());
        getToolbar().addCommandToSideMenu("Panier", null, e -> {new PanierFrom(res).show();});
       // getToolbar().addCommandToSideMenu("Trending", trendingImage, e -> new TrendingForm(res).show());
        getToolbar().addCommandToSideMenu("Wishlist", null, e -> {
            System.out.println("Please wait!!");
            System.out.println("Wishlist loading ...");
            new WishlistForm(res).show();
        });
        
        getToolbar().addCommandToSideMenu("Events", calendarImage, e -> new EventsForm(res).show());
        getToolbar().addCommandToSideMenu("Partenaires", null, e -> {new PartenairesForm(res).show();});
        getToolbar().addCommandToSideMenu("Reservation", trendingImage, e -> new ReservationForm(res).show());
        
        getToolbar().addCommandToSideMenu("Vos RDV", statsImage, e -> new RDVForm(res).show());
        getToolbar().addCommandToSideMenu("Passer Reclamation", null, e -> new AjoutReclamationForm(res).show());
        getToolbar().addCommandToSideMenu("Vos Reclamations", trendingImage, e -> new ReclamationForm(res).show());
        getToolbar().addCommandToSideMenu("Passer RDV", null, e -> { new AjoutRDVForm(res).show() ;});
        
        getToolbar().addCommandToSideMenu("Vehicule", statsImage, e -> new VehiculeForm(res).show());
        getToolbar().addCommandToSideMenu("Ajout Vehicule", calendarImage, e -> new AjoutVehiculeForm(res).show());
        getToolbar().addCommandToSideMenu("Livraison", trendingImage, e -> new LivraisonForm(res).show());
        
        
        
        // spacer
        getToolbar().addComponentToSideMenu(new Label(" ", "SideCommand"));

        getToolbar().addComponentToSideMenu(new Label("Baskel", "SideCommandNoPad"));
        getToolbar().addComponentToSideMenu(new Label("Tunisie,Ariana", "SideCommandSmall"));
    }

        
    protected boolean isCurrentInbox() {
        return false;
    }
    
    protected boolean isCurrentTrending() {
        return false;
    }

    protected boolean isCurrentCalendar() {
        return false;
    }

    protected boolean isCurrentStats() {
        return false;
    }
    protected boolean isCurrentDetais() {
        return false;
    }
}
