/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "predmet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Predmet.findAll", query = "SELECT p FROM Predmet p")
    , @NamedQuery(name = "Predmet.findByPredmetId", query = "SELECT p FROM Predmet p WHERE p.predmetId = :predmetId")
    , @NamedQuery(name = "Predmet.findByNaziv", query = "SELECT p FROM Predmet p WHERE p.naziv = :naziv")
    , @NamedQuery(name = "Predmet.findByBrCasovaPredavanjaNedeljno", query = "SELECT p FROM Predmet p WHERE p.brCasovaPredavanjaNedeljno = :brCasovaPredavanjaNedeljno")
    , @NamedQuery(name = "Predmet.findByBrCasovaVezbiNedeljno", query = "SELECT p FROM Predmet p WHERE p.brCasovaVezbiNedeljno = :brCasovaVezbiNedeljno")
    , @NamedQuery(name = "Predmet.findByOstaliCasovi", query = "SELECT p FROM Predmet p WHERE p.ostaliCasovi = :ostaliCasovi")
    , @NamedQuery(name = "Predmet.findByDrugiObliciNastave", query = "SELECT p FROM Predmet p WHERE p.drugiObliciNastave = :drugiObliciNastave")
    , @NamedQuery(name = "Predmet.findByStudijskiIstrazivackiRad", query = "SELECT p FROM Predmet p WHERE p.studijskiIstrazivackiRad = :studijskiIstrazivackiRad")
    , @NamedQuery(name = "Predmet.findByCilj", query = "SELECT p FROM Predmet p WHERE p.cilj = :cilj")
    , @NamedQuery(name = "Predmet.findByIshod", query = "SELECT p FROM Predmet p WHERE p.ishod = :ishod")
    , @NamedQuery(name = "Predmet.findByUslov", query = "SELECT p FROM Predmet p WHERE p.uslov = :uslov")
    , @NamedQuery(name = "Predmet.findBySadrzajTekst", query = "SELECT p FROM Predmet p WHERE p.sadrzajTekst = :sadrzajTekst")})
public class Predmet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "predmetId")
    private Integer predmetId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "naziv")
    private String naziv;
    @Column(name = "br_casova_predavanja_nedeljno")
    private Integer brCasovaPredavanjaNedeljno;
    @Column(name = "br_casova_vezbi_nedeljno")
    private Integer brCasovaVezbiNedeljno;
    @Column(name = "ostali_casovi")
    private Integer ostaliCasovi;
    @Size(max = 200)
    @Column(name = "drugi_oblici_nastave")
    private String drugiObliciNastave;
    @Size(max = 300)
    @Column(name = "studijski_istrazivacki_rad")
    private String studijskiIstrazivackiRad;
    @Size(max = 500)
    @Column(name = "cilj")
    private String cilj;
    @Size(max = 500)
    @Column(name = "ishod")
    private String ishod;
    @Size(max = 250)
    @Column(name = "uslov")
    private String uslov;
    @Size(max = 1000)
    @Column(name = "sadrzaj_tekst")
    private String sadrzajTekst;
    @ManyToMany(mappedBy = "predmetList")
    private List<Udzbenik> udzbenikList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "predmet")
    private List<NastavnikNaPredmetu> nastavnikNaPredmetuList;
    @OneToMany(mappedBy = "predmetId")
    private List<TematskaCelina> tematskaCelinaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "predmet")
    private List<PredmetNaStudijskomProgramu> predmetNaStudijskomProgramuList;
    @JoinColumn(name = "vrsta_i_nivo_studija", referencedColumnName = "vrstaId")
    @ManyToOne
    private VrstaINivoStudija vrstaINivoStudija;

    public Predmet() {
    }

    public Predmet(Integer predmetId) {
        this.predmetId = predmetId;
    }

    public Predmet(Integer predmetId, String naziv) {
        this.predmetId = predmetId;
        this.naziv = naziv;
    }

    public Integer getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Integer predmetId) {
        this.predmetId = predmetId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getBrCasovaPredavanjaNedeljno() {
        return brCasovaPredavanjaNedeljno;
    }

    public void setBrCasovaPredavanjaNedeljno(Integer brCasovaPredavanjaNedeljno) {
        this.brCasovaPredavanjaNedeljno = brCasovaPredavanjaNedeljno;
    }

    public Integer getBrCasovaVezbiNedeljno() {
        return brCasovaVezbiNedeljno;
    }

    public void setBrCasovaVezbiNedeljno(Integer brCasovaVezbiNedeljno) {
        this.brCasovaVezbiNedeljno = brCasovaVezbiNedeljno;
    }

    public Integer getOstaliCasovi() {
        return ostaliCasovi;
    }

    public void setOstaliCasovi(Integer ostaliCasovi) {
        this.ostaliCasovi = ostaliCasovi;
    }

    public String getDrugiObliciNastave() {
        return drugiObliciNastave;
    }

    public void setDrugiObliciNastave(String drugiObliciNastave) {
        this.drugiObliciNastave = drugiObliciNastave;
    }

    public String getStudijskiIstrazivackiRad() {
        return studijskiIstrazivackiRad;
    }

    public void setStudijskiIstrazivackiRad(String studijskiIstrazivackiRad) {
        this.studijskiIstrazivackiRad = studijskiIstrazivackiRad;
    }

    public String getCilj() {
        return cilj;
    }

    public void setCilj(String cilj) {
        this.cilj = cilj;
    }

    public String getIshod() {
        return ishod;
    }

    public void setIshod(String ishod) {
        this.ishod = ishod;
    }

    public String getUslov() {
        return uslov;
    }

    public void setUslov(String uslov) {
        this.uslov = uslov;
    }

    public String getSadrzajTekst() {
        return sadrzajTekst;
    }

    public void setSadrzajTekst(String sadrzajTekst) {
        this.sadrzajTekst = sadrzajTekst;
    }

    @XmlTransient
    public List<Udzbenik> getUdzbenikList() {
        return udzbenikList;
    }

    public void setUdzbenikList(List<Udzbenik> udzbenikList) {
        this.udzbenikList = udzbenikList;
    }

    @XmlTransient
    public List<NastavnikNaPredmetu> getNastavnikNaPredmetuList() {
        return nastavnikNaPredmetuList;
    }

    public void setNastavnikNaPredmetuList(List<NastavnikNaPredmetu> nastavnikNaPredmetuList) {
        this.nastavnikNaPredmetuList = nastavnikNaPredmetuList;
    }

    @XmlTransient
    public List<TematskaCelina> getTematskaCelinaList() {
        return tematskaCelinaList;
    }

    public void setTematskaCelinaList(List<TematskaCelina> tematskaCelinaList) {
        this.tematskaCelinaList = tematskaCelinaList;
    }

    @XmlTransient
    public List<PredmetNaStudijskomProgramu> getPredmetNaStudijskomProgramuList() {
        return predmetNaStudijskomProgramuList;
    }

    public void setPredmetNaStudijskomProgramuList(List<PredmetNaStudijskomProgramu> predmetNaStudijskomProgramuList) {
        this.predmetNaStudijskomProgramuList = predmetNaStudijskomProgramuList;
    }

    public VrstaINivoStudija getVrstaINivoStudija() {
        return vrstaINivoStudija;
    }

    public void setVrstaINivoStudija(VrstaINivoStudija vrstaINivoStudija) {
        this.vrstaINivoStudija = vrstaINivoStudija;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (predmetId != null ? predmetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Predmet)) {
            return false;
        }
        Predmet other = (Predmet) object;
        if ((this.predmetId == null && other.predmetId != null) || (this.predmetId != null && !this.predmetId.equals(other.predmetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Predmet[ predmetId=" + predmetId + " ]";
    }
    
}
