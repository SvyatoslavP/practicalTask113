package ru.panifidkin.crud.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Util {
    public static EntityManager jpaExample() {
        EntityManager em = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
            em = emf.createEntityManager();
            System.out.println("Connection successful!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection failed!");
        }
        return em;
    }
}
