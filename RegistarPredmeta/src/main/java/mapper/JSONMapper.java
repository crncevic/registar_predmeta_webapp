/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import dto.UdzbenikDTO;
import dto.UlogaUdzbenikDTO;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Petar
 */
public class JSONMapper {

    public static UdzbenikDTO jsonToUdzbenikDTO(LinkedHashMap<String, String> jsonUdzbenik) throws Exception {
        try {
            UdzbenikDTO udzbenikDTO = new UdzbenikDTO();
            udzbenikDTO.setUdzbenikId(Integer.parseInt(jsonUdzbenik.get("udzbenikId")));
            udzbenikDTO.setNaziv(jsonUdzbenik.get("naziv"));
            udzbenikDTO.setGodinaIzdanja(Integer.parseInt(jsonUdzbenik.get("godinaIzdanja")));
            udzbenikDTO.setRbrIzdanja(Integer.parseInt(jsonUdzbenik.get("rbrIzdanja")));
            udzbenikDTO.setIzdavac(jsonUdzbenik.get("izdavac"));
            udzbenikDTO.setStampa(jsonUdzbenik.get("stampa"));
            udzbenikDTO.setTiraz(Integer.parseInt(jsonUdzbenik.get("tiraz")));
            udzbenikDTO.setIsbn(Integer.parseInt(jsonUdzbenik.get("isbn")));

            return udzbenikDTO;

        } catch (Exception e) {
            throw new Exception("Greska u mapiranju json -> udzbenikDTO. {" + e.getMessage() + "}");
        }
    }

//   public static UlogaUdzbenikDTO jsonToUlogaUdzbenikDTO()
}
