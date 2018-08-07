/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Petar
 */
public class PredmetDTO {

    private Integer predmetId;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    private String naziv;
    private Integer brCasovaPredavanjaNedeljno;
    private Integer brCasovaVezbiNedeljno;
    private Integer ostaliCasovi;
    @Size(max = 200)
    private String drugiObliciNastave;
    @Size(max = 300)
    private String studijskiIstrazivackiRad;
    @Size(max = 500)
    private String cilj;
    @Size(max = 500)
    private String ishod;
    @Size(max = 250)
    private String uslov;
    @Size(max = 1000)
    @Column(name = "sadrzaj_tekst")
    private String sadrzajTekst;
    private List<UdzbenikDTO> udzbenici;
    private List<NastavnikNaPredmetuDTO> nastavniciNaPredmetu;
   
    private List<TematskaCelinaDTO> tematskaCeline;
    private List<PredmetNaStudijskomProgramuDTO> predmetiNaStudijskimProgramima;

    private VrstaINivoStudijaDTO vrstaINivoStudija;

    private KorisnikDTO korisnikId;

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

    public List<UdzbenikDTO> getUdzbenici() {
        return udzbenici;
    }

    public void setUdzbenici(List<UdzbenikDTO> udzbenici) {
        this.udzbenici = udzbenici;
    }

    public List<NastavnikNaPredmetuDTO> getNastavnici() {
        return nastavniciNaPredmetu;
    }

    public void setNastavnici(List<NastavnikNaPredmetuDTO> nastavnici) {
        this.nastavniciNaPredmetu = nastavnici;
    }

    public List<TematskaCelinaDTO> getTematskaCeline() {
        return tematskaCeline;
    }

    public void setTematskaCeline(List<TematskaCelinaDTO> tematskaCeline) {
        this.tematskaCeline = tematskaCeline;
    }

    public List<PredmetNaStudijskomProgramuDTO> getPredmetiNaStudijskimProgramima() {
        return predmetiNaStudijskimProgramima;
    }

    public void setPredmetiNaStudijskimProgramima(List<PredmetNaStudijskomProgramuDTO> studijskiProgrami) {
        this.predmetiNaStudijskimProgramima = studijskiProgrami;
    }

    public VrstaINivoStudijaDTO getVrstaINivoStudija() {
        return vrstaINivoStudija;
    }

    public void setVrstaINivoStudija(VrstaINivoStudijaDTO vrstaINivoStudija) {
        this.vrstaINivoStudija = vrstaINivoStudija;
    }

    public KorisnikDTO getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(KorisnikDTO korisnikId) {
        this.korisnikId = korisnikId;
    }

}
