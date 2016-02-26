/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.dummy;

import edu.umss.devportal.common.BasicProjectImpl;
import edu.umss.devportal.common.BasicUserImpl;
import edu.umss.devportal.common.ParameterDescriptor;
import edu.umss.devportal.common.ProjectRole;
import edu.umss.devportal.common.reports.DynamicStructure;
import edu.umss.devportal.common.reports.LineDataStructure;
import edu.umss.devportal.common.Project;
import edu.umss.devportal.common.ProjectTracker;
import edu.umss.devportal.common.ToolDescriptor;
import edu.umss.devportal.common.ToolDescriptorImpl;
import edu.umss.devportal.common.ToolPlugin;
import edu.umss.devportal.common.ToolVersion;
import edu.umss.devportal.common.User;
import edu.umss.devportal.common.reports.AvailableType;
import edu.umss.devportal.common.reports.ChartReport;
import edu.umss.devportal.common.reports.ChartType;
import edu.umss.devportal.common.reports.DataStructure;
import edu.umss.devportal.common.reports.GanttDataStructure;
import edu.umss.devportal.common.reports.PieDataStructure;
import edu.umss.devportal.common.reports.Report;
import edu.umss.devportal.common.reports.ReportDescriptor;
import edu.umss.devportal.common.reports.ReportImpl;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.InstanceNotFoundException;
import javax.naming.ConfigurationException;

/**
 *
 * This class is intended to provide a mock Project Tracker Tool Plug-in for
 * testing purposes.
 *
 * @author Raul Lopez
 * @version 1.0
 */
public class DummyProjectTracker implements ProjectTracker {

    private Map<Integer, BasicProjectImpl> projects;
    private IdGenerator idGenerator;

    private ToolDescriptor toolDescriptor;

    public static final String ToolName = "Dummy Tool";
    public static final String ToolDescription = "Project tracker dummy tool for testing purposes";
    public static final int MajorVersion = 1;
    public static final int MinorVersion = 0;


    //Logger
    private static final Logger logger = Logger.getLogger(DummyProjectTracker.class.getName());

    public DummyProjectTracker()
    {
        projects = new HashMap<Integer, BasicProjectImpl>();
        idGenerator = new IdGenerator();   
    }

    public ToolDescriptor getToolDescriptor() {
        if(toolDescriptor == null)
        {
        
            toolDescriptor = new ToolDescriptorImpl(
                    ToolName,
                    ToolDescription,
                    new ToolVersion(MajorVersion, MinorVersion));
        }

        return toolDescriptor;
    }

    public String createProject(Project project) throws Exception, NoToolServerConnectionException {
        
        Integer id = idGenerator.GenerateId();
        String projectId = id.toString();

        BasicProjectImpl basicProject = new BasicProjectImpl(                
                project.getName(),
                project.getDescription(),
                projectId);

        projects.put(id, basicProject);

        return projectId.toString();

    }

    public List<Project> getProjectList() {

        ProjectDummy projectDummy = new ProjectDummy();
        projectDummy.setId("1111");
        projectDummy.setName("example");
        projectDummy.setDescription("description example");

        ArrayList<Project> projectList = new ArrayList<Project>();
        projectList.add(projectDummy);
        return projectList;
    }

    public void applyConfiguration(Map<String, String> config) throws ConfigurationException {        
    }

    public List<List<String>> getReportResult(Properties parameters) throws NoToolServerConnectionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeProject(String projectId)
            throws InstanceNotFoundException, NoToolServerConnectionException {
        try {

            Integer id = Integer.parseInt(projectId);
            if (!projects.containsKey(id)) {
                StringBuilder sb = new StringBuilder("Project instance not found with asssociated value : ");
                sb.append(id);
                throw new InstanceNotFoundException(sb.toString());
            }
            projects.remove(id);
        } catch (NumberFormatException ex) {
            logger.log(Level.INFO, "Failed parsing projectId to integer" , ex);
            StringBuilder sb = new StringBuilder("Given projectId is null or it does not contain a parsable integer: ");
            if(projectId != null)
                sb.append(projectId);
            throw new IllegalArgumentException();
        }
    }


    /**
     * @see ToolPlugin#createUser(edu.umss.devportal.common.User)
     */
    @Override
    public String createUser(User user) throws Exception, NoToolServerConnectionException {
        return "123456789";
    }

    /**
     * @see ToolPlugin#getUserList()
     */
    @Override
    public List<User> getUserList() throws NoToolServerConnectionException {
        User user = new BasicUserImpl();
        user.setId("1");
        user.setLogin("JUAN");
        user.setName("JUAN PEREZ");
        user.setPassword("PEREZ");

        User user1 = new BasicUserImpl();
        user1.setId("2");
        user1.setLogin("JUANA");
        user1.setName("JUANA PEREZ");
        user1.setPassword("PEREZ");

        List<User> list = new ArrayList<User>();
        list.add(user);
        list.add(user1);
        return list;
    }

    /**
     * @see ToolPlugin#removeUser(java.lang.String) 
     */
    @Override
    public void removeUser(String userId) throws NoToolServerConnectionException, InstanceNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
        //TODO implement the remove user method
    }

    public boolean testConnection(Map<String, String> config) throws MissingParameterException, ServerNotFoundException {
        return true;
    }

    public Report getAssignedTasks(String projectId, List<String> usrIds) {
        List<List<String>> result = new ArrayList<List<String>>();

        result.add(Arrays.asList(new String[]{"Developer1", "Environments Management", "Iteration 3", "Research", "JEE6", "<a href=\"https://200.58.87.26:8080/xplanner/do/view/task?oid=312\">1. Review the documentation</a>", "10 Hrs", "8 Hrs"}));
        result.add(Arrays.asList(new String[]{"Developer1", "Environments Management", "Iteration 3", "Research", "JSF", "<a href=\"https://200.58.87.26:8080/xplanner/do/view/task?oid=313\">2. Develop a short example</a>", "3 Hrs", "1 Hrs"}));
        result.add(Arrays.asList(new String[]{"Developer2", "Environments Management", "Iteration 3", "Research", "JEE6", "<a href=\"https://200.58.87.26:8080/xplanner/do/view/task?oid=255\">Develop a short application</a>", "10 Hrs", "8 Hrs"}));
        result.add(Arrays.asList(new String[]{"Developer2", "Environments Management", "Iteration 3", "Research", "JFreeChart", "<a href=\"https://200.58.87.26:8080/xplanner/do/view/task?oid=254\">Review the documentation</a>", "6 Hrs", "1 Hrs"}));
        result.add(Arrays.asList(new String[]{"Developer3", "Environments Management", "Iteration 3", "Research", "JEE6", "<a href=\"https://200.58.87.26:8080/xplanner/do/view/task?oid=255\">Automate a short application</a>", "10 Hrs", "8 Hrs"}));
        result.add(Arrays.asList(new String[]{"Developer3", "Environments Management", "Iteration 3", "Research", "JFreeChart", "<a href=\"https://200.58.87.26:8080/xplanner/do/view/task?oid=254\">Refactor code</a>", "6 Hrs", "1 Hrs"}));
        result.add(Arrays.asList(new String[]{"QEngineer1", "Environments Management", "Iteration 3", "Test", "Module 1", "<a href=\"https://200.58.87.26:8080/xplanner/do/view/task?oid=254\">Test module 1</a>", "6 Hrs", "1 Hrs"}));
        result.add(Arrays.asList(new String[]{"QEngineer1", "Environments Management", "Iteration 3", "Test", "Module 2", "<a href=\"https://200.58.87.26:8080/xplanner/do/view/task?oid=255\">Test module 2 </a>", "10 Hrs", "8 Hrs"}));
        result.add(Arrays.asList(new String[]{"QEngineer2", "Environments Management", "Iteration 3", "Research", "Testing tools", "<a href=\"https://200.58.87.26:8080/xplanner/do/view/task?oid=254\">Research on testing tools</a>", "6 Hrs", "1 Hrs"}));

        DynamicStructure dynamicStructure = new DynamicStructure();
        dynamicStructure.setDynamicList(result);

        List<String> generatedDynamicHeaders = new ArrayList();
        generatedDynamicHeaders.add("Person");
        generatedDynamicHeaders.add("Project");
        generatedDynamicHeaders.add("Iteration");
        generatedDynamicHeaders.add("US");
        generatedDynamicHeaders.add("Task");
        generatedDynamicHeaders.add("Description");
        generatedDynamicHeaders.add("Estimated");
        generatedDynamicHeaders.add("Remaining");

        dynamicStructure.setHeadersList(generatedDynamicHeaders);

        List<ProjectRole> viewers = new ArrayList<ProjectRole>();
        viewers.add(ProjectRole.Manager);
        
        List<ProjectRole> editors = new ArrayList<ProjectRole>();
        editors.add(ProjectRole.Manager);
        
        Report report = new ReportImpl(
                new ReportDescriptor(
                "Assigned tasks - XPlanner",    // Report Name
                "Grid",                         // Report Description
                "",                             // Path
                "XPlanner",                     // Tool
                viewers,                        // List of Viewers
                editors,                        // List of Editors
                AvailableType.GRID),            // Report type
                dynamicStructure);              // Data to generate the report

        return report;
    }

    public Report getMyAssignedTasks(String projectId, String usrId) {
        List<List<String>> result = new ArrayList<List<String>>();

        result.add(Arrays.asList(new String[]{"Developer1", "Environments Management", "Iteration 3", "Research", "JEE6", "<a href=\"https://200.58.87.26:8080/xplanner/do/view/task?oid=312\">1. Review the documentation</a>", "10 Hrs", "8 Hrs"}));
        result.add(Arrays.asList(new String[]{"Developer1", "Environments Management", "Iteration 3", "Research", "JSF", "<a href=\"https://200.58.87.26:8080/xplanner/do/view/task?oid=313\">2. Develop a short example</a>", "3 Hrs", "1 Hrs"}));

        DynamicStructure dynamicStructure = new DynamicStructure();
        dynamicStructure.setDynamicList(result);

        List<String> generatedDynamicHeaders = new ArrayList();
        generatedDynamicHeaders.add("Person");
        generatedDynamicHeaders.add("Project");
        generatedDynamicHeaders.add("Iteration");
        generatedDynamicHeaders.add("US");
        generatedDynamicHeaders.add("Task");
        generatedDynamicHeaders.add("Description");
        generatedDynamicHeaders.add("Estimated");
        generatedDynamicHeaders.add("Remaining");

        dynamicStructure.setHeadersList(generatedDynamicHeaders);

        List<ProjectRole> viewers = new ArrayList<ProjectRole>();
        viewers.add(ProjectRole.TeamMember);

        List<ProjectRole> editors = new ArrayList<ProjectRole>();
        editors.add(ProjectRole.TeamMember);

        Report report = new ReportImpl(new ReportDescriptor(
                "My assigned tasks - XPlanner", // Report Name
                "Grid",                         // Report Description
                "",                             // Path
                "XPlanner",                     // Tool
                viewers,                        // List of Viewers
                editors,                        // List of Editors
                AvailableType.GRID),            // Report type
                dynamicStructure);              // Data to generate the report

        return report;
    }
    
    public List<ParameterDescriptor> getRequiredParameters() {
        return new ArrayList<ParameterDescriptor>();
    }

    public void associateUserToProject(String projectId, String userId, ProjectRole role) throws DevPortalPluginException {
        
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

        while (!endDateString.equals(currentDateString)) {

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

//    public Report getBurnDown(String projectId, String userId) {
//
//        List<LineDataStructure> result = new ArrayList<LineDataStructure>();
//
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat reportDateFormat = new SimpleDateFormat("MM-dd");
//
//        try
//        {
//            // Register the MySQL Driver
//            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
//
//            String userName = "root";
//            String password = "Control123";
//
//            // Get the connection
//            Connection conexion = DriverManager.getConnection (
//                "jdbc:mysql://localhost/xplanner", userName, password);
//
//            // A statement its been created in order to perform que obtainAllTimeEntries
//            Statement statement = conexion.createStatement();
//
//            // Obtain all time entries
//            projectId = "628";
//            String obtainAllTimeEntries = "SELECT time_entry.report_date, time_entry.duration "
//                    + "FROM time_entry INNER JOIN "
//                    + "(task INNER JOIN (story INNER JOIN "
//                    + "(iteration INNER JOIN project "
//                    + "ON iteration.project_id=project.id "
//                    + "AND project.id=" + projectId + ") "
//                    + "ON story.iteration_id=iteration.id) "
//                    + "ON task.story_id=story.id) "
//                    + "ON time_entry.task_id=task.id";
//
//            ResultSet resultSetTimeEntries = statement.executeQuery (obtainAllTimeEntries);
//            List timeEntryList = new ArrayList();
//
//            // Fill timeEntryList with time entries
//            while (resultSetTimeEntries.next())
//            {
//                Date reportDate = resultSetTimeEntries.getDate("report_date");
//                int duration = Integer.parseInt(resultSetTimeEntries.getString ("duration"));
//                TimeEntry timeEntry = new TimeEntry(reportDate, duration);
//                timeEntryList.add(timeEntry);
//            }
//
//            // Create a list for ordered time entries
//            List orderedTimeEntryList = new ArrayList();
//            // Add the first time entry to the ordered time entry list
//            orderedTimeEntryList.add(timeEntryList.get(0));
//            for (int j = 1; j < timeEntryList.size(); j++) {
//
//                Date date = ((TimeEntry)timeEntryList.get(j)).getTimeEntryDate();
//                int duration = ((TimeEntry)timeEntryList.get(j)).getDuration();
//                insertRegistry(orderedTimeEntryList, date, duration);
//            }
//
//            // Sort list with all entries by day
//            Collections.sort(orderedTimeEntryList);
//
//            String obtainItarationDuration = "SELECT start_date, end_date FROM iteration where project_id=" + projectId;
//            ResultSet resultSetIterationDuration = statement.executeQuery (obtainItarationDuration);
//
//            Date startDate = null;
//            Date endDate = null;
//
//            // Se recorre el ResultSet, mostrando por pantalla los resultados.
//            while (resultSetIterationDuration.next())
//            {
//                System.out.println ("Start Date: " + resultSetIterationDuration.getDate ("start_date") +
//                                    ", End Date: " + resultSetIterationDuration.getDate("end_date"));
//
//                startDate = resultSetIterationDuration.getDate ("start_date");
//                endDate = resultSetIterationDuration.getDate ("end_date");
//            }
//
//            String startDateString = startDate.toString();
//            String endDateString = endDate.toString();
//
//            int estimatedHours = 0;
//            int workload = 0;
//            int totalHours = 0;
//            String obtainEstimatedHours = "SELECT original_estimated_hours FROM story";
//            ResultSet resultSetEstimatedHours = statement.executeQuery (obtainEstimatedHours);
//            while (resultSetEstimatedHours.next())
//            {
//                estimatedHours = estimatedHours + resultSetEstimatedHours.getInt ("original_estimated_hours");
//                workload = workload + resultSetEstimatedHours.getInt ("original_estimated_hours");
//                totalHours = totalHours + resultSetEstimatedHours.getInt ("original_estimated_hours");
//            }
//
//            List fullList = insertNonWorkedDays(orderedTimeEntryList, startDateString, endDateString, estimatedHours);
//            int iterationDays = 0;
//
//            String currentDateString = startDateString;
//            while (! endDateString.equals(currentDateString)){
//
//                Calendar currentDateCalendar = Calendar.getInstance();
//                String [] dates = currentDateString.split("-");
//                int year = Integer.parseInt(dates[0]);
//                int month = Integer.parseInt(dates[1]);
//                int day = Integer.parseInt(dates[2]);
//
//                currentDateCalendar.set(year, month - 1, day);
//                currentDateString = dateFormat.format(currentDateCalendar.getTime());
//
//                currentDateCalendar.add(Calendar.DATE,1);   // or  Calendar.DAY_OF_MONTH which is a synonym
//                currentDateString = dateFormat.format(currentDateCalendar.getTime());
//
//                iterationDays++;
//
//            }
//
//            for (int i = 0; i < fullList.size(); i++) {
//                TimeEntry timeEntry = (TimeEntry)fullList.get(i);
//                LineDataStructure burnDown = new LineDataStructure(timeEntry.duration, "BurnDown", reportDateFormat.format(timeEntry.getTimeEntryDate()));
//                result.add(burnDown);
//            }
//
//            for (int i = 0; i < fullList.size(); i++) {
//                TimeEntry timeEntry = (TimeEntry)fullList.get(i);
//                LineDataStructure burnDown = new LineDataStructure(workload, "WorkLoad", reportDateFormat.format(timeEntry.getTimeEntryDate()));
//                result.add(burnDown);
//            }
//
//            int decrement = totalHours/iterationDays;
//
//            for (int i = 0; i < fullList.size(); i++) {
//                TimeEntry timeEntry = (TimeEntry)fullList.get(i);
//                LineDataStructure burnDown = new LineDataStructure(totalHours, "Linear (BurnDown)", reportDateFormat.format(timeEntry.getTimeEntryDate()));
//                result.add(burnDown);
//                totalHours = totalHours - decrement;
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        ChartReport report = new ChartReport();
//        report.setLineDataStructureList(result);
//
//        List<ProjectRole> viewers = new ArrayList<ProjectRole>();
//        viewers.add(ProjectRole.Manager);
//        viewers.add(ProjectRole.TeamMember);
//
//        List<ProjectRole> editors = new ArrayList<ProjectRole>();
//        editors.add(ProjectRole.Manager);
//
//        Report rep = new ReportImpl(
//                new ReportDescriptor(
//                "Burn down chart - XPlanner",   // Report Name
//                "Chart report",                 // Report Description
//                "",                             // Path
//                "XPlanner",                     // Tool
//                viewers,                        // List of viewers
//                editors,                        // List of editors
//                ChartType.LINECHART,            // Report type
//                "Date (axis x)",                // Axis X label
//                "Hours (axis y)",               // Axis Y label
//                600,                            // Height
//                900),                           // Width
//                (DataStructure) report);        // Data to generate the report
//
//        return rep;
//    }

    /**
     * Gets a Burn Down report
     * @param projectId
     * @param userId
     * @return a Report that represents a Burn Down chart
     */
    public Report getBurnDown(String projectId, String userId) {

        List<LineDataStructure> result = new ArrayList<LineDataStructure>();
        userId = "143";
        int total = 100;

        LineDataStructure burnDown1 =
                new LineDataStructure(total, "BurnDown", "1-Nov");

        LineDataStructure burnDown2 =
                new LineDataStructure(total - 10, "BurnDown", "2-Nov");

        LineDataStructure burnDown3 =
                new LineDataStructure(total - 20, "BurnDown", "3-Nov");

        LineDataStructure burnDown4 =
                new LineDataStructure(total - 30, "BurnDown", "4-Nov");

        LineDataStructure burnDown5 =
                new LineDataStructure(total - 40, "BurnDown", "5-Nov");

        LineDataStructure burnDown6 =
                new LineDataStructure(total - 40, "BurnDown", "6-Nov");

        LineDataStructure burnDown7 =
                new LineDataStructure(total - 50, "BurnDown", "7-Nov");

        LineDataStructure burnDown8 =
                new LineDataStructure(total - 60, "BurnDown", "8-Nov");

        LineDataStructure burnDown9 =
                new LineDataStructure(total - 70, "BurnDown", "9-Nov");

        LineDataStructure burnDown10 =
                new LineDataStructure(total - 90, "BurnDown", "10-Nov");

        LineDataStructure burnDown11 =
                new LineDataStructure(total - 100, "BurnDown", "11-Nov");

        LineDataStructure burnDown12 =
                new LineDataStructure(total - 110, "BurnDown", "12-Nov");

        LineDataStructure burnDown13 =
                new LineDataStructure(total - 120, "BurnDown", "13-Nov");

        result.add(burnDown1);
        result.add(burnDown2);
        result.add(burnDown3);
        result.add(burnDown4);
        result.add(burnDown5);
        result.add(burnDown6);
        result.add(burnDown7);
        result.add(burnDown8);
        result.add(burnDown9);
        result.add(burnDown10);
        result.add(burnDown11);
        result.add(burnDown12);
        result.add(burnDown13);

        LineDataStructure workLoad1 =
                new LineDataStructure(total, "WorkLoad", "1-Nov");

        LineDataStructure workLoad2 =
                new LineDataStructure(total, "WorkLoad", "2-Nov");

        LineDataStructure workLoad3 =
                new LineDataStructure(total, "WorkLoad", "3-Nov");

        LineDataStructure workLoad4 =
                new LineDataStructure(total, "WorkLoad", "4-Nov");

        LineDataStructure workLoad5 =
                new LineDataStructure(total, "WorkLoad", "5-Nov");

        LineDataStructure workLoad6 =
                new LineDataStructure(total, "WorkLoad", "6-Nov");

        LineDataStructure workLoad7 =
                new LineDataStructure(total, "WorkLoad", "7-Nov");

        LineDataStructure workLoad8 =
                new LineDataStructure(total, "WorkLoad", "8-Nov");

        LineDataStructure workLoad9 =
                new LineDataStructure(total, "WorkLoad", "9-Nov");

        LineDataStructure workLoad10 =
                new LineDataStructure(total + 5, "WorkLoad", "10-Nov");

        LineDataStructure workLoad11 =
                new LineDataStructure(total + 5, "WorkLoad", "11-Nov");

        LineDataStructure workLoad12 =
                new LineDataStructure(total + 10, "WorkLoad", "12-Nov");

        LineDataStructure workLoad13 =
                new LineDataStructure(total + 10, "WorkLoad", "13-Nov");

        result.add(workLoad1);
        result.add(workLoad2);
        result.add(workLoad3);
        result.add(workLoad4);
        result.add(workLoad5);
        result.add(workLoad6);
        result.add(workLoad7);
        result.add(workLoad8);
        result.add(workLoad9);
        result.add(workLoad10);
        result.add(workLoad11);
        result.add(workLoad12);
        result.add(workLoad13);

        LineDataStructure linear1 =
                new LineDataStructure(total, "Linear (BurnDown)", "1-Nov");

        LineDataStructure linear2 =
                new LineDataStructure(total - 10, "Linear (BurnDown)", "2-Nov");

        LineDataStructure linear3 =
                new LineDataStructure(total - 20, "Linear (BurnDown)", "3-Nov");

        LineDataStructure linear4 =
                new LineDataStructure(total - 30, "Linear (BurnDown)", "4-Nov");

        LineDataStructure linear5 =
                new LineDataStructure(total - 40, "Linear (BurnDown)", "5-Nov");

        LineDataStructure linear6 =
                new LineDataStructure(total - 50, "Linear (BurnDown)", "6-Nov");

        LineDataStructure linear7 =
                new LineDataStructure(total - 60, "Linear (BurnDown)", "7-Nov");

        LineDataStructure linear8 =
                new LineDataStructure(total - 70, "Linear (BurnDown)", "8-Nov");

        LineDataStructure linear9 =
                new LineDataStructure(total - 80, "Linear (BurnDown)", "9-Nov");

        LineDataStructure linear10 =
                new LineDataStructure(total - 90, "Linear (BurnDown)", "10-Nov");

        LineDataStructure linear11 =
                new LineDataStructure(total - 100, "Linear (BurnDown)", "11-Nov");

        LineDataStructure linear12 =
                new LineDataStructure(total - 110, "Linear (BurnDown)", "12-Nov");

        LineDataStructure linear13 =
                new LineDataStructure(total - 120, "Linear (BurnDown)", "13-Nov");

        result.add(linear1);
        result.add(linear2);
        result.add(linear3);
        result.add(linear4);
        result.add(linear5);
        result.add(linear6);
        result.add(linear7);
        result.add(linear8);
        result.add(linear9);
        result.add(linear10);
        result.add(linear11);
        result.add(linear12);
        result.add(linear13);

        ChartReport report = new ChartReport();
        report.setLineDataStructureList(result);

        List<ProjectRole> viewers = new ArrayList<ProjectRole>();
        viewers.add(ProjectRole.Manager);
        viewers.add(ProjectRole.TeamMember);

        List<ProjectRole> editors = new ArrayList<ProjectRole>();
        editors.add(ProjectRole.Manager);

        Report rep = new ReportImpl(
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
                (DataStructure) report);        // Data to generate the report

        return rep;
    }

    /**
     * Gets a pie report
     * @param projectId
     * @return a Report that represents a Pie Chart
     */
    public Report getTasksByDisposition(String projectId) {

        List<PieDataStructure> pieDataStructureList =
                new ArrayList<PieDataStructure>();

        pieDataStructureList.add(new PieDataStructure(
                "Accepted",
                new Double(43.2)));

        pieDataStructureList.add(new PieDataStructure(
                "Draft",
                new Double(10.0)));

        pieDataStructureList.add(new PieDataStructure(
                "In Development",
                new Double(27.5)));

        pieDataStructureList.add(new PieDataStructure(
                "Implemented",
                new Double(17.5)));

        pieDataStructureList.add(new PieDataStructure(
                "Under test",
                new Double(11.0)));

        pieDataStructureList.add(new PieDataStructure(
                "Tested with erros",
                new Double(19.4)));

        ChartReport report = new ChartReport();
        report.setPieDataStructures(pieDataStructureList);

        List<ProjectRole> viewers = new ArrayList<ProjectRole>();
        viewers.add(ProjectRole.TeamMember);
        viewers.add(ProjectRole.Manager);

        List<ProjectRole> editors = new ArrayList<ProjectRole>();
        editors.add(ProjectRole.Manager);

        Report rep = new ReportImpl(
                new ReportDescriptor(
                "Tasks by disposition - XPlanner",          // Report Name
                "Pie chart",                                // Report Description
                "/resources/reports/changeSetReport.xhtml", // Path
                "XPlanner",                                 // Tool
                viewers,                                    // List of viewers
                editors,                                    // List of editors
                ChartType.PIECHART,                         // Report type
                "",                                         // Axis Y label
                "",                                         // Axis X label
                600,                                        // Height
                900),                                       // Width
                (DataStructure) report);                    // Data

        return rep;
    }

    /**
     * Gets a Gantt Chart Report
     * @param projectId
     * @return a Report that represents a Gantt chart
     */
    public Report getGanttReport(String projectId) {

        List<GanttDataStructure> ganttDataStructures =
                new ArrayList<GanttDataStructure>();

        ganttDataStructures.add(
                new GanttDataStructure(
                "Scheduled",
                "Write Proposal",
                new Date(getCurrentDate(2001), Calendar.APRIL, 1),
                new Date(getCurrentDate(2001), Calendar.APRIL, 5)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Scheduled",
                "Obtain Approval",
                new Date(getCurrentDate(2001), Calendar.APRIL, 9),
                new Date(getCurrentDate(2001), Calendar.APRIL, 9)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Scheduled",
                "Requirements Analysis",
                new Date(getCurrentDate(2001), Calendar.APRIL, 10),
                new Date(getCurrentDate(2001), Calendar.MAY, 5)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Scheduled",
                "Design Phase",
                new Date(getCurrentDate(2001), Calendar.MAY, 6),
                new Date(getCurrentDate(2001), Calendar.MAY, 30)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Scheduled",
                "Design Signoff",
                new Date(getCurrentDate(2001), Calendar.JUNE, 2),
                new Date(getCurrentDate(2001), Calendar.JUNE, 2)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Scheduled",
                "Alpha Implementation",
                new Date(getCurrentDate(2001), Calendar.JUNE, 3),
                new Date(getCurrentDate(2001), Calendar.JULY, 31)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Scheduled",
                "Design Review",
                new Date(getCurrentDate(2001), Calendar.AUGUST, 1),
                new Date(getCurrentDate(2001), Calendar.AUGUST, 8)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Scheduled",
                "Revised Design Signoff",
                new Date(getCurrentDate(2001), Calendar.AUGUST, 10),
                new Date(getCurrentDate(2001), Calendar.AUGUST, 10)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Scheduled",
                "Beta Implementation",
                new Date(getCurrentDate(2001), Calendar.AUGUST, 12),
                new Date(getCurrentDate(2001), Calendar.SEPTEMBER, 12)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Scheduled",
                "Testing",
                new Date(getCurrentDate(2001), Calendar.SEPTEMBER, 13),
                new Date(getCurrentDate(2001), Calendar.OCTOBER, 31)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Scheduled",
                "Final Implementation",
                new Date(getCurrentDate(2001), Calendar.NOVEMBER, 1),
                new Date(getCurrentDate(2001), Calendar.NOVEMBER, 15)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Scheduled",
                "Signoff",
                new Date(getCurrentDate(2001), Calendar.NOVEMBER, 28),
                new Date(getCurrentDate(2001), Calendar.NOVEMBER, 30)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Actual",
                "Write Proposal",
                new Date(getCurrentDate(2001), Calendar.APRIL, 1),
                new Date(getCurrentDate(2001), Calendar.APRIL, 5)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Actual",
                "Obtain Approval",
                new Date(getCurrentDate(2001), Calendar.APRIL, 9),
                new Date(getCurrentDate(2001), Calendar.APRIL, 9)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Actual",
                "Requirements Analysis",
                new Date(getCurrentDate(2001), Calendar.APRIL, 10),
                new Date(getCurrentDate(2001), Calendar.MAY, 15)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Actual",
                "Design Phase",
                new Date(getCurrentDate(2001), Calendar.MAY, 15),
                new Date(getCurrentDate(2001), Calendar.JUNE, 17)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Actual",
                "Design Signoff",
                new Date(getCurrentDate(2001), Calendar.JUNE, 30),
                new Date(getCurrentDate(2001), Calendar.JUNE, 30)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Actual",
                "Alpha Implementation",
                new Date(getCurrentDate(2001), Calendar.JULY, 1),
                new Date(getCurrentDate(2001), Calendar.SEPTEMBER, 12)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Actual",
                "Design Review",
                new Date(getCurrentDate(2001), Calendar.SEPTEMBER, 12),
                new Date(getCurrentDate(2001), Calendar.SEPTEMBER, 22)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Actual",
                "Revised Design Signoff",
                new Date(getCurrentDate(2001), Calendar.SEPTEMBER, 25),
                new Date(getCurrentDate(2001), Calendar.SEPTEMBER, 27)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Actual",
                "Beta Implementation",
                new Date(getCurrentDate(2001), Calendar.SEPTEMBER, 27),
                new Date(getCurrentDate(2001), Calendar.OCTOBER, 30)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Actual",
                "Testing",
                new Date(getCurrentDate(2001), Calendar.OCTOBER, 31),
                new Date(getCurrentDate(2001), Calendar.NOVEMBER, 17)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Actual",
                "Final Implementation",
                new Date(getCurrentDate(2001), Calendar.NOVEMBER, 18),
                new Date(getCurrentDate(2001), Calendar.DECEMBER, 5)));

        ganttDataStructures.add(
                new GanttDataStructure(
                "Actual",
                "Signoff",
                new Date(getCurrentDate(2001), Calendar.DECEMBER, 10),
                new Date(getCurrentDate(2001), Calendar.DECEMBER, 11)));

        ChartReport report = new ChartReport();
        report.setGanttDataStructures(ganttDataStructures);

        List<ProjectRole> viewers = new ArrayList<ProjectRole>();
        viewers.add(ProjectRole.TeamMember);
        viewers.add(ProjectRole.Manager);

        List<ProjectRole> editors = new ArrayList<ProjectRole>();
        editors.add(ProjectRole.TeamMember);

        Report rep = new ReportImpl(
                new ReportDescriptor(
                "Gantt Diagram",            // Report Name
                "Chart",                    // Report Description
                "",                         // Path
                "XPlanner",                 // Tool
                viewers,                    // List of viewers
                editors,                    // List of editors
                ChartType.GANTTCHART,       // Type of report                
                "Tasks (axis y)",           // Axis y label
                "Date (axis x)",            // Axis x label
                800,                        // Height
                600),                       // Width
                (DataStructure) report);    // Data to generate the report

        return rep;
    }

public List<Report> getCustomReports(String projectId, List<String> usrIds) throws NoToolServerConnectionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Report> getGridReports(String projectId, List<String> usrIds) throws NoToolServerConnectionException {
        List<Report> reports = new ArrayList<Report>();
        reports.add(getMyAssignedTasks(projectId, ""));
        reports.add(getAssignedTasks(projectId, usrIds));

        return reports;
    }

    public List<Report> getChartReports(String projectId, List<String> usrIds) throws NoToolServerConnectionException {
        List<Report> reports = new ArrayList<Report>();
        reports.add(getTasksByDisposition(projectId));
        reports.add(getGanttReport(projectId));
        reports.add(getBurnDown(projectId, "100"));
        return reports;
    }

    public List<ReportDescriptor> getAvailableReports() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private int getCurrentDate(int date) {
        return date - 1900;
    }
}
