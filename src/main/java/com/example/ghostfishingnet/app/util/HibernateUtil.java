package com.example.ghostfishingnet.app.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class HibernateUtil {
    private final EntityManagerFactory entityManagerFactory;

   static String PERSISTENCEUNITNAME = "myPersistenceUnitName" ;
    public HibernateUtil() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCEUNITNAME);
    }

    @Produces
    @ApplicationScoped
  public   EntityManagerFactory getEntityManagerFactory(){
        return entityManagerFactory;
    }

    @Produces
    @ApplicationScoped
    public EntityManager createEntityManager() {

       return    entityManagerFactory.createEntityManager();

    }
}
