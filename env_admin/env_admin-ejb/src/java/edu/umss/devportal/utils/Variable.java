/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.utils;

import edu.umss.devportal.common.IssueTracker;
import edu.umss.devportal.common.ProjectTracker;
import edu.umss.devportal.common.SourceCodeManager;
import java.util.HashMap;

/**
 *
 * @author Roger Ayaviri
 */
public class Variable {

    public static HashMap<String, Class> getToolTypes(){
        HashMap<String, Class> hashMap = new HashMap<String, Class>();
        hashMap.put("ProjectTracker", ProjectTracker.class);
        hashMap.put("IssueTracker", IssueTracker.class);
        hashMap.put("SourceCodeManager", SourceCodeManager.class);
        return hashMap;
    }

}
