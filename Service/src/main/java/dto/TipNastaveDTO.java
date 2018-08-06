/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Petar
 */
public class TipNastaveDTO {

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

}
