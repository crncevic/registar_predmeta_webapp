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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Petar
 */
@Entity
@Table(name = "osoba_u_vezi_sa_udzbenikom")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OsobaUVeziSaUdzbenikom.findAll", query = "SELECT o FROM OsobaUVeziSaUdzbenikom o")
    , @NamedQuery(name = "OsobaUVeziSaUdzbenikom.findByOsobaId", query = "SELECT o FROM OsobaUVeziSaUdzbenikom o WHERE o.osobaId = :osobaId")
    , @NamedQuery(name = "OsobaUVeziSaUdzbenikom.findByIme", query = "SELECT o FROM OsobaUVeziSaUdzbenikom o WHERE o.ime = :ime")
    , @NamedQuery(name = "OsobaUVeziSaUdzbenikom.findByPrezime", query = "SELECT o FROM OsobaUVeziSaUdzbenikom o WHERE o.prezime = :prezime")
    , @NamedQuery(name = "OsobaUVeziSaUdzbenikom.findByTitula", query = "SELECT o FROM OsobaUVeziSaUdzbenikom o WHERE o.titula = :titula")
    , @NamedQuery(name = "OsobaUVeziSaUdzbenikom.findByUdzbenikId", query = "SELECT o FROM OsobaUVeziSaUdzbenikom o WHERE o.udzbenik.udzbenikId = :udzbenikId")})
public class OsobaUVeziSaUdzbenikom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "osobaId")
    private Integer osobaId;
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
    @Size(max = 100)
    @Column(name = "titula")
    private String titula;
    @JoinColumn(name = "ulogaId", referencedColumnName = "ulogaId")
    @ManyToOne
    private UlogaUdzbenik uloga;
    @JoinColumn(name = "udzbenikId", referencedColumnName = "udzbenikId")
    @ManyToOne
    private Udzbenik udzbenik;
    

    public OsobaUVeziSaUdzbenikom() {
    }

    public OsobaUVeziSaUdzbenikom(Integer osobaId) {
        this.osobaId = osobaId;
    }

    public OsobaUVeziSaUdzbenikom(Integer osobaId, String ime, String prezime) {
        this.osobaId = osobaId;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Integer getOsobaId() {
        return osobaId;
    }

    public void setOsobaId(Integer osobaId) {
        this.osobaId = osobaId;
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

    public String getTitula() {
        return titula;
    }

    public void setTitula(String titula) {
        this.titula = titula;
    }

    public UlogaUdzbenik getUloga() {
        return uloga;
    }

    public void setUloga(UlogaUdzbenik uloga) {
        this.uloga = uloga;
    }

    public Udzbenik getUdzbenik() {
        return udzbenik;
    }

    public void setUdzbenik(Udzbenik udzbenik) {
        this.udzbenik = udzbenik;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (osobaId != null ? osobaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OsobaUVeziSaUdzbenikom)) {
            return false;
        }
        OsobaUVeziSaUdzbenikom other = (OsobaUVeziSaUdzbenikom) object;
        if ((this.osobaId == null && other.osobaId != null) || (this.osobaId != null && !this.osobaId.equals(other.osobaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.OsobaUVeziSaUdzbenikom[ osobaId=" + osobaId + " ]";
    }

    

}
