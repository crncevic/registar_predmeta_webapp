/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import constants.Constants;
import domain.TematskaCelina;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author Petar
 */
public class TematskaCelinaRepository extends AbstractRepository {

    
    @Transactional
    public TematskaCelina create(TematskaCelina tematskaCelina) {
        try {
            em.persist(tematskaCelina);
            return tematskaCelina;
        } catch (Exception e) {
            throw e;
        }
    }
    @Transactional
    public TematskaCelina update(TematskaCelina tematskaCelina) {
        try {
            em.merge(tematskaCelina);
            return tematskaCelina;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public TematskaCelina delete(int id) {
        try {
            TematskaCelina tematskaCelinaFromDb = em.getReference(TematskaCelina.class, id);
            em.remove(tematskaCelinaFromDb);
            return tematskaCelinaFromDb;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<TematskaCelina> getAll() {
        try {
            TypedQuery<TematskaCelina> query = em.createNamedQuery(Constants.TEMATSKA_CELINA_FIND_ALL, TematskaCelina.class);
            return query.getResultList();
        } catch (Exception e) {
            throw e;
        }
    }

    public TematskaCelina getById(int id) {
        try {
            TypedQuery<TematskaCelina> query = em.createNamedQuery(Constants.TEMATSKA_CELINA_FIND_ALL, TematskaCelina.class);
            return query.setParameter("tematskacelinaId", id).getSingleResult();
        } catch (Exception e) {
            throw e;
        }
    }
}
