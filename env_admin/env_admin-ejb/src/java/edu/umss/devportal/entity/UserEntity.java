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

@Entity
public class UserEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "User_Gen",
    table = "ID_GEN",
    pkColumnName = "GEN_NAME",
    valueColumnName = "GEN_VAL",
    pkColumnValue = "User_Gen",
    initialValue = 10000,
    allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "User_Gen")
    private int id;
    private String name;
    private String login;
    private String pasword;
    @OneToMany(mappedBy = "userEntity", fetch = FetchType.EAGER)
    private List<UserAcountTool> userAcountTools;
    @OneToMany(mappedBy = "userEntity", fetch = FetchType.EAGER)
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public List<ProjectMembershipEntity> getProjectMembershipEntitys() {
        return projectMembershipEntitys;
    }

    public void setProjectMembershipEntitys(List<ProjectMembershipEntity> projectMembershipEntitys) {
        this.projectMembershipEntitys = projectMembershipEntitys;
    }

    public List<UserAcountTool> getUserAcountTools() {
        return userAcountTools;
    }

    public void setUserAcountTools(List<UserAcountTool> userAcountTools) {
        this.userAcountTools = userAcountTools;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserEntity other = (UserEntity) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        return hash;
    }
}
