/*
 *  @(#)User.java   30-dic-2010
 */

package edu.umss.devportal.common;

/**
 * Interface that represents a user system.
 * 
 * @author Alex Arenas
 */
public interface User {

    /**
     * @return user's id.
     */
    public String getId();

    /**
     * sets the user id
     * @param id new user id
     */
    public void setId(String id);

    /**
     * @return user's login.
     */
    public String getLogin();

    /**
     * sets the user login
     * @param login new user login
     */
    public void setLogin(String login);

    /**
     * @return user's name
     */
    public String getName();

    /**
     * gets the user's name
     * @param name new user's name
     */
    public void setName(String name);

    /**
     * sets the user password
     * @param password new user password.
     */
    public void setPassword(String password);

}
