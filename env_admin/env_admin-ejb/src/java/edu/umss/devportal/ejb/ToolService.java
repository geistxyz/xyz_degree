
package edu.umss.devportal.ejb;

import edu.umss.devportal.common.IssueTracker;
import edu.umss.devportal.common.ProjectTracker;
import edu.umss.devportal.common.SourceCodeManager;
import edu.umss.devportal.common.ToolPlugin;
import edu.umss.devportal.ejb.session.LoginSessionBean;
import edu.umss.devportal.entity.ProjectEntity;
import edu.umss.devportal.entity.ToolByProject;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.ConfigurationException;
import javax.persistence.EntityManager;

import javax.persistence.Query;

import edu.umss.devportal.entity.ToolEntity;
import edu.umss.devportal.plugins.ToolManager;
import edu.umss.devportal.utils.Variable;
import edu.umss.devportal.utils.ConfigurationUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import javax.ejb.EJB;
import javax.persistence.PersistenceContext;

// TODO: Auto-generated Javadoc
/**
 * Session Bean implementation class ToolService.
 */
@Stateless
@LocalBean
public class ToolService implements ToolServiceRemote, ToolServiceLocal {

    @EJB
    private LoginSessionBean loginSessionBean;

    /** The emf. */
    @PersistenceContext(name = "devportal-EPU")
    EntityManager em;

    @EJB
    ProjectService projectService;

    /**
     * Default constructor. 
     */
    public ToolService() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Gets the tool.
     *
     * @return the tool
     */
    public List<ToolEntity> getTool() {
        List<ToolEntity> tools;

        String queryString = "SELECT t FROM ToolEntity t";
        Query query = em.createQuery(queryString);
        tools = (List<ToolEntity>) query.getResultList();
        return tools;
    }

    public void saveTool(ToolEntity toolEntity) {
        em.persist(toolEntity);
    }

    public HashMap<String, ArrayList<ToolPlugin>> getAllToolsByProject(ProjectEntity projectEntity) throws ConfigurationException {
        HashMap<String, ArrayList<ToolPlugin>> hashMap = new HashMap<String, ArrayList<ToolPlugin>>();

        Iterator<ToolByProject> iterator = projectEntity.getToolByProjects().iterator();
        while (iterator.hasNext()) {
            ToolByProject toolByProject = iterator.next();
            ToolPlugin toolPlugin = ToolManager.getToolByName(toolByProject.getTool().getName(),
                    toolByProject.getTool().getVersion());

            toolPlugin.applyConfiguration(ConfigurationUtil.getConfiguration(toolByProject));

            if (hashMap.get(toolByProject.getTool().getType()) != null) {
                hashMap.get(toolByProject.getTool().getType()).add(toolPlugin);
            }
            else {
                ArrayList<ToolPlugin> list = new ArrayList<ToolPlugin>();
                list.add(toolPlugin);
                hashMap.put(toolByProject.getTool().getType(), list);
            }

        }
        return hashMap;
    }

    public List<ProjectTracker> getAllProjectTracker(ProjectEntity projectEntity) throws ConfigurationException {

        List<ProjectTracker> list = new ArrayList<ProjectTracker>();
        Iterator<ToolByProject> iterator = projectEntity.getToolByProjects().iterator();
        while (iterator.hasNext()) {
            ToolByProject toolByProject = iterator.next();
            if (toolByProject.getTool().getType().equalsIgnoreCase("ProjectTracker")) {

                ProjectTracker projectTracker = ToolManager.getToolByName(toolByProject.getTool().getName(), toolByProject.getTool().getVersion(), ProjectTracker.class);
                projectTracker.applyConfiguration(ConfigurationUtil.getConfiguration(toolByProject));
                list.add(projectTracker);

            }
        }
        return list;
    }



    /**
     * Permits return project tracker related to the current project.
     *
     * @return project tracker related with the current project.
     * @throws ConfigurationException if happens a configuration error.
     */
    public ProjectTracker getProjectTrackerForCurrentProject() throws ConfigurationException {

        //TODO list.get(0) should be replaced with the current project, it will
        // be accesible from a bean.
        List<ProjectTracker> listaProjectTracker = getAllProjectTracker(loginSessionBean.getCurrentProject());

        // All projects just have a project tracker.
        return listaProjectTracker.isEmpty() ? null : listaProjectTracker.get(0);
    }




    public List<IssueTracker> getAllIssueTracker(ProjectEntity projectEntity) throws ConfigurationException {

        List<IssueTracker> list = new ArrayList<IssueTracker>();
        Iterator<ToolByProject> iterator = projectEntity.getToolByProjects().iterator();
        while (iterator.hasNext()) {
            ToolByProject toolByProject = iterator.next();
            if (toolByProject.getTool().getType().equalsIgnoreCase("IssueTracker")) {

                IssueTracker issueTracker = ToolManager.getToolByName(toolByProject.getTool().getName(), toolByProject.getTool().getVersion(), IssueTracker.class);
                issueTracker.applyConfiguration(ConfigurationUtil.getConfiguration(toolByProject));
                list.add(issueTracker);
            }
        }
        return list;
    }

    /**
     * Permits return the issue tracker related to the current project.
     *
     * @return issue tracker related with the current project.
     * @throws ConfigurationException if happens a configuration error.
     */
    public IssueTracker getIssueTrackerForCurrentProject() throws ConfigurationException {

        //TODO list.get(0) should be replaced with the current project, it will
        // be accesible from a bean.
        List<IssueTracker> listIssueTracker = getAllIssueTracker(loginSessionBean.getCurrentProject());

        // All projects just have an issue tracker.
        return listIssueTracker.isEmpty() ? null : listIssueTracker.get(0);
    }

    public List<SourceCodeManager> getAllSourceCodeManager(ProjectEntity projectEntity) throws ConfigurationException {
            List<SourceCodeManager> listSourceCodeManager = new ArrayList<SourceCodeManager>();
            Iterator<ToolByProject> iterator = projectEntity.getToolByProjects().iterator();

            while (iterator.hasNext()) {
                ToolByProject toolByProject = iterator.next();
                if (toolByProject.getTool().getType().equalsIgnoreCase("SourceCodeManager")) {
                    SourceCodeManager sourceCodeManager = ToolManager.getToolByName(toolByProject.getTool().getName(), toolByProject.getTool().getVersion(), SourceCodeManager.class);
                    sourceCodeManager.applyConfiguration(ConfigurationUtil.getConfiguration(toolByProject));
                    listSourceCodeManager.add(sourceCodeManager);
                }
            }
            return listSourceCodeManager;
        }

    /**
     * Permits return project tracker related to the current project.
     *
     * @return project tracker related with the current project.
     * @throws ConfigurationException if happens a configuration error.
     */
    public SourceCodeManager getSourceCodeManagerForCurrentProject() throws ConfigurationException {

        //TODO listSourceCodeManager.get(0) should be replaced with the current project, it will
        // be accesible from a bean.
        List<SourceCodeManager> sourceCodeManager = getAllSourceCodeManager(loginSessionBean.getCurrentProject());

        // All projects just have a project tracker.
        return sourceCodeManager.isEmpty() ? null : sourceCodeManager.get(0);
    }

    public void reviewInstallTools() {

        HashMap<String, Class> hashMap = Variable.getToolTypes();

        for (Map.Entry<String, Class> attr : hashMap.entrySet()) {
            ServiceLoader<ToolPlugin> allTools = ToolManager.getToolByType(attr.getValue());
            Iterator<ToolPlugin> iterator = allTools.iterator();
            while (iterator.hasNext()) {

                ToolPlugin toolPlugin = iterator.next();

                String queryString = "SELECT t FROM ToolEntity t where t.name = '"
                        + toolPlugin.getToolDescriptor().getName() + "' and t.version ='"
                        + toolPlugin.getToolDescriptor().getVersion() + "'";

                Query query = em.createQuery(queryString);

                List<ToolEntity> toolsInstalled = (List<ToolEntity>) query.getResultList();

                if (toolPlugin.getToolDescriptor().getName() != null
                        && toolPlugin.getToolDescriptor().getVersion() != null
                        && toolsInstalled.size() <= 0) {

                    ToolEntity toolEntity = new ToolEntity();
                    toolEntity.setName(toolPlugin.getToolDescriptor().getName());
                    toolEntity.setDescription(toolPlugin.getToolDescriptor().getDescription());
                    toolEntity.setVersion(toolPlugin.getToolDescriptor().getVersion());
                    toolEntity.setType(attr.getKey());

                    em.persist(toolEntity);
                }
            }
        }
    }

}
