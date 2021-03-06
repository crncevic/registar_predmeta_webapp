/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import domain.Katedra;
import domain.Korisnik;
import domain.Nastavnik;
import domain.NastavnikNaPredmetu;
import domain.OsobaUVeziSaUdzbenikom;
import domain.Predmet;
import domain.PredmetNaStudijskomProgramu;
import domain.PredmetNaStudijskomProgramuPK;
import domain.Status;
import domain.StudijskiProgram;
import domain.TematskaCelina;
import domain.TipNastave;
import domain.Udzbenik;
import domain.UdzbenikNaPredmetu;
import domain.UdzbenikNaPredmetuPK;
import domain.Uloga;
import domain.UlogaUdzbenik;
import domain.VrstaINivoStudija;
import dto.KatedraDTO;
import dto.KorisnikDTO;
import dto.NastavnikDTO;
import dto.NastavnikNaPredmetuDTO;
import dto.OsobaUVeziSaUdzbenikomDTO;
import dto.PredmetDTO;
import dto.PredmetNaStudijskomProgramuDTO;
import dto.StatusDTO;
import dto.StudijskiProgramDTO;
import dto.TematskaCelinaDTO;
import dto.TipNastaveDTO;
import dto.UdzbenikDTO;
import dto.UdzbenikNaPredmetuDTO;
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

    //*****************
    // ENTITY -> DTO  *
    //*****************
    public static PredmetDTO toPredmetDTO(Predmet predmet) throws Exception {
        try {
            if (predmet != null) {
                PredmetDTO predmetDTO = new PredmetDTO();

                predmetDTO.setPredmetId(predmet.getPredmetId());
                predmetDTO.setNaziv(predmet.getNaziv());
                predmetDTO.setBrCasovaPredavanjaNedeljno(predmet.getBrCasovaPredavanjaNedeljno());
                predmetDTO.setBrCasovaVezbiNedeljno(predmet.getBrCasovaVezbiNedeljno());
                predmetDTO.setOstaliCasovi(predmet.getOstaliCasovi());
                predmetDTO.setCilj(predmet.getCilj());
                predmetDTO.setUslov(predmet.getUslov());
                predmetDTO.setIshod(predmet.getIshod());
                predmetDTO.setDrugiObliciNastave(predmet.getDrugiObliciNastave());
                predmetDTO.setStudijskiIstrazivackiRad(predmet.getStudijskiIstrazivackiRad());
                predmetDTO.setSadrzajTekst(predmet.getSadrzajTekst());
                predmetDTO.setVrstaINivoStudija(
                        new VrstaINivoStudijaDTO(predmet.getVrstaINivoStudija() == null ? 0 : predmet.getVrstaINivoStudija().getVrstaId(),
                                predmet.getVrstaINivoStudija() == null ? "" : predmet.getVrstaINivoStudija().getNaziv()));

                List<UdzbenikNaPredmetuDTO> udzbeniciDTO = new ArrayList<>();

                if (predmet.getUdzbenikList() != null) {

                    for (UdzbenikNaPredmetu u : predmet.getUdzbenikList()) {
                        udzbeniciDTO.add(toUdzbenikNaPredmetuDTO(u));
                    }
                }

                predmetDTO.setUdzbenici(udzbeniciDTO);

                List<NastavnikNaPredmetuDTO> nastavniciDTO = new ArrayList<>();

                if (predmet.getNastavnikNaPredmetuList() != null) {

                    for (NastavnikNaPredmetu nastavnikNaPredmetu : predmet.getNastavnikNaPredmetuList()) {

                        nastavniciDTO.add(toNastavnikNaPredmetuDTO(nastavnikNaPredmetu));
                    }
                }

                predmetDTO.setNastavnici(nastavniciDTO);

                List<TematskaCelinaDTO> tematskeCelineDTO = new ArrayList<>();

                if (predmet.getTematskaCelinaList() != null) {
                    for (TematskaCelina tematskaCelina : predmet.getTematskaCelinaList()) {
                        tematskeCelineDTO.add(toTematskaCelinaDTO(tematskaCelina));
                    }
                }

                predmetDTO.setTematskaCeline(tematskeCelineDTO);

                List<PredmetNaStudijskomProgramuDTO> studijskiProgramiDTO = new ArrayList<>();

                if (predmet.getPredmetNaStudijskomProgramuList() != null) {
                    for (PredmetNaStudijskomProgramu pnsp : predmet.getPredmetNaStudijskomProgramuList()) {
                        studijskiProgramiDTO.add(toPredmetNaStudijskomProgramuDTO(pnsp));
                    }
                }

                predmetDTO.setPredmetiNaStudijskimProgramima(studijskiProgramiDTO);

                return predmetDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju predmet->predmetDTO {" + e.getMessage() + "}");
        }
    }

    public static UdzbenikDTO toUdzbenikDTO(Udzbenik udzbenik) throws Exception {
        try {
            if (udzbenik != null) {
                UdzbenikDTO udzbenikDTO = new UdzbenikDTO();
                udzbenikDTO.setUdzbenikId(udzbenik.getUdzbenikId());
                udzbenikDTO.setNaziv(udzbenik.getNaziv());
                udzbenikDTO.setIzdavac(udzbenik.getIzdavac());
                udzbenikDTO.setUdzbenikId(udzbenik.getUdzbenikId());
                udzbenikDTO.setTiraz(udzbenik.getTiraz());
                udzbenikDTO.setIsbn(udzbenik.getIsbn());
                udzbenikDTO.setRbrIzdanja(udzbenik.getRbrIzdanja());
                udzbenikDTO.setStampa(udzbenik.getStampa());
                udzbenikDTO.setGodinaIzdanja(udzbenik.getGodinaIzdanja());

                List<OsobaUVeziSaUdzbenikomDTO> osobaUVeziSaUdzbenikomDTOList = new ArrayList<>();

                if (udzbenik.getOsobaUVeziSaUdzbenikomList() != null) {

                    for (OsobaUVeziSaUdzbenikom osobaUVeziSaUdzbenikom : udzbenik.getOsobaUVeziSaUdzbenikomList()) {
                        osobaUVeziSaUdzbenikomDTOList.add(toOsobaUVeziSaUzbenikDTO(osobaUVeziSaUdzbenikom));
                    }
                }

                udzbenikDTO.setOsobaUVeziSaUdzbenikomList(osobaUVeziSaUdzbenikomDTOList);

                return udzbenikDTO;
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new Exception("Greska u mapiranju udzbenik->udzbenikDTO {" + e.getMessage() + "}");
        }
    }

    public static NastavnikDTO toNastavnikDTO(Nastavnik nastavnik) throws Exception {
        try {
            if (nastavnik != null) {
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
            } else {
                return null;
            }

        } catch (Exception ex) {
            throw new Exception("Greska u mapiranju nastavnik->nastavnikDTO {" + ex.getMessage() + "}");
        }
    }

    public static TematskaCelinaDTO toTematskaCelinaDTO(TematskaCelina tematskaCelina) throws Exception {
        try {
            if (tematskaCelina != null) {
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

                if (tematskaCelina.getTematskaCelinaList() != null) {
                    for (TematskaCelina tc1 : tematskaCelina.getTematskaCelinaList()) {
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
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju nastavnik->nastavnikDTO {" + e.getMessage() + "}");
        }
    }

    public static StudijskiProgramDTO toStudijskiProgramDTO(StudijskiProgram studijskiProgram) throws Exception {
        try {
            if (studijskiProgram != null) {
                StudijskiProgramDTO studijskiProgramDTO = new StudijskiProgramDTO();
                studijskiProgramDTO.setNaziv(studijskiProgram.getNaziv());
                studijskiProgramDTO.setStudijskiProgramId(studijskiProgram.getStudijskiProgramId());

                return studijskiProgramDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju studisjkiProgram->studijskiProgramDTO {" + e.getMessage() + "}");
        }
    }

    public static OsobaUVeziSaUdzbenikomDTO toOsobaUVeziSaUzbenikDTO(OsobaUVeziSaUdzbenikom osobaUVeziSaUdzbenikom) throws Exception {
        try {
            if (osobaUVeziSaUdzbenikom != null) {
                OsobaUVeziSaUdzbenikomDTO osobaUVeziSaUdzbenikomDTO = new OsobaUVeziSaUdzbenikomDTO();
                osobaUVeziSaUdzbenikomDTO.setIme(osobaUVeziSaUdzbenikom.getIme());
                osobaUVeziSaUdzbenikomDTO.setPrezime(osobaUVeziSaUdzbenikom.getPrezime());
                osobaUVeziSaUdzbenikomDTO.setOsobaId(osobaUVeziSaUdzbenikom.getOsobaId());
                osobaUVeziSaUdzbenikomDTO.setTitula(osobaUVeziSaUdzbenikom.getTitula());
                osobaUVeziSaUdzbenikomDTO.setUdzbenikId(osobaUVeziSaUdzbenikom.getUdzbenik().getUdzbenikId());

                if (osobaUVeziSaUdzbenikom.getUloga() != null) {
                    osobaUVeziSaUdzbenikomDTO.setUlogaDTO(toUlogaUdzbenikDTO(osobaUVeziSaUdzbenikom.getUloga()));
                } else {

                    osobaUVeziSaUdzbenikomDTO.setUlogaDTO(new UlogaUdzbenikDTO());
                }

                return osobaUVeziSaUdzbenikomDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju osobaUVeziSaUdzbenikom->osobaUVeziSaUdzbenikomDTO {" + e.getMessage() + "}");
        }
    }

    public static KatedraDTO toKatedraDTO(Katedra katedra) throws Exception {
        try {
            if (katedra != null) {

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
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju tematskaCelina->tematskaCelinaDTO {" + e.getMessage() + "}");
        }
    }

    public static TipNastaveDTO toTipNastaveDTO(TipNastave tipNastave) throws Exception {
        try {
            if (tipNastave != null) {
                TipNastaveDTO tipNastaveDTO = new TipNastaveDTO();
                tipNastaveDTO.setNaziv(tipNastave.getNaziv());
                tipNastaveDTO.setTipnastaveId(tipNastave.getTipnastaveId());

                return tipNastaveDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju tipNastave->tipNastaveDTO {" + e.getMessage() + "}");
        }
    }

    public static UlogaUdzbenikDTO toUlogaUdzbenikDTO(UlogaUdzbenik ulogaUdzbenik) throws Exception {
        try {
            if (ulogaUdzbenik != null) {
                UlogaUdzbenikDTO ulogaUdzbenikDTO = new UlogaUdzbenikDTO();
                ulogaUdzbenikDTO.setUlogaId(ulogaUdzbenik.getUlogaId());
                ulogaUdzbenikDTO.setNaziv(ulogaUdzbenik.getNaziv());

                return ulogaUdzbenikDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju ulogaUdzbenik->ulogaUdzbenikDTO {" + e.getMessage() + "}");
        }
    }

    public static KorisnikDTO toKorisnikDTO(Korisnik korisnik) throws Exception {
        try {
            if (korisnik != null) {
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
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju korisnik->korisnikDTO {" + e.getMessage() + "}");
        }
    }

    public static UlogaDTO toUlogaDTO(Uloga uloga) throws Exception {
        try {
            if (uloga != null) {
                UlogaDTO ulogaDTO = new UlogaDTO();
                ulogaDTO.setUlogaId(uloga.getUlogaId());
                ulogaDTO.setNaziv(uloga.getNaziv());

                return ulogaDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju uloga->ulogaDTO {" + e.getMessage() + "}");
        }
    }

    public static PredmetNaStudijskomProgramuDTO toPredmetNaStudijskomProgramuDTO(PredmetNaStudijskomProgramu pnsp) throws Exception {
        try {
            if (pnsp != null) {
                PredmetNaStudijskomProgramuDTO predmetNaStudijskomProgramuDTO = new PredmetNaStudijskomProgramuDTO();
                if (pnsp.getPredmetNaStudijskomProgramuPK() != null) {
                    predmetNaStudijskomProgramuDTO.setPredmetId(pnsp.getPredmetNaStudijskomProgramuPK().getPredmetId());
                    predmetNaStudijskomProgramuDTO.setStudijskiProgramId(pnsp.getPredmetNaStudijskomProgramuPK().getStudijskiprogramId());
                    predmetNaStudijskomProgramuDTO.setStudijskiProgramDTO(toStudijskiProgramDTO(pnsp.getStudijskiProgram()));
                }
                    predmetNaStudijskomProgramuDTO.setStatusDTO(toStatusDTO(pnsp.getStatus()));
                    predmetNaStudijskomProgramuDTO.setEspb(pnsp.getEspb() != null ? pnsp.getEspb() : 0);
                    predmetNaStudijskomProgramuDTO.setSemestar(pnsp.getSemestar());
                    predmetNaStudijskomProgramuDTO.setStudijskiProgramDTO(toStudijskiProgramDTO(pnsp.getStudijskiProgram()));
                    predmetNaStudijskomProgramuDTO.setNazivPredmeta(pnsp.getPredmet()!=null ? pnsp.getPredmet().getNaziv() : "");
                    return predmetNaStudijskomProgramuDTO;
                } else {
                    return null;
                }

            }catch (Exception e) {
            throw new Exception("Greska u mapiranju PredmetNaStudijskomProgramu->PredmetNaStudijskomProgramuDTO {" + e.getMessage() + "}");
        }
        }
    

    

    

    public static NastavnikNaPredmetuDTO toNastavnikNaPredmetuDTO(NastavnikNaPredmetu nastavnikNaPredmetu) throws Exception {
        try {
            if (nastavnikNaPredmetu != null) {
                NastavnikNaPredmetuDTO nastavnikNaPredmetuDTO
                        = new NastavnikNaPredmetuDTO(nastavnikNaPredmetu.getNastavnikNaPredmetuPK().getNastavnikId(),
                                nastavnikNaPredmetu.getNastavnikNaPredmetuPK().getPredmetId(), nastavnikNaPredmetu.getNastavnikNaPredmetuPK().getTipNastaveId());
                nastavnikNaPredmetuDTO.setNastavnikDTO(toNastavnikDTO(nastavnikNaPredmetu.getNastavnik()));
                nastavnikNaPredmetuDTO.setTipNastaveDTO(toTipNastaveDTO(nastavnikNaPredmetu.getTipNastave()));

                return nastavnikNaPredmetuDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju nastavnikNaPredmetu->nastavnikNaPredmetuDTO {" + e.getMessage() + "}");
        }
    }

    public static StatusDTO toStatusDTO(Status status) throws Exception {
        try {
            if (status != null) {
                StatusDTO statusDTO = new StatusDTO();
                statusDTO.setStatusId(status.getStatusId());
                statusDTO.setNaziv(status.getNaziv());

                return statusDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju status->statusDTO {" + e.getMessage() + "}");
        }
    }

    private static UdzbenikNaPredmetuDTO toUdzbenikNaPredmetuDTO(UdzbenikNaPredmetu udzbenikNaPredmetu) throws Exception {
        try {
            if (udzbenikNaPredmetu != null) {
                UdzbenikNaPredmetuDTO udzbenikNaPredmetuDTO = new UdzbenikNaPredmetuDTO();
                if (udzbenikNaPredmetu.getUdzbenikNaPredmetuPK() != null) {
                    udzbenikNaPredmetuDTO.setPredmetId(udzbenikNaPredmetu.getUdzbenikNaPredmetuPK().getPredmetId());
                    udzbenikNaPredmetuDTO.setUdzbenikId(udzbenikNaPredmetu.getUdzbenikNaPredmetuPK().getUdzbenikId());
                }
                udzbenikNaPredmetuDTO.setUdzbenikDTO(toUdzbenikDTO(udzbenikNaPredmetu.getUdzbenik()));
                return udzbenikNaPredmetuDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju udzbenikNaPredmetu->udzbenikNaPredmetuDTO {" + e.getMessage() + "}");
        }
    }

    public static VrstaINivoStudijaDTO toVrstaINivoStudijaDTO(VrstaINivoStudija vrstaINivoStudija) throws Exception {
        try {
            if (vrstaINivoStudija != null) {
                VrstaINivoStudijaDTO vrstaINivoStudijaDTO = new VrstaINivoStudijaDTO();
                vrstaINivoStudijaDTO.setVrstaId(vrstaINivoStudija.getVrstaId());
                vrstaINivoStudijaDTO.setNaziv(vrstaINivoStudija.getNaziv());

                return vrstaINivoStudijaDTO;
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new Exception("Greska u mapiranju vrstaINivoStudija->vrstaINivoStudijaDTO {" + e.getMessage() + "}");
        }

    }

    //****************
    // DTO -> ENTITY *
    //****************
    public static Predmet toPredmet(PredmetDTO predmetDTO) throws Exception {
        try {
            if (predmetDTO != null) {
                Predmet predmet = new Predmet();
                predmet.setPredmetId(predmetDTO.getPredmetId());
                predmet.setNaziv(predmetDTO.getNaziv());
                predmet.setBrCasovaPredavanjaNedeljno(predmetDTO.getBrCasovaPredavanjaNedeljno());
                predmet.setBrCasovaVezbiNedeljno(predmetDTO.getBrCasovaVezbiNedeljno());
                predmet.setCilj(predmetDTO.getCilj());
                predmet.setUslov(predmetDTO.getUslov());
                predmet.setIshod(predmetDTO.getIshod());
                predmet.setOstaliCasovi(predmetDTO.getOstaliCasovi());
                predmet.setSadrzajTekst(predmetDTO.getSadrzajTekst());
                predmet.setVrstaINivoStudija(toVrstaINivoStudija(predmetDTO.getVrstaINivoStudija()));
                predmet.setStudijskiIstrazivackiRad(predmetDTO.getStudijskiIstrazivackiRad());
                predmet.setDrugiObliciNastave(predmetDTO.getDrugiObliciNastave());

                List<UdzbenikNaPredmetu> udzbeniciNaPredmetu = new ArrayList<>();

                if (predmetDTO.getUdzbenici() != null) {
                    for (UdzbenikNaPredmetuDTO udzbenikNaPredmetuDTO : predmetDTO.getUdzbenici()) {
                        UdzbenikNaPredmetu unp = new UdzbenikNaPredmetu();
                        unp.setUdzbenikNaPredmetuPK(new UdzbenikNaPredmetuPK(udzbenikNaPredmetuDTO.getUdzbenikId(), predmet.getPredmetId() != null ? predmet.getPredmetId() :0));
                        udzbeniciNaPredmetu.add(unp);
                    }
                }

                predmet.setUdzbenikList(udzbeniciNaPredmetu);

                List<NastavnikNaPredmetu> nastavniciNaPredmetu = new ArrayList<>();

                if (predmetDTO.getNastavnici() != null) {

                    for (NastavnikNaPredmetuDTO nnpdto : predmetDTO.getNastavnici()) {
                        NastavnikNaPredmetu nnp = new NastavnikNaPredmetu(nnpdto.getNastavnikId(), predmet.getPredmetId() != null ? predmet.getPredmetId() : 0, nnpdto.getTipNastaveId());
                        nastavniciNaPredmetu.add(nnp);
                    }
                }

                predmet.setNastavnikNaPredmetuList(nastavniciNaPredmetu);

                List<TematskaCelina> tematskeCeline = new ArrayList<>();

                if (predmetDTO.getTematskaCeline() != null) {
                    for (TematskaCelinaDTO tematskaCelinaDTO : predmetDTO.getTematskaCeline()) {
                        tematskeCeline.add(toTematskaCelina(tematskaCelinaDTO));
                    }
                }

                predmet.setTematskaCelinaList(tematskeCeline);

                List<PredmetNaStudijskomProgramu> predmetiNaStudijskimProgramima = new ArrayList<>();

                if (predmetDTO.getPredmetiNaStudijskimProgramima() != null) {
                    for (PredmetNaStudijskomProgramuDTO predmetNaStudijskomProgramuDTO : predmetDTO.getPredmetiNaStudijskimProgramima()) {
                        predmetiNaStudijskimProgramima.add(toPredmetNaStudjskomProgramu(predmetNaStudijskomProgramuDTO));
                    }
                }

                predmet.setPredmetNaStudijskomProgramuList(predmetiNaStudijskimProgramima);

                return predmet;
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new Exception("Greska u mapiranju predmetDTO->predmet {" + e.getMessage() + "}");
        }
    }

    public static VrstaINivoStudija toVrstaINivoStudija(VrstaINivoStudijaDTO vrstaINivoStudijaDTO) throws Exception {
        try {
            if (vrstaINivoStudijaDTO != null) {
                VrstaINivoStudija vrstaINivoStudija = new VrstaINivoStudija();
                vrstaINivoStudija.setVrstaId(vrstaINivoStudijaDTO.getVrstaId());
                vrstaINivoStudija.setNaziv(vrstaINivoStudijaDTO.getNaziv());

                return vrstaINivoStudija;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju vrstaINivoStudijaDTO->vrstaINivoStudija {" + e.getMessage() + "}");
        }
    }

    public static Udzbenik toUdzbenik(UdzbenikDTO udzbenikDTO) throws Exception {
        try {

            if (udzbenikDTO != null) {
                Udzbenik udzbenik = new Udzbenik();

                udzbenik.setUdzbenikId(udzbenikDTO.getUdzbenikId());
                udzbenik.setNaziv(udzbenikDTO.getNaziv());
                udzbenik.setIzdavac(udzbenikDTO.getIzdavac());
                udzbenik.setRbrIzdanja(udzbenikDTO.getRbrIzdanja());
                udzbenik.setStampa(udzbenikDTO.getStampa());
                udzbenik.setTiraz(udzbenikDTO.getTiraz());
                udzbenik.setIsbn(udzbenikDTO.getIsbn());
                udzbenik.setGodinaIzdanja(udzbenikDTO.getGodinaIzdanja());

                List<OsobaUVeziSaUdzbenikom> osobeUVeziSaUdzbenicima = new ArrayList<>();

                if (udzbenikDTO.getOsobaUVeziSaUdzbenikomList() != null) {
                    for (OsobaUVeziSaUdzbenikomDTO osobaUVeziSaUdzbenikomDTO : udzbenikDTO.getOsobaUVeziSaUdzbenikomList()) {
                        osobeUVeziSaUdzbenicima.add(toOsobaUVeziSaUdzbenikom(osobaUVeziSaUdzbenikomDTO));
                    }
                }

                udzbenik.setOsobaUVeziSaUdzbenikomList(osobeUVeziSaUdzbenicima);

                return udzbenik;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju udzbenikDTO->udzbenik {" + e.getMessage() + "}");
        }
    }

    public static TematskaCelina toTematskaCelina(TematskaCelinaDTO tematskaCelinaDTO) throws Exception {
        try {
            if (tematskaCelinaDTO != null) {
                TematskaCelina tematskaCelina = new TematskaCelina();
                tematskaCelina.setTematskacelinaId(tematskaCelinaDTO.getTematskacelinaId());
                tematskaCelina.setNaziv(tematskaCelinaDTO.getNaziv());
                if (tematskaCelinaDTO.getNadredjenatematskacelina() != null) {
                    TematskaCelina tcNadredjena = new TematskaCelina(tematskaCelinaDTO.getNadredjenatematskacelina().getTematskacelinaId());
                    tematskaCelina.setNadredjenaTematskaCelina(tcNadredjena);
                }
                tematskaCelina.setOpis(tematskaCelinaDTO.getOpis());
                tematskaCelina.setTipNastave(toTipNastave(tematskaCelinaDTO.getTipNastave()));

                return tematskaCelina;
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new Exception("Greska u mapiranju tematskaCelinaDTO->tematskaCelina {" + e.getMessage() + "}");
        }
    }

    public static PredmetNaStudijskomProgramu toPredmetNaStudjskomProgramu(PredmetNaStudijskomProgramuDTO predmetNaStudijskomProgramuDTO) throws Exception {
        try {
            if (predmetNaStudijskomProgramuDTO != null) {
                PredmetNaStudijskomProgramu predmetNaStudijskomProgramu = new PredmetNaStudijskomProgramu();
                predmetNaStudijskomProgramu.setPredmetNaStudijskomProgramuPK(new PredmetNaStudijskomProgramuPK(predmetNaStudijskomProgramuDTO.getPredmetId(), predmetNaStudijskomProgramuDTO.getStudijskiProgramId()));
                predmetNaStudijskomProgramu.setEspb(predmetNaStudijskomProgramuDTO.getEspb());
                predmetNaStudijskomProgramu.setSemestar(predmetNaStudijskomProgramuDTO.getSemestar());
                predmetNaStudijskomProgramu.setStudijskiProgram(toStudijskiProgram(predmetNaStudijskomProgramuDTO.getStudijskiProgramDTO()));
                predmetNaStudijskomProgramu.setStatus(toStatus(predmetNaStudijskomProgramuDTO.getStatusDTO()));
                return predmetNaStudijskomProgramu;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju PredmetNaStudijskomProgramuDTO->PredmetNaStudijskomProgramu {" + e.getMessage() + "}");
        }
    }

    public static OsobaUVeziSaUdzbenikom toOsobaUVeziSaUdzbenikom(OsobaUVeziSaUdzbenikomDTO osobaUVeziSaUdzbenikomDTO) throws Exception {
        try {
            if (osobaUVeziSaUdzbenikomDTO != null) {
                OsobaUVeziSaUdzbenikom osobaUVeziSaUdzbenikom = new OsobaUVeziSaUdzbenikom();
                osobaUVeziSaUdzbenikom.setOsobaId(osobaUVeziSaUdzbenikomDTO.getOsobaId());
                osobaUVeziSaUdzbenikom.setIme(osobaUVeziSaUdzbenikomDTO.getIme());
                osobaUVeziSaUdzbenikom.setPrezime(osobaUVeziSaUdzbenikomDTO.getPrezime());
                osobaUVeziSaUdzbenikom.setTitula(osobaUVeziSaUdzbenikomDTO.getTitula());
                Udzbenik udzbenik = new Udzbenik();
                udzbenik.setUdzbenikId(osobaUVeziSaUdzbenikomDTO.getUdzbenikId());
                osobaUVeziSaUdzbenikom.setUdzbenik(udzbenik);
                if (osobaUVeziSaUdzbenikomDTO.getUlogaDTO() != null) {
                    osobaUVeziSaUdzbenikom.setUloga(toUlogaNaUdzbeniku(osobaUVeziSaUdzbenikomDTO.getUlogaDTO()));
                }
                return osobaUVeziSaUdzbenikom;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju osobaUVeziSaUdzbenikom->osobaUVeziSaUdzbenikomDTO {" + e.getMessage() + "}");
        }
    }

    public static TipNastave toTipNastave(TipNastaveDTO tipNastaveDTO) throws Exception {
        try {
            if (tipNastaveDTO != null) {
                TipNastave tipNastave = new TipNastave();
                tipNastave.setTipnastaveId(tipNastaveDTO.getTipnastaveId());
                tipNastave.setNaziv(tipNastaveDTO.getNaziv());
                return tipNastave;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju tipNastaveDTO->tipNastave {" + e.getMessage() + "}");
        }
    }

    public static StudijskiProgram toStudijskiProgram(StudijskiProgramDTO studijskiProgramDTO) throws Exception {
        try {
            if (studijskiProgramDTO != null) {
                StudijskiProgram studijskiProgram = new StudijskiProgram();
                studijskiProgram.setStudijskiProgramId(studijskiProgramDTO.getStudijskiProgramId());
                studijskiProgram.setNaziv(studijskiProgramDTO.getNaziv());

                return studijskiProgram;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju studijskiProgramDTO->studijskiProgram {" + e.getMessage() + "}");
        }
    }

    public static Status toStatus(StatusDTO statusDTO) throws Exception {
        try {
            if (statusDTO != null) {
                Status status = new Status();
                status.setStatusId(statusDTO.getStatusId());
                status.setNaziv(statusDTO.getNaziv());

                return status;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju studijskiProgramDTO->studijskiProgram {" + e.getMessage() + "}");
        }
    }

    private static UlogaUdzbenik toUlogaNaUdzbeniku(UlogaUdzbenikDTO ulogaDTO) throws Exception {
        try {
            if (ulogaDTO != null) {
                UlogaUdzbenik ulogaUdzbenik = new UlogaUdzbenik();
                ulogaUdzbenik.setUlogaId(ulogaDTO.getUlogaId());
                ulogaUdzbenik.setNaziv(ulogaDTO.getNaziv());

                return ulogaUdzbenik;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju ulogaUdzbenikDTO->ulogaUdzbenik {" + e.getMessage() + "}");
        }
    }

    public static Uloga toUloga(UlogaDTO ulogaDTO) throws Exception {
        try {
            if (ulogaDTO != null) {
                Uloga uloga = new Uloga();
                uloga.setUlogaId(ulogaDTO.getUlogaId());
                uloga.setNaziv(ulogaDTO.getNaziv());

                return uloga;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju ulogaDTO->uloga {" + e.getMessage() + "}");
        }
    }

    public static Korisnik toKorisnik(KorisnikDTO korisnikDTO) throws Exception {
        try {
            if (korisnikDTO != null) {
                Korisnik korisnik = new Korisnik();
                korisnik.setKorisnikId(korisnikDTO.getKorisnikId());
                korisnik.setIme(korisnikDTO.getIme());
                korisnik.setPrezime(korisnikDTO.getPrezime());
                korisnik.setUsername(korisnikDTO.getUsername());
                korisnik.setPassword(korisnikDTO.getPassword());
                korisnik.setUloga(toUloga(korisnikDTO.getUlogaDTO()));

                return korisnik;

            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Greska u mapiranju korisnik->korisnikDTO {" + e.getMessage() + "}");
        }
    }

}
