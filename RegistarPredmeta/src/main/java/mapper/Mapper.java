/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import constants.Constants;
import dto.VrstaINivoStudijaDTO;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Petar
 */
public class Mapper {
    
    
    public static List<VrstaINivoStudijaDTO> fromJsonToListVrstaINivoStudijaDTO(ArrayList<LinkedHashMap> list){
        try {
            List<VrstaINivoStudijaDTO> resultList = new ArrayList<>();
            for (LinkedHashMap linkedHashMap : list) {
                 VrstaINivoStudijaDTO vinsdto = new VrstaINivoStudijaDTO();
                 vinsdto.setVrstaId(Integer.parseInt(linkedHashMap.get(Constants.VRSTA_ID).toString()));
                 vinsdto.setNaziv(linkedHashMap.get(Constants.VRSTA_NAZIV).toString());
                 resultList.add(vinsdto);
            }
            
          
      
           return resultList;
        } catch (Exception e) {
        return  null;
        }
    }
    
}
