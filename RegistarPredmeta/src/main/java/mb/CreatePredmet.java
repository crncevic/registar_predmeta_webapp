/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import constants.Constants;
import dto.NastavnikDTO;
import dto.NastavnikNaPredmetuDTO;
import dto.PredmetDTO;
import dto.TipNastaveDTO;
import dto.UdzbenikDTO;
import dto.UdzbenikNaPredmetuDTO;
import dto.VrstaINivoStudijaDTO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import ws.client.RestWSClient;

/**
 *
 * @author Petar
 */
@ManagedBean
@Named(value = "createPredmet")
@ViewScoped
public class CreatePredmet implements Serializable{

    private RestWSClient restWSClient;
    private PredmetDTO predmet;
    private List<UdzbenikDTO> udzbenici;
    private List<NastavnikDTO> nastavnici;
    private List<VrstaINivoStudijaDTO> vrsteINivoiStudija;
    private List<TipNastaveDTO> tipoviNastave;
    
    private UdzbenikDTO selectedUdzbenik;
    private NastavnikDTO selectedNastavnik;
    private TipNastaveDTO selectedTipNastave;
    private VrstaINivoStudijaDTO selectedVrstaINivoStudija;
    
    
    public CreatePredmet() {
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="Getters and setters">
    


    public PredmetDTO getPredmet() {
        return predmet;
    }

    public void setPredmet(PredmetDTO predmet) {
        this.predmet = predmet;
    }

    public List<UdzbenikDTO> getUdzbenici() {
        restWSClient  = new RestWSClient(Constants.UDZBENIK_CONTROLLER);
        udzbenici = restWSClient.getAll_JSON(List.class);
        return udzbenici;
    }

    public void setUdzbenici(List<UdzbenikDTO> udzbenici) {
        this.udzbenici = udzbenici;
    }

    public List<NastavnikDTO> getNastavnici() {
        restWSClient = new RestWSClient(Constants.NASTAVNIK_CONTROLLER);
        nastavnici = restWSClient.getAll_JSON(List.class);
        return nastavnici;
    }

    public void setNastavnici(List<NastavnikDTO> nastavnici) {
        this.nastavnici = nastavnici;
    }

    public List<VrstaINivoStudijaDTO> getVrsteINivoiStudija() {
        restWSClient = new RestWSClient(Constants.VRSTA_I_NIVO_STUDIJA_CONTROLLER);
        vrsteINivoiStudija = restWSClient.getAll_JSON(List.class);
        return vrsteINivoiStudija;
    }

    public void setVrsteINivoiStudija(List<VrstaINivoStudijaDTO> vrsteINivoiStudija) {
        this.vrsteINivoiStudija = vrsteINivoiStudija;
    }

    public List<TipNastaveDTO> getTipoviNastave() {
        restWSClient = new RestWSClient(Constants.TIP_NASTAVE_CONTROLLER);
        tipoviNastave = restWSClient.getAll_JSON(List.class);
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
        String id = (String) ((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent(":predmetForm:inputPredmetTipNastave")).getSubmittedValue();
        restWSClient = new RestWSClient(Constants.TIP_NASTAVE_CONTROLLER);
        this.selectedTipNastave = restWSClient.getById_JSON(TipNastaveDTO.class, id);
    }

    public void selectedNastavnikListener(AjaxBehaviorEvent event) {
        String id = (String) ((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent(":predmetForm:inputPredmetNastavnik")).getSubmittedValue();
        restWSClient = new RestWSClient(Constants.NASTAVNIK_CONTROLLER);
        this.selectedNastavnik = restWSClient.getById_JSON(NastavnikDTO.class, id);
    }

    public void selectedUdzbenikListener(AjaxBehaviorEvent event) {
        String id = (String) ((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent(":predmetForm:inputPredmetUdzbenik")).getSubmittedValue();
        restWSClient = new RestWSClient(Constants.UDZBENIK_CONTROLLER);
        this.selectedUdzbenik = restWSClient.getById_JSON(UdzbenikDTO.class, id);
    }

    public void selectedVrstaINivoStudijaListener(AjaxBehaviorEvent event) {
        String id = (String) ((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent(":predmetForm:inputPredmetVrstaINivoStudija")).getSubmittedValue();
        restWSClient = new RestWSClient(Constants.VRSTA_I_NIVO_STUDIJA_CONTROLLER);
        this.selectedVrstaINivoStudija = restWSClient.getById_JSON(VrstaINivoStudijaDTO.class, id);
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
        nnpdto.setPredmetId(predmet.getPredmetId());

        for (NastavnikNaPredmetuDTO nastavnikNaPredmetuDTO : predmet.getNastavnici()) {
            if (nastavnikNaPredmetuDTO.getNastavnikDTO().getNastavnikId() == nnpdto.getNastavnikDTO().getNastavnikId()
                    && nastavnikNaPredmetuDTO.getTipNastaveDTO().getTipnastaveId() == nnpdto.getTipNastaveDTO().getTipnastaveId()) {
                FacesMessage msg = new FacesMessage("Taj nastavnik i taj tip nastave vec postoje");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return;
            }
        }

        predmet.getNastavnici().add(nnpdto);

        FacesMessage msg = new FacesMessage(nnpdto.getNastavnikDTO().getIme() + " " + nnpdto.getNastavnikDTO().getPrezime() + " drzi " + nnpdto.getTipNastaveDTO().getNaziv());
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void onDeleteNastavnikNaPredmetu(NastavnikNaPredmetuDTO nnpdto) {

        predmet.getNastavnici().remove(nnpdto);

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
        unpdto.setPredmetId(predmet.getPredmetId());

        for (UdzbenikNaPredmetuDTO udzbenikNaPredmetuDTO : predmet.getUdzbenici()) {
            if (udzbenikNaPredmetuDTO.getUdzbenikDTO().getUdzbenikId() == unpdto.getUdzbenikDTO().getUdzbenikId()) {
                FacesMessage msg = new FacesMessage(unpdto.getUdzbenikDTO().getNaziv() + " vec postoji na predmetu  " + predmet.getNaziv());
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return;
            }
        }

        predmet.getUdzbenici().add(unpdto);
        FacesMessage msg = new FacesMessage(unpdto.getUdzbenikDTO().getNaziv() + " dodat na predmet : " + predmet.getNaziv());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onDeleteUdzbenikNaPredmetu(UdzbenikNaPredmetuDTO udzbenikDTO) {
        udzbenikDTO.setPredmetId(predmet.getPredmetId());

        predmet.getUdzbenici().remove(udzbenikDTO);
        FacesMessage msg = new FacesMessage(udzbenikDTO.getUdzbenikDTO().getNaziv() + " izbrisan sa predmeta : " + udzbenikDTO.getUdzbenikDTO().getNaziv());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //</editor-fold>
    
    public void onCreate(){
        
    }
    
}
