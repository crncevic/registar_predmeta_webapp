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
import javax.persistence.Id;
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
@Table(name = "uloga_udzbenik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UlogaUdzbenik.findAll", query = "SELECT u FROM UlogaUdzbenik u")
    , @NamedQuery(name = "UlogaUdzbenik.findByUlogaId", query = "SELECT u FROM UlogaUdzbenik u WHERE u.ulogaId = :ulogaId")
    , @NamedQuery(name = "UlogaUdzbenik.findByNaziv", query = "SELECT u FROM UlogaUdzbenik u WHERE u.naziv = :naziv")})
public class UlogaUdzbenik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ulogaId")
    private Integer ulogaId;
    @Size(max = 100)
    @Column(name = "naziv")
    private String naziv;
    @OneToMany(mappedBy = "uloga")
    private List<OsobaUVeziSaUdzbenikom> osobaUVeziSaUdzbenikomList;

    public UlogaUdzbenik() {
    }

    public UlogaUdzbenik(Integer ulogaId) {
        this.ulogaId = ulogaId;
    }

    public Integer getUlogaId() {
        return ulogaId;
    }

    public void setUlogaId(Integer ulogaId) {
        this.ulogaId = ulogaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
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
        hash += (ulogaId != null ? ulogaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UlogaUdzbenik)) {
            return false;
        }
        UlogaUdzbenik other = (UlogaUdzbenik) object;
        if ((this.ulogaId == null && other.ulogaId != null) || (this.ulogaId != null && !this.ulogaId.equals(other.ulogaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.UlogaUdzbenik[ ulogaId=" + ulogaId + " ]";
    }
    
}
