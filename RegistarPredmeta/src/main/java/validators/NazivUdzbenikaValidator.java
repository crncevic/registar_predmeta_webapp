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
@FacesValidator("validators.nazivUdzbenikaValidator")
public class NazivUdzbenikaValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
       if (value == null) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"Morate uneti naziv udzbenika!",null));
        }

        String naziv = (String) value;

        if (naziv.trim().length() == 0) {
            throw new ValidatorException(
                   new FacesMessage(FacesMessage.SEVERITY_ERROR,"Morate uneti naziv udzbenika!",null));
        }

        if (naziv.trim().length() < 3) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"Naziv udzbenika mora imati bar 3 slova!",null));
        }
    }
    
}
