/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import constants.Constants;
import dto.UdzbenikDTO;
import dto.VrstaINivoStudijaDTO;
import java.util.LinkedHashMap;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import ws.client.RestWSClient;

/**
 *
 * @author Petar
 */
@FacesConverter("vrstaINivoStudijaConverter")
public class VrstaINivoStudijaConverter implements Converter{

    /**
     * Creates a new instance of VrstaINivoStudijaConverter
     */
    public VrstaINivoStudijaConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
try {
            RestWSClient restWSClient = new RestWSClient(Constants.VRSTA_I_NIVO_STUDIJA_CONTROLLER);

            if (value != null && value.trim().length() > 0) {
                try {

                    return restWSClient.getById_JSON(VrstaINivoStudijaDTO.class, value);
                } catch (NumberFormatException e) {
                    throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid value."));
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            if (value != null) {
                String naziv = String.valueOf((Integer) ((LinkedHashMap) value).get(Constants.VRSTA_ID));
                return naziv;
            } else {
                return null;

            }
        } catch (Exception ex) {
            return "";
        }
    }
    
}
