/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.edu.devportal.exception;

/**
 *
 * @author Raul Lopez
 */
public class ServerNotFoundException extends DevPortalPluginException {

    private String server;

     /**
     * Constructs an instance of <code>ServerNotFoundException</code> with the specified detail message.
     * @param pluginName name of plugin who raises this exception.
     */
    public ServerNotFoundException(String pluginName, String server) {
        super(pluginName);
        this.server = server;
    }

    /**
     * Constructs an instance of <code>ServerNotFoundException</code> with the specified detail message.
     * @param pluginName name of plugin who raises this exception.
     * @param cause thowable object that cause the error
     */
    public ServerNotFoundException(String pluginName, String server, Exception cause) {
        super(pluginName, cause);
        this.server = server;
    }

    /**
     *
     * @return server
     */
    public String getServer() {
        return server;
    }

}
