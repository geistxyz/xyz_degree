/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.common.reports;

import edu.umss.devportal.common.ProjectRole;
import java.util.List;

/**
 *
 * @author Edson
 */
public class ReportDescriptor {
    private String name;
    private String description;    
    private String path;
    private String tool;
    private List<ProjectRole> viewedByList;
    private List<ProjectRole> editedByList;
    private TypeOfReport type;

    private String axisX;
    private String axisY;
    private int height;
    private int width;

    public ReportDescriptor(String name, String description, String path, String tool, List<ProjectRole> viewedByList, List<ProjectRole> editedByList, TypeOfReport type) {
        this.name = name;
        this.description = description;
        this.path = path;
        this.tool = tool;
        this.viewedByList = viewedByList;
        this.editedByList = editedByList;
        this.type = type;
    }

    public ReportDescriptor(String name, String description, String path, String tool, List<ProjectRole> viewedByList, List<ProjectRole> editedByList, TypeOfReport type, String axisX, String axisY, int height, int width) {
        this.name = name;
        this.description = description;
        this.path = path;
        this.tool = tool;
        this.viewedByList = viewedByList;
        this.editedByList = editedByList;
        this.type = type;
        this.axisX = axisX;
        this.axisY = axisY;
        this.height = height;
        this.width = width;
    }

    public ReportDescriptor() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public TypeOfReport getType() {
        return type;
    }

    public void setType(TypeOfReport type) {
        this.type = type;
    }

    public List<ProjectRole> getEditedByList() {
        return editedByList;
    }

    public void setEditedByList(List<ProjectRole> editedByList) {
        this.editedByList = editedByList;
    }

    public List<ProjectRole> getViewedByList() {
        return viewedByList;
    }

    public void setViewedByList(List<ProjectRole> viewedByList) {
        this.viewedByList = viewedByList;
    }

    public String getAxisX() {
        return axisX;
    }

    public void setAxisX(String axisX) {
        this.axisX = axisX;
    }

    public String getAxisY() {
        return axisY;
    }

    public void setAxisY(String axisY) {
        this.axisY = axisY;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
