/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.common.reports;

import java.util.List;

/**
 *
 * @author Edson
 */
public interface Report {
    public ReportDescriptor getReportDescriptor();
    public DataStructure getDataStructure();
    public void setParameters(String projectId,List<String> usrIds);
}
