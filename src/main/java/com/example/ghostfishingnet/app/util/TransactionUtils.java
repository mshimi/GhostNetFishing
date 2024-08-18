package com.example.ghostfishingnet.app.util;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

@Stateless
public class TransactionUtils {

    @Inject
    EntityManagerFactory entityManagerFactory;

    public  void executeTransaction( TransactionalOperation operation) throws RuntimeException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            operation.execute(entityManager);
            transaction.commit();
        } catch (RuntimeException ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            } else {
                throw ex;
            }
        }
//        finally {
//
//            em.close();
//        }
    }



}
