/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.teamtrack.jpacontroller;

import edu.umss.devportal.plugins.teamtrack.jpacontroller.common.AbstractJpaController;
import edu.umss.devportal.plugins.teamtrack.model.TsSelections;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author July Camacho
 */
public final class TsSelectionsJpaController extends AbstractJpaController<Integer, TsSelections> {
    public TsSelectionsJpaController(){
        super(TsSelections.class);
    }

    public String getTableName() {
        return "Selections";
    }
/***
 *
 * @param fielId
 * @return
 */
    public List<TsSelections> getSelectionsByFielId(int fielId){
        EntityManager em = getEntityManager();
        List<TsSelections> result = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<TsSelections> cq = cb.createQuery(TsSelections.class);
            Root<TsSelections> selections = cq.from(TsSelections.class);
            cq.where(cb.equal(selections.get("tsFldid"), fielId));
            Query q = em.createQuery(cq);
            result = q.getResultList();
        }
        catch(Exception e){
            logger.log(Level.SEVERE, e.getMessage());
        }
        finally {
            em.close();
        }
        return result;
    }
    /***
     *
     * @param name
     * @return
     */
    public int getSelectionId(String name,int severityId){
        EntityManager em = getEntityManager();
        int selectionId = 0;

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<TsSelections> cq = cb.createQuery(TsSelections.class);
            Root<TsSelections> selections = cq.from(TsSelections.class);
            cq.where(cb.equal(selections.get("tsName"), name));
            Query q = em.createQuery(cq);
            List<TsSelections> selectionsResult = q.getResultList();
            for(TsSelections tsSelection : selectionsResult){
                if(tsSelection.getTsFldid() == severityId){
                    selectionId = tsSelection.getTsId();
                    break;
                }
            }
        }
        catch(Exception e){
            logger.log(Level.SEVERE, e.getMessage());
        }
        finally {
            em.close();
        }
        return selectionId;
    }
    /***
     *
     * @param sevId
     * @return
     */
    public String getSelectionById(int sevId){
    EntityManager em = getEntityManager();
    String result = null;
        try{
            Query query = em.createNamedQuery("TsSelections.findByTsId");
            TsSelections sevSelection = (TsSelections)query.setParameter("tsId", sevId).getSingleResult();
            result = sevSelection.getTsName();
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
