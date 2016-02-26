/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.web.backbeans.project;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

/**
 *
 * @author raul
 */
@ManagedBean
@SessionScoped
public class DashboardBean implements Serializable{

    /** Creates a new instance of DashboardBean */
    public DashboardBean() {
        model = new DefaultDashboardModel();

        DashboardColumn firstCol = new DefaultDashboardColumn();
        DashboardColumn secondCol = new DefaultDashboardColumn();

        firstCol.addWidget("top-left");
        firstCol.addWidget("bottom-left");

        secondCol.addWidget("top-right");
        secondCol.addWidget("bottom-right");

        model.addColumn(firstCol);
        model.addColumn(secondCol);
    }

    private DashboardModel model;

    public DashboardModel getModel() {
        return model;
    }

    public void setModel(DashboardModel model) {
        this.model = model;
    }




}
