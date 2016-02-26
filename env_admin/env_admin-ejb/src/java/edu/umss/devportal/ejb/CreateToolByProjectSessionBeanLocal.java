/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.ejb;



import edu.umss.devportal.common.ToolPlugin;
import edu.umss.devportal.entity.ProjectEntity;
import edu.umss.devportal.entity.ToolEntity;
import javax.ejb.Local;

/**
 *
 * @author raul
 */
@Local
public interface CreateToolByProjectSessionBeanLocal {

    void init(ProjectEntity project, ToolEntity tool, ToolPlugin plugin);

    void addParameter(String name, String value);

    void save();
    
}
