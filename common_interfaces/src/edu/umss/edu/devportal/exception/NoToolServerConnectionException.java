/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.edu.devportal.exception;

/**
 *
 * @author raul
 */
public class NoToolServerConnectionException extends DevPortalPluginException {

    public NoToolServerConnectionException(String pluginName, String msg) {
        super(pluginName, msg);
    }

    public NoToolServerConnectionException(String pluginName) {
        super(pluginName);
    }


}
