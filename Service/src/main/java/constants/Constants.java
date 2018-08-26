/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constants;

/**
 *
 * @author Petar
 */
public class Constants {

    //*************************
    //NAMED QUERIES CONSTANTS *
    //*************************
    //katedra
    public static final String KATEDRA_FIND_ALL = "Katedra.findAll";
    public static final String KATEDRA_FIND_BY_ID = "Katedra.findByKatedraId";

    //korisnik
    public static final String KORISNIK_FIND_ALL = "Korisnik.findAll";
    public static final String KORISNIK_FIND_BY_ID = "Korisnik.findByKorisnikId";
    public static final String KORISNIK_FIND_BY_USERNAME = "Korisnik.findByUsername";

    //nastavnik
    public static final String NASTAVNIK_FIND_ALL = "Nastavnik.findAll";
    public static final String NASTAVNIK_FIND_BY_ID = "Nastavnik.findByNastavnikId";

    //nastavnik na predmetu
    public static final String NASTAVNIK_NA_PREDMETU_FIND_ALL_BY_NASTAVNIK_ID = "NastavnikNaPredmetu.findByNastavnikId";
    public static final String NASTAVNIK_NA_PREDMETU_FIND_ALL_BY_PREDMET_ID = "NastavnikNaPredmetu.findByPredmetId";
    public static final String NASTAVNIK_NA_PREDMETU_FIND_ALL_BY_TIP_NASTAVE_ID = "NastavnikNaPredmetu.findByTipNastaveId";
    public static final String NASTAVNIK_NA_PREDMETU_DELETE = "NastavnikNaPredmetu.delete";
    
    //osoba u vezi sa udzbenikom
    public static final String OSOBA_U_VEZI_SA_UDZBENIKOM_FIND_BY_OSOBA_ID = "OsobaUVeziSaUdzbenikom.findByOsobaId";
    public static final String OSOBA_U_VEZI_SA_UDZBENIKOM_FIND_ALL = "OsobaUVeziSaUdzbenikom.findAll";
    public static final String OSOBA_U_VEZI_SA_UDZBENIKOM_FIND_BY_UDZBENIK_ID = "OsobaUVeziSaUdzbenikom.findByUdzbenikId";

    //predmet
    public static final String PREDMET_FIND_ALL = "Predmet.findAll";
    public static final String PREDMET_FIND_BY_ID = "Predmet.findByPredmetId";
    public static final String PREDMET_FIND_BY_NAZIV = "Predmet.findByNaziv";

    //predmet na studisjkom programu
    public static final String PREDMET_NA_STUDIJSKOM_PROGRAMU_FIND_BY_PREDMET_ID = "PredmetNaStudijskomProgramu.findByPredmetId";
    public static final String PREDMET_NA_STUDIJSKOM_PROGRAMU_DELETE ="PredmetNaStudijskomProgramu.delete";
    //status
    public static final String STATUS_FIND_ALL = "Status.findAll";
    public static final String STATUS_FIND_BY_ID = "Status.findByStatusId";

    //studijski program
    public static final String STUDIJSKI_PROGRAM_FIND_ALL = "StudijskiProgram.findAll";
    public static final String STUDIJSKI_PROGRAM_FIND_BY_ID = "StudijskiProgram.findByStudijskiProgramId";

    //tematska celina
    public static final String TEMATSKA_CELINA_FIND_ALL = "TematskaCelina.findAll";
    public static final String TEMATSKA_CELINA_FIND_BY_ID = "TematskaCelina.findByTematskacelinaId";
    public static final String TEMATSKA_CELINA_FIND_BY_PREDMET_ID = "TematskaCelina.findByPredmetId";

    //tip nastave
    public static final String TIP_NASTAVE_FIND_ALL = "TipNastave.findAll";
    public static final String TIP_NASTAVE_FIND_BY_ID = "TipNastave.findByTipnastaveId";

    //udzbenik 
    public static final String UDZBENIK_FIND_ALL = "Udzbenik.findAll";
    public static final String UDZBENIK_FIND_BY_ID = "Udzbenik.findByUdzbenikId";
    public static final String UDZBENIK_FIND_BY_NAZIV = "Udzbenik.findByNaziv";

    //uloga
    public static final String ULOGA_FIND_ALL = "Uloga.findAll";
    public static final String ULOGA_FIND_BY_ID = "Uloga.findByUlogaId";

    //uloga udzbenik
    public static final String ULOGA_UDZBENIK_FIND_ALL = "UlogaUdzbenik.findAll";
    public static final String ULOGA_UDZBENIK_FIND_BY_ID = "UlogaUdzbenik.findByUlogaId";

    //vrsta i nivo studija
    public static final String VRSTA_I_NIVO_STUDIJA_FIND_ALL = "VrstaINivoStudija.findAll";
    public static final String VRSTA_I_NIVO_STUDIJA_FIND_BY_ID = "UlogaUdzbenik.findByVrstaId";

    //udzbenik na predmetu
    public static final String UDZBENIK_NA_PREDMETU_FIND_ALL_BY_UDZBENIK_ID = "UdzbenikNaPredmetu.findByUdzbenikId";
    public static final String UDZBENIK_NA_PREDMETU_FIND_ALL_BY_PREDMET_ID = "UdzbenikNaPredmetu.findByPredmetId";
    public static final String  UDZBENIK_NA_PREDMETU_DELETE = "UdzbenikNaPredmetu.delete";

    //************************************
    //PARAMS FOR NAMED QUERIES CONSTANTS *
    //************************************ 
    public static final String KATEDRA_ID = "katedraId";
    public static final String KORISNIK_ID = "korisnikId";
    public static final String KORISNIK_USERNAME = "username";
    public static final String NASTAVNIK_ID = "nastavnikId";
    public static final String PREDMET_ID = "predmetId";
    public static final String PREDMET_NAZIV = "naziv";
    public static final String TIP_NASTAVE_ID = "tipnastaveId";
    public static final String OSOBA_ID = "osobaId";
    public static final String STATUS_ID = "statusId";
    public static final String STUDIJSKI_PROGRAM_ID = "studijskiProgramId";
    public static final String TEMATSKA_CELINA_ID = "tematskacelinaId";
    public static final String UDZBENIK_ID = "udzbenikId";
    public static final String UDZBENIK_NAZIV = "naziv";
    public static final String ULOGA_ID = "ulogaId";
    public static final String VRSTA_ID = "vrstaId";
}
