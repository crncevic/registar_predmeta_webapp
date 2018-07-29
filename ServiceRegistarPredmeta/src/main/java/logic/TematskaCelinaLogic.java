/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.TematskaCelina;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import repository.TematskaCelinaRepository;

/**
 *
 * @author Petar
 */
public class TematskaCelinaLogic extends AbstractLogic {

    private TematskaCelinaRepository tcr;
    private Set<ConstraintViolation<TematskaCelina>> violations;

    public TematskaCelinaLogic() {
        super();
        tcr = new TematskaCelinaRepository();
    }

    public TematskaCelina create(TematskaCelina tematskaCelina) {
        try {
            violations = validator.validate(tematskaCelina);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO : strukturna ogranicenja
            return tcr.create(tematskaCelina);

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
            return tcr.update(tematskaCelina);

        } catch (Exception e) {
            throw e;
        }
    }

    public TematskaCelina delete(int id) {
        try {

            //TODO : strukturna ogranicenja
            return tcr.delete(id);

        } catch (Exception e) {
            throw e;
        }
    }

    public List<TematskaCelina> getAll() {
        try {
            return tcr.getAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public TematskaCelina getById(int id) {
        try {
            return tcr.getById(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
