/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.ejb.session;

import edu.umss.devportal.common.IssueTracker;
import edu.umss.devportal.common.ProjectTracker;
import edu.umss.devportal.common.SourceCodeManager;
import edu.umss.devportal.common.ToolPlugin;
import edu.umss.devportal.common.VersionControlTracker;
import edu.umss.devportal.ejb.ProjectMembershipService;
import edu.umss.devportal.ejb.ProjectService;
import edu.umss.devportal.ejb.ToolService;
import edu.umss.devportal.entity.ProjectEntity;
import edu.umss.devportal.entity.ProjectMembershipEntity;
import edu.umss.devportal.entity.ToolByProject;
import edu.umss.devportal.entity.UserEntity;
import edu.umss.devportal.plugins.ToolManager;
import edu.umss.devportal.utils.ConfigurationUtil;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.naming.ConfigurationException;

/**
 * Session bean to store the currently logged in user and selected project
 *
 * @author Raul Garvizu
 */
@Stateless
@LocalBean
public class LoginSessionBean {
    @EJB
    private ProjectMembershipService projectMembershipService;

    @EJB
    private ProjectService projectService;

    @EJB
    private ToolService toolService;
    
    protected ProjectEntity currentProject;

    protected IssueTracker currentIssueTracker;

    protected SourceCodeManager currentSourceCodeManager;

    protected ProjectTracker currentProjectTracker;

    protected String idProjectProjectTracker;

    protected String idProjectIssueTracker;

    protected String idProjectSourceCodeManager;
    
    protected String idProjectVersionControlTracker;

    private ProjectMembershipEntity projectMembership;
    
    /**
     * Get the value of currentProject
     *
     * @return the value of currentProject
     */
    public ProjectEntity getCurrentProject() {
        if (currentProject == null){
            Collection<ProjectEntity> projectsList = projectService.getProjects();
            if (!projectsList.isEmpty())
                currentProject = (ProjectEntity) projectsList.toArray()[0];
        }
        System.out.println(currentProject);
        return currentProject;
    }

    public List<ProjectEntity> getMyProjects() {
        return projectMembershipService.getProjectByUser(user);
    }

    
    /**
     * Set the value of currentProject
     *
     * @param currentProject new value of currentProject
     */
    public void setCurrentProject(ProjectEntity currentProject) throws ConfigurationException {
        this.currentProject = currentProject;

        Iterator<ToolByProject> iterator = currentProject.getToolByProjects().iterator();
        while (iterator.hasNext()) {
            ToolByProject toolByProject = iterator.next();

            if (toolByProject.getTool().getType().equalsIgnoreCase("ProjectTracker")) {

                currentProjectTracker = ToolManager.getToolByName(toolByProject.getTool().getName(), toolByProject.getTool().getVersion(), ProjectTracker.class);
                currentProjectTracker.applyConfiguration(ConfigurationUtil.getConfiguration(toolByProject));
                idProjectProjectTracker = toolByProject.getToolProjectId();

            }else if (toolByProject.getTool().getType().equalsIgnoreCase("IssueTracker")){
                currentIssueTracker = ToolManager.getToolByName(toolByProject.getTool().getName(), toolByProject.getTool().getVersion(), IssueTracker.class);
                currentIssueTracker.applyConfiguration(ConfigurationUtil.getConfiguration(toolByProject));
                idProjectIssueTracker = toolByProject.getToolProjectId();
            }else if (toolByProject.getTool().getType().equalsIgnoreCase("VersionControlTracker")){
                currentIssueTracker = ToolManager.getToolByName(toolByProject.getTool().getName(), toolByProject.getTool().getVersion(), IssueTracker.class);
                currentIssueTracker.applyConfiguration(ConfigurationUtil.getConfiguration(toolByProject));
                idProjectVersionControlTracker = toolByProject.getToolProjectId();
            }
             else if (toolByProject.getTool().getType().equalsIgnoreCase("SourceCodeManager")) {
                currentSourceCodeManager = ToolManager.getToolByName(toolByProject.getTool().getName(), toolByProject.getTool().getVersion(), SourceCodeManager.class);
                currentSourceCodeManager.applyConfiguration(ConfigurationUtil.getConfiguration(toolByProject));
                idProjectSourceCodeManager = toolByProject.getToolProjectId();
            }

        }


        List<ProjectTracker> projectTrackerList =toolService.getAllProjectTracker(currentProject);
        currentProjectTracker = projectTrackerList.isEmpty() ? null : projectTrackerList.get(0);

        List<IssueTracker> projectIssueTrackers =toolService.getAllIssueTracker(currentProject);
        currentIssueTracker = projectIssueTrackers.isEmpty() ? null : projectIssueTrackers.get(0);

        List<SourceCodeManager> sourceCodeManagers =toolService.getAllSourceCodeManager(currentProject);
        currentSourceCodeManager = sourceCodeManagers.isEmpty() ? null : sourceCodeManagers.get(0);
        
    }


    protected UserEntity user;

    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ToolPlugin getTool(Class class1) throws ConfigurationException{

        ToolPlugin toolPlugin=null;

        if (currentProject.getToolByProjects().size()> 0){
            ToolByProject toolByProject=currentProject.getToolByProjects().get(0);
            toolPlugin =ToolManager.getToolByName(toolByProject.getTool().getName(), toolByProject.getTool().getVersion(), class1);
            toolPlugin.applyConfiguration(ConfigurationUtil.getConfiguration(toolByProject));
        }
        return toolPlugin;
    }

    public IssueTracker getCurrentIssueTracker() {
        return currentIssueTracker;
    }

    public void setCurrentIssueTracker(IssueTracker currentIssueTracker) {
        this.currentIssueTracker = currentIssueTracker;
    }

    public ProjectTracker getCurrentProjectTracker() {
        return currentProjectTracker;
    }

    public void setCurrentProjectTracker(ProjectTracker currentProjectTracker) {
        this.currentProjectTracker = currentProjectTracker;
    }

    public String getIdProjectIssueTracker() {
        return idProjectIssueTracker;
    }

    public void setIdProjectIssueTracker(String idProjectIssueTracker) {
        this.idProjectIssueTracker = idProjectIssueTracker;
    }

    public String getIdProjectProjectTracker() {
        return idProjectProjectTracker;
    }

    public void setIdProjectProjectTracker(String idProjectProjectTracker) {
        this.idProjectProjectTracker = idProjectProjectTracker;
    }

    public ProjectMembershipEntity getCurrentProjectMembership() {
        return projectMembership;
    }

    public void setCurrentProjectMembership(ProjectMembershipEntity currentProjectMembership) {
        this.projectMembership = currentProjectMembership;
    }

    public void updateCurrentProjectMembership() {
        for (ProjectMembershipEntity membership: user.getProjectMembershipEntitys()) {
            if (membership.getProjectEntity().getId() == currentProject.getId()) {
                projectMembership = membership;
                try {
                    setCurrentProject(membership.getProjectEntity());
                } catch (ConfigurationException ex) {
                    Logger.getLogger(LoginSessionBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
    }

     public SourceCodeManager getCurrentSourceCodeManager() {
        return currentSourceCodeManager;
    }

    public void setCurrentSourceCodeManager(SourceCodeManager currentSourceCodeManager) {
        this.currentSourceCodeManager = currentSourceCodeManager;
    }

    public String getIdProjectVersionControlTracker() {
        return idProjectVersionControlTracker;
    }

    public void setIdProjectVersionControlTracker(String idProjectVersionControlTracker) {
        this.idProjectVersionControlTracker = idProjectVersionControlTracker;
    }

    public ProjectMembershipEntity getProjectMembership() {
        return projectMembership;
    }

    public void setProjectMembership(ProjectMembershipEntity projectMembership) {
        this.projectMembership = projectMembership;
    }

    public ProjectMembershipService getProjectMembershipService() {
        return projectMembershipService;
    }

    public void setProjectMembershipService(ProjectMembershipService projectMembershipService) {
        this.projectMembershipService = projectMembershipService;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public ToolService getToolService() {
        return toolService;
    }

    public void setToolService(ToolService toolService) {
        this.toolService = toolService;
    }

    
}
