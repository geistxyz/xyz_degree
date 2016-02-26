/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.common.reports;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edson
 */
public class ChartReport implements DataStructure{
    List<LineDataStructure> lineDataStructures = new ArrayList<LineDataStructure>();
    List<PieDataStructure> pieDataStructures = new ArrayList<PieDataStructure>();
    List<BarDataStructure> barDataStructures = new ArrayList<BarDataStructure>();
    List<GanttDataStructure> ganttDataStructures = new ArrayList<GanttDataStructure>();

    public ChartReport() {
    }

    public List<LineDataStructure> getLineDataStructures() {
        return lineDataStructures;
    }

    public void setLineDataStructureList(List<LineDataStructure> list) {
        this.lineDataStructures = list;
    }

    public List<PieDataStructure> getPieDataStructures() {
        return pieDataStructures;
    }

    public void setPieDataStructures(List<PieDataStructure> pieDataStructures) {
        this.pieDataStructures = pieDataStructures;
    }

    public List<BarDataStructure> getBarDataStructures() {
        return barDataStructures;
    }

    public void setBarDataStructures(List<BarDataStructure> barDataStructures) {
        this.barDataStructures = barDataStructures;
    }

    public List<GanttDataStructure> getGanttDataStructures() {
        return ganttDataStructures;
    }

    public void setGanttDataStructures(List<GanttDataStructure> ganttDataStructures) {
        this.ganttDataStructures = ganttDataStructures;
    }
}
