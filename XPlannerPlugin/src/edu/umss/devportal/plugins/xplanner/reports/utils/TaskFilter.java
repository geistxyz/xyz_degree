/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.xplanner.reports.utils;

import edu.umss.devportal.plugins.xplanner.reports.structure.Task;
import edu.umss.devportal.plugins.xplanner.reports.structure.TaskColumn;

/**
 *
 * @author =)
 */
public class TaskFilter implements Filter<Task, TaskColumn>{

    @Override
    public boolean filterByProperty(Task e, TaskColumn columnName, Object object) {
        boolean result = false ;
        switch (columnName){
            case ACCEPTOR_ID: {
                int acceptorId = e.getAcceptorId();
                String usrId = (String) object;
                int value = Integer.parseInt(usrId);
                result = acceptorId == value ? true : false;
            }
            
        }
        return result;
    }

}
