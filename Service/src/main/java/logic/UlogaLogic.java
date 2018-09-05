/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.Uloga;
import java.util.List;
import javax.inject.Inject;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public class UlogaLogic extends AbstractLogicClass {

    private GenericRepository<Uloga> gr;

    public UlogaLogic() {
         gr = new GenericRepository(Uloga.class);
    }
    
    


    public List<Uloga> getAll() {
        try {
            return gr.getAll(Constants.ULOGA_FIND_ALL);
        } catch (Exception e) {
            throw e;
        }
    }

    public Uloga getById(int id) {
        try {
            return gr.getSingleByParamsFromNamedQuery(
                   new Object[]{ id },
                   Constants.ULOGA_FIND_BY_ID,
                   new String[]{ Constants.ULOGA_ID});
        } catch (Exception e) {
            throw e;
        }
    }

}
