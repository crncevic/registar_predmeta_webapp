/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.Nastavnik;
import dto.NastavnikDTO;
import java.util.ArrayList;
import java.util.List;
import mapper.Mapper;
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

    public List<NastavnikDTO> getAll() throws Exception {
        try {
            List<Nastavnik> nastavnici = gr.getAll(Nastavnik.class, Constants.NASTAVNIK_FIND_ALL);
            List<NastavnikDTO> nastavniciDTO = new ArrayList<>();

            for (Nastavnik nastavnik : nastavnici) {
                nastavniciDTO.add(Mapper.toNastavnikDTO(nastavnik));
            }

            return nastavniciDTO;
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom ucitavanja svih nastavnika  {" + e.getMessage() + "}");
        }
    }

    public NastavnikDTO getById(int id) throws Exception {
        try {
            Nastavnik nastavnikFromDb = gr.getSingleByParamFromNamedQuery(id, Nastavnik.class, Constants.NASTAVNIK_FIND_BY_ID, Constants.NASTAVNIK_ID);
            return Mapper.toNastavnikDTO(nastavnikFromDb);
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom ucitavanja nastavnika sa id : " + id + " {" + e.getMessage() + "}");
        }
    }

}
