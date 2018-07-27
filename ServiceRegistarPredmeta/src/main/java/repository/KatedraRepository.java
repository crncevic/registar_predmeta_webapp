/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import constants.Constants;
import domain.Katedra;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Petar
 */
public class KatedraRepository extends AbstractRepository {

    public KatedraRepository() {
        super();
    }

    public List<Katedra> getAll() {
        try {
            TypedQuery<Katedra> query = em.createNamedQuery(Constants.KATEDRA_FIND_ALL, Katedra.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Katedra getById(int id) {
        try {
            TypedQuery<Katedra> query = em.createNamedQuery(Constants.KATEDRA_FIND_BY_ID, Katedra.class);
            return query.setParameter("katedraId", id).getSingleResult();
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    
}
