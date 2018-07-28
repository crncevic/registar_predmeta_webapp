/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.Status;
import java.util.List;
import repository.StatusRepository;

/**
 *
 * @author Petar
 */
public class StatusLogic extends AbstractLogic {

    private StatusRepository sr;

    public StatusLogic() {
        sr = new StatusRepository();
    }

    public List<Status> getAll() {
        try {
            return sr.getAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public Status getById(int id) {
        try {
            return sr.getById(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
