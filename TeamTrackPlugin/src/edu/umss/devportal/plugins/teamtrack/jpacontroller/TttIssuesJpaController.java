/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.teamtrack.jpacontroller;

import edu.umss.devportal.plugins.teamtrack.jpacontroller.common.AbstractJpaController;
import edu.umss.devportal.plugins.teamtrack.model.TttIssues;
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
public final class TttIssuesJpaController extends AbstractJpaController<Integer, TttIssues>{

    /**
     * Default constructor.
     */
    public TttIssuesJpaController(){
        super(TttIssues.class);
    }

    /**
     * @see AbstractJpaController#getTableName()
     */
    public String getTableName() {
        return "Issues";
    }
    /**
     *
     * @param projectId
     * @return
     */
    public List<TttIssues> getIssuesByProject(int projectId)
    {
        EntityManager em = getEntityManager();
        List<TttIssues> result = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<TttIssues> cq = cb.createQuery(TttIssues.class);
            Root<TttIssues> issues = cq.from(TttIssues.class);
            cq.where(cb.equal(issues.get("tsProjectid"), projectId));
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
    /**
     *
     * @param projectId
     * @param personId
     * @return
     */
    public List<TttIssues> getIssuesByUser (int projectId,int personId){

        EntityManager em = getEntityManager();
        List<TttIssues> issuesByPerson = new ArrayList<TttIssues>();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<TttIssues> cq = cb.createQuery(TttIssues.class);
            Root<TttIssues> issues = cq.from(TttIssues.class);
            cq.where(cb.equal(issues.get("tsProjectid"), projectId));
            Query q = em.createQuery(cq);
            List<TttIssues> issuesResult = q.getResultList();
            for(TttIssues issue : issuesResult){
                if(issue.getTsOwner() == personId)
                    issuesByPerson.add(issue);
            }
        }
        catch(Exception e){
            logger.log(Level.SEVERE, e.getMessage());
        }
        finally {
            em.close();
        }
        return issuesByPerson;
    }
    /**
     *
     * @param severity
     * @return
     */
    public List<TttIssues> getIssuesBySeverity(int severity){
        EntityManager em = getEntityManager();
        List<TttIssues> issues = null;
        try{
            Query query = em.createNamedQuery("TttIssues.findByTsSeverity");
             issues = query.setParameter("tsSeverity", severity).getResultList();
        }
        catch(Exception e){
            logger.log(Level.SEVERE, e.getMessage());
        }
        finally{
            em.close();
        }
        return issues;
    }

}
