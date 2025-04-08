package br.unipar.progwebTrabalho1bi.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerUtil {

    // aqui guarda a "fábrica" de EntityManagers
    private static final EntityManagerFactory emf;

    static {
        // aqui é com base no que está configurado no arquivo persistence.xml
        emf = Persistence.createEntityManagerFactory("pizzariaPU");
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
