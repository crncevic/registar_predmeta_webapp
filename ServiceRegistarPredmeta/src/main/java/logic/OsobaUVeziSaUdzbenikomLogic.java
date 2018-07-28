/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.OsobaUVeziSaUdzbenikom;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import repository.OsobaUVeziSaUdzbenikomRepository;

/**
 *
 * @author Petar
 */
public class OsobaUVeziSaUdzbenikomLogic extends AbstractLogic {

    private OsobaUVeziSaUdzbenikomRepository ouvsur;
    private Set<ConstraintViolation<OsobaUVeziSaUdzbenikom>> violations;

    public OsobaUVeziSaUdzbenikomLogic() {
        super();
        ouvsur = new OsobaUVeziSaUdzbenikomRepository();
    }

    public OsobaUVeziSaUdzbenikom create(OsobaUVeziSaUdzbenikom ouvsu) {
        try {
            violations = validator.validate(ouvsu);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO strukturna ogranicenja
            return ouvsur.create(ouvsu);
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
            return ouvsur.update(ouvsu);
        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception e) {
            throw e;
        }
    }
}
