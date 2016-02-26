/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.web.backbeans;

import edu.umss.devportal.common.Project;
import edu.umss.devportal.ejb.ProjectService;
import edu.umss.devportal.ejb.ToolService;
import edu.umss.devportal.entity.ProjectEntity;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Raul Garvizu
 */
@ManagedBean(name="projectListBean")
@SessionScoped
public class ProjectListController implements Serializable{

    @EJB
    ProjectService projectService;
    @EJB
    ToolService toolService;
    private ProjectEntity selectedProject;

    /** Creates a new instance of ProjectListController */
    public ProjectListController() {
    }

    public Collection<ProjectEntity> getProjects() {
        return projectService.getProjects();
    }

    /**
     * @return the selectedProject
     */
    public ProjectEntity getSelectedProject() {
        return selectedProject;
    }

    /**
     * @param selectedProject the selectedProject to set
     */
    public void setSelectedProject(ProjectEntity selectedProject) {
        this.selectedProject = selectedProject;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Project has been selected: ",
                ((ProjectEntity) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("No project is selected.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        setSelectedProject(null);
    }

    public boolean getIsThereASelectedProject() {
        return getSelectedProject() != null;
    }
}
