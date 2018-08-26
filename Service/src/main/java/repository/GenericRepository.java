/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Petar
 */
public class GenericRepository<T> {

    protected static Persistence persistence = new Persistence();
    protected static EntityManagerFactory emf = persistence.createEntityManagerFactory("SRP_PU");
    protected static EntityManager em = emf.createEntityManager();
    protected static EntityTransaction et;

    public T save(T entity) {
        em.persist(entity);
        return entity;
    }

    public T update(T entity) {
        em.merge(entity);
        return entity;
    }

    public T delete_SingleKey(Object id, Class c) {
        T entityFromDb = (T) em.find(c, id);
        em.remove(entityFromDb);
        return entityFromDb;
    }

    //simulacija asocijativnih nizova, paramNames[i] odgovara paramValues[i]
    public int delete_CompositeKey(String namedQuery, String[] paramNames, int[] paramValues) {
        Query query =(Query) em.createNamedQuery(namedQuery);
       for (int i = 0; i < paramNames.length; i++) {
            query.setParameter(paramNames[i], paramValues[i]);
        }
        return query.executeUpdate();
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

    public static EntityTransaction getEntityTransactionInstance() {
        if (et == null) {
            et = em.getTransaction();
        }
        return et;
    }
}
