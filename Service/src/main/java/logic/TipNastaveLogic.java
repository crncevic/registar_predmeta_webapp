/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.TipNastave;
import java.util.List;
import javax.inject.Inject;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public class TipNastaveLogic extends AbstractLogicClass {

    @Inject
    private GenericRepository<TipNastave> gr;

    

    public List<TipNastave> getAll() {
        try {
            return gr.getAll(Constants.TIP_NASTAVE_FIND_ALL);
        } catch (Exception e) {
            throw e;
        }
    }

    public TipNastave getById(int id) {
        try {
            return gr.getSingleByParamsFromNamedQuery(
                    new Object[] { id },
                    Constants.TIP_NASTAVE_FIND_BY_ID,
                    new String[] { Constants.TIP_NASTAVE_ID});
        } catch (Exception e) {
            throw e;
        }
    }

}
