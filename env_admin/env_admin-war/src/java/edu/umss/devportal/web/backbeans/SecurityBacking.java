/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.web.backbeans;

import edu.umss.devportal.ejb.UserService;
import edu.umss.devportal.ejb.session.LoginSessionBean;
import edu.umss.devportal.entity.UserEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.SelectEvent;

@ManagedBean(name="authentication")
@RequestScoped
public class SecurityBacking implements Serializable{
    @EJB
    private UserService userService;
    @EJB
    private LoginSessionBean loginSessionBean;

    private String loginName;
    private String password;

    public LoginSessionBean getLoginSessionBean() {
        return loginSessionBean;
    }

    public String login() {
        if (loginName.equals("admin") && password.equals("admin")) {
            return "/views/admin.xhtml?faces-redirect=true";
        }

        List<UserEntity> users = userService.getAllUsers();
        for (UserEntity user: users) {
            if (user.getLogin().equals(loginName) && user.getPasword().equals(password)) {
                loginSessionBean.setUser(user);
                return "/views/start.xhtml?faces-redirect=true";
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid Username or Password"));
        return "/login.xhtml";
    }

    public void setLoginSessionBean(LoginSessionBean loginSessionBean) {
        this.loginSessionBean = loginSessionBean;
    }

    public String logout() {
        String result = "/login?faces-redirect=true";
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException ex) {
            Logger.getLogger(SecurityBacking.class.getName()).
                    log(Level.SEVERE, null, ex);
            result = "/loginError?faces-redirect=true";
        }
        return result;
    }

    public Date getToday() {
        return new Date();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String onMembershipSelectedNavigate(SelectEvent event) {
        loginSessionBean.updateCurrentProjectMembership();
        return "/views/home.xhtml?faces-redirect=true";
    }
}
