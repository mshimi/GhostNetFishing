package com.example.ghostfishingnet.services;

import com.example.ghostfishingnet.domain.auth.AuthenticatedState;
import com.example.ghostfishingnet.domain.auth.Authstate;
import com.example.ghostfishingnet.domain.auth.UnAuthenticatedState;
import com.example.ghostfishingnet.entities.User;
import com.example.ghostfishingnet.repositories.UserRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Objects;

@RequestScoped
public class AuthenticationService {
    @Inject
    private UserRepository userRepository;


    public Authstate login(String email, String password){
      User user = userRepository.findByEmail(email);

      if(user != null && Objects.equals(user.getPassword(), password)){
          return new AuthenticatedState(user);
      }
      else {
    return new UnAuthenticatedState("Passwort oder Email Fehlerhaft!");
      }
    }



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
