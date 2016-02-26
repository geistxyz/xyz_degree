/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.ejb;

import edu.umss.devportal.entity.ProjectEntity;
import edu.umss.devportal.entity.RolEntity;
import edu.umss.devportal.entity.UserEntity;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Roger Ayaviri
 */
@Stateless
@LocalBean
public class ProjectMembershipService implements ProjectMembershipServiceRemote, ProjectMembershipServiceLocal {
    

    @PersistenceContext(name = "devportal-EPU")
    EntityManager em;

    public List<UserEntity> getUsersByProject(ProjectEntity projectEntity){

        Query query = em.createQuery("select distinct pme.userEntity "
                + "from ProjectMembershipEntity pme "
                + "where pme.projectEntity.id = :id");
        query.setParameter("id", projectEntity.getId());
        List<UserEntity> userEntitys = query.getResultList();
        return userEntitys;
    }

    public List<ProjectEntity> getProjectByUser(UserEntity userEntity){

        Query query = em.createQuery("select distinct pme.projectEntity "
                + "from ProjectMembershipEntity pme "
                + "where pme.userEntity.id = :id");
        query.setParameter("id", userEntity.getId());
        List<ProjectEntity> projectEntitys = query.getResultList();
        return projectEntitys;
    }

    public List<RolEntity> getRolsByUserProject(UserEntity userEntity, ProjectEntity projectEntity){

        Query query = em.createQuery("select distinct pme.rolEntity "
                + "from ProjectMembershipEntity pme "
                + "where pme.userEntity.id = :idUser "
                + "and pme.projectEntity.id = :idProject");
        query.setParameter("idUser", userEntity.getId());
        query.setParameter("idProject", projectEntity.getId());
        List<RolEntity> rolEntitys = query.getResultList();
        return rolEntitys;
    }
 
}
