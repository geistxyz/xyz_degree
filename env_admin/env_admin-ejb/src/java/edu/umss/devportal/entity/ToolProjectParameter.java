/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Raul Lopez
 * @version 1.0
 */
@Entity
public class ToolProjectParameter implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    private String name;
    
    private String assignedValue;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssignedValue() {
        return assignedValue;
    }

    public void setAssignedValue(String assignedValue) {
        this.assignedValue = assignedValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ToolProjectParameter other = (ToolProjectParameter) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.assignedValue == null) ? (other.assignedValue != null) : !this.assignedValue.equals(other.assignedValue)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 43 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 43 * hash + (this.assignedValue != null ? this.assignedValue.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "edu.umss.devportal.entity.ToolProjectParameter[id=" + id + "]";
    }

}
