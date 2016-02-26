/*
 * @(#)Project.java
 */

package edu.umss.devportal.common;

/**
 * Interface that permits give a project information to the plug-ins.  This
 * information should be used by the plug-ins for configure it and it is provided
 * by the application.
 *
 * @version 1.0
 */
public interface Project {

    /**
     * @return project's name
     */
    String getName();
    /**
     * @return project's description
     */
    String getDescription();

    String getId();

    void setId(String id);


    void setName(String name);

    void setDescription(String description);


}
