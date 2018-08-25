/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dto.PredmetDTO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import ws.client.RestWSClient;

/**
 *
 * @author Petar
 */
@ManagedBean
@Named(value = "allPredmet")
@ViewScoped
public class AllPredmet implements Serializable{

    private List<PredmetDTO> predmeti;
    private RestWSClient restWSClient;
    
    public AllPredmet() {
        restWSClient = new RestWSClient("predmet");
        predmeti = restWSClient.getAll_JSON(List.class);
    }

    public List<PredmetDTO> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(List<PredmetDTO> predmeti) {
        this.predmeti = predmeti;
    }
    
    public String getStudijskiProgramForPredmet(int predmetId){
        
      PredmetDTO predmetDTO = restWSClient.getById_JSON(PredmetDTO.class,String.valueOf(predmetId));
        
         String studijskiProgrami = "";
         
        for(int i = 0; i< predmetDTO.getPredmetiNaStudijskimProgramima().size(); i++){
            if( i == predmetDTO.getPredmetiNaStudijskimProgramima().size() - 1){
                studijskiProgrami += predmetDTO.getPredmetiNaStudijskimProgramima()
                        .get(i).getStudijskiProgramDTO().getNaziv();
            }else{
                  studijskiProgrami += predmetDTO.getPredmetiNaStudijskimProgramima()
                        .get(i).getStudijskiProgramDTO().getNaziv()+ " , ";
            }
        }
        
        return studijskiProgrami;
    }
    
    
    
}
