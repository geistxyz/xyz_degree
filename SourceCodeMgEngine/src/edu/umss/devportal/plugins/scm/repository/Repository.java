/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.scm.repository;

import edu.umss.devportal.common.Project;
import java.io.File;
import edu.umss.devportal.plugins.scm.utils.StringUtilities;

/**
 * The repository of the project
 * @author Arminda Yovana Soto
 */
public class Repository implements Project{

    private String description;
    private String name;
    private File rootPath;
    private final static String SEP = StringUtilities.FILE_SEPARATOR.getValue();

    /**
     * Constructor repository are instanced with null values
     */
    public Repository() {
        description = null;
        name = null;
        rootPath = null;
    }

    /**
     * Constructor initialize the repository name and repository path
     * @param name
     * @param rootPath
     */
    public Repository(String name,String rootPath) {
        this.name = name;
        this.rootPath = new File(rootPath);
    }

    /**
     * Sets the repository name
     * @param name repository name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the repository description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the repository path
     * @param rootPath path of the repository
     */
    public void setRootPath(File rootPath) {
        StringBuilder repoPath = new StringBuilder();
        repoPath.append(rootPath.getAbsolutePath()).append(SEP).append(name);
        File newPath = new File(repoPath.toString());
        this.rootPath = newPath;
    }

    /**
     * Returns the repository name
     * @return name repository name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a repository name that be showed
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the repository path
     * @return the repository path
     */
    public File getRootPath() {
        return rootPath;
    }

    /**
     * The repository name is the id
     * @return name repository name will be unique
     */
    public String getId() {
        return name;
    }

    /**
     * Sets the repository name how id
     * @param id name of repository
     */
    public void setId(String id) {
        this.name = id;
    }
}
