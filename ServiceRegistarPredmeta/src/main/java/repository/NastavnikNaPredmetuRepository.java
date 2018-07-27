/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import constants.Constants;
import domain.NastavnikNaPredmetu;
import domain.NastavnikNaPredmetuPK;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author Petar
 */
public class NastavnikNaPredmetuRepository extends AbstractRepository {

    @Transactional
    public NastavnikNaPredmetu create(NastavnikNaPredmetu nastavnikNaPredmetu) throws Exception {
        try {
            em.persist(nastavnikNaPredmetu);
            return nastavnikNaPredmetu;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Transactional
    public NastavnikNaPredmetu delete(NastavnikNaPredmetuPK nastavnikNaPredmetuPK) throws Exception {
        try {
            Query query
                    = em.createQuery("SELECT nnp  "
                            + "FROM NastavnikNaPredmetu nnp "
                            + "WHERE nnp.nastavnikNaPredmetuPK.nastavnikId = ?1"
                            + "AND nnp.nastavnikNaPredmetuPK.predmetId = ?2"
                            + "AND nnp.nastavnikNaPredmetuPK.tipNastaveId = ?3");
            query.setParameter(1, nastavnikNaPredmetuPK.getNastavnikId());
            query.setParameter(2, nastavnikNaPredmetuPK.getPredmetId());
            query.setParameter(3, nastavnikNaPredmetuPK.getTipNastaveId());

            NastavnikNaPredmetu nastavnikNaPredmetuFromDb = (NastavnikNaPredmetu) query.getSingleResult();

            if (nastavnikNaPredmetuFromDb == null) {
                throw new Exception("Nastavnik sa id:" + nastavnikNaPredmetuPK.getNastavnikId() + ","
                        + " ne postoji na predmetu sa id: " + nastavnikNaPredmetuPK.getPredmetId() + ","
                        + " sa tipom nastave id: " + nastavnikNaPredmetuPK.getTipNastaveId());
            }

            em.remove(nastavnikNaPredmetuFromDb);

            return nastavnikNaPredmetuFromDb;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<NastavnikNaPredmetu> getAllByNastavnikId(int nastavnikId) {
        try {
            TypedQuery<NastavnikNaPredmetu> query
                    = em.createNamedQuery(Constants.NASTAVNIK_NA_PREDMETU_FIND_ALL_BY_NASTAVNIK_ID, NastavnikNaPredmetu.class);
            return query.setParameter("nastavnikId", nastavnikId).getResultList();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<NastavnikNaPredmetu> getAllByPredmetId(int predmetId) {
        try {
            TypedQuery<NastavnikNaPredmetu> query
                    = em.createNamedQuery(Constants.NASTAVNIK_NA_PREDMETU_FIND_ALL_BY_PREDMET_ID, NastavnikNaPredmetu.class);
            return query.setParameter("predmetId", predmetId).getResultList();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<NastavnikNaPredmetu> getAllByTipNastaveId(int tipNastaveId) {
        try {
            TypedQuery<NastavnikNaPredmetu> query
                    = em.createNamedQuery(Constants.NASTAVNIK_NA_PREDMETU_FIND_ALL_BY_TIP_NASTAVE_ID, NastavnikNaPredmetu.class);
            return query.setParameter("tipNastaveId", tipNastaveId).getResultList();
        } catch (Exception e) {
            throw e;
        }
    }
}
