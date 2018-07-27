/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.Nastavnik;
import java.util.List;
import repository.NastavnikRepository;

/**
 *
 * @author Petar
 */
public class NastavnikLogic extends AbstractLogic {

    private final NastavnikRepository nr;

    public NastavnikLogic() {
        super();
        nr = new NastavnikRepository();
    }

    public List<Nastavnik> getAll() {
        try {
            return nr.getAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public Nastavnik getById(int id) {
        try {
            return nr.getById(id);
        } catch (Exception e) {
            throw e;
        }
    }

}
