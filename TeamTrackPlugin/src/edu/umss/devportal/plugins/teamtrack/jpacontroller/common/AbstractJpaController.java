/*
 *  @(#)AbstractJpaController.java   05-dic-2010
 */
package edu.umss.devportal.plugins.teamtrack.jpacontroller.common;

import edu.umss.devportal.plugins.teamtrack.jpacontroller.TsLastidsJpaController;
import edu.umss.devportal.plugins.teamtrack.jpacontroller.exceptions.NonexistentEntityException;
import edu.umss.devportal.plugins.teamtrack.jpacontroller.exceptions.PreexistingEntityException;
import edu.umss.devportal.plugins.teamtrack.model.common.Entity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Abstract class that implements the most methods of JpaController intercface.
 *
 * @author Alex Arenas
 * @version 1.0
 * @param <R> entity identifier type.
 * @param <T> entity type.
 */
public abstract class AbstractJpaController<R, T extends Entity<R>> implements JpaController<T, R> {

    protected static final Logger logger = Logger.getLogger(JpaController.class.getName());
    private Class<T> entityClass;

    /**
     * Constructor.
     *
     * @param entityClass entity class.
     */
    public AbstractJpaController(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Permits create a default entity manager.
     *
     * @return the default entity manager.
     */
    public EntityManager getEntityManager() {
        //return emf.createEntityManager();
        return DbConnection.getInstance().getEntityManager();
    }

    /**
     * @see JpaController#create(java.lang.Object)
     */
    public void create(T t) throws PreexistingEntityException, Exception {
        logger.log(Level.INFO, "Create entity {0}", t);

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            TsLastidsJpaController lastidsController = new TsLastidsJpaController();
            t.setTsId((R) lastidsController.getId(getTableName()));
            em.persist(t);

            em.getTransaction().commit();
        } catch (Exception ex) {
            logger.log(Level.WARNING, ex.getLocalizedMessage());
            if (find(t.getTsId()) != null) {
                String msg = "Entity " + t + " already exists.";
                throw new PreexistingEntityException(msg, ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }

        logger.log(Level.INFO, "The entity was created sucessfully");
    }

    /**
     * @see JpaController#edit(t)
     */
    public void edit(T t) throws NonexistentEntityException, Exception {
        logger.log(Level.INFO, "Edit the entity: {0}", t);

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            t = em.merge(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
            logger.warning(ex.getLocalizedMessage());
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                R id = t.getTsId();
                if (find(id) == null) {
                    throw new NonexistentEntityException("The entity with id "
                            + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }

        logger.info("The entity was edited sucessfully.");
    }

    /**
     * @see JpaController#destroy(id)
     */
    public void destroy(R id) throws NonexistentEntityException {
        logger.log(Level.INFO, "Destroy the entity with identifier = {0}", id);

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            T entity;
            try {
                entity = em.getReference(entityClass, id);
                entity.getTsId();
            } catch (EntityNotFoundException enfe) {
                logger.warning(enfe.getLocalizedMessage());
                throw new NonexistentEntityException("The entity with id " +
                        id + " no longer exists.", enfe);
            }
            em.remove(entity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }

        logger.info("The entity with was destroyed sucessfully");
    }

    /**
     * @see JpaController#find(java.lang.Object)
     */
    public T find(R id) {
        logger.log(Level.INFO, "Find entity with identifier {0}", id);

        EntityManager em = getEntityManager();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }

    /**
     * @see JpaController#findEntities()
     */
    public List<T> findEntities() {
        logger.info("Find all entities");
        return findTsProjectsEntities(true, -1, -1);
    }

    /**
     * @see JpaController#findEntities(int, int)
     */
    public List<T> findEntities(int maxResults, int firstResult) {
        logger.log(Level.INFO,
                "Find paginated entityes starting in {0} and having as maximum{1}elements.",
                new Object[]{firstResult, maxResults});
        return findTsProjectsEntities(false, maxResults, firstResult);
    }

    private List<T> findTsProjectsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * @see JpaController#getCount()
     */
    public int getCount() {
        logger.info("Get entities count");

        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<T> rt = cq.from(entityClass);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
