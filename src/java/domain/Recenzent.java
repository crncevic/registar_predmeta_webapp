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
@Table(name = "recenzent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recenzent.findAll", query = "SELECT r FROM Recenzent r")
    , @NamedQuery(name = "Recenzent.findByRecenzentId", query = "SELECT r FROM Recenzent r WHERE r.recenzentId = :recenzentId")
    , @NamedQuery(name = "Recenzent.findByIme", query = "SELECT r FROM Recenzent r WHERE r.ime = :ime")
    , @NamedQuery(name = "Recenzent.findByPrezime", query = "SELECT r FROM Recenzent r WHERE r.prezime = :prezime")
    , @NamedQuery(name = "Recenzent.findByTitula", query = "SELECT r FROM Recenzent r WHERE r.titula = :titula")})
public class Recenzent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "recenzentId")
    private Integer recenzentId;
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

    public Recenzent() {
    }

    public Recenzent(Integer recenzentId) {
        this.recenzentId = recenzentId;
    }

    public Recenzent(Integer recenzentId, String ime, String prezime) {
        this.recenzentId = recenzentId;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Integer getRecenzentId() {
        return recenzentId;
    }

    public void setRecenzentId(Integer recenzentId) {
        this.recenzentId = recenzentId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recenzentId != null ? recenzentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recenzent)) {
            return false;
        }
        Recenzent other = (Recenzent) object;
        if ((this.recenzentId == null && other.recenzentId != null) || (this.recenzentId != null && !this.recenzentId.equals(other.recenzentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Recenzent[ recenzentId=" + recenzentId + " ]";
    }
    
}
