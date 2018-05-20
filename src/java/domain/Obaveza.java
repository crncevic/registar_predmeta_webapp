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
@Table(name = "obaveza")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Obaveza.findAll", query = "SELECT o FROM Obaveza o")
    , @NamedQuery(name = "Obaveza.findByObavezaId", query = "SELECT o FROM Obaveza o WHERE o.obavezaId = :obavezaId")
    , @NamedQuery(name = "Obaveza.findByTip", query = "SELECT o FROM Obaveza o WHERE o.tip = :tip")
    , @NamedQuery(name = "Obaveza.findByNaziv", query = "SELECT o FROM Obaveza o WHERE o.naziv = :naziv")
    , @NamedQuery(name = "Obaveza.findByBrojPoena", query = "SELECT o FROM Obaveza o WHERE o.brojPoena = :brojPoena")})
public class Obaveza implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "obavezaId")
    private Integer obavezaId;
    @Size(max = 100)
    @Column(name = "tip")
    private String tip;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "naziv")
    private String naziv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "broj_poena")
    private int brojPoena;
    @JoinColumn(name = "predmetId", referencedColumnName = "predmetId")
    @ManyToOne
    private Predmet predmetId;

    public Obaveza() {
    }

    public Obaveza(Integer obavezaId) {
        this.obavezaId = obavezaId;
    }

    public Obaveza(Integer obavezaId, String naziv, int brojPoena) {
        this.obavezaId = obavezaId;
        this.naziv = naziv;
        this.brojPoena = brojPoena;
    }

    public Integer getObavezaId() {
        return obavezaId;
    }

    public void setObavezaId(Integer obavezaId) {
        this.obavezaId = obavezaId;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojPoena() {
        return brojPoena;
    }

    public void setBrojPoena(int brojPoena) {
        this.brojPoena = brojPoena;
    }

    public Predmet getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Predmet predmetId) {
        this.predmetId = predmetId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (obavezaId != null ? obavezaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Obaveza)) {
            return false;
        }
        Obaveza other = (Obaveza) object;
        if ((this.obavezaId == null && other.obavezaId != null) || (this.obavezaId != null && !this.obavezaId.equals(other.obavezaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Obaveza[ obavezaId=" + obavezaId + " ]";
    }
    
}
