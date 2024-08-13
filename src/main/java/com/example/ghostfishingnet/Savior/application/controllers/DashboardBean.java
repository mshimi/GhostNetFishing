package com.example.ghostfishingnet.Savior.application.controllers;


import com.example.ghostfishingnet.Authentication.application.controllers.AuthenticationBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;


@Named
@SessionScoped
public class DashboardBean implements Serializable {

    @Inject
   private   AuthenticationBean authenticationBean;


    public AuthenticationBean getAuthenticationBean() {
        return authenticationBean;
    }

    public String signout() {
        authenticationBean.logout();
        return "login?faces-redirect=true";
    }

    public String goToHomePage() {
        return "index?faces-redirect=true";
    }





}
