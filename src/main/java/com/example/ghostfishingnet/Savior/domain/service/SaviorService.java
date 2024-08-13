package com.example.ghostfishingnet.Savior.domain.service;

import com.example.ghostfishingnet.app.entities.Net;
import com.example.ghostfishingnet.app.entities.Savior;

import java.util.List;

public interface SaviorService {

    public void addNetToSavoir(Net net, Savior savior);
    public void changeNetState(Net net);

    public List<Net> getNetsOnProcess();

    public List<Net> getMyNetsOnProcess(Savior savior);

    public List<Net> getAllNets();

    public List<Net> getSavedNets(Savior savior);

    public  List<Net> getReportedNets();

    public void setNetAsSaved(Net net);

    public void resetNet(Net net);

    public void setNetAsDisappeared(Net net);

}
