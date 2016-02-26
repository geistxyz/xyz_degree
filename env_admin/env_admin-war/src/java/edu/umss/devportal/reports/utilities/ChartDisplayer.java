/*
 * @(#)ChartDisplayer.java 11/04/23
 *
 * Copyright (c) 2011 Universidad Mayor de San Simón
 * Cochabamba - Bolivia
 * All Rights Reserved.
 */

package edu.umss.devportal.reports.utilities;

import edu.umss.devportal.common.reports.LineDataStructure;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.time.SimpleTimePeriod;
import org.primefaces.model.DefaultStreamedContent;

/**
 * Class to Generate the Reports
 *
 * @author Edson Alvarez
 */
public class ChartDisplayer {

    private byte[] image;

    /**
     * Sets the chart image
     * @param image
     */
    public void setChartImage(byte[] image) {
        this.image = image;
    }

    /**
     * Gets a line chart
     * @param list a data structure list
     * @param title the title
     * @param axisX the domain
     * @param axisY the label
     * @param width the width
     * @param height the height
     * @return a Streamed content to generate Line Chart images
     */
    public DefaultStreamedContent getLineChartImage(
            List<LineDataStructure> list,
            String title,
            String axisX,
            String axisY,
            int width,
            int height) {

        // TODO: Title, domain and label, should be used parameters
        JFreeChart chart = ChartGenerator.createLineChart(
                title,
                axisX,
                axisY,
                DataTransformer.transformToLineDataSet(list));

        try {
            setChartImage(
                    ChartUtilities.encodeAsPNG(
                    chart.createBufferedImage(width, height)));
        } catch (IOException ex) {
            System.out.println("The Chart could not be generated: " + ex);
        }

        return new DefaultStreamedContent(new ByteArrayInputStream(image));
    }

    /**
     * Gets a Pie chart
     * @param pieDataset that represents the content of the chart
     * @param title the title
     * @param width the width
     * @param height the height
     * @return a default Streamed content that can be displayed
     */
    public DefaultStreamedContent getPieChartImage(
            PieDataset pieDataset,
            String title,
            int width,
            int height) {

        // TODO: Title, domain and label, should be used parameters
        JFreeChart chart =
                ChartGenerator.createPieChart(
                title,
                (DefaultPieDataset) pieDataset);

        try {
            setChartImage(ChartUtilities.encodeAsPNG(
                    chart.createBufferedImage(width, height)));
        } catch (IOException ex) {
            System.out.println("The Chart could not be generated: " + ex);
        }

        return new DefaultStreamedContent(new ByteArrayInputStream(image));
    }

    /**
     * Gets a Bar chart
     * @param title the title
     * @param axisX
     * @param axisY
     * @param defaultCategoryDataset the category set
     * @param width the width
     * @param height the height
     * @return a default Streamed content that can be displayed
     */
    public DefaultStreamedContent getBarChartImage(
            String title,
            String axisX,
            String axisY,
            DefaultCategoryDataset defaultCategoryDataset,
            int width,
            int height) {

        JFreeChart chart =
                ChartGenerator.createBarChart(
                title,
                axisX,
                axisY,
                (DefaultCategoryDataset) defaultCategoryDataset);
        try {
            setChartImage(
                    ChartUtilities.encodeAsPNG(
                    chart.createBufferedImage(width, height)));
        } catch (IOException ex) {
            System.out.println("The Chart could not be generated: " + ex);
        }

        return new DefaultStreamedContent(new ByteArrayInputStream(image));
    }

    /**
     * Gets a Stacked Bar chart
     * @param title the title
     * @param axisX
     * @param axisY
     * @param dataset the dataset
     * @param width the width
     * @param height the height
     * @return a default Streamed content that can be displayed
     */
    public DefaultStreamedContent getStackedBarChartImage(
            String title,
            String axisX,
            String axisY,
            CategoryDataset dataset,
            int width,
            int height) {

        JFreeChart chart = ChartGenerator.createStackedBarChart(
                title,
                axisX,
                axisY,
                dataset);

        try {
            setChartImage(
                    ChartUtilities.encodeAsPNG(
                    chart.createBufferedImage(width, height)));
        } catch (IOException ex) {
            System.out.println("The Chart could not be generated: " + ex);
        }

        return new DefaultStreamedContent(new ByteArrayInputStream(image));
    }

    /**
     * Gets a Gantt chart
     * @param title the tile
     * @param axisX the domain label
     * @param axisY the range label
     * @param dataset the dataset
     * @param width the width
     * @param height the height
     * @return a default Streamed content that can be displayed
     */
    public DefaultStreamedContent getGanttChartImage(
            String title,
            String axisX,
            String axisY,
            IntervalCategoryDataset dataset,
            int height,
            int width) {

        JFreeChart chart = ChartGenerator.createGanttChart(
                title,
                axisX,
                axisY,
                dataset);

        try {
            setChartImage(
                    ChartUtilities.encodeAsPNG(
                    chart.createBufferedImage(width, height)));
        } catch (IOException ex) {
            System.out.println("The Chart could not be generated: " + ex);
        }

        return new DefaultStreamedContent(new ByteArrayInputStream(image));
    }

    /**
     * Creates a sample dataset for a Gantt chart.
     *
     * @return The dataset.
     */
    public IntervalCategoryDataset createGanttDataset() {

        final TaskSeries s1 = new TaskSeries("Scheduled");
        s1.add(new Task("Write Proposal",
                new SimpleTimePeriod(date(1, Calendar.APRIL, 2001),
                date(5, Calendar.APRIL, 2001))));
        s1.add(new Task("Obtain Approval",
                new SimpleTimePeriod(date(9, Calendar.APRIL, 2001),
                date(9, Calendar.APRIL, 2001))));
        s1.add(new Task("Requirements Analysis",
                new SimpleTimePeriod(date(10, Calendar.APRIL, 2001),
                date(5, Calendar.MAY, 2001))));
        s1.add(new Task("Design Phase",
                new SimpleTimePeriod(date(6, Calendar.MAY, 2001),
                date(30, Calendar.MAY, 2001))));
        s1.add(new Task("Design Signoff",
                new SimpleTimePeriod(date(2, Calendar.JUNE, 2001),
                date(2, Calendar.JUNE, 2001))));
        s1.add(new Task("Alpha Implementation",
                new SimpleTimePeriod(date(3, Calendar.JUNE, 2001),
                date(31, Calendar.JULY, 2001))));
        s1.add(new Task("Design Review",
                new SimpleTimePeriod(date(1, Calendar.AUGUST, 2001),
                date(8, Calendar.AUGUST, 2001))));
        s1.add(new Task("Revised Design Signoff",
                new SimpleTimePeriod(date(10, Calendar.AUGUST, 2001),
                date(10, Calendar.AUGUST, 2001))));
        s1.add(new Task("Beta Implementation",
                new SimpleTimePeriod(date(12, Calendar.AUGUST, 2001),
                date(12, Calendar.SEPTEMBER, 2001))));
        s1.add(new Task("Testing",
                new SimpleTimePeriod(date(13, Calendar.SEPTEMBER, 2001),
                date(31, Calendar.OCTOBER, 2001))));
        s1.add(new Task("Final Implementation",
                new SimpleTimePeriod(date(1, Calendar.NOVEMBER, 2001),
                date(15, Calendar.NOVEMBER, 2001))));
        s1.add(new Task("Signoff",
                new SimpleTimePeriod(date(28, Calendar.NOVEMBER, 2001),
                date(30, Calendar.NOVEMBER, 2001))));

        final TaskSeries s2 = new TaskSeries("Actual");
        s2.add(new Task("Write Proposal",
                new SimpleTimePeriod(date(1, Calendar.APRIL, 2001),
                date(5, Calendar.APRIL, 2001))));
        s2.add(new Task("Obtain Approval",
                new SimpleTimePeriod(date(9, Calendar.APRIL, 2001),
                date(9, Calendar.APRIL, 2001))));
        s2.add(new Task("Requirements Analysis",
                new SimpleTimePeriod(date(10, Calendar.APRIL, 2001),
                date(15, Calendar.MAY, 2001))));
        s2.add(new Task("Design Phase",
                new SimpleTimePeriod(date(15, Calendar.MAY, 2001),
                date(17, Calendar.JUNE, 2001))));
        s2.add(new Task("Design Signoff",
                new SimpleTimePeriod(date(30, Calendar.JUNE, 2001),
                date(30, Calendar.JUNE, 2001))));
        s2.add(new Task("Alpha Implementation",
                new SimpleTimePeriod(date(1, Calendar.JULY, 2001),
                date(12, Calendar.SEPTEMBER, 2001))));
        s2.add(new Task("Design Review",
                new SimpleTimePeriod(date(12, Calendar.SEPTEMBER, 2001),
                date(22, Calendar.SEPTEMBER, 2001))));
        s2.add(new Task("Revised Design Signoff",
                new SimpleTimePeriod(date(25, Calendar.SEPTEMBER, 2001),
                date(27, Calendar.SEPTEMBER, 2001))));
        s2.add(new Task("Beta Implementation",
                new SimpleTimePeriod(date(27, Calendar.SEPTEMBER, 2001),
                date(30, Calendar.OCTOBER, 2001))));
        s2.add(new Task("Testing",
                new SimpleTimePeriod(date(31, Calendar.OCTOBER, 2001),
                date(17, Calendar.NOVEMBER, 2001))));
        s2.add(new Task("Final Implementation",
                new SimpleTimePeriod(date(18, Calendar.NOVEMBER, 2001),
                date(5, Calendar.DECEMBER, 2001))));
        s2.add(new Task("Signoff",
                new SimpleTimePeriod(date(10, Calendar.DECEMBER, 2001),
                date(11, Calendar.DECEMBER, 2001))));

        final TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);
        collection.add(s2);

        return collection;
    }

    /**
     * Utility method for creating <code>Date</code> objects.
     *
     * @param day  the date.
     * @param month  the month.
     * @param year  the year.
     *
     * @return a date.
     */
    private static Date date(final int day, final int month, final int year) {

        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        final Date result = calendar.getTime();
        return result;
    }
}
