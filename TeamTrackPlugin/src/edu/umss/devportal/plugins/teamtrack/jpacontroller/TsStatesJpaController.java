/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.teamtrack.jpacontroller;

import edu.umss.devportal.plugins.teamtrack.jpacontroller.common.AbstractJpaController;
import edu.umss.devportal.plugins.teamtrack.model.TsStates;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author July Camacho
 */
public class TsStatesJpaController extends AbstractJpaController<Integer, TsStates>{

    public TsStatesJpaController(){
        super(TsStates.class);
    }
    public String getTableName() {
        return "States";
    }
    /**
     *
     * @param stateName
     * @return
     */
    public int getStateId(String stateName){
        EntityManager em = getEntityManager();
        int result = 0;
        try{
            Query query = em.createNamedQuery("TsStates.findByTsName");
            TsStates state = (TsStates)query.setParameter("tsName", stateName).getSingleResult();
            result = state.getTsId();
        }
        catch(Exception e){
            logger.log(Level.SEVERE, e.getMessage());
        }
        finally{
            em.close();
        }
        return result;
    }

    /**
     *
     * @param stateId
     * @return
     */
    public String getStateName(int stateId){
        EntityManager em = getEntityManager();
        String result = null;
        try{
            Query query = em.createNamedQuery("TsStates.findByTsId");
            TsStates state = (TsStates)query.setParameter("tsId", stateId).getSingleResult();
            result = state.getTsName();
        }
        catch(Exception e){
            logger.log(Level.SEVERE, e.getMessage());
        }
        finally{
            em.close();
        }
        return result;
    }
}
