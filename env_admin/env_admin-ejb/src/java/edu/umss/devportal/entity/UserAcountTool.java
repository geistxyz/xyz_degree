package edu.umss.devportal.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;

@Entity
@IdClass(UserAcountToolPK.class)
public class UserAcountTool implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PROJECT_ID", insertable = false, updatable = false)
	private int projectId;
	
	@Id
	@Column(name = "USER_ID", insertable = false, updatable = false)
	private int userId;
	
	@Id
	@Column(name = "TOOL_ID", insertable = false, updatable = false)
	private int toolId;
	
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private UserEntity userEntity;
	
	@ManyToOne
	@PrimaryKeyJoinColumns(value = { @PrimaryKeyJoinColumn })
	private ToolByProject toolByProject;


    private String userToolId;
	private String userName;
	private String password;

    public String getUserToolId() {
        return userToolId;
    }

    public void setUserToolId(String userToolId) {
        this.userToolId = userToolId;
    }


    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public ToolByProject getToolByProject() {
        return toolByProject;
    }

    public void setToolByProject(ToolByProject toolByProject) {
        this.toolByProject = toolByProject;
    }

    public int getToolId() {
        return toolId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserAcountTool other = (UserAcountTool) obj;
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
        return hash;
    }


    
}
