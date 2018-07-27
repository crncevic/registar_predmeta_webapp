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
        em.persist(korisnik);
        return korisnik;
    }
    
    @Transactional
    public Korisnik update(Korisnik korisnik) {
        em.merge(korisnik);
        return korisnik;
    }

    @Transactional
    public Korisnik delete(int id) throws Exception {

        Korisnik korisnikFromDb = em.getReference(Korisnik.class, id);
        if (korisnikFromDb == null) {
            throw new Exception("Korisnik sa id:" + id + " ne postoji");
        }
        em.remove(korisnikFromDb);
        
        return korisnikFromDb;
    }
    
    public List<Korisnik> getAll(){
        TypedQuery<Korisnik> query = em.createNamedQuery(Constants.KORISNIK_FIND_ALL,Korisnik.class);
        return query.getResultList();
    }
    
    public Korisnik getById(int id){
        TypedQuery<Korisnik> query = em.createNamedQuery(Constants.KORISNIK_FIND_BY_ID,Korisnik.class);
        return query.setParameter("korisnikId", id).getSingleResult();
    }
    
    public Korisnik getByUsername(String username){
        TypedQuery<Korisnik> query = em.createNamedQuery(Constants.KORISNIK_FIND_BY_USERNAME,Korisnik.class);
        return query.setParameter("username", username).getSingleResult();
    }

}
