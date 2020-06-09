/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;


/**
 *
 * @author hp
 */
public class SessionUser {
    int id;
     int cin = 9 ;
    String username;
    String password;
    String picturepath;
    String email;
    String numtel;
    
    public static SessionUser instance = null;
    public static SessionUser getInstance() {
        if (instance == null) {
            instance = new SessionUser();
        }
        return instance;
    }

    
    public SessionUser() {
       
    }

    public void SetSessionUser(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicturepath() {
        return picturepath;
    }

    public void setPicturepath(String picturepath) {
        this.picturepath = picturepath;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }
    
    
    public SessionUser(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
    
    public SessionUser(int id, String username) {
        this.id = id;
        this.username = username;
    }
    
}
