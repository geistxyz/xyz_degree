/*
 *  @(#)IssueTrackerDummnyDescriptor.java   28-nov-2010
 */

package edu.umss.devportal.plugins.dummy;

import edu.umss.devportal.common.ToolDescriptor;

/**
 * Implementation of the ToolDescriptor, provides information about the
 * issue tracker dummy.
 *
 * @author Alex Arenas
 * @version 1.0
 */
public class IssueTrackerDummnyDescriptor implements ToolDescriptor{

    private final String DESCRIPTION = "Issue Tracker Dummny Tool";
    private final String NAME = "IT Dummny";
    private final String VERSION = "v 1.0";
    
    public String getDescription() {
        return DESCRIPTION;
    }

    public String getName() {
        return NAME;
    }

    public String getVersion() {
        return VERSION;
    }

}
