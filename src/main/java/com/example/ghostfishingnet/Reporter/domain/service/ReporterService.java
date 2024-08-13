package com.example.ghostfishingnet.Reporter.domain.service;

import com.example.ghostfishingnet.app.entities.Net;

import java.util.List;

public interface ReporterService {

    public void reportGhostNet(Net net);


    public List<Net> getAllOpenNets();


    //for the next Sprint
    public void reportNetAsDisappeared(Net net);
}
