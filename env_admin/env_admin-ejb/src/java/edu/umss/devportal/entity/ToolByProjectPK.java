package edu.umss.devportal.entity;

import java.io.Serializable;

public class ToolByProjectPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int projectId;
	
	private int toolId;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getToolId() {
		return toolId;
	}

	public void setToolId(int toolId) {
		this.toolId = toolId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + projectId;
		result = prime * result + toolId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToolByProjectPK other = (ToolByProjectPK) obj;
		if (projectId != other.projectId)
			return false;
		if (toolId != other.toolId)
			return false;
		return true;
	}



}
