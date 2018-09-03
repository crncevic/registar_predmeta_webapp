/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.Constants;
import dto.StudijskiProgramDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
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
    private ObjectMapper mapper;
     
    public AllStdProgram() {
    }
    
    @PostConstruct
    private void init(){
        mapper = new ObjectMapper();
        restWSClient = new RestWSClient(Constants.STUDIJSKI_PROGRAM_CONTROLLER);
        stdProgrami = mapper.convertValue(restWSClient.getAll_JSON(ArrayList.class), new TypeReference<List<StudijskiProgramDTO>>() {});
    }

    public List<StudijskiProgramDTO> getStdProgrami() {
       
        return stdProgrami;
    }

    public void setStdProgrami(List<StudijskiProgramDTO> stdProgrami) {
        this.stdProgrami = stdProgrami;
    }
    
    
    
    
}
