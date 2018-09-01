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
    

    public List<PredmetNaStudijskomProgramu> getByStdProgramId(int stdProgramId) {
        try {
            List<PredmetNaStudijskomProgramu> list = grpnsp.getListByParamFromNamedQuery(stdProgramId, PredmetNaStudijskomProgramu.class, Constants.PREDMET_NA_STUDIJSKOM_PROGRAMU_FIND_BY_STD_PROGRAM_ID, Constants.STUDIJSKI_PROGRAM_ID);

            for (PredmetNaStudijskomProgramu pnsp : list) {
                pnsp.setPredmet(
                        grp.getSingleByParamFromNamedQuery(pnsp.getPredmetNaStudijskomProgramuPK().getPredmetId(), Predmet.class, Constants.PREDMET_FIND_BY_ID, Constants.PREDMET_ID));
            }
            return list;
        } catch (Exception e) {
            throw e;
        }
    }
}
