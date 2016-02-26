/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

/**
 *
 * @author Roger Ayaviri
 */
@Entity
public class RolEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
	@TableGenerator(name = "Rol_Gen",
			        table = "ID_GEN",
			        pkColumnName = "GEN_NAME",
			        valueColumnName = "GEN_VAL",
			        pkColumnValue = "Rol_Gen",
			        initialValue = 10000,
			        allocationSize = 100)
	@GeneratedValue(strategy=GenerationType.TABLE, generator = "Rol_Gen")

    private int id;

    private String name;

    @OneToMany(mappedBy= "rolEntity", fetch = FetchType.EAGER)
	private List<PrivilegeByRol> privilegeByRols;

    @OneToMany(mappedBy= "rolEntity", fetch = FetchType.EAGER)
	private List<ProjectMembershipEntity> projectMembershipEntitys;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PrivilegeByRol> getPrivilegeByRols() {
        return privilegeByRols;
    }

    public void setPrivilegeByRols(List<PrivilegeByRol> privilegeByRols) {
        this.privilegeByRols = privilegeByRols;
    }

    public List<ProjectMembershipEntity> getProjectMembershipEntitys() {
        return projectMembershipEntitys;
    }

    public void setProjectMembershipEntitys(List<ProjectMembershipEntity> projectMembershipEntitys) {
        this.projectMembershipEntitys = projectMembershipEntitys;
    }




}
