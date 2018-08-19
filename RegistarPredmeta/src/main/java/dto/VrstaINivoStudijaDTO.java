/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author Petar
 */
public class VrstaINivoStudijaDTO implements Serializable{

    private int vrstaId;
    private String naziv;

    public VrstaINivoStudijaDTO() {
    }
    
    

    public VrstaINivoStudijaDTO(int vrstaId, String naziv) {
        this.vrstaId = vrstaId;
        this.naziv = naziv;
    }

    public int getVrstaId() {
        return vrstaId;
    }

    public void setVrstaId(int vrstaId) {
        this.vrstaId = vrstaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
   
    
}
