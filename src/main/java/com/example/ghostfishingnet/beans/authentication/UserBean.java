package com.example.ghostfishingnet.beans;

import com.example.ghostfishingnet.entities.User;
import com.example.ghostfishingnet.repositories.UserRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;


    @Named
    @RequestScoped
    public class UserBean {

        @Inject
        private UserRepository userRepository;

        @Inject
        private AuthenticationBean authenticationBean;

        private String email;
        private String password;

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

        public void login() throws ServletException, IOException {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            try {
                request.login(email, password);
                FacesContext.getCurrentInstance().getExternalContext().redirect("protected/welcome.xhtml");
            } catch (ServletException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed. Please try again.", null));
            }
        }

        public void register() {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);

            userRepository.save(user);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration successful", null));
        }
    }

