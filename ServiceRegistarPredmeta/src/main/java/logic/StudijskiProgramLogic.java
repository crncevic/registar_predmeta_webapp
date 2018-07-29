/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.StudijskiProgram;
import java.util.List;
import repository.StudijskiProgramRepository;

/**
 *
 * @author Petar
 */
public class StudijskiProgramLogic extends AbstractLogic {

    private StudijskiProgramRepository spr;

    public StudijskiProgramLogic() {
        super();
        spr = new StudijskiProgramRepository();
    }

    public List<StudijskiProgram> getAll() {
        try {
            return spr.getAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public StudijskiProgram getById(int id) {
        try {
            return spr.getById(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
