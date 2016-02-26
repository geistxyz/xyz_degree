/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.reports.utilities;

import edu.umss.devportal.common.ToolPlugin;
import edu.umss.devportal.ejb.session.LoginSessionBean;
import edu.umss.edu.devportal.exception.NoToolServerConnectionException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Edson
 */
public class PluginManagerReports {

    private static PluginManagerReports pluginManagerReports = null;

    @EJB
    private LoginSessionBean loginSessionBean;

    private List<ToolPlugin> plugins;

    private final Logger log = Logger.getLogger(this.getClass().getName());

    private PluginManagerReports() throws NoToolServerConnectionException{
        plugins = new ArrayList<ToolPlugin>();

        try {
            plugins.add(loginSessionBean.getCurrentIssueTracker());
            plugins.add(loginSessionBean.getCurrentProjectTracker());
            plugins.add(loginSessionBean.getCurrentSourceCodeManager());
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                     "Error when listing reports.",
                                     "Error: " + ex));
            
            Logger.getLogger(PluginManagerReports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static PluginManagerReports getInstance() throws NoToolServerConnectionException {

        if (pluginManagerReports == null) {
            pluginManagerReports = new PluginManagerReports();
        }
        return pluginManagerReports;
    }

    public List<ToolPlugin> getPlugins() {
        return plugins;
    }
}
