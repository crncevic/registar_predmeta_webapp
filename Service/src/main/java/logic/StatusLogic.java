/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.Status;
import java.util.List;
import javax.inject.Inject;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public class StatusLogic extends AbstractLogicClass {

    @Inject
    private GenericRepository<Status> gr;

    public List<Status> getAll() {
        try {
            return gr.getAll(Constants.STATUS_FIND_ALL);
        } catch (Exception e) {
            throw e;
        }
    }

    public Status getById(int id) {
        try {
            return gr.getSingleByParamsFromNamedQuery(new Object[]{ id },
                    Constants.STUDIJSKI_PROGRAM_FIND_BY_ID, 
                   new String[]{ Constants.STATUS_ID});
        } catch (Exception e) {
            throw e;
        }
    }
}
