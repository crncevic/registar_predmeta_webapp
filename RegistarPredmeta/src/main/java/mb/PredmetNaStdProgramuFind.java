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
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
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

}
