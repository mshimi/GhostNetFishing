package com.example.ghostfishingnet.Reporter.application.service;

import com.example.ghostfishingnet.Reporter.domain.service.ReporterService;
import com.example.ghostfishingnet.app.entities.Net;
import com.example.ghostfishingnet.Savior.application.repository.NetRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class ReporterServiceImpl implements ReporterService {

    @Inject
    NetRepository netRepository;


    public void reportGhostNet(Net net){
        netRepository.save(net);
    }


    public List<Net> getAllOpenNets(){
       return netRepository.findAllOpenNets();
    }


    //For the next Sprint
    @Override
    public void reportNetAsDisappeared(Net net) {
        throw new RuntimeException("not implemented Method");
    }





//            1. MUST: Als meldende Person möchte ich Geisternetze (anonym) erfassen können.
//            2. MUST: Als bergende Person will ich mich für die Bergung eines Geisternetzes eintragen können.
//            3. MUST: Als bergende Person möchte ich sehen, welche Geisternetze noch zu bergen sind.
//            4. MUST: Als bergende Person möchte ich Geisternetze als geborgen melden können.
//            5. COULD: Als bergende Person möchte ich die noch nicht geborgenen Netze auf einer Weltkarte sehen.
//            6. COULD: Als bergende Person möchte ich sehen können, wer welche Geisternetze bergen möchte, um sichggf.
//                abzustimmen und die Bergungen umzuverteilen.
//            7. COULD: Als beliebige Person möchte ich Geisternetze als verschollen melden können.

}
