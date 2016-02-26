/*
 * @(#)IssueTracker.java
 */

package edu.umss.devportal.common;

import edu.umss.devportal.common.reports.MagicNumbers;


/**
 * Interface that represents an issue tracker plugin.  This is used to publish
 * the generic behavior of a issue tracker tool, and manage any issue tracker
 * tool regardless which tool the application is managing.
 *
 * @version 1.0
 */
public interface IssueTracker extends ToolPlugin{
    public MagicNumbers getMagicNumbers();
}
