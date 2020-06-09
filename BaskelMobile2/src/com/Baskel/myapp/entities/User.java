package com.Baskel.myapp.entities;

import java.util.Objects;

public class User {
    int id;
    int cin;
    String username;
    String password;

    public User() {

    }

    String picturepath;
    String email;
    String numtel;

    public User(int id, int cin, String username, String password, String picturepath, String email, String numtel) {
        this.id = id;
        this.cin = cin;
        this.username = username;
        this.password = password;
        this.picturepath = picturepath;
        this.email = email;
        this.numtel = numtel;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                " username=" +username+
                " password=" +password+
                " email=" +email+

                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                cin == user.cin &&
                username.equals(user.username) &&
                password.equals(user.password) &&
                Objects.equals(picturepath, user.picturepath) &&
                email.equals(user.email) &&
                Objects.equals(numtel, user.numtel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cin, username, password, picturepath, email, numtel);
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
}
