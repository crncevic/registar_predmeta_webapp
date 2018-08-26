/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import constants.Constants;
import dto.UdzbenikDTO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ws.client.RestWSClient;

/**
 *
 * @author Petar
 */
@ManagedBean
@ViewScoped
@Named("allUdzbeniks")
public class AllUdzbeniks implements Serializable{

    /**
     * Creates a new instance of AllUdzbeniks
     */
    
    private RestWSClient restWSClient;
    private List<UdzbenikDTO> udzbenici;
    
    public AllUdzbeniks() {
        restWSClient = new RestWSClient(Constants.UDZBENIK_CONTROLLER);
        udzbenici =  restWSClient.getAll_JSON(List.class);
    }

    public List<UdzbenikDTO> getUdzbenici() {
        return udzbenici;
    }

    public void setUdzbenici(List<UdzbenikDTO> udzbenici) {
        this.udzbenici = udzbenici;
    }
    
    
    
}
