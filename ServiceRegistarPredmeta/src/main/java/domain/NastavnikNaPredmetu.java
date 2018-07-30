/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Petar
 */
@Entity
@Table(name = "nastavnik_na_predmetu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NastavnikNaPredmetu.findAll", query = "SELECT n FROM NastavnikNaPredmetu n")
    , @NamedQuery(name = "NastavnikNaPredmetu.findByNastavnikId", query = "SELECT n FROM NastavnikNaPredmetu n WHERE n.nastavnikNaPredmetuPK.nastavnikId = :nastavnikId")
    , @NamedQuery(name = "NastavnikNaPredmetu.findByPredmetId", query = "SELECT n FROM NastavnikNaPredmetu n WHERE n.nastavnikNaPredmetuPK.predmetId = :predmetId")
    , @NamedQuery(name = "NastavnikNaPredmetu.findByTipNastaveId", query = "SELECT n FROM NastavnikNaPredmetu n WHERE n.nastavnikNaPredmetuPK.tipNastaveId = :tipNastaveId")})
public class NastavnikNaPredmetu implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NastavnikNaPredmetuPK nastavnikNaPredmetuPK;
    @JoinColumn(name = "nastavnikId", referencedColumnName = "nastavnikId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Nastavnik nastavnik;
    @JoinColumn(name = "tipNastaveId", referencedColumnName = "tip_nastaveId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipNastave tipNastave;
    @JoinColumn(name = "predmetId", referencedColumnName = "predmetId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Predmet predmet;

    public NastavnikNaPredmetu() {
    }

    public NastavnikNaPredmetu(NastavnikNaPredmetuPK nastavnikNaPredmetuPK) {
        this.nastavnikNaPredmetuPK = nastavnikNaPredmetuPK;
    }

    public NastavnikNaPredmetu(int nastavnikId, int predmetId, int tipNastaveId) {
        this.nastavnikNaPredmetuPK = new NastavnikNaPredmetuPK(nastavnikId, predmetId, tipNastaveId);
    }

    public NastavnikNaPredmetuPK getNastavnikNaPredmetuPK() {
        return nastavnikNaPredmetuPK;
    }

    public void setNastavnikNaPredmetuPK(NastavnikNaPredmetuPK nastavnikNaPredmetuPK) {
        this.nastavnikNaPredmetuPK = nastavnikNaPredmetuPK;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public TipNastave getTipNastave() {
        return tipNastave;
    }

    public void setTipNastave(TipNastave tipNastave) {
        this.tipNastave = tipNastave;
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
        hash += (nastavnikNaPredmetuPK != null ? nastavnikNaPredmetuPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NastavnikNaPredmetu)) {
            return false;
        }
        NastavnikNaPredmetu other = (NastavnikNaPredmetu) object;
        if ((this.nastavnikNaPredmetuPK == null && other.nastavnikNaPredmetuPK != null) || (this.nastavnikNaPredmetuPK != null && !this.nastavnikNaPredmetuPK.equals(other.nastavnikNaPredmetuPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.NastavnikNaPredmetu[ nastavnikNaPredmetuPK=" + nastavnikNaPredmetuPK + " ]";
    }

   
    
}
