/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.Katedra;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
@RequestScoped
public class KatedraLogic extends AbstractLogicClass {

    @Inject
    private GenericRepository<Katedra> gr;

   

    public List<Katedra> getAll() throws Exception {
        try {

            return gr.getAll(Constants.KATEDRA_FIND_ALL);

        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom ucitavanja svih katedri {" + e.getMessage() + "}");
        }
    }

    public Katedra getById(int id) throws Exception {
        try {
            return gr.getSingleByParamsFromNamedQuery(new Object[] {id},Constants.KATEDRA_FIND_BY_ID,new String[]{ Constants.KATEDRA_ID});

        } catch (Exception ex) {
            throw new Exception("Dogodila se greska prilikom ucitavanja katedre sa id : " + id + " . {" + ex.getMessage() + "}");
        }
    }

}
