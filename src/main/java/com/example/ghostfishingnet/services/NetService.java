package com.example.ghostfishingnet.services;

import com.example.ghostfishingnet.entities.Net;
import com.example.ghostfishingnet.entities.NetState;
import com.example.ghostfishingnet.repositories.NetRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class NetService {

    @Inject
    NetRepository netRepository;


    public void reportGhostNet(Net net){
        netRepository.save(net);
    }


    public List<Net> getAllOpenNets(){
       return netRepository.findAllOpenNets();
    }

    public void reportNetAsDisappeared(Net net){

        net.setState(NetState.Disappeared);

        netRepository.save(net);
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
