/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.VrstaINivoStudija;
import java.util.List;
import javax.inject.Inject;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public class VrstaINivoStudijaLogic extends AbstractLogicClass {

    private GenericRepository<VrstaINivoStudija> gr;

    public VrstaINivoStudijaLogic() {
       gr = new GenericRepository(VrstaINivoStudija.class);
    }
    
    

    public List<VrstaINivoStudija> getAll() {
        try {
            return gr.getAll(Constants.VRSTA_I_NIVO_STUDIJA_FIND_ALL);
        } catch (Exception e) {
            throw e;
        }
    }

    public VrstaINivoStudija getById(int id) {
        try {
            return gr.getSingleByParamsFromNamedQuery(
                   new Object[] {id},
                   Constants.VRSTA_I_NIVO_STUDIJA_FIND_BY_ID,
                   new String[]{ Constants.VRSTA_ID});
        } catch (Exception e) {
            throw e;
        }
    }

}
