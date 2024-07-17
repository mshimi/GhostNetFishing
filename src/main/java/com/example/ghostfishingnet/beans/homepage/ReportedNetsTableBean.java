package com.example.ghostfishingnet.beans.homepage;


import com.example.ghostfishingnet.entities.Net;
import com.example.ghostfishingnet.services.NetService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;


@Named
@RequestScoped
public class ReportedNetsTableBean implements Serializable {

    @Inject
    NetService netService;

    @PostConstruct
    private void init(){
       nets = netService.getAllOpenNets();
    }


  private   List<Net> nets;

    public List<Net> getNets() {
        return nets;
    }
}
