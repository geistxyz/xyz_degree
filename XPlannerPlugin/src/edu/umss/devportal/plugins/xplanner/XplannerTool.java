package edu.umss.devportal.plugins.xplanner;

import edu.umss.devportal.common.reports.Report;
import edu.umss.devportal.plugins.xplanner.connection.XPlannerConnection;
import edu.umss.devportal.plugins.xplanner.connection.XPlannerWSConnection;
import edu.umss.devportal.common.ParameterDescriptor;
import edu.umss.devportal.common.Project;
import edu.umss.devportal.common.ProjectRole;
import edu.umss.devportal.common.ProjectTracker;
import edu.umss.devportal.common.ToolDescriptor;
import edu.umss.devportal.common.ToolPlugin;
import edu.umss.devportal.common.User;
import edu.umss.devportal.common.reports.ChartReport;
import edu.umss.devportal.common.reports.ChartType;
import edu.umss.devportal.common.reports.DataStructure;
import edu.umss.devportal.common.reports.LineDataStructure;
import edu.umss.devportal.common.reports.ReportDescriptor;
import edu.umss.devportal.common.reports.ReportImpl;
import edu.umss.devportal.plugins.xplanner.config.XPlannerConfig;
import edu.umss.devportal.plugins.xplanner.connection.ConnectionManager;
import edu.umss.devportal.plugins.xplanner.connection.XPlannerConnectionType;
import edu.umss.devportal.plugins.xplanner.reports.ReportManager;
import edu.umss.devportal.plugins.xplanner.reports.structure.ReportProject;
import edu.umss.edu.devportal.exception.DevPortalPluginException;
import edu.umss.edu.devportal.exception.MissingParameterException;
import edu.umss.edu.devportal.exception.NoToolServerConnectionException;
import edu.umss.edu.devportal.exception.ServerNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.management.InstanceNotFoundException;
import javax.naming.ConfigurationException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author =)
 */
public class XplannerTool implements ProjectTracker {
    
    private static final Logger logger = Logger.getLogger(XplannerTool.class.getName());
    private XPlannerConnection connection;
//    private ReportManager reportManager ;


    public XplannerTool()
    {
        this.connection = new XPlannerWSConnection();
    }

    @Override
    public ToolDescriptor getToolDescriptor() {
        return XPlannerConfig.getToolDescriptor();
    }

    public List<ParameterDescriptor> getRequiredParameters() {
        return XPlannerConfig.getRequiredParameters();
    }

    /***
     *
     * @see ToolPlugin#createProject(edu.umss.devportal.common.Project) 
     */
    @Override
    public String createProject(Project project) throws Exception {
        return connection.addObject(project);
    }

    /***
     *
     * Removes the project associated with the given project value from the XPlanner
     *
     * @param projectId value associated to project that is going to be removed
     * @throws InstanceNotFoundException If a project with given associated value
     *         does not exist in the tool.
     * @throws NoToolServerConnectionException if there is no connection to XPlanner server
     * @throws IllegalArgumentException when projectId is null or it cannot be parsed to Integer value.
     */
    public void removeProject(String projectId) throws NoToolServerConnectionException, InstanceNotFoundException
    {
        connection.removeObject(projectId, edu.umss.devportal.common.Project.class);
    }

    @Override
    public List<Project> getProjectList() {
        return connection.getProjectList();
    }

    @Override
    public void applyConfiguration(Map<String, String> config) throws ConfigurationException {
        try {
            connection = ConnectionManager.createConnection(config, XPlannerConnectionType.WebService);
        } catch (ServerNotFoundException ex) {
            throw new ConfigurationException(ex.getMessage());
        } catch (MissingParameterException ex) {
            throw new ConfigurationException(ex.getMessage());
        }
    }    

    @Override
    public boolean testConnection(Map<String, String> config) throws MissingParameterException, ServerNotFoundException {
    //    return true;
      return ConnectionManager.testConnection(config);
    }

     /***
     *
     * @see ToolPlugin#createUser(edu.umss.devportal.common.User)
     */
    public String createUser(User user) throws Exception {
        return connection.addObject(user);
    }
    
    /**
     * @see ToolPlugin#getUserList()
     */
    @Override
    public List<User> getUserList() throws NoToolServerConnectionException {
        return connection.getUserList();
    }

    /**
     * @see ToolPlugin#removeUser(java.lang.String)
     */
    @Override
    public void removeUser(String userId) throws NoToolServerConnectionException, InstanceNotFoundException {
        connection.removeObject(userId, User.class);
    }


    public void associateUserToProject(String projectId, String userId, ProjectRole role) throws DevPortalPluginException {
        connection.associateUserToProject(projectId, userId, role);
    }


    @Override
    public List<Report> getGridReports(String projectId, List<String> usrIds)
            throws NoToolServerConnectionException {

        int idProj = Integer.parseInt(projectId);
        ReportProject project = connection.getReportProject(idProj);

        if (project != null) {
            ReportManager reportManager = new ReportManager(project);
            return reportManager.getGridReports(usrIds);
        }
        return null;
    }

//    @Override
//    public List<Report> getChartReports(String projectId, List<String> usrIds)
//            throws NoToolServerConnectionException {
//         int idProj = Integer.parseInt(projectId) ;
//        ReportProject project = connection.getReportProject(idProj) ;
//
//        if ( project != null ) {
//            ReportManager reportManager = new ReportManager(project) ;
//            return reportManager.getChartReports( usrIds);
//        }
//        return null ;
//    }

    @Override
    public List<Report> getChartReports(String projectId, List<String> usrIds)
            throws NoToolServerConnectionException {
        List<Report> reportList = new ArrayList<Report>();
        reportList.add(getBurnDown(projectId, projectId));
        return reportList;
    }

    @Override
    public List<Report> getCustomReports(String projectId, List<String> usrIds)
            throws NoToolServerConnectionException {
        int idProj = Integer.parseInt(projectId) ;
        ReportProject project = connection.getReportProject(idProj) ;

        if ( project != null ) {
            ReportManager reportManager = new ReportManager(project) ;
            return reportManager.getCustomReports( usrIds);
        }
        return null ; 
    }





    private void insertRegistry (List registry, Date currentDate, int duration) {
        boolean itemFound = false;
        for (int i = 0; i < registry.size(); i++) {
            Date date = ((TimeEntry)registry.get(i)).getTimeEntryDate();
            if (date.equals(currentDate)) {
                int currentDuration = ((TimeEntry)registry.get(i)).getDuration();
                ((TimeEntry)registry.get(i)).setDuration(currentDuration + duration);
                itemFound = true;
                break; // Exit the loop
            }
        }
        if(!itemFound) {
            TimeEntry timeEntry = new TimeEntry(currentDate, duration);
            registry.add(timeEntry);
        }
    }

    private List insertNonWorkedDays(List orderedList, String startDateString, String endDateString, int workload) {
        List fullOrderedList = new ArrayList();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        workload = workload + ((TimeEntry)orderedList.get(0)).duration; // update workload

        String currentDateString = startDateString;

            Calendar outOfDateCalendar = Calendar.getInstance();
            String[] outOfDatedates = endDateString.split("-");
            int outOfDateyear = Integer.parseInt(outOfDatedates[0]);
            int outOfDatemonth = Integer.parseInt(outOfDatedates[1]);
            int outOfDateday = Integer.parseInt(outOfDatedates[2]);

            outOfDateCalendar.set(outOfDateyear, outOfDatemonth - 1, outOfDateday);

            java.util.Date utlOutOfDate = outOfDateCalendar.getTime();
            java.sql.Date outOfDate = new java.sql.Date(utlOutOfDate.getTime());

            outOfDateCalendar.add(Calendar.DATE,1);
            String outOfDateString = dateFormat.format(outOfDateCalendar.getTime());

        while (!outOfDateString.equals(currentDateString)) {

            Calendar currentDateCalendar = Calendar.getInstance();
            String[] dates = currentDateString.split("-");
            int year = Integer.parseInt(dates[0]);
            int month = Integer.parseInt(dates[1]);
            int day = Integer.parseInt(dates[2]);

            currentDateCalendar.set(year, month - 1, day);

            java.util.Date utlDate = currentDateCalendar.getTime();
            java.sql.Date currentTime = new java.sql.Date(utlDate.getTime());

            boolean nonWorkingDay = true;
            for (int i = 0; i < orderedList.size(); i++) {

                if (((TimeEntry) orderedList.get(i)).getTimeEntryDate().toString().equals(currentTime.toString())) {
                    workload = workload - ((TimeEntry)orderedList.get(i)).duration;
                    ((TimeEntry)orderedList.get(i)).setDuration(workload);
                    fullOrderedList.add(orderedList.get(i));
                    nonWorkingDay = false;
                    orderedList.remove(i); // Remove found item
                    break;
                }
            }

            if (nonWorkingDay) {
                TimeEntry timeEntry = new TimeEntry(currentTime, workload);
                fullOrderedList.add(timeEntry);
            }
            currentDateCalendar.add(Calendar.DATE,1);
            currentDateString = dateFormat.format(currentDateCalendar.getTime());
        }

        return fullOrderedList;
    }

    public Report getBurnDown(String projectId, String userId) {

        List<LineDataStructure> result = new ArrayList<LineDataStructure>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat reportDateFormat = new SimpleDateFormat("MM-dd");

        try
        {
            // Register the MySQL Driver
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());

            String userName = "root";
            String password = "Control123";

            // Get the connection
            Connection conexion = DriverManager.getConnection (
                "jdbc:mysql://localhost/xplanner", userName, password);

            // A statement its been created in order to perform que obtainAllTimeEntries
            Statement statement = conexion.createStatement();

            // Obtain all time entries
            String obtainAllTimeEntries = "SELECT time_entry.report_date, time_entry.duration "
                    + "FROM time_entry INNER JOIN "
                    + "(task INNER JOIN (story INNER JOIN "
                    + "(iteration INNER JOIN project "
                    + "ON iteration.project_id=project.id "
                    + "AND project.id=" + projectId + ") "
                    + "ON story.iteration_id=iteration.id) "
                    + "ON task.story_id=story.id) "
                    + "ON time_entry.task_id=task.id";

            ResultSet resultSetTimeEntries = statement.executeQuery (obtainAllTimeEntries);
            List timeEntryList = new ArrayList();

            // Fill timeEntryList with time entries, (date, duration)
            while (resultSetTimeEntries.next())
            {
                Date reportDate = resultSetTimeEntries.getDate("report_date");
                int duration = Integer.parseInt(resultSetTimeEntries.getString ("duration"));
                TimeEntry timeEntry = new TimeEntry(reportDate, duration);
                timeEntryList.add(timeEntry);
            }

            // Create a list for ordered time entries
            List orderedTimeEntryList = new ArrayList();

            // Add time entries to ordered time entries
            for (int j = 0; j < timeEntryList.size(); j++) {

                Date date = ((TimeEntry)timeEntryList.get(j)).getTimeEntryDate();
                int duration = ((TimeEntry)timeEntryList.get(j)).getDuration();
                insertRegistry(orderedTimeEntryList, date, duration);
            }

            // Sort list with all entries by day
            Collections.sort(orderedTimeEntryList);

            // Get the Planning date
            Date firstIterationDate = ((TimeEntry)orderedTimeEntryList.get(0)).getTimeEntryDate();
            Calendar planningDateCalendar = Calendar.getInstance();
            String[] firstDate = firstIterationDate.toString().split("-");
            int yearFirstDate = Integer.parseInt(firstDate[0]);
            int monthFirstDate = Integer.parseInt(firstDate[1]);
            int dayFirstDate = Integer.parseInt(firstDate[2]);

            planningDateCalendar.set(yearFirstDate, monthFirstDate - 1, dayFirstDate);
            // Calculates the Planning day substracting one day to the fist iteration day
            planningDateCalendar.add(Calendar.DATE, -1);
            TimeEntry planningDateTimeEntry = new TimeEntry(planningDateCalendar.getTime(), 0);
            // Add planning burndown to ordered time entry list
            orderedTimeEntryList.add(planningDateTimeEntry);
            
            // Sort list with all entries by day Include planning date
            Collections.sort(orderedTimeEntryList);

            String obtainItarationDuration = "SELECT start_date, end_date FROM iteration where project_id=" + projectId;
            ResultSet resultSetIterationDuration = statement.executeQuery (obtainItarationDuration);

            Date startDate = null;
            Date endDate = null;

            while (resultSetIterationDuration.next())
            {
                System.out.println ("Start Date: " + resultSetIterationDuration.getDate ("start_date") +
                                    ", End Date: " + resultSetIterationDuration.getDate("end_date"));

                startDate = resultSetIterationDuration.getDate ("start_date");
                endDate = resultSetIterationDuration.getDate ("end_date");
            }

            java.util.Date utlPlanningDate = planningDateCalendar.getTime();
            java.sql.Date startDateDate = new java.sql.Date(utlPlanningDate.getTime());

            // Set start and end iteration dates
            String startDateString = startDateDate.toString();
            String endDateString = endDate.toString();

            int estimatedHours = 0;
            int workload = 0;
            int totalHours = 0;
            String obtainEstimatedHours = "SELECT original_estimated_hours FROM story";
            ResultSet resultSetEstimatedHours = statement.executeQuery (obtainEstimatedHours);
            while (resultSetEstimatedHours.next())
            {
                estimatedHours = estimatedHours + resultSetEstimatedHours.getInt ("original_estimated_hours");
                workload = workload + resultSetEstimatedHours.getInt ("original_estimated_hours");
                totalHours = totalHours + resultSetEstimatedHours.getInt ("original_estimated_hours");
            }

            // Insert non working dates
            List fullList = insertNonWorkedDays(orderedTimeEntryList, startDateString, endDateString, estimatedHours);
            int iterationDays = 0;

            String currentDateString = startDateString;
            while (! endDateString.equals(currentDateString)){

                Calendar currentDateCalendar = Calendar.getInstance();
                String [] dates = currentDateString.split("-");
                int year = Integer.parseInt(dates[0]);
                int month = Integer.parseInt(dates[1]);
                int day = Integer.parseInt(dates[2]);

                currentDateCalendar.set(year, month - 1, day);
                currentDateString = dateFormat.format(currentDateCalendar.getTime());

                currentDateCalendar.add(Calendar.DATE,1);   // or  Calendar.DAY_OF_MONTH which is a synonym
                currentDateString = dateFormat.format(currentDateCalendar.getTime());

                iterationDays++;
            }

            // Build the Burn down fields
            for (int i = 0; i < fullList.size(); i++) {
                TimeEntry timeEntry = (TimeEntry)fullList.get(i);
                LineDataStructure burnDown = null;
                if (i == 0) {
                    burnDown = new LineDataStructure(timeEntry.duration, "BurnDown", "Planning");
                } else {
                    burnDown = new LineDataStructure(timeEntry.duration, "BurnDown", reportDateFormat.format(timeEntry.getTimeEntryDate()));
                }

                result.add(burnDown);
            }

            // Build the work load fields
            for (int i = 0; i < fullList.size(); i++) {
                TimeEntry timeEntry = (TimeEntry)fullList.get(i);
                LineDataStructure burnDown = null;
                if (i == 0) {
                    burnDown = new LineDataStructure(workload, "WorkLoad", "Planning");
                } else {
                    burnDown = new LineDataStructure(workload, "WorkLoad", reportDateFormat.format(timeEntry.getTimeEntryDate()));
                }
                result.add(burnDown);
            }

            int decrement = totalHours/iterationDays + 1;

            // Build the Linear Burn down fields
            for (int i = 0; i < fullList.size(); i++) {
                TimeEntry timeEntry = (TimeEntry)fullList.get(i);
                LineDataStructure burnDown = null;
                if (i == 0) {
                    burnDown = new LineDataStructure(totalHours, "Linear (BurnDown)", "Planning");
                } else {
                    burnDown = new LineDataStructure(totalHours, "Linear (BurnDown)", reportDateFormat.format(timeEntry.getTimeEntryDate()));
                }
                result.add(burnDown);
                totalHours = totalHours - decrement;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        ChartReport report = new ChartReport();
        report.setLineDataStructureList(result);

        List<ProjectRole> viewers = new ArrayList<ProjectRole>();
        viewers.add(ProjectRole.Manager);
        viewers.add(ProjectRole.TeamMember);

        List<ProjectRole> editors = new ArrayList<ProjectRole>();
        editors.add(ProjectRole.Manager);

        DataStructure dataStructure = (DataStructure) report;

        Report burnDownReport = new ReportImpl(
                new ReportDescriptor(
                "Burn down chart - XPlanner",   // Report Name
                "Chart report",                 // Report Description
                "",                             // Path
                "XPlanner",                     // Tool
                viewers,                        // List of viewers
                editors,                        // List of editors
                ChartType.LINECHART,            // Report type
                "Date (axis x)",                // Axis X label
                "Hours (axis y)",               // Axis Y label
                600,                            // Height
                900),                           // Width
                dataStructure );        // Data to generate the report

        return burnDownReport;
    }
}
