/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.reports.utilities;

/**
 *
 * @author Raul Garvizu
 */
public class CellContent {

    /**
     * data to be rendered as inner text in the table cells
     */
    private String data;

    /**
     * CSS style to apply to the cell, ie:
     */
    private String style;

    public CellContent(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
