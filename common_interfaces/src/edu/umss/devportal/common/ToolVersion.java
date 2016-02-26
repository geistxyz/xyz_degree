/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.common;

/**
 *
 * This class holds the version information. It holds four values for a version
 * the Major, Minor, Build and revision values.
 *
 * @author Raul Lopez
 * @version 1.0
 */
public class ToolVersion {
    private int major;
    private int minor;
    private int build;
    private int revision;

    /**
     * Creates an instance of ToolVersion class, values will be initialized in 0.     *
     */
    public ToolVersion() {
        this(0,0,0,0);
    }

    /***
     * Creates an instance of ToolVersion with given major and minor values. The other values
     * will be initialized in 0.
     *
     * @param major major value
     * @param minor minor value
     */
    public ToolVersion( int major, int minor) {
        this(major, minor, 0, 0);
    }

    /***
     *
     * Creates an instance of ToolVersion with the given major, minor and build values.
     * The revision value will be initialized in 0.
     *
     * @param major major value
     * @param minor minor value
     * @param build build value
     */
    public ToolVersion( int major, int minor, int build) {
        this(major, minor, build, 0);
    }

    /***
     *
     * Creates an instance of ToolVersion using given parameters for its values.
     *
     * @param major major value
     * @param minor minor value
     * @param build build value
     * @param revision revision value
     */
    public ToolVersion( int major, int minor, int build, int revision) {
        this.major = major;
        this.minor = minor;
        this.build = build;
        this.revision = revision;
    }

    /**
     * @return the major
     */
    public int getMajor() {
        return major;
    }

    /**
     * @param major the major to set
     */
    public void setMajor(int major) {
        this.major = major;
    }

    /**
     * @return the minor
     */
    public int getMinor() {
        return minor;
    }

    /**
     * @param minor the minor to set
     */
    public void setMinor(int minor) {
        this.minor = minor;
    }

    /**
     * @return the build
     */
    public int getBuild() {
        return build;
    }

    /**
     * @param build the build to set
     */
    public void setBuild(int build) {
        this.build = build;
    }

    /**
     * @return the revision
     */
    public int getRevision() {
        return revision;
    }

    /**
     * @param revision the revision to set
     */
    public void setRevision(int revision) {
        this.revision = revision;
    }

    /***
     *
     * Converts its class to a String following the format <bold>Version 1.2.3.4</bold>
     * 
     * @return A String representation for the ToolVersion.
     */
    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder(25);
        strBuilder.append("Version ");
        strBuilder.append(major);
        strBuilder.append('.');
        strBuilder.append(minor);
        strBuilder.append('.');
        strBuilder.append(build);
        strBuilder.append('.');
        strBuilder.append(revision);
        return strBuilder.toString();
    }


    

}
