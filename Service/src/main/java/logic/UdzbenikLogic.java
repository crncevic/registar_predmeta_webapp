/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Constants;
import domain.OsobaUVeziSaUdzbenikom;
import domain.Udzbenik;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public class UdzbenikLogic extends AbstractLogicClass {

    private GenericRepository<Udzbenik> gru;
    private GenericRepository<OsobaUVeziSaUdzbenikom> grouvsu;
    private Set<ConstraintViolation<Udzbenik>> violations;

    public UdzbenikLogic() {
        gru = new GenericRepository<>();
        grouvsu = new GenericRepository<>();
    }

    public Udzbenik create(Udzbenik udzbenik) {
        try {
            violations = validator.validate(udzbenik);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO : strukturna ogranicenja
            try {
                et.begin();

                for (OsobaUVeziSaUdzbenikom osobaUVeziSaUdzbenikom : udzbenik.getOsobaUVeziSaUdzbenikomList()) {
                    osobaUVeziSaUdzbenikom.setUdzbenik(udzbenik);
                    grouvsu.save(osobaUVeziSaUdzbenikom);
                }
                Udzbenik createdUdzbenik = gru.save(udzbenik);
                et.commit();

                return createdUdzbenik;
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

    public Udzbenik update(Udzbenik udzbenik) {
        try {
            violations = validator.validate(udzbenik);

            if (violations.size() > 0) {
                throw new ConstraintViolationException(violations);
            }

            //TODO : strukturna ogranicenja
            try {
                et.begin();

                //<editor-fold defaultstate="collapsed" desc="Azuriranje osoba na udzbeniku">
                List<OsobaUVeziSaUdzbenikom> osobeForDeleting = new ArrayList<>();
                List<OsobaUVeziSaUdzbenikom> osobeForInserting = new ArrayList<>();

                List<OsobaUVeziSaUdzbenikom> oldOsobe
                        = grouvsu.getListByParamFromNamedQuery(udzbenik.getUdzbenikId(), OsobaUVeziSaUdzbenikom.class, Constants.OSOBA_U_VEZI_SA_UDZBENIKOM_FIND_BY_UDZBENIK_ID,
                                Constants.UDZBENIK_ID);

                for (OsobaUVeziSaUdzbenikom oldOsoba : oldOsobe) {
                    int counter = 0;
                    for (OsobaUVeziSaUdzbenikom newOsoba : udzbenik.getOsobaUVeziSaUdzbenikomList()) {
                        if (oldOsoba.equals(newOsoba)) {
                            counter++;
                        }
                    }

                    if (counter == 0) {
                        osobeForDeleting.add(oldOsoba);
                    }
                }

                for (OsobaUVeziSaUdzbenikom newOsoba : udzbenik.getOsobaUVeziSaUdzbenikomList()) {
                    int counter = 0;
                    for (OsobaUVeziSaUdzbenikom oldOsoba : oldOsobe) {
                        if (oldOsoba.equals(newOsoba)) {
                            counter++;
                        }
                    }

                    if (counter == 0) {
                        osobeForInserting.add(newOsoba);
                    }
                }

                //brisanje izbacenih prilikom azuriranja
                for (OsobaUVeziSaUdzbenikom osobaUVeziSaUdzbenikom : osobeForDeleting) {
                    grouvsu.delete(osobaUVeziSaUdzbenikom.getOsobaId(), OsobaUVeziSaUdzbenikom.class);
                }

                //dodavanje novih prilikom azuriranja
                for (OsobaUVeziSaUdzbenikom osobaUVeziSaUdzbenikom : osobeForInserting) {
                    grouvsu.update(osobaUVeziSaUdzbenikom);
                }

                //</editor-fold>
                Udzbenik updatedUdzbenik = gru.update(udzbenik);

                et.commit();
                return updatedUdzbenik;
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

    public Udzbenik delete(int id) {
        try {

            //TODO : strukturna ogranicenja
            try {
                et.begin();

                List<OsobaUVeziSaUdzbenikom> osobeForDeleting
                        = grouvsu.getListByParamFromNamedQuery(id, OsobaUVeziSaUdzbenikom.class, Constants.OSOBA_U_VEZI_SA_UDZBENIKOM_FIND_BY_UDZBENIK_ID, Constants.UDZBENIK_ID);

                for (OsobaUVeziSaUdzbenikom osobaUVeziSaUdzbenikom : osobeForDeleting) {
                    grouvsu.delete(osobaUVeziSaUdzbenikom.getOsobaId(), OsobaUVeziSaUdzbenikom.class);
                }

                Udzbenik deletedUdzbenik = gru.delete(id, Udzbenik.class);
                et.commit();
                return deletedUdzbenik;
            } catch (Exception ex) {
                et.rollback();
                throw ex;
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public List<Udzbenik> getAll() {
        try {
            List<Udzbenik> udzbenici = gru.getAll(Udzbenik.class, Constants.UDZBENIK_FIND_ALL);
            List<Udzbenik> udzbeniciForRetreiving = new ArrayList<>();
            for (Udzbenik udzbenik : udzbenici) {
                udzbeniciForRetreiving.add(getById(udzbenik.getUdzbenikId()));
            }

            return udzbeniciForRetreiving;
        } catch (Exception e) {
            throw e;
        }
    }

    public Udzbenik getById(int id) {
        try {
            Udzbenik udzbenik = gru.getSingleByParamFromNamedQuery(id, Udzbenik.class, Constants.UDZBENIK_FIND_BY_ID, Constants.UDZBENIK_ID);
            if(udzbenik!=null){
            udzbenik.
                    setOsobaUVeziSaUdzbenikomList(grouvsu.getListByParamFromNamedQuery(id, OsobaUVeziSaUdzbenikom.class, Constants.OSOBA_U_VEZI_SA_UDZBENIKOM_FIND_BY_UDZBENIK_ID, Constants.UDZBENIK_ID));

            return udzbenik;
            }else{
                return null;
            }
            
        } catch (Exception e) {
            throw e;
        }
    }
}
