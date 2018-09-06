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
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
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
    private String username;
    private String password;

    public UserManager() {

    }

    @PostConstruct
    private void init() {
        username = "";
        password = "";
        restWSClient = new RestWSClient(Constants.KORISNIK_CONTROLLER);
    }

    public boolean isUserSignedIn() {
        return currentUser != null;
    }

    public String signIn() {
        KorisnikDTO user = restWSClient.getByParam_JSON(KorisnikDTO.class, Constants.KORISNIK_USERNAME, username);
        if (user == null || !user.getPassword().equals(password)) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesMessage msg = new FacesMessage("Pogresno korisnicko ime ili password. Pokusajte ponovo");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            resetValues();

            return "failure_sign_in";
        }
        currentUser = user;
        return "success_sign_in";
    }

    public String signOut() {
        currentUser = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "signed_out";
    }

    public KorisnikDTO getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(KorisnikDTO currentUser) {
        this.currentUser = currentUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void resetValues() {
        username = "";
        password = "";
    }

}
