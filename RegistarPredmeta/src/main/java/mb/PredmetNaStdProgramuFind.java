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
@Named(value = "predmetNaStdProgramuFind")
@ViewScoped
public class PredmetNaStdProgramuFind implements Serializable {

    private RestWSClient restWSClient;
    private PredmetNaStudijskomProgramuDTO predmetNaStdProgramu;
    private List<PredmetNaStudijskomProgramuDTO> predmetiNaStdProgramu;
    private List<StatusDTO> statusi;
    private List<PredmetDTO> predmeti;
    private StudijskiProgramDTO stdProgram;
    private ObjectMapper mapper;

    public PredmetNaStdProgramuFind() {

    }

    @PostConstruct
    private void init() {
        mapper = new ObjectMapper();
        restWSClient = new RestWSClient(Constants.STATUS_PREDMETA_CONTROLLER);
        statusi = mapper.convertValue(restWSClient.getAll_JSON(List.class), new TypeReference<List<StatusDTO>>() {
        });

        restWSClient = new RestWSClient(Constants.PREDMET_CONTROLLER);
        predmeti = mapper.convertValue(restWSClient.getAll_JSON(List.class), new TypeReference<List<PredmetDTO>>() {
        });

        restWSClient = new RestWSClient(Constants.PREDMET_NA_STD_PROGRAMU_CONTROLLER);
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int predmetId = Integer.parseInt(params.get(Constants.PREDMET_ID));
        int stdProgramId = Integer.parseInt(params.get(Constants.STUDIJSKI_PROGRAM_ID));
        predmetiNaStdProgramu = mapper.convertValue(restWSClient.getById_JSON(List.class, String.valueOf(stdProgramId)), new TypeReference<List<PredmetNaStudijskomProgramuDTO>>() {
        });

        for (PredmetNaStudijskomProgramuDTO predmetNaStudijskomProgramuDTO : predmetiNaStdProgramu) {
            if (predmetNaStudijskomProgramuDTO.getPredmetId() == predmetId
                    && predmetNaStudijskomProgramuDTO.getStudijskiProgramId() == stdProgramId) {
                predmetNaStdProgramu = predmetNaStudijskomProgramuDTO;
            }
        }

        restWSClient = new RestWSClient(Constants.STUDIJSKI_PROGRAM_CONTROLLER);
        stdProgram = restWSClient.getById_JSON(StudijskiProgramDTO.class, String.valueOf(predmetNaStdProgramu.getStudijskiProgramId()));

    }

    public PredmetNaStudijskomProgramuDTO getPredmetNaStdProgramu() {
        return predmetNaStdProgramu;
    }

    public void setPredmetNaStdProgramu(PredmetNaStudijskomProgramuDTO predmetNaStdProgramu) {
        this.predmetNaStdProgramu = predmetNaStdProgramu;
    }

    public List<StatusDTO> getStatusi() {
        return statusi;
    }

    public void setStatusi(List<StatusDTO> statusi) {
        this.statusi = statusi;
    }

    public List<PredmetDTO> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(List<PredmetDTO> predmeti) {
        this.predmeti = predmeti;
    }

    public StudijskiProgramDTO getStdProgram() {
        return stdProgram;
    }

    public void setStdProgram(StudijskiProgramDTO stdProgram) {
        this.stdProgram = stdProgram;
    }

    public String onUpdate() {
        restWSClient = new RestWSClient(Constants.PREDMET_NA_STD_PROGRAMU_CONTROLLER);

        Response response = restWSClient.update_JSON(predmetNaStdProgramu, predmetNaStdProgramu.getStudijskiProgramId() + "/" + predmetNaStdProgramu.getPredmetId());

        if (response.getStatusInfo() == Response.Status.BAD_REQUEST) {
             FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Greska (HTTP 400) ", "Uzrok: "+response.getEntity().toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (response.getStatusInfo() == Response.Status.fromStatusCode(500)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Greska na serveru(HTTP 500)" ,"Dogodila se greska u sistemu. Sistem nije u stanju da azurira predmet na studijskom programu!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (response.getStatusInfo() == Response.Status.OK) {
            return "success_delete_pnsp";
        }
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Fatal Error!","Dogodila se greska prilikom azuriranja predmeta na studijskom programu!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "failure";
    }

    public String onDelete() {
        restWSClient = new RestWSClient(Constants.PREDMET_NA_STD_PROGRAMU_CONTROLLER);
        Response response = restWSClient.delete(predmetNaStdProgramu.getStudijskiProgramId() + "/" + predmetNaStdProgramu.getPredmetId());

         if (response.getStatusInfo() == Response.Status.BAD_REQUEST) {
             FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Greska (HTTP 400) ", "Uzrok: "+response.getEntity().toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (response.getStatusInfo() == Response.Status.fromStatusCode(500)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Greska na serveru(HTTP 500)" ,"Dogodila se greska u sistemu. Sistem nije u stanju da obrise predmet na studijskom programu!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (response.getStatusInfo() == Response.Status.OK) {
            return "success_delete_predmet";
        }
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Fatal Error!","Dogodila se greska prilikom brisanja predmeta na studijskom programu!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "failure";
    }
}
