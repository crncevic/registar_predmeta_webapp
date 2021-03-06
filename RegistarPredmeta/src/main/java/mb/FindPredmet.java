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
import dto.PredmetNaStudijskomProgramuDTO;
import dto.StatusDTO;
import dto.StudijskiProgramDTO;
import dto.TipNastaveDTO;
import dto.UdzbenikDTO;
import dto.UdzbenikNaPredmetuDTO;
import dto.VrstaINivoStudijaDTO;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.ws.rs.core.Response;
import ws.client.RestWSClient;

/**
 *
 * @author Petar
 */
@ManagedBean
@Named(value = "findPredmet")
@ViewScoped
public class FindPredmet implements Serializable {

    private PredmetDTO predmet;
    private RestWSClient restWSClient;
    private List<VrstaINivoStudijaDTO> vrsteINivoiStudija;
    private List<NastavnikDTO> nastavnici;
    private List<TipNastaveDTO> tipoviNastave;
    private List<UdzbenikDTO> udzbenici;
    private ObjectMapper mapper;

    private NastavnikDTO selectedNastavnik;
    private TipNastaveDTO selectedTipNastave;
    private UdzbenikDTO selectedUdzbenik;
    private VrstaINivoStudijaDTO selectedVrstaINivoStudija;

    public FindPredmet() {
    }

    @PostConstruct
    private void init() {
        mapper = new ObjectMapper();

        restWSClient = new RestWSClient(Constants.PREDMET_CONTROLLER);
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int predmetId = Integer.parseInt(params.get(Constants.PREDMET_ID));
        predmet = restWSClient.getById_JSON(PredmetDTO.class, String.valueOf(predmetId));

        restWSClient = new RestWSClient(Constants.UDZBENIK_CONTROLLER);
        udzbenici = mapper.convertValue(restWSClient.getAll_JSON(List.class), new TypeReference<List<UdzbenikDTO>>() {
        });

        restWSClient = new RestWSClient(Constants.VRSTA_I_NIVO_STUDIJA_CONTROLLER);
        vrsteINivoiStudija = mapper.convertValue(restWSClient.getAll_JSON(List.class), new TypeReference<List<VrstaINivoStudijaDTO>>() {
        });

        restWSClient = new RestWSClient(Constants.NASTAVNIK_CONTROLLER);
        nastavnici = mapper.convertValue(restWSClient.getAll_JSON(List.class), new TypeReference<List<NastavnikDTO>>() {
        });

        restWSClient = new RestWSClient(Constants.TIP_NASTAVE_CONTROLLER);
        tipoviNastave = mapper.convertValue(restWSClient.getAll_JSON(List.class), new TypeReference<List<TipNastaveDTO>>() {
        });

        selectedVrstaINivoStudija = predmet.getVrstaINivoStudija();
        selectedNastavnik = new NastavnikDTO();
        selectedTipNastave = new TipNastaveDTO();
        selectedUdzbenik = new UdzbenikDTO();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters and setters">
    public PredmetDTO getPredmet() {
        return predmet;
    }

    public void setPredmet(PredmetDTO predmet) {
        this.predmet = predmet;
    }

    public List<UdzbenikDTO> getUdzbenici() {

        return udzbenici;
    }

    public void setUdzbenici(List<UdzbenikDTO> udzbenici) {
        this.udzbenici = udzbenici;
    }

    public List<VrstaINivoStudijaDTO> getVrsteINivoiStudija() {

        return vrsteINivoiStudija;
    }

    public void setVrsteINivoiStudija(List<VrstaINivoStudijaDTO> vrsteINivoiStudija) {
        this.vrsteINivoiStudija = vrsteINivoiStudija;
    }

    public List<NastavnikDTO> getNastavnici() {

        return nastavnici;
    }

    public void setNastavnici(List<NastavnikDTO> nastavnici) {
        this.nastavnici = nastavnici;
    }

    public List<TipNastaveDTO> getTipoviNastave() {

        return tipoviNastave;
    }

    public NastavnikDTO getSelectedNastavnik() {
        return selectedNastavnik;
    }

    public void setSelectedNastavnik(NastavnikDTO selectedNastavnik) {
        this.selectedNastavnik = selectedNastavnik;
    }

    public UdzbenikDTO getSelectedUdzbenik() {
        return selectedUdzbenik;
    }

    public void setSelectedUdzbenik(UdzbenikDTO selectedUdzbenik) {
        this.selectedUdzbenik = selectedUdzbenik;
    }

    public TipNastaveDTO getSelectedTipNastave() {
        return selectedTipNastave;
    }

    public void setSelectedTipNastave(TipNastaveDTO selectedTipNastave) {
        this.selectedTipNastave = selectedTipNastave;
    }

    public void setTipoviNastave(List<TipNastaveDTO> tipoviNastave) {
        this.tipoviNastave = tipoviNastave;
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
        String id = (String) ((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent(":predmetForm:tabview:inputPredmetTipNastave")).getSubmittedValue();
        restWSClient = new RestWSClient(Constants.TIP_NASTAVE_CONTROLLER);
        this.selectedTipNastave = restWSClient.getById_JSON(TipNastaveDTO.class, id);
    }

    public void selectedNastavnikListener(AjaxBehaviorEvent event) {
        String id = (String) ((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent(":predmetForm:tabview:inputPredmetNastavnik")).getSubmittedValue();
        restWSClient = new RestWSClient(Constants.NASTAVNIK_CONTROLLER);
        this.selectedNastavnik = restWSClient.getById_JSON(NastavnikDTO.class, id);
    }

    public void selectedUdzbenikListener(AjaxBehaviorEvent event) {
        String id = (String) ((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent(":predmetForm:tabview:inputPredmetUdzbenik")).getSubmittedValue();
        restWSClient = new RestWSClient(Constants.UDZBENIK_CONTROLLER);
        this.selectedUdzbenik = restWSClient.getById_JSON(UdzbenikDTO.class, id);
    }

    public void selectedVrstaINivoStudijaListener(AjaxBehaviorEvent event) {
        String id = (String) ((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent(":predmetForm:inputPredmetVrstaINivoStudija")).getSubmittedValue();
        restWSClient = new RestWSClient(Constants.VRSTA_I_NIVO_STUDIJA_CONTROLLER);
        this.selectedVrstaINivoStudija = restWSClient.getById_JSON(VrstaINivoStudijaDTO.class, id);
        ((UIOutput) FacesContext.getCurrentInstance().getViewRoot().findComponent(":predmetForm:vinsValue")).setValue(selectedVrstaINivoStudija.getNaziv());
    }

    //</editor-fold>
    public void onAddNewNastavnikNaPredmetu() {

        if (selectedNastavnik == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Niste izabrali nastavnika", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }

        if (selectedTipNastave == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Niste izabrali tip nastave",null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }

        NastavnikNaPredmetuDTO nnpdto = new NastavnikNaPredmetuDTO();
        nnpdto.setNastavnikDTO(selectedNastavnik);
        nnpdto.setTipNastaveDTO(selectedTipNastave);
        nnpdto.setNastavnikId(selectedNastavnik.getNastavnikId());
        nnpdto.setTipNastaveId(selectedTipNastave.getTipnastaveId());
        nnpdto.setPredmetId(predmet.getPredmetId());

        for (NastavnikNaPredmetuDTO nastavnikNaPredmetuDTO : predmet.getNastavnici()) {
            if (nastavnikNaPredmetuDTO.getNastavnikDTO().getNastavnikId() == nnpdto.getNastavnikDTO().getNastavnikId()
                    && nastavnikNaPredmetuDTO.getTipNastaveDTO().getTipnastaveId() == nnpdto.getTipNastaveDTO().getTipnastaveId()) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Taj nastavnik i taj tip nastave vec postoje",null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return;
            }
        }

        predmet.getNastavnici().add(nnpdto);

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, nnpdto.getNastavnikDTO().getIme() + " " + nnpdto.getNastavnikDTO().getPrezime() + " drzi " + nnpdto.getTipNastaveDTO().getNaziv(),null);
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void onDeleteNastavnikNaPredmetu(NastavnikNaPredmetuDTO nnpdto) {

        predmet.getNastavnici().remove(nnpdto);

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, nnpdto.getNastavnikDTO().getIme() + " " + nnpdto.getNastavnikDTO().getPrezime() + " vise ne drzi " + nnpdto.getTipNastaveDTO().getNaziv(),null);
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void onAddNewUdzbenikNaPredmetu() {
        if (selectedUdzbenik == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Niste izabrali udzbenik", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }

        UdzbenikNaPredmetuDTO unpdto = new UdzbenikNaPredmetuDTO();
        unpdto.setUdzbenikDTO(selectedUdzbenik);
        unpdto.setUdzbenikId(selectedUdzbenik.getUdzbenikId());
        unpdto.setPredmetId(predmet.getPredmetId());

        for (UdzbenikNaPredmetuDTO udzbenikNaPredmetuDTO : predmet.getUdzbenici()) {
            if (udzbenikNaPredmetuDTO.getUdzbenikDTO().getUdzbenikId() == unpdto.getUdzbenikDTO().getUdzbenikId()) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, unpdto.getUdzbenikDTO().getNaziv() + " vec postoji na predmetu  " + predmet.getNaziv(),null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return;
            }
        }

        predmet.getUdzbenici().add(unpdto);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, unpdto.getUdzbenikDTO().getNaziv() + " dodat na predmet : " + predmet.getNaziv(),null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onDeleteUdzbenikNaPredmetu(UdzbenikNaPredmetuDTO udzbenikDTO) {
        udzbenikDTO.setPredmetId(predmet.getPredmetId());

        predmet.getUdzbenici().remove(udzbenikDTO);

        

        FacesMessage msg = new FacesMessage(udzbenikDTO.getUdzbenikDTO().getNaziv() + " izbrisan sa predmeta : " + udzbenikDTO.getUdzbenikDTO().getNaziv());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String onUpdate() {
        restWSClient = new RestWSClient(Constants.PREDMET_CONTROLLER);

        predmet.setVrstaINivoStudija(selectedVrstaINivoStudija);

        Response response = restWSClient.update_JSON(predmet, String.valueOf(predmet.getPredmetId()));

        if (response.getStatusInfo() == Response.Status.BAD_REQUEST) {
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Greska (HTTP 400) ", "Uzrok: "+response.getEntity().toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (response.getStatusInfo() == Response.Status.fromStatusCode(500)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Greska na serveru(HTTP 500)" ,"Dogodila se greska u sistemu. Sistem nije u stanju da azurira predmet!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (response.getStatusInfo() == Response.Status.OK) {
//            FacesMessage msg = new FacesMessage("Sistem je uspesno zapamtio  predmet");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "success_update_predmet";
        }
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Fatal Error!","Dogodila se greska prilikom azuriranja predmeta!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "failure";
    }

    public String onDelete() {
        restWSClient = new RestWSClient(Constants.PREDMET_CONTROLLER);

        Response response = restWSClient.delete(String.valueOf(predmet.getPredmetId()));

        if (response.getStatusInfo() == Response.Status.BAD_REQUEST) {
             FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Greska (HTTP 400) ", "Uzrok: "+response.getEntity().toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (response.getStatusInfo() == Response.Status.fromStatusCode(500)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Greska na serveru(HTTP 500)" ,"Dogodila se greska u sistemu. Sistem nije u stanju da obrise predmet!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (response.getStatusInfo() == Response.Status.OK) {
            return "success_delete_predmet";
        }
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Fatal Error!","Dogodila se greska prilikom brisanja predmeta!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "failure";
    }

}
