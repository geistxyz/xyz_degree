/*
 *  @(#)TsLastidsJpaController.java   05-dic-2010
 */

package edu.umss.devportal.plugins.teamtrack.jpacontroller;

import edu.umss.devportal.plugins.teamtrack.jpacontroller.common.AbstractJpaController;
import edu.umss.devportal.plugins.teamtrack.jpacontroller.exceptions.NonexistentEntityException;
import edu.umss.devportal.plugins.teamtrack.model.TsLastids;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Alex Arenas
 */
public final class TsLastidsJpaController extends AbstractJpaController<Integer, TsLastids>{

    /**
     * Default constructor.
     */
    public TsLastidsJpaController() {
        super(TsLastids.class);
    }

    /**
     * @see AbstractJpaController#getTableName()
     */
    public String getTableName() {
        return "LastIds";
    }

    public Integer getId(String tableName) throws NonexistentEntityException, Exception{
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("TsLastids.findByTsName");
        TsLastids lastId = (TsLastids)query.setParameter("tsName", tableName).getSingleResult();
        lastId.setTsLastid(lastId.getTsLastid() + 1);
        edit(lastId);
        return lastId.getTsLastid();
    }
}
