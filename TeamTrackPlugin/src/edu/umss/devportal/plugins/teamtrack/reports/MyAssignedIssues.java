/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.teamtrack.reports;

import edu.umss.devportal.common.ProjectRole;
import edu.umss.devportal.common.reports.AvailableType;
import edu.umss.devportal.common.reports.DataStructure;
import edu.umss.devportal.common.reports.DynamicStructure;
import edu.umss.devportal.common.reports.Report;
import edu.umss.devportal.common.reports.ReportDescriptor;
import edu.umss.devportal.plugins.teamtrack.model.TttIssues;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author July Camacho
 */
public class MyAssignedIssues extends GridReport implements Report{
    private ReportDescriptor reportDescriptor;
    private List<ProjectRole> viewers;
    private DynamicStructure dataStructure;
    private List<ProjectRole> editors;
    protected static final Logger logger = Logger.getLogger(MyAssignedIssues.class.getName());

    /**
     *
     */
    public MyAssignedIssues(){
        dataStructure = new DynamicStructure();
        viewers = new ArrayList<ProjectRole>();
        viewers.add(ProjectRole.TeamMember);
        viewers.add(ProjectRole.Manager);
        editors = new ArrayList<ProjectRole>();
        editors.add(ProjectRole.TeamMember);
        editors.add(ProjectRole.Manager);
        reportDescriptor = new ReportDescriptor("MyAssignedIssues",
                                                "Assigned issues by user",
                                                "",
                                                "Team Track",
                                                viewers,
                                                editors,
                                                AvailableType.GRID);
    }

    /**
     *
     * @return
     */
    public ReportDescriptor getReportDescriptor() {
        return reportDescriptor;
    }
    /**
     *
     * @return
     */
    public DataStructure getDataStructure() {
        return dataStructure;
    }
    /**
     *
     * @param projectId
     * @param usrIds
     */
    public void loadReport(String projectId, String userId) {
        List<List<String>> issuesByPerson = getIssuesByUser(projectId,userId);
        dataStructure.setDynamicList(issuesByPerson);
        dataStructure.setHeadersList(getHeaders());

    }
    /**
     *
     * @param projectId
     * @param personId
     * @return
     */
    public List<List <String>> getIssuesByUser(String projectId, String personId){
       List<List<String>> result = new ArrayList<List<String>>();
       if(!projectId.isEmpty() && !personId.isEmpty())
       {
           List<TttIssues> issuesByProject = issuesController.getIssuesByUser(Integer.parseInt(projectId),Integer.parseInt(personId));
            if(issuesByProject != null){

                for(TttIssues issue : issuesByProject){
                   List<String> data = new ArrayList<String>();
                   data.add(statesController.getStateName(issue.getTsState()));
                   data.add(getIssueLink(issue.getTsId(), issue.getTsIssueid()));
                   data.add(issue.getTsTitle());
                   data.add(usersController.getUserNameById(issue.getTsOwner()));
                   data.add(usersController.getUserNameById(issue.getTsSubmitter()));
                   data.add(selectionController.getSelectionById(issue.getTsSeverity()));
                   data.add(selectionController.getSelectionById(issue.getTsPriority()));
                   result.add(data);
                }
           }
            else
                logger.log(Level.SEVERE, "ERROR in getIssuesByUser");
       }
        else
            logger.log(Level.WARNING, "projectId and userId can't be empty ");

       return result;
    }

    public void setParameters(String projectId, List<String> usrIds) {
        loadReport(projectId, usrIds.get(0));
    }
}
