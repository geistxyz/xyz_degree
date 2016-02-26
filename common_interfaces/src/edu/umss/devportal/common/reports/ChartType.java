/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.common.reports;

/**
 *
 * @author Edson
 */
public enum ChartType implements TypeOfReport {

    GANTTCHART(AvailableType.GANTTCHART),
    PIECHART(AvailableType.PIECHART),
    LINECHART(AvailableType.LINECHART),
    BARCHART(AvailableType.BARCHART),
    STACKEDBARCHART(AvailableType.STACKEDBARCHART);
    private final AvailableType availableType;

    private ChartType(AvailableType availableType) {
        this.availableType = availableType;
    }

    public boolean sameType(TypeOfReport other) {
        return other != null && availableType == other.toAvailableReport();
    }

    public AvailableType toAvailableReport() {
        return availableType;
    }
}
