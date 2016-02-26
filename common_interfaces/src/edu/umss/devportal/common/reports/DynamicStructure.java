/*
 * @(#)DynamicStructure.java
 */

package edu.umss.devportal.common.reports;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edson
 * @version 1.0
 *
 * Implements the DataStructure interface
 * contains a dynamic list to handle dynamic grid reports.
 */
public class DynamicStructure implements DataStructure{

    /**
     * Represents the dynamic list
     */
    private List<List<String>> dynamicList = new ArrayList<List<String>>();

    /*
     * Represents the headers list
     */
    private List<String> headersList = new ArrayList<String>();

    public DynamicStructure() {
    }

    /**
     * Gets a list of lists used to represent the dynamic grid report
     * @return dynamic list
     */
    public List<List<String>> getDynamicList() {
        return dynamicList;
    }

    /**
     * Sets the dynamic list
     * @param dynamicList
     */
    public void setDynamicList(List<List<String>> dynamicList) {
        this.dynamicList = dynamicList;
    }

    /**
     * Gets the headers list
     * @return List<String>
     */
    public List<String> getHeadersList() {
        return headersList;
    }

    /**
     * Sets the header list
     * @param headersList
     */
    public void setHeadersList(List<String> headersList) {
        this.headersList = headersList;
    }
}
