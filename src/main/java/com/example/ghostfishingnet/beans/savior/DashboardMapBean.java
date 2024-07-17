package com.example.ghostfishingnet.beans.savior;

import com.example.ghostfishingnet.beans.DashboardBean;
import com.example.ghostfishingnet.entities.Net;
import com.example.ghostfishingnet.services.SavoirService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.annotation.View;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.component.gmap.GMap;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.*;

import java.io.Serializable;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;

@Named
@ViewScoped
public class DashboardMapBean implements Serializable {


    private static final Logger LOGGER = Logger.getLogger(DashboardMapBean.class.getName());


    private final static MapModel<Net> simpleModel = new DefaultMapModel();




    private List<Net> nets;

    private StartingPosition startingPosition;

    private Marker<Net> selectedMarker;

    @PostConstruct
    public void init() {
       final List<Net> nets = getReportedNetsNets();
        this.startingPosition = initStartingPosition( nets.isEmpty() ? null : nets.get(0));

        if(!simpleModel.getMarkers().isEmpty()){
            simpleModel.getMarkers().clear();
        }

     nets.forEach(net -> {
         simpleModel.addOverlay(mapNetToMarket(net));
     });
    }

    public MapModel<Net> getSimpleModel() {
        return simpleModel;
    }


    public StartingPosition getStartingPosition() {
        return startingPosition;
    }

    @Inject
    private SavoirService savoirService;

    private List<Net> getReportedNetsNets(){
       return savoirService.getReportedNets();
    }

    public List<Net> getNets(){
        return nets;
    }

    private Marker<Net> mapNetToMarket (Net net){
       Marker<Net> marker = new Marker<>(new LatLng(net.getLatitude(), net.getLongitude()), "Größe: " + net.getSize() + "M" , net);


       marker.setClickable(true);
       return marker;
    }



    private StartingPosition initStartingPosition (Net net){

        if(net == null){
            return new StartingPosition(51.53657919828676,7.172135901110828);
        }
        return new StartingPosition(net.getLatitude(),net.getLongitude());
    }


    public void onMarkerSelect(OverlaySelectEvent<Net> event) {
        if (event.getOverlay() instanceof Marker<?>) {
            selectedMarker = (Marker<Net>) event.getOverlay();
        }
    }


    public Marker<Net> getSelectedMarker() {
        return selectedMarker;
    }

    @Inject
    DashboardBean dashboardBean;


    public void addNetToMyRescueList(){
        savoirService.addNetToSavoir(selectedMarker.getData(),dashboardBean.getAuthenticationBean().getUser().getSavior());
        init();

    }


    public static class StartingPosition{

        private double lat;
        private double lon;

       public StartingPosition(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }


        public double getLat() {
            return lat;
        }

        public double getLon() {
            return lon;
        }


        public String getPosition (){

           return lat + ", " + lon ;
        }
    }

}
