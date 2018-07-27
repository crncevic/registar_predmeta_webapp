/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.Korisnik;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import repository.KorisnikRepository;

/**
 *
 * @author Petar
 */
public class KorisnikLogic extends AbstractLogic {

    private KorisnikRepository kr;
    private Set<ConstraintViolation<Korisnik>> violations;

    public KorisnikLogic() {
        super();
        kr = new KorisnikRepository();
    }

    public Korisnik create(Korisnik korisnik) {
        try {
            violations = validator.validate(korisnik);
            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            return kr.create(korisnik);

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

            return kr.update(korisnik);

        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Korisnik delete(int id) throws Exception {
        try {
            return kr.delete(id);

        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<Korisnik> getAll() {
        try {
            return kr.getAll();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Korisnik getById(int id) {
        try {
            return kr.getById(id);
        } catch (Exception e) {
            throw e;
        }
    }

    public Korisnik getByUsername(String username) {
        try {
            return kr.getByUsername(username);
        } catch (Exception e) {
            throw e;
        }
    }

}
