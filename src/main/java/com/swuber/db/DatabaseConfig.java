package com.swuber.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConfig {

    private static final String PERSISTENCE_UNIT_NAME = "swuber-persistence-unit";
    private static EntityManagerFactory emf;
    private static EntityManager entityManager;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("swuber-persistence-unit");
            entityManager = emf.createEntityManager();
            System.out.println("DB connected with JPA");
        } catch (Exception e) {
            System.err.println("Error initializing JPA: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public DatabaseConfig() {
        initializeEntityManagerFactory();
        this.entityManager = createEntityManager();
        System.out.println("DB connected with JPA");
    }

    private void initializeEntityManagerFactory() {
        if (emf == null) {
            try {
                emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
                System.out.println("EntityManagerFactory created successfully");
            } catch (Exception e) {
                System.out.println("Error creating EntityManagerFactory: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates a new EntityManager instance
     * 
     * @return A new EntityManager instance
     */
    private EntityManager createEntityManager() {
        try {
            return emf.createEntityManager();
        } catch (Exception e) {
            System.out.println("Error creating EntityManager: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets the current EntityManager
     * 
     * @return The current EntityManager instance
     */
    public EntityManager getEntityManager() {
        // If entity manager is closed, create a new one
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = createEntityManager();
        }
        return entityManager;
    }

    public void closeResources() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }

        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}