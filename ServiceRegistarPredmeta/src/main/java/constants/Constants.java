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

    //Named queries
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

    //osoba u vezi sa udzbenikom
    public static final String OSOBA_U_VEZI_SA_UDZBENIKOM_FIND_BY_OSOBA_ID = "OsobaUVeziSaUdzbenikom.findByOsobaId";
    public static final String OSOBA_U_VEZI_SA_UDZBENIKOM_FIND_ALL = "OsobaUVeziSaUdzbenikom.findAll";

    //predmet
    public static final String PREMDET_FIND_ALL = "Predmet.findAll";
    public static final String PREMDET_FIND_BY_ID = "Predmet.findByPredmetId";

    //status
    public static final String STATUS_FIND_ALL = "Status.findAll";
    public static final String STATUS_FIND_BY_ID = "Status.findByStatusId";

    //studijski program
    public static final String STUDIJSKI_PROGRAM_FIND_ALL = "StudijskiProgram.findAll";
    public static final String STUDIJSKI_PROGRAM_FIND_BY_ID = "StudijskiProgram.findByStudijskiProgramId";
    
    //tematska celina
    
    public static final String TEMATSKA_CELINA_FIND_ALL = "TematskaCelina.findAll";
    public static final String TEMATSKA_CELINA_FIND_BY_ID = "TematskaCelina.findByTematskacelinaId";
}
