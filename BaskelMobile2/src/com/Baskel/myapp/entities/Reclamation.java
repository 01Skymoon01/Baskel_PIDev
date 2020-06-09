/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baskel.myapp.entities;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author 21692
 */
public class Reclamation {
    
    private int idR;
    private Date dateR;
    private String objetR;
    private String etatR;
    private String detailsR;
    private int userid;

    public Reclamation(int userid, Date dateR ,String objetR, String etatR, String detailsR ) {
        this.dateR = dateR;
        this.objetR = objetR;
        this.etatR = etatR;
        this.detailsR = detailsR;
        this.userid = userid;
    }

    public Reclamation() {
    }

  
    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public Date getDateR() {
        return dateR;
    }

    public void setDateR(Date dateR) {
        this.dateR = dateR;
    }

    public String getObjetR() {
        return objetR;
    }

    public void setObjetR(String objetR) {
        this.objetR = objetR;
    }

    public String getEtatR() {
        return etatR;
    }

    public void setEtatR(String etatR) {
        this.etatR = etatR;
    }

    public String getDetailsR() {
        return detailsR;
    }

    public void setDetailsR(String detailsR) {
        this.detailsR = detailsR;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
    
     
    
}

