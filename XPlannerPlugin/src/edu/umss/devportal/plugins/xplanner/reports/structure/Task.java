/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.xplanner.reports.structure;

import edu.umss.devportal.plugins.xplanner.reports.utils.TimeEntryFilter;
import edu.umss.devportal.plugins.xplanner.reports.utils.CollectionUtility;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import localhost.xplanner.soap.XPlanner.XPlanner;
import org.xplanner.soap.TaskData;
import org.xplanner.soap.TimeEntryData;

/**
 *
 * @author =)
 */
public class Task extends CollectionUtility<TimeEntry , TimeEntryColumn> {

    private TaskData taskdata ;
    private XPlanner service ;
    private List<TimeEntry> timeEntries ;
    private TimeEntryFilter filter ;
   
    Task(XPlanner service, TaskData taskData) {
        this.taskdata = taskData;
        this.service = service;
        timeEntries = new ArrayList<TimeEntry>();
        filter = new TimeEntryFilter();
    }

    

    public int getAcceptorId(){
       return taskdata.getAcceptorId();
    }

    public List<TimeEntry> getTimeEntries() {
        timeEntries.clear();
        try {
            TimeEntryData[] timeEntries1 = service.getTimeEntries(taskdata.getId());
            for (TimeEntryData timeEntryData : timeEntries1) {
                TimeEntry timeEntry = new TimeEntry(timeEntryData);
                timeEntries.add(timeEntry);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
        }

        return timeEntries;
    }

  

    public String getName() {
     return taskdata.getName();
    }

    public String getDescription() {
        return taskdata.getDescription();
    }

    public double getEstimatedHours() {
      return taskdata.getEstimatedHours();
    }


    public double getRemainingHours() {
        return taskdata.getRemainingHours();
    }

    public double getEstimatedOriginalHours (){
        return taskdata.getAdjustedEstimatedHours();
    }

    public Calendar getLastUpdateTime (){
        return taskdata.getLastUpdateTime() ;
    }

    public List<TimeEntry> filterByProperty( TimeEntryColumn columnName , Object value ){
        return super.filterByProperty(getTimeEntries(), filter, columnName, value);
    }


}
