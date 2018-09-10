/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.Constants;
import dto.PredmetDTO;
import dto.PredmetNaStudijskomProgramuDTO;
import dto.StatusDTO;
import dto.StudijskiProgramDTO;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.ws.rs.core.Response;
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
    private StudijskiProgramDTO stdProgram;
    private ObjectMapper mapper;

    private PredmetNaStudijskomProgramuDTO newPredmetNaStdProgramu;

    public CreatePredmetNaStdProgramu() {

    }

    @PostConstruct
    private void init() {
        mapper = new ObjectMapper();
        newPredmetNaStdProgramu = new PredmetNaStudijskomProgramuDTO();
        newPredmetNaStdProgramu.setStatusDTO(new StatusDTO());

        restWSClient = new RestWSClient(Constants.PREDMET_CONTROLLER);
        predmeti = mapper.convertValue(restWSClient.getAll_JSON(List.class), new TypeReference<List<PredmetDTO>>() {
        });

        restWSClient = new RestWSClient(Constants.STATUS_PREDMETA_CONTROLLER);
        statusi = mapper.convertValue(restWSClient.getAll_JSON(List.class), new TypeReference<List<StatusDTO>>() {
        });

        restWSClient = new RestWSClient(Constants.STUDIJSKI_PROGRAM_CONTROLLER);
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int stdProgramId = Integer.parseInt(params.get(Constants.STUDIJSKI_PROGRAM_ID));
        stdProgram = restWSClient.getById_JSON(StudijskiProgramDTO.class, String.valueOf(stdProgramId));
        newPredmetNaStdProgramu.setStudijskiProgramDTO(stdProgram);
        newPredmetNaStdProgramu.setStudijskiProgramId(stdProgramId);

    }

    //<editor-fold defaultstate="collapsed" desc="getters and setters">
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

    public StudijskiProgramDTO getStdProgram() {
        return stdProgram;
    }

    public void setStdProgram(StudijskiProgramDTO stdProgram) {
        this.stdProgram = stdProgram;
    }

    //</editor-fold>
    public String onCreate() {

        restWSClient = new RestWSClient(Constants.PREDMET_NA_STD_PROGRAMU_CONTROLLER);
        Response response = restWSClient.create_JSON(newPredmetNaStdProgramu);

        if (response.getStatusInfo() == Response.Status.BAD_REQUEST) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Greska (HTTP 400) ", "Uzrok: "+response.getEntity().toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (response.getStatusInfo() == Response.Status.fromStatusCode(500)) {
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Greska na serveru(HTTP 500)" ,"Dogodila se greska u sistemu. Sistem nije u stanju da zapamti predmet na stujskom programu!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (response.getStatusInfo() == Response.Status.OK) {
//            FacesMessage msg = new FacesMessage("Predmet na studijskom programu je uspesno sacuvan!");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "success_create_pnsp";
        }
        return "failure";
    }

}
