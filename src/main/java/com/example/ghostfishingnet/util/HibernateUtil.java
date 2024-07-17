package com.example.ghostfishingnet.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class HibernateUtil {
    private final EntityManagerFactory entityManagerFactory;

    public HibernateUtil() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
    }

    @Produces
    public EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
