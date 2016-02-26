/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.ejb;

import edu.umss.devportal.common.ToolPlugin;
import edu.umss.devportal.entity.ToolByProject;
import edu.umss.devportal.plugins.ToolManager;
import edu.umss.devportal.utils.ConfigurationUtil;
import edu.umss.edu.devportal.exception.MissingParameterException;
import edu.umss.edu.devportal.exception.ServerNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.ConfigurationException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Roger Ayaviri
 */
@Stateless
@LocalBean
public class ToolByProjectService implements ToolByProjectServiceRemote, ToolByProjectServiceLocal {

    /** The emf. */
    @PersistenceContext(name = "devportal-EPU")
    EntityManager em;

    public void updateToolByProject(ToolByProject toolByProject) {
        em.merge(toolByProject);
    }

    public boolean testConection(ToolByProject toolByProject) {
        boolean test = false;
        try {

            ToolPlugin toolPlugin = ToolManager.getToolByName(toolByProject.getTool().getName(), toolByProject.getTool().getVersion());
            test = toolPlugin.testConnection(ConfigurationUtil.getConfiguration(toolByProject));
            return test;
        }
        catch (MissingParameterException ex) {
            Logger.getLogger(ToolByProjectService.class.getName()).log(Level.SEVERE, null, ex);
            return test;
        }
        catch (ServerNotFoundException ex) {
            Logger.getLogger(ToolByProjectService.class.getName()).log(Level.SEVERE, null, ex);
            return test;
        }

    }

}
