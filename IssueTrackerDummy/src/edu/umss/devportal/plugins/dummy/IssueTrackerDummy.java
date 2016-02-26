/*
 *  @(#)IssueTrackerDummy.java   28-nov-2010
 */
package edu.umss.devportal.plugins.dummy;

import edu.umss.devportal.common.BasicProjectImpl;
import edu.umss.devportal.common.BasicUserImpl;
import edu.umss.devportal.common.ParameterDescriptor;
import edu.umss.devportal.common.ProjectRole;
import edu.umss.devportal.common.reports.DynamicStructure;
import edu.umss.devportal.common.IssueTracker;
import edu.umss.devportal.common.reports.MagicNumbers;
import edu.umss.devportal.common.Project;
import edu.umss.devportal.common.ToolDescriptor;
import edu.umss.devportal.common.User;
import edu.umss.devportal.common.notifications.BaseNotificator;
import edu.umss.devportal.common.reports.AvailableType;
import edu.umss.devportal.common.reports.BarDataStructure;
import edu.umss.devportal.common.reports.ChartReport;
import edu.umss.devportal.common.reports.ChartType;
import edu.umss.devportal.common.reports.DataStructure;
import edu.umss.devportal.common.reports.Report;
import edu.umss.devportal.common.reports.ReportDescriptor;
import edu.umss.devportal.common.reports.ReportImpl;
import edu.umss.devportal.plugins.dummy.notifications.RandomRule;
import edu.umss.edu.devportal.exception.DevPortalPluginException;
import edu.umss.edu.devportal.exception.MissingParameterException;
import edu.umss.edu.devportal.exception.NoToolServerConnectionException;
import edu.umss.edu.devportal.exception.ServerNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.management.InstanceNotFoundException;
import javax.naming.ConfigurationException;

/**
 * Class that represents an issue tracker dummy.  It implements all methods
 * returning fictitious information.
 *
 * @author Alex Arenas
 * @version 1.0
 */
public class IssueTrackerDummy extends BaseNotificator implements IssueTracker {

    private static final String DUMMY = "Dummy-";
    private static final String USR_DUMMY = "USR-";
    private static int counter = 1;
    private static int usrCounter = 1;
    private IssueTrackerDummnyDescriptor descriptor;
    private List<Project> projectList;
    private Map<String, String> config;
    private List<User> userList;
    private MagicNumbers magicNumbers;

    /**
     * Default constructor.
     */
    public IssueTrackerDummy() {
        add(new RandomRule());

        projectList = new ArrayList<Project>();
        projectList.add(new BasicProjectImpl(getNextId(), "Thesis project (Dummy)",
                "Issues of the thesis project"));
        projectList.add(new BasicProjectImpl(getNextId(), "Second project (Dummy)",
                "Second issue project"));
        projectList.add(new BasicProjectImpl(getNextId(), "Other project (Dumny)",
                "Other issue project"));

        userList = new ArrayList<User>();
        userList.add(new BasicUserImpl("user1", "User 1", getNextUserId()));
        userList.add(new BasicUserImpl("user2", "User 2", getNextUserId()));
        userList.add(new BasicUserImpl("user3", "User 3", getNextUserId()));
        magicNumbers = new MagicNumbersImpl();

    }

    private String getNextUserId() {
        return USR_DUMMY + usrCounter++;
    }

    private String getNextId() {
        return DUMMY + counter++;
    }

    public ToolDescriptor getToolDescriptor() {
        if (descriptor == null) {
            descriptor = new IssueTrackerDummnyDescriptor();
        }
        return descriptor;
    }

    public String createProject(Project project) throws Exception {
        BasicProjectImpl dummy = new BasicProjectImpl(getNextId(),
                project.getName() + " (Dummy)",
                project.getDescription());
        projectList.add(dummy);
        return "";
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void applyConfiguration(Map<String, String> config) throws ConfigurationException {
        this.config = config;
    }

    public void removeProject(String projectId) throws InstanceNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<List<String>> getReportResult(Properties parameters) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @see ToolPlugin#createUser(edu.umss.devportal.common.User)
     */
    @Override
    public String createUser(User user) throws Exception, NoToolServerConnectionException {
        BasicUserImpl usr = new BasicUserImpl(user.getLogin(), user.getName(), getNextUserId());
        userList.add(usr);
        return usr.getId();
    }

    /**
     * @see ToolPlugin#getUserList()
     */
    @Override
    public List<User> getUserList() throws NoToolServerConnectionException {
        return userList;
    }

    /**
     * @see ToolPlugin#removeUser(java.lang.String)
     */
    @Override
    public void removeUser(String userId) throws NoToolServerConnectionException, InstanceNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean testConnection(Map<String, String> config) throws MissingParameterException, ServerNotFoundException {
        return true;
    }

    public List<ParameterDescriptor> getRequiredParameters() {
        return new ArrayList<ParameterDescriptor>();
    }

    public void associateUserToProject(String projectId, String userId, ProjectRole role) throws DevPortalPluginException {
    }

    public Report getMyAssignedIssues(String projectId, String userId) {
        DynamicStructure dynamicStructure = new DynamicStructure();

        List<List<String>> result = new ArrayList<List<String>>();

        result.add(Arrays.asList(new String[]{"New", "<a href=\"http://www.google.com.bo/\">00001</a>", "Issue 1", "Developer1", "QEngineer1", "High", "High"}));
        result.add(Arrays.asList(new String[]{"Open", "<a href=\"http://www.google.com.bo/\">00002</a>", "Issue 2", "Developer1", "QEngineer1", "Medium", "Medium"}));
        result.add(Arrays.asList(new String[]{"Closed", "<a href=\"http://www.google.com.bo/\">00003</a>", "Issue 3", "Developer1", "QEngineer2", "Low", "Low"}));

        dynamicStructure.setDynamicList(result);

        List<String> headers = new ArrayList<String>();

        headers.add("State");
        headers.add("IssueID");
        headers.add("Title");
        headers.add("Owner");
        headers.add("Submiter");
        headers.add("Severity");
        headers.add("Prority");

        dynamicStructure.setHeadersList(headers);

        List<ProjectRole> viewers = new ArrayList<ProjectRole>();
        viewers.add(ProjectRole.TeamMember);

        List<ProjectRole> editors = new ArrayList<ProjectRole>();
        editors.add(ProjectRole.TeamMember);

        Report report = new ReportImpl(new ReportDescriptor(
                "My assigned issues - Team Track",
                "Grid",
                "",
                "Team Track",
                viewers,
                editors,
                AvailableType.GRID), dynamicStructure);

        return report;
    }

    public Report getAssignedIssues(String projectId, List<String> userIdList) {
        DynamicStructure dynamicStructure = new DynamicStructure();

        List<List<String>> result = new ArrayList<List<String>>();
        result.add(Arrays.asList(new String[]{"New", "<a href=\"http://www.google.com.bo/\">00001</a>", "Issue 1", "Developer1", "QEngineer1", "High", "High"}));
        result.add(Arrays.asList(new String[]{"Open", "<a href=\"http://www.google.com.bo/\">00002</a>", "Issue 2", "Developer1", "QEngineer1", "Medium", "Medium"}));
        result.add(Arrays.asList(new String[]{"Closed", "<a href=\"http://www.google.com.bo/\">00003</a>", "Issue 3", "Developer1", "QEngineer2", "Low", "Low"}));
        result.add(Arrays.asList(new String[]{"Closed", "<a href=\"http://www.google.com.bo/\">00004</a>", "Issue 4", "Developer2", "QEngineer1", "Medium", "Medium"}));
        result.add(Arrays.asList(new String[]{"Open", "<a href=\"http://www.google.com.bo/\">00005</a>", "Issue 5", "Developer2", "QEngineer1", "High", "High"}));
        result.add(Arrays.asList(new String[]{"Closed", "<a href=\"http://www.google.com.bo/\">00006</a>", "Issue 6", "Developer2", "QEngineer1", "Low", "Low"}));
        result.add(Arrays.asList(new String[]{"Open", "<a href=\"http://www.google.com.bo/\">00007</a>", "Issue 7", "Developer2", "QEngineer2", "High", "High"}));
        result.add(Arrays.asList(new String[]{"Closed", "<a href=\"http://www.google.com.bo/\">00008</a>", "Issue 8", "Developer3", "QEngineer2", "High", "High"}));
        result.add(Arrays.asList(new String[]{"Open", "<a href=\"http://www.google.com.bo/\">00009</a>", "Issue 9", "Developer3", "QEngineer2", "Low", "Low"}));
        result.add(Arrays.asList(new String[]{"Open", "<a href=\"http://www.google.com.bo/\">00010</a>", "Issue 10", "Developer3", "QEngineer2", "Low", "Low"}));

        dynamicStructure.setDynamicList(result);

        List<String> headers = new ArrayList<String>();

        headers.add("State");
        headers.add("IssueID");
        headers.add("Title");
        headers.add("Owner");
        headers.add("Submiter");
        headers.add("Severity");
        headers.add("Prority");

        dynamicStructure.setHeadersList(headers);

        List<ProjectRole> viewers = new ArrayList<ProjectRole>();
        viewers.add(ProjectRole.Manager);

        List<ProjectRole> editors = new ArrayList<ProjectRole>();
        editors.add(ProjectRole.Manager);

        Report report = new ReportImpl(new ReportDescriptor(
                "Assigned issues - Team Track",
                "Grid",
                "",
                "Team Track",
                viewers,
                editors,
                AvailableType.GRID), dynamicStructure);

        return report;
    }

    public MagicNumbers getMagicNumbers() {
        return magicNumbers;
    }

    /**
     * Gets the Issue resolution progress report
     * @param projectId
     * @return a Report that represents a Bar chart
     */
    public Report getIssueResolutionProgress(String projectId) {

        // row keys...
        final String series1 = "Remaining";
        final String series2 = "Assigned";
        final String series3 = "Resolved";

        // column keys...
        final String category1 = "Developer1";
        final String category2 = "Developer2";
        final String category3 = "Developer3";
        final String category4 = "Developer4";
        final String category5 = "Developer5";

        List<BarDataStructure> result = new ArrayList<BarDataStructure>();

        result.add(new BarDataStructure(10.0, series1, category1));
        result.add(new BarDataStructure(7.0, series1, category2));
        result.add(new BarDataStructure(6.0, series1, category3));
        result.add(new BarDataStructure(8.0, series1, category4));
        result.add(new BarDataStructure(4.0, series1, category5));

        result.add(new BarDataStructure(15.0, series2, category1));
        result.add(new BarDataStructure(14.0, series2, category2));
        result.add(new BarDataStructure(13.0, series2, category3));
        result.add(new BarDataStructure(15.0, series2, category4));
        result.add(new BarDataStructure(15.0, series2, category5));

        result.add(new BarDataStructure(5.0, series3, category1));
        result.add(new BarDataStructure(7.0, series3, category2));
        result.add(new BarDataStructure(7.0, series3, category3));
        result.add(new BarDataStructure(7.0, series3, category4));
        result.add(new BarDataStructure(11.0, series3, category5));

        ChartReport report = new ChartReport();
        report.setBarDataStructures(result);

        List<ProjectRole> viewers = new ArrayList<ProjectRole>();
        viewers.add(ProjectRole.Manager);

        List<ProjectRole> editors = new ArrayList<ProjectRole>();
        editors.add(ProjectRole.Manager);

        Report rep = new ReportImpl(
                new ReportDescriptor(
                "Issue resolution progress - Team Track",
                "Bar chart report",
                "",
                "Team Track",
                viewers,
                editors,
                ChartType.BARCHART,
                "Number of Issues (axis x)",
                "Developer (axis y)",
                600,
                900),
                (DataStructure) report);

        return rep;
    }

    /**
     * Gets the Issues Found report
     * @param projectId
     * @return a Report that represents the Issues Found
     */
    public Report getIssuesFound(String projectId) {

        // row keys...
        final String series1 = "High";
        final String series2 = "Medium";
        final String series3 = "Low";

        // column keys...
        final String category1 = "QEngineer1";
        final String category2 = "QEngineer2";
        final String category3 = "QEngineer3";
        final String category4 = "QEngineer4";
        final String category5 = "QEngineer5";

        List<BarDataStructure> result = new ArrayList<BarDataStructure>();

        result.add(new BarDataStructure(1.0, series1, category1));
        result.add(new BarDataStructure(4.0, series1, category2));
        result.add(new BarDataStructure(3.0, series1, category3));
        result.add(new BarDataStructure(5.0, series1, category4));
        result.add(new BarDataStructure(5.0, series1, category5));

        result.add(new BarDataStructure(5.0, series2, category1));
        result.add(new BarDataStructure(7.0, series2, category2));
        result.add(new BarDataStructure(6.0, series2, category3));
        result.add(new BarDataStructure(8.0, series2, category4));
        result.add(new BarDataStructure(4.0, series2, category5));

        result.add(new BarDataStructure(4.0, series3, category1));
        result.add(new BarDataStructure(3.0, series3, category2));
        result.add(new BarDataStructure(2.0, series3, category3));
        result.add(new BarDataStructure(3.0, series3, category4));
        result.add(new BarDataStructure(6.0, series3, category5));

        ChartReport report = new ChartReport();
        report.setBarDataStructures(result);

        List<ProjectRole> viewers = new ArrayList<ProjectRole>();
        viewers.add(ProjectRole.Manager);

        List<ProjectRole> editors = new ArrayList<ProjectRole>();
        editors.add(ProjectRole.Manager);

        Report rep = new ReportImpl(
                new ReportDescriptor(
                "Issues Found - Team Track",
                "Stacked bar chart report",
                "",
                "Team Track",
                viewers,
                editors,
                ChartType.STACKEDBARCHART,
                "Quality Engineer (axix x)",
                "Number of Issues (axis Y)",
                600,
                900),
                (DataStructure) report);

        return rep;
    }

    public List<Report> getCustomReports(String projectId, List<String> usrIds) throws NoToolServerConnectionException {
        List<Report> reports = new ArrayList<Report>();
        return reports;
    }

    public List<Report> getGridReports(String projectId, List<String> usrIds) throws NoToolServerConnectionException {
        List<Report> reports = new ArrayList<Report>();
        reports.add(getMyAssignedIssues(projectId, ""));
        reports.add(getAssignedIssues(projectId, new ArrayList()));

        return reports;
    }

    public List<Report> getChartReports(String projectId, List<String> usrIds) throws NoToolServerConnectionException {
        List<Report> reports = new ArrayList<Report>();
        reports.add(getIssueResolutionProgress(projectId));
        reports.add(getIssuesFound(projectId));

        return reports;
    }
}
