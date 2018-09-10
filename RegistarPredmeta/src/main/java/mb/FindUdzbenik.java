/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.Constants;
import dto.OsobaUVeziSaUdzbenikomDTO;
import dto.UdzbenikDTO;
import dto.UlogaUdzbenikDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
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
public class FindUdzbenik implements Serializable {

    /**
     * Creates a new instance of FindUdzbenik
     */
    private RestWSClient restWSClient;
    private UdzbenikDTO udzbenik;
    private List<UlogaUdzbenikDTO> ulogeNaUdzbeniku;
    private List<OsobaUVeziSaUdzbenikomDTO> oldOsobe;
    private ObjectMapper mapper;

    public FindUdzbenik() {
    }

    @PostConstruct
    private void init() {
        mapper = new ObjectMapper();
        restWSClient = new RestWSClient(Constants.UDZBENIK_CONTROLLER);
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int udzbenikId = Integer.parseInt(params.get(Constants.UDZBENIK_ID));
        udzbenik = restWSClient.getById_JSON(UdzbenikDTO.class, String.valueOf(udzbenikId));

        //uloge udzbenik
        restWSClient = new RestWSClient(Constants.ULOGA_UDZBENIK_CONTROLLER);
        ulogeNaUdzbeniku = mapper.convertValue(restWSClient.getAll_JSON(List.class), new TypeReference<List<UlogaUdzbenikDTO>>() {
        });
        oldOsobe = new ArrayList<>();
        for (OsobaUVeziSaUdzbenikomDTO osobaUVeziSaUdzbenikomDTO : udzbenik.getOsobaUVeziSaUdzbenikomList()) {
            oldOsobe.add(osobaUVeziSaUdzbenikomDTO);
        }
        udzbenik.getOsobaUVeziSaUdzbenikomList();
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
                osobaUVeziSaUdzbenikomDTO.setUdzbenikId(udzbenik.getUdzbenikId());
                for (UlogaUdzbenikDTO ulogaUdzbenikDTO : ulogeNaUdzbeniku) {
                    if (ulogaUdzbenikDTO.getUlogaId() == osobaUVeziSaUdzbenikomDTO.getUlogaDTO().getUlogaId()) {
                        osobaUVeziSaUdzbenikomDTO.setUlogaDTO(ulogaUdzbenikDTO);
                    }
                }

            }
        }

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,  "Osoba azurirana!", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Azuriranje otkazano", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddNew() {

        if (udzbenik.getOsobaUVeziSaUdzbenikomList().size() > 0 && isLastOsobaEmpty(udzbenik.getOsobaUVeziSaUdzbenikomList())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Niste uneli podatke!",null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            if (udzbenik.getOsobaUVeziSaUdzbenikomList().size() > 1) {
                udzbenik.getOsobaUVeziSaUdzbenikomList().remove(udzbenik.getOsobaUVeziSaUdzbenikomList().size() - 1);
            }
            return;
        }

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
        newOsoba.setUlogaDTO(new UlogaUdzbenikDTO());
        udzbenik.getOsobaUVeziSaUdzbenikomList().add(newOsoba);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Nova osoba dodata",null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onDeleteRow(OsobaUVeziSaUdzbenikomDTO ouvsudto) {

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Red izbrisan", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        udzbenik.getOsobaUVeziSaUdzbenikomList().remove(ouvsudto);
    }

    public String onUpdate() {
        restWSClient = new RestWSClient(Constants.UDZBENIK_CONTROLLER);

        //brisanje privremenih ID-eva
        for (OsobaUVeziSaUdzbenikomDTO osobaUVeziSaUdzbenikomDTO : udzbenik.getOsobaUVeziSaUdzbenikomList()) {
            int counter = 0;
            for (OsobaUVeziSaUdzbenikomDTO oldOsoba : oldOsobe) {
                if (!osobaUVeziSaUdzbenikomDTO.getOsobaId().equals(oldOsoba.getOsobaId())) {
                    counter++;
                }
            }
            if (counter == oldOsobe.size() || oldOsobe.isEmpty()) {
                osobaUVeziSaUdzbenikomDTO.setOsobaId(null);
            }
        }

        Response response = restWSClient.update_JSON(udzbenik, udzbenik.getUdzbenikId().toString());

        if (response.getStatusInfo() == Response.Status.BAD_REQUEST) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska (HTTP 400) ", "Uzrok: " + response.getEntity().toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (response.getStatusInfo() == Response.Status.fromStatusCode(500)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska na serveru(HTTP 500)", "Dogodila se greska u sistemu. Sistem nije u stanju da azurira udzbenik!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (response.getStatusInfo() == Response.Status.OK) {
            return "success_create_udzbenik";
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal Error!", "Dogodila se greska prilikom azuriranja udzbenika!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "failure_create";

    }

    public String onDelete() {
        restWSClient = new RestWSClient(Constants.UDZBENIK_CONTROLLER);
        Response response = restWSClient.delete(udzbenik.getUdzbenikId().toString());

        if (response.getStatusInfo() == Response.Status.OK) {
            return "success_delete_udzbenik";
        }
        FacesMessage msg = new FacesMessage("Greska  prilikom  brisanja udzbenika");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "failure-delete";
    }

    private boolean isLastOsobaEmpty(List<OsobaUVeziSaUdzbenikomDTO> osobaUVeziSaUdzbenikomList) {
        OsobaUVeziSaUdzbenikomDTO ouvsudto = osobaUVeziSaUdzbenikomList.get(osobaUVeziSaUdzbenikomList.size() - 1);

        if ((ouvsudto.getIme() == null || ouvsudto.getIme().trim().length() == 0) && (ouvsudto.getPrezime() == null || ouvsudto.getPrezime().trim().length() == 0)
                && (ouvsudto.getTitula() == null || ouvsudto.getTitula().trim().length() == 0)) {
            return true;
        }

        return false;
    }

}
