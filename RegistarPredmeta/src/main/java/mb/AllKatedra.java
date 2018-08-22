/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Petar
 */
@ManagedBean
@ViewScoped
@Named("allKatedra")
public class AllKatedra implements Serializable{

    /**
     * Creates a new instance of AllKatedra
     */
    public AllKatedra() {
    }
    
}
