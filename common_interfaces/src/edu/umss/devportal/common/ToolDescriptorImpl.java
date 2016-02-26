/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.common;

/**
 *
 * @author Raul Lopez
 * @version 1.0
 */
public class ToolDescriptorImpl implements ToolDescriptor {

    private String name;
    private String description;
    private ToolVersion version;

    public ToolDescriptorImpl() {        
    }
    
    public ToolDescriptorImpl(String name, String description, ToolVersion version) {
        this.name = name;
        this.description = description;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version.toString();
    }

}
