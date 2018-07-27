/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import constants.Constants;
import domain.Nastavnik;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Petar
 */
public class NastavnikRepository extends AbstractRepository {

    public List<Nastavnik> getAll() {
        try {
            TypedQuery<Nastavnik> query = em.createNamedQuery(Constants.NASTAVNIK_FIND_ALL, Nastavnik.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Nastavnik getById(int id) {
        try {
            TypedQuery<Nastavnik> query = em.createNamedQuery(Constants.NASTAVNIK_FIND_BY_ID, Nastavnik.class);
            return query.setParameter("nastavnikId", id).getSingleResult();
        } catch (Exception ex) {
            throw ex;
        }
    }
}
