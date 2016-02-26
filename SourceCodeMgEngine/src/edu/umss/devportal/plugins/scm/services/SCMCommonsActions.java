/*
 * SCMCommonsActions.java
 */
package edu.umss.devportal.plugins.scm.services;

import edu.umss.devportal.common.User;
import java.util.List;

/**
 * Lists all the commons actions of a Source Code Manager(Subversion,Mercurial,Git,etc)
 * @author Arminda Yovana Soto
 */
public interface SCMCommonsActions {

    public void createRepository(String repositoryName, String path);

    public List showRevisions(String path);

    public void authentificateUser(User user);

    public void assignPermiss(User user);
}
