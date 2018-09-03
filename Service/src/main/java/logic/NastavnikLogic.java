/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.Nastavnik;
import java.util.List;
import javax.inject.Inject;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public class NastavnikLogic extends AbstractLogicClass {

    @Inject
    private GenericRepository<Nastavnik> gr;

    public List<Nastavnik> getAll() throws Exception {
        try {
            return gr.getAll(Constants.NASTAVNIK_FIND_ALL);
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom ucitavanja svih nastavnika  {" + e.getMessage() + "}");
        }
    }

    public Nastavnik getById(int id) throws Exception {
        try {
            return gr.getSingleByParamsFromNamedQuery(new Object[] {id}, Constants.NASTAVNIK_FIND_BY_ID,new String[]{ Constants.NASTAVNIK_ID});
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom ucitavanja nastavnika sa id : " + id + " {" + e.getMessage() + "}");
        }
    }

}
