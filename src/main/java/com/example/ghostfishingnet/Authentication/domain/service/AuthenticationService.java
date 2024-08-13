package com.example.ghostfishingnet.Authentication.domain.service;

import com.example.ghostfishingnet.Authentication.application.models.Authstate;
import com.example.ghostfishingnet.app.entities.User;

public interface AuthenticationService {


    public Authstate login(String email, String password);

    public User register(User user);

    public boolean isEmailExists(String email);
}
