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
public class BarDataStructure implements DataStructure{
    private Double value;
    private String series;
    private String category;

    public BarDataStructure() {
    }

    public BarDataStructure(Double value, String series, String category) {
        this.value = value;
        this.series = series;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}