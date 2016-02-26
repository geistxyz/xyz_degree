/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.xplanner.connection;

import edu.umss.devportal.common.ProjectRole;
import edu.umss.devportal.common.reports.LineDataStructure;
import edu.umss.devportal.common.Project;
import edu.umss.devportal.common.User;
import edu.umss.devportal.common.reports.DynamicStructure;
import edu.umss.devportal.plugins.xplanner.reports.structure.ReportProject;
import edu.umss.edu.devportal.exception.NoToolServerConnectionException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.naming.ConfigurationException;

/**
 *
 * @author raul lopez
 */
public interface XPlannerConnection {

    /**
     * Adds an object into XPlanner.
     *
     * @param object Object that will be inserted into XPlanner.
     * @return The object id converted into an string object
     * @throws NoToolServerConnectionException if there is no connection to XPlanner server
     * @throws IllegalArgumentException if the given object is null or it is not managed by the system.
     */    
    public String addObject(Object object) throws NoToolServerConnectionException, IllegalArgumentException;

    public void removeObject(String objectId, Class clazz) throws NoToolServerConnectionException;

    void applyConfiguration(Map<String, String> config) throws ConfigurationException;

    List<User> getUserList();

    public List<Project> getProjectList();

    /**
     * Associates a given with the project providing it the role priviledges.
     * @param projectId
     * @param userId
     * @param projectRole
     * @throws NoToolServerConnectionException if there is no connection to XPlanner server
     */
    public void associateUserToProject(String projectId, String userId, ProjectRole projectRole) throws NoToolServerConnectionException ;
    
    public abstract ReportProject getReportProject (int projectId)  throws NoToolServerConnectionException;
}
