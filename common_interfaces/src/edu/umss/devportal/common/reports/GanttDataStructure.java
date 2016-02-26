/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.common.reports;

import java.util.Date;

/**
 *
 * @author Edson
 */
public class GanttDataStructure implements DataStructure{

    private String serie;
    private String description;
    private Date startDate;
    private Date endDate;

    public GanttDataStructure(String serie, String description, Date startDate, Date endDate) {
        this.serie = serie;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
