/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.OsobaUVeziSaUdzbenikom;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
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

    public OsobaUVeziSaUdzbenikom create(OsobaUVeziSaUdzbenikom ouvsu) {
        try {
            violations = validator.validate(ouvsu);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO strukturna ogranicenja
            return gr.save(ouvsu);
        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception e) {
            throw e;
        }
    }

    public OsobaUVeziSaUdzbenikom update(OsobaUVeziSaUdzbenikom ouvsu) {
        try {
            violations = validator.validate(ouvsu);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO strukturna ogranicenja
            return gr.update(ouvsu);
        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception e) {
            throw e;
        }
    }

    public OsobaUVeziSaUdzbenikom delete(int id) {
        try {
            //TODO strukturna ogranicenja
            return gr.delete(id, OsobaUVeziSaUdzbenikom.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public OsobaUVeziSaUdzbenikom getById(int id) {
        try {
            return gr.getSingleByParamFromNamedQuery(id, OsobaUVeziSaUdzbenikom.class, Constants.OSOBA_U_VEZI_SA_UDZBENIKOM_FIND_BY_OSOBA_ID, Constants.OSOBA_ID);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<OsobaUVeziSaUdzbenikom> getAll() {
        try {
            return gr.getAll(OsobaUVeziSaUdzbenikom.class, Constants.OSOBA_U_VEZI_SA_UDZBENIKOM_FIND_ALL);
        } catch (Exception e) {
            throw e;
        }
    }
}
