/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.xplanner.reports;


import edu.umss.devportal.plugins.xplanner.reports.structure.ReportProject;
import edu.umss.devportal.common.reports.Report;
import edu.umss.devportal.common.reports.TypeOfReport;
import java.util.List;

/**
 *
 * @author =)
 */
public interface XplannerReport extends Report{

    
    public void setUserIds(List<String> userIds) ;
    public TypeOfReport getTypeOfReport ( ); 
    public void setReportProject(ReportProject reportStructure);

}
