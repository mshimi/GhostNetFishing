package com.example.ghostfishingnet.util;

import jakarta.persistence.EntityManager;

@FunctionalInterface
public interface TransactionalOperation {
    void execute (EntityManager em);
}
