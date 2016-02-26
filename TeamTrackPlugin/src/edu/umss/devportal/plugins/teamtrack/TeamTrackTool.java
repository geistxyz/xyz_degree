/*
 *  @(#)TeamTrackTool.java   04-dic-2010
 */

package edu.umss.devportal.plugins.teamtrack;

import edu.umss.devportal.common.ParameterDescriptor;
import edu.umss.devportal.common.ProjectRole;
import edu.umss.devportal.common.IssueTracker;
import edu.umss.devportal.common.reports.MagicNumbers;
import edu.umss.devportal.common.Project;
import edu.umss.devportal.common.ToolDescriptor;
import edu.umss.devportal.common.ToolDescriptorImpl;
import edu.umss.devportal.common.ToolPlugin;
import edu.umss.devportal.common.ToolVersion;
import edu.umss.devportal.common.User;
import edu.umss.devportal.common.reports.Report;
import edu.umss.devportal.plugins.teamtrack.jpacontroller.TsMembersJpaController;
import edu.umss.devportal.plugins.teamtrack.jpacontroller.TsProjectsJpaController;
import edu.umss.devportal.plugins.teamtrack.jpacontroller.TsUsersJpaController;
import edu.umss.devportal.plugins.teamtrack.jpacontroller.common.DbConnection;
import edu.umss.devportal.plugins.teamtrack.jpacontroller.exceptions.NonexistentEntityException;
import edu.umss.devportal.plugins.teamtrack.jpacontroller.exceptions.PreexistingEntityException;
import edu.umss.devportal.plugins.teamtrack.model.TsMembers;
import edu.umss.devportal.plugins.teamtrack.model.TsProjects;
import edu.umss.devportal.plugins.teamtrack.model.TsUsers;
import edu.umss.devportal.plugins.teamtrack.reports.GridReport;
import edu.umss.devportal.plugins.teamtrack.reports.ReportManager;
import edu.umss.devportal.plugins.teamtrack.util.TypeUtil;
import edu.umss.edu.devportal.exception.DevPortalPluginException;
import edu.umss.edu.devportal.exception.MissingParameterException;
import edu.umss.edu.devportal.exception.NoToolServerConnectionException;
import edu.umss.edu.devportal.exception.ServerNotFoundException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.InstanceNotFoundException;
import javax.naming.ConfigurationException;

/**
 *
 * @author Alex Arenas
 * @author July Camacho
 */
public final class TeamTrackTool implements IssueTracker{

    // user name identifier to connect to database
    public static final String USER_NAME_KEY = "User name";

    // user password identifier to connect to database
    public static final String USER_PASSWORD_KEY = "User password";

    // database host identifier
    public static final String DATABASE_HOST_KEY = "Database host";

    // database port identifier
    public static final String DATABASE_PORT_KEY = "Database port";

    // database name identfier
    public static final String DATABASE_NAME_KEY = "Database name";

    private static final int VERSION_MAJOR = 1;
    private static final int VERSION_MINOR = 0;
    private static final String NAME = "Team Track Tool";
    private static final String DESCRIPTION = "Issue Tracker implementation for Team Track";

    private static final Logger logger = Logger.getLogger(TeamTrackTool.class.getName());

    private ToolDescriptor toolDescriptor;
    private ToolVersion toolVersion;
    private ReportManager reportManager= new ReportManager();
    /**
     * @see IssueTracker#applyConfiguration(java.util.Map)
     */
    public void applyConfiguration(Map<String, String> config)
            throws ConfigurationException {
        logger.log(Level.INFO, "Apply configuration");

        try{
            DbConnection.getInstance().applyConfiguration(config);
            GridReport.WEB_SERVER_IP_VALUE = config.get(GridReport.WEB_SERVER_IP);
            GridReport.WEB_SERVER_PORT_VALUE = config.get(GridReport.WEB_SERVER_PORT);
        }catch(MissingParameterException e){
            throw new ConfigurationException(e.getMessage());
        }
    }

    /**
     * @see IssueTracker#createProject()
     */
    public String createProject(Project project) throws Exception {
        logger.log(Level.INFO, "Create a TeamTrack Issue project");

        TsProjectsJpaController projectController = new TsProjectsJpaController();

        TsProjects newProject = createBaseProject();
        newProject.setTsName(project.getName());
        newProject.setTsDescription(project.getDescription());
        newProject.setTsSequence(projectController.getNextProjectSequence());

        projectController.create(newProject);

        return newProject.getTsId().toString();
    }

    /*
     * Create a TT project with basic configuration
     */
    private TsProjects createBaseProject(){
        TsProjects project = new TsProjects();

        project.setTsParentid(2); // Issue Base project
        project.setTsAllowchgreqs(1); // allows items to be submitted.
        project.setTsUseparent(0); // Not used in current version
        project.setTsUseparentseq(1); // use parent's secuence number
        project.setTsLastid(999); // firts item submitted is 1000.
        project.setTsZerofillto(5); // submited's id of five characters
        project.setTsWorkflowid(2); // base issue sequence workflow
        project.setTsUseparentworkflow(1); // the sub-projects use the same workflow

        return project;
    }

    /**
     * @see IssueTracker#getProjectList()
     */
    public List<Project> getProjectList() {
        TsProjectsJpaController projectController = new TsProjectsJpaController();
        List<TsProjects> allProjects = projectController.findEntities();

        return TypeUtil.copyProjectList(allProjects);
    }

    /**
     * @see IssueTracker#getToolDescriptor()
     */
    public ToolDescriptor getToolDescriptor() {

        if(toolDescriptor == null){
            toolVersion = new ToolVersion(VERSION_MAJOR, VERSION_MINOR);
            toolDescriptor = new ToolDescriptorImpl(NAME, DESCRIPTION, toolVersion);
        }

        return toolDescriptor;
    }

    /**
     * @see IssueTracker#removeProject(NAME)
     */
    public void removeProject(String projectId) throws InstanceNotFoundException {
        Integer id = Integer.parseInt(projectId);
        TsProjectsJpaController projectController = new TsProjectsJpaController();
        try {
            projectController.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TeamTrackTool.class.getName()).log(Level.SEVERE, null, ex);
            throw new InstanceNotFoundException("The project with id " + projectId + " was not found");
        }
    }

    /**
     * @see ToolPlugin#createUser(edu.umss.devportal.common.User)
     */
    @Override
    public String createUser(User user) throws Exception, NoToolServerConnectionException {
        logger.log(Level.INFO, "Create a TeamTrack Issue user");

        TsUsersJpaController userController = new TsUsersJpaController();

        TsUsers newUser = createBaseUser();
        newUser.setTsName(user.getName());
        newUser.setTsLoginid(user.getLogin());
        userController.create(newUser);

        return newUser.getTsId().toString();
    }

    private TsUsers createBaseUser(){
        TsUsers user = new TsUsers();
        user.setTsStatus(0); // active, 1 deleted
        user.setTsFieldsmask(0x0001); // access level: user 0x0001; manager 0x0004; system 0x0008
        user.setTsNotesmask(0x0004); // by default display last notes
        user.setTsNumnotes(10); // show 10 notes by default
        user.setTsChgmask(0x0004); // by default show last history information
        user.setTsNumchgs(10); // max number of changes history records
        user.setTsFilemask(20); // number of items displayed on a page at a time
        user.setTsNumfiles(0); // deprecated
        user.setTsBrowsermask(10608080); // common user
        user.setTsAccesstype(1); // common user
        user.setTsHomepagerpt(-1); // the user has not home page
        user.setTsDatepreference(1); // for future use
        user.setTsTimepreference(0); // teamtrack default
        user.setTsOtheruser(0);
        user.setTsLastlogindate(-2); // the user never log in
        user.setTsStatechangehistory(1); // state history should displayed at the top
        user.setTsManageincidentoptions(2); // by default active
        user.setTsLicensing(0); // the user is not using a concurrent licensing
        user.setTsPasswordprivilegeoptions(-2); // by default use system options
        user.setTsPreftableid(1001); // by default
        user.setTsPasswordsetdate(Calendar.getInstance().get(Calendar.MILLISECOND)); // current date
        user.setTsPasswordlengthoption(-1); // set by the app
        user.setTsGeneralmask(0); // The password no require special characters
        user.setTsContactid(0); // the user does not have contact record
        user.setTsGmtoffset(0); // not used
        
        return user;
    }

    /**
     * @see ToolPlugin#getUserList()
     */
    @Override
    public List<User> getUserList() throws NoToolServerConnectionException {
        TsUsersJpaController controller = new TsUsersJpaController();
        List<TsUsers> list = controller.findEntities();

        return TypeUtil.copyUserList(list);
    }

    /**
     * @see ToolPlugin#removeUser(java.lang.String)
     */
    @Override
    public void removeUser(String userId) throws NoToolServerConnectionException, InstanceNotFoundException {
        TsUsersJpaController controller = new TsUsersJpaController();
        try{
            TsUsers user = controller.find(Integer.parseInt(userId));
            user.setTsStatus(1);
            controller.edit(user);
        } catch (NonexistentEntityException ex) {
            String msg = "The user does not exist";
            logger.log(Level.SEVERE, msg, ex);
            throw new InstanceNotFoundException(msg);
        } catch(NumberFormatException e){
            String msg = "The user identifier is not valid";
            logger.log(Level.SEVERE, msg, e);
            throw new InstanceNotFoundException(msg);            
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @see ToolPlugin#testConnection()
     */
    public boolean testConnection(Map<String, String> config) throws MissingParameterException, ServerNotFoundException {
        logger.log(Level.INFO, "Test TeamTrack Database connection.");

        // check the configuration parameters
        DbConnection.checkConfiguration(config);        

        String server = config.get(DATABASE_HOST_KEY);
        // load the connection driver (SQL Server)
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
            throw new ServerNotFoundException(getToolDescriptor().getName(), server, ex);
        } catch (InstantiationException ex2){
            logger.log(Level.SEVERE, null, ex2);
            throw new ServerNotFoundException(getToolDescriptor().getName(), server, ex2);
        } catch (IllegalAccessException ex3){
            logger.log(Level.SEVERE, null, ex3);
            throw new ServerNotFoundException(getToolDescriptor().getName(), server, ex3);
        }
        
        String dbConnection = String.format(
                "jdbc:sqlserver://%s:%s;databaseName=%s",
                config.get(DATABASE_HOST_KEY),
                config.get(DATABASE_PORT_KEY),
                config.get(DATABASE_NAME_KEY));

        String userName = config.get(USER_NAME_KEY);
        String userPassword = config.get(USER_PASSWORD_KEY);

        try{
            logger.log(Level.CONFIG, "Connection String: {0}", dbConnection);
            DriverManager.getConnection(dbConnection, userName, userPassword);
        }catch(SQLException e){
            logger.log(Level.WARNING, e.getMessage());
            throw new ServerNotFoundException(getToolDescriptor().getName(), server, e);
        }

        logger.log(Level.INFO, "TeamTrack Database connection active");
        return true;
    }

    /**
     * @see ToolPlugin#getRequiredParameters()
     */
    public List<ParameterDescriptor> getRequiredParameters() {
        List<ParameterDescriptor> parameters = new ArrayList<ParameterDescriptor>();

        parameters.add(new ParameterDescriptor(
                DATABASE_HOST_KEY, "192.168.2.6", "TeamTrak database host", ""));
        parameters.add(new ParameterDescriptor(
                DATABASE_PORT_KEY, "1433", "TeamTrak database port number", ""));
        parameters.add(new ParameterDescriptor(
                DATABASE_NAME_KEY, "ttdata", "TeamTrak database name", ""));
        parameters.add(new ParameterDescriptor(
                USER_NAME_KEY, "sa", "TeamTrak database user name", ""));
        parameters.add(new ParameterDescriptor(
                USER_PASSWORD_KEY, "Control123", "TeamTrak database user password", ""));
        parameters.add(new ParameterDescriptor(
                GridReport.WEB_SERVER_IP,"192.168.2.6","Team Track web server IP address",""));
        parameters.add(new ParameterDescriptor(
                GridReport.WEB_SERVER_PORT,"81","Team Track web server port number",""));

        return parameters;
    }

    public void associateUserToProject(String projectId, String userId, ProjectRole role) throws DevPortalPluginException {
        // Verify the existence of the user
        int user;
        try{
            user = Integer.parseInt(userId);
            TsUsersJpaController userController = new TsUsersJpaController();
            if(userController.find(user) == null){
                String msg = "The user does not exist";
                logger.log(Level.SEVERE, msg);
                throw new DevPortalPluginException(getToolDescriptor().getName(), msg);
            }
        } catch(NumberFormatException e){
            String msg = "The user identifier format is incorrect";
            logger.log(Level.SEVERE, msg, e);
            throw new DevPortalPluginException(getToolDescriptor().getName(), msg, e);
        } catch(IllegalArgumentException e){
            String msg = "The user identifier format is incorrect";
            logger.log(Level.SEVERE, msg, e);
            throw new DevPortalPluginException(getToolDescriptor().getName(), msg, e);
        }

        // get the correct group
        int group = getGroupForRole(role);

        // Assoiate user to tt group        
        TsMembers member = new TsMembers();
        member.setTsGroupid(group);
        member.setTsUserid(user);

        TsMembersJpaController membersController = new TsMembersJpaController();
        try{
            membersController.create(member);
        } catch(PreexistingEntityException e){
            String msg = "The member with the same identifier already exist";
            logger.log(Level.WARNING, msg);
            throw new DevPortalPluginException(getToolDescriptor().getName(), msg, e);
        } catch(Exception e){
            String msg = e.getMessage();
            logger.log(Level.WARNING, msg);
            throw new DevPortalPluginException(getToolDescriptor().getName(), msg, e);
        }
    }

    private int getGroupForRole(ProjectRole role) throws DevPortalPluginException{
        // based in a default TeamTrak workflow
        switch(role){
            case Manager: return 14;
            case TeamMember: return 1;            
        }

        // the role is unknown
        String msg = "The role " + role + " is not supported yet.";
        logger.log(Level.SEVERE, msg);
        throw new DevPortalPluginException(getToolDescriptor().getName(), msg);
    }

    /**
     * Obtain all the available reports that are GRID report type
     */
    public List<Report> getGridReports(String projectId, List<String> usrIds) throws NoToolServerConnectionException {
        return reportManager.getGridReports(projectId, usrIds);
    }
    /**
     * Obtain all the available reports that are CHART report type
     */
    public List<Report> getChartReports(String projectId, List<String> usrIds) throws NoToolServerConnectionException {
        return reportManager.getChartReports(projectId, usrIds);
    }
    /**
     * Obtain all the available reports that are CUSTOM report type
     */
    public List<Report> getCustomReports(String projectId, List<String> usrIds) throws NoToolServerConnectionException {
        return reportManager.getCustomReports(projectId, usrIds);
    }
    /**
     * Obtain the MagicNumbers report with data provided
     */
    public MagicNumbers getMagicNumbers() {
       return reportManager.getMagicNumbers();
    }
}
