package com.example.ghostfishingnet.Authentication.application.controllers;


import com.example.ghostfishingnet.app.entities.User;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class AuthenticationBean implements Serializable {

    private User user;

    public User getUser() {
        return user;
    }

    public void logout(){
        this.user = null;
    }

    public void loginIn(User user){
        this.user = user;
    }

    public boolean isLoggedIn(){
        if (user != null){
            return true;
        }
        return false;
    }

    public    AuthenticationBean(){}
}
