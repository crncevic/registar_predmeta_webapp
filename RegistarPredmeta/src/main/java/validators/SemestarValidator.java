/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Petar
 */
@FacesValidator("validators.semestarValidator")
public class SemestarValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        try {
            if (value == null) {
                return;
            }

            if (value instanceof String) {
                if (((String) value).trim().length() == 0) {
                    return;
                }
            }
            int semestar = 0;
            try {
                semestar = (int) value;
            } catch (Exception ex) {
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Semestar mora biti ceo broj!", null));
            }

            if (semestar < 0) {
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Semestar mora biti veci od 0!", null));
            }
        } catch (ValidatorException ve) {
            throw ve;
        } catch (Exception ex) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Greska u sistemu prilikom validacije!", null));
        }

    }

}
