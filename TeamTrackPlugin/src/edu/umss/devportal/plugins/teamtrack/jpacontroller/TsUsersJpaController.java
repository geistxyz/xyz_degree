/*
 *  @(#)TsUsersJpaController.java   05-dic-2010
 */

package edu.umss.devportal.plugins.teamtrack.jpacontroller;

import edu.umss.devportal.plugins.teamtrack.jpacontroller.common.AbstractJpaController;
import edu.umss.devportal.plugins.teamtrack.model.TsUsers;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Alex Arenas
 * @author July Camacho
 */
public final class TsUsersJpaController extends AbstractJpaController<Integer, TsUsers>{

    /**
     * Default constructor.
     */
    public TsUsersJpaController() {
        super(TsUsers.class);
    }

    /**
     * @see AbstractJpaController#getTableName()
     */
    public String getTableName() {
        return "Users";
    }
    /**
     *
     * @param userId
     * @return
     */
    public String getUserNameById(int userId){
        EntityManager em = getEntityManager();
        try{
            Query query = em.createNamedQuery("TsUsers.findByTsId");
            TsUsers user = (TsUsers)query.setParameter("tsId", userId).getSingleResult();
            return user.getTsName();
        }
        finally{
            em.close();
        }
    }

}
