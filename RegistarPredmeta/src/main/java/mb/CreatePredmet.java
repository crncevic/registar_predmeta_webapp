/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.Constants;
import dto.NastavnikDTO;
import dto.NastavnikNaPredmetuDTO;
import dto.PredmetDTO;
import dto.TipNastaveDTO;
import dto.UdzbenikDTO;
import dto.UdzbenikNaPredmetuDTO;
import dto.VrstaINivoStudijaDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.ws.rs.core.Response;

import org.primefaces.event.RowEditEvent;
import ws.client.RestWSClient;

/**
 *
 * @author Petar
 */
@ManagedBean
@Named(value = "createPredmet")
@ViewScoped
public class CreatePredmet implements Serializable {

    private RestWSClient restWSClient;
    private PredmetDTO newPredmet;
    private List<UdzbenikDTO> udzbenici;
    private List<NastavnikDTO> nastavnici;
    private List<VrstaINivoStudijaDTO> vrsteINivoiStudija;
    private List<TipNastaveDTO> tipoviNastave;

    private UdzbenikDTO selectedUdzbenik;
    private NastavnikDTO selectedNastavnik;
    private TipNastaveDTO selectedTipNastave;
    private VrstaINivoStudijaDTO selectedVrstaINivoStudija;

    private ObjectMapper mapper;

    public CreatePredmet() {
    }

    @PostConstruct
    private void init() {
        mapper = new ObjectMapper();

        newPredmet = new PredmetDTO();
        newPredmet.setUdzbenici(new ArrayList());
        newPredmet.setNastavnici(new ArrayList());
        newPredmet.setTematskaCeline(new ArrayList());
        newPredmet.setVrstaINivoStudija(new VrstaINivoStudijaDTO());

        selectedNastavnik = new NastavnikDTO();
        selectedTipNastave = new TipNastaveDTO();
        selectedUdzbenik = new UdzbenikDTO();

        restWSClient = new RestWSClient(Constants.VRSTA_I_NIVO_STUDIJA_CONTROLLER);
        vrsteINivoiStudija = mapper.convertValue(restWSClient.getAll_JSON(List.class), new TypeReference<List<VrstaINivoStudijaDTO>>() {
        });

        restWSClient = new RestWSClient(Constants.UDZBENIK_CONTROLLER);
        udzbenici = mapper.convertValue(restWSClient.getAll_JSON(List.class), new TypeReference<List<UdzbenikDTO>>() {
        });

        restWSClient = new RestWSClient(Constants.NASTAVNIK_CONTROLLER);
        nastavnici = mapper.convertValue(restWSClient.getAll_JSON(List.class), new TypeReference<List<NastavnikDTO>>() {
        });

        restWSClient = new RestWSClient(Constants.TIP_NASTAVE_CONTROLLER);
        tipoviNastave = mapper.convertValue(restWSClient.getAll_JSON(List.class), new TypeReference<List<TipNastaveDTO>>() {
        });
    }

    //<editor-fold defaultstate="collapsed" desc="Getters and setters">
    public PredmetDTO getNewPredmet() {
        return newPredmet;
    }

    public void setNewPredmet(PredmetDTO newPredmet) {
        this.newPredmet = newPredmet;
    }

    public List<UdzbenikDTO> getUdzbenici() {
        return udzbenici;
    }

    public void setUdzbenici(List<UdzbenikDTO> udzbenici) {
        this.udzbenici = udzbenici;
    }

    public List<NastavnikDTO> getNastavnici() {
        return nastavnici;
    }

    public void setNastavnici(List<NastavnikDTO> nastavnici) {
        this.nastavnici = nastavnici;
    }

    public List<VrstaINivoStudijaDTO> getVrsteINivoiStudija() {

        return vrsteINivoiStudija;
    }

    public void setVrsteINivoiStudija(List<VrstaINivoStudijaDTO> vrsteINivoiStudija) {
        this.vrsteINivoiStudija = vrsteINivoiStudija;
    }

    public List<TipNastaveDTO> getTipoviNastave() {
        return tipoviNastave;
    }

    public void setTipoviNastave(List<TipNastaveDTO> tipoviNastave) {
        this.tipoviNastave = tipoviNastave;
    }

    public UdzbenikDTO getSelectedUdzbenik() {
        return selectedUdzbenik;
    }

    public void setSelectedUdzbenik(UdzbenikDTO selectedUdzbenik) {
        this.selectedUdzbenik = selectedUdzbenik;
    }

    public NastavnikDTO getSelectedNastavnik() {
        return selectedNastavnik;
    }

    public void setSelectedNastavnik(NastavnikDTO selectedNastavnik) {
        this.selectedNastavnik = selectedNastavnik;
    }

    public TipNastaveDTO getSelectedTipNastave() {
        return selectedTipNastave;
    }

    public void setSelectedTipNastave(TipNastaveDTO selectedTipNastave) {
        this.selectedTipNastave = selectedTipNastave;
    }

    public VrstaINivoStudijaDTO getSelectedVrstaINivoStudija() {
        return selectedVrstaINivoStudija;
    }

    public void setSelectedVrstaINivoStudija(VrstaINivoStudijaDTO selectedVrstaINivoStudija) {
        this.selectedVrstaINivoStudija = selectedVrstaINivoStudija;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Listeneri">
    public void selectedTipNastaveListener(AjaxBehaviorEvent event) {
        String id = (String) ((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent(":predmetFormCreate:tabview:createInputPredmetTipNastave")).getSubmittedValue();
        restWSClient = new RestWSClient(Constants.TIP_NASTAVE_CONTROLLER);
        this.selectedTipNastave = restWSClient.getById_JSON(TipNastaveDTO.class, id);
    }

    public void selectedNastavnikListener(AjaxBehaviorEvent event) {
        String id = (String) ((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent(":predmetFormCreate:tabview:createInputPredmetNastavnik")).getSubmittedValue();
        restWSClient = new RestWSClient(Constants.NASTAVNIK_CONTROLLER);
        this.selectedNastavnik = restWSClient.getById_JSON(NastavnikDTO.class, id);
    }

    public void selectedUdzbenikListener(AjaxBehaviorEvent event) {
        String id = (String) ((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent(":predmetFormCreate:tabview:createInputPredmetUdzbenik")).getSubmittedValue();
        restWSClient = new RestWSClient(Constants.UDZBENIK_CONTROLLER);
        this.selectedUdzbenik = restWSClient.getById_JSON(UdzbenikDTO.class, id);
    }

    public void selectedVrstaINivoStudijaListener(AjaxBehaviorEvent event) {
        String id = (String) ((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent(":predmetFormCreate:createInputPredmetVrstaINivoStudija")).getSubmittedValue();
        restWSClient = new RestWSClient(Constants.VRSTA_I_NIVO_STUDIJA_CONTROLLER);
        this.selectedVrstaINivoStudija = restWSClient.getById_JSON(VrstaINivoStudijaDTO.class, id);
        ((UIOutput) FacesContext.getCurrentInstance().getViewRoot().findComponent(":predmetFormCreate:vinsValue")).setValue(selectedVrstaINivoStudija.getNaziv());
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Metode">
    public void onAddNewNastavnikNaPredmetu() {

        if (selectedNastavnik == null) {
            FacesMessage msg = new FacesMessage("Niste izabrali nastavnika");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }

        if (selectedTipNastave == null) {
            FacesMessage msg = new FacesMessage("Niste izabrali tip nastave");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }

        NastavnikNaPredmetuDTO nnpdto = new NastavnikNaPredmetuDTO();
        nnpdto.setNastavnikDTO(selectedNastavnik);
        nnpdto.setTipNastaveDTO(selectedTipNastave);
        nnpdto.setNastavnikId(selectedNastavnik.getNastavnikId());
        nnpdto.setTipNastaveId(selectedTipNastave.getTipnastaveId());

        for (NastavnikNaPredmetuDTO nastavnikNaPredmetuDTO : newPredmet.getNastavnici()) {
            if (nastavnikNaPredmetuDTO.getNastavnikDTO().getNastavnikId() == nnpdto.getNastavnikDTO().getNastavnikId()
                    && nastavnikNaPredmetuDTO.getTipNastaveDTO().getTipnastaveId() == nnpdto.getTipNastaveDTO().getTipnastaveId()) {
                FacesMessage msg = new FacesMessage("Taj nastavnik i taj tip nastave vec postoje");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return;
            }
        }

        newPredmet.getNastavnici().add(nnpdto);

        FacesMessage msg = new FacesMessage(nnpdto.getNastavnikDTO().getIme() + " " + nnpdto.getNastavnikDTO().getPrezime() + " drzi " + nnpdto.getTipNastaveDTO().getNaziv());
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void onDeleteNastavnikNaPredmetu(NastavnikNaPredmetuDTO nnpdto) {

        newPredmet.getNastavnici().remove(nnpdto);

        FacesMessage msg = new FacesMessage(nnpdto.getNastavnikDTO().getIme() + " " + nnpdto.getNastavnikDTO().getPrezime() + " vise ne drzi " + nnpdto.getTipNastaveDTO().getNaziv());
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void onAddNewUdzbenikNaPredmetu() {
        if (selectedUdzbenik == null) {
            FacesMessage msg = new FacesMessage("Niste izabrali udzbenik");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }

        UdzbenikNaPredmetuDTO unpdto = new UdzbenikNaPredmetuDTO();
        unpdto.setUdzbenikDTO(selectedUdzbenik);
        unpdto.setUdzbenikId(selectedUdzbenik.getUdzbenikId());

        for (UdzbenikNaPredmetuDTO udzbenikNaPredmetuDTO : newPredmet.getUdzbenici()) {
            if (udzbenikNaPredmetuDTO.getUdzbenikDTO().getUdzbenikId() == unpdto.getUdzbenikDTO().getUdzbenikId()) {
                FacesMessage msg = new FacesMessage(unpdto.getUdzbenikDTO().getNaziv() + " vec postoji na predmetu !");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return;
            }
        }

        newPredmet.getUdzbenici().add(unpdto);
        FacesMessage msg = new FacesMessage(unpdto.getUdzbenikDTO().getNaziv() + " dodat na predmet");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onDeleteUdzbenikNaPredmetu(UdzbenikNaPredmetuDTO udzbenikDTO) {

        newPredmet.getUdzbenici().remove(udzbenikDTO);
        FacesMessage msg = new FacesMessage(udzbenikDTO.getUdzbenikDTO().getNaziv() + " izbrisan sa predmeta! ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //</editor-fold>
    public String onCreate() {
        restWSClient = new RestWSClient(Constants.PREDMET_CONTROLLER);

        Response response = restWSClient.create_JSON(newPredmet);

        if (response.getStatusInfo() == Response.Status.BAD_REQUEST) {
            FacesMessage msg = new FacesMessage(response.getEntity().toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "success";
        } else if (response.getStatusInfo() == Response.Status.fromStatusCode(500)) {
            FacesMessage msg = new FacesMessage("Dogodila se greska u sistemu. Sistem nije u stanju da zapamti predmet!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (response.getStatusInfo() == Response.Status.OK) {
            FacesMessage msg = new FacesMessage("Sistem je uspesno zapamtio novi predmet");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "success";
        }
        FacesMessage msg = new FacesMessage("Dogodila se greska prilikom kreiranja predmeta!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "failure";
    }

}
