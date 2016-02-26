/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.common.reports;

/**
 *
 * @author Edson
 */
public enum AvailableType implements TypeOfReport {

    GRID, PIECHART, LINECHART, BARCHART, STACKEDBARCHART, GANTTCHART, CUSTOM;

    public AvailableType toAvailableReport() {
        return this;
    }

    public boolean sameType(TypeOfReport other) {
        return other != null && this == other.toAvailableReport();
    }
}
