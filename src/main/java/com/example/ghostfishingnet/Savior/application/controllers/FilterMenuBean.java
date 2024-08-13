package com.example.ghostfishingnet.Savior.application.controllers;

import com.example.ghostfishingnet.Savior.application.models.FilterMenuItem;
import com.example.ghostfishingnet.Savior.application.models.TableFilterItem;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.List;

@ApplicationScoped
@Named
public class FilterMenuBean {


   private  List<FilterMenuItem> items;

    public List<FilterMenuItem> getItems() {

        return items;

    }


    @PostConstruct
    private void init(){
        this.items = List.of(new FilterMenuItem(TableFilterItem.ReportedNets, "Gemeldete Netze"),
                new FilterMenuItem(TableFilterItem.SavedNets, "Abgeschlossene Rettungen"),
                new FilterMenuItem(TableFilterItem.MyRescueNets, "Meine Bergungsaufträge"),
                new FilterMenuItem(TableFilterItem.AllNets, "Alle Netze"),
                new FilterMenuItem(TableFilterItem.SavingOnProgressNets,"Aktive Bergungen") );
    }


}
