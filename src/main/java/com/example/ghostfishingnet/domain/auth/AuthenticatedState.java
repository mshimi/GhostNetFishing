package com.example.ghostfishingnet.domain.auth;

import com.example.ghostfishingnet.entities.User;

public class AuthenticatedState extends Authstate {


    private User user;

    public AuthenticatedState(User user){
        this.user = user;
    }


    public User getUser() {
        return user;
    }
}

