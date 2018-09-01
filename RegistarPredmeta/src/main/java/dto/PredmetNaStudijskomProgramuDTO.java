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
public class PredmetNaStudijskomProgramuDTO implements Serializable{

    private int predmetId;
    private int studijskiProgramId;
    private StudijskiProgramDTO studijskiProgramDTO;
    private int semestar;
    private int espb;
    private StatusDTO statusDTO;
    public String nazivPredmeta;

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    public int getSemestar() {
        return semestar;
    }

    public void setSemestar(int semestar) {
        this.semestar = semestar;
    }

    public int getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(int predmetId) {
        this.predmetId = predmetId;
    }

    public int getStudijskiProgramId() {
        return studijskiProgramId;
    }

    public void setStudijskiProgramId(int studijskiProgramId) {
        this.studijskiProgramId = studijskiProgramId;
    }

    public StudijskiProgramDTO getStudijskiProgramDTO() {
        return studijskiProgramDTO;
    }

    public void setStudijskiProgramDTO(StudijskiProgramDTO studijskiProgramDTO) {
        this.studijskiProgramDTO = studijskiProgramDTO;
    }

    public StatusDTO getStatusDTO() {
        return statusDTO;
    }

    public void setStatusDTO(StatusDTO statusDTO) {
        this.statusDTO = statusDTO;
    }

    public String getNazivPredmeta() {
        return nazivPredmeta;
    }

    public void setNazivPredmeta(String nazivPredmeta) {
        this.nazivPredmeta = nazivPredmeta;
    }

   

  
    
    

}
