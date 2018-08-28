/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Petar
 */
public class NastavnikDTO implements Serializable{
     private Integer nastavnikId;
    
    @NotNull
    @Size(min = 1, max = 100)
    private String ime;
    @NotNull
    @Size(min = 1, max = 100)
    private String prezime;
    @NotNull
    @Size(min = 1, max = 100)
    private String zvanje;
    @Size(max = 100)
    private String telefon;
    @Size(max = 50)
    private String kabinet;
    @Size(max = 100)
    private String ePosta;
    
    private KatedraDTO katedraDTO;

    public Integer getNastavnikId() {
        return nastavnikId;
    }

    public void setNastavnikId(Integer nastavnikId) {
        this.nastavnikId = nastavnikId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getZvanje() {
        return zvanje;
    }

    public void setZvanje(String zvanje) {
        this.zvanje = zvanje;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getKabinet() {
        return kabinet;
    }

    public void setKabinet(String kabinet) {
        this.kabinet = kabinet;
    }

    public String getePosta() {
        return ePosta;
    }

    public void setePosta(String ePosta) {
        this.ePosta = ePosta;
    }

    public KatedraDTO getKatedraDTO() {
        return katedraDTO;
    }

    public void setKatedraDTO(KatedraDTO katedraDTO) {
        this.katedraDTO = katedraDTO;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.nastavnikId);
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
        final NastavnikDTO other = (NastavnikDTO) obj;
        if (!Objects.equals(this.nastavnikId, other.nastavnikId)) {
            return false;
        }
        return true;
    }
    
    
   
}
