/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.Constants;
import dto.UdzbenikDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;

import ws.client.RestWSClient;

/**
 *
 * @author Petar
 */
@ManagedBean
@ViewScoped
@Named("allUdzbeniks")
public class AllUdzbeniks implements Serializable {

    /**
     * Creates a new instance of AllUdzbeniks
     */
    private RestWSClient restWSClient;
    private List<UdzbenikDTO> udzbenici;
    private ObjectMapper mapper;

    public AllUdzbeniks() {

    }

    @PostConstruct
    private void init() {
        restWSClient = new RestWSClient(Constants.UDZBENIK_CONTROLLER);
        mapper = new ObjectMapper();
        udzbenici = mapper.convertValue(restWSClient.getAll_JSON(List.class), new TypeReference<List<UdzbenikDTO>>() {});
    }

    public List<UdzbenikDTO> getUdzbenici() {
        return udzbenici;
    }

    public void setUdzbenici(List<UdzbenikDTO> udzbenici) {
        this.udzbenici = udzbenici;
    }

}
