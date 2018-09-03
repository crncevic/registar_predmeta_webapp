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
import java.util.List;
import javax.inject.Inject;
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
