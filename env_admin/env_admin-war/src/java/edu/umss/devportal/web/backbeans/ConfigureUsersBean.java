/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.web.backbeans;

import edu.umss.devportal.common.User;
import edu.umss.devportal.ejb.RolService;
import edu.umss.devportal.ejb.UserService;
import edu.umss.devportal.entity.ProjectMembershipEntity;
import edu.umss.devportal.entity.RolEntity;
import edu.umss.devportal.entity.ToolByProject;
import edu.umss.devportal.entity.ToolProjectParameter;
import edu.umss.devportal.entity.UserEntity;
import edu.umss.devportal.web.utils.MemberConfigurationHelper;
import edu.umss.edu.devportal.exception.MissingParameterException;
import edu.umss.edu.devportal.exception.NoToolServerConnectionException;
import edu.umss.edu.devportal.exception.ServerNotFoundException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author raul
 */
@ManagedBean(name = "configureUsers")
@ViewScoped
public class ConfigureUsersBean implements Serializable, PropertyChangeListener {

    @EJB
    private RolService rolService;
    @EJB
    private UserService userService;
    private List<UserEntity> availableUsers;
    private List<UserEntity> selectedUsers;
    private UserEntity[] usersToAdd;
    private List<ProjectMembershipEntity> memberAccounts;
    private ProjectMembershipEntity selectedMember;
    private List<MemberConfigurationHelper> memberConfigurationDetails;
    private MemberConfigurationHelper selectedMembershipConfigHelper;
    private List<RolEntity> availableRoles;
    private String memberAccountId;
    private MemberConfigurationHelper accountToConfigure;
    @ManagedProperty("#{createUserBean}")
    private CreateUserController createUserBean;
    @ManagedProperty("#{configureTools}")
    private ConfigureToolsBean configureTools;

    /** Creates a new instance of ConfigureToolsBean */
    public ConfigureUsersBean() {
        memberConfigurationDetails = new ArrayList<MemberConfigurationHelper>();
    }

    /**
     * @return the users
     */
    public CreateUserController getCreateUserBean() {
        return createUserBean;
    }

    public void setCreateUserBean(CreateUserController createUserBean) {
        this.createUserBean = createUserBean;
        createUserBean.addPropertyChangeListener(this);
    }

    public List<UserEntity> getSelectedUsers() {
        if (selectedUsers == null) {
            selectedUsers = new ArrayList<UserEntity>();
        }
        return selectedUsers;
    }

    public void setSelectedUsers(List<UserEntity> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    public List<UserEntity> getAvailableUsers() {
        if (availableUsers == null) {
            availableUsers = userService.getAllUsers();
        }
        availableUsers.removeAll(getSelectedUsers());

        return availableUsers;
    }

    public void setAvailableUsers(List<UserEntity> availableUsers) {
        this.availableUsers = availableUsers;
    }

    public void addSelectedMembers() {
        System.out.println("Adding selected members: " + getUsersToAdd().length);
        UserEntity currentUser;
        ProjectMembershipEntity newMember;
        String memberRoleName = "Team Member";
        for (int index = 0; index < getUsersToAdd().length; index++) {
            currentUser = getUsersToAdd()[index];
            getSelectedUsers().add(currentUser);
            newMember = new ProjectMembershipEntity();
            newMember.setUserEntity(currentUser);
            getMemberAccounts().add(newMember);
            MemberConfigurationHelper helper = new MemberConfigurationHelper();
            helper.setMembership(newMember);
            helper.setAssignedRoleName(memberRoleName);
            getMemberConfigurationDetails().add(helper);
        }
        setUsersToAdd(null);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("new user")) {
            // refresh available users on new user created event
            setAvailableUsers(null);
        }
    }

    public UserEntity[] getUsersToAdd() {
        return usersToAdd;
    }

    public void setUsersToAdd(UserEntity[] usersToAdd) {
        this.usersToAdd = usersToAdd;
    }

    public ConfigureToolsBean getConfigureTools() {
        return configureTools;
    }

    public void setConfigureTools(ConfigureToolsBean configureTools) {
        this.configureTools = configureTools;
    }

    public List<ProjectMembershipEntity> getMemberAccounts() {
        if (memberAccounts == null) {
            memberAccounts = new ArrayList<ProjectMembershipEntity>();
        }

        return memberAccounts;
    }

    public void setMemberAccounts(List<ProjectMembershipEntity> memberAccounts) {
        this.memberAccounts = memberAccounts;
    }

    public ProjectMembershipEntity getSelectedMember() {
        return selectedMember;
    }

    public void setSelectedMember(ProjectMembershipEntity selectedMember) {
        this.selectedMember = selectedMember;
    }

    public List<MemberConfigurationHelper> getMemberConfigurationDetails() {
        return memberConfigurationDetails;
    }

    public void setMemberConfigurationDetails(List<MemberConfigurationHelper> memberConfigurationDetails) {
        this.memberConfigurationDetails = memberConfigurationDetails;
    }

    public MemberConfigurationHelper getSelectedMembershipConfigHelper() {
        return selectedMembershipConfigHelper;
    }

    public void setSelectedMembershipConfigHelper(MemberConfigurationHelper selectedMembershipConfigHelper) {
        this.selectedMembershipConfigHelper = selectedMembershipConfigHelper;
    }

    public List<RolEntity> getAvailableRoles() {
        if (availableRoles == null) {
            availableRoles = rolService.getRolsApp();
        }
        return availableRoles;
    }

    public MemberConfigurationHelper getAccountToConfigure() {
        return accountToConfigure;
    }

    public void setAccountToConfigure(MemberConfigurationHelper accountToConfigure) {
        this.accountToConfigure = accountToConfigure;
    }
    private ToolByProject toolAccountProvider;

    public ToolByProject getToolAccountProvider() {
        return toolAccountProvider;
    }

    public void setToolAccountProvider(ToolByProject toolAccountProvider) {
        System.out.println("new provider " + toolAccountProvider);
        this.toolAccountProvider = toolAccountProvider;
    }
    private boolean neededNewAccountOnTool = true;

    public boolean isNeededNewAccountOnTool() {
        return neededNewAccountOnTool;
    }

    public void setNeededNewAccountOnTool(boolean neededNewAccountOnTool) {
        this.neededNewAccountOnTool = neededNewAccountOnTool;
    }
    private List<User> availableUserAccountsFromCurrentTool;

    public List<User> getAvailableUserAccountsFromCurrentTool() {
        return availableUserAccountsFromCurrentTool;
    }

    public void setAvailableUserAccountsFromCurrentTool(List<User> availableUserAccountsFromCurrentTool) {
        this.availableUserAccountsFromCurrentTool = availableUserAccountsFromCurrentTool;
    }

    public String getMemberAccountId() {
        return memberAccountId;
    }

    public void setMemberAccountId(String memberAccountId) {
        this.memberAccountId = memberAccountId;
    }

    public void calculateAvailableUserAccountsForCurrentTool() {
        int MAX_ATTEMPTS = 5;
        int count = 0;
        boolean userAccountsLoaded = true;

        try {
            availableUserAccountsFromCurrentTool = toolAccountProvider.getTool().getPlugin().getUserList();

            while (availableUserAccountsFromCurrentTool.isEmpty()) {
                if (MAX_ATTEMPTS >= count) {
                    reconect();
                    availableUserAccountsFromCurrentTool = toolAccountProvider.getTool().getPlugin().getUserList();
                    count++;
                } else {
                    userAccountsLoaded = false;
                    break;
                }
            }

        } catch (NoToolServerConnectionException ex) {
            if (userAccountsLoaded == false) {
                availableUserAccountsFromCurrentTool.clear();
            }
            Logger.getLogger(ConfigureUsersBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onAccountProviderSelect(SelectEvent evt) {
        calculateAvailableUserAccountsForCurrentTool();
    }
    private User selectedMirrorAccount;

    public User getSelectedMirrorAccount() {
        return selectedMirrorAccount;
    }

    public void setSelectedMirrorAccount(User selectedMirrorAccount) {
        this.selectedMirrorAccount = selectedMirrorAccount;
    }

    public void selectMirrorAccount() {
        selectedMirrorAccount.setId(memberAccountId);
        accountToConfigure.saveMirrorAccount(toolAccountProvider,
                selectedMirrorAccount);
    }

    public List<String> getAvailableRolesLabels() {
        List<String> labels = new ArrayList<String>();
        for (RolEntity role : getAvailableRoles()) {
            labels.add(role.getName());
        }
        return labels;
    }

    public void onAccountToConfigureUnselected(UnselectEvent evt) {
        setAccountToConfigure(null);
    }

    void reset() {
        setMemberAccounts(new ArrayList<ProjectMembershipEntity>());
        setMemberConfigurationDetails(new ArrayList<MemberConfigurationHelper>());
        setSelectedUsers(new ArrayList<UserEntity>());
    }

    // TODO: Retry connection
    void reconect() {

        String user = "";
        String password = "";
        String serviceUrl = "";

        for (ToolProjectParameter parameter : toolAccountProvider.getParameters()) {
            if (parameter.getName().equals("user")) {
                user = parameter.getAssignedValue();
            }
            if (parameter.getName().equals("password")) {
                password = parameter.getAssignedValue();
            }
            if (parameter.getName().equals("serviceURL")) {
                serviceUrl = parameter.getAssignedValue();
            }
        }

        Map<String, String> config = new HashMap<String, String>();
//        config.put("serviceURL", "http://192.168.1.8:9080/xplanner/");
//        config.put("user", "sysadmin");
//        config.put("password", "admin");
        config.put("serviceURL", serviceUrl);
        config.put("user", user);
        config.put("password", password);

        try {
            toolAccountProvider.getTool().getPlugin().testConnection(config);
        } catch (MissingParameterException ex) {
            Logger.getLogger(ConfigureUsersBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServerNotFoundException ex) {
            Logger.getLogger(ConfigureUsersBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
