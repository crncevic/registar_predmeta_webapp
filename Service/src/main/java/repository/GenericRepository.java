/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domain.Predmet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Petar
 */
public class GenericRepository<T> {

    Persistence persistence = new Persistence();
    protected EntityManagerFactory emf = persistence.createEntityManagerFactory("SRP_PU");
    protected EntityManager em = emf.createEntityManager();
    protected EntityTransaction et = em.getTransaction();

    public T save(T entity) {
        et.begin();
        em.persist(entity);
        et.commit();
        return entity;
    }

    public T update(T entity) {
        et.begin();
        em.merge(entity);
        et.commit();
        return entity;
    }

    public T delete(Object id, Class c) {
        et.begin();
        T entityFromDb = (T) em.find(c, id);
        em.remove(entityFromDb);
        et.commit();
        return entityFromDb;
    }

    public List<T> getAll(Class c, String namedQuery) {
        TypedQuery<T> query = em.createNamedQuery(namedQuery, c);
        return query.getResultList();
    }

    public T getSingleByParamFromNamedQuery(Object paramValue, Class c, String namedQuery, String paramName) {       
        
        TypedQuery<T> query = em.createNamedQuery(namedQuery, c);
        List<T> result = query.setParameter(paramName, paramValue).getResultList();

        if (result == null || result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    public List<T> getListByParamFromNamedQuery(Object paramValue, Class c, String namedQuery, String paramName) {
        TypedQuery<T> query = em.createNamedQuery(namedQuery, c);
        return query.setParameter(paramName, paramValue).getResultList();
    }
}
