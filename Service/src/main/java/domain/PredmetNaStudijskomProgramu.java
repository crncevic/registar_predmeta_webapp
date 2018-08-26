/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
    , @NamedQuery(name = "PredmetNaStudijskomProgramu.findByPredmetId", query = "SELECT p FROM PredmetNaStudijskomProgramu p WHERE p.predmetNaStudijskomProgramuPK.predmetId = :predmetId")
    , @NamedQuery(name = "PredmetNaStudijskomProgramu.findByStudijskiprogramId", query = "SELECT p FROM PredmetNaStudijskomProgramu p WHERE p.predmetNaStudijskomProgramuPK.studijskiprogramId = :studijskiprogramId")
    , @NamedQuery(name = "PredmetNaStudijskomProgramu.findByEspb", query = "SELECT p FROM PredmetNaStudijskomProgramu p WHERE p.espb = :espb")
    , @NamedQuery(name = "PredmetNaStudijskomProgramu.findBySemestar", query = "SELECT p FROM PredmetNaStudijskomProgramu p WHERE p.semestar = :semestar")
    , @NamedQuery(name = "PredmetNaStudijskomProgramu.delete",query = "DELETE FROM PredmetNaStudijskomProgramu pnsp WHERE pnsp.predmetNaStudijskomProgramuPK.predmetId=:predmetId AND pnsp.predmetNaStudijskomProgramuPK.studijskiprogramId=:studijskiProgramId")    
})
public class PredmetNaStudijskomProgramu implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PredmetNaStudijskomProgramuPK predmetNaStudijskomProgramuPK;
    @Column(name = "espb")
    private Integer espb;
    @Basic(optional = false)
    @NotNull
    @Column(name = "semestar")
    private int semestar;
    @JoinColumn(name = "statusId", referencedColumnName = "statusId")
    @ManyToOne
    private Status statusId;
    @JoinColumn(name = "studijski_programId", referencedColumnName = "studijskiProgramId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private StudijskiProgram studijskiProgram;
    @JoinColumn(name = "predmetId", referencedColumnName = "predmetId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Predmet predmet;

    public PredmetNaStudijskomProgramu() {
    }

    public PredmetNaStudijskomProgramu(PredmetNaStudijskomProgramuPK predmetNaStudijskomProgramuPK) {
        this.predmetNaStudijskomProgramuPK = predmetNaStudijskomProgramuPK;
    }

    public PredmetNaStudijskomProgramu(PredmetNaStudijskomProgramuPK predmetNaStudijskomProgramuPK, int semestar) {
        this.predmetNaStudijskomProgramuPK = predmetNaStudijskomProgramuPK;
        this.semestar = semestar;
    }

    public PredmetNaStudijskomProgramu(int predmetId, int studijskiprogramId) {
        this.predmetNaStudijskomProgramuPK = new PredmetNaStudijskomProgramuPK(predmetId, studijskiprogramId);
    }

    public PredmetNaStudijskomProgramuPK getPredmetNaStudijskomProgramuPK() {
        return predmetNaStudijskomProgramuPK;
    }

    public void setPredmetNaStudijskomProgramuPK(PredmetNaStudijskomProgramuPK predmetNaStudijskomProgramuPK) {
        this.predmetNaStudijskomProgramuPK = predmetNaStudijskomProgramuPK;
    }

    public Integer getEspb() {
        return espb;
    }

    public void setEspb(Integer espb) {
        this.espb = espb;
    }

    public int getSemestar() {
        return semestar;
    }

    public void setSemestar(int semestar) {
        this.semestar = semestar;
    }

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        this.statusId = statusId;
    }

    public StudijskiProgram getStudijskiProgram() {
        return studijskiProgram;
    }

    public void setStudijskiProgram(StudijskiProgram studijskiProgram) {
        this.studijskiProgram = studijskiProgram;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (predmetNaStudijskomProgramuPK != null ? predmetNaStudijskomProgramuPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PredmetNaStudijskomProgramu)) {
            return false;
        }
        PredmetNaStudijskomProgramu other = (PredmetNaStudijskomProgramu) object;
        if ((this.predmetNaStudijskomProgramuPK == null && other.predmetNaStudijskomProgramuPK != null) || (this.predmetNaStudijskomProgramuPK != null && !this.predmetNaStudijskomProgramuPK.equals(other.predmetNaStudijskomProgramuPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.PredmetNaStudijskomProgramu[ predmetNaStudijskomProgramuPK=" + predmetNaStudijskomProgramuPK + " ]";
    }

   
}
