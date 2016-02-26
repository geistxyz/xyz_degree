/*
 *  @(#)TypeUtil.java   30-dic-2010
 */

package edu.umss.devportal.plugins.teamtrack.util;

import edu.umss.devportal.common.BasicProjectImpl;
import edu.umss.devportal.common.BasicUserImpl;
import edu.umss.devportal.common.Project;
import edu.umss.devportal.common.User;
import edu.umss.devportal.plugins.teamtrack.model.TsProjects;
import edu.umss.devportal.plugins.teamtrack.model.TsUsers;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alex Arenas
 */
public class TypeUtil {

    /**
     * Permits coy a list of projects of the the persistence model in another
     * list of common project objects.
     *
     * @param projects list of project objects from persistent model
     * @return a list of common project objects.
     */
    public static List<Project> copyProjectList(List<TsProjects> projects){
        List<Project> allProjects = new ArrayList<Project>();

        for (TsProjects tsProjects : projects) {
            allProjects.add(createProject(tsProjects));
        }

        return allProjects;
    }

    /**
     * Permits create a common project based in a project of the persistent model.
     *
     * @param tsProject object from persistent model.
     * @return a instance of common project.
     */
    public static Project createProject(TsProjects tsProject){
        Project project = new BasicProjectImpl();
        project.setName(tsProject.getTsName());
        project.setDescription(tsProject.getTsDescription());
        // project.setId(Integer.toString(tsProject.getTsId()));

        return project;
    }

    /**
     * Permits coy a list of users from the the persistence model in another
     * list of common project objects.
     *
     * @param users persistence model user list.
     * @return a new list of common user objects
     */
    public static List<User> copyUserList(List<TsUsers> users){
        List<User> allProjects = new ArrayList<User>();

        for (TsUsers tsUsers : users) {
            allProjects.add(createUser(tsUsers));
        }

        return allProjects;
    }

    /**
     * Permits create a common user based on persistent model user.
     * @param tsUser user from persistense model
     * @return common user.
     */
    public static User createUser(TsUsers tsUser){
        User user = new BasicUserImpl();
        user.setName(tsUser.getTsName());
        user.setPassword(tsUser.getTsPassword());
        user.setLogin(tsUser.getTsLoginid());

        return user;
    }
}
