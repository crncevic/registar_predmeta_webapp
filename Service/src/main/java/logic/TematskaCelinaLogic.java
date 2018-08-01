/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.TematskaCelina;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public class TematskaCelinaLogic extends AbstractLogic {

    private GenericRepository<TematskaCelina> gr;
    private Set<ConstraintViolation<TematskaCelina>> violations;

    public TematskaCelinaLogic() {
       
        gr = new GenericRepository<>();
    }

    public TematskaCelina create(TematskaCelina tematskaCelina) {
        try {
            violations = validator.validate(tematskaCelina);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO : strukturna ogranicenja
            return gr.save(tematskaCelina);

        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception e) {
            throw e;
        }
    }

    public TematskaCelina update(TematskaCelina tematskaCelina) {
        try {
            violations = validator.validate(tematskaCelina);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO : strukturna ogranicenja
            return gr.update(tematskaCelina);

        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception e) {
            throw e;
        }
    }

    public TematskaCelina delete(int id) {
        try {

            //TODO : strukturna ogranicenja
            return gr.delete(id, TematskaCelina.class);

        } catch (Exception e) {
            throw e;
        }
    }

    public List<TematskaCelina> getAll() {
        try {
            return gr.getAll(TematskaCelina.class, Constants.TEMATSKA_CELINA_FIND_ALL);
        } catch (Exception e) {
            throw e;
        }
    }

    public TematskaCelina getById(int id) {
        try {
            return gr.getSingleByParamFromNamedQuery(id, TematskaCelina.class, Constants.TEMATSKA_CELINA_FIND_BY_ID, Constants.TEMATSKA_CELINA_ID);
        } catch (Exception e) {
            throw e;
        }
    }
}