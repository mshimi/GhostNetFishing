package com.example.ghostfishingnet.Authentication.application.service;

import com.example.ghostfishingnet.Authentication.application.models.AuthenticatedState;
import com.example.ghostfishingnet.Authentication.application.models.Authstate;
import com.example.ghostfishingnet.Authentication.application.models.UnAuthenticatedState;
import com.example.ghostfishingnet.Authentication.domain.service.AuthenticationService;
import com.example.ghostfishingnet.app.entities.User;
import com.example.ghostfishingnet.Authentication.application.repositories.UserRepository;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Objects;

@RequestScoped
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserRepository userRepository;


    @Override
    public Authstate loginInWithEmailAndPassword(String email, String password){
      User user = userRepository.findByEmail(email);

      if(user != null && Objects.equals(user.getPassword(), password)){
          return new AuthenticatedState(user);
      }
      else {
    return new UnAuthenticatedState("Passwort oder Email Fehlerhaft!");
      }
    }


    @Override
    public User register(User user) throws RuntimeException{

        try {
            userRepository.save(user);
            User savedUser =  userRepository.findByEmail(user.getEmail());

            return  savedUser;

        } catch (RuntimeException e){
            throw e;
        }
    }

    public boolean isEmailExists(String email) {
      User user =  userRepository.findByEmail(email);

      return user != null;
    }
}
