/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Petar
 */
public class UlogaDTO {
    private int ulogaId;
    private String naziv;

    public UlogaDTO() {
    }

    
    
    public UlogaDTO(int ulogaId, String naziv) {
        this.ulogaId = ulogaId;
        this.naziv = naziv;
    }

    public int getUlogaId() {
        return ulogaId;
    }

    public void setUlogaId(int ulogaId) {
        this.ulogaId = ulogaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    
}
