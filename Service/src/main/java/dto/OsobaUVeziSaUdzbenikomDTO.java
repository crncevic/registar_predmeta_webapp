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
public class OsobaUVeziSaUdzbenikomDTO implements Serializable{
   
    private Integer osobaId;
  
    @NotNull
    @Size(min = 1, max = 100)
    private String ime;

    @NotNull
    @Size(min = 1, max = 100)
    private String prezime;
    @Size(max = 100)
    private String titula;
   
    @NotNull
    private UlogaUdzbenikDTO ulogaDTO;

    public Integer getOsobaId() {
        return osobaId;
    }

    public void setOsobaId(Integer osobaId) {
        this.osobaId = osobaId;
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

    public String getTitula() {
        return titula;
    }

    public void setTitula(String titula) {
        this.titula = titula;
    }

    public UlogaUdzbenikDTO getUlogaDTO() {
        return ulogaDTO;
    }

    public void setUlogaDTO(UlogaUdzbenikDTO ulogaDTO) {
        this.ulogaDTO = ulogaDTO;
    }
   
    
    
   
}
