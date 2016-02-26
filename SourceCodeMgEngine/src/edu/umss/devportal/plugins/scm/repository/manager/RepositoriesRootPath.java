/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.scm.repository.manager;

import edu.umss.devportal.plugins.scm.utils.DirectoryManager;
import edu.umss.devportal.plugins.scm.utils.StringUtilities;

/**
 *Manage the general path of repositories for default is 'repositorios'
 * into this directory will be create the repositories.
 * @author Arminda Yovana Soto
 */
public class RepositoriesRootPath{

    /**
     * If the user decides change the directory name where repositories will be saved
     * he can sets other name before of create a new repository,this option is not recommended
     * @param generalRepositoriesPath the new name of directory of the repositories
     */
    public static void setRepositoriesDirectoryPath(String generalRepositoriesPath) {
        StringUtilities.REPOSITORIES_NAME.setValue(generalRepositoriesPath);
    }

    /**
     * Returns the repository path integrated into 'repositorios' directory
     * @param repositoryPath path of repository
     * @return the complete path
     */
    public static String getRootPath(String repositoryPath) {
        String root = repositoryPath.concat(StringUtilities.FILE_SEPARATOR.getValue()).
                                            concat(StringUtilities.REPOSITORIES_NAME.getValue());

        String createDirectory = null;
        if (DirectoryManager.search(root) == null) {
             createDirectory = DirectoryManager.createDirectory(root);
        } else {
             createDirectory = root;
        }
      return createDirectory;
    }

}
