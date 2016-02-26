/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.edu.devportal.exception;

/**
 *
 * @author raul lopez
 */
public class DevPortalPluginException extends Exception {

    /**
     * Name of plug-in responsible of raising this exception.
     */
    private String pluginName;

    /**
     * Creates a new instance of <code>DevPortalPluginException</code> without detail message.
     */
    public DevPortalPluginException(String pluginName) {
        this.pluginName = pluginName;
    }

    /**
     * Creates a new instance of <code>DevPortalPluginException</code> without detail message.
     */
    public DevPortalPluginException(String pluginName, Throwable cause) {
        super(cause);
        this.pluginName = pluginName;
    }

    /**
     * Constructs an instance of <code>DevPortalPluginException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DevPortalPluginException(String pluginName, String msg) {
        super(msg);
        this.pluginName = pluginName;
    }

    /**
     * Constructs an instance of <code>DevPortalPluginException</code> with the specified detail message.
     * @param msg the detail message.
     * @param cause exception that cause the error
     */
    public DevPortalPluginException(String pluginName, String msg, Throwable cause) {
        super(msg, cause);
        this.pluginName = pluginName;
    }

    /**
     * Gets name of plugin who raised the exception.
     * @return Name of plugin owner of this exception
     */
    public String getPluginName() {
        return pluginName;
    }



}
