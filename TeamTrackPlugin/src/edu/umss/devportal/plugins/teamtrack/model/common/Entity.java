/*
 *  @(#)AbstractEntity.java   05-dic-2010
 */

package edu.umss.devportal.plugins.teamtrack.model.common;

/**
 * Class that permits manage entity controllers in a generic way.
 *
 * @author Alex Arenas
 * @version 1.0
 * @param <R> entity identifier type.
 */
public interface Entity<R> {

    /**
     * @return the entity's identifier.
     */
    public R getTsId();

    /**
     * @param id set the entity's identifier.
     */
    public void setTsId(R id);
    
}
