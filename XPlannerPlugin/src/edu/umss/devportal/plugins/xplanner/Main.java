/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.xplanner;

import edu.umss.devportal.common.reports.DataStructure;
import edu.umss.devportal.common.reports.DynamicStructure;
import edu.umss.devportal.common.reports.Report;
import edu.umss.devportal.plugins.xplanner.connection.XPlannerWSConnection;
import edu.umss.edu.devportal.exception.NoToolServerConnectionException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.ConfigurationException;

/**
 *
 * @author =)
 */
public class Main {

    public static void main(String args[]) {
       XPlannerWSConnection tool = new  XPlannerWSConnection();
       XplannerTool xplannerTool = new XplannerTool();
       Map<String, String> config = new HashMap<String, String>();
       config.put("serviceURL", "http://localhost:9080/xplanner/");
       config.put("user", "grace");
       config.put("password", "grace");

       List<String> userids = new ArrayList<String>();
       userids.add("211");
        try {
            xplannerTool.applyConfiguration(config);
            // load any available plugin
            //xplannerTool.reloadReportPlugins();

            List<Report> gridReports = xplannerTool.getGridReports("298", userids);
            List<Report> chartReports = xplannerTool.getChartReports("298", userids);
           // List<Report> customReports = xplannerTool.getCustomReports("300", userids);

            System.out.println("Size:" + gridReports.size());
            for (Report report : gridReports) {
                DynamicStructure  ds = (DynamicStructure) report.getDataStructure();
                List<List<String>> dynamicList = ds.getDynamicList();
                System.out.println("Size Interno :" + dynamicList.size());

                for (List<String> list : dynamicList) {
                    for (String value : list) {
                        System.out.print( ". " + value);
                    }
                    System.out.println("");
                }

                System.out.println("---");
            }
            
            for (Report report : chartReports) {
                DataStructure  ds = report.getDataStructure();
                
                System.out.println(""+ds.getClass().getName());
            }

//            for (Report report : customReports) {
//                DataStructure  ds = report.getDataStructure();
//                System.out.println(""+ds.getClass().getName());
//            }

        } catch (ConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoToolServerConnectionException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

    }

}
