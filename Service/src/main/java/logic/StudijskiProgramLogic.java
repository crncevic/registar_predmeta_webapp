/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.PredmetNaStudijskomProgramu;
import domain.StudijskiProgram;
import java.util.List;
import javax.inject.Inject;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public class StudijskiProgramLogic extends AbstractLogicClass {

    private GenericRepository<StudijskiProgram> gr;

    public StudijskiProgramLogic() {
        gr = new GenericRepository(StudijskiProgram.class);
    }

    public List<StudijskiProgram> getAll() {
        try {
            return gr.getAll(Constants.STUDIJSKI_PROGRAM_FIND_ALL);
        } catch (Exception e) {
            throw e;
        }
    }

    public StudijskiProgram getById(int id) {
        try {
            return gr.getSingleByParamsFromNamedQuery(new Object[]{id},
                    Constants.STUDIJSKI_PROGRAM_FIND_BY_ID,
                    new String[]{Constants.STUDIJSKI_PROGRAM_ID});
        } catch (Exception e) {
            throw e;
        }
    }

}
