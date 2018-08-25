/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dto.NastavnikDTO;
import dto.NastavnikNaPredmetuDTO;
import dto.PredmetDTO;
import dto.TipNastaveDTO;
import dto.VrstaINivoStudijaDTO;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;
import javax.faces.component.UISelectItems;
import javax.faces.component.UISelectOne;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
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

    private NastavnikDTO selectedNastavnik;
    private TipNastaveDTO selectedTipNastave;

    public FindPredmet() {

        restWSClient = new RestWSClient("predmet");
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int predmetId = Integer.parseInt(params.get("predmetId"));
        predmet = restWSClient.getById_JSON(PredmetDTO.class, String.valueOf(predmetId));

    }

    public PredmetDTO getPredmet() {
        return predmet;
    }

    public void setPredmet(PredmetDTO predmet) {
        this.predmet = predmet;
    }

    public List<VrstaINivoStudijaDTO> getVrsteINivoiStudija() {
        restWSClient = new RestWSClient("vrsta-i-nivo-studija");
        vrsteINivoiStudija = restWSClient.getAll_JSON(List.class);
        return vrsteINivoiStudija;
    }

    public void setVrsteINivoiStudija(List<VrstaINivoStudijaDTO> vrsteINivoiStudija) {
        this.vrsteINivoiStudija = vrsteINivoiStudija;
    }

    public List<NastavnikDTO> getNastavnici() {
        restWSClient = new RestWSClient("nastavnik");
        nastavnici = restWSClient.getAll_JSON(List.class);

        return nastavnici;
    }

    public void setNastavnici(List<NastavnikDTO> nastavnici) {
        this.nastavnici = nastavnici;
    }

    public List<TipNastaveDTO> getTipoviNastave() {
        restWSClient = new RestWSClient("tip-nastave");
        tipoviNastave = restWSClient.getAll_JSON(List.class);
        return tipoviNastave;
    }

    public NastavnikDTO getSelectedNastavnik() {
        return selectedNastavnik;
    }

    public void setSelectedNastavnik(NastavnikDTO selectedNastavnik) {
        this.selectedNastavnik = selectedNastavnik;
    }

    public void selectedTipNastaveListener(AjaxBehaviorEvent event) {
        String id = (String) ((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent(":predmetForm:inputPredmetTipNastave")).getSubmittedValue();
        restWSClient = new RestWSClient("tip-nastave");
        this.selectedTipNastave = restWSClient.getById_JSON(TipNastaveDTO.class, id);
    }

    public void selectedNastavnikListener(AjaxBehaviorEvent event) {
        String id = (String) ((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent(":predmetForm:inputPredmetNastavnik")).getSubmittedValue();
        restWSClient = new RestWSClient("nastavnik");
        this.selectedNastavnik = restWSClient.getById_JSON(NastavnikDTO.class, id);
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

    }

    public void onDeleteNastavnikNaPredmetu(NastavnikNaPredmetuDTO nnpdto) {
        for (NastavnikNaPredmetuDTO nastavnikNaPredmetuDTO : predmet.getNastavnici()) {
            if (nastavnikNaPredmetuDTO.equals(nnpdto)) {
                predmet.getNastavnici().remove(nastavnikNaPredmetuDTO);
            }
        }

        FacesMessage msg = new FacesMessage("Nastavnik : " + nnpdto.getNastavnikDTO().getIme() + nnpdto.getNastavnikDTO().getPrezime()
                + " koji drzi " + nnpdto.getTipNastaveDTO().getNaziv() + " izbrisan");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
