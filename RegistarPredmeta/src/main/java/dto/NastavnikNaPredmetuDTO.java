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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.nastavnikId;
        hash = 79 * hash + this.predmetId;
        hash = 79 * hash + this.tipNastaveId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NastavnikNaPredmetuDTO other = (NastavnikNaPredmetuDTO) obj;
        if (this.nastavnikId != other.nastavnikId) {
            return false;
        }
        if (this.predmetId != other.predmetId) {
            return false;
        }
        if (this.tipNastaveId != other.tipNastaveId) {
            return false;
        }
        return true;
    }
    

}
