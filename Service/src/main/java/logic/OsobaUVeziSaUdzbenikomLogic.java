/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.OsobaUVeziSaUdzbenikom;
import dto.OsobaUVeziSaUdzbenikomDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import mapper.Mapper;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public class OsobaUVeziSaUdzbenikomLogic extends AbstractLogic {

    private GenericRepository<OsobaUVeziSaUdzbenikom> gr;
    private Set<ConstraintViolation<OsobaUVeziSaUdzbenikom>> violations;

    public OsobaUVeziSaUdzbenikomLogic() {

        gr = new GenericRepository<>();
    }

    public OsobaUVeziSaUdzbenikomDTO create(OsobaUVeziSaUdzbenikom ouvsu) throws Exception {
        try {
            violations = validator.validate(ouvsu);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO strukturna ogranicenja
            OsobaUVeziSaUdzbenikom createdOsoba = gr.save(ouvsu);
            return Mapper.toOsobaUVeziSaUzbenikDTO(ouvsu);
        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom kreiranja osobe ! {" + e.getMessage() + "}");
        }
    }

    public OsobaUVeziSaUdzbenikomDTO update(OsobaUVeziSaUdzbenikom ouvsu) throws Exception {
        try {
            violations = validator.validate(ouvsu);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO strukturna ogranicenja
            OsobaUVeziSaUdzbenikom updatedOsoba = gr.update(ouvsu);
            return Mapper.toOsobaUVeziSaUzbenikDTO(ouvsu);
        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom azuriranja osobe ! {" + e.getMessage() + "}");
        }
    }

    public OsobaUVeziSaUdzbenikomDTO delete(int id) throws Exception {
        try {
            //TODO strukturna ogranicenja
            OsobaUVeziSaUdzbenikom deletedOsoba = gr.delete(id, OsobaUVeziSaUdzbenikom.class);
            return Mapper.toOsobaUVeziSaUzbenikDTO(deletedOsoba);
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom brisanja osobe ! {" + e.getMessage() + "}");
        }
    }

    public OsobaUVeziSaUdzbenikomDTO getById(int id) throws Exception {
        try {
            OsobaUVeziSaUdzbenikom osobaFromDb
                    = gr.getSingleByParamFromNamedQuery(id, OsobaUVeziSaUdzbenikom.class, Constants.OSOBA_U_VEZI_SA_UDZBENIKOM_FIND_BY_OSOBA_ID, Constants.OSOBA_ID);
            return Mapper.toOsobaUVeziSaUzbenikDTO(osobaFromDb);
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom ucitavanja osobe sa id:{" + e.getMessage() + "} ! {" + e.getMessage() + "}");
        }
    }

    public List<OsobaUVeziSaUdzbenikomDTO> getOsobaByUdzbenikId(int udzbenikId) throws Exception {
        try {
            List<OsobaUVeziSaUdzbenikom> osobe = gr.getListByParamFromNamedQuery(udzbenikId, OsobaUVeziSaUdzbenikom.class, Constants.OSOBA_U_VEZI_SA_UDZBENIKOM_FIND_BY_UDZBENIK_ID, Constants.UDZBENIK_ID);
            List<OsobaUVeziSaUdzbenikomDTO> osobeDTO = new ArrayList<>();

            for (OsobaUVeziSaUdzbenikom osobaUVeziSaUdzbenikom : osobe) {
                osobeDTO.add(Mapper.toOsobaUVeziSaUzbenikDTO(osobaUVeziSaUdzbenikom));
            }

            return osobeDTO;

        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom ucitavanja svih osoba za udzbenik sa id:"+udzbenikId+" ! {" + e.getMessage() + "}");
        }
    }

    public List<OsobaUVeziSaUdzbenikomDTO> getAll() throws Exception {
        try {
            List<OsobaUVeziSaUdzbenikom> osobe = gr.getAll(OsobaUVeziSaUdzbenikom.class, Constants.OSOBA_U_VEZI_SA_UDZBENIKOM_FIND_ALL);
            
            List<OsobaUVeziSaUdzbenikomDTO> osobeDTO = new ArrayList<>();

            for (OsobaUVeziSaUdzbenikom osobaUVeziSaUdzbenikom : osobe) {
                osobeDTO.add(Mapper.toOsobaUVeziSaUzbenikDTO(osobaUVeziSaUdzbenikom));
            }

            return osobeDTO;
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom ucitavanja svih osoba ! {" + e.getMessage() + "}");
        }
    }
}
