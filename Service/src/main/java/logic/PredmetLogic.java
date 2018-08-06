/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.Nastavnik;
import domain.NastavnikNaPredmetu;
import domain.Predmet;
import domain.PredmetNaStudijskomProgramu;
import domain.StudijskiProgram;
import domain.Udzbenik;
import domain.UdzbenikNaPredmetu;
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
public class PredmetLogic extends AbstractLogic {

    private final GenericRepository<Predmet> gr;
    private final GenericRepository<UdzbenikNaPredmetu> grunp;
    private final GenericRepository<NastavnikNaPredmetu> grnnp;
    private final GenericRepository<Udzbenik> gru;
    private final GenericRepository<Nastavnik> grn;
    private final GenericRepository<PredmetNaStudijskomProgramu> grpnsp;
    private final GenericRepository<StudijskiProgram> grsp;

    private Set<ConstraintViolation<Predmet>> violations;

    public PredmetLogic() {

        gr = new GenericRepository<>();
        grunp = new GenericRepository<>();
        gru = new GenericRepository<>();
        grn = new GenericRepository<>();
        grnnp = new GenericRepository<>();
        grpnsp = new GenericRepository<>();
        grsp = new GenericRepository<>();
    }

    public Predmet create(Predmet predmet) {
        try {
            violations = validator.validate(predmet);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO : strukturna ogranicenja
            return gr.save(predmet);
        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception e) {
            throw e;
        }
    }

    public Predmet update(Predmet predmet) {
        try {
            violations = validator.validate(predmet);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO : strukturna ogranicenja
            return gr.update(predmet);
        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception e) {
            throw e;
        }
    }

    public Predmet delete(int id) throws Exception {
        try {

            //TODO : strukturna ogranicenja
            return gr.delete(id, Predmet.class);

        } catch (Exception e) {
            throw e;
        }
    }

    public List<PredmetDTO> getAll() throws Exception {
        try {
            List<Predmet> predmeti = gr.getAll(Predmet.class, Constants.PREMDET_FIND_ALL);
            List<PredmetDTO> predmetiDTO = new ArrayList<>();

            for (Predmet predmet : predmeti) {
                predmetiDTO.add(getById(predmet.getPredmetId()));
            }

            return predmetiDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    public PredmetDTO getById(int id) throws Exception {
        try {

            Predmet predmet = gr.getSingleByParamFromNamedQuery(id, Predmet.class, Constants.PREMDET_FIND_BY_ID, Constants.PREDMET_ID);

            List<UdzbenikNaPredmetu> udzbeniciNaPredmetu = grunp.getListByParamFromNamedQuery(id, UdzbenikNaPredmetu.class, Constants.UDZBENIK_NA_PREDMETU_FIND_ALL_BY_PREDMET_ID, Constants.PREDMET_ID);
            List<NastavnikNaPredmetu> nastavniciNaPredmetu = grnnp.getListByParamFromNamedQuery(id, NastavnikNaPredmetu.class, Constants.NASTAVNIK_NA_PREDMETU_FIND_ALL_BY_PREDMET_ID, Constants.PREDMET_ID);
            List<PredmetNaStudijskomProgramu> predmetiNaStudijskomProgramu = grpnsp.getListByParamFromNamedQuery(id, PredmetNaStudijskomProgramu.class, Constants.PREDMET_NA_STUDIJSKOM_PROGRAMU_FIND_BY_PREDMET_ID, Constants.PREDMET_ID);

            List<Udzbenik> udzbenici = new ArrayList<>();

            for (UdzbenikNaPredmetu udzbenikNaPredmetu : udzbeniciNaPredmetu) {
                udzbenici.add(gru.getSingleByParamFromNamedQuery(udzbenikNaPredmetu.getUdzbenikNaPredmetuPK().getUdzbenikId(), Udzbenik.class, Constants.UDZBENIK_FIND_BY_ID, Constants.UDZBENIK_ID));
            }

            List<Nastavnik> nastavnici = new ArrayList<>();

            if (nastavniciNaPredmetu != null) {
                for (NastavnikNaPredmetu nastavnikNaPredmetu : nastavniciNaPredmetu) {
                    nastavnici.add(grn.getSingleByParamFromNamedQuery(nastavnikNaPredmetu.getNastavnikNaPredmetuPK().getNastavnikId(), Nastavnik.class, Constants.NASTAVNIK_FIND_BY_ID, Constants.NASTAVNIK_ID));
                }
            }

            List<StudijskiProgram> stdProgrami = new ArrayList<>();

            if (predmetiNaStudijskomProgramu != null) {
                for (PredmetNaStudijskomProgramu predmetNaStudijskomProgramu : predmetiNaStudijskomProgramu) {
                    stdProgrami.add(grsp.getSingleByParamFromNamedQuery(predmetNaStudijskomProgramu.getPredmetNaStudijskomProgramuPK().getStudijskiprogramId(), StudijskiProgram.class, Constants.STUDIJSKI_PROGRAM_FIND_BY_ID, Constants.STUDIJSKI_PROGRAM_ID));
                }
            }

            return Mapper.toPredmetDTO(predmet, udzbenici, nastavnici, stdProgrami, null);
        } catch (Exception e) {
            throw e;
        }
    }

}
