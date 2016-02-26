/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.common;

/**
 *
 * @author Alex Arenas
 */
public interface Tool {

    String getName();
    void setName(String name);
    String getServerAddress();
    void setServerAddress();
    String getPort();
    void setPort(int port);
    
}
