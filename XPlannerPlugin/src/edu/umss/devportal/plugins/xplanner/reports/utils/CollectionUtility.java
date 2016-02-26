/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.xplanner.reports.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author =)
 */

public class CollectionUtility<T,P> {
    
    public List<T> filterByProperty( List<T> list, Filter filter , P property , Object value ){
        List<T> result  = new ArrayList<T>();
        for (T object : list) {
            if ( filter.filterByProperty(object, property, value )){
                result.add(object);
            }
        }
        return result;
    }

}
