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
@FacesValidator("validators.rbrIzdanjaValidator")
public class RbrIzdanjaValidator implements Validator {

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

            int rbr = 0;
            try {
                rbr = (int) value;
            } catch (Exception ex) {
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Redni broj izdanja mora biti ceo broj!", null));
            }

            if (rbr < 0) {
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Redni broj izdanja mora biti veci od 0!", null));
            }
        } catch (ValidatorException ve) {
            throw ve;
        } catch (Exception e) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Greska u sistemu prilikom validacije!", null));
        }
    }

}
