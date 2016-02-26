package edu.umss.devportal.plugins.xplanner;

import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class TimeEntry implements Comparable<TimeEntry>{
    Date timeEntryDate;
    int duration;

    public TimeEntry (Date timeEntryDate, int duration) {
        this.timeEntryDate = timeEntryDate;
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getTimeEntryDate() {
        return timeEntryDate;
    }

    public void setTimeEntryDate(Date timeEntryDate) {
        this.timeEntryDate = timeEntryDate;
    }

    @Override
    public int compareTo(TimeEntry currentTimeEntry) {
        return getTimeEntryDate().compareTo(currentTimeEntry.getTimeEntryDate());
    }
}
