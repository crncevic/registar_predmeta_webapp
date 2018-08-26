/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import constants.Constants;
import dto.KatedraDTO;
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
@Named("allKatedra")
public class AllKatedra implements Serializable{

    /**
     * Creates a new instance of AllKatedra
     */
    
    private RestWSClient restWSClient;
    private List<KatedraDTO> katedre;
    
    public AllKatedra() {
        restWSClient = new RestWSClient(Constants.KATEDRA_CONTROLLER);
        katedre = restWSClient.getAll_JSON(List.class);
    }

    public List<KatedraDTO> getKatedre() {
        return katedre;
    }

    public void setKatedre(List<KatedraDTO> katedre) {
        this.katedre = katedre;
    }
    
    
    
}
