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

    @Inject
    private GenericRepository<UlogaUdzbenik> gr;

   

    public List<UlogaUdzbenik> getAll() {
        try {
            return gr.getAll(UlogaUdzbenik.class, Constants.ULOGA_UDZBENIK_FIND_ALL);
        } catch (Exception e) {
            throw e;
        }
    }

    public UlogaUdzbenik getById(int id) {
        try {
            return gr.getSingleByParamFromNamedQuery(id, UlogaUdzbenik.class, Constants.ULOGA_FIND_BY_ID, Constants.ULOGA_ID);
        } catch (Exception e) {
            throw e;
        }
    }

}
