/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.web.backbeans;

import com.sun.rmi.rmid.ExecOptionPermission;
import edu.umss.devportal.common.ParameterDescriptor;
import edu.umss.devportal.common.Project;
import edu.umss.devportal.common.ToolPlugin;
import edu.umss.devportal.ejb.ProjectService;
import edu.umss.devportal.ejb.ToolService;
import edu.umss.devportal.entity.ToolByProject;
import edu.umss.devportal.entity.ToolEntity;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author raul
 */
@ManagedBean(name = "configureTools")
@ViewScoped
public class ConfigureToolsBean implements Serializable {

    @EJB
    ToolService toolService;
    private ToolEntity[] toolsToSelect;
    private ToolEntity[] toolsToRemove;
    private List<ToolEntity> tools;
    PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    @EJB
    private ProjectService projectService;
    private Project selectedProject;

    /** Creates a new instance of ConfigureToolsBean */
    public ConfigureToolsBean() {
    }
    private boolean createMirror = false;

    public boolean isCreateMirror() {
        return createMirror;
    }

    public void setCreateMirror(boolean createMirror) {
        this.createMirror = createMirror;
    }

    public void toogleCreateMirror() {
        setCreateMirror(!isCreateMirror());
        if (!isCreateMirror() && getSelectedToolProjects() == null) {
            retrieveSelectedToolProjects();
        }
    }

    public ToolEntity[] getToolsToRemove() {
        return toolsToRemove;
    }

    public void setToolsToRemove(ToolEntity[] toolsToRemove) {
        this.toolsToRemove = toolsToRemove;
    }
    private List<Project> selectedToolProjects;

    public void retrieveSelectedToolProjects() {
        try {
            if (projectToolAssociation != null) {
                selectedToolProjects =
                        projectService.getAllPluguinsProjects(projectToolAssociation);
            }

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error when listing projects.",
                    projectToolAssociation.getName() + ": " + ex));
        }
    }

    /**
     * Returns a list of available projects inside selected tool
     *
     * @return
     */
    public List<Project> getSelectedToolProjects() {
        return selectedToolProjects;
    }

    public void setSelectedToolProjects(List<Project> selectedToolProjects) {
        this.selectedToolProjects = selectedToolProjects;
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {

        this.selectedProject = selectedProject;
    }

    /**
     * @return the selectedTools
     */
    public ToolEntity[] getToolsToSelect() {
        return toolsToSelect;
    }

    /**
     * @param array containing Tools to select
     */
    public void setToolsToSelect(ToolEntity[] toolsToSelect) {
        this.toolsToSelect = toolsToSelect;
    }

    /**
     * @return the tools
     */
    public List<ToolEntity> getTools() {
        toolService.reviewInstallTools();
        tools = toolService.getTool();
        return tools;
    }
    private DualListModel<ToolEntity> availableTools;
    private List<ToolEntity> installedTools;

    public List<ToolEntity> getInstalledTools() {
        if (installedTools == null) {
            installedTools = new ArrayList<ToolEntity>();
            installedTools.addAll(getTools());
        }
        return installedTools;
    }

    public void setInstalledTools(List<ToolEntity> installedTools) {
        this.installedTools = installedTools;
    }

    public List<ToolEntity> getSelectedToolsList() {
        if (selectedToolsList == null) {
            selectedToolsList = new ArrayList<ToolEntity>();
        }
        return selectedToolsList;
    }

    public void setSelectedToolsList(List<ToolEntity> selectedToolsList) {
        this.selectedToolsList = selectedToolsList;
    }
    private List<ToolEntity> selectedToolsList;

    public DualListModel<ToolEntity> getAvailableTools() {
        if (availableTools == null) {
            availableTools = new DualListModel<ToolEntity>(getInstalledTools(), getSelectedToolsList());
        }
        return availableTools;
    }

    public void setAvailableTools(DualListModel<ToolEntity> availableTools) {
        this.availableTools = availableTools;
    }

    /**
     * @param tools the tools to set
     */
    public void setTools(List<ToolEntity> tools) {
        this.tools = tools;
    }

    public void addTools() {
        ToolEntity[] selectedTools = getToolsToSelect();
        for (int i = 0; i < selectedTools.length; i++) {
            getSelectedToolsList().add(selectedTools[i]);
            getInstalledTools().remove(selectedTools[i]);
        }
        setToolToConfigure(null);
    }

    public void removeTools() {
        ToolEntity[] removedTools = getToolsToRemove();
        for (int i = 0; i < removedTools.length; i++) {
            getInstalledTools().add(removedTools[i]);
            getSelectedToolsList().remove(removedTools[i]);
        }
        setToolsToRemove(null);
        setToolToConfigure(null);
    }
    private ToolEntity toolToConfigure;

    public ToolEntity getToolToConfigure() {
        return toolToConfigure;
    }

    public void setToolToConfigure(ToolEntity toolToConfigure) {
        pcs.firePropertyChange("toolToConfigure", this.toolToConfigure, toolToConfigure);
        this.toolToConfigure = toolToConfigure;
    }

    public void unselectedConfigureTool(UnselectEvent evt) {
        setToolToConfigure(null);
    }
    private ToolByProject projectToolAssociation;

    public ToolByProject getProjectToolAssociation() {
        return projectToolAssociation;
    }

    public void setProjectToolAssociation(ToolByProject projectToolAssociation) {
        this.projectToolAssociation = projectToolAssociation;
    }

    public void createProjectToolAssociation(SelectEvent evt) {
        ToolByProject association = new ToolByProject();
        association.setName(toolToConfigure.getName());
        association.setTool(toolToConfigure);
        association.setServer("http://localhost:9080/xplanner/");
        setProjectToolAssociation(association);
        setCreateMirror(true);
        setSelectedToolProjects(null);
        setToolParameters(null);

    }
    private List<ToolByProject> configuredTools;

    public List<ToolByProject> getConfiguredTools() {
        if (configuredTools == null) {
            configuredTools = new ArrayList<ToolByProject>();
        }
        return configuredTools;
    }

    public void setConfiguredTools(List<ToolByProject> configuredTools) {
        this.configuredTools = configuredTools;
    }

    public void saveProjectToolAssociationConfiguration() {

        ToolEntity tool = getToolToConfigure();
        ToolByProject association = getProjectToolAssociation();

        String serviceURL = "";

        for (ParameterDescriptor parameterDescriptor : toolParameters) {
            if (parameterDescriptor.getName().equals("serviceURL")) {
                serviceURL = parameterDescriptor.getDefaultValue();
            }
        }

        association.setServer(serviceURL);

        /*
         * TODO: it is required to ask for the ToolByProject.toolProjectId at the time the whole
         * project is being saved, in order to determine if a mirror project needs to be created
         * inside the tool
         */
        if (selectedProject != null) {
            String selectedProjectId = selectedProject.getId();
            association.setToolProjectId(selectedProjectId);

            String userName = "";
            String userIdInTool = "";
            setConfiguration(
                    userName,
                    selectedProject.getName(),
                    projectToolAssociation.getName(),
                    projectToolAssociation.getToolProjectId(),
                    userIdInTool);
        }

        association.setName(tool.getName());
        getConfiguredTools().add(association);
        getSelectedToolsList().remove(getToolToConfigure());
        setToolToConfigure(null);

        // THIS SHOULD REMOVE PROJECT CREATION IN XPLANNER AND TT
        //setCreateMirror(true);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public List<ParameterDescriptor> getToolParameters() {
        if (toolParameters == null) {
            ToolPlugin plugin = getPluginForSelectedTool();
            if (plugin != null) {
                toolParameters = plugin.getRequiredParameters();
            }
        }
        return toolParameters;
    }
    private List<ParameterDescriptor> toolParameters;

    public void setToolParameters(List<ParameterDescriptor> toolParameters) {
        this.toolParameters = toolParameters;
    }

    private ToolPlugin getPluginForSelectedTool() {
        if (getProjectToolAssociation() == null) {
            return null;
        }
        return getProjectToolAssociation().getTool().getPlugin();
    }

    void reset() {
        setToolToConfigure(null);
        setInstalledTools(null);
        setConfiguredTools(new ArrayList<ToolByProject>());
        setSelectedToolsList(new ArrayList<ToolEntity>());
    }

    private void setConfiguration(String userName,
            String projectName,
            String toolName,
            String toolId,
            String userIdInTool) {

        String key;
        String value;

        if (toolName.equals("Xplanner Tool")) {
            key = "ProjectTrackerProjectID";
        } else if (toolName.equals("Team Track Tool")) {
            key = "IssueTrackerProjectID";
        } else {
            key = toolName + "UserID";
        }

        value = toolId;

        File file = new File("configuration.properties");
        Properties properties = new Properties();

        if (file.exists()) {
            try {
                properties.load(new FileInputStream("configuration.properties"));
            } catch (IOException ex) {
                Logger.getLogger(ConfigureToolsBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            properties.setProperty(key, value);
            properties.store(new FileOutputStream("configuration.properties"), null);
        } catch (IOException exeption) {
            exeption.printStackTrace();
        }
    }
}
