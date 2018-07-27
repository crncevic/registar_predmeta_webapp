/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import constants.Constants;
import domain.Korisnik;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author Petar
 */
public class KorisnikRepository extends AbstractRepository {

    public KorisnikRepository() {
        super();
    }

    @Transactional
    public Korisnik create(Korisnik korisnik) {
        try {
            em.persist(korisnik);
            return korisnik;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Transactional
    public Korisnik update(Korisnik korisnik) {
        try {
            em.merge(korisnik);
            return korisnik;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Transactional
    public Korisnik delete(int id) throws Exception {
        try {
            Korisnik korisnikFromDb = em.getReference(Korisnik.class, id);
            if (korisnikFromDb == null) {
                throw new Exception("Korisnik sa id:" + id + " ne postoji");
            }
            em.remove(korisnikFromDb);

            return korisnikFromDb;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<Korisnik> getAll() {
        try {
            TypedQuery<Korisnik> query = em.createNamedQuery(Constants.KORISNIK_FIND_ALL, Korisnik.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Korisnik getById(int id) {
        try {
            TypedQuery<Korisnik> query = em.createNamedQuery(Constants.KORISNIK_FIND_BY_ID, Korisnik.class);
            return query.setParameter("korisnikId", id).getSingleResult();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Korisnik getByUsername(String username) {
        try {
            TypedQuery<Korisnik> query = em.createNamedQuery(Constants.KORISNIK_FIND_BY_USERNAME, Korisnik.class);
            return query.setParameter("username", username).getSingleResult();
        } catch (Exception ex) {
            throw ex;
        }
    }

}
