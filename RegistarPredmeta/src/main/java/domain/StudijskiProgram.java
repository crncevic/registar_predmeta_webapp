/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Petar
 */
@Entity
@Table(name = "studijski_program")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudijskiProgram.findAll", query = "SELECT s FROM StudijskiProgram s")
    , @NamedQuery(name = "StudijskiProgram.findByStudijskiProgramId", query = "SELECT s FROM StudijskiProgram s WHERE s.studijskiProgramId = :studijskiProgramId")
    , @NamedQuery(name = "StudijskiProgram.findByNaziv", query = "SELECT s FROM StudijskiProgram s WHERE s.naziv = :naziv")})
public class StudijskiProgram implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "studijskiProgramId")
    private Integer studijskiProgramId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "naziv")
    private String naziv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studijskiProgram")
    private List<PredmetNaStudijskomProgramu> predmetNaStudijskomProgramuList;

    public StudijskiProgram() {
    }

    public StudijskiProgram(Integer studijskiProgramId) {
        this.studijskiProgramId = studijskiProgramId;
    }

    public StudijskiProgram(Integer studijskiProgramId, String naziv) {
        this.studijskiProgramId = studijskiProgramId;
        this.naziv = naziv;
    }

    public Integer getStudijskiProgramId() {
        return studijskiProgramId;
    }

    public void setStudijskiProgramId(Integer studijskiProgramId) {
        this.studijskiProgramId = studijskiProgramId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public List<PredmetNaStudijskomProgramu> getPredmetNaStudijskomProgramuList() {
        return predmetNaStudijskomProgramuList;
    }

    public void setPredmetNaStudijskomProgramuList(List<PredmetNaStudijskomProgramu> predmetNaStudijskomProgramuList) {
        this.predmetNaStudijskomProgramuList = predmetNaStudijskomProgramuList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studijskiProgramId != null ? studijskiProgramId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudijskiProgram)) {
            return false;
        }
        StudijskiProgram other = (StudijskiProgram) object;
        if ((this.studijskiProgramId == null && other.studijskiProgramId != null) || (this.studijskiProgramId != null && !this.studijskiProgramId.equals(other.studijskiProgramId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.StudijskiProgram[ studijskiProgramId=" + studijskiProgramId + " ]";
    }
    
}
