package com.example.ghostfishingnet.Authentication.application.controllers;

import com.example.ghostfishingnet.Authentication.domain.service.AuthenticationService;
import com.example.ghostfishingnet.app.entities.Savior;
import com.example.ghostfishingnet.app.entities.User;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class RegisterBean implements Serializable {


    private final AuthenticationService authenticationService;


    @Inject
    public RegisterBean(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String telephone;
    private String password;
    private String email;


    public String register() {
        String message = null;

        if (authenticationService.isEmailExists(email)) {
            message = "Email ist bereits registriert";

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));

            return null;
        } else {


            try {
                User user = new User();
                Savior savior = new Savior();

                user.setEmail(email);
                user.setPassword(password);
                savior.setTelephone(telephone);
                savior.setLastName(lastName);
                savior.setFirstName(firstName);
                user.setSavior(savior);
                savior.setUser(user);

                authenticationService.register(user);
                return "login?faces-redirect=true";


            } catch (RuntimeException e) {
                message = "Etwas ist schief gelaufen, bitte später erneut versuchen!";

            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            return null;


        }
    }


}




