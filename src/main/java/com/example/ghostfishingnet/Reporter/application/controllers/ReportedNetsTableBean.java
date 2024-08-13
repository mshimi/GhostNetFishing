package com.example.ghostfishingnet.Reporter.application.controllers;


import com.example.ghostfishingnet.Reporter.domain.service.ReporterService;
import com.example.ghostfishingnet.app.entities.Net;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;


@Named
@RequestScoped
public class ReportedNetsTableBean implements Serializable {


  private final  ReporterService reporterService;

    @Inject
    public ReportedNetsTableBean(ReporterService reporterService){
        this.reporterService = reporterService;
    }

    @PostConstruct
    private void init(){
       nets = reporterService.getAllOpenNets();
    }


  private   List<Net> nets;

    public List<Net> getNets() {
        return nets;
    }
}
