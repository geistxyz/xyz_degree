/*
 * @(#)DynamicGridBean.java 11/04/21
 *
 * Copyright (c) 2011 Universidad Mayor de San Simón
 * Cochabamba - Bolivia
 * All Rights Reserved.
 */
package edu.umss.devportal.reports.backbeans;

import edu.umss.devportal.reports.utilities.CellContent;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 * Abstract class that Generates a table dynamically from a dynamic list
 * @author Edson
 */
@ManagedBean
@RequestScoped
public abstract class DynamicGridBean extends ReportBackingBean {

    /**
     * Reports controller variable
     */
    @ManagedProperty("#{reportsController}")
    protected ReportsController reportsController;

    /**
     * Constructor with no parameters
     */
    public DynamicGridBean() {
    }

    /**
     * Gets the reports controller
     * @return reports controller managed property
     */
    public ReportsController getReportsController() {
        return reportsController;
    }

    /**
     * Sets the reports controller
     * @param reportsController
     */
    public void setReportsController(ReportsController reportsController) {
        this.reportsController = reportsController;
    }

    /**
     * List of columns
     * @return columns
     */
    public abstract List<String> getColumns();

    /**
     * List that represents the table
     * @return table
     */
    public abstract List<CellContent[]> getData();

    /**
     * Calculates the row data
     * @param dynamicList
     * @return array with the cell content
     */
    protected CellContent[] calculateRowData(List<String> dynamicList) {
        CellContent[] rowData = new CellContent[dynamicList.size() + 2];
        for (int index = 0; index < dynamicList.size(); index++) {
            rowData[index] = new CellContent(dynamicList.get(index).toString());
        }
        return rowData;
    }
}
