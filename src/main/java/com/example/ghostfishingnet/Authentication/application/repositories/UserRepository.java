package com.example.ghostfishingnet.Authentication.application.repositories;

import com.example.ghostfishingnet.app.entities.User;
import com.example.ghostfishingnet.app.util.TransactionUtils;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManagerFactory;



@Stateless
public class UserRepository {
    @Inject
    private EntityManagerFactory entityManagerFactory;
    @Inject
    TransactionUtils transactionUtils;

    public void save(User user) {
        transactionUtils.executeTransaction(em-> {
            em.persist(user);
        });
    }
    public User findByEmail(String email) {
        try {

            return entityManagerFactory.createEntityManager().createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
