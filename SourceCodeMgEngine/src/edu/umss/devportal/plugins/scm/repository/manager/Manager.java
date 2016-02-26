/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.scm.repository.manager;

import edu.umss.devportal.plugins.scm.repository.Repository;
import edu.umss.devportal.plugins.scm.utils.DirectoryManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Manage all repositories repositories folder
 * @author Arminda Yovana Soto
 */
public class Manager {

    private String defaultPath;
    private List<Repository> listRepositories;
    private List<File> listFiles;

    /**
     * Constructor initialize the list of repositories
     * @param path the root path of general repositories
     */
    public Manager(String path) {
        this.defaultPath = path;
        this.listRepositories = new ArrayList<Repository>();
        this.listFiles = new ArrayList<File>();
    }
    /**
     * Returns all repositories of the general path for default into the 'repositorios' folder
     * @param pathGeneralRepositories
     * @return listRepositories all the repositories in the path indicated
     */
    public List<Repository> loadRepositories() {
        File search = new File(DirectoryManager.search(defaultPath));
        if (search.isDirectory()) {
            for (File file : search.listFiles()) {
                if (file.isDirectory()) {
                    Repository repository = new Repository(file.getName(), file.getAbsolutePath());
                    boolean add = listRepositories.add(repository);
                    if (!add) {
                        Logger.getLogger(Manager.class.getName()).log(Level.INFO, "don't add the"
                                + "repository to list");
                    }
                }
            }

        } else {
            Logger.getLogger(Manager.class.getName()).log(Level.INFO, "cann't found"
                    + " the repositories root");
        }
        return listRepositories;
    }


    /**
     * Instanced the repositories and loads to list of repositories
     * @param pathGeneralRepositories
     * @return listRepositories all the repositories in the path indicated
     */
    public List<Repository> loadRepositories(String pathGeneralRepositories) {
        File search = new File(DirectoryManager.search(pathGeneralRepositories));
        if (search.isDirectory()) {
            for (File file : search.listFiles()) {
                if (file.isDirectory()) {
                    Repository repository = new Repository(file.getName(), file.getAbsolutePath());
                    boolean add = listRepositories.add(repository);
                    if (!add) {
                        Logger.getLogger(Manager.class.getName()).log(Level.INFO, "don't add the"
                                + "repository to list");
                    }
                }
            }

        } else {
            Logger.getLogger(Manager.class.getName()).log(Level.INFO, "cann't found"
                    + " the repositories root");
        }
        return listRepositories;
    }

    /**
     * Updates the list of repositories, newly
     * @param rootPath location of all repositories
     */
    public List<Repository> update(String rootPath) {
        List<Repository> loadRepositories = loadRepositories(rootPath);
        return loadRepositories;
    }

    /**
     * Returns the parent location of all repositories
     * @return path directory of all repositories
     */
    public String getGeneralPath() {
        return this.defaultPath;
    }

    /**
     * Returns list of files from the specific repository
     * @param repositoryPath the repositories path
     * @return listFiles all files inside specific repository
     */
    public List<File> loadFilesOfRepository(String repositoryPath) {
        File repository = new File(repositoryPath);
        if (repository.exists() && repository.isDirectory()) {
            boolean addAll = listFiles.addAll(Arrays.asList(repository.listFiles()));
            if (!addAll) {
                Logger.getLogger(Manager.class.getName()).log(Level.INFO, "donf charged the files"
                        + "from specific repository");
            }
        }
        return listFiles;
    }
}
