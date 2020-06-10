/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.Date;

/**
 *
 * @author 21692
 */
public class RDV {
   
    
  
    private Technicien technicienid;
    private int idRDV;
    private Date dateRDV;
    private String objetRDV;
    private String etatRDV;
    private String detailsRDV;
    private int userid;
    private Date dateDeptRDV;

    
    
    
    public int getIdRDV() {
        return idRDV;
    }


    public Technicien getTechnicienid() {
        return technicienid;
    }

    public void setTechnicienid(Technicien technicienid) {
        this.technicienid = technicienid;
    }

    
    public void setIdRDV(int idRDV) {
        this.idRDV = idRDV;
    }

    public Date getDateRDV() {
        return dateRDV;
    }

    public void setDateRDV(Date dateRDV) {
        this.dateRDV = dateRDV;
    }

    public String getObjetRDV() {
        return objetRDV;
    }

    public void setObjetRDV(String objetRDV) {
        this.objetRDV = objetRDV;
    }

    public String getEtatRDV() {
        return etatRDV;
    }

    public void setEtatRDV(String etatRDV) {
        this.etatRDV = etatRDV;
    }

    public String getDetailsRDV() {
        return detailsRDV;
    }

    public void setDetailsRDV(String detailsRDV) {
        this.detailsRDV = detailsRDV;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Date getDateDeptRDV() {
        return dateDeptRDV;
    }

    public void setDateDeptRDV(Date dateDeptRDV) {
        this.dateDeptRDV = dateDeptRDV;
    }

    public RDV() {
    }

    public RDV( Date dateRDV, String objetRDV, String etatRDV, String detailsRDV, int userid, Date dateDeptRDV) {
       
            
        this.dateRDV = dateRDV;
        this.objetRDV = objetRDV;
        this.etatRDV = etatRDV;
        this.detailsRDV = detailsRDV;
        this.userid = userid;
        this.dateDeptRDV = dateDeptRDV;
    }

    public RDV(Technicien technicienid, int idRDV, Date dateRDV, String objetRDV, String etatRDV, String detailsRDV, int userid, Date dateDeptRDV) {
        this.technicienid = technicienid;
        this.idRDV = idRDV;
        this.dateRDV = dateRDV;
        this.objetRDV = objetRDV;
        this.etatRDV = etatRDV;
        this.detailsRDV = detailsRDV;
        this.userid = userid;
        this.dateDeptRDV = dateDeptRDV;
    }

   
}

