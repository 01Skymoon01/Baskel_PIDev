/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceProduits;

import Entite.Categories;
import Entite.Commande;
import Entite.Personne;
import Entite.Produits;
import Entite.Ratings;
import Entite.Wishlist;
import adhma.Utils.Database;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import static java.lang.Math.PI;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Ery's Desktop
 */
public class ServiceProduits {
     private Connection con;
    private Statement ste;
    

    public ServiceProduits() {
        con = Database.getInstance().getConnection();

    }

    public void AjouterCat(Categories c) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `categories` ( `ref_c`, `libelle`) VALUES ( ? , ?);");
    pre.setInt(1, c.getRef_c());
    pre.setString(2, c.getLibelle());
  
    pre.executeUpdate();
    }
    
    public void AjouterProduct(Produits p,ObservableList<String> checkBoxList) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `produits` ( `ref_p`,`ref_c`, `nom_p`, `marque_p`, `prix_p`, `quantite_p`, `genre_p`, `description` ,`couleur_p`,`image`) VALUES ( ? , ?,?,?,?,?,?,?,?,?);");
    pre.setInt(1, p.getRef_p());
    pre.setInt(2, p.getC().getRef_c());
    pre.setString(3, p.getNom_p());
    pre.setString(4, p.getMarque_p());
    pre.setDouble(5, p.getPrix_p());
    pre.setInt(6,p.getQuantite_p());
    pre.setString(7, p.getGenre_p());
    pre.setString(8, p.getDescription());
   // pre.setString(8, p.getDescription());
 
    pre.setString(9, checkBoxList.toString());
    
    pre.setString(10,p.getImage());
    
  
    pre.executeUpdate();
    }
    

    public ObservableList<Categories> ShowCategories() throws SQLException {
    ObservableList<Categories> arr = FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from categories");
     while (rs.next()) {                
               
        int ref_c=rs.getInt("ref_c");
        String libelle=rs.getString("libelle");
               
               Categories c=new Categories( ref_c, libelle);
     arr.add(c);
     }
    return arr;
    }
    
    
    
    public Categories getCategorie(int id) throws SQLException {
     Categories c = null;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from categories where ref_c="+id);
     while (rs.next()) {                
               
        int ref_c=rs.getInt("ref_c");
        String libelle=rs.getString("libelle");
               
        c=new Categories( ref_c, libelle);
  
     }
    return c;
    }
    
    
    public ObservableList<String> ShowCategoriesLibelle() throws SQLException {
    ObservableList<String> arr = FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select ref_c,libelle from categories");
     while (rs.next()) {                
               
        
        String libelles=rs.getString("libelle");
        
        int ref_c=rs.getInt("ref_c");
        String ref_c2=Integer.toString(ref_c);
        
        String refLibelle=ref_c2.concat("-").concat(libelles);
        

     arr.add(refLibelle);
     }
    return arr;
    }
    
    
    public ObservableList<Produits> ShowProduct2(int from,int to) throws SQLException {
    ObservableList<Produits> arr = FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select ref_p,nom_p from produits limit "+from+","+to);
     while (rs.next()) {                
               
        int ref_p=rs.getInt("ref_p");
        String nom_p=rs.getString("nom_p");
               
               Produits p=new Produits( ref_p, nom_p);
     arr.add(p);
     }
    return arr;
    }
    
    
    public ObservableList<Produits> ShowProduct() throws SQLException {
    ObservableList<Produits> arr = FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select ref_p,nom_p from produits");
     while (rs.next()) {                
               
        int ref_p=rs.getInt("ref_p");
        String nom_p=rs.getString("nom_p");
               
               Produits p=new Produits( ref_p, nom_p);
     arr.add(p);
     }
    return arr;
    }
    
    
    public int countProdRows() throws SQLException {
    int count=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select count(*) from produits");
     while (rs.next()) {                
               
       rs.first();
       count=rs.getInt(1);
        
     }
    return count;
    }
    
    
   
    public Produits ShowProductFiltre(int id) throws SQLException {
   //ObservableList<Produits> arr = FXCollections.observableArrayList();
   Produits p=new Produits();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select ref_p,nom_p from produits where ref_p="+id);
     while (rs.next()) {                
               
        int ref_p=rs.getInt("ref_p");
        String nom_p=rs.getString("nom_p");
               
      p.setRef_p(ref_p);
      p.setNom_p(nom_p);
    
     }
    return p;
    }
    
    
     public Produits ShowProductFiltre2(int id,int from ,int to) throws SQLException {
   //ObservableList<Produits> arr = FXCollections.observableArrayList();
   Produits p=new Produits();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select ref_p,nom_p from produits where ref_p="+id+" limit "+from+" , "+to);
     while (rs.next()) {                
               
        int ref_p=rs.getInt("ref_p");
        String nom_p=rs.getString("nom_p");
               
      p.setRef_p(ref_p);
      p.setNom_p(nom_p);
    
     }
    return p;
    }
     
     
     
     public Produits ShowProductFiltreFront(int id) throws SQLException {
   //ObservableList<Produits> arr = FXCollections.observableArrayList();
   Produits p=new Produits();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select nom_p,genre_p,quantite_p,prix_p,marque_p,ref_p from produits where ref_p="+id);
     while (rs.next()) {                
               
       
        String nom_p=rs.getString("nom_p");
        String genre_p=rs.getString("genre_p");
        int quantite_p=rs.getInt("quantite_p");
        double prix_p=rs.getDouble("prix_p");
        String marque_p=rs.getString("marque_p");
        
         int ref_p=rs.getInt("ref_p");
        
        
               
      
      p.setNom_p(nom_p);
       p.setGenre_p(genre_p);
      p.setQuantite_p(quantite_p);
      p.setPrix_p(prix_p);
      p.setMarque_p(marque_p);
     
      p.setRef_p(ref_p);
    
     }
    return p;
    }
     
     
     /* for panier */
     
      public Produits showAllInfoOfProduct(int id) throws SQLException {
   
   Produits p=new Produits();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select nom_p,genre_p,quantite_p,prix_p,marque_p,ref_p,image from produits where ref_p="+id);
     while (rs.next()) {                
               
       
        String nom_p=rs.getString("nom_p");
        String genre_p=rs.getString("genre_p");
        int quantite_p=rs.getInt("quantite_p");
        double prix_p=rs.getDouble("prix_p");
        
       
        String marque_p=rs.getString("marque_p");
        String image_p=rs.getString("image");

         int ref_p=rs.getInt("ref_p");
        
        
               
      
      p.setNom_p(nom_p);
       p.setGenre_p(genre_p);
      p.setQuantite_p(quantite_p);
      p.setPrix_p(prix_p);
      p.setMarque_p(marque_p);
     p.setImage(image_p);
      p.setRef_p(ref_p);
    
     }
    return p;
    }
    
    public ObservableList<Produits> ShowProductRef() throws SQLException {
    ObservableList<Produits> arr = FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select ref_p from produits");
     while (rs.next()) {                
               
        int ref_p=rs.getInt("ref_p");
        //String nom_p=rs.getString("nom_p");
               
               Produits p=new Produits( ref_p);
     arr.add(p);
     }
    return arr;
    }
    
    public List<Integer> ShowProductAllRef() throws SQLException {
    List<Integer> arr =  new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select ref_p from produits");
     while (rs.next()) {                
               
        int ref_p=rs.getInt("ref_p");
        
        arr.add(ref_p);
     }
    return arr;
    }
    
  /*  public void showProduct2(int id) {
        try {
            Statement st = con.createStatement();
            String req = "select ref_c,genre_p,quantite_p,prix_p,marque_p,description,image,rating_p,solde from produits where ref_p=" + id;
            st.executeUpdate(req);
            //System.out.println("suppression ok");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduits.class.getName()).log(Level.SEVERE, null, ex);
        }

    }*/
    
    public String ShowImage(int id) throws SQLException {
    String image="";
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select image from produits where ref_p="+ id);
     while (rs.next()) {                
               
        image=rs.getString("image");

     }
    return image;
    }
    

    public InputStream ShowImage2(int id) throws SQLException {
    InputStream image = null;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select image2 from produits where ref_p="+ id);
     while (rs.next()) {                
               
      image=rs.getBinaryStream("image2");

     }
    return image;
    }
    
    public String ShowCategoryProd(int id) throws SQLException {
    String image="";
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select libelle from categories c,produits p where p.ref_c=c.ref_c and ref_p="+ id);
     while (rs.next()) {                
               
        image=rs.getString("libelle");

     }
    return image;
    }
    
    public String ShowGenre(int id) throws SQLException {
    String image="";
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select genre_p from produits where ref_p="+ id);
     while (rs.next()) {                
               
        image=rs.getString("genre_p");

     }
    return image;
    }
    
     public String showProdNom(int id) throws SQLException {
    String image="";
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select nom_p from produits where ref_p="+ id);
     while (rs.next()) {                
               
        image=rs.getString("nom_p");

     }
    return image;
    }
     
     
     public int ShowRefC(int id) throws SQLException {
    int ref=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select ref_c from produits where ref_p="+ id);
     while (rs.next()) {                
               
        ref=rs.getInt("ref_c");

     }
    return ref;
    }
     
      public int ShowRefP(int id) throws SQLException {
    int ref=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select ref_p from produits where ref_p="+ id);
     while (rs.next()) {                
               
        ref=rs.getInt("ref_p");

     }
    return ref;
    }
    
    
    public String ShowMarque(int id) throws SQLException {
    String image="";
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select marque_p from produits where ref_p="+ id);
     while (rs.next()) {                
               
        image=rs.getString("marque_p");

     }
    return image;
    }
    
    public String ShowDescription(int id) throws SQLException {
    String image="";
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select description from produits where ref_p="+ id);
     while (rs.next()) {                
               
        image=rs.getString("description");

     }
    return image;
    }
    
    public int ShowQuantite(int id) throws SQLException {
    int qte=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select quantite_p from produits where ref_p="+ id);
     while (rs.next()) {                
               
        qte=rs.getInt("quantite_p");

     }
    return qte;
    }
    
    public double ShowPrix(int id) throws SQLException {
    double qte=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select prix_p from produits where ref_p="+ id);
     while (rs.next()) {                
               
        qte=rs.getDouble("prix_p");


     }
    return qte;
    }
    
    
     public double ShowRemise(int id) throws SQLException {
    double qte=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select solde from produits where ref_p="+ id);
     while (rs.next()) {                
               
        qte=rs.getDouble("solde");


     }
    return qte;
    }
    
    
    
    public String ShowColors(int id) throws SQLException {
    String image="";
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select couleur_p from produits where ref_p="+ id);
     while (rs.next()) {                
               
        image=rs.getString("couleur_p");

     }
    return image;
    }
    
    public ObservableList<String> ShowCouleurs(int id) throws SQLException {
    ObservableList<String> arr = FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select couleur_p from produits where ref_p="+ id);
     while (rs.next()) {                
               
       
        String couleur_p=rs.getString("couleur_p");
 
     arr.add(couleur_p);
     }
    return arr;
    }
    
    
    
    
     public void SupprimerCategorie(int id) {
        try {
            Statement st = con.createStatement();
            String req = "delete from categories where ref_c=" + id;
            st.executeUpdate(req);
            System.out.println("suppression ok");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduits.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     
     public void modifierContrat(String libelle,int ref_c) {
        try {
            PreparedStatement ps = con.prepareStatement("update categories set libelle= '"+libelle+"'where ref_c='"+ref_c+"'");

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
     
     public void modifierProduitImage(String p,int ref) {
        try {
            PreparedStatement ps = con.prepareStatement("update produits set image= '"+p+"'where ref_p='"+ref+"'");

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
     
     
      public void modifierProduitNom(String p,int ref) {
        try {
            PreparedStatement ps = con.prepareStatement("update produits set nom_p= '"+p+"'where ref_p='"+ref+"'");

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
      
      public void modifierProduitDesc(String p,int ref) {
        try {
            PreparedStatement ps = con.prepareStatement("update produits set description= '"+p+"'where ref_p='"+ref+"'");

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
      
      public void modifierProduitQte(int p,int ref) {
        try {
            PreparedStatement ps = con.prepareStatement("update produits set quantite_p= '"+p+"'where ref_p='"+ref+"'");

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
      
      public void modifierProduitPrix(double p,int ref) {
        try {
            PreparedStatement ps = con.prepareStatement("update produits set prix_p= '"+p+"'where ref_p='"+ref+"'");

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
      
      
    
     
     public void supprimerProduit(int ref) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from produits where ref_p='"+ref+"'");

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
     
     public void supprimerProduit2(int ref) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from produits where ref_c='"+ref+"'");

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
     
    public void modifierProduit2(Produits p,int ref) {
        try {
            PreparedStatement ps = con.prepareStatement("update produits set nom_p ='"+p.getNom_p()+"',quantite_p='"+p.getQuantite_p()+"',prix_p'"+p.getPrix_p()+"',marque_p'"+p.getMarque_p()+"',description'"+p.getDescription()+"' where ref_p= '"+p.getRef_p()+"'");
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
    
     public void modifierProduitCouleur(ObservableList<String> checkBoxList,int ref) {
        try {
            PreparedStatement ps = con.prepareStatement("update produits set couleur_p = '"+checkBoxList.toString()+"'where ref_p='"+ref+"'");
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
     
     
     public void updateProduct(Produits p,ObservableList<String> checkBoxList) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("update produits set ref_p=? ,ref_c=?, nom_p=?, marque_p=?,prix_p=?, quantite_p=?, genre_p=? ,description=?, couleur_p=?, image=? where ref_p=?");
    pre.setInt(1, p.getRef_p());
    pre.setInt(2, p.getRef_c());
    pre.setString(3, p.getNom_p());
    pre.setString(4, p.getMarque_p());
    pre.setDouble(5, p.getPrix_p());
    pre.setInt(6,p.getQuantite_p());
    pre.setString(7, p.getGenre_p());
    pre.setString(8, p.getDescription());
   // pre.setString(8, p.getDescription());
 
    pre.setString(9, checkBoxList.toString());
    
    pre.setString(10,p.getImage());
    
  pre.setInt(11, p.getRef_p());
    pre.executeUpdate();
    }
     
     /**********************for wishliiiiist**************************/
     
     /*
      public String selectRandom1() throws SQLException {
    String image="";
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select image from produits ORDER BY RANDOM() LIMIT 1");
     while (rs.next()) {                
               
        image=rs.getString("image");

     }
    return image;
    }*/
      
       public ObservableList<Produits> ShowProductFront() throws SQLException {
    ObservableList<Produits> arr = FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select ref_p,nom_p,genre_p,quantite_p,prix_p,marque_p from produits");
     while (rs.next()) {                
               
        int ref_p=rs.getInt("ref_p");
        String nom_p=rs.getString("nom_p");
        String genre_p=rs.getString("genre_p");
        String marque_p=rs.getString("marque_p");
        int quantite_p=rs.getInt("quantite_p");
        double prix_p=rs.getDouble("prix_p");
               
        Produits p=new Produits( ref_p,nom_p,genre_p,marque_p,quantite_p,prix_p);
     arr.add(p);
     }
    return arr;
    }
     
     
       
       
    public void AjouterAWishlist(Wishlist w) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `wishlist` ( `id_client`, `nom_prod`,`prix_prod`,`qte_prod`,`image_prod`,`refP`) VALUES ( ? , ?,?,?,?,?);");
   
    pre.setInt(1, w.getId_client() );
    pre.setString(2, w.getP().getNom_p() );
    pre.setDouble(3, w.getP().getPrix_p());
    pre.setInt(4, w.getP().getQuantite_p());
    pre.setString(5, w.getP().getImage());
    pre.setInt(6, w.getP().getRef_p());
     
  
    pre.executeUpdate();
    }
    
    
    
    
    public int produitExist(int id) throws SQLException {
    int ok=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select refP from wishlist where refP="+ id);
     while (rs.next()) {                
               
        ok=rs.getInt("refP");

     }
    return ok;
    }
    
    
    public int produitExistWishlist(int id,int idC) throws SQLException {
    int ok=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select refP from wishlist where refP="+ id+" and id_client="+idC);
     while (rs.next()) {                
               
        ok=rs.getInt("refP");

     }
    return ok;
    }
    
    
   
    public ObservableList<String> showWishlistNomProd(int id) throws SQLException {
    ObservableList<String> arr = FXCollections.observableArrayList();
    String ok="";
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select image_prod from wishlist where id_client="+ id);
     while (rs.next()) {                
               
        ok=rs.getString("image_prod");
       arr.add(ok);

     }
    return arr;
    }
    
    public ObservableList<Wishlist> showWishlist(int id) throws SQLException {
    ObservableList<Wishlist> arr = FXCollections.observableArrayList();
   
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select nom_prod,refP from wishlist where id_client="+ id);
     while (rs.next()) {               
               
       // String image=rs.getString("image_prod");
        String nom=rs.getString("nom_prod");
        int ref=rs.getInt("refP");
        
        Wishlist w=new Wishlist(nom,ref);
        
       arr.add(w);

     }
    return arr;
    }
    
    
    
     public List<Produits> afficherAllWhishlistParIdWhishlist(int id) throws SQLException {

        List<Produits> arr = new ArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT * FROM `wishlist` WHERE id='" + id + "'");
        while (rs.next()) {
            int ID = rs.getInt(1);
            String NOMPRODUIT = rs.getString(3);
            String IMAGE = rs.getString(6);
            Double PRIXPRODUIT = rs.getDouble(4);
            int QTEPRODUIT = rs.getInt(5);
            int IDCLIENT = rs.getInt(2);
            int REFPRODIUT = rs.getInt(7);
           
            Produits p = new Produits();
            p = this.showAllInfoOfProduct(REFPRODIUT);
            //Wishlist w = new Wishlist(IDCLIENT,p);
            arr.add(p);
        }
        return arr;
    }
    
     public List<Integer> showIdWishlistByIdClient(int id) throws SQLException {
      List<Integer> arr = new ArrayList();
   
       ste=con.createStatement();
       ResultSet rs=ste.executeQuery("select id from wishlist where id_client="+ id);
        while (rs.next()) {               
         
        int ref=rs.getInt("id");
        
       
       arr.add(ref);

     }
    return arr;
    }
    
    
    public String showWishlistImgProd(int id) throws SQLException {
    String ok="";
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select nom_prod from wishlist where id_client="+ id);
     while (rs.next()) {                
               
        ok=rs.getString("nom_prod");

     }
    return ok;
    }
     
    
    public void SupprimerWish(int id,int idClient) {
        try {
            Statement st = con.createStatement();
            String req = "delete from wishlist where refP=" + id +" and id_client="+idClient;
            st.executeUpdate(req);
            System.out.println("suppression ok");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduits.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void SupprimerWishProd(int id) {
        try {
            Statement st = con.createStatement();
            String req = "delete from wishlist where refP=" + id ;
            st.executeUpdate(req);
            System.out.println("suppression ok");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduits.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
    
    
    
     public void updatePrix(double prix,int ref) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("update produits set prix_p="+prix+" where ref_p="+ref);

    pre.executeUpdate();
    }
     
   
    
    public void updateSolde(double remise,int ref) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("update produits set solde="+remise+" where ref_p="+ref);

    pre.executeUpdate();
    }
    
    
    
    public void AddRating(Ratings w) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `rating` ( `idClient`, `idProd`,`rate`,`totalRate`) VALUES ( ? , ?,?,?);");
   
    pre.setInt(1, w.getIdClient() );
    pre.setInt(2, w.getP().getRef_p() );
    pre.setDouble(3, w.getRate());
    pre.setDouble(4, w.getTotalRate());
   
     
  
    pre.executeUpdate();
    }
    
    
    public void UpdateRating(double rate,int idc,int ref ) throws SQLException
    {
     try {
       PreparedStatement pre=con.prepareStatement("update rating set rate="+rate+"where idClient="+idc+" and idProd="+ref);


            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
    
    
    public void UpdateRatingTotal(double total,int ref ) throws SQLException
    {
     try {
       PreparedStatement pre=con.prepareStatement("update rating set totalRate="+total+"where idProd="+ref);


            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
    
    public double showRating(int idClient,int idProd) throws SQLException {
    double ok=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select rate from rating where idClient="+ idClient+" and idProd="+idProd);
     while (rs.next()) {                
               
        ok=rs.getDouble("rate");

     }
    return ok;
    }
    
    
    public int existRating(int idClient,int idProd) throws SQLException {
    int ok=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from rating where idClient="+ idClient+" and idProd="+idProd);
     while (rs.next()) {                
               
        ok=ok+1;

     }
    return ok;
    }
    
    
    public void deleteRating(int id) throws SQLException
    {
        try {
            Statement st = con.createStatement();
            String req = "delete from rating where idProd=" + id;
            st.executeUpdate(req);
            System.out.println("suppression ok");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduits.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     public double sommeRate(int idProd) throws SQLException {
    double somme=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select sum(rate) from rating where idProd="+idProd);
     while (rs.next()) {                
               
      rs.first();
       somme=rs.getDouble(1);

     }
    return somme;
    }
     
     public double getAllRates(int idProd) throws SQLException {
    double ok=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select count(*) from rating where idProd="+idProd);
     while (rs.next()) {                
           rs.first();    
        ok=rs.getDouble(1);

     }
    return ok;
    }
    
    
    
    public int InStock() throws SQLException {
    int count=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select count(*) from produits where quantite_p > 0");
     while (rs.next()) {                
               
      rs.first();
       count=rs.getInt(1);

     }
    return count;
    }
    
    public int HorsStock() throws SQLException {
    int count=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select count(*) from produits where quantite_p = 0");
     while (rs.next()) {                
               
      rs.first();
       count=rs.getInt(1);

     }
    return count;
    }
    
    
     public void setRemiseAZero(int value,int ref) {
        try {
            PreparedStatement ps = con.prepareStatement("update produits set solde = '"+value+"'where ref_p='"+ref+"'");
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }
    
}
