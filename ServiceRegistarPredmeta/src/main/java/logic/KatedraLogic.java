/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.Katedra;
import java.util.List;
import javax.transaction.Transactional;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
@Transactional
public class KatedraLogic extends AbstractLogic {
    
    private final GenericRepository<Katedra> gr;
    
    public KatedraLogic() {
        super();
        gr = new GenericRepository<>();
    }
    
    public List<Katedra> getAll() {
        return gr.getAll(Katedra.class, Constants.KATEDRA_FIND_ALL);
    }
    
    public Katedra getById(int id) {
        return gr.getSingleByParamFromNamedQuery(id, Katedra.class, Constants.KATEDRA_FIND_BY_ID, Constants.KATEDRA_ID);
    }
    
}
