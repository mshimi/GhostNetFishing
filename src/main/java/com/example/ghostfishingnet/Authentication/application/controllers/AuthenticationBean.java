package com.example.ghostfishingnet.Authentication.application.controllers;


import com.example.ghostfishingnet.Authentication.application.models.AuthenticatedState;
import com.example.ghostfishingnet.Authentication.application.models.Authstate;
import com.example.ghostfishingnet.Authentication.application.models.UnAuthenticatedState;
import com.example.ghostfishingnet.app.entities.User;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class AuthenticationBean implements Serializable {

    @PostConstruct
    private void init(){
        authstate = new UnAuthenticatedState("init state");
    }
    private User user;
    private Authstate authstate;

    public User getUser() {
        return user;
    }
    public void logout(){

        changeAuthState(new UnAuthenticatedState("logged Out"));
    }
    public void changeAuthState(Authstate authstate){
        this.authstate = authstate;
        if (authstate instanceof AuthenticatedState) {

             user = ((AuthenticatedState) authstate).getUser();


        } else if (authstate instanceof UnAuthenticatedState) {
            user = null;

        }
    }
    public boolean isLoggedIn(){
        return user != null;
    }
}
