/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.common.reports;

import edu.umss.devportal.common.reports.DataStructure;
import edu.umss.devportal.common.reports.Report;
import edu.umss.devportal.common.reports.ReportDescriptor;
import java.util.List;

/**
 *
 * @author Edson
 */
public class ReportImpl implements Report{

    private ReportDescriptor reportDescriptor;
    private DataStructure dataStructure;

    public ReportImpl(ReportDescriptor reportDescriptor, DataStructure dataStructure) {
        this.reportDescriptor = reportDescriptor;
        this.dataStructure = dataStructure;
    }

    public ReportImpl() {
    }

    public void setDataStructure(DataStructure dataStructure) {
        this.dataStructure = dataStructure;
    }

    public void setReportDescriptor(ReportDescriptor reportDescriptor) {
        this.reportDescriptor = reportDescriptor;
    }

    @Override
    public ReportDescriptor getReportDescriptor() {
        return reportDescriptor;
    }

    @Override
    public DataStructure getDataStructure() {
        return dataStructure;
    }

    public void setParameters(String projectId, List<String> usrIds) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
