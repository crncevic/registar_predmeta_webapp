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
public class KorisnikLogic extends AbstractLogic {

    private GenericRepository<Korisnik> gr;
    private Set<ConstraintViolation<Korisnik>> violations;

    public KorisnikLogic() {

        gr = new GenericRepository<>();
    }

    public KorisnikDTO create(Korisnik korisnik) throws Exception {
        try {
            violations = validator.validate(korisnik);
            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO: strukturna ogranicenja
            Korisnik createdKorisnik = gr.save(korisnik);
            return Mapper.toKorisnikDTO(createdKorisnik);

        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception ex) {
            throw new Exception("Dogodila se greska prilikom kreiranja korisnika {" + ex.getMessage() + "}");
        }
    }

    public KorisnikDTO update(Korisnik korisnik) throws Exception {
        try {
            violations = validator.validate(korisnik);
            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO strukturna ogranicenja
            Korisnik updatedKorisnik = gr.update(korisnik);
            return Mapper.toKorisnikDTO(updatedKorisnik);

        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception ex) {
            throw new Exception("Dogodila se greska prilikom azuriranja korisnika {" + ex.getMessage() + "}");
        }
    }

    public KorisnikDTO delete(int id) throws Exception {
        try {

            //TODO strukturna ogranicenja
            Korisnik deletedKorisnik = gr.delete(id, Korisnik.class);
            return Mapper.toKorisnikDTO(deletedKorisnik);

        } catch (Exception ex) {
            throw new Exception("Dogodila se greska prilikom brisanja sa id: " + id + " korisnika {" + ex.getMessage() + "}");
        }
    }

    public List<KorisnikDTO> getAll() throws Exception {
        try {
            List<Korisnik> korisnici = gr.getAll(Korisnik.class, Constants.KORISNIK_FIND_ALL);
            List<KorisnikDTO> korisniciDTO = new ArrayList<>();

            for (Korisnik korisnik : korisnici) {
                korisniciDTO.add(Mapper.toKorisnikDTO(korisnik));
            }

            return korisniciDTO;
        } catch (Exception ex) {
            throw new Exception("Dogodila se greska prilikom ucitavanja svih korisnika {" + ex.getMessage() + "}");
        }
    }

    public KorisnikDTO getById(int id) throws Exception {
        try {
            Korisnik korisnikFromDb = gr.getSingleByParamFromNamedQuery(id, Korisnik.class, Constants.KORISNIK_FIND_BY_ID, Constants.KORISNIK_ID);
            return Mapper.toKorisnikDTO(korisnikFromDb);
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom ucitavanja korisnika sa id: " + id + "{" + e.getMessage() + "}");
        }
    }

    public KorisnikDTO getByUsername(String username) throws Exception {
        try {
            Korisnik korisnikFromDb = gr.getSingleByParamFromNamedQuery(username, Korisnik.class, Constants.KORISNIK_FIND_ALL, Constants.KORISNIK_USERNAME);
            return Mapper.toKorisnikDTO(korisnikFromDb);
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom ucitavanja korisnika sa username: " + username + "{" + e.getMessage() + "}");

        }
    }

}
