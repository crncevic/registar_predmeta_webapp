/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.Nastavnik;
import java.util.List;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public class NastavnikLogic extends AbstractLogic {

    private final GenericRepository<Nastavnik> gr;

    public NastavnikLogic() {
       
        gr = new GenericRepository();
    }

    public List<Nastavnik> getAll() {
        try {
            return gr.getAll(Nastavnik.class, Constants.NASTAVNIK_FIND_ALL);
        } catch (Exception e) {
            throw e;
        }
    }

    public Nastavnik getById(int id) {
        try {
            return gr.getSingleByParamFromNamedQuery(id, Nastavnik.class, Constants.NASTAVNIK_FIND_BY_ID, Constants.NASTAVNIK_ID);
        } catch (Exception e) {
            throw e;
        }
    }

}
