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
public class UdzbenikNaPredmetuDTO {
    private int udzbenikId;
    private int predmetId;
    
    private UdzbenikDTO udzbenikDTO;

    public UdzbenikNaPredmetuDTO() {
    }

    public UdzbenikNaPredmetuDTO(int udzbenikId, int predmetId, UdzbenikDTO udzbenikDTO) {
        this.udzbenikId = udzbenikId;
        this.predmetId = predmetId;
        this.udzbenikDTO = udzbenikDTO;
    }

    public int getUdzbenikId() {
        return udzbenikId;
    }

    public void setUdzbenikId(int udzbenikId) {
        this.udzbenikId = udzbenikId;
    }

    public int getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(int predmetId) {
        this.predmetId = predmetId;
    }

    public UdzbenikDTO getUdzbenikDTO() {
        return udzbenikDTO;
    }

    public void setUdzbenikDTO(UdzbenikDTO udzbenikDTO) {
        this.udzbenikDTO = udzbenikDTO;
    }
    
    
}
