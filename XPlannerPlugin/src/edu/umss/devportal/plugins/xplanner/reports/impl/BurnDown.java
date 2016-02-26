/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.xplanner.reports.impl;

import edu.umss.devportal.common.ProjectRole;
import edu.umss.devportal.common.reports.AvailableType;
import edu.umss.devportal.common.reports.ChartReport;
import edu.umss.devportal.common.reports.DataStructure;
import edu.umss.devportal.common.reports.ReportDescriptor;
import edu.umss.devportal.common.reports.TypeOfReport;
import edu.umss.devportal.plugins.xplanner.reports.XplannerReport;
import edu.umss.devportal.plugins.xplanner.reports.structure.ReportProject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Grace
 */
public class BurnDown implements XplannerReport{

    private ReportProject reportStructure;
    private ReportDescriptor reportDescriptor;
    private ChartReport chartReport ; 

    private List<String> userIds;

    public BurnDown() {
         userIds = new ArrayList<String>();
         chartReport= new ChartReport();
    }

    @Override
    public void setReportProject(ReportProject reportStructure) {
        this.reportStructure = reportStructure;
    }

    @Override
    public ReportDescriptor getReportDescriptor() {
        if(reportDescriptor== null){
             List<ProjectRole> viewers = new ArrayList<ProjectRole>();
            viewers.add(ProjectRole.TeamMember);

            List<ProjectRole> editors = new ArrayList<ProjectRole>();
            editors.add(ProjectRole.TeamMember);

        reportDescriptor = new ReportDescriptor(
                "Burn Down",
                "Chart report",
                "",
                "XPlanner",
                viewers,
                editors, AvailableType.LINECHART );
        }
        return reportDescriptor;
    }

    @Override
    public DataStructure getDataStructure() {
       return chartReport;
    }

    @Override
    public void setParameters(String projectId, List<String> usrIds) {
        throw new UnsupportedOperationException("Not supported yet.");
//        int idProject = Integer.parseInt(projectId);
//        reportStructure.setProjectId(idProject);
//        this.userIds= usrIds;
    }

    @Override
    public void setUserIds(List<String> userIds) {
        this.userIds = userIds; 
    }

    @Override
    public TypeOfReport getTypeOfReport() {
        return AvailableType.LINECHART ; 
    }

}
