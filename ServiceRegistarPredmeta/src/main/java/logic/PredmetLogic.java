/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.Predmet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public class PredmetLogic extends AbstractLogic {

    private final GenericRepository<Predmet> gr;
    private Set<ConstraintViolation<Predmet>> violations;

    public PredmetLogic() {
        super();
        gr = new GenericRepository<>();
    }

    public Predmet create(Predmet predmet) {
        try {
            violations = validator.validate(predmet);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO : strukturna ogranicenja
            return gr.save(predmet);
        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception e) {
            throw e;
        }
    }

    public Predmet update(Predmet predmet) {
        try {
            violations = validator.validate(predmet);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO : strukturna ogranicenja
            return gr.update(predmet);
        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception e) {
            throw e;
        }
    }

    public Predmet delete(int id) throws Exception {
        try {

            //TODO : strukturna ogranicenja
            return gr.delete(id, Predmet.class);

        } catch (Exception e) {
            throw e;
        }
    }

    public List<Predmet> getAll() {
        try {
            return gr.getAll(Predmet.class,Constants.PREMDET_FIND_ALL);
        } catch (Exception e) {
            throw e;
        }
    }

    public Predmet getById(int id) {
        try {
            return gr.getSingleByParamFromNamedQuery(id, Predmet.class, Constants.PREMDET_FIND_BY_ID , Constants.PREDMET_ID);
        } catch (Exception e) {
            throw e;
        }
    }

}
