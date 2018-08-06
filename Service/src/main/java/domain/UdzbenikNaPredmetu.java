/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 *
 * @author Petar
 */
@Entity
@Table(name="udzbenik_na_predmetu")
@XmlRootElement
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize
@NamedQueries({
    @NamedQuery(name = "UdzbenikNaPredmetu.findByUdzbenikId",query = "SELECT up FROM UdzbenikNaPredmetu up WHERE up.udzbenik.udzbenikId=:udzbenikId"),
    @NamedQuery(name = "UdzbenikNaPredmetu.findByPredmetId",query = "SELECT up FROM UdzbenikNaPredmetu up WHERE up.predmet.predmetId=:predmetId"),
})
public class UdzbenikNaPredmetu implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private UdzbenikNaPredmetuPK udzbenikNaPredmetuPK;

    @JoinColumn(name = "udzbenikId", referencedColumnName = "udzbenikId", insertable = false, updatable = false)
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Udzbenik udzbenik;
    
    @JoinColumn(name = "predmetId", referencedColumnName = "predmetId", insertable = false, updatable = false)
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Predmet predmet;

    public UdzbenikNaPredmetuPK getUdzbenikNaPredmetuPK() {
        return udzbenikNaPredmetuPK;
    }

    public void setUdzbenikNaPredmetuPK(UdzbenikNaPredmetuPK udzbenikNaPredmetuPK) {
        this.udzbenikNaPredmetuPK = udzbenikNaPredmetuPK;
    }

    public Udzbenik getUdzbenik() {
        return udzbenik;
    }

    public void setUdzbenik(Udzbenik udzbenik) {
        this.udzbenik = udzbenik;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.udzbenikNaPredmetuPK);
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
        final UdzbenikNaPredmetu other = (UdzbenikNaPredmetu) obj;
        if (!Objects.equals(this.udzbenikNaPredmetuPK, other.udzbenikNaPredmetuPK)) {
            return false;
        }
        return true;
    }
}
