/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.entity;

import java.io.Serializable;

/**
 *
 * @author Roger Ayaviri
 */
public class ProjectMembershipEntityPK implements Serializable{

    private static final long serialVersionUID = 1L;

    private int userId;

    private int projectId;

	private int rolId;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProjectMembershipEntityPK other = (ProjectMembershipEntityPK) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.projectId != other.projectId) {
            return false;
        }
        if (this.rolId != other.rolId) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.userId;
        hash = 59 * hash + this.projectId;
        hash = 59 * hash + this.rolId;
        return hash;
    }



}
