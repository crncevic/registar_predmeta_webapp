/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.VrstaINivoStudija;
import java.util.List;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public class VrstaINivoStudijaLogic extends AbstractLogicClass {

    private GenericRepository<VrstaINivoStudija> gr;

    public VrstaINivoStudijaLogic() {
        gr = new GenericRepository<>();
    }

    public List<VrstaINivoStudija> getAll() {
        try {
            return gr.getAll(VrstaINivoStudija.class, Constants.VRSTA_I_NIVO_STUDIJA_FIND_ALL);
        } catch (Exception e) {
            throw e;
        }
    }

    public VrstaINivoStudija getById(int id) {
        try {
            return gr.getSingleByParamFromNamedQuery(id, VrstaINivoStudija.class, Constants.VRSTA_I_NIVO_STUDIJA_FIND_BY_ID, Constants.VRSTA_ID);
        } catch (Exception e) {
            throw e;
        }
    }

}
