package com.example.ghostfishingnet.app.entities;


import jakarta.inject.Named;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Entity
@Table(name = "net")
@Named
public class Net {
    public Net() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public NetState getState() {
        return state;
    }

    public String getStateText(){
        if(state.equals(NetState.Disappeared)){
            return "Verschollen";
        } else if (state.equals(NetState.OnProcess)) {
            return "Bergung bevorstehend";
        } else if(state.equals(NetState.Reported)){
            return  "Gemeldet";
        } else {
            return "Geborgen";
        }
    }

    public void setState(NetState state) {
        this.state = state;
    }

    public NetReporter getReporter() {
        return reporter;
    }

    public void setReporter(NetReporter reporter) {
        this.reporter = reporter;
    }

    public Savior getSavior() {
        return savior;
    }

    public void setSavior(Savior savior) {
        this.savior = savior;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    public String getReporterFullName() {
        if (reporter != null) {
            return reporter.getFirstName() + " " + reporter.getLastName();
        } else {
            return "anonym";
        }
    }

    @Id
    @GeneratedValue
   private Integer id;

    private Integer size;

    private Double  latitude;
    private Double longitude;


    private NetState state = NetState.Reported;

    @OneToOne (cascade = CascadeType.ALL)// mappedBy specifies the property in NetReporter that owns the relationship
    @JoinColumn(name = "reporter_id" )
    private NetReporter reporter;

    @ManyToOne
    @JoinColumn(name = "savior_id") // Many nets can belong to one savior
    private Savior savior;

    @CreationTimestamp()
    private Date createdAt;


    @Override
    public String toString() {
        return "Net{" +
                "id=" + id +
                ", size=" + size +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", state=" + state +
                ", reporter=" + reporter +
                ", savior=" + savior +
                ", createdAt=" + createdAt +
                '}';
    }
}
