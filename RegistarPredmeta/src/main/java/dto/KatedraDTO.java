/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Petar
 */
public class KatedraDTO implements Serializable{

    private Integer katedraId;

    @NotNull
    @Size(min = 1, max = 100)
    private String naziv;

    private String sef;

    private String zamenikSefa;

    private String sekretar;

    public Integer getKatedraId() {
        return katedraId;
    }

    public void setKatedraId(Integer katedraId) {
        this.katedraId = katedraId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSef() {
        return sef;
    }

    public void setSef(String sef) {
        this.sef = sef;
    }

    public String getZamenikSefa() {
        return zamenikSefa;
    }

    public void setZamenikSefa(String zamenikSefa) {
        this.zamenikSefa = zamenikSefa;
    }

    public String getSekretar() {
        return sekretar;
    }

    public void setSekretar(String sekretar) {
        this.sekretar = sekretar;
    }

}
