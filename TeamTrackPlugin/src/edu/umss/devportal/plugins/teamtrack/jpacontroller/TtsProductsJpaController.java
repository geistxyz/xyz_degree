/*
 *  @(#)TtsProductsJpaController.java   05-dic-2010
 */

package edu.umss.devportal.plugins.teamtrack.jpacontroller;

import edu.umss.devportal.plugins.teamtrack.jpacontroller.common.AbstractJpaController;
import edu.umss.devportal.plugins.teamtrack.model.TtsProducts;

/**
 *
 * @author Alex Arenas
 */
public final class TtsProductsJpaController extends AbstractJpaController<Integer, TtsProducts>{

    /**
     * Default constructor.
     */
    public TtsProductsJpaController() {
        super(TtsProducts.class);
    }

    /**
     * @see AbstractJpaController#getTableName()
     */
    public String getTableName() {
        return "Products";
    }


}
