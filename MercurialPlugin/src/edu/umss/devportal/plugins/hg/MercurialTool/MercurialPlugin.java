package edu.umss.devportal.plugins.hg.MercurialTool;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import edu.umss.devportal.common.ProjectRole;
import edu.umss.devportal.common.reports.Report;
import edu.umss.edu.devportal.exception.DevPortalPluginException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.InstanceNotFoundException;
import javax.naming.ConfigurationException;
import edu.umss.devportal.common.ParameterDescriptor;
import edu.umss.devportal.common.User;
import edu.umss.devportal.common.reports.ReportDescriptor;
import edu.umss.edu.devportal.exception.MissingParameterException;
import edu.umss.edu.devportal.exception.NoToolServerConnectionException;
import edu.umss.edu.devportal.exception.ServerNotFoundException;
import edu.umss.devportal.common.Project;
import edu.umss.devportal.common.SourceCodeManager;
import edu.umss.devportal.common.ToolDescriptor;
import edu.umss.devportal.common.ToolDescriptorImpl;
import edu.umss.devportal.common.ToolVersion;
import edu.umss.devportal.common.reports.DynamicStructure;
import edu.umss.devportal.plugins.hg.changesets.ChangeSet;
import edu.umss.devportal.plugins.hg.receiver.HgActionReceiver;
import edu.umss.devportal.plugins.scm.commands.processor.CommandProcessor;
import edu.umss.devportal.plugins.scm.repository.manager.SCMManager;
import edu.umss.devportal.plugins.scm.services.SCMCommonsActions;
import java.util.Iterator;

/**
 * Manages the input data for the UI Mercurial,describes the tool plug-in,
 * requires path, repository name, System Control Management name
 * @author Arminda Yovana Soto
 * @version 1.0
 */
public class MercurialPlugin implements SourceCodeManager {

    private ToolDescriptor basicToolDescriptor;
    private static final String repositoryPath="repopath";
    private SCMManager repositoryManager;
    private static final Logger logger = Logger.getLogger(MercurialPlugin.class.getName());

    public MercurialPlugin() {
        logger.log(Level.INFO, "MercurialTool MercurialTool()");
        SCMCommonsActions receiver = new HgActionReceiver(CommandProcessor.getInstance());
        repositoryManager = new SCMManager(receiver);
    }

    /**
     *Returns the mercurial plug-in description
     * @return basicToolDescriptor description of the plug-in
     */
    @Override
    public ToolDescriptor getToolDescriptor() {
        if (basicToolDescriptor == null) {
            basicToolDescriptor = new ToolDescriptorImpl("Mercurial Tool",
                    "A plugin that manage mercurial system",
                    new ToolVersion(1, 0));
        }
        return basicToolDescriptor;
    }

    /**
     * Creates repository in a 'repositorios' directory
     * @param project the repository can will be created
     */
    @Override
    public String createProject(Project project) {
        String reponame = repositoryManager.createRepository(project.getName(), repositoryPath);
        return reponame;
    }

    @Override
    public void applyConfiguration(Map<String, String> config) throws ConfigurationException {
    }

    @Override
    public void removeProject(String repositoryName) throws InstanceNotFoundException {
    }


    /**
     * Show the list in console of change sets objects
     */
    public void showLits(List<Object> list) {
        Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println("\t" + iterator.next());
        }
    }

    public DynamicStructure getChangeSet(HashMap<String, String> parameters) {
        String localhost = "localhost";
        String port = "8000";
        String protocol = "http";
        String scopes = ":";
        String doubleSlash = "//";
        String path = parameters.get("repositoryPath");
        String prefix = "repositorios/hg/" + parameters.get("repositoryName");
        String rev = "rev";
        String slash = "/";
        String revId;


        List showRepositoryVersions = repositoryManager.showRepositoryVersions(
                parameters.get("repositoryPath"));

        DynamicStructure dynamicStructure = new DynamicStructure();

        List<List<String>> result = new ArrayList<List<String>>();

        Iterator<ChangeSet> iterator = showRepositoryVersions.iterator();

        while (iterator.hasNext()) {
            ChangeSet changeSt = iterator.next();
            //buscar por nombre del repositorio
            result.add(Arrays.asList(new String[]{
                        changeSt.getDate(),
                        changeSt.getUserName(),
                        "<a href=\"" + protocol + scopes + doubleSlash + localhost + scopes + port
                                     + slash + prefix + slash + rev + slash
                                     + (changeSt.getRevision().getLiteralRevision())
                                     + "\">" + changeSt.getSummary() + "</a>"
                      }));

        }
        dynamicStructure.setDynamicList(result);

        List<String> headers = new ArrayList<String>();
        headers.add("Age");
        headers.add("Author");
        headers.add("Description");

        dynamicStructure.setHeadersList(headers);

        return dynamicStructure;
    }


    public boolean testConnection(Map<String, String> config) throws MissingParameterException,
            ServerNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String createUser(User user) throws Exception, NoToolServerConnectionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<User> getUserList() throws NoToolServerConnectionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeUser(String userId) throws NoToolServerConnectionException,
            InstanceNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<ReportDescriptor> getAvailableReports() {
        List<ReportDescriptor> availableReports = new ArrayList<ReportDescriptor>();
//        availableReports.add(new ReportDescriptor("Change Set", "Grid", ""));

        return availableReports;
    }

    public List<ParameterDescriptor> getRequiredParameters() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Project> getProjectList() {
        List listRepositories = repositoryManager.listRepositories(repositoryManager.getDefautltRepositoriesPath());
        return listRepositories;
    }

    public String getRepositoriesParentPath() {
        return repositoryManager.getDefautltRepositoriesPath();
    }

    public void setParentRepositoryPath(String nameOfParentRepositories) {
        repositoryManager.setDefaultAllRepositoriesPath(nameOfParentRepositories);
    }

    public void associateUserToProject(String projectId, String userId, ProjectRole role) throws DevPortalPluginException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Report> getGridReports(String projectId, List<String> usrIds) throws NoToolServerConnectionException {
        List<Report> reports = new ArrayList<Report>();
        return reports;
    }

    public List<Report> getChartReports(String projectId, List<String> usrIds) throws NoToolServerConnectionException {
        List<Report> reports = new ArrayList<Report>();
        return reports;
    }

    public List<Report> getCustomReports(String projectId, List<String> usrIds) throws NoToolServerConnectionException {
        List<Report> reports = new ArrayList<Report>();
        return reports;
    }
}
