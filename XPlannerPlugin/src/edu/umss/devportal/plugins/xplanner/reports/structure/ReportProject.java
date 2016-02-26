/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.xplanner.reports.structure;

import edu.umss.devportal.plugins.xplanner.config.XPlannerConfig;
import edu.umss.devportal.plugins.xplanner.reports.utils.IterationFilter;
import edu.umss.devportal.plugins.xplanner.reports.utils.CollectionUtility;
import edu.umss.edu.devportal.exception.NoToolServerConnectionException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import localhost.xplanner.soap.XPlanner.XPlanner;
import org.xplanner.soap.IterationData;
import org.xplanner.soap.ProjectData;

/**
 *
 * @author =)
 */
public class ReportProject extends CollectionUtility<Iteration,IterationColumn>{

    private XPlanner service ;

    private ProjectData project;

    private List<Iteration> iterations ;

    private IterationFilter iterationFilter ;

    private static final Logger logger = Logger.getLogger(ReportProject.class.getName());

    public ReportProject(XPlanner service) throws NoToolServerConnectionException{
        if(service == null){
            throw new NoToolServerConnectionException(XPlannerConfig.ToolName);
        }
        this.service = service ;
        iterationFilter = new IterationFilter();
        iterations = new ArrayList<Iteration>();

    }

    public ReportProject(XPlanner service, ProjectData project) {
        this.project = project ;
        this.service = service ;
    }

    public List<Iteration> getIterations (){
        iterations.clear();
        try {
            IterationData[] iterations1 = service.getIterations(project.getId());
            for (IterationData iterationData : iterations1) {
                Iteration iteration = new Iteration(service, iterationData);
                iterations.add(iteration);
            }
        } catch (RemoteException ex) {
            logger.log(Level.SEVERE, "Can't recover the iterations", ex);
        }
        return iterations;
    }

    public void setProjectId(int idProject) {
        try {
             project = service.getProject(idProject);
        } catch (RemoteException ex) {
           logger.log(Level.SEVERE, "Can't recover the ProjectData.", ex);
        }
    }

  
    public String getProjectName() {
        return project.getName();
    }

    public Iteration getCurrentIteration() {
        Iteration currentIteration = null;
        try {
            IterationData iterationdata = service.getCurrentIteration(project.getId());
            currentIteration = new Iteration(service, iterationdata);

        } catch (RemoteException ex) {
            logger.log(Level.SEVERE, "Can't recover the current iteration.", ex);
        }
        return currentIteration;
    }

    public List<Iteration> getIterationsByProperty ( IterationColumn property , Object value ){
      return  super.filterByProperty(getIterations(), iterationFilter, property, value);
    }

}
