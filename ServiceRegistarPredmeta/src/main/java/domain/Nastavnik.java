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
@Table(name = "nastavnik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nastavnik.findAll", query = "SELECT n FROM Nastavnik n")
    , @NamedQuery(name = "Nastavnik.findByNastavnikId", query = "SELECT n FROM Nastavnik n WHERE n.nastavnikId = :nastavnikId")
    , @NamedQuery(name = "Nastavnik.findByIme", query = "SELECT n FROM Nastavnik n WHERE n.ime = :ime")
    , @NamedQuery(name = "Nastavnik.findByPrezime", query = "SELECT n FROM Nastavnik n WHERE n.prezime = :prezime")
    , @NamedQuery(name = "Nastavnik.findByZvanje", query = "SELECT n FROM Nastavnik n WHERE n.zvanje = :zvanje")
    , @NamedQuery(name = "Nastavnik.findByTelefon", query = "SELECT n FROM Nastavnik n WHERE n.telefon = :telefon")
    , @NamedQuery(name = "Nastavnik.findByKabinet", query = "SELECT n FROM Nastavnik n WHERE n.kabinet = :kabinet")
    , @NamedQuery(name = "Nastavnik.findByEPosta", query = "SELECT n FROM Nastavnik n WHERE n.ePosta = :ePosta")})
public class Nastavnik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nastavnikId")
    private Integer nastavnikId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ime")
    private String ime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "prezime")
    private String prezime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "zvanje")
    private String zvanje;
    @Size(max = 100)
    @Column(name = "telefon")
    private String telefon;
    @Size(max = 50)
    @Column(name = "kabinet")
    private String kabinet;
    @Size(max = 100)
    @Column(name = "ePosta")
    private String ePosta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nastavnik")
    private List<NastavnikNaPredmetu> nastavnikNaPredmetuList;
    @JoinColumn(name = "katedraId", referencedColumnName = "katedraId")
    @ManyToOne
    private Katedra katedraId;
    @OneToMany(mappedBy = "sef")
    private List<Katedra> katedraList;
    @OneToMany(mappedBy = "zamenikSefa")
    private List<Katedra> katedraList1;
    @OneToMany(mappedBy = "sekretar")
    private List<Katedra> katedraList2;

    public Nastavnik() {
    }

    public Nastavnik(Integer nastavnikId) {
        this.nastavnikId = nastavnikId;
    }

    public Nastavnik(Integer nastavnikId, String ime, String prezime, String zvanje) {
        this.nastavnikId = nastavnikId;
        this.ime = ime;
        this.prezime = prezime;
        this.zvanje = zvanje;
    }

    public Integer getNastavnikId() {
        return nastavnikId;
    }

    public void setNastavnikId(Integer nastavnikId) {
        this.nastavnikId = nastavnikId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getZvanje() {
        return zvanje;
    }

    public void setZvanje(String zvanje) {
        this.zvanje = zvanje;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getKabinet() {
        return kabinet;
    }

    public void setKabinet(String kabinet) {
        this.kabinet = kabinet;
    }

    public String getEPosta() {
        return ePosta;
    }

    public void setEPosta(String ePosta) {
        this.ePosta = ePosta;
    }

    @XmlTransient
    public List<NastavnikNaPredmetu> getNastavnikNaPredmetuList() {
        return nastavnikNaPredmetuList;
    }

    public void setNastavnikNaPredmetuList(List<NastavnikNaPredmetu> nastavnikNaPredmetuList) {
        this.nastavnikNaPredmetuList = nastavnikNaPredmetuList;
    }

    public Katedra getKatedraId() {
        return katedraId;
    }

    public void setKatedraId(Katedra katedraId) {
        this.katedraId = katedraId;
    }

    @XmlTransient
    public List<Katedra> getKatedraList() {
        return katedraList;
    }

    public void setKatedraList(List<Katedra> katedraList) {
        this.katedraList = katedraList;
    }

    @XmlTransient
    public List<Katedra> getKatedraList1() {
        return katedraList1;
    }

    public void setKatedraList1(List<Katedra> katedraList1) {
        this.katedraList1 = katedraList1;
    }

    @XmlTransient
    public List<Katedra> getKatedraList2() {
        return katedraList2;
    }

    public void setKatedraList2(List<Katedra> katedraList2) {
        this.katedraList2 = katedraList2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nastavnikId != null ? nastavnikId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nastavnik)) {
            return false;
        }
        Nastavnik other = (Nastavnik) object;
        if ((this.nastavnikId == null && other.nastavnikId != null) || (this.nastavnikId != null && !this.nastavnikId.equals(other.nastavnikId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Nastavnik[ nastavnikId=" + nastavnikId + " ]";
    }
    
}
