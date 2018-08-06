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
public class KatedraLogic extends AbstractLogic {

    private final GenericRepository<Katedra> gr;

    public KatedraLogic() {

        gr = new GenericRepository<>();
    }

    public List<KatedraDTO> getAll() throws Exception {
        try {

            List<Katedra> katedre = gr.getAll(Katedra.class, Constants.KATEDRA_FIND_ALL);
            List<KatedraDTO> katedreDTO = new ArrayList<>();

            for (Katedra katedra : katedre) {
                katedreDTO.add(Mapper.toKatedraDTO(katedra));
            }

            return katedreDTO;
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom ucitavanja svih katedri {" + e.getMessage() + "}");
        }
    }

    public KatedraDTO getById(int id) throws Exception {
        try {
            Katedra katedraFromDb = gr.getSingleByParamFromNamedQuery(id, Katedra.class, Constants.KATEDRA_FIND_BY_ID, Constants.KATEDRA_ID);

            return Mapper.toKatedraDTO(katedraFromDb);
        } catch (Exception ex) {
            throw new Exception("Dogodila se greska prilikom ucitavanja katedre sa id : " + id + " . {" + ex.getMessage() + "}");
        }
    }

}
