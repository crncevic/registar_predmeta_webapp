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
@Table(name = "tip_nastave")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipNastave.findAll", query = "SELECT t FROM TipNastave t")
    , @NamedQuery(name = "TipNastave.findByTipnastaveId", query = "SELECT t FROM TipNastave t WHERE t.tipnastaveId = :tipnastaveId")
    , @NamedQuery(name = "TipNastave.findByNaziv", query = "SELECT t FROM TipNastave t WHERE t.naziv = :naziv")})
public class TipNastave implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "tip_nastaveId")
    private Integer tipnastaveId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "naziv")
    private String naziv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipNastave")
    private List<NastavnikNaPredmetu> nastavnikNaPredmetuList;
    @OneToMany(mappedBy = "tipnastaveId")
    private List<TematskaCelina> tematskaCelinaList;

    public TipNastave() {
    }

    public TipNastave(Integer tipnastaveId) {
        this.tipnastaveId = tipnastaveId;
    }

    public TipNastave(Integer tipnastaveId, String naziv) {
        this.tipnastaveId = tipnastaveId;
        this.naziv = naziv;
    }

    public Integer getTipnastaveId() {
        return tipnastaveId;
    }

    public void setTipnastaveId(Integer tipnastaveId) {
        this.tipnastaveId = tipnastaveId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public List<NastavnikNaPredmetu> getNastavnikNaPredmetuList() {
        return nastavnikNaPredmetuList;
    }

    public void setNastavnikNaPredmetuList(List<NastavnikNaPredmetu> nastavnikNaPredmetuList) {
        this.nastavnikNaPredmetuList = nastavnikNaPredmetuList;
    }

    @XmlTransient
    public List<TematskaCelina> getTematskaCelinaList() {
        return tematskaCelinaList;
    }

    public void setTematskaCelinaList(List<TematskaCelina> tematskaCelinaList) {
        this.tematskaCelinaList = tematskaCelinaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipnastaveId != null ? tipnastaveId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipNastave)) {
            return false;
        }
        TipNastave other = (TipNastave) object;
        if ((this.tipnastaveId == null && other.tipnastaveId != null) || (this.tipnastaveId != null && !this.tipnastaveId.equals(other.tipnastaveId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.TipNastave[ tipnastaveId=" + tipnastaveId + " ]";
    }
    
}
