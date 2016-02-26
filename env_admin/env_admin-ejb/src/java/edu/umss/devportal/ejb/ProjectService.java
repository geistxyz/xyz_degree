
package edu.umss.devportal.ejb;

import edu.umss.devportal.common.Project;
import edu.umss.devportal.common.ToolPlugin;
import edu.umss.devportal.entity.ProjectEntity;
import edu.umss.devportal.entity.ProjectMembershipEntity;
import edu.umss.devportal.entity.ToolByProject;
import edu.umss.devportal.entity.ToolEntity;
import edu.umss.devportal.entity.UserAcountTool;
import edu.umss.devportal.plugins.ToolManager;
import edu.umss.devportal.utils.ConfigurationUtil;
import edu.umss.devportal.utils.ProjectPlugin;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.ConfigurationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class ProjectService
 */
@Stateless
@LocalBean
public class ProjectService implements ProjectServiceRemote,
                                       ProjectServiceLocal {

    @PersistenceContext(name = "devportal-EPU")
    EntityManager em;

    /**
     * Default constructor.
     */
    public ProjectService() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Save project.
     *     
     * @param project to save
     */
    public void saveProject(ProjectEntity project) {

        em.persist(project);

    }

    public void updateProject(ProjectEntity project) {
        em.merge(project);
    }

    public Collection<ProjectEntity> getProjects() {
        Query query = em.createQuery("select distinct tp.project from ToolByProject tp");
        Collection<ProjectEntity> listProjectEntitys = query.getResultList();
        return listProjectEntitys;
    }

    public void addPluguinsToProject(ProjectEntity projectEntity, List<ToolByProject> tools) {

        Iterator<ToolByProject> iterator=tools.iterator();
        while (iterator.hasNext()) {
            ToolByProject toolByProject = iterator.next();
            if(toolByProject.getToolProjectId() == null || toolByProject.getToolProjectId().length() == 0){
                createProjectPlugin(toolByProject, projectEntity);
            }
        }        
        projectEntity.setToolByProjects(tools);
        em.merge(projectEntity);

    }

    public List<Project> getAllPluguinsProjects(ToolByProject toolByProject) throws ConfigurationException {

        List<Project> projects = new ArrayList<Project>();

        ToolPlugin toolPlugin = ToolManager.getToolByName(toolByProject.getTool().getName(), toolByProject.getTool().getVersion());
        toolPlugin.applyConfiguration(ConfigurationUtil.getConfiguration(toolByProject));
        projects = toolPlugin.getProjectList();

        return projects;
    }

    public void createProjectPlugin(ToolByProject toolByProject, ProjectEntity projectEntity) {

        ProjectPlugin projectPluguin = new ProjectPlugin();
        projectPluguin.setName(projectEntity.getName());
        projectPluguin.setDescription(projectEntity.getDescription());

        try {

            ToolPlugin toolPlugin = ToolManager.getToolByName(toolByProject.getTool().getName(), toolByProject.getTool().getVersion());
            toolPlugin.applyConfiguration(ConfigurationUtil.getConfiguration(toolByProject));
            String idProject=toolPlugin.createProject(projectPluguin);
            toolByProject.setToolProjectId(idProject);

            System.out.print("size" + toolByProject.getUserAcountTools().size());
            for (UserAcountTool userAcountTool : toolByProject.getUserAcountTools())
            {
                System.out.print("user acount" + userAcountTool.getUserToolId());
            }
        }

        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void assignProjectPlugin(ToolByProject toolByProject, Project project){
        toolByProject.setToolProjectId(project.getId());
        em.merge(toolByProject);
    }

    public void addUsersToProject(ProjectEntity project, List<ProjectMembershipEntity> memberAccounts) {
        for (ProjectMembershipEntity membership: memberAccounts) {
            membership.setProjectEntity(project);
            em.persist(membership);
            project.getMemberAccounts().add(membership);
        }
        em.merge(project);
    }

}
