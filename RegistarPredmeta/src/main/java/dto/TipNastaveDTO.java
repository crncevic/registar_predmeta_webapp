/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Petar
 */
public class TipNastaveDTO implements Serializable{

    private Integer tipnastaveId;

    @NotNull
    @Size(min = 1, max = 200)
    private String naziv;

    public Integer getTipnastaveId() {
        return tipnastaveId;
    }

    public void setTipnastaveId(Integer tipnastaveId) {
        this.tipnastaveId = tipnastaveId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.tipnastaveId);
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
        final TipNastaveDTO other = (TipNastaveDTO) obj;
        if (!Objects.equals(this.tipnastaveId, other.tipnastaveId)) {
            return false;
        }
        return true;
    }

}
