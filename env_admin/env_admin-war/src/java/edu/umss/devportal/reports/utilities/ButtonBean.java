/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.reports.utilities;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "deleteReport")
@SessionScoped
public class ButtonBean {

    public void message(ActionEvent actionEvent) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "The report has been deleted", "done.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
