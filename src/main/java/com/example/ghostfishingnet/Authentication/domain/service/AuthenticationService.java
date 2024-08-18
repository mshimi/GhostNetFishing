package com.example.ghostfishingnet.Authentication.domain.service;

import com.example.ghostfishingnet.Authentication.application.models.Authstate;
import com.example.ghostfishingnet.app.entities.User;

public interface AuthenticationService {

    //Sing in Method
    public Authstate loginInWithEmailAndPassword(String email, String password);

    //Create an Account Method
    public User register(User user);

    //to Check for Signup if the user already has an Account
    public boolean isEmailExists(String email);
}
