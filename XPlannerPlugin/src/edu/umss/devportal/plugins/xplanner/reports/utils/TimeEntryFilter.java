/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.xplanner.reports.utils;

import edu.umss.devportal.plugins.xplanner.reports.structure.TimeEntry;
import edu.umss.devportal.plugins.xplanner.reports.structure.TimeEntryColumn;

/**
 *
 * @author =)
 */
public class TimeEntryFilter implements Filter<TimeEntry, TimeEntryColumn> {

    @Override
    public boolean filterByProperty(TimeEntry e, TimeEntryColumn columnName, Object object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

}
