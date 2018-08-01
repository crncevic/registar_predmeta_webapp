/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.NastavnikNaPredmetu;
import domain.NastavnikNaPredmetuPK;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public class NastavnikNaPredmetuLogic extends AbstractLogic {

    private GenericRepository<NastavnikNaPredmetu> gr;
    private Set<ConstraintViolation<NastavnikNaPredmetu>> violations;

    public NastavnikNaPredmetuLogic() {
        
        gr = new GenericRepository<>();
    }

    public NastavnikNaPredmetu create(NastavnikNaPredmetu nastavnikNaPredmetu) throws Exception {
        try {
            violations = validator.validate(nastavnikNaPredmetu);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO strukturna ogranicenja
            return gr.save(nastavnikNaPredmetu);
        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception e) {
            throw e;
        }
    }

    public NastavnikNaPredmetu delete(NastavnikNaPredmetuPK nastavnikNaPredmetuPK) throws Exception {
        try {

            //TODO strukturna ogranicenja
            return gr.delete(nastavnikNaPredmetuPK, NastavnikNaPredmetu.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<NastavnikNaPredmetu> getAllByNastavnikId(int nastavnikId) {
        try {
            return gr.getListByParamFromNamedQuery(String.valueOf(nastavnikId), NastavnikNaPredmetu.class, Constants.NASTAVNIK_NA_PREDMETU_FIND_ALL_BY_NASTAVNIK_ID, Constants.NASTAVNIK_ID);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<NastavnikNaPredmetu> getAllByPredmetId(int predmetId) {
        try {
            return gr.getListByParamFromNamedQuery(predmetId, NastavnikNaPredmetu.class, Constants.NASTAVNIK_NA_PREDMETU_FIND_ALL_BY_PREDMET_ID, Constants.PREDMET_ID);

        } catch (Exception e) {
            throw e;
        }
    }

    public List<NastavnikNaPredmetu> getAllByTipNastaveId(int tipNastaveId) {
        try {
            return gr.getListByParamFromNamedQuery(tipNastaveId, NastavnikNaPredmetu.class, Constants.NASTAVNIK_NA_PREDMETU_FIND_ALL_BY_TIP_NASTAVE_ID, Constants.TIP_NASTAVE_ID);
        } catch (Exception e) {
            throw e;
        }
    }

}
