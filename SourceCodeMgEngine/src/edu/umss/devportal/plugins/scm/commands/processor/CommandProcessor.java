/*
 *CommandProcessor.java
 */
package edu.umss.devportal.plugins.scm.commands.processor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Process the commands,use Runtime for command execution
 * @author Arminda Yovana Soto
 */
public class CommandProcessor {

    private Runtime runtime;
    private Process process;
    private static CommandProcessor instance = null;

    /**
     * Constructor obtains the runtime of the System
     */
    private CommandProcessor() {
        runtime = Runtime.getRuntime();
    }

    /**
     * Returns an instance of this class
     * @return instance if null creates an instance
     */
    public static CommandProcessor getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }

    /**
     * Creates an instance of this class
     */
    private static synchronized void createInstance() {
        if (instance == null) {
            instance = new CommandProcessor();
        }
    }

    /**
     * Process the command in the path indicated
     * @param command a string of instructions
     * @param path a file where will be executed the command
     */
    public void process(String command, File path) {
        if (command.length() <= 0) {
            System.err.println("Need command to run");
            System.exit(-1);
        } else {
            try {
                process = runtime.exec(command, null, path);

            } catch (IOException ex) {
                Logger.getLogger(CommandProcessor.class.getName()).log(Level.SEVERE,
                        "The file path is not valid", ex.getCause());
            }
        }
    }

    /**
     * Returns a input stream of process
     * @return flow of the processor
     */
    public InputStream getInputStream() {
        return process.getInputStream();
    }
}
