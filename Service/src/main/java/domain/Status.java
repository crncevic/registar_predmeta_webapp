/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Status.findAll", query = "SELECT s FROM Status s")
    , @NamedQuery(name = "Status.findByStatusId", query = "SELECT s FROM Status s WHERE s.statusId = :statusId")
    , @NamedQuery(name = "Status.findByNaziv", query = "SELECT s FROM Status s WHERE s.naziv = :naziv")})
public class Status implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "statusId")
    private Integer statusId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "naziv")
    private String naziv;
    @OneToMany(mappedBy = "statusId")
    private List<PredmetNaStudijskomProgramu> predmetNaStudijskomProgramuList;

    public Status() {
    }

    public Status(Integer statusId) {
        this.statusId = statusId;
    }

    public Status(Integer statusId, String naziv) {
        this.statusId = statusId;
        this.naziv = naziv;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
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
        hash += (statusId != null ? statusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Status)) {
            return false;
        }
        Status other = (Status) object;
        if ((this.statusId == null && other.statusId != null) || (this.statusId != null && !this.statusId.equals(other.statusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Status[ statusId=" + statusId + " ]";
    }

    
    
}
