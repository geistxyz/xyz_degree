/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.xplanner.reports.utils;

import edu.umss.devportal.plugins.xplanner.reports.structure.Iteration;
import edu.umss.devportal.plugins.xplanner.reports.structure.IterationColumn;

/**
 *
 * @author =)
 */
public class IterationFilter implements Filter<Iteration,IterationColumn>{

    @Override
    public boolean filterByProperty(Iteration e, IterationColumn columnName, Object object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
