/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.Objects;

/**
 *
 * @author pinxh
 */
public class User {
    private int id;
    private int cin;
    private String username;
    private String email;
    private String role;
    private String password;

 
   
    
    public User(int cin, String username, String email) {
        this.cin = cin;
        this.username = username;
        this.email = email;
        this.role = "a:0:{}";
    }
    public User(int id,int cin, String username, String email) {
        this.id = id;
        this.cin = cin;
        this.username = username;
        this.email = email;
        this.role = "a:0:{}";
    }

    public User(int id,int cin, String name, String email, String password) {
        this.id = id;
        this.cin = cin;
        this.email = email;
        this.username = name;
        this.password = password;
        this.role = "a:0:{}";
    }
    
    
   public User(int cin, String username, String email, String password) {
        this.cin = cin;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = "a:0:{}";
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
       @Override
    public String toString() {
        return "User{" + "id=" + id + ", cin=" + cin + ", username=" + username + ", email=" + email + ", role=" + role + ", password=" + password + '}';
    }

    public User() {
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + this.cin;
        hash = 89 * hash + Objects.hashCode(this.username);
        hash = 89 * hash + Objects.hashCode(this.email);
        hash = 89 * hash + Objects.hashCode(this.role);
        hash = 89 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.cin != other.cin) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }
    

   
    
            
    
}
