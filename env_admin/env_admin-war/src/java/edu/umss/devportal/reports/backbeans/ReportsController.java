/*
 * @(#)ReportsController.java 12/09/2011
 *
 * Copyright (c) 2011 Universidad Mayor de San Simón
 * Cochabamba - Bolivia
 * All Rights Reserved.
 */
package edu.umss.devportal.reports.backbeans;

import edu.umss.devportal.entity.ProjectEntity;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.StreamedContent;

import edu.umss.devportal.common.BasicProjectImpl;
import edu.umss.devportal.common.IssueTracker;
import edu.umss.devportal.common.ProjectRole;
import edu.umss.devportal.common.reports.DynamicStructure;
import edu.umss.devportal.common.ProjectTracker;
import edu.umss.devportal.ejb.session.LoginSessionBean;
import edu.umss.devportal.common.reports.ChartReport;
import edu.umss.devportal.common.reports.ChartType;
import edu.umss.devportal.common.reports.DataStructure;
import edu.umss.devportal.common.reports.Report;
import edu.umss.devportal.common.reports.ReportDescriptor;
import edu.umss.devportal.entity.ProjectMembershipEntity;
import edu.umss.devportal.entity.ToolByProject;
import edu.umss.devportal.entity.UserAcountTool;
import edu.umss.devportal.entity.UserEntity;
import edu.umss.devportal.reports.utilities.DataTransformer;
import edu.umss.devportal.reports.utilities.Image;
import edu.umss.edu.devportal.exception.NoToolServerConnectionException;
import java.util.Date;
import java.util.Map;
import javax.enterprise.context.SessionScoped;

/**
 * Recover and manages the reports from the plug-ins
 *
 * @author Edson Alvarez
 */
@ManagedBean
@SessionScoped
public class ReportsController {

    /**
     * Current logger
     */
    private static final Logger logger = Logger.getLogger(ReportsController.class.getName());
    private static final String PROJECTS_URL = "start.xhtml?faces-redirect=true";
    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;
    /**
     * Session bean
     */
    @EJB
    private LoginSessionBean loginSessionBean;
    /**
     * Current selected project
     */
    private ProjectEntity currentProject;
    /**
     * Selected project tracker id
     */
    private int projectTrackerToolId;
    /**
     * Current project tracker
     */
    private ProjectTracker projectTracker;
    /**
     * Current project tracker id
     */
    private String projectTrackerProjectId;
    /**
     * Current project tracker user id
     */
    private String projectTrackerUserId;
    /**
     * Selected issue tracker id
     */
    private int issueTrackerToolId;
    /**
     * Current issue tracker
     */
    private IssueTracker issueTracker;
    /**
     * Current issue tracker id
     */
    private String issueTrackerProjectId;
    /**
     * Current issue tracker user id
     */
    private String issueTrackerUserId;
    private UserEntity currentLoggedInUser;
    private int userId;
    private List<GridTemplateBackingbean> gridReports;
    private List<Image> imageList;
    private HashMap<Integer, Image> charts;
    private ProjectRole role;
    private List<Report> toolChartReports;
    private List<Report> toolGridReports;
    private boolean display = false;
    private boolean loading = true;
    private boolean issueTrackerDemoEnabled = false;
    private boolean projectTrackerDemoEnabled = false;
    private boolean issueTrackerConfigured = false;
    private boolean projectTrackerConfigured = false;
    private boolean displayReports = false;
    private boolean displayUpdater = true;
    private List projectTrackerUserList;
    private List issueTrackerUserList;
    private String loggedUserName;
    private String projectName;
    private boolean areGridReportsLoaded = false;
    private boolean areChartReportsLoaded = false;
    private int currentImageId;
    private Image currentImage;

    /**
     * Initializes and sets the needed variables to generate the reports
     */
    @PostConstruct
    public void postConstruct() {

        // Initialize the list of reports
        toolChartReports = new ArrayList<Report>();
        toolGridReports = new ArrayList<Report>();
        // Initialize the list of tool users
        issueTrackerUserList = new ArrayList();
        projectTrackerUserList = new ArrayList();
        // Initialize the list of grids and charts
        gridReports = new ArrayList<GridTemplateBackingbean>();
        imageList = new ArrayList<Image>();
        charts = new HashMap<Integer, Image>();

        if (loginSessionBean != null) {
            // Obtains the current project
            currentProject = loginSessionBean.getCurrentProject();
            // Obtains the current logged in user
            currentLoggedInUser = loginSessionBean.getUser();
            // Set the User ID
            userId = currentLoggedInUser.getId();

        } else {
            logger.log(Level.SEVERE, "There is no connection properly made");
        }
    }

    public String setImageIdToDisplay(int imageId) {
        setCurrentImageId(imageId);
        return "";
    }

    public int getCurrentImageId() {
        return currentImageId;
    }

    public void setCurrentImageId(int currentImageId) {
        this.currentImageId = currentImageId;
    }

    public Image getCurrentImage() {
        currentImage = imageList.get(currentImageId);
        return currentImage;
    }

    public void setCurrentImage(Image currentImage) {
        this.currentImage = currentImage;
    }

    public void updateToolsId() {

//        if (role == null || projectTrackerConfigured == false || issueTrackerConfigured == false
//                || projectTracker == null || projectTrackerProjectId == null
//                || issueTracker == null || issueTrackerProjectId == null) {




// Set the Role to display the proper reports
        List<ProjectMembershipEntity> memberAccounts = currentProject.getMemberAccounts();
        for (ProjectMembershipEntity projectMembershipEntity : memberAccounts) {
            if (projectMembershipEntity.getProjectId() == currentProject.getId()
                    && projectMembershipEntity.getUserId() == userId) {
                String currentRoleName = projectMembershipEntity.getRolEntity().getName();
                if (currentRoleName != null && ProjectRole.Manager.toString().equals(currentRoleName)) {
                    role = ProjectRole.Manager;
                    break;
                } else {
                    role = ProjectRole.TeamMember;
                    break;
                }
            }
        }

        // If role is null, set Team member by default
        if (role == null) {
            role = ProjectRole.TeamMember;
            logger.log(Level.WARNING, null,
                    "Role could not be properly set, User role was set to TeamMember");
        } else {
            logger.log(Level.INFO, null,
                    String.format("The role of this user is %s", role.toString()));
        }

        for (ToolByProject currentTool : loginSessionBean.getCurrentProject().getToolByProjects()) {
            // If project tracker demo is enabled set default user id and user id list
            if (currentTool.getName().equals("Dummy Tool")) {
                projectTrackerDemoEnabled = true;
                projectTrackerConfigured = true;
                projectTrackerUserId = "100";
                projectTrackerUserList.add("100");
            }
            // If issue tracker demo is enabled set default user id and user id list
            if (currentTool.getName().equals("IT Dummny")) {
                issueTrackerDemoEnabled = true;
                issueTrackerConfigured = true;
                issueTrackerUserId = "100";
                issueTrackerUserList.add("100");
            }
        }

        for (ToolByProject toolByProject : currentProject.getToolByProjects()) {
            if (toolByProject.getToolProjectId().equals(projectTrackerProjectId)) {
                projectTrackerToolId = toolByProject.getToolId();
                projectTrackerConfigured = true;
            }
            if (toolByProject.getToolProjectId().equals(issueTrackerProjectId)) {
                issueTrackerToolId = toolByProject.getToolId();
                issueTrackerConfigured = true;
            }
        }

        if (role.equals(ProjectRole.TeamMember)) {
            projectTrackerUserList.clear();
            if (projectTrackerUserId != null) {
                projectTrackerUserList.add(projectTrackerUserId);
            }
            issueTrackerUserList.clear();
            if (issueTrackerUserId != null) {
                issueTrackerUserList.add(issueTrackerUserId);
            }
        }

        if (projectTracker == null) {
            projectTracker = loginSessionBean.getCurrentProjectTracker();
        }
        if (projectTrackerProjectId == null) {
            projectTrackerProjectId = loginSessionBean.getIdProjectProjectTracker();
        }
        if (issueTracker == null) {
            issueTracker = loginSessionBean.getCurrentIssueTracker();
        }
        if (issueTrackerProjectId == null) {
            issueTrackerProjectId = loginSessionBean.getIdProjectIssueTracker();
        }
        // }
    }

    public boolean isIssueTrackerConfigured() {
        return issueTrackerConfigured;
    }

    public boolean isProjectTrackerConfigured() {
        return projectTrackerConfigured;
    }

    public boolean isIssueTrackerDemoEnabled() {
        return issueTrackerDemoEnabled;
    }

    public void setIssueTrackerDemoEnabled(boolean issueTrackerDemoEnabled) {
        this.issueTrackerDemoEnabled = issueTrackerDemoEnabled;
    }

    public void updateProjectTrackerIds() {
        if (projectTracker != null && projectTrackerDemoEnabled == false) {
            try {
                projectTrackerUserList = projectTracker.getUserList();

            } catch (NoToolServerConnectionException ex) {
                projectTrackerUserList.clear();
                logger.log(Level.SEVERE, "Project tracker user list could not be loaded properly", ex);
                FacesContext.getCurrentInstance().
                        addMessage(
                        null,
                        new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        "Error",
                        "Project tracker user list could not be loaded properly."));
            }

            List projectTrackerList = new ArrayList();

            try {
                projectTrackerList = projectTracker.getProjectList();
            } catch (Exception ex) {
                projectTrackerList.clear();
                logger.log(Level.SEVERE, "Project tracker list could not be loaded properly", ex);
                FacesContext.getCurrentInstance().
                        addMessage(
                        null,
                        new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        "Error",
                        "Project tracker list could not be loaded properly."));
            }

            BasicProjectImpl currentProjectInTool = (BasicProjectImpl) projectTrackerList.get(0);
            projectTrackerProjectId = currentProjectInTool.getId();

            List<UserAcountTool> userAccountToolList = loginSessionBean.getUser().getUserAcountTools();

            for (UserAcountTool userAcountTool : userAccountToolList) {

                int currentToolId = userAcountTool.getToolId();
                int currentProjectId = userAcountTool.getProjectId();
                int currentUserId = userAcountTool.getUserId();
                String currentUserToolId = userAcountTool.getUserToolId();

                if (projectTrackerToolId == currentToolId) {
                    if (currentProject.getId() == currentProjectId) {
                        if (userId == currentUserId) {
                            projectTrackerUserId = currentUserToolId;
                            break;
                        }
                    }
                }
            }
        }
    }

    public void updateIssueTrackerIds() {
        if (issueTracker != null && issueTrackerDemoEnabled == false) {
            try {
                issueTrackerUserList = issueTracker.getUserList();
            } catch (Exception ex) {
                issueTrackerUserList.clear();
                logger.log(Level.SEVERE, "Issue tracker user list could not be loaded properly", ex);
                FacesContext.getCurrentInstance().
                        addMessage(
                        null,
                        new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        "Error",
                        "Issue tracker user list could not be loaded properly."));
            }

            List issueTrackerList = new ArrayList();

            try {
                issueTrackerList = issueTracker.getProjectList();
            } catch (Exception ex) {
                issueTrackerList.clear();
                logger.log(Level.SEVERE, "Issue tracker list could not be loaded properly", ex);
                FacesContext.getCurrentInstance().
                        addMessage(
                        null,
                        new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        "Error",
                        "Issue tracker list could not be loaded properly."));
            }

            if (!issueTrackerList.isEmpty()) {
                BasicProjectImpl currentProjectInTool = (BasicProjectImpl) issueTrackerList.get(3);
                issueTrackerProjectId = currentProjectInTool.getId();

                List<UserAcountTool> userAccountToolList = loginSessionBean.getUser().getUserAcountTools();

                for (UserAcountTool userAcountTool : userAccountToolList) {
                    int currentToolId = userAcountTool.getToolId();
                    int currentProjectId = userAcountTool.getProjectId();
                    int currentUserId = userAcountTool.getUserId();
                    String currentUserToolId = userAcountTool.getUserToolId();

                    if (issueTrackerToolId == currentToolId) {
                        if (currentProject.getId() == currentProjectId) {
                            if (userId == currentUserId) {
                                issueTrackerUserId = currentUserToolId;
                            }
                        }
                    }
                    break;
                }
            }
        }
    }

    /**
     * Get a list images that will represent the charts
     * @return List of Images
     */
    public List<Image> getImageList() throws NoToolServerConnectionException {
        updateToolsId();
        updateProjectTrackerIds();
        updateIssueTrackerIds();
        loadChartReports();
        if (imageList.isEmpty()) {
            imageList = new ArrayList<Image>(charts.values());
        }
        return imageList;
    }

    /**
     * Gets the Grids reports
     * @return a list of Grid template backing bean
     * @throws NoToolServerConnectionException
     */
    public List<GridTemplateBackingbean> getGridReports() throws NoToolServerConnectionException {
        updateToolsId();
        updateProjectTrackerIds();
        updateIssueTrackerIds();
        loadGridReports();

        return gridReports;
    }

    /**
     * Gets the streamed content of the image
     * @return a Streamed content to display the image
     */
    public StreamedContent getImage() {

        String image_id = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("image_id");

        image_id = "" + currentImageId;

//        if (image_id == null) {
//            // If you return null here then it won't work!!!
//            // You have to return something.
//
//            return charts.get(0).getImage();
//        }

        return charts.get(Integer.parseInt(image_id)).getImage();
    }

    /**
     * Load the Grid Reports from Plug-ins
     * @throws NoToolServerConnectionException
     */
    private void loadChartReports() throws NoToolServerConnectionException {
        if (!areChartReportsLoaded) {
            try {
                System.out.println("Loading chart reports...");
                String retrievedReports = "";

                charts = new HashMap<Integer, Image>();

                if (projectTracker != null && projectTrackerProjectId != null && !projectTrackerUserList.isEmpty()) {

                    List<Report> chartProjectTrackerReports =
                            projectTracker.getChartReports(
                            projectTrackerProjectId,
                            projectTrackerUserList);

                    for (int i = 0; i < chartProjectTrackerReports.size(); i++) {
                        toolChartReports.add(chartProjectTrackerReports.get(i));
                    }
                }
                if (issueTracker != null && issueTrackerProjectId != null && !issueTrackerUserList.isEmpty()) {
                    try {
                        List<Report> chartIssueTrackerReports =
                                issueTracker.getChartReports(
                                issueTrackerProjectId,
                                issueTrackerUserList);

                        for (int i = 0; i < chartIssueTrackerReports.size(); i++) {
                            toolChartReports.add(chartIssueTrackerReports.get(i));

                        }
                    } catch (Exception ex) {
                        System.out.println("Error trying to load chart reports from issue tracker: " + ex);
                    }
                }

//        if (projectTrackerDemoEnabled) {
//            List<Report> mockChartReports =
//                    pluginMock.getChartReports(
//                    "Mock",
//                    new ArrayList<String>());
//
//            for (int i = 0; i < mockChartReports.size(); i++) {
//                toolChartReports.add(mockChartReports.get(i));
//            }
//        }

                for (int i = 0; i < toolChartReports.size(); i++) {

                    // Load Chart reports
                    Report report = toolChartReports.get(i);

                    if (report != null) {

                        try {
                            ReportDescriptor reportDescriptor = report.getReportDescriptor();
                            DataStructure dataStructure = report.getDataStructure();

                            ChartReport chartReport = (ChartReport) dataStructure;

                            if (dataStructure instanceof ChartReport) {

                                //chartReport = (ChartReport) dataStructure;
                                ChartTemplate template = new ChartTemplate();

                                List<ProjectRole> viewers = toolChartReports.get(i).getReportDescriptor().getViewedByList();

                                if (viewers.contains(role)) {

                                    try {
                                        template.setReportName(reportDescriptor.getName());
                                        String currentReportName = reportDescriptor.getName();
                                        ChartType currentType = (ChartType) reportDescriptor.getType();

                                        String axisX = "";
                                        String axisY = "";

                                        int heightChart = 0;
                                        int widthChart = 0;

                                        axisX = reportDescriptor.getAxisX();
                                        axisY = reportDescriptor.getAxisY();

                                        if (axisX == null || axisY == null) {
                                            axisX = "Axis Y";
                                            axisY = "Axis X";
                                        }

                                        heightChart = reportDescriptor.getHeight();
                                        widthChart = reportDescriptor.getWidth();

                                        if (heightChart == 0 || widthChart != 0) {
                                            heightChart = 900;
                                            widthChart = 600;
                                        }

                                        template.setType(currentType);
                                        template.setId(i);

                                        if (currentType == ChartType.LINECHART) {
                                            template.setLineDataStructureList(chartReport.getLineDataStructures());
                                            charts.put(i, new Image(i, template.getChart(currentReportName, axisX, axisY, heightChart, widthChart), template.getReportName()));

                                        } else if (currentType == ChartType.PIECHART) {
                                            template.setPieDataset(DataTransformer.transformToPieDataSet(chartReport.getPieDataStructures()));
                                            charts.put(i, new Image(i, template.getChart(currentReportName, axisX, axisY, heightChart, widthChart), template.getReportName()));

                                        } else if (currentType == ChartType.BARCHART) {
                                            template.setDefaultCategoryDataset(DataTransformer.transformToDefaultCategoryDataSet(chartReport.getBarDataStructures()));
                                            charts.put(i, new Image(i, template.getChart(currentReportName, axisX, axisY, heightChart, widthChart), template.getReportName()));

                                        } else if (currentType == ChartType.STACKEDBARCHART) {
                                            template.setCategoryDataset(DataTransformer.transformToDefaultCategoryDataSet(chartReport.getBarDataStructures()));
                                            charts.put(i, new Image(i, template.getChart(currentReportName, axisX, axisY, heightChart, widthChart), template.getReportName()));

                                        } else if (currentType == ChartType.GANTTCHART) {
                                            template.setIntervalCategoryDataset(DataTransformer.transformToIntervalCategoryDataset(chartReport.getGanttDataStructures()));
                                            charts.put(i, new Image(i, template.getChart(currentReportName, axisX, axisY, heightChart, widthChart), template.getReportName()));

                                        } else {
                                            System.out.println("WRONG TYPE DEFINED");
                                        }

                                        if (i == toolChartReports.size() - 1) {
                                            retrievedReports += template.getReportName() + ".";
                                        } else {
                                            retrievedReports += template.getReportName() + ", ";
                                        }

                                    } catch (Exception ex) {
                                        System.out.println("Error generating the Chart Reports: " + ex);
                                        FacesContext.getCurrentInstance().
                                                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                "Error generating report: ", reportDescriptor.getName() + ", Description: " + ex));

                                    }
                                }
                            } else {
                                System.out.println("CASTING ERROR");

                            }

                        } catch (Exception exception) {
                            System.out.println("Error generating the Chart Reports: " + exception);
                            FacesContext.getCurrentInstance().
                                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "There is no data to display chart reports", "Please verify user data in configured tools."));
                        }
                    }
                }

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(
                        FacesMessage.SEVERITY_INFO, "Generated " + charts.size() + " Chart reports:",
                        retrievedReports));

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(
                        FacesMessage.SEVERITY_INFO, "Generated 1 Custom Report:",
                        "Magic Numbers"));

            } catch (NoToolServerConnectionException ex) {
                FacesContext.getCurrentInstance().
                        addMessage(
                        null,
                        new FacesMessage(
                        FacesMessage.SEVERITY_WARN,
                        "Error Loading Reports",
                        "Chart Reports could not be loaded: " + ex));
            }
            areChartReportsLoaded = true;
        }
    }

    /**
     * Load the Grid Reports from Plug-ins
     * @throws NoToolServerConnectionException
     */
    private void loadGridReports() throws NoToolServerConnectionException {

        if (!areGridReportsLoaded) {

            try {
                System.out.println("Loading grid reports...");
                updateToolsId();
                String retrievedReports = "";

                List<Report> gridProjectTrackerReports = new ArrayList<Report>();
                List<Report> gridIssueTrackerReports = new ArrayList<Report>();

                //int userId = loginSessionBean.getUser().getId();

                if (projectTracker != null) {

                    gridProjectTrackerReports =
                            projectTracker.getGridReports(
                            projectTrackerProjectId,
                            projectTrackerUserList);

                    for (int i = 0; i < gridProjectTrackerReports.size(); i++) {
                        toolGridReports.add(gridProjectTrackerReports.get(i));

                    }
                }

                if (issueTracker != null) {

//            List ptList = issueTracker.getUserList();
//            List projectL = issueTracker.getProjectList();
//            BasicProjectImpl p = (BasicProjectImpl) projectL.get(0);
//            String currentId = p.getId();

                    try {
                        gridIssueTrackerReports =
                                issueTracker.getGridReports(
                                issueTrackerProjectId,
                                issueTrackerUserList);

                        for (Report report : gridIssueTrackerReports) {
                            toolGridReports.add(report);
                        }

                    } catch (Exception ex) {
                        System.out.println("Error at trying to load grid reports from issue tracker: " + ex);

                    }
                }

//        if (issueTrackerDemoEnabled) {
//            List<Report> mockGridReports =
//                    pluginMock.getGridReports(
//                    "Mock",
//                    new ArrayList<String>());
//            for (int i = 0; i < mockGridReports.size(); i++) {
//                toolGridReports.add(mockGridReports.get(i));
//            }
//        }

                gridReports = new ArrayList<GridTemplateBackingbean>();

                for (int i = 0; i < toolGridReports.size(); i++) {

                    List<ProjectRole> viewers = toolGridReports.get(i).getReportDescriptor().getViewedByList();

                    //if (true) {
                    if (viewers.contains(role)) {

                        String name = toolGridReports.get(i).getReportDescriptor().getName();
                        if (toolGridReports.get(i) != null) {
                            try {
                                DynamicStructure structure = (DynamicStructure) toolGridReports.get(i).getDataStructure();

                                GridTemplateBackingbean template = new GridTemplateBackingbean();
                                template.setDynamicStructure(structure);
                                template.setReportName(name);
                                gridReports.add(template);

                                if (i == toolGridReports.size() - 1) {
                                    retrievedReports += template.getReportName() + ".";
                                } else {
                                    retrievedReports += template.getReportName() + ", ";
                                }

                            } catch (Exception ex) {
                                System.out.println("Error trying to retrieve the "
                                        + "project tracker grid reports:" + ex);
                                FacesContext.getCurrentInstance().
                                        addMessage(
                                        null,
                                        new FacesMessage(
                                        FacesMessage.SEVERITY_ERROR,
                                        "Error trying to retrieve the project grid reports",
                                        "Description: " + ex));
                            }
                        }
                    }
                }

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(
                        FacesMessage.SEVERITY_INFO, "Generated " + gridReports.size() + " Grid reports:",
                        retrievedReports));
            } catch (NoToolServerConnectionException ex) {
                FacesContext.getCurrentInstance().
                        addMessage(
                        null,
                        new FacesMessage(
                        FacesMessage.SEVERITY_WARN,
                        "Error Loading Reports",
                        "Grid Reports could not be loaded: " + ex));
            }
        }
        areGridReportsLoaded = true;
    }

    public String getProjectsUrl() {
        return PROJECTS_URL;
    }

    public String getIssueTrackerUserId() {
        return issueTrackerUserId;
    }

    public void setIssueTrackerUserId(String issueTrackerUserId) {
        this.issueTrackerUserId = issueTrackerUserId;
    }

    public String getProjectTrackerUserId() {
        return projectTrackerUserId;
    }

    public void setProjectTrackerUserId(String projectTrackerUserId) {
        this.projectTrackerUserId = projectTrackerUserId;
    }

    /**
     * Gets the project tracker from login session bean
     * @return Project tracker current instance
     */
    public ProjectTracker getProjectTracker() {
        return projectTracker;
    }

    /**
     * Gets the issue tracker from login session bean
     * @return current Issue tracker instance
     */
    public IssueTracker getIssueTracker() {
        return issueTracker;
    }

    /**
     * Gets the issue tracker project ID
     * @return The ID of the issue tracker project
     */
    public String getIssueTrackerProjectId() {
        return issueTrackerProjectId;
    }

    /**
     * Sets the issue tracker project ID
     * @param issueTrackerProjectId
     */
    public void setIssueTrackerProjectId(String issueTrackerProjectId) {
        this.issueTrackerProjectId = issueTrackerProjectId;
    }

    /**
     * Gets the project tracker project ID
     * @return the ID of the Project tracker in the project
     */
    public String getProjectTrackerProjectId() {
        return projectTrackerProjectId;
    }

    /**
     * Sets the project tracker project ID
     * @param projectTrackerProjectId
     */
    public void setProjectTrackerProjectId(String projectTrackerProjectId) {
        this.projectTrackerProjectId = projectTrackerProjectId;
    }

    /**
     * Gets the user ID
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the default HEIGHT of the charts
     * @return the default HEIGHT
     */
    public int getHeight() {
        return HEIGHT;
    }

    /**
     * Gets the default WIDTH of the charts
     * @return the default WIDTH
     */
    public int getWidth() {
        return WIDTH;
    }

    public boolean getDisplay() {
        return display;
    }

    public boolean getLoading() {
        return loading;
    }

    public void updateReports(ActionEvent actionEvent) {
        FacesMessage message =
                new FacesMessage(
                FacesMessage.SEVERITY_INFO,
                "Updated...", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public boolean getDisplayReports() {
        return displayReports;
    }

    public boolean getDisplayUpdater() {
        return displayUpdater;
    }

    public String getLoggedUserName() {

        if (loginSessionBean.getUser() != null
                && loginSessionBean.getUser().getName() != null) {
            loggedUserName = loginSessionBean.getUser().getName();
        }
        return loggedUserName;
    }

    public String getProjectName() {
        if (loginSessionBean.getCurrentProject() != null
                && loginSessionBean.getCurrentProject().getName() != null) {
            projectName = loginSessionBean.getCurrentProject().getName();
        }
        return projectName;
    }
}
