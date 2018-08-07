/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author Petar
 */
public class StatusDTO implements Serializable{

    private int statusId;
    private String naziv;

    public StatusDTO() {
    }

    public StatusDTO(int statusId, String naziv) {
        this.statusId = statusId;
        this.naziv = naziv;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

}
