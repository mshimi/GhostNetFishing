package com.example.ghostfishingnet.Authentication.application.controllers;

import com.example.ghostfishingnet.Authentication.application.repositories.UserRepository;
import com.example.ghostfishingnet.Authentication.domain.service.AuthenticationService;
import com.example.ghostfishingnet.app.entities.Savior;
import com.example.ghostfishingnet.app.entities.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;

@Named
@RequestScoped
public class RegisterBean implements Serializable {


    private final AuthenticationService authenticationService;




    private static Logger logger = LoggerFactory.getLogger(RegisterBean.class);



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
        logger.warn("Register Method Started");
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.isValidationFailed()) {
            logger.warn("Validation Error");
            return null;
        }
        String message = null;

        if (authenticationService.isEmailExists(email)) {
            message = "Email ist bereits registriert";
            logger.warn("Email alread existes error");
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

                User savedUser = authenticationService.register(user);

                if (savedUser != null) {
                    logger.info("savedUser " + savedUser.getEmail() );
                    return "login?faces-redirect=true";
                } else {
                    logger.info("savedUser is null");
                    throw new RuntimeException();
                }

            } catch (RuntimeException e) {
                message = "Etwas ist schief gelaufen, bitte später erneut versuchen!";

            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            return null;


        }
    }


    public void validateForm(ActionEvent actionEvent) {
        logger.info("validateFrom method Started");

        FacesContext context = FacesContext.getCurrentInstance();
        if (context.isValidationFailed()) {
            context.validationFailed();
        }
    }
}




