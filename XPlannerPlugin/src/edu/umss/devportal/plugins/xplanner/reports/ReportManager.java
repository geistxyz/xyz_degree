/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.xplanner.reports;

import edu.umss.devportal.common.Project;
import edu.umss.devportal.common.reports.AvailableType;
import edu.umss.devportal.common.reports.ChartType;
import edu.umss.devportal.common.reports.Report;
import edu.umss.devportal.common.reports.TypeOfReport;
import edu.umss.devportal.plugins.xplanner.reports.structure.ReportProject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 *
 * @author =)
 */
public class ReportManager {

    private ReportProject xpProject ;
    

    public ReportManager(ReportProject reportPrj) {
        this.xpProject = reportPrj;
    }

    

    public List<Report>  loadPlugins ( List<String> usrIds , TypeOfReport type ) {
     
        List<Report> reportsByType = new ArrayList<Report>();
        ServiceLoader<XplannerReport> loader = ServiceLoader.load(XplannerReport.class);
        Iterator<XplannerReport> reports = loader.iterator();

        while (reports.hasNext()) {
            XplannerReport report = reports.next();

            if(report.getTypeOfReport() == type ){
                report.setReportProject(xpProject);                
                report.setUserIds(usrIds) ; 
                reportsByType.add(report);
            }
            
        }
        return reportsByType ;
    }

    public List<Report> getGridReports(List<String> usrIds) {
         return loadPlugins( usrIds, AvailableType.GRID);
    }

    public List<Report> getChartReports(List<String> usrIds) {
        return loadPlugins(usrIds, ChartType.LINECHART);
    }

    public List<Report> getCustomReports(List<String> usrIds) {
         return loadPlugins(usrIds, AvailableType.CUSTOM);
    }

}