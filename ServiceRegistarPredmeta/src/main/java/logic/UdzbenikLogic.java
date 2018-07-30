/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.Udzbenik;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public class UdzbenikLogic extends AbstractLogic {

    private GenericRepository<Udzbenik> gr;
    private Set<ConstraintViolation<Udzbenik>> violations;

    public UdzbenikLogic() {
        gr = new GenericRepository<>();
    }

    public Udzbenik create(Udzbenik udzbenik) {
        try {
            violations = validator.validate(udzbenik);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO : strukturna ogranicenja
            return gr.save(udzbenik);

        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception e) {
            throw e;
        }
    }

    public Udzbenik update(Udzbenik udzbenik) {
        try {
            violations = validator.validate(udzbenik);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO : strukturna ogranicenja
            return gr.update(udzbenik);

        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception e) {
            throw e;
        }
    }

    public Udzbenik delete(int id) {
        try {

            //TODO : strukturna ogranicenja
            return gr.delete(id, Udzbenik.class);

        } catch (Exception e) {
            throw e;
        }
    }

    public List<Udzbenik> getAll() {
        try {
            return gr.getAll(Udzbenik.class, Constants.UDZBENIK_FIND_ALL);
        } catch (Exception e) {
            throw e;
        }
    }

    public Udzbenik getById(int id) {
        try {
            return gr.getSingleByParamFromNamedQuery(id, Udzbenik.class, Constants.UDZBENIK_FIND_BY_ID, Constants.UDZBENIK_ID);
        } catch (Exception e) {
            throw e;
        }
    }
}
