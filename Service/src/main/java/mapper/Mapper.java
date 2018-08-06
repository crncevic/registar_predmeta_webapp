/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import domain.Katedra;
import domain.Korisnik;
import domain.Nastavnik;
import domain.OsobaUVeziSaUdzbenikom;
import domain.Predmet;
import domain.StudijskiProgram;
import domain.TematskaCelina;
import domain.TipNastave;
import domain.Udzbenik;
import domain.Uloga;
import domain.UlogaUdzbenik;
import dto.KatedraDTO;
import dto.KorisnikDTO;
import dto.NastavnikDTO;
import dto.OsobaUVeziSaUdzbenikomDTO;
import dto.PredmetDTO;
import dto.StudijskiProgramDTO;
import dto.TematskaCelinaDTO;
import dto.TipNastaveDTO;
import dto.UdzbenikDTO;
import dto.UlogaDTO;
import dto.UlogaUdzbenikDTO;
import dto.VrstaINivoStudijaDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petar
 */
public class Mapper {

    public static PredmetDTO toPredmetDTO(Predmet predmet, List<Udzbenik> udzbenici, List<Nastavnik> nastavnici, List<StudijskiProgram> stdProgrami, List<TematskaCelina> tematskeCeline) throws Exception {
        try {
            PredmetDTO predmetDTO = new PredmetDTO();

            predmetDTO.setPredmetId(predmet.getPredmetId());
            predmetDTO.setNaziv(predmet.getNaziv());
            predmetDTO.setBrCasovaPredavanjaNedeljno(predmet.getBrCasovaPredavanjaNedeljno());
            predmetDTO.setBrCasovaVezbiNedeljno(predmet.getBrCasovaVezbiNedeljno());
            predmetDTO.setCilj(predmet.getCilj());
            predmetDTO.setUslov(predmet.getUslov());
            predmetDTO.setIshod(predmet.getIshod());
            predmetDTO.setDrugiObliciNastave(predmet.getDrugiObliciNastave());
            predmetDTO.setVrstaINivoStudija(new VrstaINivoStudijaDTO(predmet.getVrstaINivoStudija().getVrstaId(), predmet.getVrstaINivoStudija().getNaziv()));

            List<UdzbenikDTO> udzbeniciDTO = new ArrayList<>();

            if (udzbenici != null) {

                for (Udzbenik u : udzbenici) {
                    udzbeniciDTO.add(toUdzbenikDTO(u, null));
                }
            }

            predmetDTO.setUdzbenici(udzbeniciDTO);

            List<NastavnikDTO> nastavniciDTO = new ArrayList<>();

            if (nastavnici != null) {

                for (Nastavnik nastavnik : nastavnici) {
                    nastavniciDTO.add(toNastavnikDTO(nastavnik));
                }
            }

            predmetDTO.setNastavnici(nastavniciDTO);

            List<TematskaCelinaDTO> tematskeCelineDTO = new ArrayList<>();

            if (tematskeCeline != null) {
                for (TematskaCelina tematskaCelina : tematskeCeline) {
                    tematskeCelineDTO.add(toTematskaCelinaDTO(tematskaCelina, null));
                }
            }

            predmetDTO.setTematskaCeline(tematskeCelineDTO);

            List<StudijskiProgramDTO> studijskiProgramiDTO = new ArrayList<>();

            if (stdProgrami != null) {
                for (StudijskiProgram studijskiProgram : stdProgrami) {
                    studijskiProgramiDTO.add(toStudijskiProgramDTO(studijskiProgram));
                }
            }

            predmetDTO.setStudijskiProgrami(studijskiProgramiDTO);

            return predmetDTO;
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju predmet->predmetDTO {" + e.getMessage() + "}");
        }
    }

    public static UdzbenikDTO toUdzbenikDTO(Udzbenik udzbenik, List<OsobaUVeziSaUdzbenikom> osobaUVeziSaUdzbenikomList) throws Exception {
        try {
            UdzbenikDTO udzbenikDTO = new UdzbenikDTO();
            udzbenikDTO.setNaziv(udzbenik.getNaziv());
            udzbenikDTO.setIzdavac(udzbenik.getIzdavac());
            udzbenikDTO.setUdzbenikId(udzbenik.getUdzbenikId());
            udzbenikDTO.setTiraz(udzbenik.getTiraz());
            udzbenikDTO.setIsbn(udzbenik.getIsbn());
            udzbenikDTO.setRbrIzdanja(udzbenik.getRbrIzdanja());
            udzbenikDTO.setStampa(udzbenik.getStampa());

            List<OsobaUVeziSaUdzbenikomDTO> osobaUVeziSaUdzbenikomDTOList = new ArrayList<>();

            if (osobaUVeziSaUdzbenikomList != null) {

                for (OsobaUVeziSaUdzbenikom osobaUVeziSaUdzbenikom : osobaUVeziSaUdzbenikomList) {
                    osobaUVeziSaUdzbenikomDTOList.add(toOsobaUVeziSaUzbenikDTO(osobaUVeziSaUdzbenikom));
                }
            }

            udzbenikDTO.setOsobaUVeziSaUdzbenikomList(osobaUVeziSaUdzbenikomDTOList);

            return udzbenikDTO;

        } catch (Exception e) {
            throw new Exception("Greska u mapiranju predmet->predmetDTO {" + e.getMessage() + "}");
        }
    }

    public static NastavnikDTO toNastavnikDTO(Nastavnik nastavnik) throws Exception {
        try {
            NastavnikDTO nastavnikDTO = new NastavnikDTO();
            nastavnikDTO.setNastavnikId(nastavnik.getNastavnikId());
            nastavnikDTO.setIme(nastavnik.getIme());
            nastavnikDTO.setPrezime(nastavnik.getPrezime());
            nastavnikDTO.setZvanje(nastavnik.getZvanje());
            nastavnikDTO.setTelefon(nastavnik.getTelefon());
            nastavnikDTO.setePosta(nastavnik.getEPosta());
            nastavnikDTO.setKabinet(nastavnik.getKabinet());

            if (nastavnik.getKatedra() != null) {
                nastavnikDTO.setKatedraDTO(toKatedraDTO(nastavnik.getKatedra()));
            } else {
                nastavnikDTO.setKatedraDTO(new KatedraDTO());
            }

            return nastavnikDTO;

        } catch (Exception ex) {
            throw new Exception("Greska u mapiranju nastavnik->nastavnikDTO {" + ex.getMessage() + "}");
        }
    }

    public static TematskaCelinaDTO toTematskaCelinaDTO(TematskaCelina tematskaCelina, List<TematskaCelina> tematskeCeline) throws Exception {
        try {
            TematskaCelinaDTO tematskaCelinaDTO = new TematskaCelinaDTO();
            tematskaCelinaDTO.setTematskacelinaId(tematskaCelina.getTematskacelinaId());
            tematskaCelinaDTO.setNaziv(tematskaCelina.getNaziv());
            tematskaCelinaDTO.setOpis(tematskaCelina.getOpis());

            if (tematskaCelina.getTipNastave() != null) {
                tematskaCelinaDTO.setTipNastave(toTipNastaveDTO(tematskaCelina.getTipNastave()));
            } else {
                tematskaCelinaDTO.setTipNastave(new TipNastaveDTO());
            }

            List<TematskaCelinaDTO> tematskeCelineDTO = new ArrayList<>();

            if (tematskeCeline != null) {
                for (TematskaCelina tc1 : tematskeCeline) {
                    TematskaCelinaDTO tcDTO1 = new TematskaCelinaDTO();

                    tcDTO1.setTematskacelinaId(tc1.getTematskacelinaId());
                    tcDTO1.setNaziv(tc1.getNaziv());
                    tcDTO1.setOpis(tc1.getOpis());

                    if (tc1.getTipNastave() != null) {
                        tcDTO1.setTipNastave(toTipNastaveDTO(tc1.getTipNastave()));
                    } else {
                        tcDTO1.setTipNastave(new TipNastaveDTO());
                    }
                }
            }

            tematskaCelinaDTO.setTematskaCelinaList(tematskeCelineDTO);

            return tematskaCelinaDTO;
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju nastavnik->nastavnikDTO {" + e.getMessage() + "}");
        }
    }

    public static StudijskiProgramDTO toStudijskiProgramDTO(StudijskiProgram studijskiProgram) throws Exception {
        try {

            StudijskiProgramDTO studijskiProgramDTO = new StudijskiProgramDTO();
            studijskiProgramDTO.setNaziv(studijskiProgram.getNaziv());
            studijskiProgramDTO.setStudijskiProgramId(studijskiProgram.getStudijskiProgramId());

            return studijskiProgramDTO;
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju studisjkiProgram->studijskiProgramDTO {" + e.getMessage() + "}");
        }
    }

    public static OsobaUVeziSaUdzbenikomDTO toOsobaUVeziSaUzbenikDTO(OsobaUVeziSaUdzbenikom osobaUVeziSaUdzbenikom) throws Exception {
        try {
            OsobaUVeziSaUdzbenikomDTO osobaUVeziSaUdzbenikomDTO = new OsobaUVeziSaUdzbenikomDTO();
            osobaUVeziSaUdzbenikomDTO.setIme(osobaUVeziSaUdzbenikom.getIme());
            osobaUVeziSaUdzbenikomDTO.setPrezime(osobaUVeziSaUdzbenikom.getPrezime());
            osobaUVeziSaUdzbenikomDTO.setOsobaId(osobaUVeziSaUdzbenikom.getOsobaId());
            osobaUVeziSaUdzbenikomDTO.setTitula(osobaUVeziSaUdzbenikom.getTitula());

            if (osobaUVeziSaUdzbenikom.getUloga() != null) {
                osobaUVeziSaUdzbenikomDTO.setUlogaDTO(toUlogaUdzbenikDTO(osobaUVeziSaUdzbenikom.getUloga()));
            } else {

                osobaUVeziSaUdzbenikomDTO.setUlogaDTO(new UlogaUdzbenikDTO());
            }

            return osobaUVeziSaUdzbenikomDTO;
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju osobaUVeziSaUdzbenikom->osobaUVeziSaUdzbenikomDTO {" + e.getMessage() + "}");
        }
    }

    public static KatedraDTO toKatedraDTO(Katedra katedra) throws Exception {
        try {
            KatedraDTO katedraDTO = new KatedraDTO();
            katedraDTO.setKatedraId(katedra.getKatedraId());
            katedraDTO.setNaziv(katedra.getNaziv());

            if (katedra.getSef() != null) {
                katedraDTO.setSef(katedra.getSef().getIme() + " " + katedra.getSef().getPrezime());
            } else {
                katedraDTO.setSef("");
            }

            if (katedra.getZamenikSefa() != null) {
                katedraDTO.setZamenikSefa(katedra.getZamenikSefa().getIme() + " " + katedra.getZamenikSefa().getPrezime());
            } else {
                katedraDTO.setZamenikSefa("");
            }

            if (katedra.getSekretar() != null) {
                katedraDTO.setSekretar(katedra.getSekretar().getIme() + " " + katedra.getSekretar().getPrezime());
            } else {
                katedraDTO.setSekretar("");
            }

            return katedraDTO;
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju tematskaCelina->tematskaCelinaDTO {" + e.getMessage() + "}");
        }
    }

    public static TipNastaveDTO toTipNastaveDTO(TipNastave tipNastave) throws Exception {
        try {

            TipNastaveDTO tipNastaveDTO = new TipNastaveDTO();
            tipNastaveDTO.setNaziv(tipNastave.getNaziv());
            tipNastaveDTO.setTipnastaveId(tipNastave.getTipnastaveId());

            return tipNastaveDTO;
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju tipNastave->tipNastaveDTO {" + e.getMessage() + "}");
        }
    }

    public static UlogaUdzbenikDTO toUlogaUdzbenikDTO(UlogaUdzbenik uloga) throws Exception {
        try {
            UlogaUdzbenikDTO ulogaUdzbenikDTO = new UlogaUdzbenikDTO();
            ulogaUdzbenikDTO.setUlogaId(uloga.getUlogaId());
            ulogaUdzbenikDTO.setNaziv(uloga.getNaziv());

            return ulogaUdzbenikDTO;
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju ulogaUdzbenik->ulogaUdzbenikDTO {" + e.getMessage() + "}");
        }
    }

    public static KorisnikDTO toKorisnikDTO(Korisnik korisnik) throws Exception {
        try {
            KorisnikDTO korisnikDTO = new KorisnikDTO();
            korisnikDTO.setKorisnikId(korisnik.getKorisnikId());
            korisnikDTO.setIme(korisnik.getIme());
            korisnikDTO.setPrezime(korisnik.getPrezime());
            korisnikDTO.setUsername(korisnik.getUsername());
            korisnikDTO.setPassword(korisnik.getPassword());

            if (korisnik.getUloga() != null) {
                korisnikDTO.setUlogaDTO(toUlogaDTO(korisnik.getUloga()));
            } else {
                korisnikDTO.setUlogaDTO(new UlogaDTO());
            }

            return korisnikDTO;
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju korisnik->korisnikDTO {" + e.getMessage() + "}");
        }
    }

    private static UlogaDTO toUlogaDTO(Uloga uloga) throws Exception {
        try {
            UlogaDTO ulogaDTO = new UlogaDTO();
            ulogaDTO.setUlogaId(uloga.getUlogaId());
            ulogaDTO.setNaziv(uloga.getNaziv());

            return ulogaDTO;
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju uloga->ulogaDTO {" + e.getMessage() + "}");
        }
    }
}
