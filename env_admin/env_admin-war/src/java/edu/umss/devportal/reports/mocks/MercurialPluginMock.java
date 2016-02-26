/*
 * @(#)MercurialPluginMock.java 11/04/22
 *
 * Copyright (c) 2011 Universidad Mayor de San Simón
 * Cochabamba - Bolivia
 * All Rights Reserved.
 */
package edu.umss.devportal.reports.mocks;

import edu.umss.devportal.common.reports.ReportImpl;
import edu.umss.devportal.common.ParameterDescriptor;
import edu.umss.devportal.common.Project;
import edu.umss.devportal.common.ProjectRole;
import edu.umss.devportal.common.ToolDescriptor;
import edu.umss.devportal.common.ToolPlugin;
import edu.umss.devportal.common.User;
import edu.umss.devportal.common.reports.AvailableType;
import edu.umss.devportal.common.reports.DynamicStructure;
import edu.umss.devportal.common.reports.Report;
import edu.umss.devportal.common.reports.ReportDescriptor;
import edu.umss.edu.devportal.exception.DevPortalPluginException;
import edu.umss.edu.devportal.exception.MissingParameterException;
import edu.umss.edu.devportal.exception.NoToolServerConnectionException;
import edu.umss.edu.devportal.exception.ServerNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.management.InstanceNotFoundException;
import javax.naming.ConfigurationException;

/**
 * Class that simulates a plugin with the necesary data to generete all 
 * supported reports.
 *
 * @author Edson
 * @see ToolPlugin
 */
public class MercurialPluginMock implements ToolPlugin {


    /**
     * Gets a Grid Report
     * @param projectId
     * @return a Report that represents a Change set grid
     */
    public Report getMockGridReport(String projectId) {

        DynamicStructure dynamicStructure = new DynamicStructure();
        List<List<String>> result = new ArrayList<List<String>>();

        result.add(Arrays.asList(
                new String[]{
                    "2 days ago",
                    "Developer1",
                    "<a href=\"https:"
                    + "//200.58.87.26/hg/titulacion/admin_ambientes"
                    + "/rev/2ff2c2113609\">"
                    + "Added: Dialog to set the criteria, "
                    + "that reads form .properties file "
                    + "(TODO: use database).</a>"}));

        result.add(Arrays.asList(
                new String[]{
                    "3 days ago",
                    "Developer1",
                    "<a href=\"https:"
                    + "//200.58.87.26/hg/titulacion/admin_ambientes"
                    + "/rev/87aec3793b8b\">Add: a new User interface "
                    + "implementation used in TeamTrack plugin</a>"}));

        result.add(Arrays.asList(
                new String[]{
                    "4 days ago",
                    "Developer2",
                    "<a href=\"https:"
                    + "//200.58.87.26/hg/titulacion/admin_ambientes"
                    + "/rev/e2c7110d48e7\">plug-in integration "
                    + "with xplanner</a>"}));

        result.add(Arrays.asList(
                new String[]{
                    "5 days ago",
                    "Developer1",
                    "<a href=\"https:"
                    + "//200.58.87.26/hg/titulacion/admin_ambientes"
                    + "/rev/2ff2c2113609\">Added: Dialog to set the "
                    + "criteria, that reads form .properties file "
                    + "(TODO: use database).</a>"}));

        result.add(Arrays.asList(
                new String[]{
                    "6 days ago",
                    "Developer2",
                    "<a href=\"https:"
                    + "//200.58.87.26/hg/titulacion/admin_ambientes"
                    + "/rev/87aec3793b8b\">Add: a new User interface "
                    + "implementation used in TeamTrack plugin</a>"}));

        result.add(Arrays.asList(
                new String[]{
                    "7 days ago",
                    "Developer3",
                    "<a href=\"https:"
                    + "//200.58.87.26/hg/titulacion/admin_ambientes"
                    + "/rev/e2c7110d48e7\">plug-in integration with "
                    + "xplanner</a>"}));

        dynamicStructure.setDynamicList(result);

        List<String> headers = new ArrayList<String>();

        headers.add("Age");
        headers.add("Author");
        headers.add("Description");

        dynamicStructure.setHeadersList(headers);

        List<ProjectRole> viewers = new ArrayList<ProjectRole>();
        viewers.add(ProjectRole.Manager);

        List<ProjectRole> editors = new ArrayList<ProjectRole>();
        editors.add(ProjectRole.Manager);

        Report report = new ReportImpl(
                new ReportDescriptor(
                "Changeset - Mercurial",
                "comment Grid",
                "/resources/reports/changeSetReport.xhtml",
                "Mercurial",
                viewers,
                editors,
                AvailableType.GRID),
                dynamicStructure);

        return report;
    }

    @Override
    public ToolDescriptor getToolDescriptor() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Project> getProjectList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void applyConfiguration(Map<String, String> config)
            throws ConfigurationException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean testConnection(Map<String, String> config)
            throws MissingParameterException, ServerNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String createProject(Project project)
            throws Exception, NoToolServerConnectionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeProject(String projectId)
            throws NoToolServerConnectionException, InstanceNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String createUser(User user)
            throws Exception, NoToolServerConnectionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> getUserList() throws NoToolServerConnectionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeUser(String userId)
            throws NoToolServerConnectionException, InstanceNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ParameterDescriptor> getRequiredParameters() {
        return new ArrayList<ParameterDescriptor>();
    }

    @Override
    public void associateUserToProject(
            String projectId,
            String userId,
            ProjectRole role) throws DevPortalPluginException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Report> getGridReports(String projectId, List<String> usrIds)
            throws NoToolServerConnectionException {
        List<Report> reports = new ArrayList<Report>();
        reports.add(getMockGridReport(projectId));

        return reports;
    }

    @Override
    public List<Report> getChartReports(String projectId, List<String> usrIds)
            throws NoToolServerConnectionException {
        List<Report> reports = new ArrayList<Report>();

        return reports;
    }

    @Override
    public List<Report> getCustomReports(String projectId, List<String> usrIds)
            throws NoToolServerConnectionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}