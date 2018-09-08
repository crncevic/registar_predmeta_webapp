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
@FacesValidator("validators.passwordValidator")
public class PasswordValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        try {

            if (value == null) {
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niste uneli password!", null));
            }

            String password = "";

            try {
                password = (String) value;
            } catch (Exception ex) {
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password mora imati bar jedan karakter!", null));
            }

            if (password.trim().length() == 0) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niste uneli password!", null));
            }
        } catch (ValidatorException ve) {
            throw ve;
        } catch (Exception ex) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Greska u sistemu prilikom validacije!", null));
        }
    }

}
