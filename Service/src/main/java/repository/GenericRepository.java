/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domain.Katedra;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author Petar
 */
public class GenericRepository<T> {

    Persistence persistence = new Persistence();
    private EntityManagerFactory emf = persistence.createEntityManagerFactory("SRP_PU");
    private EntityManager em = emf.createEntityManager();

    public T save(T entity) {

        em.persist(entity);
        return entity;

    }

    public T update(T entity) {
        em.merge(entity);
        return entity;
    }

    public T delete(Object id, Class c) {
        T entityFromDb = (T) em.getReference(c, id);
        em.remove(entityFromDb);
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
