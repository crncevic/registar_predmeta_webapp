/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dto.KatedraDTO;
import dto.UdzbenikDTO;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import mapper.JSONMapper;
import ws.client.RestWSClient;

/**
 *
 * @author Petar
 */
@ManagedBean
@Named(value = "allUdzbeniks")
@SessionScoped
public class AllUdzbeniks {

    /**
     * Creates a new instance of AllUdzbeniks
     */
    
    private RestWSClient restWSClient;
    private List<UdzbenikDTO> udzbenici;
    
    public AllUdzbeniks() {
        restWSClient = new RestWSClient("udzbenik");
        udzbenici =  restWSClient.getAll_JSON(List.class);
    }

    public List<UdzbenikDTO> getUdzbenici() {
        return udzbenici;
    }

    public void setUdzbenici(List<UdzbenikDTO> udzbenici) {
        this.udzbenici = udzbenici;
    }
    
    
    
}
