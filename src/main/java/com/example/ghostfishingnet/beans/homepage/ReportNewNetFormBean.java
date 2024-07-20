package com.example.ghostfishingnet.beans.homepage;

import com.example.ghostfishingnet.beans.authentication.AuthenticationBean;
import com.example.ghostfishingnet.entities.Net;
import com.example.ghostfishingnet.entities.NetReporter;
import com.example.ghostfishingnet.services.NetService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jdk.javadoc.doclet.Reporter;
import org.primefaces.PrimeFaces;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.LatLng;

import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class ReportNewNetFormBean implements Serializable {

@Inject
    AuthenticationBean authenticationBean;

    @PostConstruct
    private void init(){
        anonymous = true;
        assignAssignedUserToInputFields();

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

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }



    private Integer size;
    private Double latitude;
    private Double longitude;
    private boolean anonymous;

    public String getReporterFirstName() {
        return reporterFirstName;
    }

    public void setReporterFirstName(String reporterFirstName) {
        this.reporterFirstName = reporterFirstName;
    }

    public String getReporterLastName() {
        return reporterLastName;
    }

    public void setReporterLastName(String reporterLastName) {
        this.reporterLastName = reporterLastName;
    }

    public String getReporterTelephone() {
        return reporterTelephone;
    }

    public void setReporterTelephone(String reporterTelephone) {
        this.reporterTelephone = reporterTelephone;
    }

    private String reporterFirstName;

    private String reporterLastName;

    private String reporterTelephone;


    @Inject
    NetService netService;

    public void save() throws IOException {

        try {
            netService.reportGhostNet(createNetObject());
            reset();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Net reported successfully"));

        } catch (Exception e) {

//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error reporting net", e.getMessage()));
        }


    }


    private Net createNetObject(){
        Net net = new Net();
        if(!anonymous){
            NetReporter netReporter = new NetReporter();
            netReporter.setFirstName(reporterFirstName);
            netReporter.setLastName(reporterLastName);
            netReporter.setTelephone(reporterTelephone);
            net.setReporter(netReporter);
            netReporter.setNet(net);
        }
        net.setLatitude(latitude);
        net.setLongitude(longitude);
        net.setSize(size);
        return net;
    }
    public void onPointSelect(PointSelectEvent event) {
        LatLng latlng = event.getLatLng();
        latitude = latlng.getLat();
        longitude = latlng.getLng();

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Location Selected",
                        "Latitude: " + latitude + ", Longitude: " + longitude));
    }



    public void reset(){
        this.anonymous = true;
        this.latitude = null;
        this.longitude = null;
        if(authenticationBean.isLoggedIn()){
            assignAssignedUserToInputFields();
        } else {
            this.reporterFirstName = null;
            this.reporterLastName = null;
            this.reporterTelephone = null;
        }
        this.size = null;



    }


    private void assignAssignedUserToInputFields(){
        if(authenticationBean.isLoggedIn()){
            this.reporterFirstName = authenticationBean.getUser().getSavior().getFirstName();
            this.reporterLastName = authenticationBean.getUser().getSavior().getLastName();
            this.reporterTelephone = authenticationBean.getUser().getSavior().getTelephone();
        }
    }



    public void deleteFormValues (){
        reset();
        PrimeFaces.current().resetInputs("j_idt14:new-net-form");
    }


}