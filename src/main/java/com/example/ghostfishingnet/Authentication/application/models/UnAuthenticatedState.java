package com.example.ghostfishingnet.Authentication.application.models;

public class UnAuthenticatedState extends Authstate {

   private String message;
    public UnAuthenticatedState(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
