package com.example.ghostfishingnet.beans;


import com.example.ghostfishingnet.beans.authentication.AuthenticationBean;
import com.example.ghostfishingnet.domain.nets.TableFilterItem;
import com.example.ghostfishingnet.entities.Net;
import com.example.ghostfishingnet.repositories.NetRepository;
import com.example.ghostfishingnet.services.SavoirService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Named
@SessionScoped
public class DashboardBean implements Serializable {



    private static final Logger LOGGER = Logger.getLogger(DashboardBean.class.getName());


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
