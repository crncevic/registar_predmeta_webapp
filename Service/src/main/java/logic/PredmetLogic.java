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
import domain.Udzbenik;
import domain.UdzbenikNaPredmetu;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public class PredmetLogic extends AbstractLogicClass {

    @Inject
    private GenericRepository<Predmet> grp;
    @Inject
    private GenericRepository<UdzbenikNaPredmetu> grunp;
    @Inject
    private GenericRepository<NastavnikNaPredmetu> grnnp;
    @Inject
    private GenericRepository<Nastavnik> grn;
    @Inject
    private GenericRepository<PredmetNaStudijskomProgramu> grpnsp;
    @Inject
    private GenericRepository<TematskaCelina> grtc;

    private Set<ConstraintViolation<Predmet>> violations;

    public Predmet create(Predmet predmet) throws Exception {
        try {
            //provera vrednosnih ogranicenja
            violations = validator.validate(predmet);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO : strukturna ogranicenja
            Predmet sameNamePredmet = grp.getSingleByParamFromNamedQuery(predmet.getNaziv(), Predmet.class, Constants.PREDMET_FIND_BY_NAZIV, Constants.PREDMET_NAZIV);

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
                        = grunp.getListByParamFromNamedQuery(predmet.getPredmetId(), UdzbenikNaPredmetu.class, Constants.UDZBENIK_NA_PREDMETU_FIND_ALL_BY_PREDMET_ID, Constants.PREDMET_ID);

                for (UdzbenikNaPredmetu oldUdzbenik : oldUdzbenici) {
                    int counter = 0;
                    for (UdzbenikNaPredmetu newUdzbenik : predmet.getUdzbenikList()) {
                        if (oldUdzbenik.equals(newUdzbenik)) {
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
                        if (oldUdzbenik.equals(newUdzbenik)) {
                            counter++;
                        }
                    }

                    if (counter == 0) {
                        udzbeniciForInserting.add(newUdzbenik);
                    }
                }

                //brisanje izbacenih iz agregacije
                for (UdzbenikNaPredmetu udzbenikNaPredmetu : udzbeniciForDeletening) {
                    grunp.delete(udzbenikNaPredmetu.getUdzbenikNaPredmetuPK(), UdzbenikNaPredmetu.class);
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
                        = grnnp.getListByParamFromNamedQuery(predmet.getPredmetId(), NastavnikNaPredmetu.class, Constants.NASTAVNIK_NA_PREDMETU_FIND_ALL_BY_PREDMET_ID, Constants.PREDMET_ID);

                for (NastavnikNaPredmetu oldNastavnik : oldNastavniciNaPredmetu) {
                    int counter = 0;
                    for (NastavnikNaPredmetu newNastavnik : predmet.getNastavnikNaPredmetuList()) {
                        if (oldNastavnik.equals(newNastavnik)) {
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
                        if (oldNastavnik.equals(newNastavnik)) {
                            counter++;
                        }
                    }
                    if (counter == 0) {
                        nastavniciForInserting.add(newNastavnik);
                    }
                }

                //brisanje iz agregacije 
                for (NastavnikNaPredmetu nastavnikNaPredmetu : nastavniciForDeleting) {
                    grnnp.delete(nastavnikNaPredmetu.getNastavnikNaPredmetuPK(), NastavnikNaPredmetu.class);
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
                        = grtc.getListByParamFromNamedQuery(predmet.getPredmetId(), TematskaCelina.class, Constants.TEMATSKA_CELINA_FIND_BY_PREDMET_ID, Constants.PREDMET_ID);

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
                    grtc.delete(tematskaCelina.getTematskacelinaId(), TematskaCelina.class);
                }

                //dodavanje novih tematskih celina u agregaciju
                for (TematskaCelina tematskaCelina : tematskeCelineFroInserting) {
                    grtc.update(tematskaCelina);
                }

                //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="Azuriranje agregacije PredmetNaStudijskomProgramu">
                List<PredmetNaStudijskomProgramu> predmetNaStudijskomProgramuForInserting = new ArrayList<>();
                List<PredmetNaStudijskomProgramu> predmetNaStudijskomProgramuForDeleting = new ArrayList<>();

                List<PredmetNaStudijskomProgramu> oldPredmetiNaStudiskomProgramu
                        = grpnsp.getListByParamFromNamedQuery(predmet.getPredmetId(), PredmetNaStudijskomProgramu.class, Constants.PREDMET_NA_STUDIJSKOM_PROGRAMU_FIND_BY_PREDMET_ID, Constants.PREDMET_ID);

                for (PredmetNaStudijskomProgramu oldPredmetNaStudijskomProgramu : oldPredmetiNaStudiskomProgramu) {
                    int counter = 0;
                    for (PredmetNaStudijskomProgramu newPredmetNaStudijskomProgramu : predmet.getPredmetNaStudijskomProgramuList()) {
                        if (oldPredmetNaStudijskomProgramu.equals(newPredmetNaStudijskomProgramu)) {
                            counter++;
                        }
                    }

                    if (counter == 0) {
                        predmetNaStudijskomProgramuForDeleting.add(oldPredmetNaStudijskomProgramu);
                    }
                }

                for (PredmetNaStudijskomProgramu newPredmetNaStudijskomProgramu : predmet.getPredmetNaStudijskomProgramuList()) {
                    int counter = 0;
                    for (PredmetNaStudijskomProgramu oldPredmetNaStudijskomProgramu : oldPredmetiNaStudiskomProgramu) {
                        if (oldPredmetNaStudijskomProgramu.equals(newPredmetNaStudijskomProgramu)) {
                            counter++;
                        }
                    }

                    if (counter == 0) {
                        predmetNaStudijskomProgramuForInserting.add(newPredmetNaStudijskomProgramu);
                    }
                }

                //brisanje iz agregacije
                for (PredmetNaStudijskomProgramu predmetNaStudijskomProgramu : predmetNaStudijskomProgramuForDeleting) {
                    grpnsp.delete(predmetNaStudijskomProgramu.getPredmetNaStudijskomProgramuPK(), PredmetNaStudijskomProgramu.class);
                }

                //dodavanje novih u agregaciju 
                for (PredmetNaStudijskomProgramu predmetNaStudijskomProgramu : predmetNaStudijskomProgramuForInserting) {
                    grpnsp.update(predmetNaStudijskomProgramu);
                }

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
                    grunp.delete(udzbenikNaPredmetu.getUdzbenikNaPredmetuPK(), UdzbenikNaPredmetu.class);
                }

                for (NastavnikNaPredmetu nastavnikNaPredmetu : deletedPredmet.getNastavnikNaPredmetuList()) {
                    grnnp.delete(nastavnikNaPredmetu.getNastavnikNaPredmetuPK(), NastavnikNaPredmetu.class);
                }

                for (TematskaCelina tematskaCelina : deletedPredmet.getTematskaCelinaList()) {
                    grtc.delete(tematskaCelina.getTematskacelinaId(), TematskaCelina.class);
                }

                for (PredmetNaStudijskomProgramu predmetNaStudijskomProgramu : deletedPredmet.getPredmetNaStudijskomProgramuList()) {
                    grpnsp.delete(predmetNaStudijskomProgramu.getPredmetNaStudijskomProgramuPK(), PredmetNaStudijskomProgramu.class);
                }

                grp.delete(id, Predmet.class);
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
            return grp.getAll(Predmet.class, Constants.PREDMET_FIND_ALL);

        } catch (Exception e) {
            throw e;
        }
    }

    public Predmet getById(int id) throws Exception {
        try {

            Predmet predmet = grp.getSingleByParamFromNamedQuery(id, Predmet.class, Constants.PREDMET_FIND_BY_ID, Constants.PREDMET_ID);

            if (predmet != null) {
                predmet.setUdzbenikList(grunp.getListByParamFromNamedQuery(id, UdzbenikNaPredmetu.class, Constants.UDZBENIK_NA_PREDMETU_FIND_ALL_BY_PREDMET_ID, Constants.PREDMET_ID));
                predmet.setNastavnikNaPredmetuList(grnnp.getListByParamFromNamedQuery(id, NastavnikNaPredmetu.class, Constants.NASTAVNIK_NA_PREDMETU_FIND_ALL_BY_PREDMET_ID, Constants.PREDMET_ID));

                for (NastavnikNaPredmetu nastavnikNaPredmetu : predmet.getNastavnikNaPredmetuList()) {
                    nastavnikNaPredmetu.setNastavnik(
                            grn.getSingleByParamFromNamedQuery(nastavnikNaPredmetu.getNastavnikNaPredmetuPK().getNastavnikId(), Nastavnik.class, Constants.NASTAVNIK_FIND_BY_ID, Constants.NASTAVNIK_ID));
                }

                predmet.setPredmetNaStudijskomProgramuList(grpnsp.getListByParamFromNamedQuery(id, PredmetNaStudijskomProgramu.class, Constants.PREDMET_NA_STUDIJSKOM_PROGRAMU_FIND_BY_PREDMET_ID, Constants.PREDMET_ID));
                predmet.setTematskaCelinaList(grtc.getListByParamFromNamedQuery(id, TematskaCelina.class, Constants.TEMATSKA_CELINA_FIND_BY_PREDMET_ID, Constants.PREDMET_ID));

                return predmet;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
