/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.edu.devportal.exception;

/**
 *
 * This exception class is intended to reflect missing parameters exceptions in plugins context.
 *
 * @author Raul Lopez
 * @version 1.0
 */
public class MissingParameterException extends DevPortalPluginException {

    private String paramName;

    /**
     * Constructs an instance of <code>MissingParameterException</code> with the specified detail message.
     * @param pluginName the detail message.
     * @param paramName  name of missing parameter.
     */
    public MissingParameterException(String pluginName, String paramName) {
        super(pluginName);
        this.paramName = paramName;
    }

    public String getParamName() {
        return paramName;
    }

}
