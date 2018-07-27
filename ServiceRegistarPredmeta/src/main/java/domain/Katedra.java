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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "katedra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Katedra.findAll", query = "SELECT k FROM Katedra k")
    , @NamedQuery(name = "Katedra.findByKatedraId", query = "SELECT k FROM Katedra k WHERE k.katedraId = :katedraId")
    , @NamedQuery(name = "Katedra.findByNaziv", query = "SELECT k FROM Katedra k WHERE k.naziv = :naziv")})
public class Katedra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "katedraId")
    @NotNull
    private Integer katedraId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "naziv")
    private String naziv;
    @OneToMany(mappedBy = "katedraId")
    private List<Nastavnik> nastavnikList;
    @JoinColumn(name = "sef", referencedColumnName = "nastavnikId")
    @ManyToOne
    private Nastavnik sef;
    @JoinColumn(name = "zamenikSefa", referencedColumnName = "nastavnikId")
    @ManyToOne
    private Nastavnik zamenikSefa;
    @JoinColumn(name = "sekretar", referencedColumnName = "nastavnikId")
    @ManyToOne
    private Nastavnik sekretar;

    public Katedra() {
    }

    public Katedra(Integer katedraId) {
        this.katedraId = katedraId;
    }

    public Katedra(Integer katedraId, String naziv) {
        this.katedraId = katedraId;
        this.naziv = naziv;
    }

    public Integer getKatedraId() {
        return katedraId;
    }

    public void setKatedraId(Integer katedraId) {
        this.katedraId = katedraId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public List<Nastavnik> getNastavnikList() {
        return nastavnikList;
    }

    public void setNastavnikList(List<Nastavnik> nastavnikList) {
        this.nastavnikList = nastavnikList;
    }

    public Nastavnik getSef() {
        return sef;
    }

    public void setSef(Nastavnik sef) {
        this.sef = sef;
    }

    public Nastavnik getZamenikSefa() {
        return zamenikSefa;
    }

    public void setZamenikSefa(Nastavnik zamenikSefa) {
        this.zamenikSefa = zamenikSefa;
    }

    public Nastavnik getSekretar() {
        return sekretar;
    }

    public void setSekretar(Nastavnik sekretar) {
        this.sekretar = sekretar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (katedraId != null ? katedraId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Katedra)) {
            return false;
        }
        Katedra other = (Katedra) object;
        if ((this.katedraId == null && other.katedraId != null) || (this.katedraId != null && !this.katedraId.equals(other.katedraId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Katedra[ katedraId=" + katedraId + " ]";
    }
    
}
