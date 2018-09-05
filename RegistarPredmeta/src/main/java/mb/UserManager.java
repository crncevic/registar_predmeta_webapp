/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import constants.Constants;
import dto.KorisnikDTO;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import ws.client.RestWSClient;

/**
 *
 * @author Petar
 */
@ManagedBean
@Named(value = "userManager")
@SessionScoped
public class UserManager implements Serializable {

    /**
     * Creates a new instance of UserManager
     */
    private RestWSClient restWSClient;
    private KorisnikDTO currentUser;

    public UserManager() {
    }

    @PostConstruct
    private void init() {
        restWSClient = new RestWSClient(Constants.KORISNIK_CONTROLLER);
    }

    public boolean isUserSignedIn() {
        return currentUser != null;
    }

    public void signIn() {
        KorisnikDTO user = restWSClient.getByParam_JSON(KorisnikDTO.class, Constants.KORISNIK_USERNAME, currentUser.getUsername());
        if(!user.getPassword().equals(currentUser.getPassword())){
            FacesMessage msg = new FacesMessage("Pogresno korisnicko ime ili password. Pokusajte ponovo");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void signOut() {
     currentUser = null;
     FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

}
