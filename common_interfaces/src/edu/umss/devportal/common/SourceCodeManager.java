/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.common;

import edu.umss.devportal.common.reports.DynamicStructure;
import java.util.HashMap;

/**
 * Extended Interface for manage System control versions
 * @author Arminda Yovana Soto
 */
public interface SourceCodeManager extends ToolPlugin {

    public DynamicStructure getChangeSet(HashMap<String, String> parameters);
}
