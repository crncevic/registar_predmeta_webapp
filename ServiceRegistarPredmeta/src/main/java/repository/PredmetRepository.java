/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import constants.Constants;
import domain.Predmet;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author Petar
 */
public class PredmetRepository extends AbstractRepository {

    @Transactional
    public Predmet create(Predmet predmet) {
        try {
            em.persist(predmet);
            return predmet;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Transactional
    public Predmet update(Predmet predmet) {
        try {
            em.merge(predmet);
            return predmet;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public Predmet delete(int id) throws Exception {
        try {

            Predmet predmetFromDb = em.getReference(Predmet.class, id);

            if (predmetFromDb == null) {
                throw new Exception("Predmet sa id:" + id + " ne postoji");
            }
            em.remove(predmetFromDb);
            return predmetFromDb;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Predmet> getAll() {
        try {
            TypedQuery<Predmet> query = em.createNamedQuery(Constants.PREMDET_FIND_ALL, Predmet.class);
            return query.getResultList();
        } catch (Exception e) {
            throw e;
        }
    }

    public Predmet getById(int id) {
        try {
            TypedQuery<Predmet> query = em.createNamedQuery(Constants.PREMDET_FIND_BY_ID, Predmet.class);
            return query.setParameter("predmetId", id).getSingleResult();
        } catch (Exception e) {
            throw e;
        }
    }

}
