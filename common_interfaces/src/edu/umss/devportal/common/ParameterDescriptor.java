/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.common;

/**
 *
 * @author Raul Lopez
 * @version 1.0
 */
public class ParameterDescriptor {

    /**
     * Parameter name.
     */
    private String name;

    /**
     * Default parameter value.
     */
    private String defaultValue;

    /**
     * Parameter's description.
     */
    private String description;

    /**
     * Regular expression pattern for validating it.
     */
    private String checkPattern;

    /**
     * Default constructor, initializes all members in null
     */
    public ParameterDescriptor()
    {
        this(null, null, null, null);
    }

    /**
     * ParameterDescriptor constructor.
     * @param name name of the parameter.
     */
    public ParameterDescriptor(String name) {
        this(name, null, null, null);
    }


    /**
     * ParameterDescriptor constructor.
     * @param name name of the parameter.
     * @param defaultValue default value of the parameter.
     */
    public ParameterDescriptor(String name, String defaultValue) {
        this(name, defaultValue, null, null);
    }

    /**
     * ParameterDescriptor constructor.
     * @param name name of the parameter.
     * @param defaultValue default value of the parameter.
     * @param description description of the parameter.
     */
    public ParameterDescriptor(String name, String defaultValue, String description) {
        this(name, defaultValue, description, null);
    }

    /**
     * ParameterDescriptor constructor.
     * @param name name of the parameter.
     * @param defaultValue default value of the parameter.
     * @param description description of the parameter.
     * @param checkPattern pattern used to validate it
     */
    public ParameterDescriptor(String name, String defaultValue, String description, String checkPattern) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.description = description;
        this.checkPattern = checkPattern;
    }

    public String getCheckPattern() {
        return checkPattern;
    }

    public void setCheckPattern(String checkPattern) {
        this.checkPattern = checkPattern;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ParameterDescriptor other = (ParameterDescriptor) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.defaultValue == null) ? (other.defaultValue != null) : !this.defaultValue.equals(other.defaultValue)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if ((this.checkPattern == null) ? (other.checkPattern != null) : !this.checkPattern.equals(other.checkPattern)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 67 * hash + (this.defaultValue != null ? this.defaultValue.hashCode() : 0);
        hash = 67 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 67 * hash + (this.checkPattern != null ? this.checkPattern.hashCode() : 0);
        return hash;
    }

}
