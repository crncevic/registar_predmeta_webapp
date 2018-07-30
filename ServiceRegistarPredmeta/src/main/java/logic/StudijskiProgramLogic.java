/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.StudijskiProgram;
import java.util.List;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public class StudijskiProgramLogic extends AbstractLogic {

    private GenericRepository<StudijskiProgram> gr;

    public StudijskiProgramLogic() {
        super();
        gr = new GenericRepository<>();
    }

    public List<StudijskiProgram> getAll() {
        try {
            return gr.getAll(StudijskiProgram.class,Constants.STUDIJSKI_PROGRAM_FIND_ALL);
        } catch (Exception e) {
            throw e;
        }
    }

    public StudijskiProgram getById(int id) {
        try {
            return gr.getSingleByParamFromNamedQuery(id, StudijskiProgram.class, Constants.STUDIJSKI_PROGRAM_FIND_BY_ID, Constants.STUDIJSKI_PROGRAM_ID);
        } catch (Exception e) {
            throw e;
        }
    }
}
