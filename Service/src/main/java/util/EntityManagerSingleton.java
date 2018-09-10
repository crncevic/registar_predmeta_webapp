/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author Petar
 */
@ApplicationScoped
public class EntityManagerSingleton {

    private static EntityManagerFactory emf;
    protected static EntityManager em;

    public static EntityManager getInstance() {
        if (em == null) {
            emf = Persistence.createEntityManagerFactory("SRP_PU");
            em = emf.createEntityManager();
        }
        return em;
    }
}
