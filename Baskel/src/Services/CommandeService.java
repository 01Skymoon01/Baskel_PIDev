/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entite.Commande;
import Entite.Details_commande;
import Entite.Panier;
import Entite.Personne;
import Entite.Produits;
import Entite.SessionUser;
import adhma.Utils.Database;
import java.sql.Connection;
import static java.sql.JDBCType.NULL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author hp
 */
public class CommandeService {

    private Connection con;
    private Statement ste;

    public CommandeService() {
        con = Database.getInstance().getConnection();

    }

    public void ajouter(Personne t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `esprit1`.`personne` (`id`, `nom`, `prenom`, `age`) VALUES (NULL, '" + t.getNom() + "', '" + t.getPrenom() + "', '" + t.getAge() + "');";
        ste.executeUpdate(requeteInsert);
    }

//************Details *********************************************************/ 
    public ObservableList<Personne> readAll() throws SQLException {
        ObservableList<Personne> arr = FXCollections.observableArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from personne");
        while (rs.next()) {
            /*int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               int age=rs.getInt("age");
               Personne p=new Personne(id, nom, prenom, age);*/
            arr.add(new Personne(rs.getString(2), rs.getString(3), rs.getInt(4)));
        }
        return arr;
    }

    public List<Integer> readIdCommandeForClient(int id) throws SQLException {
        List<Integer> arr = new ArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from commande where id_client = '" + id + "'");
        while (rs.next()) {

            Commande c = new Commande(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));
            arr.add(c.getId());

        }
        return arr;
    }
    
    
    public List<Commande> readCommande(int id) throws SQLException {
        List<Commande> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from commande where id = '" + id + "'");
        while (rs.next()) {

            Commande c = new Commande(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));
            arr.add(c);

        }
        return arr;
    }

    public ObservableList<Commande> readAllCommandes() throws SQLException {
        ObservableList<Commande> arr = FXCollections.observableArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from commande");
        while (rs.next()) {

            Commande c = new Commande(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));
            arr.add(c);

        }
        return arr;
    }

    public List<Details_commande> readAllDetailsCommandes(int id) throws SQLException {
        List<Details_commande> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from details_commande where idCommande = '" + id + "'");
        while (rs.next()) {

            Details_commande c = new Details_commande(rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5), rs.getInt(6));
            arr.add(c);

        }
        return arr;
    }

    public ObservableList<Details_commande> readAllDetailsCommandesForTableView(int id) throws SQLException {
        ObservableList<Details_commande> arr = FXCollections.observableArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from details_commande where idCommande = '" + id + "'");
        while (rs.next()) {

            Details_commande c = new Details_commande(rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5));
            arr.add(c);

        }
        return arr;
    }

    public List<Details_commande> readAllDetailsCommandesForPdf(int id) throws SQLException {
        List<Details_commande> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from details_commande where idCommande = '" + id + "'");
        while (rs.next()) {

            Details_commande c = new Details_commande(rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5));
            arr.add(c);

        }
        return arr;
    }

    /**
     * *********************************************************END DETAILS
     * *****************************
     */
    //****Supprimer *********/ 
    public void SupprimerDetails(int id) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "DELETE FROM `details_commande` WHERE idCommande = '" + id + "'";
        ste.executeUpdate(requeteInsert);
    }

    public void SupprimerCommande(int id) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "DELETE FROM `commande` WHERE id = '" + id + "'";
        ste.executeUpdate(requeteInsert);
    }

    /**
     * *****End Supprimer************
     */
    /**
     * **************Recherche
     *
     * @param id
     * @return
     * @throws java.sql.SQLException ********************************************************
     */
    public ObservableList<Commande> readParIdCommandes(String id) throws SQLException {
        ObservableList<Commande> arr = FXCollections.observableArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from commande WHERE id = '" + id + "'");
        while (rs.next()) {

            Commande c = new Commande(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));
            arr.add(c);

        }
        return arr;
    }

    /**
     * **************End Recherche ********************************************************
     */

    /**
     * *******Client Plus Fidele ******
     *
     *
     * @return
     * @throws java.sql.SQLException
     */
    public String ClientPlusFidele() throws SQLException {

        String arr = "";
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select id_client c FROM commande where etat=1 GROUP BY id_client ORDER BY c DESC LIMIT 1");
        while (rs.next()) {

            arr = Integer.toString(rs.getInt(1));
            System.out.print("offfff =" + arr);

        }
        return arr;
    }

    /**
     * *******End Client Plus Fidele *****
     */
    /**
     * *******Revenue ******
     *
     *
     * @return
     * @throws java.sql.SQLException
     */
    public String Revenue() throws SQLException {

        String arr = "";
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT FORMAT(SUM(TotalPrix),2) revenue,MONTH(date) datec FROM commande WHERE MONTH(date)=MONTH(NOW())  and etat=1");
        while (rs.next()) {

            arr = rs.getString(1);
            if ("".equals(arr)) {
                arr = "0";
            } else {
            }
        }
        return arr;
    }

    /**
     * *******End Revenue *****
     */
    
      /**
     * *******Revenue de Chaque moins ******
     *
     *
     * @return
     * @throws java.sql.SQLException
     */
    public List<Commande> RevenueChaqueMoins() throws SQLException {
        List<Commande> arr = new ArrayList<>();

        
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT SUM(TotalPrix) revenue,MONTH(date) datec FROM commande WHERE etat = 1 GROUP BY MONTH(date) ORDER BY MONTH(date) ");
        while (rs.next()) {

            System.out.println("\n"+rs.getInt(2)+"   "+rs.getString(1));
            arr.add(new Commande(rs.getInt(2),rs.getString(1)));
        }
        return arr;
    }

    /**
     * *******End Revenue de Chaque moins  *****
     */
   
    /**
     * *******Nbr Commande Non Paye ******
     *
     *
     * @return
     * @throws java.sql.SQLException
     */
    public String NbrCommandeNonPaye() throws SQLException {

        String arr = "";
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT COUNT(id) nbr FROM commande WHERE etat=0 ");
        while (rs.next()) {

            arr = Integer.toString(rs.getInt("nbr"));

        }
        return arr;
    }

    /**
     * *******End Nbr Commande Non Paye *****
     */
    
    /**
     * *******Nbr Commande Paye ******
     *
     *
     * @return
     * @throws java.sql.SQLException
     */
    public String NbrCommandePaye() throws SQLException {

        String arr = "";
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT COUNT(id) nbr FROM commande WHERE etat=1 ");
        while (rs.next()) {

            arr = Integer.toString(rs.getInt("nbr"));

        }
        return arr;
    }

    /**
     * *******End Nbr Commande Paye *****
     */
    /**
     * *******BestSaler ******
     *
     *
     * @return
     * @throws java.sql.SQLException
     */
    public String BestSaler() throws SQLException {

        String arr = "";
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select nomProduit ,SUM(qteProduit) q FROM details_commande GROUP BY idProduit  ORDER BY q DESC LIMIT 1");
        while (rs.next()) {

            arr = rs.getString("nomProduit");

        }
        return arr;
    }

    /**
     * *******End BestSaler*****
     */
    
    /**
     * *******Best5Saler ******
     *
     *
     * @return
     * @throws java.sql.SQLException
     */
    public List<Details_commande> Best5Saler() throws SQLException {
    List<Details_commande> arr = new ArrayList<>();
       // String arr = "";
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select nomProduit ,SUM(qteProduit) q FROM details_commande GROUP BY idProduit ORDER BY q DESC LIMIT 5 ");
        while (rs.next()) {

            arr.add(new Details_commande(rs.getString("nomProduit"), (int) rs.getDouble("q")));
        }
        return arr;
    }

    /**
     * *******End Best5Saler*****
     */
    /**
     * ***Modifier Etat
     *
     * @param id
     * @throws java.sql.SQLException **************
     */
    public void ModifierEtatPayement(int id) throws SQLException {

        ste = con.createStatement();
        String requeteInsert = "update commande set etat=1 where id ='" + id + "'";
        ste.executeUpdate(requeteInsert);
    }

    //*****End Modifier *************/
    //Panier  Panier Panier Panier Panier Panier Panier Panier Panier Panier Panier Panier */
    /* Ajouter Pnaier */
    public void AjouterPanier(Panier p) throws SQLException {
        ste = con.createStatement();
        String requeteInsert ;
         int arr = 0;
        ResultSet rs = ste.executeQuery("SELECT `refP` FROM `panier` WHERE refP = '" + p.getRefP() + "' and id_client='" + SessionUser.getInstance().getId() + "' " );
        while (rs.next()) {
            arr = rs.getInt(1);
            System.out.println("\n Existance :"+arr);
        }
        requeteInsert = "INSERT INTO `panier`(`id_client`, `nom_prod`, `prix_prod`, `qte_prod`, `refP`) VALUES ('" + SessionUser.getInstance().getId() + "','" + p.getNom_prod() + "','" + p.getPrix_prod() + "','" + p.getQte_prod() + "','" + p.getRefP() + "')";
        ste.executeUpdate(requeteInsert);
    }
    
        /*Ajouter Panier finale*/
    public void AjouterProduitAuPanier(Produits p,int id) throws SQLException {
        ste = con.createStatement();
        String requeteInsert ;
         int arr = 0;
        ResultSet rs = ste.executeQuery("SELECT `refP` FROM `panier` WHERE refP = '" + p.getRef_p() + "' and id_client='" + SessionUser.getInstance().getId() + "' " );
        while (rs.next()) {
            arr = rs.getInt(1);
            System.out.println("\n Existance :"+arr);
        }
      System.out.println("prix : " +p.getPrix_p()+ "image: "+ p.getImage());
        requeteInsert = "INSERT INTO `panier`(`id_client`, `nom_prod`, `prix_prod`, `qte_prod`, `refP`,`image_prod`) VALUES ('" + SessionUser.getInstance().getId() + "','" + p.getNom_p() + "','" + p.getPrix_p() + "','" + 1 + "','" + p.getRef_p() + "','" + p.getImage() + "')";
        ste.executeUpdate(requeteInsert);
    }
    
        /* Recherche Panier */
    public int RecherchePanier(Produits p,int idclient) throws SQLException {
        ste = con.createStatement();
        String requeteInsert ;
         int arr = 0;
        ResultSet rs = ste.executeQuery("SELECT `refP` FROM `panier` WHERE refP = '" + p.getRef_p() + "' and id_client='" + SessionUser.getInstance().getId() + "' " );
        while (rs.next()) {
            arr = rs.getInt(1);
            System.out.println("\n Existance :"+arr);
            return 1;
        }
        System.out.println("\n Existance :"+arr);
        return 0;
     
    }


    /* End Ajouter Pnaier */
 /* Count Pnaier */
    public int NbrProduitAuPanier(int id) throws SQLException {

        int arr = 0;
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT COUNT(`id`) as Items FROM `panier` WHERE id_client='" + SessionUser.getInstance().getId() + "'");
        while (rs.next()) {
            arr = rs.getInt(1);

        }
        return arr;
    }

    /* End Count Pnaier */

 /* Afficher Pnaier */
    public ObservableList<Panier> AfficherPanier(int id) throws SQLException {

        ObservableList<Panier> arr = FXCollections.observableArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT * FROM `panier` WHERE id_client='" + SessionUser.getInstance().getId() + "'");
        while (rs.next()) {
            int ID = rs.getInt(1);
            String NOMPRODUIT = rs.getString(3);
            Double PRIXPRODUIT = rs.getDouble(4);
            int QTEPRODUIT = rs.getInt(5);
            int REFPRODIUT = rs.getInt(7);

            Panier p = new Panier(ID, NOMPRODUIT, PRIXPRODUIT, QTEPRODUIT, id, REFPRODIUT);
            arr.add(p);
        }
        return arr;
    }

    /* End Afficher Pnaier */
    
    
 /* Afficher Pnaier */
    public List<Panier> afficherAllPanierParIdPanier(int id) throws SQLException {

        List<Panier> arr = new ArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT * FROM `panier` WHERE id='" + id + "'");
        while (rs.next()) {
            int ID = rs.getInt(1);
            String NOMPRODUIT = rs.getString(3);
            String IMAGE = rs.getString(6);
            Double PRIXPRODUIT = rs.getDouble(4);
            int QTEPRODUIT = rs.getInt(5);
            int REFPRODIUT = rs.getInt(7);
            Panier p = new Panier(ID,NOMPRODUIT, PRIXPRODUIT,QTEPRODUIT, id, REFPRODIUT, IMAGE);
           System.out.println(p.toString());
            arr.add(p);
        }
        return arr;
    }

    /* End Afficher Pnaier */
    
    
 /* Afficher Pnaier */
    public List<Integer> showIdPanierParIdClient(int id) throws SQLException {

        List<Integer> arr = new ArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT * FROM `panier` WHERE id_client='" + SessionUser.getInstance().getId() + "'");
        while (rs.next()) {
            int ID = rs.getInt(1);
            String NOMPRODUIT = rs.getString(3);
            String IMAGE = rs.getString(6);
            Double PRIXPRODUIT = rs.getDouble(4);
            int QTEPRODUIT = rs.getInt(5);
            int REFPRODIUT = rs.getInt(7);
            Panier p = new Panier(ID,NOMPRODUIT, PRIXPRODUIT,QTEPRODUIT, id, REFPRODIUT, IMAGE);
            arr.add(p.getId());
        }
        return arr;
    }

    /* End Afficher Pnaier */

 /* Supprimer an Item From Panier */
    public void SupprimerItemFromPanier(int id, String ref) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "DELETE FROM `panier` WHERE id_client='" + SessionUser.getInstance().getId() + "' and 	nom_prod ='" + ref + "'";
        ste.executeUpdate(requeteInsert);
    }

    /* End Supprimer an Item From Panier */
    
     /* Supprimer an Item From Panier */
    public void DeleteItemFromPanier(int refPanier) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "DELETE FROM `panier` WHERE id ='" + refPanier + "'";
        ste.executeUpdate(requeteInsert);
    }

    /* End Supprimer an Item From Panier */

 /* Modifier an Item From Panier */
    public void ModifierItemFromPanier(Panier p) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "update panier set qte_prod='" + p.getQte_prod() + "'  WHERE id_client='" + SessionUser.getInstance().getId() + "' and nom_prod ='" + p.getNom_prod() + "' ";
        ste.executeUpdate(requeteInsert);
    }
    
    /* Modifier an Qte From Panier */
    public void ModifierQtePanier(int ref,int qte) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "update panier set qte_prod='" + qte + "'  WHERE id ='" + ref + "'";
        ste.executeUpdate(requeteInsert);
    }

    /* Modifier an Prix From Panier */
    public void ModifierPrixPanier(int ref,double prixT) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "update panier set prix_prod='" + prixT + "'  WHERE id ='" + ref + "'";
        ste.executeUpdate(requeteInsert);
    }
    /* End Supprimer an Item From Panier */

 /* Valider Pnaier */
    public void ValiderPanier(int id) throws SQLException {
        List<Panier> ListeProduit = new ArrayList<>();
        double TotalPrix = 0;
        int nbrProduit = 0;
        ste = con.createStatement();
        ResultSet rsPanier = ste.executeQuery("SELECT * FROM `panier` WHERE id_client='" + SessionUser.getInstance().getId() + "'");
        while (rsPanier.next()) {
            int ID = rsPanier.getInt(1);
            String NOMPRODUIT = rsPanier.getString(3);
            Double PRIXPRODUIT = rsPanier.getDouble(4);
            int QTEPRODUIT = rsPanier.getInt(5);
            int REFPRODIUT = rsPanier.getInt(7);

            Panier p = new Panier(ID, NOMPRODUIT, PRIXPRODUIT, QTEPRODUIT, SessionUser.getInstance().getId() , REFPRODIUT);

            ListeProduit.add(p);
            TotalPrix = PRIXPRODUIT + TotalPrix;
            nbrProduit++;
        }
System.out.println("requete Insert client id= "+SessionUser.getInstance().getId());
LocalDate dateSys = LocalDate.now();
        String requeteInsert = "INSERT INTO `commande`(`id_client`, `TotalPrix`, `nbrProduit`,`date`,`etat`) VALUES ('" + SessionUser.getInstance().getId() + "','" + TotalPrix + "','" + nbrProduit + "','" + dateSys +"','"+ 0 +"')";
     System.out.println("requeteInsert = "+requeteInsert);

        ste.executeUpdate(requeteInsert);

        //Ajouter Au Detail 
        int IdCommande = 0;
        ResultSet rs2 = ste.executeQuery("SELECT id FROM commande WHERE date IN (SELECT max(date) FROM commande  WHERE id_client='"+SessionUser.getInstance().getId()+"')");
        while (rs2.next()) {

            IdCommande = rs2.getInt(1);
            System.out.print("idddddd= " + IdCommande);
        }
        ResultSet rsPanier2 = ste.executeQuery("SELECT * FROM `panier` WHERE id_client='" + SessionUser.getInstance().getId() + "'");
        String requeteInsert2;
        while (rsPanier2.next()) {
            int ID = rsPanier2.getInt(1);
            String NOMPRODUIT = rsPanier2.getString(3);
            Double PRIXPRODUIT = rsPanier2.getDouble(4);
            int QTEPRODUIT = rsPanier2.getInt(5);
            int REFPRODIUT = rsPanier2.getInt(7);

            Panier p = new Panier(ID, NOMPRODUIT, PRIXPRODUIT, QTEPRODUIT, SessionUser.getInstance().getId(), REFPRODIUT);

            requeteInsert2 = "INSERT INTO `details_commande`( `nomProduit`, `idProduit`, `qteProduit`, `PrixPrduit`, `idCommande`) VALUES ('" + p.getNom_prod() + "','" + p.getRefP() + "','" + p.getQte_prod() + "','" + p.getPrix_prod() + "','" + IdCommande + "')";

            ste = con.createStatement();

            ste.executeUpdate(requeteInsert2);
        }

    }
    /* End Afficher Pnaier */

    //End     Panier Panier Panier Panier Panier Panier Panier Panier Panier Panier Panier */
    
    
}
