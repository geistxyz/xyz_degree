/*
 * @(#)LineDataStructure.java
 */

package edu.umss.devportal.common.reports;

/**
 * @author Edson
 * @version 1.0
 *
 * Represents the structure needed to generate a LineDataStructure
 */
public class LineDataStructure implements DataStructure{
    private int value;
    private String domainAxis;
    private String columnAxis;

    public LineDataStructure(){
    }

    /**
     * Represents the constructor of the LineDataStructure
     * @param value
     * @param domainAxis
     * @param columnAxis
     */
    public LineDataStructure(int value, String domainAxis, String columnAxis) {
        this.value = value;
        this.domainAxis = domainAxis;
        this.columnAxis = columnAxis;
    }

    /**
     * Gets the Column Axis
     * @return String that represents the Column Axis
     */
    public String getColumnAxis() {
        return columnAxis;
    }

    /**
     * Sets the Column Axis
     * @param columnAxis
     */
    public void setColumnAxis(String columnAxis) {
        this.columnAxis = columnAxis;
    }

    /**
     * Gets the Domain Axis
     * @return String that represents the Domain Axis
     */
    public String getDomainAxis() {
        return domainAxis;
    }

    /**
     * Sets the Column Axis
     * @param domainAxis
     */
    public void setDomainAxis(String domainAxis) {
        this.domainAxis = domainAxis;
    }

    /**
     * Gets the value
     * @return integer that represents the Value
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }
}
