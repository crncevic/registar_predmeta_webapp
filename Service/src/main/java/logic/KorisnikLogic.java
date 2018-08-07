/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.Korisnik;
import dto.KorisnikDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import mapper.Mapper;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public class KorisnikLogic extends AbstractLogicClass {

    private GenericRepository<Korisnik> gr;
    private Set<ConstraintViolation<Korisnik>> violations;

    public KorisnikLogic() {

        gr = new GenericRepository<>();
    }

    public Korisnik create(Korisnik korisnik) throws Exception {
        try {
            violations = validator.validate(korisnik);
            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO: strukturna ogranicenja
            try {
                et.begin();
                Korisnik createdKorisnik = gr.save(korisnik);
                et.commit();
                return createdKorisnik;
            } catch (Exception ex) {
                et.rollback();
                throw ex;
            }

        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception ex) {
            throw new Exception("Dogodila se greska prilikom kreiranja korisnika {" + ex.getMessage() + "}");
        }
    }

    public Korisnik update(Korisnik korisnik) throws Exception {
        try {
            violations = validator.validate(korisnik);
            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO strukturna ogranicenja
            try {
                et.begin();
                Korisnik updatedKorisnik = gr.update(korisnik);
                et.commit();
                return updatedKorisnik;
            } catch (Exception ex) {
                et.rollback();
                throw ex;
            }

        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception ex) {
            throw new Exception("Dogodila se greska prilikom azuriranja korisnika {" + ex.getMessage() + "}");
        }
    }

    public Korisnik delete(int id) throws Exception {
        try {

            //TODO strukturna ogranicenja
            try {
                et.begin();
                Korisnik deletedKorisnik = gr.delete(id, Korisnik.class);
                et.commit();
                return deletedKorisnik;

            } catch (Exception ex) {
                et.rollback();
                throw ex;
            }

        } catch (Exception ex) {
            throw new Exception("Dogodila se greska prilikom brisanja sa id: " + id + " korisnika {" + ex.getMessage() + "}");
        }
    }

    public List<Korisnik> getAll() throws Exception {
        try {
            return gr.getAll(Korisnik.class, Constants.KORISNIK_FIND_ALL);

        } catch (Exception ex) {
            throw new Exception("Dogodila se greska prilikom ucitavanja svih korisnika {" + ex.getMessage() + "}");
        }
    }

    public Korisnik getById(int id) throws Exception {
        try {
            return gr.getSingleByParamFromNamedQuery(id, Korisnik.class, Constants.KORISNIK_FIND_BY_ID, Constants.KORISNIK_ID);

        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom ucitavanja korisnika sa id: " + id + "{" + e.getMessage() + "}");
        }
    }

    public Korisnik getByUsername(String username) throws Exception {
        try {
            return gr.getSingleByParamFromNamedQuery(username, Korisnik.class, Constants.KORISNIK_FIND_ALL, Constants.KORISNIK_USERNAME);
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom ucitavanja korisnika sa username: " + username + "{" + e.getMessage() + "}");
        }
    }

}
