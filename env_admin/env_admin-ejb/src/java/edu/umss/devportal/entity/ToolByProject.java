
package edu.umss.devportal.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@IdClass(ToolByProjectPK.class)
public class ToolByProject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TOOL_ID", insertable = false, updatable = false)
    private int toolId;

    @Id
    @Column(name = "PROJECT_ID", insertable = false, updatable = false)
    private int projectId;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private ProjectEntity project;

    @ManyToOne
    @JoinColumn(name = "TOOL_ID")
    private ToolEntity tool;

    @OneToMany(mappedBy = "toolByProject", fetch = FetchType.EAGER)
    private List<UserAcountTool> userAcountTools;

    @OneToMany(orphanRemoval=true, cascade=CascadeType.ALL)
    private List<ToolProjectParameter> parameters;

    private String toolProjectId;

    private String server;

    private String userName;

    private String password;

    private String port;

    private String name;

    private String typeConection;

     /**
     * Default Constructor
     */
    public ToolByProject() {

        // initialize parameters list.
        parameters = new ArrayList<ToolProjectParameter>();
        userAcountTools = new ArrayList<UserAcountTool>();
    }

    public String getToolProjectId() {
        return toolProjectId;
    }

    public void setToolProjectId(String toolProjectId) {
        this.toolProjectId = toolProjectId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public ToolEntity getTool() {
        return tool;
    }

    public void setTool(ToolEntity tool) {
        this.tool = tool;
    }

    public int getToolId() {
        return toolId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
    }

    public String getTypeConection() {
        return typeConection;
    }

    public void setTypeConection(String typeConection) {
        this.typeConection = typeConection;
    }

    public List<UserAcountTool> getUserAcountTools() {
        return userAcountTools;
    }

    public void setUserAcountTools(List<UserAcountTool> userAcountTools) {
        this.userAcountTools = userAcountTools;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ToolProjectParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<ToolProjectParameter> parameters) {
        this.parameters = parameters;
    }

}
