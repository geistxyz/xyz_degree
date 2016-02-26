/*
 * @(#)ChartTemplate.java 11/04/21
 *
 * Copyright (c) 2011 Universidad Mayor de San Simón
 * Cochabamba - Bolivia
 * All Rights Reserved.
 */

package edu.umss.devportal.reports.backbeans;

import java.util.List;
import edu.umss.devportal.common.reports.ChartType;
import edu.umss.devportal.common.reports.LineDataStructure;
import edu.umss.devportal.reports.utilities.ChartDisplayer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.general.PieDataset;
import org.primefaces.model.DefaultStreamedContent;

/**
 * Class that converts and sets a General Chart into a Specific Chart
 *
 * @author Edson Alvarez
 */
public class ChartTemplate {

    private int id;
    private String reportName;
    private ChartType type;
    private List<LineDataStructure> lineDataStructureList;
    private PieDataset pieDataset;
    private DefaultCategoryDataset defaultCategoryDataset;
    private CategoryDataset categoryDataset;
    private IntervalCategoryDataset intervalCategoryDataset;

    /*
     * Default constructor
     */
    public ChartTemplate() {
    }

    /**
     * Gets the id of the Chart
     * @return Integer that represents the ID of the Chart
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the if og the Chart
     * @param id represents the ID of the Chart
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the type of the Chart
     * @return ChartType that represents an available type of Chart
     */
    public ChartType getType() {
        return type;
    }

    /**
     * Sets the type of the Chart
     * @param type of the Chart
     */
    public void setType(ChartType type) {
        this.type = type;
    }

    /**
     * Gets the report Chart Name
     * @return String that represents the Report Name
     */
    public String getReportName() {
        return reportName;
    }

    /**
     * Sets the report Chart Name
     * @param String that represents the Report name
     */
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    /**
     * Gets the Line Data Structure list
     * @return List of line data structures
     */
    public List<LineDataStructure> getLineDataStructureList() {
        return lineDataStructureList;
    }

    /**
     * Sets the Line Data Structure list
     * @param List that represents a line data structure
     */
    public void setLineDataStructureList(
            List<LineDataStructure> lineDataStructureList) {
        this.lineDataStructureList = lineDataStructureList;
    }

    /**
     * Gets the Pie Data set
     * @return PieDataset that represents a pie chart
     */
    public PieDataset getPieDataset() {
        return pieDataset;
    }

    /**
     * Sets the Pie Data set
     * @param pieDataset
     */
    public void setPieDataset(PieDataset pieDataset) {
        this.pieDataset = pieDataset;
    }

    /**
     * Gets the Category Data Set
     * @return CategoryDataset
     */
    public CategoryDataset getCategoryDataset() {
        return categoryDataset;
    }

    /**
     * Sets the Category Data Set
     * @param categoryDataset
     */
    public void setCategoryDataset(CategoryDataset categoryDataset) {
        this.categoryDataset = categoryDataset;
    }

    /**
     * Gets the Default Category Data Set
     * @return DefaultCategoryDataset
     */
    public DefaultCategoryDataset getDefaultCategoryDataset() {
        return defaultCategoryDataset;
    }

    /**
     * Sets the Default Category Data Set
     * @param defaultCategoryDataset
     */
    public void setDefaultCategoryDataset(
            DefaultCategoryDataset defaultCategoryDataset) {
        this.defaultCategoryDataset = defaultCategoryDataset;
    }

    /**
     * Gets the Interval Category Data Set
     * @return IntervalCategoryDataset
     */
    public IntervalCategoryDataset getIntervalCategoryDataset() {
        return intervalCategoryDataset;
    }

    /**
     * Sets the Interval Category Data Set
     * @param intervalCategoryDataset
     */
    public void setIntervalCategoryDataset(
            IntervalCategoryDataset intervalCategoryDataset) {
        this.intervalCategoryDataset = intervalCategoryDataset;
    }

//    /**
//     * Returns as Line Chart
//     * @return a DefaultStreamedContent
//     */
//    public DefaultStreamedContent getAsLineChart() {
//        ChartDisplayer chartDisplayer = new ChartDisplayer();
//        return chartDisplayer.getLineChartImage(
//                lineDataStructureList,
//                getReportName(),
//                "Date",
//                "Hours",
//                900,
//                600);
//    }
//
//    /**
//     * Returns as Pie Chart
//     * @return a DefaultStreamedContent
//     */
//    public DefaultStreamedContent getAsPieChart() {
//        ChartDisplayer chartDisplayer = new ChartDisplayer();
//        return chartDisplayer.getPieChartImage(
//                pieDataset,
//                reportName,
//                900,
//                600);
//    }
//
//    /**
//     * Returns as Bar Chart
//     * @return a DefaultStreamedContent
//     */
//    public DefaultStreamedContent getAsBarChart() {
//        ChartDisplayer chartDisplayer = new ChartDisplayer();
//        return chartDisplayer.getBarChartImage(
//                "Bar Chart",
//                "Developer",
//                "Number of issues",
//                defaultCategoryDataset,
//                900,
//                600);
//    }
//
//    /**
//     * Returns as Stacked Bar Chart
//     * @return a DefaultStreamedContent
//     */
//    public DefaultStreamedContent getAsStackedBarChart() {
//        ChartDisplayer chartDisplayer = new ChartDisplayer();
//        return chartDisplayer.getStackedBarChartImage(
//                "Stacked Bar",
//                "Quality engineer",
//                "Number of issues",
//                categoryDataset,
//                900,
//                600);
//    }
//
//    /**
//     * Returns as Gantt Chart
//     * @return a DefaultStreamedContent
//     */
//    public DefaultStreamedContent getAsGanttChart() {
//        ChartDisplayer chartDisplayer = new ChartDisplayer();
//        return chartDisplayer.getGanttChartImage(
//                "Gantt",
//                "Task",
//                "Date",
//                intervalCategoryDataset,
//                900,
//                600);
//    }
//

    /**
     * Returns the Chart to display
     * @return a chart to be displayed
     */
    public DefaultStreamedContent getChart(String reportDescriptor, String axisX, String axisY, int height, int width) {
        ChartDisplayer chartDisplayer = new ChartDisplayer();
        DefaultStreamedContent defaultStreamedContent =
                new DefaultStreamedContent();

        if (type.equals(ChartType.BARCHART)) {
            defaultStreamedContent = chartDisplayer.getBarChartImage(
                    reportDescriptor,
                    axisX,
                    axisY,
                    defaultCategoryDataset,
                    height,
                    width);

        } else if (type.equals(ChartType.PIECHART)) {
            defaultStreamedContent = chartDisplayer.getPieChartImage(
                    pieDataset,
                    reportDescriptor,
                    height,
                    width);

        } else if (type.equals(ChartType.STACKEDBARCHART)) {
            defaultStreamedContent = chartDisplayer.getStackedBarChartImage(
                    reportDescriptor,
                    axisX,
                    axisY,
                    categoryDataset,
                    height,
                    width);

        } else if (type.equals(ChartType.LINECHART)) {
            defaultStreamedContent = chartDisplayer.getLineChartImage(
                    lineDataStructureList,
                    reportDescriptor,
                    axisX,
                    axisY,
                    height,
                    width);

        } else if (type.equals(ChartType.GANTTCHART)) {
            defaultStreamedContent = chartDisplayer.getGanttChartImage(
                    reportDescriptor,
                    axisX,
                    axisY,
                    intervalCategoryDataset,
                    height,
                    width);

        } else {
            System.out.println("Type is not supported: " + type.toString());
        }

        return defaultStreamedContent;
    }
}
