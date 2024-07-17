package com.example.ghostfishingnet.repositories;


//import com.example.ghostfishingnet.entities.Savior;
import com.example.ghostfishingnet.entities.Net;
import com.example.ghostfishingnet.entities.Savior;
import com.example.ghostfishingnet.util.TransactionUtils;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transactional;

import java.util.logging.Logger;

@RequestScoped
public class SaviorRepository {

    private static final Logger LOGGER = Logger.getLogger(SaviorRepository.class.getName());

    @Inject
    EntityManager entityManager;


    public void save(Savior savior) {
        TransactionUtils.executeTransaction(entityManager,(em)->{
            em.persist(savior);
        });
    }

    public void update(Savior net) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            LOGGER.info("Updating Savoir: " + net);
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
}

