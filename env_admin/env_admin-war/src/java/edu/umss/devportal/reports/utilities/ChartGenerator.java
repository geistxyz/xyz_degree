/*
 * @(#)ChartGenerator.java 11/04/21
 *
 * Copyright (c) 2011 Universidad Mayor de San Simón
 * Cochabamba - Bolivia
 * All Rights Reserved.
 */

package edu.umss.devportal.reports.utilities;

import java.awt.Color;
import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 * Class to generate Charts
 * @author Edson Alvarez
 */
public class ChartGenerator {

    public static JFreeChart createPieChart(
            String chartTitle,
            DefaultPieDataset dataset) {

        JFreeChart chart = ChartFactory.createPieChart(
                chartTitle,
                dataset,
                true,
                true,
                false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        return chart;
    }

    /**
     * Creates a Bar Chart using JFreeChart
     * @param title the title
     * @param axisX the category
     * @param axisY the value
     * @param dataset the data set
     * @return returns a JFreeChart Bar chart
     */
    public static JFreeChart createBarChart(
            String title,
            String axisX,
            String axisY,
            DefaultCategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createBarChart(
                title,
                axisY,
                axisX,
                dataset,
                PlotOrientation.HORIZONTAL,
                true,
                true,
                true);
        return chart;
    }

    /**
     * Creates a Stacked Bar Chart using JFreeChart
     * @param charTitle the title
     * @param axisX
     * @param axisY
     * @param dataset the dataset
     * @return a JFreeChart Stacked Bar chart
     */
    public static JFreeChart createStackedBarChart(
            String charTitle,
            String axisX,
            String axisY,
            CategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createStackedBarChart(
                charTitle, // chart title
                axisX, // axis X label
                axisY, // axis Y label
                dataset, // data
                PlotOrientation.VERTICAL, // the plot orientation
                true, // legend
                true, // tooltips
                false // urls
                );

        return chart;
    }

    /**
     * Creates a Line Chart using JFreeChart
     * @param charTitle the chart
     * @param axisX the domain label
     * @param axisY the range label
     * @param dataset the dataset
     * @return a JFreeChart Line chart
     */
    public static JFreeChart createLineChart(
            String charTitle,
            String axisX,
            String axisY,
            CategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createLineChart(
                charTitle,
                axisX,
                axisY,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.getDomainAxis().setTickLabelFont(new Font("Serif", Font.ITALIC, 12));
        plot.getDomainAxis().setMaximumCategoryLabelLines(2);

        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setShapesVisible(true);
        renderer.setDrawOutlines(true);
        renderer.setUseFillPaint(true);
        renderer.setFillPaint(Color.red);
        return chart;
    }

    /**
     * Creates a Gantt Chart using JFreeChart
     * @param ganttTitle the title
     * @param axisX
     * @param axisY
     * @param dataset the dataset
     * @return a JFreeChart Gantt chart
     */
    public static JFreeChart createGanttChart(
            String ganttTitle,
            String axisX,
            String axisY,
            IntervalCategoryDataset dataset) {

        final JFreeChart chart = ChartFactory.createGanttChart(
                ganttTitle, // chart title
                axisX, // domain axis label
                axisY, // range axis label
                dataset, // data
                true, // include legend
                true, // tooltips
                false // urls
                );

        return chart;
    }
}
