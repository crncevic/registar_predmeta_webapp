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
public class KorisnikDTO implements Serializable{

    private Integer korisnikId;
    @NotNull
    @Size(min = 1, max = 100)
    private String ime;
    @NotNull
    @Size(min = 1, max = 100)
    private String prezime;
    @NotNull
    @Size(min = 1, max = 100)
    private String username;
    @NotNull
    @Size(min = 1, max = 100)
    private String password;
    private UlogaDTO ulogaDTO;

    public Integer getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UlogaDTO getUlogaDTO() {
        return ulogaDTO;
    }

    public void setUlogaDTO(UlogaDTO ulogaDTO) {
        this.ulogaDTO = ulogaDTO;
    }

}
