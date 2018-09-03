/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import constants.Constants;
import dto.OsobaUVeziSaUdzbenikomDTO;
import dto.UdzbenikDTO;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.ws.rs.core.Response;
import org.primefaces.event.RowEditEvent;
import ws.client.RestWSClient;

/**
 *
 * @author Petar
 */
@ManagedBean
@Named(value = "createUdzbenik")
@ViewScoped
public class CreateUdzbenik implements Serializable {

    private UdzbenikDTO newUdzbenik;
    private RestWSClient restWSClient;

    public CreateUdzbenik() {

    }

    @PostConstruct
    private void init() {
        restWSClient = new RestWSClient(Constants.UDZBENIK_CONTROLLER);
        newUdzbenik = new UdzbenikDTO();
    }

    public UdzbenikDTO getNewUdzbenik() {
        return newUdzbenik;
    }

    public void setNewUdzbenik(UdzbenikDTO newUdzbenik) {
        this.newUdzbenik = newUdzbenik;
    }

    public void onRowEdit(RowEditEvent event) {
        OsobaUVeziSaUdzbenikomDTO newOsoba = (OsobaUVeziSaUdzbenikomDTO) event.getObject();
        newUdzbenik.getOsobaUVeziSaUdzbenikomList().add(newOsoba);

        FacesMessage msg = new FacesMessage("Osoba uspesno dodata");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Dodavanje otkazano");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String createUdzbenik() {
        Response response = restWSClient.create_JSON(newUdzbenik);

        if (response.getStatusInfo() == Response.Status.OK) {
            FacesMessage msg = new FacesMessage("Udzbenik uspesno kreiran!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "success-create";

        }
        return "failure-create";

    }
}
