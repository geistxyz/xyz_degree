/*
 * CommnandInvoker.java
 */
package edu.umss.devportal.plugins.scm.commands.invoker;

import edu.umss.devportal.plugins.scm.command.Command;

/**
 * Manage the command
 * @author Arminda Yovana Soto
 */
public class CommandInvoker {

    private Command commnad;

    /**
     * Sets a command
     * @param command
     */
    public void setCommand(Command command) {
        this.commnad = command;
    }

    /**
     * Invokes the command for execute its action
     */
    public void invoke() {
        commnad.execute();
    }
}
