/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.Korisnik;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public class KorisnikLogic extends AbstractLogic {

    private GenericRepository<Korisnik> gr;
    private Set<ConstraintViolation<Korisnik>> violations;

    public KorisnikLogic() {
        super();
        gr = new GenericRepository<>();
    }

    public Korisnik create(Korisnik korisnik) {
        try {
            violations = validator.validate(korisnik);
            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO: strukturna ogranicenja
            return gr.save(korisnik);

        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Korisnik update(Korisnik korisnik) {
        try {
            violations = validator.validate(korisnik);
            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO strukturna ogranicenja
            return gr.update(korisnik);

        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Korisnik delete(int id) throws Exception {
        try {

            //TODO strukturna ogranicenja
            return gr.delete(id, Korisnik.class);

        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<Korisnik> getAll() {
        try {
            return gr.getAll(Korisnik.class, Constants.KORISNIK_FIND_ALL);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Korisnik getById(int id) {
        try {
            return gr.getSingleByParamFromNamedQuery(id, Korisnik.class, Constants.KORISNIK_FIND_BY_ID, Constants.KORISNIK_ID);
        } catch (Exception e) {
            throw e;
        }
    }

    public Korisnik getByUsername(String username) {
        try {
            return gr.getSingleByParamFromNamedQuery(username,Korisnik.class,Constants.KORISNIK_FIND_ALL,Constants.KORISNIK_USERNAME);
        } catch (Exception e) {
            throw e;
        }
    }

}
