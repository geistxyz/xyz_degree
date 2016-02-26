/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.web.backbeans;

import edu.umss.devportal.ejb.ToolByProjectService;
import edu.umss.devportal.entity.ToolByProject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Raul Garvizu
 */
@ManagedBean(name="configureToolBean")
@SessionScoped
public class ToolListController implements Serializable {

    @EJB ToolByProjectService toolByProjectService;

    private ToolByProject selectedTool;

    @ManagedProperty(value="#{projectListBean}")
    private ProjectListController projectListBean;

    /** Creates a new instance of ConfigureToolBean */
    public ToolListController() {
    }

    public ProjectListController getProjectListBean() {
        return projectListBean;
    }

    public void setProjectListBean(ProjectListController projectListBean) {
        this.projectListBean = projectListBean;
    }

    public ToolByProject getSelectedTool() {
        return selectedTool;
    }

    public void setSelectedTool(ToolByProject toolByProject) {
        this.selectedTool = toolByProject;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Tool has been selected",
                ((ToolByProject) event.getObject()).getTool().getName() +
                " for Project: " + projectListBean.getSelectedProject().getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        setSelectedTool(null);
    }

    public List<ToolByProject> getTools() {
        if (projectListBean.getSelectedProject() == null)
            return new ArrayList<ToolByProject>();
        return projectListBean.getSelectedProject().getToolByProjects();
    }

    public void save() {
        toolByProjectService.updateToolByProject(selectedTool);
    }

    private void resetFields() {

    }
}
