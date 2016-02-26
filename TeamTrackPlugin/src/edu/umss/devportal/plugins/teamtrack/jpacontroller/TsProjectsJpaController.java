/*
 *  @(#)TsProjectsJpaController.java   05-dic-2010
 */

package edu.umss.devportal.plugins.teamtrack.jpacontroller;

import edu.umss.devportal.plugins.teamtrack.jpacontroller.common.AbstractJpaController;
import edu.umss.devportal.plugins.teamtrack.model.TsProjects;
import javax.persistence.Query;

/**
 *
 * @author Alex Arenas
 */
public final class TsProjectsJpaController extends AbstractJpaController<Integer, TsProjects>{

    /**
     * Default constructor.
     */
    public TsProjectsJpaController() {
        super(TsProjects.class);
    }

    /**
     * @see AbstractJpaController#getTableName()
     */
    public String getTableName() {
        return "Projects";
    }

    /**
     * @return the project sequence for the next project entity.
     */
    public Integer getNextProjectSequence(){
        logger.info("Get the next project sequence");
        
        Query query = getEntityManager().createNamedQuery("TsProjects.findMaxTsProjectSequence");
        Integer lastMaxProjectSequence = (Integer)query.getSingleResult();

        return lastMaxProjectSequence + 1000;
    }
}
