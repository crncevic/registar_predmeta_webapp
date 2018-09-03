/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.Constants;
import dto.NastavnikDTO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
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
public class AllNastavnik implements Serializable {

    /**
     * Creates a new instance of AllNastavnik
     */
    private RestWSClient restWSClient;
    private List<NastavnikDTO> nastavnici;
    private ObjectMapper mapper;

    public AllNastavnik() {

    }

    @PostConstruct
    private void init() {
        mapper = new ObjectMapper();
        restWSClient = new RestWSClient(Constants.NASTAVNIK_CONTROLLER);
        nastavnici = mapper.convertValue(restWSClient.getAll_JSON(List.class), new TypeReference<List<NastavnikDTO>>() {
        });
    }

    public List<NastavnikDTO> getNastavnici() {
        return nastavnici;
    }

    public void setNastavnici(List<NastavnikDTO> nastavnici) {
        this.nastavnici = nastavnici;
    }

}
