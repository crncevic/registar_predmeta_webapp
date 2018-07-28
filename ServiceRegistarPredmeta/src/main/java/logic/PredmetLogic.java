/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.Predmet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import repository.PredmetRepository;

/**
 *
 * @author Petar
 */
public class PredmetLogic extends AbstractLogic {

    private final PredmetRepository pr;
    private Set<ConstraintViolation<Predmet>> violations;

    public PredmetLogic() {
        super();
        pr = new PredmetRepository();
    }

    public Predmet create(Predmet predmet) {
        try {
            violations = validator.validate(predmet);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO : strukturna ogranicenja
            return pr.create(predmet);
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
            return pr.update(predmet);
        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception e) {
            throw e;
        }
    }

    public Predmet delete(int id) throws Exception {
        try {

            //TODO : strukturna ogranicenja
            return pr.delete(id);

        } catch (Exception e) {
            throw e;
        }
    }

    public List<Predmet> getAll() {
        try {
            return pr.getAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public Predmet getById(int id) {
        try {
            return pr.getById(id);
        } catch (Exception e) {
            throw e;
        }
    }

}
