/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.xplanner.reports.structure;

import edu.umss.devportal.plugins.xplanner.reports.utils.CollectionUtility;
import edu.umss.devportal.plugins.xplanner.reports.utils.TaskFilter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import localhost.xplanner.soap.XPlanner.XPlanner;
import org.xplanner.soap.TaskData;
import org.xplanner.soap.UserStoryData;

/**
 *
 * @author =)
 */
public class UserStory extends CollectionUtility<Task,TaskColumn> {

    private UserStoryData userStory ;
    private XPlanner service ;
    private List<Task> taskOfUserStory ;
    private TaskFilter filter ;

    private static final Logger logger = Logger.getLogger(UserStory.class.getName());

    public UserStory(XPlanner service , UserStoryData userStory) {

        taskOfUserStory = new ArrayList<Task>();
        this.userStory = userStory;
        this.service= service;
        filter = new TaskFilter();
    }

    public List<Task> getTasks(){
        taskOfUserStory.clear();
        try {
            TaskData[] tasks = service.getTasks(userStory.getId());
            for (TaskData taskData : tasks) {
                Task task = new Task(service,taskData);
                taskOfUserStory.add(task);
            }
        } catch (RemoteException ex) {
            logger.log(Level.SEVERE,"Can't recover the tasks of the user story", ex);
        }
        return taskOfUserStory;
    }

    public List<Task> getTasksByProperty ( TaskColumn property , Object value ){
        return super.filterByProperty(getTasks(), filter, property, value);
    }

    public String getName() {
       return userStory.getName();
    }

    public double getEstimatedOriginalHours(){
    return userStory.getAdjustedEstimatedHours();
    }

}
