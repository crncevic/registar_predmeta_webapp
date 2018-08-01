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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Petar
 */
@Entity
@Table(name = "vrsta_i_nivo_studija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VrstaINivoStudija.findAll", query = "SELECT v FROM VrstaINivoStudija v")
    , @NamedQuery(name = "VrstaINivoStudija.findByVrstaId", query = "SELECT v FROM VrstaINivoStudija v WHERE v.vrstaId = :vrstaId")
    , @NamedQuery(name = "VrstaINivoStudija.findByNaziv", query = "SELECT v FROM VrstaINivoStudija v WHERE v.naziv = :naziv")})
public class VrstaINivoStudija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vrstaId")
    private Integer vrstaId;
    @Size(max = 200)
    @Column(name = "naziv")
    private String naziv;
    @OneToMany(mappedBy = "vrstaINivoStudija")
    private List<Predmet> predmetList;

    public VrstaINivoStudija() {
    }

    public VrstaINivoStudija(Integer vrstaId) {
        this.vrstaId = vrstaId;
    }

    public Integer getVrstaId() {
        return vrstaId;
    }

    public void setVrstaId(Integer vrstaId) {
        this.vrstaId = vrstaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public List<Predmet> getPredmetList() {
        return predmetList;
    }

    public void setPredmetList(List<Predmet> predmetList) {
        this.predmetList = predmetList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vrstaId != null ? vrstaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VrstaINivoStudija)) {
            return false;
        }
        VrstaINivoStudija other = (VrstaINivoStudija) object;
        if ((this.vrstaId == null && other.vrstaId != null) || (this.vrstaId != null && !this.vrstaId.equals(other.vrstaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.VrstaINivoStudija[ vrstaId=" + vrstaId + " ]";
    }
    
}
