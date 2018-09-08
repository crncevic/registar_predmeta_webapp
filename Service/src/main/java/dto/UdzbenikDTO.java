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
public class UdzbenikDTO implements Serializable{
    
    
    
   
    private Integer udzbenikId;
   
    @NotNull
    @Size(min = 1, max = 100)
    private String naziv;
    
    private Integer godinaIzdanja;
    @Size(max = 100)
    
    private String izdavac;
    @Size(max = 200)
    
    private String stampa;
    
    private Integer rbrIzdanja;
    
    private Integer tiraz;
   
    private String isbn;
  
   
   
    private List<OsobaUVeziSaUdzbenikomDTO> osobaUVeziSaUdzbenikomList;

    public Integer getUdzbenikId() {
        return udzbenikId;
    }

    public void setUdzbenikId(Integer udzbenikId) {
        this.udzbenikId = udzbenikId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getGodinaIzdanja() {
        return godinaIzdanja;
    }

    public void setGodinaIzdanja(Integer godinaIzdanja) {
        this.godinaIzdanja = godinaIzdanja;
    }

    public String getIzdavac() {
        return izdavac;
    }

    public void setIzdavac(String izdavac) {
        this.izdavac = izdavac;
    }

    public String getStampa() {
        return stampa;
    }

    public void setStampa(String stampa) {
        this.stampa = stampa;
    }

    public Integer getRbrIzdanja() {
        return rbrIzdanja;
    }

    public void setRbrIzdanja(Integer rbrIzdanja) {
        this.rbrIzdanja = rbrIzdanja;
    }

    public Integer getTiraz() {
        return tiraz;
    }

    public void setTiraz(Integer tiraz) {
        this.tiraz = tiraz;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<OsobaUVeziSaUdzbenikomDTO> getOsobaUVeziSaUdzbenikomList() {
        return osobaUVeziSaUdzbenikomList;
    }

    public void setOsobaUVeziSaUdzbenikomList(List<OsobaUVeziSaUdzbenikomDTO> osobaUVeziSaUdzbenikomList) {
        this.osobaUVeziSaUdzbenikomList = osobaUVeziSaUdzbenikomList;
    }
    
    
    
}
