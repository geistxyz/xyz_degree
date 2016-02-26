/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.web.backbeans;

import edu.umss.devportal.common.ParameterDescriptor;
import edu.umss.devportal.ejb.ToolByProjectService;
import edu.umss.devportal.entity.ToolProjectParameter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author raul
 */
@ManagedBean(name="connectionTester")
@ViewScoped
public class ConnectionTestBean implements Serializable, PropertyChangeListener {

    @ManagedProperty("#{configureToolBean}")
    private ToolListController configureToolBean;

    @ManagedProperty("#{configureTools}")
    private ConfigureToolsBean configureTools;

    public ConfigureToolsBean getConfigureTools() {
        return configureTools;
    }

    public void setConfigureTools(ConfigureToolsBean configureTools) {
        this.configureTools = configureTools;
        this.configureTools.addPropertyChangeListener(this);
    }

    @EJB ToolByProjectService toolByProjectService;

    private Boolean testSuccess = false;

    private String severity;

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Boolean getTestSuccess() {
        return testSuccess;
    }

    public void setTestSuccess(Boolean testSuccess) {
        this.testSuccess = testSuccess;
    }

    public ToolListController getConfigureToolBean() {
        return configureToolBean;
    }

    public void setConfigureToolBean(ToolListController configureToolBean) {
        this.configureToolBean = configureToolBean;
    }

    /** Creates a new instance of ConnectionTestBean */
    public ConnectionTestBean() {
        testSuccess = false;
    }

    public void testConnection() {

        List<ParameterDescriptor> connectionParameters = configureTools.getToolParameters();
        for (ParameterDescriptor param : connectionParameters) {
            ToolProjectParameter toolParam = new ToolProjectParameter();
            toolParam.setName(param.getName());
            toolParam.setAssignedValue(param.getDefaultValue());
            configureTools.getProjectToolAssociation().getParameters().add(toolParam);
        }

        setTestSuccess(toolByProjectService.testConection(configureTools.getProjectToolAssociation()));
        FacesMessage msg = new FacesMessage("Connection Test");
        if (getTestSuccess()) {
            msg.setDetail("Success");
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            setSeverity("info");

            if (configureTools.getSelectedToolProjects() == null) {
                configureTools.retrieveSelectedToolProjects();
            }
        }
        else {
            msg.setDetail("Destination host cannot be located");
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            setSeverity("warn");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String getTestMessage() {
        if (configureToolBean.getSelectedTool() == null)
            return "";
        return getTestSuccess() ? "Successful connection test to " + 
                configureToolBean.getSelectedTool().getServer() :
                    "Failed connection test to " + configureToolBean.getSelectedTool().getServer();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("toolToConfigure") && evt.getNewValue() == null) {
            reset();
        }
    }

    private void reset() {
        setTestSuccess(false);
    }

}
