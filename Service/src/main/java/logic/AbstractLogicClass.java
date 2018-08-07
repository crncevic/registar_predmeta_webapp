/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import repository.GenericRepository;

/**
 *
 * @author Petar
 */
public abstract class AbstractLogicClass {

    private ValidatorFactory vf = Validation.buildDefaultValidatorFactory();

    protected Validator validator =  vf.getValidator();
    
    protected EntityTransaction et = GenericRepository.getEntityTransactionInstance();

    

}
