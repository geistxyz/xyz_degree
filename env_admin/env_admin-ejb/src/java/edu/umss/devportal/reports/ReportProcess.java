/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.reports;

import edu.umss.devportal.common.reports.DataStructure;
import edu.umss.devportal.common.Project;
import edu.umss.devportal.plugins.ToolManager;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.ServiceLoader;
import edu.umss.devportal.common.ProjectTracker;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.ConfigurationException;

/**
 *
 * @author July
 */
public class ReportProcess {

    private ProjectTrackerProcess projectProc;

    public ReportProcess() {

        projectProc = new ProjectTrackerProcess();

    }
    public static final String ServiceUrl = "http://localhost:9080/xplanner/soap";
    public static final String User = "sysadmin";
    public static final String Pasword = "admin";
//
//    public static void main(String arg[]) throws ConfigurationException {
//        XplannerTool xplannerTool = new XplannerTool();
//
//

    public List<List<String>> getResult(Properties parameters) {
        // TODO very large process to do something with the query

//        Map<String, String> conf = new HashMap<String, String>();
//        conf.put("User", User);
//        conf.put("Pasword", Pasword);
//        conf.put("ServiceUrl",ServiceUrl);
//
//        ServiceLoader<ProjectTracker> allTools = ToolManager.getToolByType(ProjectTracker.class);
//        Iterator<ProjectTracker> iterator = allTools.iterator();
//
//
//
//        List<List<String>> result = null ;
//        while(iterator.hasNext()){
//        ProjectTracker projectTracker = (ProjectTracker) iterator.next();
//            try {
//                projectTracker.applyConfiguration(conf);
//            } catch (ConfigurationException ex) {
//                Logger.getLogger(ReportProcess.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        result = projectTracker.getReportResult(parameters);
//        }

        List<List<String>> result = projectProc.getResult(parameters);

        if (parameters.getProperty("type") == null ? "MyTasks" == null : parameters.getProperty("type").equals("MyTasks")) {

            result.add(Arrays.asList(new String[]{"July Camacho", "Environments Management", "Iteration 1", "Research", "JEE6", "Read documentation", "10 Hrs", "8 Hrs"}));
            result.add(Arrays.asList(new String[]{"July Camacho", "Environments Management", "Iteration 1", "Research", "JPA", "Read documentation", "2 Hrs", "1 Hrs"}));
            result.add(Arrays.asList(new String[]{"July Camacho", "Environments Management", "Iteration 2", "Research", "JSF", "Read documentation", "3 Hrs", "1 Hrs"}));
            result.add(Arrays.asList(new String[]{"July Camacho", "Environments Management", "Iteration 2", "Research", "JFreeChart", "Read documentation", "6 Hrs", "1 Hrs"}));
            result.add(Arrays.asList(new String[]{"July Camacho", "Environments Management", "Iteration 3", "Research", "PrimeFaces", "Read documentation", "7 Hrs", "1 Hrs"}));
        }

        if (parameters.getProperty("type") == null ? "MyIssues" == null : parameters.getProperty("type").equals("MyIssues")) {

            result.add(Arrays.asList(new String[]{"High", "Breaking the system", "UI crashes on IE9", "Bug", "Open"}));
            result.add(Arrays.asList(new String[]{"Normal", "Annoyance", "Message hardcoded", "Bug", "Closed"}));
            result.add(Arrays.asList(new String[]{"Low", "Improper color of the button", "minor annoyance", "Bug", "Open"}));
            result.add(Arrays.asList(new String[]{"Urgent", "Crashes the OS", "Button restarts the machine", "Bug", "Open"}));
        }

        return result;
    }
}
