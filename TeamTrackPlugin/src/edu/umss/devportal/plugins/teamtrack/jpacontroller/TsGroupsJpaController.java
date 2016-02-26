/*
 *  @(#)TsGroupsJpaController.java   05-dic-2010
 */

package edu.umss.devportal.plugins.teamtrack.jpacontroller;

import edu.umss.devportal.plugins.teamtrack.jpacontroller.common.AbstractJpaController;
import edu.umss.devportal.plugins.teamtrack.model.TsGroups;

/**
 *
 * @author Alex Arenas
 */
public final class TsGroupsJpaController extends AbstractJpaController<Integer, TsGroups>{

    /**
     * Default constructor.
     */
    public TsGroupsJpaController() {
        super(TsGroups.class);
    }

    /**
     * @see AbstractJpaController#getTableName()
     */
    public String getTableName() {
        return "Groups";
    }
}
