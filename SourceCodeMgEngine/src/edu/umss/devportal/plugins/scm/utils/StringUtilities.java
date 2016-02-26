/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.scm.utils;

import java.io.File;

/**
 *Sets a general values
 * @author Arminda Yovana Soto
 */
public enum StringUtilities {

    FILE_SEPARATOR(Character.toString(File.separatorChar)),
    SPACE(" "),
    REPOSITORIES_NAME("repositorios");
    private String value;

    /**
     * Constructor initialize a value
     * @param value represents the enumerators value
     */
    private StringUtilities(String value) {
        this.value = value;
    }

    /**
     * Sets a value
     * @param value represents the enumerators value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Returns a enumerator value
     * @param value represents the enumerators value
     */
    public String getValue() {
        return value;
    }
}
