/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.Nastavnik;
import domain.NastavnikNaPredmetu;
import domain.Predmet;
import domain.PredmetNaStudijskomProgramu;
import domain.StudijskiProgram;
import domain.TematskaCelina;
import domain.TipNastave;
import domain.Udzbenik;
import domain.UdzbenikNaPredmetu;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public class PredmetLogic extends AbstractLogicClass {

    private GenericRepository<Predmet> grp;
    private GenericRepository<UdzbenikNaPredmetu> grunp;
    private GenericRepository<NastavnikNaPredmetu> grnnp;
    private GenericRepository<Nastavnik> grn;
    private GenericRepository<PredmetNaStudijskomProgramu> grpnsp;
    private GenericRepository<TematskaCelina> grtc;
    private GenericRepository<Udzbenik> gru;
    private GenericRepository<TipNastave> grtn;

    private Set<ConstraintViolation<Predmet>> violations;

    public PredmetLogic() {
      grn = new GenericRepository(Nastavnik.class);
      grnnp = new GenericRepository(NastavnikNaPredmetu.class);
      grp = new GenericRepository(Predmet.class);
      grpnsp = new GenericRepository(PredmetNaStudijskomProgramu.class);
      grtn = new GenericRepository(TipNastave.class);
      grtc = new GenericRepository(TematskaCelina.class);
      grunp = new GenericRepository(UdzbenikNaPredmetu.class);
      gru = new GenericRepository(Udzbenik.class);
    }

    
    
    
    public Predmet create(Predmet predmet) throws Exception {
        try {
            //provera vrednosnih ogranicenja
            violations = validator.validate(predmet);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO : strukturna ogranicenja
            Predmet sameNamePredmet = grp.getSingleByParamsFromNamedQuery(new Object[]{predmet.getNaziv()}, Constants.PREDMET_FIND_BY_NAZIV, new String[]{Constants.PREDMET_NAZIV});

            if (sameNamePredmet != null) {
                throw new ConstraintViolationException("Predmet sa nazivom : " + predmet.getNaziv() + " vec postoji u bazi!", null);
            }

            try {
                et.begin();
                Predmet predmetForCreating = new Predmet();
                predmetForCreating.setNaziv(predmet.getNaziv());

                Predmet createdPredmet = grp.save(predmetForCreating);
                predmet.setPredmetId(createdPredmet.getPredmetId());

                if (predmet.getUdzbenikList() != null) {
                    for (UdzbenikNaPredmetu udzbenikNaPredmetu : predmet.getUdzbenikList()) {
                        udzbenikNaPredmetu.getUdzbenikNaPredmetuPK().setPredmetId(predmet.getPredmetId());
                        grunp.save(udzbenikNaPredmetu);
                    }
                }

                if (predmet.getNastavnikNaPredmetuList() != null) {
                    for (NastavnikNaPredmetu nastavnikNaPredmetu : predmet.getNastavnikNaPredmetuList()) {
                        nastavnikNaPredmetu.getNastavnikNaPredmetuPK().setPredmetId(predmet.getPredmetId());
                        grnnp.save(nastavnikNaPredmetu);
                    }
                }

                if (predmet.getTematskaCelinaList() != null) {
                    for (TematskaCelina tematskaCelina : predmet.getTematskaCelinaList()) {
                        tematskaCelina.setPredmet(predmet);
                        grtc.save(tematskaCelina);
                    }
                }

                Predmet resultPredmet = grp.update(predmet);
                et.commit();
                return resultPredmet;
            } catch (Exception ex) {
                et.rollback();
                throw ex;
            }

        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception e) {
            throw e;
        }
    }

    public Predmet update(Predmet predmet) throws Exception {
        try {
            violations = validator.validate(predmet);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO : strukturna ogranicenja
            try {
                et.begin();

                //<editor-fold defaultstate="collapsed" desc="Azuriranje agregacije UdzbenikNaPredmetu">
                List<UdzbenikNaPredmetu> udzbeniciForDeletening = new ArrayList<>();
                List<UdzbenikNaPredmetu> udzbeniciForInserting = new ArrayList<>();
                List<UdzbenikNaPredmetu> oldUdzbenici
                        = grunp.getListByParamsFromNamedQuery(new Object[]{predmet.getPredmetId()}, Constants.UDZBENIK_NA_PREDMETU_FIND_ALL_BY_PREDMET_ID, new String[]{Constants.PREDMET_ID});

                for (UdzbenikNaPredmetu oldUdzbenik : oldUdzbenici) {
                    int counter = 0;
                    for (UdzbenikNaPredmetu newUdzbenik : predmet.getUdzbenikList()) {
                        if (oldUdzbenik.getUdzbenikNaPredmetuPK().getPredmetId() == newUdzbenik.getUdzbenikNaPredmetuPK().getPredmetId()
                                && oldUdzbenik.getUdzbenikNaPredmetuPK().getUdzbenikId() == newUdzbenik.getUdzbenikNaPredmetuPK().getUdzbenikId()) {
                            counter++;
                        }
                    }

                    if (counter == 0) {
                        udzbeniciForDeletening.add(oldUdzbenik);
                    }
                }

                for (UdzbenikNaPredmetu newUdzbenik : predmet.getUdzbenikList()) {
                    int counter = 0;
                    for (UdzbenikNaPredmetu oldUdzbenik : oldUdzbenici) {
                        if (oldUdzbenik.getUdzbenikNaPredmetuPK().getPredmetId() == newUdzbenik.getUdzbenikNaPredmetuPK().getPredmetId()
                                && oldUdzbenik.getUdzbenikNaPredmetuPK().getUdzbenikId() == newUdzbenik.getUdzbenikNaPredmetuPK().getUdzbenikId()) {
                            counter++;
                        }
                    }

                    if (counter == 0) {
                        udzbeniciForInserting.add(newUdzbenik);
                    }
                }

                //brisanje izbacenih iz agregacije
                for (UdzbenikNaPredmetu udzbenikNaPredmetu : udzbeniciForDeletening) {
                    grunp.delete(udzbenikNaPredmetu.getUdzbenikNaPredmetuPK());
                }

                // dodavanje novih udzbenika na predmetu
                for (UdzbenikNaPredmetu udzbenikNaPredmetu : udzbeniciForInserting) {
                    grunp.update(udzbenikNaPredmetu);
                }

                //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="Azuriranje agregacije NastavnikNaPredmetu">
                List<NastavnikNaPredmetu> nastavniciForDeleting = new ArrayList<>();
                List<NastavnikNaPredmetu> nastavniciForInserting = new ArrayList<>();

                List<NastavnikNaPredmetu> oldNastavniciNaPredmetu
                        = grnnp.getListByParamsFromNamedQuery(new Object[]{predmet.getPredmetId()}, Constants.NASTAVNIK_NA_PREDMETU_FIND_ALL_BY_PREDMET_ID, new String[]{Constants.PREDMET_ID});

                for (NastavnikNaPredmetu oldNastavnik : oldNastavniciNaPredmetu) {
                    int counter = 0;
                    for (NastavnikNaPredmetu newNastavnik : predmet.getNastavnikNaPredmetuList()) {
                        if (oldNastavnik.getNastavnikNaPredmetuPK().getNastavnikId() == newNastavnik.getNastavnikNaPredmetuPK().getNastavnikId()
                                && oldNastavnik.getNastavnikNaPredmetuPK().getPredmetId() == newNastavnik.getNastavnikNaPredmetuPK().getPredmetId()
                                && oldNastavnik.getNastavnikNaPredmetuPK().getTipNastaveId() == newNastavnik.getNastavnikNaPredmetuPK().getTipNastaveId()) {
                            counter++;
                        }
                    }
                    if (counter == 0) {
                        nastavniciForDeleting.add(oldNastavnik);
                    }
                }

                for (NastavnikNaPredmetu newNastavnik : predmet.getNastavnikNaPredmetuList()) {
                    int counter = 0;
                    for (NastavnikNaPredmetu oldNastavnik : oldNastavniciNaPredmetu) {
                        if (oldNastavnik.getNastavnikNaPredmetuPK().getNastavnikId() == newNastavnik.getNastavnikNaPredmetuPK().getNastavnikId()
                                && oldNastavnik.getNastavnikNaPredmetuPK().getPredmetId() == newNastavnik.getNastavnikNaPredmetuPK().getPredmetId()
                                && oldNastavnik.getNastavnikNaPredmetuPK().getTipNastaveId() == newNastavnik.getNastavnikNaPredmetuPK().getTipNastaveId()) {
                            counter++;
                        }
                    }
                    if (counter == 0) {
                        nastavniciForInserting.add(newNastavnik);
                    }
                }

                //brisanje iz agregacije 
                for (NastavnikNaPredmetu nastavnikNaPredmetu : nastavniciForDeleting) {
                    grnnp.delete(nastavnikNaPredmetu.getNastavnikNaPredmetuPK());
                }

                //dodavanje u agregaciju novih nastavnika na predmetu
                for (NastavnikNaPredmetu nastavnikNaPredmetu : nastavniciForInserting) {
                    grnnp.update(nastavnikNaPredmetu);
                }

                //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="Azuriranje agregacije TematskaCelina">
                List<TematskaCelina> tematskeCelineForDeleting = new ArrayList<>();
                List<TematskaCelina> tematskeCelineFroInserting = new ArrayList<>();

                List<TematskaCelina> oldTematskeCeline
                        = grtc.getListByParamsFromNamedQuery(new Object[]{predmet.getPredmetId()}, Constants.TEMATSKA_CELINA_FIND_BY_PREDMET_ID, new String[]{Constants.PREDMET_ID});

                for (TematskaCelina oldTematskaCelina : oldTematskeCeline) {
                    int counter = 0;
                    for (TematskaCelina newTematskaCelina : predmet.getTematskaCelinaList()) {
                        if (oldTematskaCelina.equals(newTematskaCelina)) {
                            counter++;
                        }
                    }

                    if (counter == 0) {
                        tematskeCelineForDeleting.add(oldTematskaCelina);
                    }
                }

                for (TematskaCelina newTematskaCelina : predmet.getTematskaCelinaList()) {
                    int counter = 0;
                    for (TematskaCelina oldTematskaCelina : oldTematskeCeline) {
                        if (oldTematskaCelina.equals(newTematskaCelina)) {
                            counter++;
                        }
                    }

                    if (counter == 0) {
                        tematskeCelineFroInserting.add(newTematskaCelina);
                    }
                }

                // brisanje izbacenih tematskih celina
                for (TematskaCelina tematskaCelina : tematskeCelineForDeleting) {
                    grtc.delete(tematskaCelina.getTematskacelinaId());
                }

                //dodavanje novih tematskih celina u agregaciju
                for (TematskaCelina tematskaCelina : tematskeCelineFroInserting) {
                    grtc.update(tematskaCelina);
                }

                //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="Azuriranje agregacije PredmetNaStudijskomProgramu">
//                List<PredmetNaStudijskomProgramu> predmetNaStudijskomProgramuForInserting = new ArrayList<>();
//                List<PredmetNaStudijskomProgramu> predmetNaStudijskomProgramuForDeleting = new ArrayList<>();
//
//                List<PredmetNaStudijskomProgramu> oldPredmetiNaStudiskomProgramu
//                        = grpnsp.getListByParamFromNamedQuery(predmet.getPredmetId(), PredmetNaStudijskomProgramu.class, Constants.PREDMET_NA_STUDIJSKOM_PROGRAMU_FIND_BY_PREDMET_ID, Constants.PREDMET_ID);
//
//                for (PredmetNaStudijskomProgramu oldPredmetNaStudijskomProgramu : oldPredmetiNaStudiskomProgramu) {
//                    int counter = 0;
//                    for (PredmetNaStudijskomProgramu newPredmetNaStudijskomProgramu : predmet.getPredmetNaStudijskomProgramuList()) {
//                        if (oldPredmetNaStudijskomProgramu.equals(newPredmetNaStudijskomProgramu)) {
//                            counter++;
//                        }
//                    }
//
//                    if (counter == 0) {
//                        predmetNaStudijskomProgramuForDeleting.add(oldPredmetNaStudijskomProgramu);
//                    }
//                }
//
//                for (PredmetNaStudijskomProgramu newPredmetNaStudijskomProgramu : predmet.getPredmetNaStudijskomProgramuList()) {
//                    int counter = 0;
//                    for (PredmetNaStudijskomProgramu oldPredmetNaStudijskomProgramu : oldPredmetiNaStudiskomProgramu) {
//                        if (oldPredmetNaStudijskomProgramu.equals(newPredmetNaStudijskomProgramu)) {
//                            counter++;
//                        }
//                    }
//
//                    if (counter == 0) {
//                        predmetNaStudijskomProgramuForInserting.add(newPredmetNaStudijskomProgramu);
//                    }
//                }
//
//                //brisanje iz agregacije
//                for (PredmetNaStudijskomProgramu predmetNaStudijskomProgramu : predmetNaStudijskomProgramuForDeleting) {
//                    grpnsp.delete(predmetNaStudijskomProgramu.getPredmetNaStudijskomProgramuPK(), PredmetNaStudijskomProgramu.class);
//                }
//
//                //dodavanje novih u agregaciju 
//                for (PredmetNaStudijskomProgramu predmetNaStudijskomProgramu : predmetNaStudijskomProgramuForInserting) {
//                    grpnsp.update(predmetNaStudijskomProgramu);
//                }
                //</editor-fold>
                Predmet updatedPredmet = grp.update(predmet);
                et.commit();
                return updatedPredmet;
            } catch (Exception ex) {
                et.rollback();
                throw ex;
            }

        } catch (ConstraintViolationException cve) {
            throw cve;
        } catch (Exception e) {
            throw e;
        }
    }

    public Predmet delete(int id) throws Exception {
        try {
            Predmet deletedPredmet = getById(id);
            //TODO : strukturna ogranicenja
            try {
                et.begin();

                for (UdzbenikNaPredmetu udzbenikNaPredmetu : deletedPredmet.getUdzbenikList()) {
                       grunp.delete(udzbenikNaPredmetu.getUdzbenikNaPredmetuPK());
                }

                for (NastavnikNaPredmetu nastavnikNaPredmetu : deletedPredmet.getNastavnikNaPredmetuList()) {
                    grnnp.delete(nastavnikNaPredmetu.getNastavnikNaPredmetuPK());
                }

                for (TematskaCelina tematskaCelina : deletedPredmet.getTematskaCelinaList()) {
                    grtc.delete(tematskaCelina.getTematskacelinaId());
                }

                for (PredmetNaStudijskomProgramu predmetNaStudijskomProgramu : deletedPredmet.getPredmetNaStudijskomProgramuList()) {
                    grpnsp.delete(predmetNaStudijskomProgramu.getPredmetNaStudijskomProgramuPK());
                }

                grp.delete(id);
                et.commit();
                return deletedPredmet;
            } catch (Exception ex) {
                et.rollback();
                throw ex;
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public List<Predmet> getAll() throws Exception {
        try {
            return grp.getAll(Constants.PREDMET_FIND_ALL);

        } catch (Exception e) {
            throw e;
        }
    }

    public Predmet getById(int id) throws Exception {
        try {

            Predmet predmet = grp.getSingleByParamsFromNamedQuery(
                    new Object[]{id}, Constants.PREDMET_FIND_BY_ID, new String[]{Constants.PREDMET_ID});

            if (predmet != null) {
                predmet.setUdzbenikList(grunp.getListByParamsFromNamedQuery(
                        new Object[]{id},
                        Constants.UDZBENIK_NA_PREDMETU_FIND_ALL_BY_PREDMET_ID,
                        new String[]{Constants.PREDMET_ID}));
                predmet.setNastavnikNaPredmetuList(grnnp.getListByParamsFromNamedQuery(
                        new Object[]{id},
                        Constants.NASTAVNIK_NA_PREDMETU_FIND_ALL_BY_PREDMET_ID,
                        new String[]{Constants.PREDMET_ID}));

                for (UdzbenikNaPredmetu udzbenikNaPredmetu : predmet.getUdzbenikList()) {
                    udzbenikNaPredmetu.setUdzbenik(
                            gru.getSingleByParamsFromNamedQuery(
                                    new Object[]{udzbenikNaPredmetu.getUdzbenikNaPredmetuPK().getUdzbenikId()},
                                    Constants.UDZBENIK_FIND_BY_ID,
                                    new String[]{Constants.UDZBENIK_ID}));
                }

                for (NastavnikNaPredmetu nastavnikNaPredmetu : predmet.getNastavnikNaPredmetuList()) {
                    nastavnikNaPredmetu.setNastavnik(
                            grn.getSingleByParamsFromNamedQuery(
                                    new Object[]{nastavnikNaPredmetu.getNastavnikNaPredmetuPK().getNastavnikId()},
                                    Constants.NASTAVNIK_FIND_BY_ID,
                                    new String[]{Constants.NASTAVNIK_ID}));
                    nastavnikNaPredmetu.setTipNastave(
                            grtn.getSingleByParamsFromNamedQuery(
                                    new Object[]{nastavnikNaPredmetu.getNastavnikNaPredmetuPK().getTipNastaveId()},
                                    Constants.TIP_NASTAVE_FIND_BY_ID,
                                    new String[]{Constants.TIP_NASTAVE_ID}));
                }

                predmet.setPredmetNaStudijskomProgramuList(
                        grpnsp.getListByParamsFromNamedQuery(new Object[]{id},
                        Constants.PREDMET_NA_STUDIJSKOM_PROGRAMU_FIND_BY_PREDMET_ID,
                        new String[]{Constants.PREDMET_ID}));
                predmet.setTematskaCelinaList(grtc.getListByParamsFromNamedQuery(
                        new Object[]{id},
                        Constants.TEMATSKA_CELINA_FIND_BY_PREDMET_ID,
                        new String[]{Constants.PREDMET_ID}));

                return predmet;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
