/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.teamtrack.reports;

import edu.umss.devportal.common.reports.AvailableType;
import edu.umss.devportal.common.reports.MagicNumbers;
import edu.umss.devportal.common.reports.Report;
import edu.umss.edu.devportal.exception.NoToolServerConnectionException;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Iterator;

/**
 *
 * @author July Camacho
 */
public class ReportManager {
    private MagicNumbers magicNumbers;
    private List<Report> gridReports ;
    private List<Report> chartReports ;
    private List<Report> customReports ;

    /**
     *
     */
    public ReportManager(){
     magicNumbers = new MagicNumbersImpl();
     gridReports = new ArrayList<Report>();
     chartReports = new ArrayList<Report>();
     customReports = new ArrayList<Report>();
    }
    /**
     *
     * @param list
     * @param type
     * @return
     */
    public boolean loadReports (List<Report> list, AvailableType type){

        if(list.size() > 0)
            list.clear();
        ServiceLoader<Report> loader = ServiceLoader.load(Report.class);
        Iterator<Report> reports = loader.iterator();
        while (reports.hasNext()) {
            Report report = reports.next();
            //if(report.getReportDescriptor().getType().getClass().equals(type.getClass())){
            if(report.getReportDescriptor().getType().equals(type)){
                list.add(report);
            }
        }
        return true ;
    }
    /**
     *
     * @param projectId
     * @param usrIds
     * @return
     * @throws NoToolServerConnectionException
     */
    public List<Report> getGridReports(String projectId, List<String> usrIds) throws NoToolServerConnectionException {
        loadReports(gridReports, AvailableType.GRID);
        for(Report report : gridReports){
            report.setParameters(projectId, usrIds);
        }
       return gridReports;
    }
    /**
     *
     * @param projectId
     * @param usrIds
     * @return
     * @throws NoToolServerConnectionException
     */
    public List<Report> getChartReports(String projectId, List<String> usrIds) throws NoToolServerConnectionException {
        loadReports(chartReports, AvailableType.LINECHART);
        for(Report report : chartReports)
            report.setParameters(projectId, usrIds);
        return chartReports;
    }
    /**
     *
     * @param projectId
     * @param usrIds
     * @return
     * @throws NoToolServerConnectionException
     */
    public List<Report> getCustomReports(String projectId, List<String> usrIds) throws NoToolServerConnectionException {
        loadReports(customReports, AvailableType.CUSTOM);
        for(Report report : customReports)
            report.setParameters(projectId, usrIds);
        return customReports;
    }
    /**
     *
     * @return
     */
    public MagicNumbers getMagicNumbers() {
        return magicNumbers;
    }

}
