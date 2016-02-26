/*
 * @(#)CustomReportBackingbean.java 11/04/21
 *
 * Copyright (c) 2011 Universidad Mayor de San Simón
 * Cochabamba - Bolivia
 * All Rights Reserved.
 */
package edu.umss.devportal.reports.backbeans;

import edu.umss.devportal.common.IssueTracker;
import edu.umss.devportal.common.ProjectTracker;
import edu.umss.devportal.common.reports.Report;
import edu.umss.devportal.ejb.session.LoginSessionBean;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

/**
 * Class that Loads and returns all the custom reports
 *
 * @author Edson Alvarez
 */
//@ManagedBean(name = "customReportBackingbean")
@ManagedBean
@Stateless
public class CustomReportBackingbean {

    @EJB
    private LoginSessionBean loginSessionBean;
    private String projectTrackerProjectId;
    private String issueTrackerProjectId;
    private int personId;
    private IssueTracker issueTracker;
    private ProjectTracker projectTracker;
    public List<String> paths = new ArrayList<String>();
    public int customReportsCount;

    public List<String> getPaths() {
        paths.add("/resources/reports/magicNumbersReport.xhtml");
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

    public int getCustomReportsCount() {
        try {
            personId = loginSessionBean.getUser().getId();
            issueTrackerProjectId = loginSessionBean.getIdProjectIssueTracker();
            issueTracker = loginSessionBean.getCurrentIssueTracker();
            List<Report> reports = issueTracker.getCustomReports(
                    issueTrackerProjectId,
                    new ArrayList<String>());

            for (int i = 0; i < reports.size(); i++) {
                if (reports.get(i).getReportDescriptor().getPath() != "") {
                    customReportsCount++;
                    paths.add(reports.get(i).getReportDescriptor().getPath());
                }
            }

        } catch (Exception e) {
        }
        return customReportsCount;
    }

    public void setCustomReportsCount(int customReportsCount) {
        this.customReportsCount = customReportsCount;
    }
}
