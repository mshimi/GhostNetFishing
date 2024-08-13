package com.example.ghostfishingnet.app.util;

import jakarta.persistence.EntityManager;

@FunctionalInterface
public interface TransactionalOperation {
    void execute (EntityManager em);
}
