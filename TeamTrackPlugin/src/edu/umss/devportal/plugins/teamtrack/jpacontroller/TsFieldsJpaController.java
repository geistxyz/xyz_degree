/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.teamtrack.jpacontroller;

import edu.umss.devportal.plugins.teamtrack.jpacontroller.common.AbstractJpaController;
import edu.umss.devportal.plugins.teamtrack.model.TsFields;
import java.util.logging.Level;
import javax.persistence.Query;
import javax.persistence.EntityManager;

/**
 *
 * @author July Camacho
 */
public final class TsFieldsJpaController extends AbstractJpaController<Integer, TsFields>{

    /**
     * Default Constructor
     */
    public TsFieldsJpaController(){
        super(TsFields.class);
    }
    /**
     * Obtain the Data Base table name
     * @return A name String
     */
    public String getTableName() {
        return "Fields";
    }
    /**
     * Obtain the severity ID
     * @return ID Integer
     */
    public int getSeverityId(){
        EntityManager em = getEntityManager();
        TsFields tsField = null;
        try{
            Query query = em.createNamedQuery("TsFields.findByTsDbname");
            tsField = (TsFields)query.setParameter("tsDbname", "SEVERITY").getSingleResult();
        }
        catch(Exception e){
            em.close();
            logger.log(Level.SEVERE, e.getMessage());
        }
        return tsField.getTsId();
    }
    /**
     *
     * @param dbName
     * @return
     */
    public int getSeverityId(String dbName ){
        EntityManager em = getEntityManager();
        TsFields tsField = null;
        try{
            Query query = em.createNamedQuery("TsFields.findByTsDbname");
            tsField= (TsFields)query.setParameter("tsDbname", dbName).getSingleResult();
        }
        catch(Exception e){
            em.close();
            logger.log(Level.SEVERE, e.getMessage());
        }
        return tsField.getTsId();
    }
}
