/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.web.backbeans;

import edu.umss.devportal.ejb.UserService;
import edu.umss.devportal.entity.UserEntity;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Raul Garvizu
 */
@ManagedBean(name="createUserBean")
@SessionScoped
public class CreateUserController implements Serializable {
    @EJB
    private UserService userService;

    private UserEntity newUser;

    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private String confirmPassword;

    /** Creates a new instance of CreateUserController */
    public CreateUserController() {
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * @return the newUser
     */
    public UserEntity getNewUser() {
        if (newUser == null) {
            newUser = new UserEntity();
        }
        return newUser;
    }

    /**
     * @param newUser the newUser to set
     */
    public void setNewUser(UserEntity newUser) {
        this.newUser = newUser;
    }

    public void saveUser() {

        if(newUser.getPasword().equals(confirmPassword)) {
            userService.saveUser(newUser);
            pcs.firePropertyChange("new user", null, newUser);
            setNewUser(null);
            retrieveAllUsers();
        } else {
            FacesContext.getCurrentInstance().
                        addMessage(
                        null,
                        new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        "Error creating user",
                        "Password does not match."));
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public List<UserEntity> allUsers;

    public List<UserEntity> getAllUsers() {
        if (allUsers == null)
            retrieveAllUsers();
        return allUsers;
    }

    public void setAllUsers(List<UserEntity> allUsers) {
        this.allUsers = allUsers;
    }

    private void retrieveAllUsers() {
        allUsers = userService.getAllUsers();
    }
}
