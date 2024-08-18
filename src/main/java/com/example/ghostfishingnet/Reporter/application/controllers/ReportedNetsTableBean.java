package com.example.ghostfishingnet.Reporter.application.controllers;


import com.example.ghostfishingnet.Reporter.domain.service.ReporterService;
import com.example.ghostfishingnet.app.entities.Net;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;


@Named
@ViewScoped
public class ReportedNetsTableBean implements Serializable {


  @Inject
  private   ReporterService reporterService;



    @PostConstruct
    private void init(){
      refreshNets();
    }

    public void refreshNets(){
        nets = reporterService.getAllOpenNets();
    }

  private   List<Net> nets;

    public List<Net> getNets() {
        return nets;
    }
}
