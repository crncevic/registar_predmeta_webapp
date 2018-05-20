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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "autor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Autor.findAll", query = "SELECT a FROM Autor a")
    , @NamedQuery(name = "Autor.findByAutorId", query = "SELECT a FROM Autor a WHERE a.autorId = :autorId")
    , @NamedQuery(name = "Autor.findByIme", query = "SELECT a FROM Autor a WHERE a.ime = :ime")
    , @NamedQuery(name = "Autor.findByPrezime", query = "SELECT a FROM Autor a WHERE a.prezime = :prezime")
    , @NamedQuery(name = "Autor.findByTitula", query = "SELECT a FROM Autor a WHERE a.titula = :titula")})
public class Autor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "autorId")
    private Integer autorId;
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
    @JoinTable(name = "autor_na_udzbeniku", joinColumns = {
        @JoinColumn(name = "autorId", referencedColumnName = "autorId")}, inverseJoinColumns = {
        @JoinColumn(name = "udzbenikId", referencedColumnName = "udzbenikId")})
    @ManyToMany
    private List<Udzbenik> udzbenikList;

    public Autor() {
    }

    public Autor(Integer autorId) {
        this.autorId = autorId;
    }

    public Autor(Integer autorId, String ime, String prezime) {
        this.autorId = autorId;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Integer getAutorId() {
        return autorId;
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
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

    @XmlTransient
    public List<Udzbenik> getUdzbenikList() {
        return udzbenikList;
    }

    public void setUdzbenikList(List<Udzbenik> udzbenikList) {
        this.udzbenikList = udzbenikList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (autorId != null ? autorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autor)) {
            return false;
        }
        Autor other = (Autor) object;
        if ((this.autorId == null && other.autorId != null) || (this.autorId != null && !this.autorId.equals(other.autorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Autor[ autorId=" + autorId + " ]";
    }
    
}
