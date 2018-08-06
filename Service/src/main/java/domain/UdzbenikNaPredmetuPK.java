/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Petar
 */
@Embeddable
public class UdzbenikNaPredmetuPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "udzbenikId")
    private int udzbenikId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "predmetId")
    private int predmetId;

    public UdzbenikNaPredmetuPK() {
    }

    public UdzbenikNaPredmetuPK(int udzbenikId, int predmetId) {
        this.udzbenikId = udzbenikId;
        this.predmetId = predmetId;
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
    
    
   
    
    
    
    
}
