/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import constants.Constants;
import domain.Status;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Petar
 */
public class StatusRepository extends AbstractRepository {

    public List<Status> getAll() {
        try {
            TypedQuery<Status> query = em.createNamedQuery(Constants.STATUS_FIND_ALL, Status.class);
            return query.getResultList();
        } catch (Exception e) {
            throw e;
        }
    }
    
     public Status getById(int id) {
        try {
            TypedQuery<Status> query = em.createNamedQuery(Constants.STATUS_FIND_BY_ID, Status.class);
            return query.setParameter("statusId", id).getSingleResult();
        } catch (Exception e) {
            throw e;
        }
    }

}
