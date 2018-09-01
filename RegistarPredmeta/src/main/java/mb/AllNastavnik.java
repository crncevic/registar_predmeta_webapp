/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import constants.Constants;
import dto.NastavnikDTO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.view.ViewScoped;
import ws.client.RestWSClient;

/**
 *
 * @author Petar
 */
@ManagedBean
@Named(value = "allNastavnik")
@ViewScoped
public class AllNastavnik implements Serializable{

    /**
     * Creates a new instance of AllNastavnik
     */
    
    private RestWSClient restWSClient;
    private List<NastavnikDTO> nastavnici; 
    private List<NastavnikDTO> filteredNastavnici;
    
    public AllNastavnik() {
        restWSClient = new RestWSClient(Constants.NASTAVNIK_CONTROLLER);
        nastavnici = restWSClient.getAll_JSON(List.class);
    }

    public List<NastavnikDTO> getNastavnici() {
        return nastavnici;
    }

    public void setNastavnici(List<NastavnikDTO> nastavnici) {
        this.nastavnici = nastavnici;
    }

    public List<NastavnikDTO> getFilteredNastavnici() {
        return filteredNastavnici;
    }

    public void setFilteredNastavnici(List<NastavnikDTO> filteredNastavnici) {
        this.filteredNastavnici = filteredNastavnici;
    }
    
    
    
    
    
}
