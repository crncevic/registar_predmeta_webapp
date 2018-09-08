/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import java.time.Year;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author Petar
 */
@FacesValidator("validators.godinaIzdanjaValidator")
public class GodinaIzdanjaValidator implements Validator {

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

            int year = 0;
            try {
                year = (int) value;
            } catch (Exception ex) {
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Godina izdanja mora biti ceo broj!", null));
            }

            if (year < 0 || year > Year.now().getValue()) {
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Godina izdanja mora biti izmedju 0 i " + Year.now().getValue(), null));
            }
        } catch (ValidatorException ve) {
            throw ve;
        } catch(Exception e){
             throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "Greska u sistemu prilikom validacije!", null));
        }

    }
}