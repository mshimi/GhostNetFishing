package com.example.ghostfishingnet.domain.auth;

public class UnAuthenticatedState extends Authstate {

   private String message;
    public UnAuthenticatedState(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
