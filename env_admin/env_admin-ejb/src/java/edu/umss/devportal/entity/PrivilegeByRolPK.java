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
public class PrivilegeByRolPK implements Serializable{

    private static final long serialVersionUID = 1L;

    private int privilegeId;

	private int rolId;

    public int getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(int privilegeId) {
        this.privilegeId = privilegeId;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PrivilegeByRolPK other = (PrivilegeByRolPK) obj;
        if (this.privilegeId != other.privilegeId) {
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
        hash = 17 * hash + this.privilegeId;
        hash = 17 * hash + this.rolId;
        return hash;
    }
    


}
