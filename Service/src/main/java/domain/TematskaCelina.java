/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Petar
 */
@Entity
@Table(name = "tematska_celina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TematskaCelina.findAll", query = "SELECT t FROM TematskaCelina t")
    , @NamedQuery(name = "TematskaCelina.findByTematskacelinaId", query = "SELECT t FROM TematskaCelina t WHERE t.tematskacelinaId = :tematskacelinaId")
    , @NamedQuery(name = "TematskaCelina.findByNaziv", query = "SELECT t FROM TematskaCelina t WHERE t.naziv = :naziv")
    , @NamedQuery(name = "TematskaCelina.findByOpis", query = "SELECT t FROM TematskaCelina t WHERE t.opis = :opis")
    , @NamedQuery(name = "TematskaCelina.findByPredmetId", query = "SELECT t FROM TematskaCelina t WHERE t.predmet.predmetId = :predmetId")
})
public class TematskaCelina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tematska_celinaId")
    private Integer tematskacelinaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "naziv")
    private String naziv;
    @Size(max = 500)
    @Column(name = "opis")
    private String opis;
    @JoinColumn(name = "tip_nastaveId", referencedColumnName = "tip_nastaveId")
    @ManyToOne
    private TipNastave tipNastave;
    @OneToMany(mappedBy = "nadredjenaTematskaCelina")
    private List<TematskaCelina> tematskaCelinaList;
    @JoinColumn(name = "nadredjena_tematska_celinaId", referencedColumnName = "tematska_celinaId")
    @ManyToOne
    private TematskaCelina nadredjenaTematskaCelina;
    @JoinColumn(name = "predmetId", referencedColumnName = "predmetId")
    @ManyToOne
    private Predmet predmet;

  

    public TematskaCelina() {
    }

    public TematskaCelina(Integer tematskacelinaId) {
        this.tematskacelinaId = tematskacelinaId;
    }

    public TematskaCelina(Integer tematskacelinaId, String naziv) {
        this.tematskacelinaId = tematskacelinaId;
        this.naziv = naziv;
    }

    public Integer getTematskacelinaId() {
        return tematskacelinaId;
    }

    public void setTematskacelinaId(Integer tematskacelinaId) {
        this.tematskacelinaId = tematskacelinaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public TipNastave getTipNastave() {
        return tipNastave;
    }

    public void setTipNastave(TipNastave tipNastave) {
        this.tipNastave = tipNastave;
    }

    @XmlTransient
    public List<TematskaCelina> getTematskaCelinaList() {
        return tematskaCelinaList;
    }

    public void setTematskaCelinaList(List<TematskaCelina> tematskaCelinaList) {
        this.tematskaCelinaList = tematskaCelinaList;
    }

    public TematskaCelina getNadredjenaTematskaCelina() {
        return nadredjenaTematskaCelina;
    }

    public void setNadredjenaTematskaCelina(TematskaCelina nadredjenaTematskaCelina) {
        this.nadredjenaTematskaCelina = nadredjenaTematskaCelina;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tematskacelinaId != null ? tematskacelinaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TematskaCelina other = (TematskaCelina) obj;
        if (!Objects.equals(this.tematskacelinaId, other.tematskacelinaId)) {
            return false;
        }
        return true;
    }

   

   

    @Override
    public String toString() {
        return "domain.TematskaCelina[ tematskacelinaId=" + tematskacelinaId + " ]";
    }

}
