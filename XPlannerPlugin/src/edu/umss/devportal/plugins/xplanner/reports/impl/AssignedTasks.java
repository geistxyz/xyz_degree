/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.xplanner.reports.impl;

import edu.umss.devportal.common.ProjectRole;
import edu.umss.devportal.common.reports.AvailableType;
import edu.umss.devportal.common.reports.DataStructure;
import edu.umss.devportal.common.reports.DynamicStructure;
import edu.umss.devportal.common.reports.ReportDescriptor;
import edu.umss.devportal.common.reports.TypeOfReport;
import edu.umss.devportal.plugins.xplanner.reports.structure.ReportProject;
import edu.umss.devportal.plugins.xplanner.reports.XplannerReport;
import edu.umss.devportal.plugins.xplanner.reports.structure.Iteration;
import edu.umss.devportal.plugins.xplanner.reports.structure.Task;
import edu.umss.devportal.plugins.xplanner.reports.structure.TaskColumn;
import edu.umss.devportal.plugins.xplanner.reports.structure.UserStory;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author =)
 */
public class AssignedTasks implements XplannerReport {
    private ReportDescriptor reportDescriptor ;
    private DynamicStructure assignedTasks;
    private ReportProject reportProject;
    private List<String> userIds;

    private static final Logger logger = Logger.getLogger(AssignedTasks.class.getName());

    public AssignedTasks() {
        assignedTasks = new DynamicStructure();
        userIds = new ArrayList<String> ();
    }


    @Override
    public ReportDescriptor getReportDescriptor() {
        if (reportDescriptor == null) {
            List<ProjectRole> viewers = new ArrayList<ProjectRole>();
            viewers.add(ProjectRole.TeamMember);

            List<ProjectRole> editors = new ArrayList<ProjectRole>();
            editors.add(ProjectRole.TeamMember);

            reportDescriptor = new ReportDescriptor(
                    "Assigned Tasks",
                    "Grid",
                    "",
                    "XPlanner",
                    viewers,
                    editors, AvailableType.GRID);

        }
        return reportDescriptor;
    }
    private void setHeadears() {

        List<String> headers= new ArrayList<String>();

        headers.add("Person Name");
        headers.add("Project Name");
        headers.add("Iteration");
        headers.add("User Story");
        headers.add("Task");
        headers.add("Description");
        headers.add("Estimated Hours");
        headers.add("Remaining Hours");

        assignedTasks.setHeadersList(headers);
    }

    private List<String> createEntry(String personName, String projectName,
            String iterationName, String userStory, Task task) {
        List<String> reportEntry= new ArrayList<String>();
        reportEntry.add(personName);
        reportEntry.add(projectName);
        reportEntry.add(iterationName);
        reportEntry.add(userStory);
        reportEntry.add(task.getName());
        reportEntry.add(task.getDescription());
        reportEntry.add(Double.toString(task.getEstimatedHours()));
        reportEntry.add(Double.toString(task.getRemainingHours()));
        return reportEntry;
    }



    @Override
    public DataStructure getDataStructure() {
        List<List<String>> tasksReport = new ArrayList<List<String>>() ;
        String projectName = reportProject.getProjectName();
        Iteration currentIteration = reportProject.getCurrentIteration();

        List<UserStory> userStories = currentIteration.getUserStories();
        List<Task> tasksByProperty ;
        String userStoryName = null;
        for (UserStory userStory : userStories) {
            userStoryName = userStory.getName();
            for (String userId : userIds) {
                tasksByProperty = userStory.getTasksByProperty(TaskColumn.ACCEPTOR_ID, userId);
                for (Task task : tasksByProperty) {
                    tasksReport.add(createEntry(userId, projectName, currentIteration.getName(),
                            userStoryName, task));
                }
            }
        }
        
        setHeadears();
        
        assignedTasks.setDynamicList(tasksReport);
        return assignedTasks;
    }

    @Override
    public void setReportProject(ReportProject reportPrj) {
        this.reportProject = reportPrj;
    }   
    
    @Override
    public void setUserIds(List<String> userIds) {
        this.userIds= userIds;
    }

    @Override
    public TypeOfReport getTypeOfReport() {
        return  AvailableType.GRID ; 
    }

    //We do not use this method because the userIds and Project Id is already set 
    @Override
    public void setParameters(String projectId, List<String> usrIds) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
