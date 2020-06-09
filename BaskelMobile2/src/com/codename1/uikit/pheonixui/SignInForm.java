
package com.codename1.uikit.pheonixui;
import com.Baskel.myapp.entities.SessionUser;
import com.Baskel.myapp.entities.User;
import com.Baskel.myapp.services.ServiceUser;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.uikit.pheonixui.ProfileForm;




public class SignInForm extends com.codename1.ui.Form {


    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.TextField gui_Text_Field_2 = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField gui_Text_Field_1 = new com.codename1.ui.TextField();
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();



    public SignInForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public SignInForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SigninTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
        getContentPane().setUIID("SignInForm");
    }

    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_Button_2.addActionListener(callback);
    }

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {
        private com.codename1.ui.Component cmp;
        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();
            if(sourceComponent.getParent().getLeadParent() != null) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if(sourceComponent == gui_Button_2) {
                onButton_2ActionEvent(ev);
            }
        }

        public void dataChanged(int type, int index) {
        }
    }
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {

        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Authentification");
        setName("SignInForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(gui_Text_Field_2);
        gui_Component_Group_1.addComponent(gui_Text_Field_1);
        gui_Text_Field_2.setText("");
        gui_Text_Field_2.setName("Text_Field_2");
        gui_Text_Field_1.setText("");
        gui_Text_Field_1.setName("Text_Field_1");//TextArea.PASSWORD
        gui_Container_1.addComponent(gui_Button_2);
        gui_Container_1.addComponent(gui_Button_3);
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        gui_Label_1.setIcon(resourceObjectInstance.getImage("profile_picture.png"));
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("Sign In");
        gui_Button_2.setName("Button_2");
        gui_Button_3.setText("Forgot Your Password");
        gui_Button_3.setUIID("CenterLabelSmall");
        gui_Button_3.setName("Button_3");
        addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Button_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Button_1.setText("Create New Account");
        gui_Button_1.setUIID("CenterLabel");
        gui_Button_1.setName("Button_1");
        /*gui_Button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new ProfileForm(resourceObjectInstance).show();
            }
        });*/
    }


   public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {

        String login = gui_Text_Field_2.getText();

        String entredpassword = gui_Text_Field_1.getText();
        

        System.out.println(login);
        System.out.println(entredpassword);
        ServiceUser s = new ServiceUser();
        User t = ServiceUser.getInstance().Authentification(login,entredpassword);
        if (t == null){
            System.out.println("Auth failed");
        }
        else {
            SessionUser.getInstance().SetSessionUser(t.getId(),t.getUsername(),t.getEmail());
             new ProductsForm(com.codename1.ui.util.Resources.getGlobalResources()).show();
           // new ProfileForm(com.codename1.ui.util.Resources.getGlobalResources(),t.getEmail(),login,t.getPassword()).show();
            System.out.println("Auth Success");
        }
        /*ArrayList<User> t = ServiceUser.getInstance().getAllUser();
        //ArrayList<User> t =  s.getAllUser();
        System.out.println("Array list : " + t);
        String passwordDB = "";
        String emailDB = "";
        String cinDB = "";
        //String picturePath = "";
        for (int i=0;i<t.size();i++){
            if(t.get(i).getUsername().equals(login)){
                System.out.println("true");
                passwordDB = t.get(i).getPassword();
                emailDB = t.get(i).getEmail();
                //int cin = t.get(i).getCin();
                //picturePath = t.get(i).getPicturepath();
                //System.out.println(picturePath);
                break;

            }

        }

        System.out.println("password outside of loop : " + passwordDB);

        //fetch the values present in database
        byte[] passbyte;
        passbyte = entredpassword.getBytes(StandardCharsets.UTF_8);
        BCrypt.Result resultStrict = BCrypt.verifyer(BCrypt.Version.VERSION_2Y).verifyStrict(passbyte, passwordDB.getBytes());
        boolean resultStrictbool = resultStrict.verified;
        if (resultStrictbool && !entredpassword.isEmpty()) {
            new ProfileForm(com.codename1.ui.util.Resources.getGlobalResources(),emailDB,login,passwordDB).show();
            System.out.println("Auth Success !");
        }
        else {
            System.out.println("Auth Failed !");
        }*/


    }
}