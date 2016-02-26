/*
 * @(#)DataTransformer.java 11/04/23
 *
 * Copyright (c) 2011 Universidad Mayor de San Simón
 * Cochabamba - Bolivia
 * All Rights Reserved.
 */
package edu.umss.devportal.reports.utilities;

import edu.umss.devportal.common.reports.BarDataStructure;
import edu.umss.devportal.common.reports.GanttDataStructure;
import edu.umss.devportal.common.reports.LineDataStructure;
import edu.umss.devportal.common.reports.MagicNumberData;
import edu.umss.devportal.common.reports.PieDataStructure;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 * This class transforms the retrieved data into information that the report
 * generators understands to generate the report.
 * @author Edson Alvarez
 */
public class DataTransformer {

    /**
     * Transforms a List of DataStructure into a DefaultCategoryDataset, this
     * type is recognized by JFreeChart to generate reports
     * @param arrayValues
     * @return the transformed List into DefaultCategoryDataset
     */
    public static DefaultCategoryDataset transformToLineDataSet(
            List<LineDataStructure> arrayValues) {

        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

        Iterator<LineDataStructure> iterator = arrayValues.iterator();

        while (iterator.hasNext()) {
            LineDataStructure data = iterator.next();
            dataSet.setValue(
                    data.getValue(),
                    data.getDomainAxis(),
                    data.getColumnAxis());
        }
        return dataSet;
    }

    /**
     * Transforms a list of MagicNumbersData into list of Integers
     * @param data
     * @return List<Integer>, list of Integers
     */
    public static List<Integer> transformToIntegers(List<MagicNumberData> data) {

        List<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < data.size(); i++) {
            list.add(Integer.parseInt(data.get(i).getValue().toString()));
        }

        return list;
    }

    /**
     * Transforms a list of pie data structure into a Pie dataset
     * @param pieDataStructure
     * @return a pie dataset
     */
    public static PieDataset transformToPieDataSet(
            List<PieDataStructure> pieDataStructure) {

        final DefaultPieDataset dataset = new DefaultPieDataset();

        for (int i = 0; i < pieDataStructure.size(); i++) {
            dataset.setValue(
                    pieDataStructure.get(i).getKey(),
                    pieDataStructure.get(i).getValue());
        }

        return dataset;
    }

    /**
     * Transforms a list of BarDataStructure into DefaultCategoryDataset
     * @param barDataStructure
     * @return a DefaultCategoryDataset
     */
    public static DefaultCategoryDataset transformToDefaultCategoryDataSet(
            List<BarDataStructure> barDataStructure) {

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < barDataStructure.size(); i++) {
            dataset.setValue(
                    barDataStructure.get(i).getValue(),
                    barDataStructure.get(i).getSeries(),
                    barDataStructure.get(i).getCategory());
        }

        return dataset;
    }

    /**
     * Transforms a list of GanttDataStructure into IntervalCategoryDataset
     * @param ganttDataStructures
     * @return a IntervalCategoryDataset
     */
    public static IntervalCategoryDataset transformToIntervalCategoryDataset(
            List<GanttDataStructure> ganttDataStructures) {

        final TaskSeriesCollection collection = new TaskSeriesCollection();

        String currentSerie = null;
        TaskSeries taskSeries = null;

        for (int i = 0; i < ganttDataStructures.size(); i++) {
            if (
                    (currentSerie == null) ||
                    (currentSerie != ganttDataStructures.get(i).getSerie()) ||
                    (i == ganttDataStructures.size() - 1)) {
                if (taskSeries != null) {
                    collection.add(taskSeries);
                }

                taskSeries = new TaskSeries(ganttDataStructures.get(i).
                        getSerie());

            }
            taskSeries.add(
                    new Task(ganttDataStructures.get(i).getDescription(),
                    ganttDataStructures.get(i).getStartDate(),
                    ganttDataStructures.get(i).getEndDate()));            
            currentSerie = ganttDataStructures.get(i).getSerie();
        }

        return collection;
    }
}
