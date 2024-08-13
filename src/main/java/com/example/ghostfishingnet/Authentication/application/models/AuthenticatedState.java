package com.example.ghostfishingnet.Authentication.application.models;

import com.example.ghostfishingnet.app.entities.User;

public class AuthenticatedState extends Authstate {


    private User user;

    public AuthenticatedState(User user){
        this.user = user;
    }


    public User getUser() {
        return user;
    }
}

