/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.xplanner.reports.structure;

import edu.umss.devportal.plugins.xplanner.reports.utils.CollectionUtility;
import edu.umss.devportal.plugins.xplanner.reports.utils.Filter;
import edu.umss.devportal.plugins.xplanner.reports.utils.UserStoryFilter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import localhost.xplanner.soap.XPlanner.XPlanner;
import org.xplanner.soap.IterationData;
import org.xplanner.soap.UserStoryData;

/**
 *
 * @author =)
 */
public class Iteration extends CollectionUtility<UserStory,UserStoryColumn>  {

    private IterationData iteration;
    private XPlanner service;
    private List<UserStory> stories ;

    private UserStoryFilter filter ;

    private static final Logger logger = Logger.getLogger(ReportProject.class.getName());

    public Iteration(XPlanner service , IterationData iterationData) {
        iteration = iterationData;
        this.service = service;
        stories = new ArrayList<UserStory>();
        filter = new UserStoryFilter();

    }


    public List<UserStory> getUserStories(){

        stories.clear();
        
        try {
           UserStoryData [] userStories = service.getUserStories(iteration.getId());
            for (UserStoryData userStoryData : userStories) {
             UserStory userStory = new UserStory(service, userStoryData);
             stories.add(userStory);
            }

        } catch (RemoteException ex) {
            logger.log(Level.SEVERE, "Can't recover the user stories.", ex);
        }
        return stories;
    }

    public List<UserStory> getUserStoriesByProperty(UserStoryColumn property , Object value ){
        return super.filterByProperty(getUserStories(), filter, property, value);
    }

    public String getName(){
        return iteration.getName();
    }
    
    public Calendar getStartDate (){
    return iteration.getStartDate() ;
    }

    public Calendar getEndDate(){
    return iteration.getEndDate();
    }

    public double getAdjustedEstimatedHours (){
    return iteration.getAdjustedEstimatedHours();
    }

}
