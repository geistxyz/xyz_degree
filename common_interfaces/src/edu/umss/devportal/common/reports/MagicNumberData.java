/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.common.reports;

/**
 *
 * @author July Camacho
 */
public interface MagicNumberData {
    /**
     * Set a String key data to MagicNumberdata
     * @param key
     */
    public void setKey(String key);
    /**
     * Set an Object value to MagicNumberData
     * @param value
     */
    public void setValue(Object value);
    /**
     * Obtain the MagicNumberData key
     * @return String key
     */
    public String getKey();
    /**
     * Obtain the MagicNumberData value
     * @return Object value
     */
    public Object getValue();
}
