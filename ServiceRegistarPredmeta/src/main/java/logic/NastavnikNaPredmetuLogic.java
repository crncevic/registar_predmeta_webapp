/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.NastavnikNaPredmetu;
import domain.NastavnikNaPredmetuPK;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import repository.NastavnikNaPredmetuRepository;

/**
 *
 * @author Petar
 */
public class NastavnikNaPredmetuLogic extends AbstractLogic {

    private NastavnikNaPredmetuRepository nnpr;
    private Set<ConstraintViolation<NastavnikNaPredmetu>> violations;

    public NastavnikNaPredmetuLogic() {
        super();
        nnpr = new NastavnikNaPredmetuRepository();
    }

    public NastavnikNaPredmetu create(NastavnikNaPredmetu nastavnikNaPredmetu) throws Exception {
        try {
            violations = validator.validate(nastavnikNaPredmetu);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO strukturna ogranicenja
            
            return nnpr.create(nastavnikNaPredmetu);
        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception e) {
            throw e;
        }
    }

    public NastavnikNaPredmetu delete(NastavnikNaPredmetuPK nastavnikNaPredmetuPK) throws Exception {
        try {
            
            //TODO strukturna ogranicenja
            
            return nnpr.delete(nastavnikNaPredmetuPK);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<NastavnikNaPredmetu> getAllByNastavnikId(int nastavnikId) {
        try {
            return nnpr.getAllByNastavnikId(nastavnikId);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<NastavnikNaPredmetu> getAllByPredmetId(int predmetId) {
        try {
            return nnpr.getAllByPredmetId(predmetId);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<NastavnikNaPredmetu> getAllByTipNastaveId(int tipNastaveId) {
        try {
            return nnpr.getAllByTipNastaveId(tipNastaveId);
        } catch (Exception e) {
            throw e;
        }
    }

}
