/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.scm.repository.manager;

import edu.umss.devportal.common.User;
import edu.umss.devportal.plugins.scm.commands.impl.CreateRepositoryCommand;
import edu.umss.devportal.plugins.scm.commands.impl.ShowRevisionCommand;
import edu.umss.devportal.plugins.scm.command.Command;
import edu.umss.devportal.plugins.scm.repository.Repository;
import edu.umss.devportal.plugins.scm.services.SCMCommonsActions;
import edu.umss.devportal.plugins.scm.commands.invoker.CommandInvoker;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *Manages the Repository operations
 * @author Arminda Yovana Soto
 */
public class SCMManager<E> {

    private Command command;
    private CommandInvoker commandInvoker;
    private SCMCommonsActions action;
    private String parentRootPathRepositories;

    /**
     * Constructor receives the required parameters
     * @param systemControlVersionName name of the system control version
     * @param projectName will be the repository name
     * @param repositoryPath the where repository will be created
     */
    public SCMManager(SCMCommonsActions systemControlVersion) {
        commandInvoker = new CommandInvoker();
        action = systemControlVersion;
    }

    /**
     * Creates the repository
     */
    public String createRepository(String projectName, String repositoryPath) {
        parentRootPathRepositories = RepositoriesRootPath.getRootPath(repositoryPath);
        setDefaultAllRepositoriesPath(parentRootPathRepositories);
        command = new CreateRepositoryCommand(action, projectName, parentRootPathRepositories);
        commandInvoker.setCommand(command);
        commandInvoker.invoke();
        return projectName;
    }

    /**
     * Returns the list of all change sets
     * @param repositoryPath the repository of this list change sets
     * @return revisions list of all change sets
     */
    public List<E> showRepositoryVersions(String repositoryPath) {
        List<E> revisions = new ArrayList<E>();
        command = new ShowRevisionCommand(action, repositoryPath);
        revisions = action.showRevisions(repositoryPath);
        return revisions;
    }

    /**
     * Authentificate user in the repository
     * @param user for the repository
     */
    public void authentificateUser(User user) {
        action.authentificateUser(user);
    }

    /**
     * Assign permission to user
     * @param user for the repository
     */
    public void assignPermissions(User user) {
        action.assignPermiss(user);
    }

    /**
     * Returns list of repositories from indicated path
     * @param pathAllRepositories
     * @return loadRepositories
     */
    public List<E> listRepositories(String pathAllRepositories) {
        Manager manager = new Manager(pathAllRepositories);
        List<Repository> loadRepositories = manager.loadRepositories(pathAllRepositories);
        return (List<E>) loadRepositories;
    }

    /**
     * Returns list of repositories from indicated path
     * @param pathAllRepositories
     * @return loadRepositories
     */
    public List<E> listRepositories() {
        Manager manager = new Manager(parentRootPathRepositories);
        List<Repository> loadRepositories = manager.loadRepositories();
        return (List<E>) loadRepositories;
    }

    /**
     * Returns the list of a specific repository
     * @param repositoryPath
     * @return
     */
    public List<E> getFilesIntoRepository(String repositoryPath) {
        Manager manager = new Manager(repositoryPath);
        List<File> loadFilesOfRepository = manager.loadFilesOfRepository(repositoryPath);
        return (List<E>) loadFilesOfRepository;
    }

    /**
     * Sets the general root path, where will be saved the created repositories
     * @param generaParentRootPath
     */
    public void setDefaultAllRepositoriesPath(String generaParentRootPath) {
        this.parentRootPathRepositories = generaParentRootPath;
    }

    /**
     * Returns the general root path
     * @return parentRootPathRepositories the root of all repositories
     */
    public String getDefautltRepositoriesPath() {
        return parentRootPathRepositories;
    }
}
