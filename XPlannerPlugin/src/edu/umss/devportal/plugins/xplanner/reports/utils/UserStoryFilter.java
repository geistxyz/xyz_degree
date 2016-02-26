/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.xplanner.reports.utils;

import edu.umss.devportal.plugins.xplanner.reports.structure.UserStory;
import edu.umss.devportal.plugins.xplanner.reports.structure.UserStoryColumn;

/**
 *
 * @author =)
 */
public class UserStoryFilter implements Filter<UserStory, UserStoryColumn> {

    @Override
    public boolean filterByProperty(UserStory e, UserStoryColumn property, Object object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
