/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.xplanner.reports.structure;

import java.util.Calendar;
import org.xplanner.soap.TimeEntryData;

/**
 *
 * @author Grace
 */
public class TimeEntry {
    
    TimeEntryData timeEntryData;

    public TimeEntry( TimeEntryData timeEntryData) {
        this.timeEntryData = timeEntryData;
    }

    public Calendar getReportDate(){
    return timeEntryData.getReportDate();
    }

    public double getDuration() {
    return timeEntryData.getDuration() ;
    }

}
