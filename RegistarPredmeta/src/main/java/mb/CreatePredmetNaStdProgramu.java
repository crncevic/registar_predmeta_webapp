/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import constants.Constants;
import dto.PredmetDTO;
import dto.PredmetNaStudijskomProgramuDTO;
import dto.StatusDTO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import ws.client.RestWSClient;

/**
 *
 * @author Petar
 */
@ManagedBean
@Named(value = "predmetNaStdProgramuCreate")
@ViewScoped
public class CreatePredmetNaStdProgramu implements Serializable {

    private RestWSClient restWSClient;
    private List<PredmetDTO> predmeti;
    private List<StatusDTO> statusi;
    private ObjectMapper mapper;
    
    private PredmetNaStudijskomProgramuDTO newPredmetNaStdProgramu;

    public CreatePredmetNaStdProgramu() {

    }

    @PostConstruct
    private void init() {
        mapper = new ObjectMapper();
        newPredmetNaStdProgramu = new PredmetNaStudijskomProgramuDTO();
        
        restWSClient = new RestWSClient(Constants.PREDMET_CONTROLLER);
        predmeti = mapper.convertValue(restWSClient.getAll_JSON(List.class), new TypeReference<List<PredmetDTO>>(){});
        
        restWSClient = new RestWSClient(Constants.STATUS_PREDMETA_CONTROLLER);
        statusi = mapper.convertValue(restWSClient.getAll_JSON(List.class),new TypeReference<List<StatusDTO>>(){});
    }

    public List<PredmetDTO> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(List<PredmetDTO> predmeti) {
        this.predmeti = predmeti;
    }

    public PredmetNaStudijskomProgramuDTO getNewPredmetNaStdProgramu() {
        return newPredmetNaStdProgramu;
    }

    public void setNewPredmetNaStdProgramu(PredmetNaStudijskomProgramuDTO newPredmetNaStdProgramu) {
        this.newPredmetNaStdProgramu = newPredmetNaStdProgramu;
    }

    public List<StatusDTO> getStatusi() {
        return statusi;
    }

    public void setStatusi(List<StatusDTO> statusi) {
        this.statusi = statusi;
    }
    
    public void onCreate(){
        FacesMessage msg = new FacesMessage("Osoba azurirana");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
    
}
