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
public class PredmetNaStudijskomProgramuPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "predmetId")
    private int predmetId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "studijski_programId")
    private int studijskiprogramId;

    public PredmetNaStudijskomProgramuPK() {
    }

    public PredmetNaStudijskomProgramuPK(int predmetId, int studijskiprogramId) {
        this.predmetId = predmetId;
        this.studijskiprogramId = studijskiprogramId;
    }

    public int getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(int predmetId) {
        this.predmetId = predmetId;
    }

    public int getStudijskiprogramId() {
        return studijskiprogramId;
    }

    public void setStudijskiprogramId(int studijskiprogramId) {
        this.studijskiprogramId = studijskiprogramId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) predmetId;
        hash += (int) studijskiprogramId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PredmetNaStudijskomProgramuPK)) {
            return false;
        }
        PredmetNaStudijskomProgramuPK other = (PredmetNaStudijskomProgramuPK) object;
        if (this.predmetId != other.predmetId) {
            return false;
        }
        if (this.studijskiprogramId != other.studijskiprogramId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.PredmetNaStudijskomProgramuPK[ predmetId=" + predmetId + ", studijskiprogramId=" + studijskiprogramId + " ]";
    }
    
}
