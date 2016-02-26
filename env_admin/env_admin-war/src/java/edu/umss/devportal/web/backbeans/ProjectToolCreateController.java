/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.web.backbeans;

import edu.umss.devportal.common.Project;
import edu.umss.devportal.ejb.ProjectService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.naming.ConfigurationException;

/**
 *
 * @author raul
 */
@ManagedBean(name="projectToolBean")
@ViewScoped
public class ProjectToolCreateController implements Serializable {

    @ManagedProperty("#{configureTools}")
    private ConfigureToolsBean configureTools;

    @EJB
    private ProjectService projectService;

    private Project selectedProject;

    /** Creates a new instance of ProjectToolCreateController */
    public ProjectToolCreateController() {
    }

    private boolean createMirror = true;

    public boolean isCreateMirror() {
        return createMirror;
    }

    public void setCreateMirror(boolean createMirror) {
        this.createMirror = createMirror;
    }

    public void toogleCreateMirror() {
        setCreateMirror(!isCreateMirror());
    }

    public ConfigureToolsBean getConfigureTools() {
        return configureTools;
    }

    public void setConfigureTools(ConfigureToolsBean configureTools) {
        this.configureTools = configureTools;
    }

    private List<Project> selectedToolProjects;

    public List<Project> getSelectedToolProjects() throws ConfigurationException {
        if (configureTools.getProjectToolAssociation() == null)
            return null;
        return projectService.getAllPluguinsProjects(configureTools.getProjectToolAssociation());
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
}
