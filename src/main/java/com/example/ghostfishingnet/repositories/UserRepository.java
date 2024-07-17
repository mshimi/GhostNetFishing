package com.example.ghostfishingnet.repositories;

import com.example.ghostfishingnet.entities.User;
import com.example.ghostfishingnet.util.TransactionUtils;
import jakarta.ejb.LocalBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
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
