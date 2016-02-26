/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.ejb;

import java.util.List;
import edu.umss.devportal.reports.ReportProcess;
import java.util.Properties;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Grace
 */
@Stateless
@LocalBean
public class ReportsService implements ReportsServiceLocal, ReportsServiceRemote {

    ReportProcess reportProcess;

    public ReportsService() {
        reportProcess = new ReportProcess();
    }

    public List<List<String>> getResult(Properties query) {
        //TODO Here there are a process of query evaluation to send  the
        // request to appropiated Collector

        return reportProcess.getResult(query);
    }
}
