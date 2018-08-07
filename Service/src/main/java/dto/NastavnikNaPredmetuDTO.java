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
public class NastavnikNaPredmetuDTO implements Serializable{

    private int nastavnikId;
    private int predmetId;
    private int tipNastaveId;

    private NastavnikDTO nastavnikDTO;

    private TipNastaveDTO tipNastaveDTO;

    public NastavnikNaPredmetuDTO() {
    }

    public NastavnikNaPredmetuDTO(int nastavnikId, int predmetId, int tipNastaveId) {
        this.nastavnikId = nastavnikId;
        this.predmetId = predmetId;
        this.tipNastaveId = tipNastaveId;
    }

    public int getNastavnikId() {
        return nastavnikId;
    }

    public void setNastavnikId(int nastavnikId) {
        this.nastavnikId = nastavnikId;
    }

    public int getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(int predmetId) {
        this.predmetId = predmetId;
    }

    public int getTipNastaveId() {
        return tipNastaveId;
    }

    public void setTipNastaveId(int tipNastaveId) {
        this.tipNastaveId = tipNastaveId;
    }

    public NastavnikDTO getNastavnikDTO() {
        return nastavnikDTO;
    }

    public void setNastavnikDTO(NastavnikDTO nastavnikDTO) {
        this.nastavnikDTO = nastavnikDTO;
    }

    public TipNastaveDTO getTipNastaveDTO() {
        return tipNastaveDTO;
    }

    public void setTipNastaveDTO(TipNastaveDTO tipNastaveDTO) {
        this.tipNastaveDTO = tipNastaveDTO;
    }
    

}
