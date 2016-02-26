/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.hg.changesets;

/**
 *Represents the change set of a revision
 *
 * @author Arminda Yovana Soto
 */
public class ChangeSet {

    private Revision revision;
    private String tag;
    private String userName;
    private String date;
    private String summary;

    public ChangeSet(Revision idRevision, String tag, String userName,String date, String summary) {
        this.revision = idRevision;
        this.tag = tag;
        this.userName = userName;
        this.date = date;
        this.summary = summary;
    }

    /**
     * Sets date
     * @param date the modification date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Sets a revision identifier
     * @param idRevision identifier of the revision
     */
    public void setRevision(Revision idRevision) {
        this.revision = idRevision;
    }

    /**
     * Sets the summary or description of one revision
     * @param summary represents the description in the summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Sets the tag of a revision or one change set
     * @param tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Returns the user name that modified a file in the change set
     * @param userName name of user
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns the date of the change set
     * @return date the last modification
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the id of the change set
     * @return idRevision number of revision
     */
    public Revision getRevision() {
        return revision;
    }

    /**
     * Returns the message of the modified file
     * @return summary description of the change set
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Returns the tag of the change set
     * @return tag represent a name of the change set
     */
    public String getTag() {
        return tag;
    }

    /**
     * Returns user name of the change set
     * @return userName userName
     */
    public String getUserName() {
        return userName;
    }
}
