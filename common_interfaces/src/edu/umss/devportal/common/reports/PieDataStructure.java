/*
 * @(#)PieDataStructure.java
 */

package edu.umss.devportal.common.reports;

/**
 * Represents the structure needed to generate a PieDataStructure
 * @author Grace
 */
public class PieDataStructure implements DataStructure {

    private String key;
    private double value;

    /**
     * Represents the constructor of the PieDataStructure
     * @param key
     * @param value
     */
    public PieDataStructure(String key, double value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Gets the Key
     * @return String that represents the Key
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the Key
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets the Value
     * @return double that represents the Value
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets the Value
     * @param value
     */
    public void setValue(double value) {
        this.value = value;
    }
}
