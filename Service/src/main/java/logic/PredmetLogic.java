/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.Nastavnik;
import domain.NastavnikNaPredmetu;
import domain.NastavnikNaPredmetuPK;
import domain.Predmet;
import domain.PredmetNaStudijskomProgramu;
import domain.StudijskiProgram;
import domain.TematskaCelina;
import domain.Udzbenik;
import domain.UdzbenikNaPredmetu;
import domain.UdzbenikNaPredmetuPK;
import dto.PredmetDTO;
import dto.UdzbenikDTO;
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
public class PredmetLogic extends AbstractLogicClass {

    private final GenericRepository<Predmet> grp;
    private final GenericRepository<UdzbenikNaPredmetu> grunp;
    private final GenericRepository<NastavnikNaPredmetu> grnnp;
    private final GenericRepository<Udzbenik> gru;
    private final GenericRepository<Nastavnik> grn;
    private final GenericRepository<PredmetNaStudijskomProgramu> grpnsp;
    private final GenericRepository<StudijskiProgram> grsp;
    private final GenericRepository<TematskaCelina> grtc;

    private Set<ConstraintViolation<Predmet>> violations;

    public PredmetLogic() {

        grp = new GenericRepository<>();
        grunp = new GenericRepository<>();
        gru = new GenericRepository<>();
        grn = new GenericRepository<>();
        grnnp = new GenericRepository<>();
        grpnsp = new GenericRepository<>();
        grsp = new GenericRepository<>();
        grtc = new GenericRepository<>();
    }

    public Predmet create(Predmet predmet) throws Exception {
        try {
            //provera vrednosnih ogranicenja
            violations = validator.validate(predmet);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO : strukturna ogranicenja
            Predmet sameNamePredmet = grp.getSingleByParamFromNamedQuery(predmet.getNaziv(), Predmet.class, Constants.PREDMET_FIND_BY_NAZIV, Constants.PREDMET_NAZIV);

            if (sameNamePredmet != null) {
                throw new ConstraintViolationException("Predmet sa nazivom : " + predmet.getNaziv() + " vec postoji u bazi!", null);
            }

            try {
                et.begin();
                Predmet predmetForCreating = new Predmet();
                predmetForCreating.setNaziv(predmet.getNaziv());

                Predmet createdPredmet = grp.save(predmetForCreating);
                predmet.setPredmetId(createdPredmet.getPredmetId());

                if (predmet.getUdzbenikList() != null) {
                    for (UdzbenikNaPredmetu udzbenikNaPredmetu : predmet.getUdzbenikList()) {
                        udzbenikNaPredmetu.getUdzbenikNaPredmetuPK().setPredmetId(predmet.getPredmetId());
                        grunp.save(udzbenikNaPredmetu);
                    }
                }

                if (predmet.getNastavnikNaPredmetuList() != null) {
                    for (NastavnikNaPredmetu nastavnikNaPredmetu : predmet.getNastavnikNaPredmetuList()) {
                        nastavnikNaPredmetu.getNastavnikNaPredmetuPK().setPredmetId(predmet.getPredmetId());
                        grnnp.save(nastavnikNaPredmetu);
                    }
                }

                if (predmet.getTematskaCelinaList() != null) {
                    for (TematskaCelina tematskaCelina : predmet.getTematskaCelinaList()) {
                        tematskaCelina.setPredmet(predmet);
                        grtc.save(tematskaCelina);
                    }
                }

                Predmet resultPredmet = grp.update(predmet);
                et.commit();
                return resultPredmet;
            } catch (Exception ex) {
                et.rollback();
                throw ex;
            }

        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception e) {
            throw e;
        }
    }

    public Predmet update(Predmet predmet) throws Exception {
        try {
            violations = validator.validate(predmet);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO : strukturna ogranicenja
            try {
                et.begin();
                Predmet updatedPredmet = grp.update(predmet);
                et.commit();
                return updatedPredmet;
            } catch (Exception ex) {
                et.rollback();
                throw ex;
            }

        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception e) {
            throw e;
        }
    }

    public Predmet delete(int id) throws Exception {
        try {
            Predmet deletedPredmet = getById(id);
            //TODO : strukturna ogranicenja
            try {
                et.begin();
                grp.delete(id, Predmet.class);
                et.commit();
                return deletedPredmet;
            } catch (Exception ex) {
                et.rollback();
                throw ex;
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public List<Predmet> getAll() throws Exception {
        try {
            return grp.getAll(Predmet.class, Constants.PREDMET_FIND_ALL);

        } catch (Exception e) {
            throw e;
        }
    }

    public Predmet getById(int id) throws Exception {
        try {

            Predmet predmet = grp.getSingleByParamFromNamedQuery(id, Predmet.class, Constants.PREDMET_FIND_BY_ID, Constants.PREDMET_ID);

            predmet.setUdzbenikList(grunp.getListByParamFromNamedQuery(id, UdzbenikNaPredmetu.class, Constants.UDZBENIK_NA_PREDMETU_FIND_ALL_BY_PREDMET_ID, Constants.PREDMET_ID));
            predmet.setNastavnikNaPredmetuList(grnnp.getListByParamFromNamedQuery(id, NastavnikNaPredmetu.class, Constants.NASTAVNIK_NA_PREDMETU_FIND_ALL_BY_PREDMET_ID, Constants.PREDMET_ID));
            predmet.setPredmetNaStudijskomProgramuList(grpnsp.getListByParamFromNamedQuery(id, PredmetNaStudijskomProgramu.class, Constants.PREDMET_NA_STUDIJSKOM_PROGRAMU_FIND_BY_PREDMET_ID, Constants.PREDMET_ID));
            predmet.setTematskaCelinaList(grtc.getListByParamFromNamedQuery(id, TematskaCelina.class, Constants.TEMATSKA_CELINA_FIND_BY_PREDMET_ID, Constants.PREDMET_ID));

            return predmet;
        } catch (Exception e) {
            throw e;
        }
    }

}
