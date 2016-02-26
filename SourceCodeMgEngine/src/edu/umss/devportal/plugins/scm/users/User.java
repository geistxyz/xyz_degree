/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.scm.users;

import edu.umss.devportal.plugins.scm.utils.StringUtilities;

/**
 *
 * @author Arminda Yovana Soto
 */
public class User {
    private String name;
    private String lastName;
    private String password;
    private String email;
    private String lastName2;
    private final String SPACE = StringUtilities.SPACE.getValue();

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name, String lastName,  String password) {
        this.name = name;
        this.lastName = lastName;
        this.password = password;
    }
    public User(String name, String lastName,String lastName2,String email) {
        this.name = name;
        this.lastName = lastName;
        this.lastName2=lastName2;
        this.email=email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    protected String getPassword() {
        return password;
    }
    public String getCompleteName(){
        return name.concat(SPACE).concat(lastName).concat(SPACE).concat(lastName2);
    }
}
