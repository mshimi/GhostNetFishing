package com.example.ghostfishingnet.app.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TransactionUtils {


    public static void executeTransaction(EntityManager em, TransactionalOperation operation) throws RuntimeException {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            operation.execute(em);
            transaction.commit();
        } catch (RuntimeException ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            } else {
                throw ex;
            }
        } finally {
            em.close();
        }
    }
}
