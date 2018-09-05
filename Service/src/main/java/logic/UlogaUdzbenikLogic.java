/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.UlogaUdzbenik;
import java.util.List;
import javax.inject.Inject;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public class UlogaUdzbenikLogic extends AbstractLogicClass {

    private GenericRepository<UlogaUdzbenik> gr;

    public UlogaUdzbenikLogic() {
      gr = new GenericRepository(UlogaUdzbenik.class);
    }

   

    public List<UlogaUdzbenik> getAll() {
        try {
            return gr.getAll(Constants.ULOGA_UDZBENIK_FIND_ALL);
        } catch (Exception e) {
            throw e;
        }
    }

    public UlogaUdzbenik getById(int id) {
        try {
            return gr.getSingleByParamsFromNamedQuery(
                    new Object[]{ id },
                    Constants.ULOGA_FIND_BY_ID,
                    new String[]{Constants.ULOGA_ID});
        } catch (Exception e) {
            throw e;
        }
    }

}
