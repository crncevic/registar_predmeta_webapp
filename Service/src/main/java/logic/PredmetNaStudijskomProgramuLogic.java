/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.Predmet;
import domain.PredmetNaStudijskomProgramu;
import domain.Status;
import domain.StudijskiProgram;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public class PredmetNaStudijskomProgramuLogic extends AbstractLogicClass {

    @Inject
    private GenericRepository<PredmetNaStudijskomProgramu> grpnsp;
    @Inject
    private GenericRepository<Predmet> grp;
    @Inject
    private GenericRepository<StudijskiProgram> grsp;
    @Inject
    private GenericRepository<Status> grs;
    private Set<ConstraintViolation<PredmetNaStudijskomProgramu>> violations;

    public PredmetNaStudijskomProgramu create(PredmetNaStudijskomProgramu pnsp) {
        try {
            et.begin();
            violations = validator.validate(pnsp);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            Predmet predmet = grp.getSingleByParamsFromNamedQuery(new Object[]{pnsp.getPredmetNaStudijskomProgramuPK().getPredmetId()},
                    Constants.PREDMET_FIND_BY_ID, new String[]{Constants.PREDMET_ID});

            if (predmet == null) {
                throw new ConstraintViolationException("Predmet ne postoji u bazi", null);
            }

            StudijskiProgram studijskiProgram = grsp.getSingleByParamsFromNamedQuery(
                    new Object[]{pnsp.getPredmetNaStudijskomProgramuPK().getStudijskiprogramId()},
                    Constants.STUDIJSKI_PROGRAM_FIND_BY_ID,
                    new String[]{Constants.STUDIJSKI_PROGRAM_ID});

            if (studijskiProgram == null) {
                throw new ConstraintViolationException("Studijski program ne postoji u bazi", null);
            }

            if (pnsp.getEspb() < 0 || pnsp.getSemestar() < 0) {
                throw new ConstraintViolationException("Semestar ili espb su manji od 0", null);
            }

            grpnsp.save(pnsp);
            et.commit();
            return getById(pnsp.getPredmetNaStudijskomProgramuPK().getStudijskiprogramId(), pnsp.getPredmetNaStudijskomProgramuPK().getPredmetId());

        } catch (ConstraintViolationException cve) {
            et.rollback();
            throw cve;
        } catch (Exception e) {
            et.rollback();
            throw e;
        }
    }

    public PredmetNaStudijskomProgramu update(PredmetNaStudijskomProgramu pnsp) {
        try {
            et.begin();
            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            if (pnsp.getEspb() < 0 || pnsp.getSemestar() < 0) {
                throw new ConstraintViolationException("Semestar ili espb su manji od 0", null);
            }

            PredmetNaStudijskomProgramu savedPnsp = grpnsp.update(pnsp);
            et.commit();
            return savedPnsp;

        } catch (ConstraintViolationException cve) {
            et.rollback();
            throw cve;
        } catch (Exception e) {
            et.rollback();
            throw e;
        }
    }

    public PredmetNaStudijskomProgramu delete(int stdProgramId, int predmetId) throws Exception {
        try {
            et.begin();
            Predmet predmet = grp.getSingleByParamsFromNamedQuery(new Object[]{predmetId},
                    Constants.PREDMET_FIND_BY_ID, new String[]{Constants.PREDMET_ID});

            if (predmet == null) {
                throw new ConstraintViolationException("Predmet ne postoji u bazi", null);
            }

            StudijskiProgram studijskiProgram = grsp.getSingleByParamsFromNamedQuery(
                    new Object[]{stdProgramId},
                    Constants.STUDIJSKI_PROGRAM_FIND_BY_ID,
                    new String[]{Constants.STUDIJSKI_PROGRAM_ID});

            if (studijskiProgram == null) {
                throw new ConstraintViolationException("Studijski program ne postoji u bazi", null);
            }

            PredmetNaStudijskomProgramu deletedPnsp = getById(stdProgramId, predmetId);

            int result = grpnsp.delete_CompositeKey(Constants.PREDMET_NA_STUDIJSKOM_PROGRAMU_DELETE,
                    new String[]{Constants.PREDMET_ID, Constants.STUDIJSKI_PROGRAM_ID},
                    new int[]{predmetId, stdProgramId});

            if (result == 1) {
                et.commit();
                return deletedPnsp;
            } else {
                throw new Exception("DB did not delete entry");
            }
        } catch (ConstraintViolationException cve) {
            et.rollback();
            throw cve;
        } catch (Exception e) {
            et.rollback();
            throw e;
        }
    }

    public PredmetNaStudijskomProgramu getById(int stdProgramId, int predmetId) {
        try {
            PredmetNaStudijskomProgramu pnsp = grpnsp.getSingleByParamsFromNamedQuery(
                    new Object[]{stdProgramId, predmetId}, Constants.PREDMET_NA_STUDIJSKOM_PROGRAMU_FIND_BY_COMPOSITE_KEY,
                    new String[]{Constants.STUDIJSKI_PROGRAM_ID, Constants.PREDMET_ID});
            pnsp.setPredmet(grp.getSingleByParamsFromNamedQuery(new Object[]{pnsp.getPredmetNaStudijskomProgramuPK().getPredmetId()},
                    Constants.PREDMET_FIND_BY_ID, new String[]{Constants.PREDMET_ID}));
            return pnsp;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<PredmetNaStudijskomProgramu> getByStdProgramId(int stdProgramId) {
        try {
            List<PredmetNaStudijskomProgramu> list = grpnsp.getListByParamsFromNamedQuery(
                    new Object[]{stdProgramId},
                    Constants.PREDMET_NA_STUDIJSKOM_PROGRAMU_FIND_BY_STD_PROGRAM_ID,
                    new String[]{Constants.STUDIJSKI_PROGRAM_ID});
            if (list != null && !list.isEmpty()) {
                list.get(list.size() - 1).
                        setStatus(grs.getSingleByParamsFromNamedQuery(
                                new Object[]{list.get(list.size() - 1).getStatus().getStatusId()},
                                Constants.STATUS_FIND_BY_ID,
                                new String[]{Constants.STATUS_ID}));
            }

            for (PredmetNaStudijskomProgramu pnsp : list) {
                pnsp.setPredmet(
                        grp.getSingleByParamsFromNamedQuery(new Object[]{pnsp.getPredmetNaStudijskomProgramuPK().getPredmetId()},
                        Constants.PREDMET_FIND_BY_ID, new String[]{Constants.PREDMET_ID}));
            }
            return list;
        } catch (Exception e) {
            throw e;
        }
    }
}
