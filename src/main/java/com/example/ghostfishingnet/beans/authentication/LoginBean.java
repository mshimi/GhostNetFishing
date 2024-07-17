package com.example.ghostfishingnet.beans.authentication;

import com.example.ghostfishingnet.beans.authentication.AuthenticationBean;
import com.example.ghostfishingnet.domain.auth.AuthenticatedState;
import com.example.ghostfishingnet.domain.auth.Authstate;
import com.example.ghostfishingnet.domain.auth.UnAuthenticatedState;
import com.example.ghostfishingnet.entities.User;
import com.example.ghostfishingnet.services.AuthenticationService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;


@Named
@ViewScoped

public class LoginBean implements Serializable {


    @Inject
    private AuthenticationService authService;

    @Inject
    private AuthenticationBean authenticationBean;


    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String password;

    private String message;

    public String login() {
        Authstate authstate = authService.login(email, password);
        String message = null;
        if (authstate instanceof AuthenticatedState) {

            User user = ((AuthenticatedState) authstate).getUser();
            authenticationBean.loginIn(user);
            return "dashboard?faces-redirect=true";
        } else if (authstate instanceof UnAuthenticatedState) {
            message = ((UnAuthenticatedState) authstate).getMessage();

        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    return  null;
    }


}
