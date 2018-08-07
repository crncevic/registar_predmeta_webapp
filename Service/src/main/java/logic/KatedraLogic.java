/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.Katedra;
import dto.KatedraDTO;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import mapper.Mapper;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
@Transactional
public class KatedraLogic extends AbstractLogicClass {

    private final GenericRepository<Katedra> gr;

    public KatedraLogic() {

        gr = new GenericRepository<>();
    }

    public List<Katedra> getAll() throws Exception {
        try {

            return gr.getAll(Katedra.class, Constants.KATEDRA_FIND_ALL);

        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom ucitavanja svih katedri {" + e.getMessage() + "}");
        }
    }

    public Katedra getById(int id) throws Exception {
        try {
            return gr.getSingleByParamFromNamedQuery(id, Katedra.class, Constants.KATEDRA_FIND_BY_ID, Constants.KATEDRA_ID);

        } catch (Exception ex) {
            throw new Exception("Dogodila se greska prilikom ucitavanja katedre sa id : " + id + " . {" + ex.getMessage() + "}");
        }
    }

}
