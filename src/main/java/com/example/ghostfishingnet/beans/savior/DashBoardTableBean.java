package com.example.ghostfishingnet.beans.savior;


import com.example.ghostfishingnet.beans.DashboardBean;
import com.example.ghostfishingnet.domain.nets.TableFilterItem;
import com.example.ghostfishingnet.entities.Net;
import com.example.ghostfishingnet.services.SavoirService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named
public class DashBoardTableBean implements Serializable {


    @Inject
  private   SavoirService savoirService;

    @Inject
   private DashboardBean dashboardBean;


    private boolean showOnlyMyNets;
    private TableFilterItem selectedFilter;

    private Net selectedNet;

    //Getter & Setter
    public boolean isShowOnlyMyNets() {
        return showOnlyMyNets;
    }


    public void setShowOnlyMyNets(boolean showOnlyMyNets) {

        this.showOnlyMyNets = showOnlyMyNets;
    }

    public Net getSelectedNet (){
        return this.selectedNet;
    }

    public void setSelectedNet(Net net){
        this.selectedNet = net;
    }


    public TableFilterItem getSelectedFilter() {
        return selectedFilter;
    }

    public void setSelectedFilter(TableFilterItem selectedFilter) {
        this.selectedFilter = selectedFilter;
    }


    private List<Net> getAllNets() {

        switch (selectedFilter){
            case ReportedNets:
                return savoirService.getReportedNets();
            case SavedNets:
                return savoirService.getSavedNets(null);

            case MyRescueNets:
                return savoirService.getMyNetsOnProcess(dashboardBean.getAuthenticationBean().getUser().getSavior());
            case AllNets:
                return savoirService.getAllNets();

            case SavingOnProgressNets:
                return savoirService.getNetsOnProcess();

            default:
                return new ArrayList<>();
        }

    }


    public List<Net> getCurrentNets() {
        return currentNets;
    }

    private List<Net> currentNets;

    @PostConstruct
    private void init() throws IOException {
        showOnlyMyNets = false;
        this.selectedFilter = TableFilterItem.ReportedNets;
        currentNets = getAllNets();
    }


    public void addNetToMyRescueList(Net net){
        savoirService.addNetToSavoir(net,dashboardBean.getAuthenticationBean().getUser().getSavior());
        refresh();
    }

    public void RemoveNetFromMyRescueList(Net net){
        savoirService.resetNet(net);
        refresh();
    }


    public void setNetAsSaved(Net net) {
        savoirService.setNetAsSaved(net);
        refresh();

    }

    public void setNetStateAsDisappeared(Net net){
        savoirService.setNetAsDisappeared(net);
        refresh();
    }

    private void refresh() {
        this.currentNets = getAllNets();
    }


    public void onFilterChange() throws IOException {
        currentNets = getAllNets();

    }



}
