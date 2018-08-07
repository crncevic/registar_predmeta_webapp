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
public class TematskaCelinaDTO implements Serializable{

    private Integer tematskacelinaId;

    @NotNull
    @Size(min = 1, max = 100)
    private String naziv;
    
    @Size(max = 500)
    private String opis;

    private TipNastaveDTO tipNastave;

    private List<TematskaCelinaDTO> tematskaCelinaList;

    private TematskaCelinaDTO nadredjenatematskacelina;

    public Integer getTematskacelinaId() {
        return tematskacelinaId;
    }

    public void setTematskacelinaId(Integer tematskacelinaId) {
        this.tematskacelinaId = tematskacelinaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public TipNastaveDTO getTipNastave() {
        return tipNastave;
    }

    public void setTipNastave(TipNastaveDTO tipNastave) {
        this.tipNastave = tipNastave;
    }

    public List<TematskaCelinaDTO> getTematskaCelinaList() {
        return tematskaCelinaList;
    }

    public void setTematskaCelinaList(List<TematskaCelinaDTO> tematskaCelinaList) {
        this.tematskaCelinaList = tematskaCelinaList;
    }

    public TematskaCelinaDTO getNadredjenatematskacelina() {
        return nadredjenatematskacelina;
    }

    public void setNadredjenatematskacelina(TematskaCelinaDTO nadredjenatematskacelina) {
        this.nadredjenatematskacelina = nadredjenatematskacelina;
    }

}
