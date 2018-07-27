/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Petar
 */
public abstract class AbstractLogic {
     
     @Inject
     private ValidatorFactory vf;
     
     protected Validator validator;

     
    public AbstractLogic() {
      validator = vf.getValidator();
    }
     
     
     
}
