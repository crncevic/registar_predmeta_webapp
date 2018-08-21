/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dto.OsobaUVeziSaUdzbenikomDTO;
import dto.UdzbenikDTO;
import dto.UlogaUdzbenikDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.GenericType;
import org.primefaces.event.RowEditEvent;
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
    private List<UlogaUdzbenikDTO> ulogeNaUdzbeniku;

    public FindUdzbenik() {

        restWSClient = new RestWSClient("udzbenik");
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int udzbenikId = Integer.parseInt(params.get("udzbenikId"));
        udzbenik = restWSClient.getById_JSON(UdzbenikDTO.class, String.valueOf(udzbenikId));

        //uloge udzbenik
        restWSClient = new RestWSClient("uloga-udzbenik");
        ulogeNaUdzbeniku = restWSClient.getAll_JSON(List.class);
    }

    public UdzbenikDTO getUdzbenik() {
        return udzbenik;
    }

    public void setUdzbenik(UdzbenikDTO udzbenik) {
        this.udzbenik = udzbenik;
    }

    public List<UlogaUdzbenikDTO> getUlogeNaUdzbeniku() {
        return ulogeNaUdzbeniku;
    }

    public void setUlogeNaUdzbeniku(List<UlogaUdzbenikDTO> ulogeNaUdzbeniku) {
        this.ulogeNaUdzbeniku = ulogeNaUdzbeniku;
    }

    public void onRowEdit(RowEditEvent rowEditEvent) {
        FacesMessage msg = new FacesMessage("Osoba azurirana", ((OsobaUVeziSaUdzbenikomDTO) rowEditEvent.getObject()).getOsobaId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent rowEditEvent) {
        FacesMessage msg = new FacesMessage("Azuriranje otkazano", ((OsobaUVeziSaUdzbenikomDTO) rowEditEvent.getObject()).getOsobaId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddNew() {
         udzbenik.getOsobaUVeziSaUdzbenikomList().add(new OsobaUVeziSaUdzbenikomDTO());
         FacesMessage msg = new FacesMessage("Nova osoba dodata");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
