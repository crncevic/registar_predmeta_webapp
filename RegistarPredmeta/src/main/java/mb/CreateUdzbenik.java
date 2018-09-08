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
import java.util.Random;
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
    private List<UlogaUdzbenikDTO> ulogeNaUdzbeniku;
    private ObjectMapper mapper;

    public CreateUdzbenik() {

    }

    @PostConstruct
    private void init() {
        mapper = new ObjectMapper();
        restWSClient = new RestWSClient(Constants.UDZBENIK_CONTROLLER);
        newUdzbenik = new UdzbenikDTO();
        newUdzbenik.setOsobaUVeziSaUdzbenikomList(new ArrayList<OsobaUVeziSaUdzbenikomDTO>());

        restWSClient = new RestWSClient(Constants.ULOGA_UDZBENIK_CONTROLLER);
        ulogeNaUdzbeniku = mapper.convertValue(
                restWSClient.getAll_JSON(List.class),
                new TypeReference<List<UlogaUdzbenikDTO>>() {
        });
    }

    public UdzbenikDTO getNewUdzbenik() {
        return newUdzbenik;
    }

    public void setNewUdzbenik(UdzbenikDTO newUdzbenik) {
        this.newUdzbenik = newUdzbenik;
    }

    public List<UlogaUdzbenikDTO> getUlogeNaUdzbeniku() {
        return ulogeNaUdzbeniku;
    }

    public void setUlogeNaUdzbeniku(List<UlogaUdzbenikDTO> ulogeNaUdzbeniku) {
        this.ulogeNaUdzbeniku = ulogeNaUdzbeniku;
    }

    public void onRowEdit(RowEditEvent event) {
        OsobaUVeziSaUdzbenikomDTO newOsoba = (OsobaUVeziSaUdzbenikomDTO) event.getObject();
        for (OsobaUVeziSaUdzbenikomDTO osobaUVeziSaUdzbenikomDTO : newUdzbenik.getOsobaUVeziSaUdzbenikomList()) {

            if (osobaUVeziSaUdzbenikomDTO.getOsobaId() == newOsoba.getOsobaId()) {
                osobaUVeziSaUdzbenikomDTO.setIme(newOsoba.getIme());
                osobaUVeziSaUdzbenikomDTO.setPrezime(newOsoba.getPrezime());
                osobaUVeziSaUdzbenikomDTO.setTitula(newOsoba.getTitula());
                for (UlogaUdzbenikDTO ulogaUdzbenikDTO : ulogeNaUdzbeniku) {
                    if (ulogaUdzbenikDTO.getUlogaId() == newOsoba.getUlogaDTO().getUlogaId()) {
                        osobaUVeziSaUdzbenikomDTO.setUlogaDTO(ulogaUdzbenikDTO);
                    }
                }

                FacesMessage msg = new FacesMessage("Osoba uspesno azurirana");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return;
            }
        }
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Dodavanje otkazano");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddNew() {

        if (newUdzbenik.getOsobaUVeziSaUdzbenikomList().size() > 0 && isLastOsobaEmpty(newUdzbenik.getOsobaUVeziSaUdzbenikomList())) {
            FacesMessage msg = new FacesMessage("Niste uneli podatke!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            if (newUdzbenik.getOsobaUVeziSaUdzbenikomList().size() > 1) {
                newUdzbenik.getOsobaUVeziSaUdzbenikomList().remove(newUdzbenik.getOsobaUVeziSaUdzbenikomList().size() - 1);
            }
            return;
        }

        int temporaryId = 0;
        while (true) {
            temporaryId = (new Random()).nextInt();
            int counter = 0;

            for (OsobaUVeziSaUdzbenikomDTO osobaUVeziSaUdzbenikomDTO : newUdzbenik.getOsobaUVeziSaUdzbenikomList()) {
                if (temporaryId != osobaUVeziSaUdzbenikomDTO.getOsobaId()) {
                    counter++;
                }
            }

            if (counter == newUdzbenik.getOsobaUVeziSaUdzbenikomList().size()) {
                break;
            }
        }

        OsobaUVeziSaUdzbenikomDTO newOsoba = new OsobaUVeziSaUdzbenikomDTO();
        newOsoba.setOsobaId(temporaryId);
        newOsoba.setUlogaDTO(new UlogaUdzbenikDTO());
        newUdzbenik.getOsobaUVeziSaUdzbenikomList().add(newOsoba);
        FacesMessage msg = new FacesMessage("Nova osoba dodata");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onDeleteRow(OsobaUVeziSaUdzbenikomDTO ouvsudto) {

        FacesMessage msg = new FacesMessage("Red x izbrisan");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        newUdzbenik.getOsobaUVeziSaUdzbenikomList().remove(ouvsudto);
    }

    public String createUdzbenik() {

        restWSClient = new RestWSClient(Constants.UDZBENIK_CONTROLLER);

        for (OsobaUVeziSaUdzbenikomDTO osobaUVeziSaUdzbenikomDTO : newUdzbenik.getOsobaUVeziSaUdzbenikomList()) {
            osobaUVeziSaUdzbenikomDTO.setOsobaId(null);
        }

        Response response = restWSClient.create_JSON(newUdzbenik);

        if (response.getStatusInfo() == Response.Status.OK) {
            return "success_create_udzbenik";
        }
        FacesMessage msg = new FacesMessage("Udzbenik uspesno kreiran!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "failure_create";

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
