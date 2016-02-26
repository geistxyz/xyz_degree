/*
 * @(#)ToolPlugin.java
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.common;

import edu.umss.devportal.common.notifications.NotificationResponse;
import edu.umss.devportal.common.notifications.NotificationRule;
import edu.umss.devportal.common.reports.Report;
import edu.umss.devportal.common.reports.ReportDescriptor;
import edu.umss.edu.devportal.exception.DevPortalPluginException;
import edu.umss.edu.devportal.exception.MissingParameterException;
import edu.umss.edu.devportal.exception.NoToolServerConnectionException;
import edu.umss.edu.devportal.exception.ServerNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.management.InstanceNotFoundException;
import javax.naming.ConfigurationException;

/**
 * Provides generic methods that have to be implemented by all plugins.  This
 * methods will be used by the system to execute main common operations in all
 * implemented plugins.
 *
 * @version 1.0
 */
public interface ToolPlugin {

    /**
     * @return the tool descriptor of the current plugin.
     */
    ToolDescriptor getToolDescriptor();

    /**
     * @return all project list that the tool manages.
     */
    List<Project> getProjectList();
    void applyConfiguration(Map<String, String> config) throws ConfigurationException;

    /***
     *
     * Test if a connection could be established.
     *
     * @param config Map of parameters used to establish the connection.
     * @return Returns true if the connection can be established.
     * @throws MissingParameterException if any of required parameters for establishing the connection to the server has missed.
     * @throws ServerNotFoundException if server could not be found or its not running the service.
     */
    boolean testConnection(Map<String, String> config) throws MissingParameterException, ServerNotFoundException;

    /**
     * Permits to create a new project in the tool that manages the plugin.
     * That's it, the plugin should create a project, generally in the tool
     * database using any way to access it.
     *
     * @param project project that will be created.
     * @return a string that represents the project identifier.
     * @throws Exception it an error happens while the project is created.
     * @throws NoToolServerConnectionException if there is no connection to plugin server.
     */
    String createProject(Project project) throws Exception, NoToolServerConnectionException;

    /**
     * Permits to apply certain configuration to the plugin, it will be used
     * to connect with the tool for read and write information.
     * Removes from the tool the project associated with the given project value.
     *
     * @param config a map that contains the configuration in the form key-value.
     * @throws NoToolServerConnectionException if there is no connection to plugin server.
     * @throws ConfigurationException if happens some error in the configuration
     *              process.
     */
    void removeProject(String projectId)
            throws NoToolServerConnectionException, InstanceNotFoundException;

    
    /**
     * Method that permits create a new user.
     *
     * @param user user to be created.
     * @return a string that represents the user identifier
     * @throws Exception if an error happens while the user is created.
     * @throws NoToolServerConnectionException if there is no connection to plugin server.
     */
    public String createUser(User user) throws Exception, NoToolServerConnectionException;

    /**
     * Method that permits to get all registered users in the plugin
     *
     * @return all registered users.
     * @throws NoToolServerConnectionException if there is no conextion to plugin server.
     */
    public List<User> getUserList() throws NoToolServerConnectionException;

    /**
     * Permits to remove a user from the tool plugin.
     *
     * @param userId user's identifier to be removed.
     * @throws NoToolServerConnectionException if there is no conection to plugin server.
     * @throws InstanceNotFoundException if the user's identifier does not found.
     */
    public void removeUser(String userId)
            throws NoToolServerConnectionException, InstanceNotFoundException;

    /**
     * List of ParameterDescriptor objects, those parameters are required for plug-in configuration .
     * @return Returns a list of required parameters.
     */
    public List<ParameterDescriptor> getRequiredParameters() ;

    /**
     * Associates the user to the given project using ProjectRole to give to the user
     * enough privilegies
     * @param projectId
     * @param userId
     * @param role
     * @throws DevPortalPluginException
     */
    void associateUserToProject(String projectId, String userId, ProjectRole role) throws DevPortalPluginException;

    //List<NotificationRule> getAvailableNotificationRules();
    //NotificationResponse executeNotificationRule(String identifier, Properties prop);
    
    public List<Report> getGridReports(String projectId, List<String> usrIds) throws NoToolServerConnectionException;

    public List<Report> getChartReports(String projectId, List<String> usrIds) throws NoToolServerConnectionException;

    public List<Report> getCustomReports(String projectId, List<String> usrIds) throws NoToolServerConnectionException;
}
