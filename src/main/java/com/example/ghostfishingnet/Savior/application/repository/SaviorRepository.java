package com.example.ghostfishingnet.Savior.application.repository;


//import com.example.ghostfishingnet.entities.Savior;
import com.example.ghostfishingnet.app.entities.Savior;
import com.example.ghostfishingnet.app.util.TransactionUtils;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;


@RequestScoped
public class SaviorRepository {


    @Inject
    EntityManagerFactory entityManagerFactory;

    @Inject
    TransactionUtils transactionUtils;

    public void save(Savior savior) {
        transactionUtils.executeTransaction((em)->{
            em.persist(savior);
        });
    }

    public void update(Savior net) {
//     EntityManager   entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            transaction.begin();
//            entityManager.merge(net); // Merge the entity to update its state in the database
//            transaction.commit();
//        } catch (RuntimeException ex) {
//            if (transaction.isActive()) {
//                transaction.rollback();
//            }
//            throw ex; // Rethrow the exception after rollback
//        }

        transactionUtils.executeTransaction(em-> {
            em.merge(net);
        });

    }
}

