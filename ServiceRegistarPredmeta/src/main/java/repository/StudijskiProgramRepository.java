/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import constants.Constants;
import domain.StudijskiProgram;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Petar
 */
public class StudijskiProgramRepository extends AbstractRepository {

    public List<StudijskiProgram> getAll() {
        try {
            TypedQuery<StudijskiProgram> query = em.createNamedQuery(Constants.STUDIJSKI_PROGRAM_FIND_ALL, StudijskiProgram.class);
            return query.getResultList();
        } catch (Exception e) {
            throw e;
        }
    }

    public StudijskiProgram getById(int id) {
        try {
            TypedQuery<StudijskiProgram> query = em.createNamedQuery(Constants.STUDIJSKI_PROGRAM_FIND_BY_ID, StudijskiProgram.class);
            return query.setParameter("studijskiProgramId", id).getSingleResult();
        } catch (Exception e) {
            throw e;
        }
    }
}
