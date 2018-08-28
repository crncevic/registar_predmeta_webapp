/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import constants.Constants;
import dto.StudijskiProgramDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.view.ViewScoped;
import ws.client.RestWSClient;

/**
 *
 * @author Petar
 */
@ManagedBean
@Named(value = "allStdProgram")
@ViewScoped
public class AllStdProgram implements Serializable{

    /**
     * Creates a new instance of AllStdProgram
     */
    
    private RestWSClient restWSClient;
    private List<StudijskiProgramDTO> stdProgrami;
     
    public AllStdProgram() {
    }

    public List<StudijskiProgramDTO> getStdProgrami() {
        restWSClient = new RestWSClient(Constants.STUDIJSKI_PROGRAM_CONTROLLER);
        stdProgrami = restWSClient.getAll_JSON(ArrayList.class);
        return stdProgrami;
    }

    public void setStdProgrami(List<StudijskiProgramDTO> stdProgrami) {
        this.stdProgrami = stdProgrami;
    }
    
    
    
    
}
