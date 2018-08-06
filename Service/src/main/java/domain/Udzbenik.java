/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 *
 * @author Petar
 */
@Entity
@Table(name = "udzbenik")
@XmlRootElement
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize
@NamedQueries({
    @NamedQuery(name = "Udzbenik.findAll", query = "SELECT u FROM Udzbenik u")
    , @NamedQuery(name = "Udzbenik.findByUdzbenikId", query = "SELECT u FROM Udzbenik u WHERE u.udzbenikId = :udzbenikId")
    , @NamedQuery(name = "Udzbenik.findByNaziv", query = "SELECT u FROM Udzbenik u WHERE u.naziv = :naziv")
    , @NamedQuery(name = "Udzbenik.findByGodinaIzdanja", query = "SELECT u FROM Udzbenik u WHERE u.godinaIzdanja = :godinaIzdanja")
    , @NamedQuery(name = "Udzbenik.findByIzdavac", query = "SELECT u FROM Udzbenik u WHERE u.izdavac = :izdavac")
    , @NamedQuery(name = "Udzbenik.findByStampa", query = "SELECT u FROM Udzbenik u WHERE u.stampa = :stampa")
    , @NamedQuery(name = "Udzbenik.findByRbrIzdanja", query = "SELECT u FROM Udzbenik u WHERE u.rbrIzdanja = :rbrIzdanja")
    , @NamedQuery(name = "Udzbenik.findByTiraz", query = "SELECT u FROM Udzbenik u WHERE u.tiraz = :tiraz")
    , @NamedQuery(name = "Udzbenik.findByIsbn", query = "SELECT u FROM Udzbenik u WHERE u.isbn = :isbn")})
public class Udzbenik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "udzbenikId")
    private Integer udzbenikId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "naziv")
    private String naziv;
    @Column(name = "godina_izdanja")
    private Integer godinaIzdanja;
    @Size(max = 100)
    @Column(name = "izdavac")
    private String izdavac;
    @Size(max = 200)
    @Column(name = "stampa")
    private String stampa;
    @Column(name = "rbr_izdanja")
    private Integer rbrIzdanja;
    @Column(name = "tiraz")
    private Integer tiraz;
    @Column(name = "isbn")
    private Integer isbn;
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "udzbenik",fetch = FetchType.LAZY)
    private List<UdzbenikNaPredmetu> predmetList;
    @OneToMany(mappedBy = "udzbenik")
    private List<OsobaUVeziSaUdzbenikom> osobaUVeziSaUdzbenikomList;

    public Udzbenik() {
    }

    public Udzbenik(Integer udzbenikId) {
        this.udzbenikId = udzbenikId;
    }

    public Udzbenik(Integer udzbenikId, String naziv) {
        this.udzbenikId = udzbenikId;
        this.naziv = naziv;
    }

    public Integer getUdzbenikId() {
        return udzbenikId;
    }

    public void setUdzbenikId(Integer udzbenikId) {
        this.udzbenikId = udzbenikId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getGodinaIzdanja() {
        return godinaIzdanja;
    }

    public void setGodinaIzdanja(Integer godinaIzdanja) {
        this.godinaIzdanja = godinaIzdanja;
    }

    public String getIzdavac() {
        return izdavac;
    }

    public void setIzdavac(String izdavac) {
        this.izdavac = izdavac;
    }

    public String getStampa() {
        return stampa;
    }

    public void setStampa(String stampa) {
        this.stampa = stampa;
    }

    public Integer getRbrIzdanja() {
        return rbrIzdanja;
    }

    public void setRbrIzdanja(Integer rbrIzdanja) {
        this.rbrIzdanja = rbrIzdanja;
    }

    public Integer getTiraz() {
        return tiraz;
    }

    public void setTiraz(Integer tiraz) {
        this.tiraz = tiraz;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    @XmlTransient
    public List<UdzbenikNaPredmetu> getPredmetList() {
        return predmetList;
    }

    public void setPredmetList(List<UdzbenikNaPredmetu> predmetList) {
        this.predmetList = predmetList;
    }

    @XmlTransient
    public List<OsobaUVeziSaUdzbenikom> getOsobaUVeziSaUdzbenikomList() {
        return osobaUVeziSaUdzbenikomList;
    }

    public void setOsobaUVeziSaUdzbenikomList(List<OsobaUVeziSaUdzbenikom> osobaUVeziSaUdzbenikomList) {
        this.osobaUVeziSaUdzbenikomList = osobaUVeziSaUdzbenikomList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (udzbenikId != null ? udzbenikId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Udzbenik)) {
            return false;
        }
        Udzbenik other = (Udzbenik) object;
        if ((this.udzbenikId == null && other.udzbenikId != null) || (this.udzbenikId != null && !this.udzbenikId.equals(other.udzbenikId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Udzbenik[ udzbenikId=" + udzbenikId + " ]";
    }
    
}
