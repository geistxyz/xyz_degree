/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Roger Ayaviri
 */
@Entity
@IdClass(PrivilegeByRolPK.class)
public class PrivilegeByRol implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PRIVILEGE_ID", insertable = false, updatable = false)
    private int privilegeId;

    @Id
    @Column(name = "ROL_ID", insertable = false, updatable = false)
    private int rolId;

    @ManyToOne
    @JoinColumn(name = "PRIVILEGE_ID")
    private PrivilegeEntity privilegeEntity;

    @ManyToOne
    @JoinColumn(name = "ROL_ID")
    private RolEntity rolEntity;

    public PrivilegeEntity getPrivilegeEntity() {
        return privilegeEntity;
    }

    public void setPrivilegeEntity(PrivilegeEntity privilegeEntity) {
        this.privilegeEntity = privilegeEntity;
    }

    public int getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(int privilegeId) {
        this.privilegeId = privilegeId;
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

   

}
