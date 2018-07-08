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
public class NastavnikNaPredmetuPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "nastavnikId")
    private int nastavnikId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "predmetId")
    private int predmetId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipNastaveId")
    private int tipNastaveId;

    public NastavnikNaPredmetuPK() {
    }

    public NastavnikNaPredmetuPK(int nastavnikId, int predmetId, int tipNastaveId) {
        this.nastavnikId = nastavnikId;
        this.predmetId = predmetId;
        this.tipNastaveId = tipNastaveId;
    }

    public int getNastavnikId() {
        return nastavnikId;
    }

    public void setNastavnikId(int nastavnikId) {
        this.nastavnikId = nastavnikId;
    }

    public int getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(int predmetId) {
        this.predmetId = predmetId;
    }

    public int getTipNastaveId() {
        return tipNastaveId;
    }

    public void setTipNastaveId(int tipNastaveId) {
        this.tipNastaveId = tipNastaveId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) nastavnikId;
        hash += (int) predmetId;
        hash += (int) tipNastaveId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NastavnikNaPredmetuPK)) {
            return false;
        }
        NastavnikNaPredmetuPK other = (NastavnikNaPredmetuPK) object;
        if (this.nastavnikId != other.nastavnikId) {
            return false;
        }
        if (this.predmetId != other.predmetId) {
            return false;
        }
        if (this.tipNastaveId != other.tipNastaveId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.NastavnikNaPredmetuPK[ nastavnikId=" + nastavnikId + ", predmetId=" + predmetId + ", tipNastaveId=" + tipNastaveId + " ]";
    }
    
}
