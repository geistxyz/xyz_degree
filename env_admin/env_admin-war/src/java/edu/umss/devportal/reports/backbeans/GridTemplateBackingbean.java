/*
 * @(#)GridTemplateBackingbean.java 11/04/21
 *
 * Copyright (c) 2011 Universidad Mayor de San Simón
 * Cochabamba - Bolivia
 * All Rights Reserved.
 */
package edu.umss.devportal.reports.backbeans;

import edu.umss.devportal.reports.utilities.CellContent;
import edu.umss.devportal.common.reports.DynamicStructure;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edson
 * @see DynamicGridBean
 */
public class GridTemplateBackingbean extends DynamicGridBean {

    private DynamicStructure dynamicStructure;
    private String reportName;

    /**
     * Default Constructor
     */
    public GridTemplateBackingbean() {
    }

    /**
     * Gets the report name
     * @return String that represents the report name
     */
    public String getReportName() {
        return reportName;
    }

    /**
     * Sets the Report name
     * @param reportName
     */
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    /**
     * Returns the columns of the grid
     * @see DynamicGridBean.getColumns()
     * @return a list of strings
     */
    @Override
    public List<String> getColumns() {
        List<String> headers = dynamicStructure.getHeadersList();
        return headers;
    }

    /**
     * Returns the data of the grid
     * @see DynamicGridBean.getData()
     * @return a list of CellContent array
     */
    @Override
    public List<CellContent[]> getData() {
        List<CellContent[]> data = new ArrayList<CellContent[]>();
        List<List<String>> dynamicList = dynamicStructure.getDynamicList();

        for (int i = 0; i < dynamicList.size(); i++) {
            List<String> row = dynamicList.get(i);
            data.add(calculateRowData(row));
        }

        return data;
    }

    /**
     * Sets the dynamic Structure
     * @param dynamicStructure
     */
    public void setDynamicStructure(DynamicStructure dynamicStructure) {
        this.dynamicStructure = dynamicStructure;
    }
}
