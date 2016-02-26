
package edu.umss.devportal.entity;

import edu.umss.devportal.common.ToolPlugin;
import edu.umss.devportal.plugins.ToolManager;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity
public class ToolEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = "Tool_Gen", table = "ID_GEN",
                    pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL",
                    pkColumnValue = "Tool_Gen", initialValue = 10000,
                    allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Tool_Gen")
    private int id;

    private String name;

    private String description;

    private String version;

    private String type;

    @OneToMany(mappedBy = "tool")
    Set<ToolByProject> toolByProjects;

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

    public Set<ToolByProject> getToolByProjects() {
        return toolByProjects;
    }

    public void setToolByProjects(Set<ToolByProject> toolByProjects) {
        this.toolByProjects = toolByProjects;
    }

        public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ToolPlugin getPlugin() {
        return ToolManager.getToolByName(name, version);
    }

}
