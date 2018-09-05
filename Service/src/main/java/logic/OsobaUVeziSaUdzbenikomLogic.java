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
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import mapper.Mapper;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public class OsobaUVeziSaUdzbenikomLogic extends AbstractLogicClass {

    private GenericRepository<OsobaUVeziSaUdzbenikom> gr;
    private Set<ConstraintViolation<OsobaUVeziSaUdzbenikom>> violations;

    public OsobaUVeziSaUdzbenikomLogic() {
        gr = new GenericRepository(OsobaUVeziSaUdzbenikom.class);
    }

    
    
    public OsobaUVeziSaUdzbenikom create(OsobaUVeziSaUdzbenikom ouvsu) throws Exception {
        try {
            violations = validator.validate(ouvsu);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO strukturna ogranicenja
            try {
                et.begin();
                OsobaUVeziSaUdzbenikom createdOsoba = gr.save(ouvsu);
                et.commit();
                return createdOsoba;
            } catch (Exception ex) {
                et.rollback();
                throw ex;
            }
        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom kreiranja osobe ! {" + e.getMessage() + "}");
        }
    }

    public OsobaUVeziSaUdzbenikom update(OsobaUVeziSaUdzbenikom ouvsu) throws Exception {
        try {
            violations = validator.validate(ouvsu);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO strukturna ogranicenja
            try {
                et.begin();
                OsobaUVeziSaUdzbenikom updatedOsoba = gr.update(ouvsu);
                et.commit();
                return updatedOsoba;
            } catch (Exception e) {
                et.rollback();
                throw e;
            }

        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom azuriranja osobe ! {" + e.getMessage() + "}");
        }
    }

    public OsobaUVeziSaUdzbenikom delete(int id) throws Exception {
        try {
            //TODO strukturna ogranicenja
            try {
                et.begin();
                OsobaUVeziSaUdzbenikom deletedOsoba = gr.delete(id);
                et.commit();
                return deletedOsoba;
            } catch (Exception ex) {
                et.rollback();
                throw ex;
            }

        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom brisanja osobe ! {" + e.getMessage() + "}");
        }
    }

    public OsobaUVeziSaUdzbenikom getById(int id) throws Exception {
        try {

            return gr.getSingleByParamsFromNamedQuery(new Object[]{ id}, Constants.OSOBA_U_VEZI_SA_UDZBENIKOM_FIND_BY_OSOBA_ID,new String[]{ Constants.OSOBA_ID});

        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom ucitavanja osobe sa id:{" + e.getMessage() + "} ! {" + e.getMessage() + "}");
        }
    }

    public List<OsobaUVeziSaUdzbenikom> getOsobaByUdzbenikId(int udzbenikId) throws Exception {
        try {
            return gr.getListByParamsFromNamedQuery(new Object[]{ udzbenikId }, Constants.OSOBA_U_VEZI_SA_UDZBENIKOM_FIND_BY_UDZBENIK_ID,new String[] { Constants.UDZBENIK_ID });
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom ucitavanja svih osoba za udzbenik sa id:" + udzbenikId + " ! {" + e.getMessage() + "}");
        }
    }

    public List<OsobaUVeziSaUdzbenikom> getAll() throws Exception {
        try {
            return gr.getAll(Constants.OSOBA_U_VEZI_SA_UDZBENIKOM_FIND_ALL);

        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom ucitavanja svih osoba ! {" + e.getMessage() + "}");
        }
    }
}
