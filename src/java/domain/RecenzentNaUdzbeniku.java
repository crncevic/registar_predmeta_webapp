/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Petar
 */
@Entity
@Table(name = "recenzent_na_udzbeniku")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecenzentNaUdzbeniku.findAll", query = "SELECT r FROM RecenzentNaUdzbeniku r")
    , @NamedQuery(name = "RecenzentNaUdzbeniku.findByRecenzentId", query = "SELECT r FROM RecenzentNaUdzbeniku r WHERE r.recenzentNaUdzbenikuPK.recenzentId = :recenzentId")
    , @NamedQuery(name = "RecenzentNaUdzbeniku.findByUdzbenikId", query = "SELECT r FROM RecenzentNaUdzbeniku r WHERE r.recenzentNaUdzbenikuPK.udzbenikId = :udzbenikId")})
public class RecenzentNaUdzbeniku implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RecenzentNaUdzbenikuPK recenzentNaUdzbenikuPK;

    public RecenzentNaUdzbeniku() {
    }

    public RecenzentNaUdzbeniku(RecenzentNaUdzbenikuPK recenzentNaUdzbenikuPK) {
        this.recenzentNaUdzbenikuPK = recenzentNaUdzbenikuPK;
    }

    public RecenzentNaUdzbeniku(int recenzentId, int udzbenikId) {
        this.recenzentNaUdzbenikuPK = new RecenzentNaUdzbenikuPK(recenzentId, udzbenikId);
    }

    public RecenzentNaUdzbenikuPK getRecenzentNaUdzbenikuPK() {
        return recenzentNaUdzbenikuPK;
    }

    public void setRecenzentNaUdzbenikuPK(RecenzentNaUdzbenikuPK recenzentNaUdzbenikuPK) {
        this.recenzentNaUdzbenikuPK = recenzentNaUdzbenikuPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recenzentNaUdzbenikuPK != null ? recenzentNaUdzbenikuPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecenzentNaUdzbeniku)) {
            return false;
        }
        RecenzentNaUdzbeniku other = (RecenzentNaUdzbeniku) object;
        if ((this.recenzentNaUdzbenikuPK == null && other.recenzentNaUdzbenikuPK != null) || (this.recenzentNaUdzbenikuPK != null && !this.recenzentNaUdzbenikuPK.equals(other.recenzentNaUdzbenikuPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.RecenzentNaUdzbeniku[ recenzentNaUdzbenikuPK=" + recenzentNaUdzbenikuPK + " ]";
    }
    
}
