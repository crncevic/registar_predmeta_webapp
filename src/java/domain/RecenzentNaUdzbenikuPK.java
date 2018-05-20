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
import javax.validation.constraints.NotNull;

/**
 *
 * @author Petar
 */
@Embeddable
public class RecenzentNaUdzbenikuPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "recenzentId")
    private int recenzentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "udzbenikId")
    private int udzbenikId;

    public RecenzentNaUdzbenikuPK() {
    }

    public RecenzentNaUdzbenikuPK(int recenzentId, int udzbenikId) {
        this.recenzentId = recenzentId;
        this.udzbenikId = udzbenikId;
    }

    public int getRecenzentId() {
        return recenzentId;
    }

    public void setRecenzentId(int recenzentId) {
        this.recenzentId = recenzentId;
    }

    public int getUdzbenikId() {
        return udzbenikId;
    }

    public void setUdzbenikId(int udzbenikId) {
        this.udzbenikId = udzbenikId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) recenzentId;
        hash += (int) udzbenikId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecenzentNaUdzbenikuPK)) {
            return false;
        }
        RecenzentNaUdzbenikuPK other = (RecenzentNaUdzbenikuPK) object;
        if (this.recenzentId != other.recenzentId) {
            return false;
        }
        if (this.udzbenikId != other.udzbenikId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.RecenzentNaUdzbenikuPK[ recenzentId=" + recenzentId + ", udzbenikId=" + udzbenikId + " ]";
    }
    
}
