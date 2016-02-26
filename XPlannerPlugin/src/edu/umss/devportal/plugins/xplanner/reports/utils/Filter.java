/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.xplanner.reports.utils;

/**
 *
 * @author =)
 */
public interface Filter<T,P> {
    public boolean filterByProperty( T e , P property, Object object );
}
