/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.validation.constraints.Size;

/**
 *
 * @author Petar
 */
public class UlogaUdzbenikDTO implements Serializable{

    private Integer ulogaId;
    @Size(max = 100)
    @Column(name = "naziv")
    private String naziv;

    public Integer getUlogaId() {
        return ulogaId;
    }

    public void setUlogaId(Integer ulogaId) {
        this.ulogaId = ulogaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

}
