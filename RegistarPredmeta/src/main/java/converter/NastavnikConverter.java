/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import constants.Constants;
import dto.NastavnikDTO;
import dto.TipNastaveDTO;
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
@FacesConverter("nastavnikConverter")
public class NastavnikConverter implements Converter{

    /**
     * Creates a new instance of NastavnikConverter
     */
    public NastavnikConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            RestWSClient restWSClient = new RestWSClient(Constants.NASTAVNIK_CONTROLLER);
            
            if (value != null && value.trim().length() > 0) {
            try {

                return restWSClient.getById_JSON(NastavnikDTO.class, value);
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
   try{
        if (value != null) {
            
            if(value instanceof NastavnikDTO){
                if(((NastavnikDTO) value).getNastavnikId()!= null){
                  return String.valueOf(((NastavnikDTO) value).getNastavnikId()); 
                }
                return null;
            }
            String naziv =  String.valueOf((Integer)((LinkedHashMap) value).get(Constants.NASTAVNIK_ID));
            return naziv;
        } else {
            return null;
        }
        }catch(Exception ex){
            return "";
        }
    }
    
}
