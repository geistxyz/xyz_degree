/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.web.utils;

import edu.umss.devportal.common.User;
import edu.umss.devportal.entity.ProjectMembershipEntity;
import edu.umss.devportal.entity.RolEntity;
import edu.umss.devportal.entity.ToolByProject;
import edu.umss.devportal.entity.UserAcountTool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author raul
 */
public class MemberConfigurationHelper {
    private ProjectMembershipEntity membership;

    private List<RolEntity> assignedRoles;

    private Map<ToolByProject, UserAcountTool> selectedMirrorAccounts;

    public Map<ToolByProject, UserAcountTool> getSelectedMirrorAccounts() {
        if (selectedMirrorAccounts == null) {
            selectedMirrorAccounts = new HashMap<ToolByProject, UserAcountTool>();
        }
        return selectedMirrorAccounts;
    }

    public void setSelectedMirrorAccounts(Map<ToolByProject, UserAcountTool> toolAccounts) {
        this.selectedMirrorAccounts = toolAccounts;
    }

    public ProjectMembershipEntity getMembership() {
        return membership;
    }

    public void setMembership(ProjectMembershipEntity membership) {
        this.membership = membership;
    }

    public List<RolEntity> getAssignedRoles() {
        if (assignedRoles == null)
            assignedRoles = new ArrayList<RolEntity>();
        return assignedRoles;
    }

    public void setAssignedRoles(List<RolEntity> assignedRoles) {
        this.assignedRoles = assignedRoles;
    }

    public void saveMirrorAccount(ToolByProject toolByProject, User selectedMirrorAccount) {
        
        System.out.println("Tool name:" + toolByProject.getName());
        System.out.println("user size:" +toolByProject.getUserAcountTools().size());
        UserAcountTool userAcountTool = new UserAcountTool();
        userAcountTool.setUserEntity(membership.getUserEntity());
        userAcountTool.setUserToolId(selectedMirrorAccount.getId());
        userAcountTool.setToolByProject(toolByProject);
        getSelectedMirrorAccounts().put(toolByProject, userAcountTool);

    }

    private String assignedRoleName;

    public String getAssignedRoleName() {
        if (assignedRoleName == null)
            return "none";
        return assignedRoleName;
    }

    public void setAssignedRoleName(String assignedRoleName) {
        this.assignedRoleName = assignedRoleName;
    }
}
