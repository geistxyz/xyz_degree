/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.common;


/**
 *
 * This class is a basic User implementation
 *
 * @author Raul Lopez
 * @version 1.0
 */
public class BasicUserImpl implements User{

    /***
     * user login string
     */
    private String login;

    /***
     * user name
     */
    private String name;

    /***
     * user id
     */
    private String id;

    /**
     * Default constructor.
     */
    public BasicUserImpl() {
    }

    /**
     * Constructor
     *
     * @param login user's login
     * @param name user's name
     * @param id user's identifier
     */
    public BasicUserImpl(String login, String name, String id) {
        this.login = login;
        this.name = name;
        this.id = id;
    }


    /***
     * @see User#getLogin()
     */
    @Override
    public String getLogin() {
        return this.login;
    }

    /***
     * @see User#setLogin(java.lang.String)
     */
    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    /***
     * @see User#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /***
     * @see User#setName(java.lang.String)
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /***
     * Currently this method is doing nothing
     * @see User#setPassword(java.lang.String)     
     */
    @Override
    public void setPassword(String password) {        
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BasicUserImpl other = (BasicUserImpl) obj;
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 83 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
