package edu.umss.devportal.entity;

import java.io.Serializable;

public class UserAcountToolPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private int projectId;

	private int userId;

	private int toolId;

	public int getToolId() {
		return toolId;
	}

	public void setToolId(int toolId) {
		this.toolId = toolId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getUserSystemId() {
		return userId;
	}

	public void setUserSystemId(int userId) {
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
        final UserAcountToolPK other = (UserAcountToolPK) obj;
        if (this.projectId != other.projectId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (this.toolId != other.toolId) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.projectId;
        hash = 37 * hash + this.userId;
        hash = 37 * hash + this.toolId;
        return hash;
    }

}
