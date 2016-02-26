/*
 * CreateRepositoryCommnad.java
 */
package edu.umss.devportal.plugins.scm.commands.impl;

import edu.umss.devportal.plugins.scm.command.Command;
import edu.umss.devportal.plugins.scm.services.SCMCommonsActions;

/**
 *References the receiver of commons actions and execute its corresponding action
 * @author Arminda Yovana Soto
 */
public class CreateRepositoryCommand implements Command {

    private SCMCommonsActions receiver;
    private String repositoryName;
    private String repositoryPath;

    /**
     * References to receiver indicated
     * @param receiver
     */
    public CreateRepositoryCommand(SCMCommonsActions receiver, String repositoryName,
                                   String repositoryPath) {
        this.receiver = receiver;
        this.repositoryName = repositoryName;
        this.repositoryPath = repositoryPath;
    }

    /**
     * Execute the action of receiver indicated
     */
    public void execute() {
        receiver.createRepository(repositoryName,repositoryPath);
    }
}
