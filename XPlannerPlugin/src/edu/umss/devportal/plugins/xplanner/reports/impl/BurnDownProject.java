/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.xplanner.reports.impl;

import edu.umss.devportal.common.ProjectRole;
import edu.umss.devportal.common.reports.AvailableType;
import edu.umss.devportal.common.reports.ChartReport;
import edu.umss.devportal.common.reports.ChartType;
import edu.umss.devportal.common.reports.DataStructure;
import edu.umss.devportal.common.reports.ReportDescriptor;
import edu.umss.devportal.common.reports.TypeOfReport;
import edu.umss.devportal.plugins.xplanner.reports.structure.Iteration;
import edu.umss.devportal.plugins.xplanner.reports.structure.ReportProject;
import edu.umss.devportal.plugins.xplanner.reports.structure.UserStory;
import edu.umss.devportal.plugins.xplanner.reports.utils.DateUtility;
import edu.umss.devportal.common.reports.LineDataStructure;
import edu.umss.devportal.plugins.xplanner.reports.XplannerReport;
import edu.umss.devportal.plugins.xplanner.reports.structure.Task;
import edu.umss.devportal.plugins.xplanner.reports.structure.TimeEntry;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author =)
 */
public class BurnDownProject  implements XplannerReport {

    
    List<String> userIds ;
    private Calendar initIteration;
    private Calendar endIteration;
  //  private Calendar today ;

    private int hoursPerDay ;

    LinkedList<Date> dates;

    Map<Date, Double> burnHours;
    Map<Date, Double> idealLine;
    Map<Date, Double> workload;

    private double adjusted;
    double original  ;
    private ReportProject reportStructure;
    private ReportDescriptor reportDescriptor ;
    private ChartReport chartReport ; 

    private List<LineDataStructure> burnDown ;
    private static final Logger logger = Logger.getLogger(AssignedTasks.class.getName());

    public BurnDownProject( ) {

        burnHours = new LinkedHashMap();
        idealLine = new LinkedHashMap();
        workload = new LinkedHashMap();

        burnDown = new ArrayList<LineDataStructure>();
        userIds = new ArrayList<String>() ;

        chartReport = new ChartReport();
        adjusted = 0 ;
        original = 0 ; 
        hoursPerDay  = 4;
    }

    private void prepareData() {

            Iteration iterationData = reportStructure.getCurrentIteration();
            List<UserStory> userStories = iterationData.getUserStories();

            initIteration = iterationData.getStartDate();
            endIteration = iterationData.getEndDate();
                dates = DateUtility.searchBetweenDates(initIteration.getTime(), endIteration.getTime());
                adjusted = iterationData.getAdjustedEstimatedHours();
                
                fillDatesOfIteration();

                
                for (UserStory userStory : userStories) {
                    original += userStory.getEstimatedOriginalHours(); 
                    List<Task> taskDatas = userStory.getTasks() ;
                    for (Task taskData : taskDatas) {

                        if ( taskData.getEstimatedOriginalHours() == 0) {
                            double estimatedHours = taskData.getEstimatedHours();
                            Calendar createdDate = taskData.getLastUpdateTime();
                            Date date = getDate(createdDate);
                            Double hours = workload.get(date);
                            hours += estimatedHours;

                            workload.put(date, hours) ;
                        }

                        List<TimeEntry> timeEntries = taskData.getTimeEntries() ;
                        getEntrySumatory(timeEntries);
                    }
                }
    }

    public void getBurnDownProject() {

        prepareData();
        getBurnDownPoints();
        logger.log(Level.INFO, " Burn Down Points {0} ", burnDown.size());
        getEstimatedEndPoints();
        logger.log(Level.INFO, " Estimated Points {0} ", burnDown.size());
        getWorkLoadPoints();
        logger.log(Level.INFO, " WorkLoad Points {0} ", burnDown.size());
        
    }

    private void fillDatesOfIteration() {
        double ideal = adjusted;
        
        for (Object date : dates) {
            Date dayOfIteration = (Date) date;
            workload.put(dayOfIteration, adjusted);
            idealLine.put(dayOfIteration, ideal);
            burnHours.put(dayOfIteration, ideal);

            ideal -= hoursPerDay;
        
        }
    }

    public void getEntrySumatory(List<TimeEntry> entries) {
        for (TimeEntry timeEntryData : entries) {
            Calendar reportDate = timeEntryData.getReportDate();
            double timeDuration = 0;
            if (reportDate.after(initIteration) && reportDate.before(endIteration)) {
                timeDuration += timeEntryData.getDuration();
                Date dateOfIteration = getDate(reportDate);
                Double hours = burnHours.get(dateOfIteration);
                hours -= timeDuration;
                burnHours.put(dateOfIteration, hours);
            }
        }
    }

    public Date getDate(Calendar reportDate) {
        for (Object date : dates) {
            Date dayOfIteration = (Date) date;
            Calendar dia = Calendar.getInstance();
            dia.setTime(dayOfIteration);
            if (DateUtility.isSameDay(dia, reportDate)) {
                return dayOfIteration;
            }
        }
        return null;
    }

    private void getBurnDownPoints() {
      Set<Date> keySet = burnHours.keySet();
      double previous = adjusted;

        for (Iterator<Date> it = keySet.iterator(); it.hasNext();) {
            Date date = it.next();
            Double value = burnHours.get(date);
            Double ideal = idealLine.get(date);
            
            if (value == ideal) {
                value = previous ;
            }else {
                previous = value ; 
            }

            String dateString = new java.text.SimpleDateFormat("dd/M").format(date);
           // logger.log(Level.INFO, " VAlue "+value + " - IDEAL - " + ideal + " - Previous - " + previous  );

            LineDataStructure line = new LineDataStructure(value.intValue(), "Burned Hours", dateString);
            burnDown.add(line);
        }
    }


    private void getWorkLoadPoints(){

        Set<Date> keySet = workload.keySet();

        for (Iterator<Date> it = keySet.iterator(); it.hasNext();) {
            Date date = it.next();
            Double value = workload.get(date);
            String dateString = new java.text.SimpleDateFormat("dd/M").format(date);
            LineDataStructure line = new LineDataStructure(value.intValue(), "WorkLoad", dateString);
            burnDown.add(line);

        }

    }

    private void getEstimatedEndPoints(){

        Set<Date> keySet = idealLine.keySet();

        for (Iterator<Date> it = keySet.iterator(); it.hasNext();) {
            Date date = it.next();
            Double value = idealLine.get(date);
            String dateString = new java.text.SimpleDateFormat("dd/M").format(date);
            LineDataStructure line = new LineDataStructure(value.intValue(), "Ideal", dateString);
            burnDown.add(line);

        }
    }

    @Override
    public void setReportProject(ReportProject reportStructure) {
        this.reportStructure = reportStructure ;
    }

    @Override
    public ReportDescriptor getReportDescriptor() {
        if( reportDescriptor == null ){
             List<ProjectRole> viewers = new ArrayList<ProjectRole>();
            viewers.add(ProjectRole.TeamMember);

            List<ProjectRole> editors = new ArrayList<ProjectRole>();
            editors.add(ProjectRole.TeamMember);

            reportDescriptor = new ReportDescriptor("Burn Down Project",
                    "Burn Down of project", null, "XPlanner", viewers, editors,
                    ChartType.LINECHART) ;
        }
        return reportDescriptor ;
    }

    @Override
    public DataStructure getDataStructure() {
        getBurnDownProject();
        chartReport.setLineDataStructureList(burnDown);
        return chartReport ;

    }

    @Override
    public void setParameters(String projectId, List<String> usrIds) {
//        this.projectId = Integer.parseInt(projectId);
//        reportStructure.setProjectId(this.projectId);
//
//        // si userIds == empty => Burn Down del proyecot
//        // si no entonces del usuario
//        this.userIds= usrIds;
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setUserIds(List<String> userIds) {
      this.userIds = userIds ; 
    }

    @Override
    public TypeOfReport getTypeOfReport() {
        return ChartType.LINECHART ;
    }
}
