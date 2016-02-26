/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.ejb;

import edu.umss.devportal.common.BasicUserImpl;
import edu.umss.devportal.common.ToolPlugin;
import edu.umss.devportal.common.User;
import edu.umss.devportal.entity.ToolByProject;
import edu.umss.devportal.entity.UserEntity;
import edu.umss.devportal.plugins.ToolManager;
import edu.umss.devportal.utils.ConfigurationUtil;
import edu.umss.edu.devportal.exception.NoToolServerConnectionException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.ConfigurationException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Roger Ayaviri
 */
@Stateless
@LocalBean
public class UserService implements UserServiceRemote, UserServiceLocal {
    
    @PersistenceContext(name = "devportal-EPU")
    EntityManager em;

    public void saveUser(UserEntity userEntity) {
        em.persist(userEntity);
    }

    public List<User> getUsersByTools(ToolByProject toolByProject) throws ConfigurationException, NoToolServerConnectionException{
        List<User> users = new ArrayList<User>();
        
        ToolPlugin toolPlugin = ToolManager.getToolByName(toolByProject.getTool().getName(), toolByProject.getTool().getVersion());
        toolPlugin.applyConfiguration(ConfigurationUtil.getConfiguration(toolByProject));
        users = toolPlugin.getUserList();

        return users;
    }

    public String createUser(ToolByProject toolByProject, UserEntity userEntity) throws ConfigurationException, Exception{
        String id= "";

        ToolPlugin toolPlugin = ToolManager.getToolByName(toolByProject.getTool().getName(), toolByProject.getTool().getVersion());
        toolPlugin.applyConfiguration(ConfigurationUtil.getConfiguration(toolByProject));

        BasicUserImpl userImpl= new BasicUserImpl();
        userImpl.setName(userEntity.getName());
        userImpl.setLogin(userEntity.getLogin());
        return toolPlugin.createUser(userImpl);

    }

    public List<UserEntity> getAllUsers() {
        Query query = em.createQuery("select distinct tp from UserEntity tp");
        List<UserEntity> usersList = query.getResultList();
        return usersList;
    }
}
