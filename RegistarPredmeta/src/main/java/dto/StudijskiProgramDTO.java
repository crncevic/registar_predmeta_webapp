/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Petar
 */
public class StudijskiProgramDTO implements Serializable{

    @NotNull
    private Integer studijskiProgramId;

    @NotNull
    @Size(min = 1, max = 150)
    private String naziv;

    public Integer getStudijskiProgramId() {
        return studijskiProgramId;
    }

    public void setStudijskiProgramId(Integer studijskiProgramId) {
        this.studijskiProgramId = studijskiProgramId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

}
