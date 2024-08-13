package com.example.ghostfishingnet.app.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "net_reporter")
public class NetReporter {

    @Id
@GeneratedValue
   private Integer id;

    private String firstName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Net getNet() {
        return net;
    }

    public void setNet(Net net) {
        this.net = net;
    }

    private  String lastName;
    private  String telephone;


    @OneToOne
    private Net net;

}
