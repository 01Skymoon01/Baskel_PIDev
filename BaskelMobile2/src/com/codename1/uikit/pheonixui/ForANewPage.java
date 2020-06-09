
package com.codename1.uikit.pheonixui;

import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 * bch t3ayet lil page :  new ForANewPage().show();
 * @author nour
 */
public class ForANewPage extends BaseForm {

    public ForANewPage(Resources res) {

        
         initGuiBuilderComponents(res);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Details", "Title") /* ,
                        new Label(Integer.toString(ServiceCommande.getInstance().getAllCommandes().size()), "InboxNumber")*/
                )
        );

        installSidemenu(res);

        getToolbar().addCommandToRightBar("", res.getImage("toolbar-profile-pic.png"), e -> {
        });
        
    }
    
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        
    }
}

