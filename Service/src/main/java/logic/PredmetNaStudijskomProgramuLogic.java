/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
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
    private GenericRepository<Status> grs;

    public List<PredmetNaStudijskomProgramu> getByStdProgramId(int stdProgramId) {
        try {
            return grpnsp.getListByParamFromNamedQuery(stdProgramId, PredmetNaStudijskomProgramu.class, Constants.PREDMET_NA_STUDIJSKOM_PROGRAMU_FIND_BY_STD_PROGRAM_ID, Constants.STUDIJSKI_PROGRAM_ID);

        } catch (Exception e) {
            throw e;
        }
    }
}
