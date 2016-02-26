/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Roger Ayaviri
 */
@Entity
@IdClass(ProjectMembershipEntityPK.class)
public class ProjectMembershipEntity implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "USER_ID", insertable = false, updatable = false)
    private int userId;

    @Id
    @Column(name = "PROJECT_ID", insertable = false, updatable = false)
    private int projectId;

    @Id
    @Column(name = "ROL_ID", insertable = false, updatable = false)
	private int rolId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private ProjectEntity projectEntity;

    @ManyToOne
    @JoinColumn(name = "ROL_ID")
    private RolEntity rolEntity;

    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public void setProjectEntity(ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public RolEntity getRolEntity() {
        return rolEntity;
    }

    public void setRolEntity(RolEntity rolEntity) {
        this.rolEntity = rolEntity;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
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

   
}
