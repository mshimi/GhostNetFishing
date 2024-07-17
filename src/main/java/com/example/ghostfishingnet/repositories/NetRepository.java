package com.example.ghostfishingnet.repositories;


import com.example.ghostfishingnet.entities.Net;
import com.example.ghostfishingnet.entities.NetState;
import com.example.ghostfishingnet.entities.Savior;
import com.example.ghostfishingnet.entities.User;
import com.example.ghostfishingnet.util.TransactionUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.logging.Logger;

import java.util.List;


@RequestScoped
public class NetRepository {

    private static final Logger LOGGER = Logger.getLogger(NetRepository.class.getName());


    @Inject
    EntityManager entityManager;

    public void save(Net net) {
        LOGGER.info("Saving net: " + net);
        try {
            TransactionUtils.executeTransaction(entityManager,(em)->{
                em.persist(net);
            });
        }catch (RuntimeException e){
            LOGGER.warning("Saving net Error: " + e);
        }
    }


    public void update(Net net) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            LOGGER.info("Updating net: " + net);
            entityManager.merge(net); // Merge the entity to update its state in the database
            transaction.commit();
        } catch (RuntimeException ex) {
            if (transaction.isActive()) {
                transaction.rollback();
                LOGGER.info("Rolling Back: " + ex);
            }
            throw ex; // Rethrow the exception after rollback
        }
    }


    public List<Net> findAll(){
        TypedQuery<Net> query = entityManager.createQuery("SELECT n FROM Net n", Net.class);
        return query.getResultList();
    }


    public List<Net> findNetByState(NetState state) {
        TypedQuery<Net> query = entityManager.createQuery("SELECT n FROM Net n WHERE n.state = :state", Net.class);
        query.setParameter("state", state);
        return query.getResultList();
    }

    public List<Net> findNetBySavior(Savior savior) {
        TypedQuery<Net> query = entityManager.createQuery("SELECT n FROM Net n WHERE n.savior = :savior", Net.class);
        query.setParameter("savior", savior);
        return query.getResultList();
    }

    public List<Net> findNetBySavior(Savior savior, NetState state) {
        TypedQuery<Net> query = entityManager.createQuery("SELECT n FROM Net n WHERE n.savior = :savior and n.state = :state", Net.class);
        query.setParameter("savior", savior);
        query.setParameter("state", state);
        return query.getResultList();
    }

    public List<Net> findAllOpenNets( ) {

        final  NetState removed = NetState.Removed;
        final  NetState disappeared = NetState.Disappeared;

        TypedQuery<Net> query = entityManager.createQuery("SELECT n FROM Net n WHERE  n.state != :removed and n.state != :disappeared", Net.class);

        query.setParameter("removed", removed);
        query.setParameter("disappeared", disappeared);
        return query.getResultList();
    }

}
