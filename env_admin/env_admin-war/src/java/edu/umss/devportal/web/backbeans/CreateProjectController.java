/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.web.backbeans;

import edu.umss.devportal.ejb.ProjectService;
import edu.umss.devportal.ejb.RolService;
import edu.umss.devportal.ejb.ToolByProjectService;
import edu.umss.devportal.ejb.UserService;
import edu.umss.devportal.ejb.session.LoginSessionBean;
import edu.umss.devportal.entity.ProjectEntity;
import edu.umss.devportal.entity.RolEntity;
import edu.umss.devportal.entity.ToolByProject;
import edu.umss.devportal.entity.UserAcountTool;
import edu.umss.devportal.web.utils.MemberConfigurationHelper;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.ConfigurationException;

/**
 *
 * @author raul
 */
@ManagedBean(name="createProjectBean")
@ViewScoped
public class CreateProjectController implements Serializable{
    @EJB
    private RolService rolService;

    @EJB ProjectService projectService;

    @EJB UserService userService;

    @EJB ToolByProjectService toolByProjectService;

    @EJB LoginSessionBean logingSessionBean;

    @ManagedProperty("#{configureTools}")
    private ConfigureToolsBean configureTools;

    @ManagedProperty("#{configureUsers}")
    private ConfigureUsersBean configureUsers;

    private MemberConfigurationHelper selectedMembershipConfigHelper;

    private ProjectEntity project;

    /** Creates a new instance of CreateProjectController */
    public CreateProjectController() {

    }

    public ProjectEntity getProject() {
        if (project == null)
            project = new ProjectEntity();
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    /**
     * Action method called when clicking "Save" button in Create Project
     * Dialog
     */
    public void saveProjectAction() {
        projectService.saveProject(project);
        projectService.addPluguinsToProject(project, configureTools.getConfiguredTools());
        saveMembersDetails();
        projectService.addUsersToProject(project, configureUsers.getMemberAccounts());
        resetFields();
    }


    /**
     * Process member configuration details
     */
    private void saveMembersDetails() {
        List<RolEntity> roles = rolService.getRolsApp();
        for (MemberConfigurationHelper config: configureUsers.getMemberConfigurationDetails()) {

            for(ToolByProject toolByProject : project.getToolByProjects()){

                UserAcountTool userAcountTool= config.getSelectedMirrorAccounts().get(toolByProject);
                if(userAcountTool == null){
                    userAcountTool = new UserAcountTool();
                    userAcountTool.setUserEntity(config.getMembership().getUserEntity());

                    try {
                        userAcountTool.setUserToolId(userService.createUser(toolByProject, config.getMembership().getUserEntity()));
                    }
                    catch (ConfigurationException ex) {
                        Logger.getLogger(CreateProjectController.class.getName()).log(Level.SEVERE, "User account tool could not be set", ex);
                    }
                    catch (Exception ex) {
                        Logger.getLogger(CreateProjectController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    userAcountTool.setToolByProject(toolByProject);
                }
                toolByProject.getUserAcountTools().add(userAcountTool);
                toolByProjectService.updateToolByProject(toolByProject);

            }

            for (RolEntity role: roles) {
                if (config.getAssignedRoleName().equals(role.getName())) {
                    config.getMembership().setRolEntity(role);
                    break;
                }
            }
        }
    }

    /**
     * Restores the bean fields to enable the dialog to be used to create a new
     * Project from scratch
     */
    public void resetFields() {
        setProject(new ProjectEntity());
        configureTools.reset();
        configureUsers.reset();
    }

    /**
     * @return the configureTools
     */
    public ConfigureToolsBean getConfigureTools() {
        return configureTools;
    }

    /**
     * @param configureTools the configureTools to set
     */
    public void setConfigureTools(ConfigureToolsBean configureTools) {
        this.configureTools = configureTools;
    }

    /**
     * @return the configureUsers
     */
    public ConfigureUsersBean getConfigureUsers() {
        return configureUsers;
    }

    /**
     * @param configureUsers the configureUsers to set
     */
    public void setConfigureUsers(ConfigureUsersBean configureUsers) {
        this.configureUsers = configureUsers;
    }

    public void validateName() {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Entered Project name: ", getProject().getName()));
    }

    public MemberConfigurationHelper getSelectedMembershipConfigHelper() {
        return selectedMembershipConfigHelper;
    }

    public void setSelectedMembershipConfigHelper(MemberConfigurationHelper selectedMembershipConfigHelper) {
        this.selectedMembershipConfigHelper = selectedMembershipConfigHelper;
    }
}
