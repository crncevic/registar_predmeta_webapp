/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.Katedra;
import java.util.List;
import javax.transaction.Transactional;
import repository.KatedraRepository;

/**
 *
 * @author Petar
 */
@Transactional
public class KatedraLogic extends AbstractLogic {

    private final KatedraRepository kr;

    public KatedraLogic() {
        super();
        kr = new KatedraRepository();
    }

    public List<Katedra> getAll() {
        return kr.getAll();
    }

    public Katedra getById(int id) {
        return kr.getById(id);
    }

}
