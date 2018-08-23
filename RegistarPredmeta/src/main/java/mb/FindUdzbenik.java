/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dto.OsobaUVeziSaUdzbenikomDTO;
import dto.UdzbenikDTO;
import dto.UlogaUdzbenikDTO;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.annotation.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import org.primefaces.event.RowEditEvent;
import ws.client.RestWSClient;

/**
 *
 * @author Petar
 */
@ManagedBean
@ViewScoped
@Named("findUdzbenik")
public class FindUdzbenik implements Serializable{

    /**
     * Creates a new instance of FindUdzbenik
     */
    private RestWSClient restWSClient;
    private UdzbenikDTO udzbenik;
    private List<UlogaUdzbenikDTO> ulogeNaUdzbeniku;
    private final List<OsobaUVeziSaUdzbenikomDTO> oldOsobe;

    public FindUdzbenik() {

        restWSClient = new RestWSClient("udzbenik");
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int udzbenikId = Integer.parseInt(params.get("udzbenikId"));
        udzbenik = restWSClient.getById_JSON(UdzbenikDTO.class, String.valueOf(udzbenikId));

        //uloge udzbenik
        restWSClient = new RestWSClient("uloga-udzbenik");
        ulogeNaUdzbeniku = restWSClient.getAll_JSON(List.class);
        oldOsobe = udzbenik.getOsobaUVeziSaUdzbenikomList();
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

    public void onRowEdit(RowEditEvent event) {

        OsobaUVeziSaUdzbenikomDTO updatedOsoba = (OsobaUVeziSaUdzbenikomDTO) event.getObject();

        for (OsobaUVeziSaUdzbenikomDTO osobaUVeziSaUdzbenikomDTO : udzbenik.getOsobaUVeziSaUdzbenikomList()) {
            if (osobaUVeziSaUdzbenikomDTO.equals(updatedOsoba)) {
                osobaUVeziSaUdzbenikomDTO.setIme(updatedOsoba.getIme());
                osobaUVeziSaUdzbenikomDTO.setPrezime(updatedOsoba.getPrezime());
                osobaUVeziSaUdzbenikomDTO.setTitula(updatedOsoba.getTitula());
                osobaUVeziSaUdzbenikomDTO.setUlogaDTO(updatedOsoba.getUlogaDTO());
            }
        }

        FacesMessage msg = new FacesMessage("Osoba azurirana");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Azuriranje otkazano");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddNew() {
        int temporaryId = 0;
        while (true) {
            temporaryId = (new Random()).nextInt();
            int counter = 0;

            for (OsobaUVeziSaUdzbenikomDTO osobaUVeziSaUdzbenikomDTO : udzbenik.getOsobaUVeziSaUdzbenikomList()) {
                if (temporaryId != osobaUVeziSaUdzbenikomDTO.getOsobaId()) {
                    counter++;
                }
            }

            if (counter == udzbenik.getOsobaUVeziSaUdzbenikomList().size()) {
                break;
            }
        }

        OsobaUVeziSaUdzbenikomDTO newOsoba = new OsobaUVeziSaUdzbenikomDTO();
        newOsoba.setOsobaId(temporaryId);
        udzbenik.getOsobaUVeziSaUdzbenikomList().add(newOsoba);
        FacesMessage msg = new FacesMessage("Nova osoba dodata");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onDeleteRow(OsobaUVeziSaUdzbenikomDTO ouvsudto) {

        

        FacesMessage msg = new FacesMessage("Red x izbrisan");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        udzbenik.getOsobaUVeziSaUdzbenikomList().remove(ouvsudto);
    }

    public String onUpdate() {
        restWSClient = new RestWSClient("udzbenik");

        //brisanje privremenih ID-eva
        for (OsobaUVeziSaUdzbenikomDTO osobaUVeziSaUdzbenikomDTO : udzbenik.getOsobaUVeziSaUdzbenikomList()) {
            int counter = 0;
            for (OsobaUVeziSaUdzbenikomDTO oldOsoba : oldOsobe) {
                if (!osobaUVeziSaUdzbenikomDTO.getOsobaId().equals(oldOsoba.getOsobaId())) {
                    counter++;
                }
            }
            if (counter == oldOsobe.size()) {
                osobaUVeziSaUdzbenikomDTO.setOsobaId(null);
            }
        }

        Response response = restWSClient.update_JSON(udzbenik, udzbenik.getUdzbenikId().toString());

        if (response.getStatusInfo() == Response.Status.OK) {
            FacesMessage msg = new FacesMessage("Udzbenik uspesno azuriran");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "success-update";
        }
        FacesMessage msg = new FacesMessage("Greska prilikom azuriranja udzbenika!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "failure-update";

    }

    public String onDelete() {
        restWSClient = new RestWSClient("udzbenik");
        Response response = restWSClient.delete(udzbenik.getUdzbenikId().toString());

        if (response.getStatusInfo() == Response.Status.OK) {
            FacesMessage msg = new FacesMessage("Udzbenik uspesno obrisan");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "success-delete";
        }
        FacesMessage msg = new FacesMessage("Greska  prilikom  brisanja udzbenika");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "failure-delete";
    }

}
