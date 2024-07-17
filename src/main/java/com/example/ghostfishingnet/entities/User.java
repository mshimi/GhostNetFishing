package com.example.ghostfishingnet.entities;


import jakarta.persistence.*;

import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;

@Entity
@Table(name = "user")
public class User  {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Savior savior;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", savior firstname='" + savior.getFirstName() + '\'' +
                ", savior telephone='" + savior.getTelephone() + '\'' +
                ", savior lastName='" + savior.getLastName() + '\'' +

                '}';
    }


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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Savior getSavior() {
        return savior;
    }

    public void setSavior(Savior savior) {
        this.savior = savior;
    }
}
