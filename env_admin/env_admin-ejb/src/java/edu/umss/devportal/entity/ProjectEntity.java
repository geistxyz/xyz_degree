
package edu.umss.devportal.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity
public class ProjectEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = "Project_Gen", table = "ID_GEN",
                    pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL",
                    pkColumnValue = "Project_Gen", initialValue = 10000,
                    allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Project_Gen")
    private int id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private List<ToolByProject> toolByProjects;

    @OneToMany(mappedBy = "projectEntity", fetch = FetchType.EAGER)
    private List<ProjectMembershipEntity> memberAccounts;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ToolByProject> getToolByProjects() {
        return toolByProjects;
    }

    public void setToolByProjects(List<ToolByProject> toolByProjects) {
        this.toolByProjects = toolByProjects;

        for(ToolByProject item : toolByProjects)
        {
            associateToolByProject(item);
        }
    }

    private void associateToolByProject(ToolByProject toolByProject) {
        toolByProject.setProject(this);
    }

    public List<ProjectMembershipEntity> getMemberAccounts() {
        if (memberAccounts == null)
            memberAccounts = new ArrayList<ProjectMembershipEntity>();
        return memberAccounts;
    }

    public void setMemberAccounts(List<ProjectMembershipEntity> memberAccounts) {
        this.memberAccounts = memberAccounts;
    }

}
