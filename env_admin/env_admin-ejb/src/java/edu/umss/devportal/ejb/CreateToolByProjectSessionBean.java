/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.ejb;

import edu.umss.devportal.common.ToolPlugin;
import edu.umss.devportal.entity.ProjectEntity;
import edu.umss.devportal.entity.ToolByProject;
import edu.umss.devportal.entity.ToolEntity;
import edu.umss.devportal.entity.ToolProjectParameter;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author raul
 */
@Stateful
public class CreateToolByProjectSessionBean implements CreateToolByProjectSessionBeanLocal {

    @PersistenceUnit
    EntityManager em;

    private ToolByProject toolProject;



    @Override
    public void init( ProjectEntity project, ToolEntity tool, ToolPlugin plugin)
    {

        toolProject =  new ToolByProject();
        
        toolProject.setProject(project);
        toolProject.setProjectId(project.getId());
        toolProject.setTool(tool);
        toolProject.setToolId(tool.getId());

    }

    
    @Override
    public void addParameter(String name, String value)
    {
        ToolProjectParameter parameter = new ToolProjectParameter();
        parameter.setName(name);
        parameter.setAssignedValue(value);
        // TODO: check parameters name and value
        toolProject.getParameters().add(parameter);
    }


    @Remove
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void save()
    {
        em.persist(toolProject);
    }

}
