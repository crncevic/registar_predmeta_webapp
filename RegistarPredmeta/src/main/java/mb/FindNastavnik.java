/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dto.NastavnikDTO;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import ws.client.RestWSClient;

/**
 *
 * @author Petar
 */
@ManagedBean
@Named(value = "findNastavnik")
@ViewScoped
public class FindNastavnik implements Serializable{

    private NastavnikDTO nastavnik;
    private RestWSClient restWSClient;
    
    
    public FindNastavnik() {
        restWSClient = new RestWSClient("nastavnik");
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int nastavnikId = Integer.parseInt(params.get("nastavnikId"));
        nastavnik = restWSClient.getById_JSON(NastavnikDTO.class, String.valueOf(nastavnikId));
    }

    public NastavnikDTO getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(NastavnikDTO nastavnik) {
        this.nastavnik = nastavnik;
    }
    
}
