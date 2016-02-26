/*
 * @(#)Severity.java 11/04/23
 *
 * Copyright (c) 2011 Universidad Mayor de San Simón
 * Cochabamba - Bolivia
 * All Rights Reserved.
 */
package edu.umss.devportal.reports.utilities;

/**
 *
 * @author Edson Alvarez
 */
public class Severity {

    private String label;
    private int value;

    /**
     * Default constructor
     */
    public Severity() {
    }

    /**
     * Constructor with parameters
     * @param label
     * @param value
     */
    public Severity(String label, int value) {
        this.label = label;
        this.value = value;
    }

    /**
     * Gets the label of the spinner control
     * @return a String that represents the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the label of the spinner control
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Gets the value of the spinner control
     * @return an integer that represents the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value
     * @param value
     */
    public void setValue(int value) {
        System.out.println("call to set value " + label + " ," + value);
        this.value = value;
    }
}
