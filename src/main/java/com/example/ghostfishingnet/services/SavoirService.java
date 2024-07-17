package com.example.ghostfishingnet.services;

import com.example.ghostfishingnet.entities.Net;
import com.example.ghostfishingnet.entities.NetState;
import com.example.ghostfishingnet.entities.Savior;
import com.example.ghostfishingnet.repositories.NetRepository;
import com.example.ghostfishingnet.repositories.SaviorRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;

@RequestScoped
public class SavoirService {

    @Inject
    NetRepository netRepository;



//            5. COULD: Als bergende Person möchte ich die noch nicht geborgenen Netze auf einer Weltkarte sehen.
//            6. COULD: Als bergende Person möchte ich sehen können, wer welche Geisternetze bergen möchte, um sichggf.
//                abzustimmen und die Bergungen umzuverteilen.
//            7. COULD: Als beliebige Person möchte ich Geisternetze als verschollen melden können.


    //            2. MUST: Als bergende Person will ich mich für die Bergung eines Geisternetzes eintragen können.
    public void addNetToSavoir(Net net, Savior savior){

        final Savior s = savior;
      s.getNets().add(net);


        net.setSavior(s);
        net.setState(NetState.OnProcess);

        netRepository.update(net);
        saviorRepository.update(s);

    }


    public void changeNetState(Net net){
        net.setState(NetState.Removed);
        netRepository.save(net);
    }


    public List<Net> getNetsOnProcess(){
      return   netRepository.findNetByState(NetState.OnProcess);
    }


    public List<Net> getMyNetsOnProcess(Savior savior){
        return   netRepository.findNetBySavior(savior, NetState.OnProcess);
    }

    public List<Net> getAllNets(){
        return netRepository.findAll();
    }

    public List<Net> getSavedNets(Savior savior){

            if(savior != null){
                return   netRepository.findNetBySavior(savior, NetState.Removed);
            }

        return   netRepository.findNetByState(NetState.Removed);
    }

    //            3. MUST: Als bergende Person möchte ich sehen, welche Geisternetze noch zu bergen sind.
    public  List<Net> getReportedNets(){
        return netRepository.findNetByState(NetState.Reported);
    }

    //            4. MUST: Als bergende Person möchte ich Geisternetze als geborgen melden können.
    public void setNetAsSaved(Net net){
        net.setState(NetState.Removed);
    netRepository.update(net);

    }

    @Inject
    SaviorRepository saviorRepository;


    public void resetNet(Net net) {

        Savior savior = net.getSavior();

        if (savior != null) {
            // Remove Net from Savior's collection of Nets
            savior.getNets().remove(net);
            // Optionally, nullify the Savior reference in Net
            net.setSavior(null);

            // Update Savior in the repository
            saviorRepository.update(savior);
        }

        // Set Net's state to Reported (if needed)
        net.setState(NetState.Reported);

        // Update Net in the repository
        netRepository.update(net);

    }

    public void setNetAsDisappeared(Net net) {
        net.setState(NetState.Disappeared);
        netRepository.update(net);
    }
}
