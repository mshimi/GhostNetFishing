package com.example.ghostfishingnet.Authentication.application.repositories;

import com.example.ghostfishingnet.app.entities.User;
import com.example.ghostfishingnet.app.util.TransactionUtils;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.inject.Inject;


@RequestScoped
public class UserRepository {
    @Inject
    private EntityManager entityManager;



    public void save(User user) {
        TransactionUtils.executeTransaction(entityManager,(em)->{
            em.persist(user);
        });
    }

    public User findByEmail(String email) {
        try {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
