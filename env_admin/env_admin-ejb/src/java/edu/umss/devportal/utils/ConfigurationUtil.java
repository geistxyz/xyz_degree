/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.utils;

import edu.umss.devportal.entity.ToolByProject;
import edu.umss.devportal.entity.ToolProjectParameter;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Roger Ayaviri
 */
public class ConfigurationUtil {

    public static HashMap<String, String> getConfiguration(ToolByProject toolByProject) {

        HashMap<String, String> config = new HashMap();

        Iterator<ToolProjectParameter> iterator = toolByProject.getParameters().iterator();
        while (iterator.hasNext()) {
            ToolProjectParameter toolProjectParameter = iterator.next();
            config.put(toolProjectParameter.getName(), toolProjectParameter.getAssignedValue());
        }
        return config;
    }

}
