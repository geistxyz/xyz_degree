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
public class PrivilegeEntity implements Serializable{
    
    private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator(name = "Privilege_Gen", 
			        table = "ID_GEN", 
			        pkColumnName = "GEN_NAME", 
			        valueColumnName = "GEN_VAL", 
			        pkColumnValue = "Privilege_Gen", 
			        initialValue = 10000, 
			        allocationSize = 100)	
	@GeneratedValue(strategy=GenerationType.TABLE, generator = "Privilege_Gen")

    private int id;
    
    private String page;

    @OneToMany(mappedBy= "privilegeEntity", fetch = FetchType.EAGER)
	private List<PrivilegeByRol> privilegeByRols;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<PrivilegeByRol> getPrivilegeByRols() {
        return privilegeByRols;
    }

    public void setPrivilegeByRols(List<PrivilegeByRol> privilegeByRols) {
        this.privilegeByRols = privilegeByRols;
    }



}
