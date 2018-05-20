/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Petar
 */
@Entity
@Table(name = "predmet_na_studijskom_programu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PredmetNaStudijskomProgramu.findAll", query = "SELECT p FROM PredmetNaStudijskomProgramu p")
    , @NamedQuery(name = "PredmetNaStudijskomProgramu.findByPredmetId", query = "SELECT p FROM PredmetNaStudijskomProgramu p WHERE p.predmetId = :predmetId")
    , @NamedQuery(name = "PredmetNaStudijskomProgramu.findByStatus", query = "SELECT p FROM PredmetNaStudijskomProgramu p WHERE p.status = :status")
    , @NamedQuery(name = "PredmetNaStudijskomProgramu.findByEspb", query = "SELECT p FROM PredmetNaStudijskomProgramu p WHERE p.espb = :espb")})
public class PredmetNaStudijskomProgramu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "predmetId")
    private Integer predmetId;
    @Size(max = 100)
    @Column(name = "status")
    private String status;
    @Column(name = "espb")
    private Integer espb;
    @JoinColumn(name = "predmetId", referencedColumnName = "predmetId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Predmet predmet;
    @JoinColumn(name = "studijski_programId", referencedColumnName = "studijskiProgramId")
    @ManyToOne
    private StudijskiProgram studijskiprogramId;

    public PredmetNaStudijskomProgramu() {
    }

    public PredmetNaStudijskomProgramu(Integer predmetId) {
        this.predmetId = predmetId;
    }

    public Integer getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Integer predmetId) {
        this.predmetId = predmetId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getEspb() {
        return espb;
    }

    public void setEspb(Integer espb) {
        this.espb = espb;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public StudijskiProgram getStudijskiprogramId() {
        return studijskiprogramId;
    }

    public void setStudijskiprogramId(StudijskiProgram studijskiprogramId) {
        this.studijskiprogramId = studijskiprogramId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (predmetId != null ? predmetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PredmetNaStudijskomProgramu)) {
            return false;
        }
        PredmetNaStudijskomProgramu other = (PredmetNaStudijskomProgramu) object;
        if ((this.predmetId == null && other.predmetId != null) || (this.predmetId != null && !this.predmetId.equals(other.predmetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.PredmetNaStudijskomProgramu[ predmetId=" + predmetId + " ]";
    }
    
}
