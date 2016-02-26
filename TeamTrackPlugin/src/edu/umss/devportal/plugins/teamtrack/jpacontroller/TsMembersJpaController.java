/*
 *  @(#)TsMembersJpaController.java   05-dic-2010
 */

package edu.umss.devportal.plugins.teamtrack.jpacontroller;

import edu.umss.devportal.plugins.teamtrack.jpacontroller.common.AbstractJpaController;
import edu.umss.devportal.plugins.teamtrack.model.TsMembers;

/**
 *
 * @author Alex Arenas
 */
public final class TsMembersJpaController extends AbstractJpaController<Integer, TsMembers>{

    /**
     * Default constructor.
     */
    public TsMembersJpaController() {
        super(TsMembers.class);
    }

    /**
     * @see AbstractJpaController#getTableName()
     */
    public String getTableName() {
        return "Members";
    }
}
