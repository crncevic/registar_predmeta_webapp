/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author Petar
 */
public class GenericRepository<T> {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public T save(T entity) {

        em.persist(entity);
        return entity;

    }

    @Transactional
    public T update(T entity) {
        em.merge(entity);
        return entity;
    }

    @Transactional
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
        return query.setParameter(paramName, paramValue).getSingleResult();
    }

    public List<T> getListByParamFromNamedQuery(Object paramValue, Class c, String namedQuery, String paramName) {
        TypedQuery<T> query = em.createNamedQuery(namedQuery, c);
        return query.setParameter(paramName, paramValue).getResultList();
    }
}
