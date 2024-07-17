package com.example.ghostfishingnet.entities;

import com.sun.security.auth.UserPrincipal;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "savior")
public class Savior {

    @Id
    @GeneratedValue
    private Integer id;



    private String firstName;

    private  String lastName;

    private String telephone;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @OneToMany(mappedBy = "savior", cascade = CascadeType.ALL)
    @OneToMany
    private List<Net> nets = new ArrayList<>();


    public List<Net> getNets() {
        return nets;
    }

    public void setNets(List<Net> nets) {
        this.nets = nets;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTelephone() {
        return telephone;
    }


        public void setUser(User user) {
            this.user = user;
        }

        public User getUser() {
            return user;
        }
}
