/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dto.UdzbenikDTO;
import java.util.Map;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import ws.client.RestWSClient;

/**
 *
 * @author Petar
 */
@ManagedBean
@Named(value = "findUdzbenik")
@SessionScoped
public class FindUdzbenik {

    /**
     * Creates a new instance of FindUdzbenik
     */
    private RestWSClient restWSClient;
    private UdzbenikDTO udzbenik;

    public FindUdzbenik() {
        
        restWSClient = new RestWSClient("udzbenik");
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int udzbenikId = Integer.parseInt(params.get("udzbenikId"));
        udzbenik = restWSClient.getById_JSON(UdzbenikDTO.class, String.valueOf(udzbenikId));
       
    }


    public UdzbenikDTO getUdzbenik() {
        return udzbenik;
    }

    public void setUdzbenik(UdzbenikDTO udzbenik) {
        this.udzbenik = udzbenik;
    }

}
