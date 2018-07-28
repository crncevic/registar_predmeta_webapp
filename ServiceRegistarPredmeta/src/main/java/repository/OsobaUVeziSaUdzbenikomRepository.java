/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import constants.Constants;
import domain.OsobaUVeziSaUdzbenikom;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author Petar
 */
public class OsobaUVeziSaUdzbenikomRepository extends AbstractRepository {

    @Transactional
    public OsobaUVeziSaUdzbenikom create(OsobaUVeziSaUdzbenikom ouvsu) {
        try {
            em.persist(ouvsu);
            return ouvsu;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Transactional
    public OsobaUVeziSaUdzbenikom update(OsobaUVeziSaUdzbenikom ouvsu) {
        try {
            em.merge(ouvsu);
            return ouvsu;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public OsobaUVeziSaUdzbenikom delete(int id) throws Exception {
        try {
            OsobaUVeziSaUdzbenikom ouvsuFromDb = em.getReference(OsobaUVeziSaUdzbenikom.class, id);

            if (ouvsuFromDb == null) {
                throw new Exception("Osoba u vezi sa udzbenikom sa id:" + id + " ne postoji");
            }

            em.remove(ouvsuFromDb);

            return ouvsuFromDb;
        } catch (Exception e) {
            throw e;
        }
    }

    public OsobaUVeziSaUdzbenikom getById(int id) {
        try {
            TypedQuery<OsobaUVeziSaUdzbenikom> query
                    = em.createNamedQuery(Constants.OSOBA_U_VEZI_SA_UDZBENIKOM_FIND_BY_OSOBA_ID, OsobaUVeziSaUdzbenikom.class);
            return query.setParameter("osobaId", id).getSingleResult();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<OsobaUVeziSaUdzbenikom> getAll() {
        try {
            TypedQuery<OsobaUVeziSaUdzbenikom> query
                    = em.createNamedQuery(Constants.OSOBA_U_VEZI_SA_UDZBENIKOM_FIND_ALL, OsobaUVeziSaUdzbenikom.class);
            return query.getResultList();
        } catch (Exception e) {
            throw e;
        }
    }

}
