/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.scm.commands.impl;

import edu.umss.devportal.plugins.scm.command.Command;
import edu.umss.devportal.plugins.scm.services.SCMCommonsActions;

/**
 *Shows the revision of repository
 * @author Arminda Yovana Soto
 */
public class ShowRevisionCommand implements Command{

    private SCMCommonsActions receiver;
    private String path;
    /**
     * Receiver an action
     * @param receiver an instance
     */
    public ShowRevisionCommand(SCMCommonsActions receiver,String path) {
        this.receiver = receiver;
        this.path = path;
    }

    /**
     * Execute the action
     */
    public void execute() {
        this.receiver.showRevisions(path);
    }
}
