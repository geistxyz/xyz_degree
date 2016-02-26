/*
 * @(#)ToolDescriptor.java
 */

package edu.umss.devportal.common;

/**
 * Interface that permits to give information about a developing tool.  This
 * interface should be implemented for all plugins and will be used by the
 * application to manage plugins and to show information about a plugin int
 * the user interface.
 *
 * @version  1.0
 */
public interface ToolDescriptor {

    /**
     * @return tool's name
     */
    String getName();

    /**
     * @return tool's description.
     */
    String getDescription();

    /**
     * @return tool's version
     */
    String getVersion();
}
