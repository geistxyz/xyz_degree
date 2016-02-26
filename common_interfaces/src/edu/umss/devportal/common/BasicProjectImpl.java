/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.common;

/**
 *
 *
 * @author Raul Lopez
 * @version 1.0
 */
public class BasicProjectImpl implements Project{

    private String id;
    private String name;
    private String description;

    /**
     * Default constructor
     */
    public BasicProjectImpl() {
    }

    /***
     *
     * @param id project's id
     * @param name project's name
     * @param description project's description
     */
    public BasicProjectImpl(String name, String description, String id)
    {
        this.id = id;
        this.name = name;
        this.description = description;
    }
 
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BasicProjectImpl other = (BasicProjectImpl) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 23 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 23 * hash + (this.description != null ? this.description.hashCode() : 0);
        return hash;
    }

 
 
}
