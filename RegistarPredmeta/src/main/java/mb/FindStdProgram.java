/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import constants.Constants;
import dto.PredmetDTO;
import dto.PredmetNaStudijskomProgramuDTO;
import dto.StudijskiProgramDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.ManagedBean;
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
@Named(value = "findStdProgram")
@ViewScoped
public class FindStdProgram implements Serializable {

    /**
     * Creates a new instance of FindStdProgram
     */
    private StudijskiProgramDTO stdProgram;
    private List<PredmetNaStudijskomProgramuDTO> predmetiNaStdProgramu;
    private RestWSClient restWSClient;

    public FindStdProgram() {
        restWSClient = new RestWSClient(Constants.STUDIJSKI_PROGRAM_CONTROLLER);
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int stdProgramId = Integer.parseInt(params.get(Constants.STUDIJSKI_PROGRAM_ID));
        stdProgram = restWSClient.getById_JSON(StudijskiProgramDTO.class, String.valueOf(stdProgramId));
    }

    public StudijskiProgramDTO getStdProgram() {
        return stdProgram;
    }

    public void setStdProgram(StudijskiProgramDTO stdProgram) {
        this.stdProgram = stdProgram;
    }

    public List<PredmetNaStudijskomProgramuDTO> getPredmetiNaStdProgramu() {
        restWSClient = new RestWSClient(Constants.PREDMET_NA_STD_PROGRAMU_CONTROLLER);
        predmetiNaStdProgramu = restWSClient.getById_JSON(List.class,String.valueOf(stdProgram.getStudijskiProgramId()));
        return predmetiNaStdProgramu;
    }

    public void setPredmetiNaStdProgramu(List<PredmetNaStudijskomProgramuDTO> predmetiNaStdProgramu) {
        this.predmetiNaStdProgramu = predmetiNaStdProgramu;
    }

    

    

   
}
